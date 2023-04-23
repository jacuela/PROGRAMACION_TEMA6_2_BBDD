/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jacuela
 */
public class Contacto {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDate fecha_nac;

   
    public Contacto(int id, String nombre, String apellidos, String email, LocalDate fecha_nac) {
        this.id=id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fecha_nac = fecha_nac;
    }

    public Contacto(String nombre, String apellidos, String email, LocalDate fecha_nac) {
        this.id = -1;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fecha_nac = fecha_nac;
    }

   

    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }
    
    
    @Override
    public String toString(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String cad;
        
        cad=String.format("%3d - %-10s %-15s %-20s %10s",id, nombre, apellidos,email,fecha_nac.format(dtf));
       
        return cad;
        
        
    }
    
    
    
    
    
    
    
}
