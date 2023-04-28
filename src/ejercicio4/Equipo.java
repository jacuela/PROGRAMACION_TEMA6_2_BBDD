/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jacuela
 */
public class Equipo implements Comparable<Equipo> {
    private int id;
    private String nombre;
    private int puntos;
    private ArrayList<Jugador> plantilla;
    //private boolean plantillaCompleta;

    public Equipo(int id, String nombre, int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = puntos;
        plantilla=new ArrayList();
        //plantillaCompleta=false;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setPlantilla(ArrayList<Jugador> plantilla) {
        this.plantilla = plantilla;
    }

    
    //MÉTODO NO NECESARIO PORQUE MI PROGRAMA NO CONTEMPLA FICHAR
    public void fichar(Jugador j){
        if (plantilla.size()==2)
            System.out.println("ERROR: plantilla completa");
        else{
            plantilla.add(j);
        }    
    }
    
    
    public void imprimirPlantilla(){
        
        System.out.println("");
        System.out.println("Plantilla del "+this.nombre);
        System.out.println("================================");
        for (Jugador jugador : plantilla) {
            System.out.println(jugador);
        }
        
        
    }
    
    
    @Override
    public String toString(){
        return String.format("[%3d] %-10s %2d puntos",id, nombre, puntos);
        
        
    }

    @Override
    public int compareTo(Equipo o) {
         if (this.puntos < o.puntos)
                return -1;
          else if (this.puntos > o.puntos)
                return 1;
          else 
                return 0;
    
    }
    
    
}
