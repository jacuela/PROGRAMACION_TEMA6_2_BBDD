/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import ejercicio1.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacuela
 */
public class BD_World_antiguo {

    
    private static final String URL = "jdbc:mariadb://localhost:3306/world";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection conexion;
    
    public BD_World_antiguo(){
         //this.conectar();
        
    }
    
    
    public static Connection conectar(){
        
            //***********************************************************
            //    CARGAMOS EL DRIVER
            //***********************************************************
            try {
            
                Class.forName("org.mariadb.jdbc.Driver").newInstance();
                //System.out.println("Driver <org.mariadb.jdbc.Driver> cargado.");
            
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
    
    
    //Metodo para consultar los habitantes que tiene un pais dado
    public static void habitantesPais(String nombrePais) {
        
        try {
            
            Statement statement = conexion.createStatement();
          
            ResultSet rs = statement.executeQuery("SELECT name,population FROM country WHERE"
                    + " name='"+nombrePais+"'");
           
            while (rs.next()) {

                    String nombrepais = rs.getString("name");
                    String habitantes = rs.getString("population");
                    
                    System.out.printf("\nPAIS             HABITANTES\n");
                    System.out.printf("---------------------------\n");
                    System.out.printf("%-20s %s\n",nombrepais, habitantes);
                   
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(main_antiguo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //Metodo para consultar los habitantes que tiene una coidad. Se incluye el
    //nombre del paius en la salida
    public static void habitantesCiudad(String nombreCiudad) {
        try {
            
            Statement statement = conexion.createStatement();
          
            ResultSet rs = statement.executeQuery("SELECT city.name,country.name,city.population"
                    + " FROM city INNER JOIN country ON city.countrycode=country.code "
                    + "WHERE city.name='"+nombreCiudad+"'");
           
            while (rs.next()) {

                    String ciudad = rs.getString("city.name");
                    String pais = rs.getString("country.name");
                    String habitantes = rs.getString("city.population");
                    
                    System.out.printf("\nCIUDAD           PAIS           HABITANTES\n");
                    System.out.printf("---------------------------------------------\n");
                    System.out.printf("%-20s %-20s %s\n",ciudad,pais,habitantes);
                   
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(main_antiguo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
    public static void paisesMinHabitantes(int minhabit) {
        try {
            
            Statement statement = conexion.createStatement();
          
            ResultSet rs = statement.executeQuery("SELECT name,population FROM country WHERE"
                    + " population>'"+minhabit+"'");
           
            
            
            System.out.printf("\nPAIS             HABITANTES\n");
            System.out.printf("---------------------------\n");
                    
            while (rs.next()) {

                    String nombrepais = rs.getString("name");
                    String habitantes = rs.getString("population");
                    
                    System.out.printf("%-20s %s\n",nombrepais, habitantes);
                   
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(main_antiguo.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    public static void ciudadesMinHabitantes(int minhabit) {
       try {
            
            Statement statement = conexion.createStatement();
          
           
             ResultSet rs = statement.executeQuery("SELECT city.name,country.name,city.population"
                    + " FROM city,country "
                    + "WHERE country.code=city.countrycode AND city.population>'"+minhabit+"' ORDER BY 3 DESC");
             
             
             System.out.printf("\nCIUDAD           PAIS           HABITANTES\n");
             System.out.printf("---------------------------------------------\n");
                    
            while (rs.next()) {

                String ciudad = rs.getString("city.name");
                String pais = rs.getString("country.name");
                String habitantes = rs.getString("city.population");
                    
                System.out.printf("%-20s %-20s %s\n",ciudad,pais,habitantes); 
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(main_antiguo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    
    
    }
    
    //Metodo para listar los habitantes de todas las ciudades de la
    //base de datos. Mostramos tb el nombre del pais.
    //La variable limite es para limitar la salida
    public static void mostrarHabitantesCiudad(int limite) {
        try {
            String sql;
            Statement statement = conexion.createStatement();
            
            if (limite!=0){
                sql=String.format("SELECT city.name,country.name,city.population"
                    + " FROM city INNER JOIN country ON city.countrycode=country.code "
                    + "ORDER BY 3 DESC LIMIT %d",limite);
            }
            else{
                sql=String.format("SELECT city.name,country.name,city.population"
                    + " FROM city INNER JOIN country ON city.countrycode=country.code "
                    + "ORDER BY 3 DESC LIMIT 50");
            }
            
            ResultSet rs = statement.executeQuery(sql);
           
            System.out.printf("\nCIUDAD           PAIS           HABITANTES\n");
            System.out.printf("---------------------------------------------\n");
            while (rs.next()) {

                    String ciudad = rs.getString("city.name");
                    String pais = rs.getString("country.name");
                    String habitantes = rs.getString("city.population");
                    
                    System.out.printf("%-20s %-20s %s\n",ciudad,pais,habitantes);
                   
            }

            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            Logger.getLogger(main_antiguo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
}

