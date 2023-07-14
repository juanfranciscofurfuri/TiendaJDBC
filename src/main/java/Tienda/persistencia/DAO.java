/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Tienda.persistencia;

import java.sql.*;



public abstract class DAO {
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    
    private final String USER = "root";
    private final String PASS = "root";
    private final String DATABASE = "tienda";
    
    protected void ConectarBase() throws Exception{
        try {
            String urlBaseDeDatos="jdbc:mysql://localhost:3306/" + DATABASE + "?characterEncoding=utf8";
            conexion = DriverManager.getConnection(urlBaseDeDatos,USER,PASS);
        } catch (SQLException ex) {
            throw ex;
        }
    }
    protected void desconectarBase() throws Exception{
        try {
            if (resultado!=null) {
                resultado.close();
            }
            if (conexion!=null) {
                conexion.close();
            }
            if (sentencia!=null) {
                sentencia.close();
            }
        } catch (Exception e) {
            throw e;    
        }
    }
    protected void insertarModificarEliminar(String sql) throws Exception{
        try {
            ConectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            throw ex;
        }
        finally{
            desconectarBase();
        }
    }
    protected void consultarBaseDeDatos(String sql) throws Exception{
        try {
            ConectarBase();
            sentencia = conexion.createStatement();
            resultado=sentencia.executeQuery(sql);           
        } catch (Exception e){
            throw e;
        }
        }
    }

