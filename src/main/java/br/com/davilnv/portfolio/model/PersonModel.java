package br.com.davilnv.portfolio.model;

import br.com.davilnv.portfolio.dto.person.PersonRegisterDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "pessoa")
@Table(name = "pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;

    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(length = 14)
    private String cpf;

    @Column(name = "funcionario")
    private boolean employee;

    public PersonModel(PersonRegisterDto personRegisterDto) {
        this.name = personRegisterDto.getName();
        this.birthDate = personRegisterDto.getBirthDate();
        this.cpf = personRegisterDto.getCpf();
        this.employee = personRegisterDto.isEmployee();
    }

}
