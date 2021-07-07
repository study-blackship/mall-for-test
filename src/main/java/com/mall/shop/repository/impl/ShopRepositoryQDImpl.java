package com.mall.shop.repository.impl;

import com.mall.base.supporter.Querydsl4RepositorySupport;
import com.mall.shop.entity.Location;
import com.mall.shop.entity.Shop;
import com.mall.shop.repository.ShopRepositoryQD;
import com.mall.shop.request.ShopCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.mall.shop.entity.QShop.shop;
import static org.springframework.util.StringUtils.*;

@Repository
public class ShopRepositoryQDImpl extends Querydsl4RepositorySupport implements ShopRepositoryQD {

    public ShopRepositoryQDImpl() {
        super(Shop.class);
    }

    @Override
    public Page<Shop> selectByCondition(ShopCondition shopCondition, Pageable pageable) {
        return applyPagination(pageable, query -> query
                .selectFrom(shop)
                .where(
                        labelContains(shopCondition.getLabel()),
                        categoryEq(shopCondition.getCategoryId()),
                        dongEq(shopCondition.getDong()),
                        floorEq(shopCondition.getFloor()),
                        hoEq(shopCondition.getHo())
                )
        );
    }

    private BooleanExpression labelContains(String label) {
        if (!hasText(label)) return null;
        return shop.label.contains(label);
    }

    private BooleanExpression categoryEq(Long categoryId) {
        if (categoryId == null) return null;
        return shop.category.id.eq(categoryId);
    }

    private BooleanExpression dongEq(Location.Dong dong) {
        if (dong == null) return null;
        return shop.location.dong.eq(dong);
    }

    private BooleanExpression floorEq(Integer floor) {
        if (floor == null) return null;
        return shop.location.floor.eq(floor);
    }

    private BooleanExpression hoEq(Integer ho) {
        if (ho == null) return null;
        return shop.location.ho.eq(ho);
    }
}
