package com.example.demo.enums;

public enum StatePayment {
    PENDING(0, "Pendente"),
    SETTLED(1, "Quitado"),
    CANCELED(2, "Cancelado");

    private int id;
    private String description;

    StatePayment(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static StatePayment toEnum(Integer id) throws IllegalAccessException {

        if(id == null) {
            return null;
        }

        for(StatePayment c : StatePayment.values()) {
            if(id.equals(c.getId())) {
                return c;
            }
        }

        throw new IllegalAccessException("Id inválido: " + id);
    }
}
