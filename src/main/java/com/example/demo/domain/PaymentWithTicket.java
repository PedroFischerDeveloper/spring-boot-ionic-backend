package com.example.demo.domain;

import com.example.demo.enums.StatePayment;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentWithTicket extends Payment{
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date finalDate;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date paymentDate;

    public PaymentWithTicket() {};

    public PaymentWithTicket(Integer id, StatePayment state, Order order, Date finalDate, Date paymentDate) {
        super(id, state, order);
        this.finalDate = finalDate;
        this.paymentDate = paymentDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


}
