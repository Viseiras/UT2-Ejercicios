package org.example;

import java.sql.*;

public class ej8Transaccion
{
    public static void main(String args[])
    {  
            System.out.println("Conectando con la Base de datos...");
        // LA IP INDICADA ES LA DEL CONTENEDOR POSTGRESQL DE DOCKER: CAMBIARLA SEGÚN CORRESPONDA
            String jdbcUrl = "jdbc:postgresql://localhost/pedidos";
            Connection conexion = null;
            PreparedStatement sentencia=null;
            ResultSet idGenerados = null;

     /* Estos valores vendrán dados por el usuario al introducirlos o seleccionarlos
        * en el interfaz de la aplicación
        */
        
       String descripcionProducto = "salsa de pisto";
       float preciounit = 1.8f;
       int idProveedor=10, idCategoria = 100, existencias =10;
       // El id del producto que vamos a registrar aún no se conoce

       String sqlAltaProducto = "INSERT INTO productos (productoid,proveedorid,categoriaid,descripcion, preciounit,existencia) VALUES (?,?, ?,?, ?,?)";
       String sqlAltaCategoria = 
              "INSERT INTO categorias(categoriaid, nombrecat) " + "VALUES (?, ?)";
                                     
       try {
        conexion =  DriverManager.getConnection(jdbcUrl,"root","root");
         //Inicia transacción
         conexion.setAutoCommit(false);
         
        sentencia = 
                           conexion.prepareStatement(sqlAltaProducto,
                           PreparedStatement.RETURN_GENERATED_KEYS);
        sentencia.setInt(1, 14);
         sentencia.setInt(2, idProveedor);
         sentencia.setInt(3, idCategoria);
         sentencia.setString(4, descripcionProducto);
         sentencia.setFloat(5, preciounit);
         sentencia.setInt(6, existencias);
         sentencia.executeUpdate();
         
         // Obtiene el id del producto que se acaba de registrar
         idGenerados = sentencia.getGeneratedKeys();
         idGenerados.next();
         int idProducto = idGenerados.getInt(1);
         //System.out.println("El identificador del producto añadido es " + idProducto);
         sentencia.close();
         
         sentencia =  conexion.prepareStatement(sqlAltaCategoria);
         idCategoria = 800;
         String nomCateg = "deportes";
         sentencia.setInt(1, idCategoria);
         sentencia.setString(2, nomCateg);
         sentencia.executeUpdate();
         // Valida la transacción
         conexion.commit();
       } catch (SQLException sqle) {
         sqle.printStackTrace();
       } finally {
          try {
               conexion.rollback();
               conexion.setAutoCommit(true);
               idGenerados.close();
               sentencia.close();
           } catch (SQLException sqle) {
             sqle.printStackTrace();
           }
       }
    }
}
