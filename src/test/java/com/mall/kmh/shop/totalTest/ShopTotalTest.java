package com.mall.kmh.shop.totalTest;

import com.mall.base.Money;
import com.mall.base.PageRequest;
import com.mall.base.Ratio;
import com.mall.shop.entity.*;
import com.mall.shop.repository.CategoryRepository;
import com.mall.shop.request.ShopCondition;
import com.mall.shop.request.ShopRequest;
import com.mall.shop.response.ShopResponse;
import com.mall.shop.service.ShopService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class ShopTotalTest {

    @Autowired
    EntityManager em;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ShopService shopService;

    @Test
    void Shop_paging_조회() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop1 = new Shop("이케아1호", new Location(Location.Dong.A, 1, 104), category, Ratio.valueOf(0.1));
        Shop shop2 = new Shop("이케아2호", new Location(Location.Dong.B, 1, 104), category, Ratio.valueOf(0.1));
        Shop shop3 = new Shop("이케아3호", new Location(Location.Dong.A, 2, 201), category, Ratio.valueOf(0.1));
        Shop shop4 = new Shop("이케아4호", new Location(Location.Dong.B, 2, 204), category, Ratio.valueOf(0.1));
        em.persist(shop1);
        em.persist(shop2);
        em.persist(shop3);
        em.persist(shop4);

        em.flush();
        em.clear();

        //when
        ShopCondition condition = ShopCondition.builder()
                .label("이케아")
                .dong(Location.Dong.A)
                .categoryId(category.getId())
                .build();
        PageRequest pageRequest = new PageRequest();
        pageRequest.getProperties().add("label");

        Page<ShopResponse> shopResponses = shopService.selectByCondition(condition, pageRequest);

        //then
        shopResponses.forEach(shop -> {
            assertThat(shop.getLocation().getDong()).isEqualTo(condition.getDong());
            assertThat(shop.getLabel()).contains(condition.getLabel());
            assertThat(shop.getCategory().getId()).isEqualTo(condition.getCategoryId());
        });
    }

    @Test
    void Shop을_등록() {
        //given
        Category category = new Category("침구류");
        em.persist(category);
        em.flush();
        em.clear();

        //when
        ShopRequest shopRequest = new ShopRequest("이케아", new Location(Location.Dong.A, 1, 104), category.getId(), Ratio.valueOf(0.1));
        ShopResponse shop = shopService.registerShop(shopRequest);

        //then
        JPAQueryFactory query = new JPAQueryFactory(em);
        Shop registered = query.selectFrom(QShop.shop)
                .where(QShop.shop.id.eq(shop.getId()))
                .fetchOne();

        assertThat(registered).isNotNull();
        assertThat(registered.getLabel()).isEqualTo(shopRequest.getLabel());
        assertThat(registered.getLocation().getDong()).isEqualTo(shopRequest.getLocation().getDong());
        assertThat(registered.getLocation().getHo()).isEqualTo(shopRequest.getLocation().getHo());
        assertThat(registered.getLocation().getFloor()).isEqualTo(shopRequest.getLocation().getFloor());
        assertThat(registered.getCategory().getLabel()).isEqualTo(category.getLabel());
    }

    @Test
    void Shop을_수정() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category, Ratio.valueOf(0.1));
        em.persist(shop);

        em.flush();
        em.clear();

        //when
        ShopRequest shopUpdateRequest = new ShopRequest(shop.getId(), "롯데마트", new Location(Location.Dong.B, 2, 201), category.getId(), Ratio.valueOf(0.1));
        ShopResponse shopResponse = shopService.updateShop(shopUpdateRequest);

        //then
        JPAQueryFactory query = new JPAQueryFactory(em);

        Shop updated = query.selectFrom(QShop.shop)
                .where(QShop.shop.id.eq(shopResponse.getId()))
                .fetchOne();

        assertThat(updated).isNotNull();
        assertThat(updated.getLabel()).isEqualTo(shopUpdateRequest.getLabel());
        assertThat(updated.getLocation().getDong()).isEqualTo(shopUpdateRequest.getLocation().getDong());
        assertThat(updated.getLocation().getHo()).isEqualTo(shopUpdateRequest.getLocation().getHo());
        assertThat(updated.getLocation().getFloor()).isEqualTo(shopUpdateRequest.getLocation().getFloor());
        assertThat(updated.getCategory().getLabel()).isEqualTo(category.getLabel());
    }

    @Test
    void Shop을_삭제() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category, Ratio.valueOf(0.1));
        em.persist(shop);

        Product product = new Product(Money.wons(10000), "침대", shop);
        em.persist(product);

        em.flush();
        em.clear();

        //when
        shopService.deleteShop(shop.getId());

        //then
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Shop> shops = query.selectFrom(QShop.shop)
                .where(QShop.shop.id.eq(shop.getId()))
                .fetch();

        List<Product> products = query.selectFrom(QProduct.product)
                .where(QProduct.product.shop.id.eq(shop.getId()))
                .fetch();

        assertThat(shops).isEmpty();
        products.forEach(p -> assertThat(p.getShop()).isNull());
    }

}
