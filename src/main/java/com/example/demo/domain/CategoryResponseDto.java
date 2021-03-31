package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponseDto {

    private Integer id;
    private String nome;

    public CategoryResponseDto() {}

    public CategoryResponseDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public Category categoryDtoToObject() {
        return new Category(
                new Category().getId() == 0 ? 1 :
                new Category().getId() + 1, nome);
    }

    public static CategoryResponseDto objectToDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getNome()
        );
    }

}
