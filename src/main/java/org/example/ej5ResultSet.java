package org.example;
import java.sql.*;

public class ej5ResultSet {

         public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            //Registrando el Driver
            /*String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            System.out.println("Driver "+driver+" Registrado correctamente");*/
            
            //Abrir la conexión con la Base de Datos
            System.out.println("Conectando con la Base de datos...");
            String jdbcUrl = "jdbc:postgresql://172.22.0.2:5432/pedidos";
            conn = DriverManager.getConnection(jdbcUrl,"root","root");
            System.out.println("Conexión establecida con la Base de datos...");
            
            stmt = conn.createStatement();
            
            //Ejecutamos la SELECT sobre la tabla articulos
            String sql = "select * from clientes";
            
            rs = stmt.executeQuery(sql);
            
            System.out.println("Cursor antes de la primera fila = "+ rs.isBeforeFirst());
            int id;
            String cedula,nomcia,nomcontacto,direcli;
            
            while (rs.next()) {
                //Obtenemos la información por el nombre de la columna
                
                id = rs.getInt("clienteid");
                cedula = rs.getString("cedula_ruc");
                nomcia = rs.getString("nombrecia");
                
                //Obtenemos la información por el indice de la columna
                nomcontacto = rs.getString(4);
                direcli = rs.getString(5);
                

                //Mostramos la información
                System.out.print("Numero de Fila=" + rs.getRow());
                System.out.print(", id: " + id);
                System.out.print(", cédula: " + cedula);
                System.out.print(", nombre compañía: " + nomcia);
                System.out.print(", persona de contacto: " + nomcontacto);
                System.out.println(", dirección: " + direcli);
            }
            System.out.println("Cursor despues de la ultima fila= " +rs.isAfterLast());            
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(stmt!=null)
                    stmt.close();
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try  
    }
    
}
