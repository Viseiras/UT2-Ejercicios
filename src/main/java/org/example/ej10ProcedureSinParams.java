package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class ej10ProcedureSinParams {

     public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            //Registrando el Driver
            /*String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            
            System.out.println("Driver "+driver+" Registrado correctamente");*/
            
            //Abrir la conexión con la Base de Datos
            System.out.println("Conectando con la Base de datos...");
            String jdbcUrl = "jdbc:postgresql://localhost/pedidos";
            conn = DriverManager.getConnection(jdbcUrl,"root","root");
            System.out.println("Conexión establecida con la Base de datos...");
            
            String sentenciaSql = "call borracategorias()";

              cstmt = conn.prepareCall(sentenciaSql);
              cstmt.execute();
            }catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            try {
                if(cstmt!=null)
                    cstmt.close();
                if(conn!=null)
                    conn.close();
                System.out.println("Cerrando conexión con la BD");
            } catch(SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try  
    }
    
}
