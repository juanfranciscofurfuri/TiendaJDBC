
package Tienda.servicios;

import Tienda.persistencia.DAO;
import java.util.Scanner;

public class ProductoServicio extends DAO {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void Menu() throws Exception {
        int rta;
        String sql;
        do {
            System.out.println("""
                                   ===============================================================================
                                   =================================== MENU ======================================
                                   =                                                                             =
                                   =                       1 - Listar productos                                  =
                                   =                       2 - Listar nombre y precio de productos               =
                                   =                       3 - Listar productos entre $120 y $202                =
                                   =                       4 - Listar Portatiles                                 =
                                   =                       5 - Listar producto mas barato                        =
                                   =                       6 - Ingresar producto                                 =
                                   =                       7 - Ingresar fabricante                               =
                                   =                       8 - Eliminar producto                                 =
                                   =                       9 - Editar producto                                   =
                                   =                      10 - Salir                                             =
                                   =                                                                             =
                                   ===============================================================================
                                   """);
            rta = leer.nextInt();
            switch (rta) {
                case 1 -> {
                    
                    sql = "SELECT nombre FROM producto";
                    try {
                        consultarBaseDeDatos(sql);
                        while (resultado.next()) {
                            System.out.println("Nombre: " + resultado.getString(1));

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 2 -> {
                    sql = "SELECT nombre,precio FROM producto";
                    try {
                        consultarBaseDeDatos(sql);
                        while (resultado.next()) {
                            System.out.println("Nombre: " + resultado.getString(1) + " Precio: " + resultado.getDouble(2));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 3 -> {
                    sql = "SELECT nombre,precio FROM producto WHERE precio BETWEEN 120 AND 202";
                    try {
                        consultarBaseDeDatos(sql);
                        while (resultado.next()) {
                            System.out.println("Nombre: " + resultado.getString(1) + " Precio: " + resultado.getDouble(2));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 4 -> {
                    sql = "SELECT nombre,precio FROM producto WHERE nombre LIKE 'Portátil%'";
                    try {
                        consultarBaseDeDatos(sql);
                        while (resultado.next()) {
                            System.out.println("Nombre: " + resultado.getString(1) + " Precio: " + resultado.getDouble(2));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 5 -> {
                    sql = "SELECT nombre,precio FROM producto ORDER BY precio ASC LIMIT 1";
                    try {
                        consultarBaseDeDatos(sql);
                        while (resultado.next()) {
                            System.out.println("Nombre: " + resultado.getString(1) + " Precio: " + resultado.getDouble(2));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 6 -> {
                    System.out.println("Ingrese el nombre del producto");
                    String nom = leer.next();
                    System.out.println("Ingrese el precio del producto");
                    double precio = leer.nextDouble();
                    System.out.println("Ingrese el codigo de fabricante");
                    int codFabri = leer.nextInt();
                    sql = "INSERT INTO producto(nombre,precio,codigo_fabricante)"
                            + "VALUES('" + nom + "','" + precio + "','" + codFabri + "')";
                    try {
                        insertarModificarEliminar(sql);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 7 -> {
                    System.out.println("Ingrese el nombre de fabricante:");
                    String nombre = leer.next();
                    sql = "INSERT INTO fabricante(nombre)"
                            + "VALUES('" + nombre + "')";
                    try {
                        insertarModificarEliminar(sql);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 8 -> {
                    System.out.println("Ingrese el codigo del producto que desea eliminar");
                    int cod = leer.nextInt();
                    sql = "DELETE FROM producto WHERE codigo='" + cod + "'";
                    try {
                        insertarModificarEliminar(sql);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 9 -> {
                    System.out.println("Ingrese el codigo del producto que desea editar");
                    int codigo = leer.nextInt();
                    System.out.println("Que desea editar? NOMBRE(N) O PRECIO (P)");
                    String rta1 = leer.next().toUpperCase();
                    boolean flag = true;
                    sql="";
                    do {
                        switch (rta1) {
                            case "N" -> {
                                System.out.println("Ingrese el nombre al cual desea cambiar:");
                                String nombre1 = leer.next();
                                sql = "UPDATE producto SET nombre='" + nombre1 + "' WHERE codigo='" + codigo + "';";
                                flag = false;
                            }
                            case "P" -> {
                                System.out.println("Ingrese el precio al cual desea cambiar:");
                                double prec1 = leer.nextDouble();
                                sql = "UPDATE producto SET precio='" + prec1 + "' WHERE codigo='" + codigo + "';";
                                flag = false;
                            }
                            default -> System.out.println("Ingrese una opcion correcta");
                        }
                    } while (flag);

                    try {
                        insertarModificarEliminar(sql);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                case 10 -> System.out.println("""
                                   ===============================================================================
                                   =                         Saliendo del programa                               =
                                   ===============================================================================
                                   """);
                default -> System.out.println("Ingrese una opcion correcta");
            }
        } while (rta != 10);
    }
}
