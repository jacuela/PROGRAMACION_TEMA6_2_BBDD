/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import ejercicio1.*;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


/**
 *
 * @author jacuela
 */
public class main_antiguo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        
        //BD_World bdMundo=new BD_World_antiguo();
        System.out.print("Conectando con base de datos WORLD......");
        if (BD_World_antiguo.conectar()==null) 
            System.out.println("\033[31mERROR");
            
        else{
            System.out.println("\033[32mOK");
        
            boolean salir = false;
            int opcion; //Guardaremos la opcion del usuario
        
            
            while(!salir){
           
                System.out.println("\n\n\033[34m***** BBDD WORLD *****\033[30m");
                System.out.println("\033[34m***************************\033[30m");

                System.out.println("1. Habitantes de paises");
                System.out.println("2. Habitantes de ciudades");
                System.out.println("3. Paises con un minimo de X habitantes");
                System.out.println("4. Ciudades con un minimo de X habitantes");
                System.out.println("5. Habitantes de todas las ciudades");
                System.out.println("6. Salir");

                System.out.print("Elige una opcion >");
                opcion = Integer.parseInt(entrada.nextLine());

                switch(opcion){
                    case 1:
                         System.out.print("Nombre del pais:");
                         String nombrePais = entrada.nextLine();
                         BD_World_antiguo.habitantesPais(nombrePais);
                         break;

                    case 2:
                         System.out.print("Nombre de la ciudad:");
                         String nombreCiudad = entrada.nextLine();
                         BD_World_antiguo.habitantesCiudad(nombreCiudad);
                         break;

                    case 3:
                         System.out.print("Número min. de habitantes del pais:");
                         int habitantes = Integer.parseInt(entrada.nextLine());
                         BD_World_antiguo.paisesMinHabitantes(habitantes);
                         break;

                    case 4:
                         System.out.print("Número min. de habitantes de la ciudad:");
                         int habitantes2 = Integer.parseInt(entrada.nextLine());
                         BD_World_antiguo.ciudadesMinHabitantes(habitantes2);
                         break;

                    case 5:
                         System.out.print("Indica num filas a mostrar [0 para todas]: ");
                         int limite = Integer.parseInt(entrada.nextLine());
                         BD_World_antiguo.mostrarHabitantesCiudad(limite);
                         break;     
                         
                    case 6:
                        salir=true;
                        BD_World_antiguo.cerrarConexion();
                        break;

                     default:
                        System.out.println("Solo números entre 1 y 5");
                }
            
            } //fin menu
        
        } //fin else    
    }//fin main_antiguo 
    
}
