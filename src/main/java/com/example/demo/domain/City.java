package com.example.demo.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;

    public City() {}

    public City(Integer id, String nome, State state) {
        super();
        this.id = id;
        this.nome = nome;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
