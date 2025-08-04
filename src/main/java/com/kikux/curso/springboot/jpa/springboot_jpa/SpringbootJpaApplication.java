package com.kikux.curso.springboot.jpa.springboot_jpa;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kikux.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.kikux.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getOne();
		// create();
		// getLists();
	}

	public void create() {
		Person p = new Person(null, "Lalo", "Thor", "Python");
		Person personNew = repository.save(p);
		System.out.println(personNew);

	}

	private void getOne() {
		// Optional<Person> optPerson = repository.findById(1L);
		// if(optPerson.isPresent()) { we avoid an exception with this
		// 	System.out.println(optPerson.get());
		// }

		//a cleaner option:
		// repository.findById(1L).ifPresent(p -> System.out.println(p));
		repository.findById(1L).ifPresent(System.out::println);
		
		repository.findOneLikeName("jos").ifPresent(System.out::println);

		// repository.findByNameContaining("jose").ifPresent(System.out::println);

	}

	private void getLists() {
		// List<Person> persons = (List<Person>) repository.findAll();
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Python", "Pepe"); 
		persons.stream().forEach(p -> System.out.println(p));
		System.out.println("=====================================");
		List<Object[]> peopleData = repository.buscarPersonDataPorName("Andres");
		peopleData.stream().forEach(p -> System.out.println(p[0] + " es experto en: " + p[1]));
		System.out.println("=====================================");
		List<Object[]> peopleData2 = repository.buscarPersonData();
		peopleData2.stream().forEach(p -> System.out.println(p[0] + " es experto en: " + p[1]));
		
	}

}
