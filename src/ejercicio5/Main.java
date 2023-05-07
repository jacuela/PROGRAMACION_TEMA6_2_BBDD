/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio5;


import java.sql.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author nurik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        
        
        //BD_World bdMundo=new BD_World();
        System.out.print("Conectando con base de datos WORLD......");
        if (BD_World.conectar()==null) 
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
                System.out.println("5. Mostrar los habitantes");
                System.out.println("6. Salir");

                System.out.print("Elige una opcion >");
                opcion = Integer.parseInt(entrada.nextLine());

                switch(opcion){
                    case 1:
                         System.out.print("Nombre del pais:");
                         String nombrePais = entrada.nextLine();
                         JSONArray ja = BD_World.habitantesPais(nombrePais);
                         JSONObject json;
                         
                         for (int i = 0; i < ja.length(); i++) {
                            json = ja.getJSONObject(i);
                            imprimirJSONObject(json);
                         
//                            if(json.has("PAIS")){
//                               System.out.println("PAIS: "+json.getString("PAIS")); 
//                            }
//                            if(json.has("HABITANTES")){
//                                System.out.println("HABITANTES: "+json.getInt("HABITANTES"));
//                            }
//                             
                        }
                         break;

                    case 2:
                         System.out.print("Nombre de la ciudad:");
                         String nombreCiudad = entrada.nextLine();
                         JSONArray jsona = BD_World.habitantesCiudad(nombreCiudad);
                         JSONObject jsono;
                         for (int i = 0; i < jsona.length(); i++) {
                            jsono = jsona.getJSONObject(i);
                            imprimirJSONObject(jsono);

//                            if(jsono.has("CIUDAD")){
//                                System.out.println("CIUDAD: "+jsono.getString("CIUDAD"));
//                            }
//                            if(jsono.has("PAIS")){
//                                System.out.println("PAIS: "+jsono.getString("PAIS"));
//                            }
//                            if(jsono.has("HABITANTES")){
//                                System.out.println("HABITANTES: "+jsono.getInt("HABITANTES"));
//                            }
                        }
                         break;

                    case 3:
                         System.out.print("Número min. de habitantes del pais:");
                         int habitantes = Integer.parseInt(entrada.nextLine());
                         JSONArray jsa = BD_World.paisesMinHabitantes(habitantes);
                         JSONObject jso;
                         for (int i = 0; i < jsa.length(); i++) {
                            jso = jsa.getJSONObject(i);
                            imprimirJSONObject(jso);
//                            if(jso.has("pais")){
//                                System.out.println("PAIS: "+jso.getString("pais"));
//                            }
//                            if(jso.has("habitantes")){
//                                System.out.println("HABITANTES: "+jso.getInt("habitantes"));
//                            }
                             System.out.println("");
                        }
                         break;

                    case 4:
                         System.out.print("Número min. de habitantes de la ciudad:");
                         int habitantes2 = Integer.parseInt(entrada.nextLine());
                         
                         JSONArray jsn = BD_World.ciudadesMinHabitantes(habitantes2);
                         JSONObject jsonob;
                         for (int i = 0; i < jsn.length(); i++) {
                            jsonob = jsn.getJSONObject(i);
                            imprimirJSONObject(jsonob);
//                            if(jsonob.has("ciudad")){
//                                System.out.println("CIUDAD: " +jsonob.getString("ciudad"));
//                            }if(jsonob.has("pais")){
//                                System.out.println("PAIS: "+jsonob.getString("pais"));
//                            }if(jsonob.has("habitantes")){
//                                System.out.println("HABITANTES: "+jsonob.getInt("habitantes"));
//                            }
                             System.out.println("");
                        }
                         break;

                    case 5:
                        System.out.println("Introduzca el límite a mostrar [0 para mostrar todo]: ");
                        int limit = Integer.parseInt(entrada.nextLine());
                        JSONArray jsonar = BD_World.mostrarHabitantes(limit);
                        JSONObject jsonobj;
                        for (int i = 0; i < jsonar.length(); i++) {
                            jsonobj = jsonar.getJSONObject(i);
                            imprimirJSONObject(jsonobj);
//                            if(jsonobj.has("ciudad")){
//                                System.out.println("CIUDAD: "+jsonobj.getString("ciudad"));
//                            }if(jsonobj.has("pais")){
//                                System.out.println("PAIS: "+jsonobj.getString("pais"));
//                            }if(jsonobj.has("habitantes")){
//                                System.out.println("HABITANTES: "+jsonobj.getInt("habitantes"));
//                            }
                            System.out.println("");
                        }
                        break;
                        
                    case 6:
                        salir=true;
                        BD_World.cerrarConexion();
                        break;
                        
                     default:
                        System.out.println("Solo números entre 1 y 5");
                }
            
            } //fin menu
        
        } //fin else    
    }//fin main 
    
    public static void imprimirJSONObject(JSONObject jo){
        Iterator <String> it = jo.keys();
        
        while(it.hasNext()){
            String key = it.next();
            String value = jo.getString(key);
            System.out.println(key +" "+value);
        }
    }
    
} 

