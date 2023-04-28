/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

import ejercicio2.*;
import ejercicio1.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


/**
 *
 * @author jacuela
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        
        ArrayList<Equipo> listaEquipos; //=new ArrayList();
        ArrayList<Jugador> listaJugadores; //=new ArrayList();
        
        
        
        System.out.print("Conectando con base de datos LIGABASKET......");
        if (BD_LigabasketSQL.conectar()==null) 
            System.out.println("\033[31mERROR");
            
        else{
            System.out.println("\033[32mOK");
          
            boolean salir = false;
            int opcion,id;
            Equipo e=null;
           
        
            while(!salir){
           
                listaEquipos=BD_LigabasketSQL.getEquiposSLQ();
                
                System.out.println();System.out.println();
                System.out.println("\033[34m               LIGA BASKET\033[30m");
                System.out.println("\033[34m*********************************************\033[30m");

                Collections.sort(listaEquipos,Collections.reverseOrder()); //Ordenamos por puntos
                for (Equipo equipo : listaEquipos) {
                    System.out.println(equipo);
                }
                System.out.println("\033[34m*********************************************\033[30m");
                System.out.println("");    
                System.out.println("1. Mostrar plantillas");
                System.out.println("2. Añadir puntos a equipo");
                System.out.println("3. Resetear puntos");
                System.out.println("4. Salir");

                System.out.print("Elige una opcion >");
                opcion = Integer.parseInt(entrada.nextLine());

                switch(opcion){
                    case 1:
                        
                        do{
                            
                            System.out.print("\n\nId de equipo:");
                            id = Integer.parseInt(entrada.nextLine());
                            //Busco en la bbdd la plantilla de este id
                            listaJugadores=BD_LigabasketSQL.getPlantillaSQL(id);
                            
                        }while(listaJugadores==null);
                       
                       
                        //OPCION1: Obtengo el equipo. No es necaria la consulfa a la BBDD pq tengo aquí
                        //un array con todos los equipos
//                        for (Equipo equipo : listaEquipos) {
//                            if (equipo.getId()==id) 
//                                e=equipo;
//                        }
                        
                        //OPCION2: consulta a la base de datod
                         e=BD_LigabasketSQL.getEquipoSQL(id);
                        
                        
                         //En este punto, podría mostrar la plantilla sin más.
                         //Pero lo más correcto sería añadir este array de jugadores
                         //al equipo (a la plantilla) y dejar el objeto relleno y 
                         //coherente.
                         //    e.setPlantilla(listaJugadores);
                         
                         e.setPlantilla(listaJugadores); //Asigno la plantilla al equipo
                         
                         System.out.println("\n\nPlantilla del "+e.getNombre());
                         System.out.println("===============================");
                         for (Jugador j : listaJugadores) {
                             System.out.println(j);
                         }
                         
                         break;

                    case 2:
                         System.out.print("Id de equipo:");
                         id = Integer.parseInt(entrada.nextLine());
                         System.out.print("Puntos a añadir:");
                         int puntos = Integer.parseInt(entrada.nextLine());
                         
                         //Voy a averiguar los puntos de este equipo
                         for (Equipo equipo : listaEquipos) {
                            if (equipo.getId()==id) 
                                e=equipo;
                         }
                         
                         
                         
                         //Calculo los puntos totales
                         int puntosTotales=e.getPuntos()+puntos;
                         BD_LigabasketSQL.actualizarPuntuacionSQL(id, puntosTotales);
                        
                         break;

                    case 3:
                        System.out.print("\033[31mSeguro de resetear los puntos [S | N]: \033[30m");
                        char resetear = entrada.nextLine().toUpperCase().charAt(0);
                        if (resetear=='S'){
                            BD_LigabasketSQL.resetearSQL();
                           
                        }
                        break;
     
                   
                    case 4:
                        salir=true;
                        BD_LigabasketSQL.cerrarConexion();
                        break;

                     default:
                        System.out.println("Solo números entre 1 y 3");
                }
            
            } //fin menu
        
        } //fin else    
    }//fin main 
    
    
    
    
    
    
    
}
