package com.mall.kmh.order.unitTest;

import com.mall.base.Ratio;
import com.mall.billing.entity.Billing;
import com.mall.customer.entity.Address;
import com.mall.customer.entity.Customer;
import com.mall.delivery.entity.Delivery;
import com.mall.order.entity.Order;
import com.mall.shop.entity.Category;
import com.mall.shop.entity.Location;
import com.mall.shop.entity.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.ZonedDateTime;

import static com.mall.kmh.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderEventTest {
    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    EntityManager em;

    @Test
    public void OrderEvent_발생() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category, Ratio.valueOf(0.1));
        em.persist(shop);

        Customer customer = new Customer("고객", ZonedDateTime.now(), new Address("광명시", "철산로", "1304-205"));
        em.persist(customer);

        em.flush();
        em.clear();

        //when
        Order order = aOrder().shopId(shop.getId()).build();
        order.payed(publisher);

        //then
        Delivery delivery = em.find(Delivery.class, 1L);
        assertThat(delivery).isNotNull();
        assertThat(delivery.getOrderId()).isEqualTo(order.getId());
        assertThat(delivery.getDeliveryStatus()).isEqualTo(Delivery.DeliveryStatus.DELIVERING);
    }

    @Test
    public void OrderDeliveredEvent_발생() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category, Ratio.valueOf(0.1));
        em.persist(shop);

        em.flush();
        em.clear();

        //when
        Order order = aOrder().shopId(shop.getId()).build();
        order.delivered(publisher);

        //then
        Billing billing = em.find(Billing.class, 1L);
        assertThat(billing).isNotNull();
        assertThat(order.getOrderStatus()).isEqualTo(Order.OrderStatus.DELIVERED);
    }
}
