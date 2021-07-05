package com.mall.shop.repository.impl;

import com.mall.base.Querydsl4RepositorySupport;
import com.mall.shop.entity.Product;
import com.mall.shop.repository.ProductRepositoryQD;
import com.mall.shop.request.ProductCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.mall.shop.entity.QProduct.product;
import static org.springframework.util.StringUtils.*;

@Repository
public class ProductRepositoryQDImpl extends Querydsl4RepositorySupport implements ProductRepositoryQD {

    public ProductRepositoryQDImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> selectByCondition(ProductCondition productCondition, Pageable pageable) {
        return applyPagination(pageable, query -> query
                .selectFrom(product)
                .where(
                        labelContains(productCondition.getLabel()),
                        shopIdEq(productCondition.getShopId())
                )
        );
    }

    private BooleanExpression labelContains(String label) {
        if (!hasText(label)) return null;
        return product.label.contains(label);
    }

    private BooleanExpression shopIdEq(Long shopId) {
        if (shopId == null) return null;
        return product.shop.id.eq(shopId);
    }
}
