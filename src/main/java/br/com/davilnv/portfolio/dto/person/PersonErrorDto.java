package br.com.davilnv.portfolio.dto.person;

import lombok.Data;

@Data
public class PersonErrorDto {
    private boolean status;
    private String message;

    public PersonErrorDto(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
