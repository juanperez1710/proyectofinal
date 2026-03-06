# ProyectoFinal

## Descripción
Proyecto final de la academia Java Xideral-Accenture. Esta aplicación está desarrollada con Spring Boot y tiene como objetivo demostrar el uso de controladores, servicios, manejo de excepciones, validaciones y persistencia de datos utilizando JPA y H2.

## Tecnologías
- Java 21
- Spring Boot 3.5.11
- Spring Data JPA
- Spring Validation
- H2 Database
- Maven
- Lombok
- JUnit y Mockito

## Instrucciones de instalación
1. Clona el repositorio:
   ```bash
   git clone <https://github.com/juanperez1710/proyectofinal.git>
   ```
2. Accede al directorio del proyecto:
   ```bash
   cd proyectofinal
   ```
3. Verifica que tengas Java 21 y Maven instalado.
4. Ejecuta la aplicación:
   ```bash
   ./mvnw.cmd spring-boot:run
   ```
   o
   ```bash
   mvn spring-boot:run
   ```

## Instrucciones de ejecución
1. **Ejecutar la aplicación**:
   En Windows:
   ```cmd
   .\mvnw.cmd spring-boot:run
   ```
   En Linux/Mac:
   ```bash
   ./mvnw spring-boot:run
   ```
3. La aplicación iniciará en el puerto **8080**.

---

## API Endpoints

La API REST se encuentra bajo el prefijo `/api/v1`.

### 1. Gestión de Productos

**Listar todos los productos**
- **Método:** `GET`
- **URL:** `/api/v1/products`

**Obtener producto por ID**
- **Método:** `GET`
- **URL:** `/api/v1/products/{id}`

**Buscar producto por nombre**
- **Método:** `GET`
- **URL:** `/api/v1/products/search?name={nombre}`

**Crear un producto**
- **Método:** `POST`
- **URL:** `/api/v1/products`
- **Body (JSON):**
  ```json
  {
    "name": "Laptop Gamer",
    "price": 1500.00,
    "description": "Laptop de alta gama",
    "stock": 10,
    "category": "ELECTRONICS"
  }
  ```
  *Categorías disponibles:* `ELECTRONICS`, `BOOKS`, `CLOTHING`, `FOOD`, `SPORTS`.

**Actualizar un producto**
- **Método:** `PUT`
- **URL:** `/api/v1/products/{id}`
- **Body (JSON):** (Igual al de creación)

**Eliminar un producto**
- **Método:** `DELETE`
- **URL:** `/api/v1/products/{id}`

---

### 2. Gestión de Clientes

**Crear un cliente**
- **Método:** `POST`
- **URL:** `/api/v1/customers`
- **Body (JSON):**
  ```json
  {
    "name": "Juan Pérez",
    "email": "juan.perez@example.com"
  }
  ```

**Obtener órdenes de un cliente**
- **Método:** `GET`
- **URL:** `/api/v1/customers/{id}/orders`

---

### 3. Gestión de Órdenes

**Crear una orden**
- **Método:** `POST`
- **URL:** `/api/v1/orders?customerId={id_cliente}`
- **Parámetros:** `customerId` (Query param obligatorio).
- **Body (JSON):** Lista de ítems a comprar.
  ```json
  [
    {
      "product": { "id": 1 },
      "quantity": 2
    },
    {
      "product": { "id": 2 },
      "quantity": 1
    }
  ]
  ```

**Obtener orden por ID**
- **Método:** `GET`
- **URL:** `/api/v1/orders/{id}`

---

## Base de Datos (H2 Console)

La aplicación utiliza una base de datos en memoria H2. Puedes acceder a la consola web para inspeccionar los datos directamente.

- **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **Driver Class:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User Name:** `sa`
- **Password:** (dejar vacío)

**Nota:** Al ser una base de datos en memoria, los datos se pierden al detener la aplicación.

## Pruebas

Para ejecutar las pruebas unitarias y de integración incluidas en el proyecto:

```bash
.\mvnw.cmd test
```



## Notas
- Si tienes problemas con Maven Wrapper, asegúrate de que el archivo `.mvn/wrapper/maven-wrapper.properties` exista y que tu red permita descargar Maven.
- Puedes modificar la configuración de la base de datos H2 en `application.properties`.

---
Co-authored-by: Copilot <223556219+Copilot@users.noreply.github.com>
