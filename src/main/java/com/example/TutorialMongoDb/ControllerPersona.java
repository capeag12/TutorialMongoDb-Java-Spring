/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TutorialMongoDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author capea
 */
@CrossOrigin(origins = "http://localhost:8080") //Sirve para habilitar el intercambio de recursos de origen cruzado
@RestController //Se usa para definir un controlador 
@RequestMapping("/personas")
public class ControllerPersona {
    
    @Autowired //Sirve para inyectar dependencias, en este caso un repositorio
    RepositorioPersona personaRepositorio;
    
    //Añadir persona
    @PostMapping("/addPersona")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona p){
        try{
            Persona persona = personaRepositorio.save(p);
            System.out.println("Añadido");
            return ResponseEntity.ok().body(persona);
        } catch(Exception e){
            System.out.println("Ha fallado");
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    //Buscar todas las personas
    @GetMapping("/getPersonas")
    public ResponseEntity<List<Persona>> obtenerPersonas(){
        try {
            List<Persona> personas = new ArrayList<Persona>();
            personaRepositorio.findAll().forEach(personas::add);
            if (personas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return ResponseEntity.ok().body(personas);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
            
    }
    //Buscar por apellido
    @GetMapping("/getPersonasApellido/{apellido}")
    public ResponseEntity<List<Persona>> obtenerPersonasApellido(@PathVariable("apellido") String apellido){
        try{
        List<Persona> listaPersonas = personaRepositorio.findByApellido(apellido);
        
        if(listaPersonas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return ResponseEntity.ok().body(listaPersonas);
        }
        catch(Exception e){
            return new ResponseEntity(null,HttpStatus.BAD_GATEWAY);
        }
       
    }
    
    //Buscar por nombre usando parametros en la url
    @GetMapping("/getPersonaNombre")
    public ResponseEntity<List<Persona>> obtenerPersonasNombre(@RequestParam(name = "nombre", required = true) String nombre){
        List<Persona> personas = null;
        personas = personaRepositorio.findByNombreLike(nombre);
        
        if(personas.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok().body(personas);
        
        
    }
    
}
