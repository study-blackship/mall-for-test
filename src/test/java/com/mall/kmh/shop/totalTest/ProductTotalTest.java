package com.mall.kmh.shop.totalTest;

import com.mall.base.Money;
import com.mall.base.PageRequest;
import com.mall.shop.entity.*;
import com.mall.shop.request.ProductCondition;
import com.mall.shop.request.ProductRequest;
import com.mall.shop.response.ProductResponse;
import com.mall.shop.service.ProductService;
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
public class ProductTotalTest {

    @Autowired
    EntityManager em;

    @Autowired
    ProductService productService;

    @Test
    void Product_paging_조회() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category);
        em.persist(shop);

        Product product1 = new Product(Money.wons(100_000), "수류탄", shop);
        Product product2 = new Product(Money.wons(100_000_000), "미사일", shop);
        Product product3 = new Product(Money.wons(1_000), "토마호크", shop);
        Product product4 = new Product(Money.wons(300_000_000), "땅크", shop);
        Product product5 = new Product(Money.wons(10_000), "수륙챙이", shop);
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.persist(product4);
        em.persist(product5);

        //when
        ProductCondition productCondition = ProductCondition.builder()
                .label("")
                .min(Money.wons(10_000))
                .max(Money.wons(100_000_000))
                .shopId(shop.getId())
                .build();
        PageRequest pageRequest = new PageRequest();

        Page<ProductResponse> productResponses = productService.selectByCondition(productCondition, pageRequest);
        productResponses.getContent().forEach(System.out::println);

        //then
        productResponses.forEach(productResponse -> {
            assertThat(productResponse.getLabel()).contains(productCondition.getLabel());
            assertThat(productResponse.getShopResponse().getId()).isEqualTo(productCondition.getShopId());
        });
    }

    @Test
    void Product를_등록() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category);
        em.persist(shop);
        em.flush();
        em.clear();

        //when
        ProductRequest productRequest = ProductRequest.builder()
                .label("수류탄")
                .price(Money.wons(100_000))
                .shopId(shop.getId())
                .build();

        ProductResponse productResponse = productService.registerProduct(productRequest);

        //then
        JPAQueryFactory query = new JPAQueryFactory(em);

        Product product = query.selectFrom(QProduct.product)
                .where(QProduct.product.id.eq(productResponse.getId()))
                .fetchOne();

        assertThat(product).isNotNull();
        assertThat(product.getLabel()).isEqualTo(productRequest.getLabel());
        assertThat(product.getPrice()).isEqualTo(productRequest.getPrice());
        assertThat(product.getShop().getId()).isEqualTo(shop.getId());
    }

    @Test
    void Product를_수정() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category);
        em.persist(shop);

        Product product = new Product(Money.wons(100_000), "수류탄", shop);
        em.persist(product);

        em.flush();
        em.clear();

        //when
        ProductRequest productUpdateRequest = ProductRequest.builder()
                .id(product.getId())
                .label("미사일")
                .price(Money.wons(100_000_000))
                .shopId(shop.getId())
                .build();

        ProductResponse productResponse = productService.updateProduct(productUpdateRequest);

        //then
        JPAQueryFactory query = new JPAQueryFactory(em);

        Product updated = query.selectFrom(QProduct.product)
                .where(QProduct.product.id.eq(productResponse.getId()))
                .fetchOne();

        assertThat(updated).isNotNull();
        assertThat(updated.getLabel()).isEqualTo(productUpdateRequest.getLabel());
        assertThat(updated.getPrice()).isEqualTo(productUpdateRequest.getPrice());
        assertThat(updated.getShop().getId()).isEqualTo(shop.getId());
    }

    @Test
    void Product를_삭제() {
        //given
        Category category = new Category("침구류");
        em.persist(category);

        Shop shop = new Shop("이케아", new Location(Location.Dong.A, 1, 104), category);
        em.persist(shop);

        Product product = new Product(Money.wons(100_000), "수류탄", shop);
        em.persist(product);

        em.flush();
        em.clear();

        //when
         productService.deleteProduct(product.getId());

        //then
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Product> products = query.selectFrom(QProduct.product)
                .where(QProduct.product.id.eq(product.getId()))
                .fetch();

        assertThat(products).isEmpty();
    }
}
