package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    /*
    * Relacionamento muitos para muitos
    * incluir as notações abaixo para ser
    * criado a tabela automaticamente
    * */
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> categorys = new ArrayList<>();

    @OneToMany(mappedBy = "id.product")
    private Set<ItemOrder> itens = new HashSet<>();

    public Product() {};

    /*
    * Não incluir coleções no construtor com parametro
    * */
    public Product(Integer id, String nome, Double price) {
        super();
        this.id = id;
        this.nome = nome;
        this.preco = price;
    }

    public List<Order> getOrders() {
        List<Order> list = new ArrayList<>();
        for(ItemOrder item: itens) {
            list.add(item.getOrder());
        }
        return list;
    };

    public Set<ItemOrder> getItens() {
        return itens;
    }

    public void setItens(Set<ItemOrder> itens) {
        this.itens = itens;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
