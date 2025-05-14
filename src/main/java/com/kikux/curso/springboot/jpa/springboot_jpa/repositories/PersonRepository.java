package com.kikux.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kikux.curso.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

    List<Person> findByProgrammingLanguage(String programmingLanguage); //this method is implemented automatically
    
    // @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    // List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name); //this method cannot be auto implemented so we use @Query

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> buscarPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> buscarPersonDataPorName(String name);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneByName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name); //query implemented  automatically: the same as before
}
