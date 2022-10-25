package org.example;

import java.sql.*;

public class ej3Insert {
    
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conexion = null;
        Statement stmt = null;
        try {
            //Registrando el Driver
            /*String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            System.out.println("Driver "+driver+" Registrado correctamente");*/
            
           
            //Abrir la conexión con la Base de Datos
            System.out.println("Conectando con la Base de datos...");
            String jdbcUrl = "jdbc:postgresql://localhost/pedidos";
            conexion = DriverManager.getConnection(jdbcUrl,"root","root");
            System.out.println("Conexión establecida con la Base de datos...");

            //CREANDO UN OBJETO STATEMENT
            
            stmt = conexion.createStatement();

            String sql = "CREATE TABLE CATEGORIAS2(CATEGORIAID int NOT NULL,NOMBRECAT char(50) NOT NULL, CONSTRAINT PK_CATEGORIAS2 PRIMARY KEY(CATEGORIAID) )";
          
            stmt.executeUpdate(sql);

            //Añadimos datos a la tabla vendedores
            sql = "INSERT INTO categorias2 VALUES (1,'nuevacategoria')";
            stmt.executeUpdate(sql);
            
            System.out.println("Registro añadido!");
            
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null)
                    stmt.close();                
                if(conexion!=null)
                    conexion.close();
                System.out.println("Conexión cerrada");
            } catch(SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try  
    }
    
}
