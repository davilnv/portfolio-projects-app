package br.com.davilnv.portfolio.dto.person;

import br.com.davilnv.portfolio.model.PersonModel;
import lombok.Data;

import java.util.Date;

@Data
public class PersonGetDto {

    private String name;
    private Date birthDate;
    private boolean employee;

    public PersonGetDto(PersonModel personModel) {
        this.name = personModel.getName();
        this.birthDate = personModel.getBirthDate();
        this.employee = personModel.isEmployee();
    }

}
