package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer type;

    @OneToMany(mappedBy="client", cascade=CascadeType.ALL)
    private List<Address> address = new ArrayList<>();

    // conjunto, não aceita repetição
    @ElementCollection // anotação que permite representar uma entidade fraca
    @CollectionTable(name = "PHONE")
    private Set<String> phones = new HashSet<>();

    public Client() {};

    public Client(Integer id, String name, String email, String cpfOrCnpj, ClienteType clienteType) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = clienteType.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public ClienteType getClienteType() throws IllegalAccessException {
        return ClienteType.toEnum(type);
    }

    public void setClienteType(ClienteType clienteType) {
        this.type = clienteType.getId();
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getId().equals(client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
