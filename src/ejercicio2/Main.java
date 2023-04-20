/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        LocalDate unaFechaLD;
        Scanner teclado = new Scanner(System.in);
        int opcionElegida = -1;
        
        System.out.print("Conectando con base de datos Agenda......");
        if (BD_AgendaSQL.conectar()==null) 
            System.out.println(COLOR_ROJO+"ERROR"+COLOR_NEGRO);
            
        else{
            System.out.println(COLOR_VERDE+"OK"+COLOR_NEGRO);
         
            do {
                BD_AgendaSQL.listar();


                System.out.println("1. Insertar Contacto");
                System.out.println("2. Borrar Contacto");
                System.out.println("3. Listar contactos por letra comienzo");
                System.out.println("4. Listar contactos por mes");
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

                        unaFechaLD = LocalDate.parse(fecha,dtf);

                        BD_AgendaSQL.insertar(nombre, apellidos, email, unaFechaLD);
                        break;
                    case 2:
                        System.out.print("Introduce el ID a eliminar: ");
                        int id = Integer.parseInt(teclado.nextLine());
                        BD_AgendaSQL.eliminar(id);
                        break;
                    case 3:
                        System.out.print("Escoge letra [A-Z]: ");
                        char letra=teclado.nextLine().charAt(0);
                        BD_AgendaSQL.listarLetra(letra);
                        pulsa_intro();
                        break;
                    case 4:
                        System.out.print("Mes de nacimiento [1-12]: ");
                        int mes=Integer.parseInt(teclado.nextLine());
                        BD_AgendaSQL.listarPorMes(mes);
                        pulsa_intro();
                        break;
                    case 5:
                        break;
                }
            } while (opcionElegida != 5);
        }
    }
    
    
    public static void pulsa_intro(){
        System.out.println("- - PULSA [INTRO] PARA CONTINUAR - -");
        Scanner teclado = new Scanner(System.in);
        String tecla=teclado.nextLine();
        
    }
    
    
    
    
}
