package org.example;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class ej1Conexio {

    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        try {
            //REGISTRANDO EL DRIVER
            //String driver = "com.mysql.jdbc.Driver";
            //Class.forName(driver).newInstance();

            //System.out.println("Driver Registrado correctamente");

            //Abrir la conexión con la Base de Datos
            System.out.println("Conectando con la Base de datos...");

            String jdbcUrl = "jdbc:postgresql://localhost:5432/pedidos";
            Connection conexion = DriverManager.getConnection(jdbcUrl,"root","root");

            System.out.println("Conexión establecida con la Base de datos..." + conexion.getSchema());

        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores debidos al Class.forName
            e.printStackTrace();
        } finally {
            try {
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

}