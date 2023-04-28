/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author jacuela
 */
public class Jugador {
    private int id;
    private String nombre;
    private LocalDate fecha_nac;

    public Jugador(int id, String nombre, LocalDate fecha_nac) {
        this.id = id;
        this.nombre = nombre;
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

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }
    
    
    @Override
    public String toString(){
        
       DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
       long edad=ChronoUnit.YEARS.between(fecha_nac, LocalDate.now());
        
       return String.format("%15s %2d años (%s)",nombre,edad,fecha_nac.format(dtf));
    }
    
    
    
}
