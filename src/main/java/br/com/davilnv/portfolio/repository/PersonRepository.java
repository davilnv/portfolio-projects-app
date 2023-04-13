package br.com.davilnv.portfolio.repository;

import br.com.davilnv.portfolio.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {

    List<PersonModel> findAllByEmployeeFalse();

}
