package Tienda.entidades;


public class producto {
    private int codigo;
    private String nombre;
    private double precio;
    private int codFab;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodFab() {
        return codFab;
    }

    public void setCodFab(int codFab) {
        this.codFab = codFab;
    }

    public producto(int codigo, String nombre, double precio, int codFab) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.codFab = codFab;
    }

    public producto() {
    }

    @Override
    public String toString() {
        return "producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", codFab=" + codFab + '}';
    }
    
}
