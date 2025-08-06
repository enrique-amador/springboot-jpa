package com.kikux.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kikux.curso.springboot.jpa.springboot_jpa.dto.PersonDTO;
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

    @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Object obtenerPersonDataPorId(Long id);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonDataPorName(String name);

    @Query("select p.name, p.programmingLanguage  from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonDataPorLenguajeYNombre(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixedPersonData();  

    //para esto creamos un constructor con solo nombre y apellido
    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllCostumizedPerson();

    @Query("select new com.kikux.curso.springboot.jpa.springboot_jpa.dto.PersonDTO(p.name, p.lastname) from Person p")
    List<PersonDTO> findAllPersonDTO();

    @Query("select p.name from Person p")
    List<String> getAllNames();

    @Query("select DISTINCT(p.name) from Person p")
    List<String> getAllDistinctNames();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguagesDistinctCount();

    List<Person> findByIdBetween(Long id1, long id2);

    List<Person> findByNameBetweenOrderByNameAscProgrammingLanguageDesc(String name1, String name2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc, p.lastname asc")
    List<Person> getByNameBetween(String name1, String name2);

    List<Person> findAllByOrderByNameDesc();
}
