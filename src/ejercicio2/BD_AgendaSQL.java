/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
    
    public static void insertar(String nombre, String apellidos, String email, LocalDate fecha){
    try {
            
            Statement statement = conexion.createStatement();
            String sql=String.format("INSERT INTO contactos VALUES(null,'%s','%s','%s','%s')",nombre,apellidos,email,fecha);
            
            //System.out.println("SQL-->"+sql);
            
            statement.executeUpdate(sql);
            //System.out.println("Insertado Correctamente.");
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
    
    public static void listar(){
    try {
            
            Statement statement = conexion.createStatement();
          
            ResultSet rs = statement.executeQuery("SELECT id, nombre, apellidos, email, DATE_FORMAT(fecha_nac,'%d/%m/%Y') as FechaNac FROM contactos");
            System.out.println("");System.out.println("");
            System.out.println("************************AGENDA*****************************");
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String fechaNac = rs.getString("FechaNac");
        
                System.out.printf("%1d %-10s %-15s %-20s %10s\n",id, nombre, apellidos,email,fechaNac);
                   
            }
            System.out.println("***********************************************************");
            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    public static void listarLetra(char letra){
    try {
            
            Statement statement = conexion.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT id, nombre, apellidos, email, DATE_FORMAT(fecha_nac,'%d/%m/%Y') as FechaNac "
                    + "FROM contactos "
                    + "WHERE nombre LIKE '"+letra+"%'");
            
            System.out.println("\n\033[34mLISTA DE CONTACTOS QUE EMPIEZAN POR....\033[32m"+letra);
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String fechaNac = rs.getString("FechaNac");
                    
                System.out.printf("\033[34m%1d %-10s %-15s %-20s %10s\033[30m\n",id, nombre, apellidos,email,fechaNac);  
            }
            
            //System.out.println("***********************************************************");
            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    public static void listarPorMes(int mes){
    try {
            
            Statement statement = conexion.createStatement();
          
            ResultSet rs = statement.executeQuery("SELECT id, nombre, apellidos, email, DATE_FORMAT(fecha_nac,'%d/%m/%Y') FechaNac "
                    + "FROM contactos "
                    + "WHERE MONTH(fecha_nac) = "+mes);
           
            System.out.println("\n\033[34mCONTACTOS NACIDOS EN EL MES....\033[32m"+mes);
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String fechaNac = rs.getString("FechaNac");
                    
                System.out.printf("\033[34m%1d %-10s %-15s %-20s %10s\033[30m\n",id, nombre, apellidos,email,fechaNac);  
            }
            rs.close();
            statement.close();
            //conexion.close();    
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
}
