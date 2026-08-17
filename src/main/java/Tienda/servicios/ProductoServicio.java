package Tienda.servicios;

import Tienda.persistencia.DAO;
import java.util.Scanner;

public class ProductoServicio extends DAO {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void Menu() throws Exception {
        int rta;
        do {
            System.out.println("""
                =========================================================================
                ============================== MENU =====================================
                =                                                                       =
                =        1 - Listar productos                                           =
                =        2 - Listar nombre y precio de productos                        =
                =        3 - Listar productos entre $120 y $202                         =
                =        4 - Listar Portatiles                                          =
                =        5 - Listar producto mas barato                                 =
                =        6 - Ingresar producto                                          =
                =        7 - Ingresar fabricante                                        =
                =        8 - Eliminar producto                                          =
                =        9 - Editar producto                                            =
                =       10 - Salir                                                      =
                =                                                                       =
                =========================================================================
                """);
            rta = leer.nextInt();
            switch (rta) {
                case 1 -> listarNombres("SELECT nombre FROM producto");
                case 2 -> listarNombreYPrecio("SELECT nombre,precio FROM producto");
                case 3 -> listarNombreYPrecio(
                        "SELECT nombre,precio FROM producto WHERE precio BETWEEN 120 AND 202");
                case 4 -> listarNombreYPrecio(
                        "SELECT nombre,precio FROM producto WHERE nombre LIKE 'Portátil%'");
                case 5 -> listarNombreYPrecio(
                        "SELECT nombre,precio FROM producto ORDER BY precio ASC LIMIT 1");
                case 6 -> ingresarProducto();
                case 7 -> ingresarFabricante();
                case 8 -> eliminarProducto();
                case 9 -> editarProducto();
                case 10 -> System.out.println("""
                    =========================================================================
                    =                      Saliendo del programa                            =
                    =========================================================================
                    """);
                default -> System.out.println("Ingrese una opcion correcta");
            }
        } while (rta != 10);
    }

    private void listarNombres(String sql) {
        try {
            consultarBaseDeDatos(sql);
            while (resultado.next()) {
                System.out.println("Nombre: " + resultado.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cerrar();
        }
    }

    private void listarNombreYPrecio(String sql) {
        try {
            consultarBaseDeDatos(sql);
            while (resultado.next()) {
                System.out.println("Nombre: " + resultado.getString(1)
                        + " Precio: " + resultado.getDouble(2));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cerrar();
        }
    }

    private void ingresarProducto() {
        System.out.println("Ingrese el nombre del producto");
        String nombre = leer.next();
        System.out.println("Ingrese el precio del producto");
        double precio = leer.nextDouble();
        System.out.println("Ingrese el codigo de fabricante");
        int codFabricante = leer.nextInt();

        String sql = "INSERT INTO producto(nombre,precio,codigo_fabricante) VALUES(?,?,?)";
        try {
            insertarModificarEliminar(sql, nombre, precio, codFabricante);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void ingresarFabricante() {
        System.out.println("Ingrese el nombre de fabricante:");
        String nombre = leer.next();

        String sql = "INSERT INTO fabricante(nombre) VALUES(?)";
        try {
            insertarModificarEliminar(sql, nombre);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void eliminarProducto() {
        System.out.println("Ingrese el codigo del producto que desea eliminar");
        int codigo = leer.nextInt();

        String sql = "DELETE FROM producto WHERE codigo=?";
        try {
            insertarModificarEliminar(sql, codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void editarProducto() {
        System.out.println("Ingrese el codigo del producto que desea editar");
        int codigo = leer.nextInt();
        System.out.println("Que desea editar? NOMBRE(N) O PRECIO (P)");

        String sql;
        Object valor;
        while (true) {
            String opcion = leer.next().toUpperCase();
            if (opcion.equals("N")) {
                System.out.println("Ingrese el nombre al cual desea cambiar:");
                valor = leer.next();
                sql = "UPDATE producto SET nombre=? WHERE codigo=?";
                break;
            } else if (opcion.equals("P")) {
                System.out.println("Ingrese el precio al cual desea cambiar:");
                valor = leer.nextDouble();
                sql = "UPDATE producto SET precio=? WHERE codigo=?";
                break;
            }
            System.out.println("Ingrese una opcion correcta");
        }

        try {
            insertarModificarEliminar(sql, valor, codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cerrar() {
        try {
            desconectarBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
