package br.com.davilnv.portfolio.service.person;

import br.com.davilnv.portfolio.exception.PersonNotFoundException;
import br.com.davilnv.portfolio.model.PersonModel;
import br.com.davilnv.portfolio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonModel savePerson(PersonModel person) {
        return repository.save(person);
    }

    @Override
    public List<PersonModel> findAll() {
        return repository.findAll();
    }

    @Override
    public PersonModel getPersonById(Long id) throws PersonNotFoundException {
        Optional<PersonModel> person = repository.findById(id);
        if (person.isEmpty()) {
            throw new PersonNotFoundException("Pessoa não encontrada");
        }
        return person.get();
    }

    @Override
    public List<PersonModel> findAllByEmployeeFalse() {
        return repository.findAllByEmployeeFalse();
    }
}
