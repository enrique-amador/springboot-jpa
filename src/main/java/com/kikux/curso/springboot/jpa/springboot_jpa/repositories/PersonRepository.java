package com.kikux.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kikux.curso.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

    List<Person> findByProgrammingLanguage(String programmingLanguage); //this method is implemented automatically
    
    @Query("select p from Person p where p.programmingLanguage=?1")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage); //this method cannot be auto implemented so we use @Query
}
