package org.example;

import java.sql.*;
public class ej4ResultSet {

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
            String jdbcUrl = "jdbc:postgresql://172.22.0.2/pedidos";
            conn = DriverManager.getConnection(jdbcUrl,"root","root");
            System.out.println("Conexión establecida con la Base de datos...");
            
            //USO DEL MÉTODO executeQuery
            
            stmt = conn.createStatement(); //Por defecto sólo lectura y movimiendo hacia delante
            String sql = "select * from categorias";
            rs = stmt.executeQuery(sql);              
/*            System.out.println("Timeout de consulta: "+stmt.getQueryTimeout()+ " sg");
            System.out.println("Número máximo de registros a buscar: "+stmt.getMaxRows());
            System.out.println("Tamaño máximo de campo: "+stmt.getMaxFieldSize()+" bytes");
            System.out.println("Número de registros devueltos cada vez: "+stmt.getFetchSize());*/
            int id ; String descr;
            while(rs.next() )
            { 
            	id = rs.getInt(1);	// leo la primera columna (1) de la fila actual, que es int (getInt)
            	// id = rs.getInt("id");	, equivalente a la línea anterior
            	//descr = rs.getString(2);
            	descr = rs.getString("nombrecat");	// equivalente a la líne anterior
            	System.out.println("Identificador: " + id + ", descripción: " + descr);
            }
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            try {
            	if (rs != null)
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
