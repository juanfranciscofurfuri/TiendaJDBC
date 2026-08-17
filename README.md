# TiendaJDBC

Sistema de gestión de ventas por consola, en Java, con persistencia en MySQL a
través de JDBC.

Los productos viven en la base de datos, no en memoria: la aplicación arma la
consulta, JDBC la envía a MySQL, y los resultados vuelven al programa. Ese ciclo
es el punto del proyecto — separa el acceso a datos de la lógica en lugar de
resolver todo en una sola clase.

*[English below](#english)*

## Estructura

```
src/main/java/Tienda/
├── Main.java                        punto de entrada
├── entidades/producto.java          el modelo
├── persistencia/DAO.java            acceso a datos: conexión y ejecución de queries
└── servicios/ProductoServicio.java  menú y operaciones
```

Tres capas: la entidad describe el dato, el DAO habla con la base, y el servicio
tiene las operaciones. Sumar una entidad nueva es agregar una clase por capa, no
tocar todo.

## Qué hace

- Listar productos, y listar nombre con precio
- Filtrar por rango de precio y por tipo de producto
- Buscar el producto más barato
- Alta de productos y de fabricantes
- Editar nombre o precio de un producto
- Eliminar productos

## Cómo ejecutarlo

Requiere Java 17 o superior, Maven y MySQL 8.

**1. Crear la base de datos**

```bash
mysql -u root -p < schema.sql
```

Esto crea la base `tienda`, sus dos tablas y datos de ejemplo.

**2. Configurar la conexión**

Los datos de conexión se leen de variables de entorno. Si no las definís se usan
los valores de desarrollo local (`localhost:3306`, base `tienda`, usuario `root`,
contraseña vacía).

```bash
export DB_HOST=localhost:3306 DB_NAME=tienda DB_USER=root DB_PASS=tu_password
```

**3. Compilar y ejecutar**

```bash
mvn clean package
```

```bash
java -jar target/TiendaJDBC-1.0-SNAPSHOT.jar
```

El build empaqueta el driver de MySQL dentro del jar, así que no hace falta
agregar nada al classpath.

## Notas

Proyecto hecho durante la Tecnicatura en Programación en UTN FRM. Usa JDBC directo
en lugar de un ORM, así que las consultas SQL y el manejo de conexiones están
escritos a mano.

Las escrituras usan `PreparedStatement` con parámetros: la consulta viaja con
marcadores `?` y los valores van aparte, de modo que el driver los trata como
datos y no como parte del SQL.

---

<a name="english"></a>

## English

A console-based sales management system in Java, with MySQL persistence through
JDBC.

Products live in the database rather than in memory: the application builds the
query, JDBC sends it to MySQL, and the results come back into the program. That
round trip is the point of the project — it separates data access from logic
instead of keeping everything in one class.

**Structure:** three layers — `entidades` holds the model, `persistencia` talks to
the database, `servicios` holds the operations.

**What it does:** list products, filter by price range and product type, find the
cheapest item, add products and manufacturers, edit and delete.

**Running it:** requires Java 17+, Maven and MySQL 8. Load `schema.sql` into MySQL,
set your credentials in `DAO.java`, then `mvn clean package` and run the jar.

Built while studying Programming at UTN FRM, Mendoza. Uses plain JDBC rather than
an ORM, so the SQL and connection handling are written by hand.
