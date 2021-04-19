package com.example.demo.domain;

/*
* Atributos básicos
* Associações (inicie as coleções)
* Construtores (não incluir coleções no construtor com parametro)
* Getters and Setters
* HashCodes
* Serializable
* */

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
    * @Id (define primary key)
    * GenerateValue (generate primary key)
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    /*
    * Evitará referencia ciclica e os
    * objetos associados viram nessa classe
    * que contém a anotação @JsonManagedReference
    * */
    @JsonManagedReference
    @ManyToMany(mappedBy = "categorys")
    private List<Product> products = new ArrayList<>();

    public Category(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getId().equals(category.getId()) && getNome().equals(category.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }
}
