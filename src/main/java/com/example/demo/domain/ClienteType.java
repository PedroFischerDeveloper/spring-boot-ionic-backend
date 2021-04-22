package com.example.demo.domain;

public enum ClienteType {
    PERSON(0, "Pessoa Física"),
    COMPANY(1, "Pessoa Jurídica");

    private int id;
    private String description;

    ClienteType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static ClienteType toEnum(Integer id) throws IllegalAccessException {

        if(id == null) {
            return null;
        }

        for(ClienteType c : ClienteType.values()) {
            if(id.equals(c.getId())) {
                return c;
            }
        }

        throw new IllegalAccessException("Id inválido: " + id);
    }
}
