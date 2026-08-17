package Tienda.persistencia;

import java.sql.*;

public abstract class DAO {

    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    protected PreparedStatement preparada = null;

    // Los datos de conexión se leen del entorno para no dejarlos escritos en el
    // código. Si las variables no están definidas se usan los valores de
    // desarrollo local que documenta el README.
    private static final String HOST = variableDeEntorno("DB_HOST", "localhost:3306");
    private static final String DATABASE = variableDeEntorno("DB_NAME", "tienda");
    private static final String USER = variableDeEntorno("DB_USER", "root");
    private static final String PASS = variableDeEntorno("DB_PASS", "");

    private static String variableDeEntorno(String nombre, String porDefecto) {
        String valor = System.getenv(nombre);
        return (valor == null || valor.isBlank()) ? porDefecto : valor;
    }

    protected void ConectarBase() throws Exception {
        String urlBaseDeDatos = "jdbc:mysql://" + HOST + "/" + DATABASE + "?characterEncoding=utf8";
        conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASS);
    }

    protected void desconectarBase() throws Exception {
        try {
            if (resultado != null) {
                resultado.close();
                resultado = null;
            }
            if (sentencia != null) {
                sentencia.close();
                sentencia = null;
            }
            if (preparada != null) {
                preparada.close();
                preparada = null;
            }
            if (conexion != null) {
                conexion.close();
                conexion = null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Ejecuta un INSERT, UPDATE o DELETE.
     *
     * La consulta viaja con marcadores '?' y los valores se pasan aparte, de
     * modo que el driver los env&iacute;a como datos y nunca como parte del SQL.
     */
    protected void insertarModificarEliminar(String sql, Object... parametros) throws Exception {
        try {
            ConectarBase();
            preparada = conexion.prepareStatement(sql);
            for (int i = 0; i < parametros.length; i++) {
                preparada.setObject(i + 1, parametros[i]);
            }
            preparada.executeUpdate();
        } finally {
            desconectarBase();
        }
    }

    /**
     * Ejecuta un SELECT y deja el ResultSet abierto en el campo 'resultado'.
     * Quien llama debe recorrerlo y despu&eacute;s invocar desconectarBase().
     */
    protected void consultarBaseDeDatos(String sql) throws Exception {
        ConectarBase();
        sentencia = conexion.createStatement();
        resultado = sentencia.executeQuery(sql);
    }
}
