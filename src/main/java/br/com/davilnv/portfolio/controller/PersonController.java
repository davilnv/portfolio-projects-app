package br.com.davilnv.portfolio.controller;

import br.com.davilnv.portfolio.dto.person.PersonErrorDto;
import br.com.davilnv.portfolio.dto.person.PersonGetDto;
import br.com.davilnv.portfolio.dto.person.PersonRegisterDto;
import br.com.davilnv.portfolio.exception.PersonNotFoundException;
import br.com.davilnv.portfolio.model.PersonModel;
import br.com.davilnv.portfolio.service.person.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> listPerson() {
        return new ResponseEntity<>(personService.findAll().stream().map(PersonGetDto::new).toList(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPersonById(@RequestParam("id") Long id) {
        try {
            PersonModel personModel = personService.getPersonById(id);
            return new ResponseEntity<>(new PersonGetDto(personModel), HttpStatus.OK);
        } catch (PersonNotFoundException e) {
            return new ResponseEntity<>(new PersonErrorDto(false, "Usuário não encontrado"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPerson(@RequestBody PersonRegisterDto personRegisterDto) {
        if (personRegisterDto.getName() == null || personRegisterDto.getName().isEmpty()) {
            return new ResponseEntity<>(new PersonErrorDto(false, "O campo nome é obrigatório, não pode ser vazio"), HttpStatus.BAD_REQUEST);
        }

        PersonModel savedPerson = personService.savePerson(new PersonModel(personRegisterDto));

        return new ResponseEntity<>(new PersonGetDto(savedPerson), HttpStatus.OK);

    }

}
