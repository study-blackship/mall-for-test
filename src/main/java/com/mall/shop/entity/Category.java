package com.mall.shop.entity;

import com.mall.base.Base;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORY")
@ToString
@Getter
public class Category extends Base implements Serializable {
    private String label;

    public Category() {
    }

    public Category(String label) {
        this.label = label;
    }
}
