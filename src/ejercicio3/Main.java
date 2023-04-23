/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;


import ejercicio2.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alberto
 */
public class Main {

    public static final String COLOR_ROJO="\033[31m";
    public static final String COLOR_VERDE="\033[32m";
    public static final String COLOR_NEGRO="\033[30m";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner teclado = new Scanner(System.in);
        int opcionElegida = -1;
        boolean filtrada=false;
        char letra=' ';
        
        //Este array list es de apoyo  y va a tener en tiempo de ejecución 
        //los contactos a mostrar.  
        //O tenemos tofos los contactos o los filtrados por una letra
        ArrayList<Contacto> listaContactos; //=null;
        
        
        
        System.out.print("Conectando con base de datos Agenda......");
        if (BD_AgendaSQL.conectar()==null) 
            System.out.println(COLOR_ROJO+"ERROR"+COLOR_NEGRO);
            
        else{
            System.out.println(COLOR_VERDE+"OK"+COLOR_NEGRO);
            
            //Al arrancar, pedimos todos los contactos
            listaContactos=BD_AgendaSQL.getListaCompleta();
            
            System.out.println("contactos encontrados:");
            
            
            
            do {
               
                System.out.println("");System.out.println("");System.out.println("");
                
                
                
                //Listo los contactos de la lista (puede estar completa o filtrada) 
                //Ademas, dependiendo de si esta o no filtrada, muestro una cabecera u otra
                if (!filtrada) System.out.println("====================== LISTA DE CONTACTOS ["+listaContactos.size()+"]====================");
                else System.out.printf("================== LISTA FILTRADA (Letra: %c) =====================\n",letra);
                    
                for (Contacto contacto : listaContactos) {
                        System.out.println(contacto);
                }
                
                System.out.println("==================================================================");
                
                
                //Muestro el menú
                System.out.println("1. Insertar Contacto");
                System.out.println("2. Borrar Contacto");
                System.out.println("3. Lista completa");
                System.out.println("4. Filtrar por letra comienzo");
                System.out.println("5. Salir");
                

                System.out.print("Escoge Opcion >");
                opcionElegida = Integer.parseInt(teclado.nextLine());

                switch(opcionElegida){

                    case 1:
                        System.out.println("");
                        System.out.print("  Introduce el nombre: ");
                        String nombre = teclado.nextLine();
                        System.out.print("  Introduce los apellidos: ");
                        String apellidos = teclado.nextLine();
                        System.out.print("  Introduce el email: ");
                        String email = teclado.nextLine();
                        System.out.print("  Introduce la fecha de nacimiento:[dd/MM/yyyy]: ");
                        String fecha = teclado.nextLine();

                        //OPCION1 - sin crear el contacto. No está programado
                        //BD_AgendaSQL.insertar(nombre, apellidos, email, LocalDate.parse(fecha,dtf));
                        
                        //OPCION2 - creando el contacto. Es un poco tonta en este ejercicio, pq luego
                        //lo que hacemos es extraer los campos y el contacto lo desechamos. Pero
                        //ejemplifica como trabajar con objetos
                        BD_AgendaSQL.insertar(new Contacto(nombre, apellidos, email, LocalDate.parse(fecha,dtf)));
                        listaContactos=BD_AgendaSQL.getListaCompleta();
                        break;
                    case 2:
                        System.out.print("Introduce el ID a eliminar: ");
                        int id = Integer.parseInt(teclado.nextLine());
                        BD_AgendaSQL.eliminar(id);
                        listaContactos=BD_AgendaSQL.getListaCompleta();
                        break;
                    case 3:
                        listaContactos=BD_AgendaSQL.getListaCompleta();
                        if (listaContactos==null){
                            System.out.println("ERROR al leer lista");
                            listaContactos.clear();
                        }
                        filtrada=false;
                        break;    
                    case 4:
                        System.out.print("Escoge letra [A-Z]: ");
                        letra=teclado.nextLine().charAt(0);
                        listaContactos=BD_AgendaSQL.getListaPorLetra(letra);
                        if (listaContactos==null){
                            System.out.println("ERROR al leer lista");
                            listaContactos.clear();
                        }
                        filtrada=true;
                        break;
                        
                    case 5:
                        break;
                }
            } while (opcionElegida != 5);
        }
    }
    
    

    
    
    
}
