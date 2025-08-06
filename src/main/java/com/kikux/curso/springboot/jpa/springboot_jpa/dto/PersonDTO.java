package com.kikux.curso.springboot.jpa.springboot_jpa.dto;

public class PersonDTO { //clase personalizada y simplificada de una Entity

    String name;
    String lastname;

    //no es necesario constructor vacio porque esta instancia
    //se va a manejar con un new, no con un framework como hibernate o jpa o un entity
    public PersonDTO(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    //solo getters porque los datos se pueblas con el constructor

    
    public String getName() {
        return name;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    @Override
    public String toString() {
        return "PersonDTO [name=" + name + ", lastname=" + lastname + "]";
    }
    
    
}
