package com.kikux.curso.springboot.jpa.springboot_jpa;


import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		// getOne();
		// create();
		// getLists();
		// update();
		// deleteUsingID();
		// deleteUsingEntity();
		customizedQueries2();
	}
	
	private void customizedQueries2(){
		System.out.println("==============consulta persona y lenguaje=======================");
		List<Object[]> results = repository.findAllMixedPersonData();
		results.forEach(o -> System.out.println(o[0] + " & Language: " + o[1]));
		System.out.println("==============consulta clase persona con solo name y lastname=======================");
		repository.findAllCostumizedPerson().forEach(System.out::println);

	}

	private void customizedQueries(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Escribe ID: ");
		Long id = scanner.nextLong();
		scanner.close();
		String fullName = repository.getFullNameById(id);
		System.out.println("==============Full Name By ID=======================");
		System.out.println(fullName);
		System.out.println("==============Person List=======================");
		repository.obtenerPersonDataList().forEach(o -> System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]));
		System.out.println("==============Person Data By ID=======================");
		Object[] personData1 = (Object[])repository.obtenerPersonDataPorId(id);
		System.out.println("ID: " + personData1[0] + ", Name: " + personData1[1] + ", Lastname: " + personData1[2] + ", Programming Language: " + personData1[3]);
	}

	private void deleteUsingEntity() {
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID to delete: ");
		Long id = scanner.nextLong();	
		scanner.close();
 
		repository.findById(id).ifPresentOrElse(p -> repository.delete(p),
		 () -> System.out.println("Person with ID " + id + " not found."));

		repository.findAll().forEach(System.out::println);
		System.out.println("=====================================");

	}

	private void deleteUsingID() {
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID to delete: ");
		Long id = scanner.nextLong();	

		scanner.close();
		if(repository.findById(id).isPresent()) {
			repository.deleteById(id);
			System.out.println("Person with ID " + id + " deleted.");
		} else {
			System.out.println("Person with ID " + id + " not found.");
		}

		repository.findAll().forEach(System.out::println);
		System.out.println("=====================================");

	}


	private void update() {
		System.out.println("Updating person with given ID");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter ID: ");
		Long id = scanner.nextLong();

		// repository.findById(id).ifPresent( person -> {
		if(repository.findById(id).isPresent()) {
			Person person = repository.findById(id).get();
			System.out.println(person);
			System.out.print("Enter new programming language: ");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguage(programmingLanguage);
			
			Person updatedPerson = repository.save(person);
			System.out.println("Updated person: " + updatedPerson);
		} else {
			System.out.println("Person with ID " + id + " not found.");
		}
	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		String lastName = scanner.next();
		String programmingLanguage = scanner.next();
		scanner.close();
		Person p = new Person(null, name, lastName, programmingLanguage);
		Person personNew = repository.save(p);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
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
