package com.mall;

import com.mall.shop.entity.Category;
import com.mall.shop.entity.Location;
import com.mall.shop.entity.Shop;
import com.mall.shop.mapper.ShopMapper;
import com.mall.shop.repository.CategoryRepository;
import com.mall.shop.request.ShopRequest;
import com.mall.shop.response.ShopResponse;
import com.mall.shop.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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
    void ShopRequest로_Shop엔티티_생성() {
        //given
        Category category = new Category("침구류");
        em.persist(category);
        em.flush();
        em.clear();

        ShopRequest shopRequest = new ShopRequest("이케아", new Location(Location.Dong.A, 1, 104), category.getId());

        //when
        Shop shop = ShopMapper.INSTANCE.requestToShop(shopRequest);
        shop.joinCategory(categoryRepository.findById(1L).orElseThrow());


        //then
        assertThat(shop, is(notNullValue()));
        assertThat(shop.getLabel(), is(shopRequest.getLabel()));
        assertThat(shop.getLocation().getDong(), is(shopRequest.getLocation().getDong()));
        assertThat(shop.getLocation().getHo(), is(shopRequest.getLocation().getHo()));
        assertThat(shop.getLocation().getFloor(), is(shopRequest.getLocation().getFloor()));
        assertThat(shop.getCategory().getLabel(), is(category.getLabel()));
    }

    @Test
    void Shop을_등록() {
        //given
        Category category = new Category("침구류");
        em.persist(category);
        em.flush();
        em.clear();
        ShopRequest shopRequest = new ShopRequest("이케아", new Location(Location.Dong.A, 1, 104), category.getId());

        //when
        ShopResponse shop = shopService.registerShop(shopRequest);
        System.out.println(shop);

        //then
        assertThat(shop, is(notNullValue()));
        assertThat(shop.getLabel(), is(shopRequest.getLabel()));
        assertThat(shop.getLocation().getDong(), is(shopRequest.getLocation().getDong()));
        assertThat(shop.getLocation().getHo(), is(shopRequest.getLocation().getHo()));
        assertThat(shop.getLocation().getFloor(), is(shopRequest.getLocation().getFloor()));
        assertThat(shop.getCategory().getLabel(), is(category.getLabel()));
    }

    @Test
    void Shop을_수정() {
        //given
        Category category = new Category("침구류");
        em.persist(category);
        em.flush();
        em.clear();
        ShopRequest shopRequest = new ShopRequest("이케아", new Location(Location.Dong.A, 1, 104), category.getId());
        ShopResponse shop = shopService.registerShop(shopRequest);
        ShopRequest shopUpdateRequest = new ShopRequest(shop.getId(), "롯데마트", new Location(Location.Dong.B, 2, 201), category.getId());

        //when
        Shop updated = shopService.updateShop(shopUpdateRequest);
        System.out.println(updated);

        //then
        assertThat(updated, is(notNullValue()));
        assertThat(updated.getLabel(), is(shopUpdateRequest.getLabel()));
        assertThat(updated.getLocation().getDong(), is(shopUpdateRequest.getLocation().getDong()));
        assertThat(updated.getLocation().getHo(), is(shopUpdateRequest.getLocation().getHo()));
        assertThat(updated.getLocation().getFloor(), is(shopUpdateRequest.getLocation().getFloor()));
        assertThat(updated.getCategory().getLabel(), is(category.getLabel()));
    }

}
