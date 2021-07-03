package com.mall;

import com.mall.base.Money;
import com.mall.base.PageRequest;
import com.mall.shop.entity.*;
import com.mall.shop.mapper.ShopMapper;
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
class Test_강민형 {

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

        Shop shop1 = new Shop("이케아1호", new Location(Location.Dong.A, 1, 104), category);
        Shop shop2 = new Shop("이케아2호", new Location(Location.Dong.B, 1, 104), category);
        Shop shop3 = new Shop("이케아3호", new Location(Location.Dong.A, 2, 201), category);
        Shop shop4 = new Shop("이케아4호", new Location(Location.Dong.B, 2, 204), category);
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
    void ShopRequest로_Shop엔티티_생성() {
        //given
        Category category = new Category("침구류");
        em.persist(category);
        em.flush();
        em.clear();

        //when
        ShopRequest shopRequest = new ShopRequest("이케아", new Location(Location.Dong.A, 1, 104), category.getId());
        Shop shop = ShopMapper.INSTANCE.requestToShop(shopRequest);
        shop.joinCategory(categoryRepository.findById(1L).orElseThrow());

        //then
        assertThat(shop).isNotNull();
        assertThat(shop.getLabel()).isEqualTo(shopRequest.getLabel());
        assertThat(shop.getLocation().getDong()).isEqualTo(shopRequest.getLocation().getDong());
        assertThat(shop.getLocation().getHo()).isEqualTo(shopRequest.getLocation().getHo());
        assertThat(shop.getLocation().getFloor()).isEqualTo(shopRequest.getLocation().getFloor());
        assertThat(shop.getCategory().getLabel()).isEqualTo(category.getLabel());
    }

    @Test
    void Shop을_등록() {
        //given
        Category category = new Category("침구류");
        em.persist(category);
        em.flush();
        em.clear();

        //when
        ShopRequest shopRequest = new ShopRequest("이케아", new Location(Location.Dong.A, 1, 104), category.getId());
        ShopResponse shop = shopService.registerShop(shopRequest);

        //then
        assertThat(shop).isNotNull();
        assertThat(shop.getLabel()).isEqualTo(shopRequest.getLabel());
        assertThat(shop.getLocation().getDong()).isEqualTo(shopRequest.getLocation().getDong());
        assertThat(shop.getLocation().getHo()).isEqualTo(shopRequest.getLocation().getHo());
        assertThat(shop.getLocation().getFloor()).isEqualTo(shopRequest.getLocation().getFloor());
        assertThat(shop.getCategory().getLabel()).isEqualTo(category.getLabel());
    }

    @Test
    void Shop을_수정() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category);
        em.persist(shop);

        em.flush();
        em.clear();

        //when
        ShopRequest shopUpdateRequest = new ShopRequest(shop.getId(), "롯데마트", new Location(Location.Dong.B, 2, 201), category.getId());
        shopService.updateShop(shopUpdateRequest);

        //then
        JPAQueryFactory query = new JPAQueryFactory(em);

        Shop updated = query.selectFrom(QShop.shop)
                .where(QShop.shop.id.eq(shop.getId()))
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

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category);
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
