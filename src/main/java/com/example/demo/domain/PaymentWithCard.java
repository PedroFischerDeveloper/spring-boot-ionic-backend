package com.example.demo.domain;

import com.example.demo.enums.StatePayment;

import javax.persistence.Entity;

@Entity
public class PaymentWithCard extends Payment{
    private static final long serialVersionUID = 1L;

    private Integer numberOfInstallments;

    public PaymentWithCard() {};

    public PaymentWithCard(Integer id, StatePayment state, Order order, Integer numberOfInstallments) {
        super(id, state, order);
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
