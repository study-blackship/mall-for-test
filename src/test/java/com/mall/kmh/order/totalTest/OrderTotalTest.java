package com.mall.kmh.order.totalTest;

import com.mall.base.Money;
import com.mall.base.Ratio;
import com.mall.customer.entity.Address;
import com.mall.customer.entity.Customer;
import com.mall.order.entity.Order;
import com.mall.order.entity.OrderEntry;
import com.mall.order.entity.QOrder;
import com.mall.order.request.OrderEntryRequest;
import com.mall.order.request.OrderRequest;
import com.mall.order.response.OrderResponse;
import com.mall.order.service.OrderService;
import com.mall.shop.entity.Category;
import com.mall.shop.entity.Location;
import com.mall.shop.entity.Product;
import com.mall.shop.entity.Shop;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderTotalTest {

    @Autowired
    OrderService orderService;

    @Autowired
    EntityManager em;

    @Test
    public void Order_등록() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category, Ratio.valueOf(0.1));
        em.persist(shop);

        Customer customer = new Customer("고객", ZonedDateTime.now(), new Address("광명시", "철산로", "1304-205"));
        em.persist(customer);

        Product product1 = new Product(Money.wons(100_000), "수류탄", shop);
        Product product2 = new Product(Money.wons(100_000_000), "미사일", shop);
        em.persist(product1);
        em.persist(product2);

        em.flush();
        em.clear();

        //when
        OrderEntryRequest orderEntryRequest1 = OrderEntryRequest.builder()
                .productId(product1.getId())
                .count(2)
                .price(product1.getPrice())
                .build();

        OrderEntryRequest orderEntryRequest2 = OrderEntryRequest.builder()
                .productId(product2.getId())
                .count(3)
                .price(product2.getPrice())
                .build();

        OrderRequest orderRequest = OrderRequest.builder()
                .userId(customer.getId())
                .shopId(shop.getId())
                .orderStatus(Order.OrderStatus.ORDERED)
                .entryRequestList(List.of(orderEntryRequest1, orderEntryRequest2))
                .build();

        OrderResponse orderResponse = orderService.registerOrder(orderRequest);

        //then
        JPAQueryFactory query = new JPAQueryFactory(em);

        Order order = query.selectFrom(QOrder.order)
                .where(QOrder.order.id.eq(orderResponse.getId()))
                .fetchOne();

        assertThat(order).isNotNull();
        assertThat(order.getUserId()).isEqualTo(orderRequest.getUserId());
        assertThat(order.getShopId()).isEqualTo(orderRequest.getShopId());
        assertThat(order.getOrderStatus()).isEqualTo(Order.OrderStatus.ORDERED);
        assertThat(order.getOrderEntryList()).isNotEmpty();
        assertThat(order.getOrderEntryList().size()).isEqualTo(orderRequest.getEntryRequestList().size());
    }
}
