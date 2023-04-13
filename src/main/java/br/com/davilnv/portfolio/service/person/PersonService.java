package br.com.davilnv.portfolio.service.person;

import br.com.davilnv.portfolio.exception.PersonNotFoundException;
import br.com.davilnv.portfolio.model.PersonModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonService {

    PersonModel savePerson(PersonModel person);
    List<PersonModel> findAll();

    Optional<PersonModel> getPersonById(Long id) throws PersonNotFoundException;

    List<PersonModel> findAllByEmployeeFalse();

}
