package br.com.davilnv.portfolio.dto.person;

import lombok.Data;

import java.util.Date;

@Data
public class PersonRegisterDto {

    private String name;
    private String cpf;
    private Date birthDate;
    private boolean employee;

}
