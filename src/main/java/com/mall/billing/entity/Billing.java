package com.mall.billing.entity;

import com.mall.base.Base;
import com.mall.base.Money;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="BILLINGS")
@Getter
public class Billing extends Base implements Serializable {
    private Long shopId;

    private Money commission = Money.ZERO;

    public Billing() {
    }

    @Builder
    public Billing(Long shopId, Money commission) {
        this.shopId = shopId;
        this.commission = commission;
    }

    public void billCommissionFee(Money money) {
        commission = commission.plus(money);
    }
}
