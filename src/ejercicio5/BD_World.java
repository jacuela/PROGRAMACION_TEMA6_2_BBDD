/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 *
 * @author nurik
 */
public class BD_World{
    
    private static final String URL = "jdbc:mariadb://localhost:3306/world";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection conexion;
    
    
    
    public BD_World(){
         //this.conectar();
        
    }
    
    public static Connection conectar(){
        
        //***********************************************************
        //    CARGAMOS EL DRIVER
        //***********************************************************
        try {

            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            System.out.println("Driver <org.mariadb.jdbc.Driver> cargado.");

        } catch (Exception ex) {
            System.out.println("Error, no se ha podido cargar MariaDB JDBC Driver");
        }


        try {
            //***********************************************************
            //    NOS CONECTAMOS A LA BASE DE DATOS
            //***********************************************************

            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return conexion;



        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            return null;
        }
        
    }
    
    
    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    public static JSONArray mostrarHabitantes(int limit){
        JSONArray ja = new JSONArray();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs;
            if (limit == 0){
                rs = statement.executeQuery("SELECT *" +
                                            " FROM country LEFT JOIN city" +
                                            " ON city.countryCode = country.code" +
                                            " ORDER BY city.population DESC" +
                                            " LIMIT 50");
            }else{
                rs = statement.executeQuery("SELECT *" +
                                            " FROM city LEFT JOIN country" +
                                            " ON city.CountryCode = country.Code" +
                                            " ORDER BY city.Population DESC" +
                                            " LIMIT "+limit+"");
            }
            
            while (rs.next()){
                JSONObject jo = new JSONObject();
                String nombreciudad = rs.getString("city.name");
                String nombrepais = rs.getString("country.name");
                String habitantes = rs.getString("city.population");

                jo.put("ciudad", nombreciudad);
                jo.put("pais", nombrepais);
                jo.put("habitantes", habitantes);
                
                ja.put(jo);
            }
            rs.close();
            statement.close();
            
        }catch(SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ja;
    }
    
    //Metodo para consultar los habitantes que tiene un pais dado
    public static JSONArray habitantesPais(String nombrePais) {
        
        JSONArray ja = new JSONArray();
        try {
            
            Statement statement = conexion.createStatement();
          
            ResultSet rs = statement.executeQuery("SELECT name,population FROM country WHERE"
                    + " name ='" +nombrePais+"'"
                            + "ORDER BY population DESC");
            
            JSONObject jo;
           
            while (rs.next()) {
                jo = new JSONObject();
                String nombrepais = rs.getString("name");
                String habitantes = rs.getString("population");
                jo.put("PAIS", nombrepais);
                jo.put("HABITANTES", habitantes);
                
                ja.put(jo);
                   
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ja;
    }

    //Metodo para consultar los habitantes que tiene una coidad. Se incluye el
    //nombre del paius en la salida
    public static JSONArray habitantesCiudad(String nombreCiudad) {
        JSONArray ja = new JSONArray();
        try {
            
            Statement statement = conexion.createStatement();
          
            ResultSet rs = statement.executeQuery("SELECT city.name,country.name,city.population"
                    + " FROM city INNER JOIN country ON city.countrycode=country.code "
                    + "WHERE city.name='"+nombreCiudad+"'");
           
            while (rs.next()) {

                String ciudad = rs.getString("city.name");
                String pais = rs.getString("country.name");
                String habitantes = rs.getString("city.population");
                
                JSONObject jo = new JSONObject();

                jo.put("CIUDAD", ciudad);
                jo.put("PAIS", pais);
                jo.put("HABITANTES", habitantes);
                
                ja.put(jo);
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ja;
    }

    
    
    public static JSONArray paisesMinHabitantes(int minhabit) {
        JSONArray ja = new JSONArray();
        try {
            
            Statement statement = conexion.createStatement();
          
            ResultSet rs = statement.executeQuery("SELECT name,population FROM country WHERE"
                    + " population>'"+minhabit+"'");
           
            
            System.out.printf("\nPAIS             HABITANTES\n");
            System.out.printf("---------------------------\n");
                    
            while (rs.next()) {
                JSONObject jo = new JSONObject();

                String nombrepais = rs.getString("name");
                String habitantes = rs.getString("population");

                jo.put("pais", nombrepais);
                jo.put("habitantes", habitantes);
                
                ja.put(jo);
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ja;
    
    }

    public static JSONArray ciudadesMinHabitantes(int minhabit) {
        JSONArray ja = new JSONArray();
       try {
            
            Statement statement = conexion.createStatement();
          
           
             ResultSet rs = statement.executeQuery("SELECT city.name,country.name,city.population"
                    + " FROM city,country "
                    + "WHERE country.code=city.countrycode AND city.population>'"+minhabit+"' ORDER BY 3 DESC");
             
             
             System.out.printf("\nCIUDAD           PAIS           HABITANTES\n");
             System.out.printf("---------------------------------------------\n");
                    
            while (rs.next()) {
                JSONObject jo = new JSONObject();
                String ciudad = rs.getString("city.name");
                String pais = rs.getString("country.name");
                String habitantes = rs.getString("city.population");
                
                jo.put("ciudad", ciudad);
                jo.put("pais", pais);
                jo.put("habitantes", habitantes);
                
                ja.put(jo);
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ja;
    }
}
    

