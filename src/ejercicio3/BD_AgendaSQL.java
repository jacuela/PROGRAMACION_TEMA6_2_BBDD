/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import ejercicio2.*;
import ejercicio4old.Equipo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alberto
 */
public class BD_AgendaSQL {
    
    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection conexion;
    public static final String COLOR_AZUL="\033[34m";
    public static final String COLOR_NEGRO="\033[30m";
    
    
    
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
    
    
    public static void insertar(Contacto c){
        try {
            Statement statement = conexion.createStatement();
           
            String sql=String.format("INSERT INTO contactos VALUES(null,'%s','%s','%s','%s')",
                    c.getNombre(),c.getApellidos(),c.getEmail(),c.getFecha_nac());
            //System.out.println("SQL-->"+sql);
            statement.executeUpdate(sql);
           
            statement.close();
      } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
    public static void eliminar(int id){
        try {
            
            Statement statement = conexion.createStatement();
          
            statement.executeUpdate("DELETE FROM contactos WHERE id = '"+id+"'");
            //System.out.println("Borrado Correctamente.");
            
            statement.close();
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
   
    
    public static ArrayList<Contacto> getListaCompleta(){
        try {
            ArrayList<Contacto> listaContactos=new ArrayList();
            
            Statement statement = conexion.createStatement();
          
            //ResultSet rs = statement.executeQuery("SELECT id, nombre, apellidos, email, DATE_FORMAT(fecha_nac,'%d/%m/%Y') as FechaNac FROM contactos");
            ResultSet rs = statement.executeQuery("SELECT id, nombre, apellidos, email, fecha_nac FROM contactos");
           
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String fecha = rs.getString("fecha_nac");
                LocalDate fechaNac = LocalDate.parse(fecha);
               
                //System.out.printf("%1d %-10s %-15s %-20s %10s\n",id, nombre, apellidos,email,fechaNac.format(dtf));
                listaContactos.add(new Contacto(id,nombre,apellidos,email,fechaNac));
            }
            rs.close();
            statement.close();
            return listaContactos;
            
        } catch (SQLException ex) {
            System.out.println("ERROR: sql excepcion");
            System.out.println(ex);
            return null;
        } catch(Exception ex){
            System.out.println("ERROR: parse de fecha");
            System.out.println(ex);
            return null;
        }    
        
        
        
        
    
    }
    
    
    
    public static  ArrayList<Contacto> getListaPorLetra(char letra){
    try {
            ArrayList<Contacto> listaContactos=new ArrayList();
            Statement statement = conexion.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT id, nombre, apellidos, email, fecha_nac "
                    + "FROM contactos "
                    + "WHERE nombre LIKE '"+letra+"%'");
            
            //System.out.println("\n\033[34mLISTA DE CONTACTOS QUE EMPIEZAN POR....\033[32m"+letra);
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String fecha = rs.getString("fecha_nac");
                LocalDate fecha_nac=LocalDate.parse(fecha);
                
                //System.out.printf("\033[34m%1d %-10s %-15s %-20s %10s\033[30m\n",id, nombre, apellidos,email,fecha);
                listaContactos.add(new Contacto(id,nombre,apellidos,email,fecha_nac));
                
            }
            
            rs.close();
            statement.close();
            //conexion.close();    
            return listaContactos;
            
        } catch (SQLException ex) {
            System.out.println("ERROR: sql excepcion");
            System.out.println(ex);
            return null;
        } catch(Exception ex){
            System.out.println("ERROR: parse de fecha");
            System.out.println(ex);
            return null;
        }     
        
    }
    
    
    
    
}
