package com.example.demo.domain;

import com.example.demo.enums.StatePayment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // mapeando herença
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer state;

    
  	@JsonIgnore
  	@OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    public Payment() {};

    public Payment(Integer id, StatePayment state, Order order) {
        this.id = id;
        this.state = state.getId();
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatePayment getState() throws IllegalAccessException {
        return StatePayment.toEnum(state);
    }

    public void setState(StatePayment state) {
        this.state = state.getId();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return getId().equals(payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
