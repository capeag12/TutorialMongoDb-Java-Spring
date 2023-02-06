/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TutorialMongoDb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author capea
 */
//Sirve para señalar que será un documento que irá en la colección de personas
@Document(collection = "personas")
public class Persona {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String curso;

    public Persona(String nombre, String apellido, String nacionalidad, String curso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.curso = curso;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getCurso() {
        return curso;
    }

    public String getId() {
        return id;
    }
    
    
    
    
}
