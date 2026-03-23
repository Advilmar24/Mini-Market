# Implementación de Ventas, Detalle y Reglas de Negocio

El proyecto actual solo cuenta con la entidad [Sales.java](file:///c:/Users/jorge/Desktop/Homework_Springboot/Mini-Market/mini-market/src/main/java/com/groupadso/mini_market/Entity/Sales.java). Para cumplir con los requerimientos, necesitamos modelar la base de datos completa para una venta y aplicar las reglas de validación de stock.

## Cambios Propuestos

### Entidades (Capa de Datos)
- **[NUEVO] `Employee.java`**: Entidad para el Empleado. Contendrá ID y nombre.
- **[NUEVO] `Product.java`**: Entidad para el Producto. Contendrá ID, nombre, precio y `stock` (requerido para la Regla 1 y 2).
- **[MODIFICAR] [Sales.java](file:///c:/Users/jorge/Desktop/Homework_Springboot/Mini-Market/mini-market/src/main/java/com/groupadso/mini_market/Entity/Sales.java)**: Modificar para incluir la relación obligatoria (`@ManyToOne`) con `Employee`. Modificar para incluir una lista de `SalesDetail`.
- **[NUEVO] `SalesDetail.java`**: Entidad para el Detalle de Venta. Tendrá relación `@ManyToOne` con [Sales](file:///c:/Users/jorge/Desktop/Homework_Springboot/Mini-Market/mini-market/src/main/java/com/groupadso/mini_market/Entity/Sales.java#11-26) y con `Product`, además de cantidad y subtotal.

### Repositorios
- **[NUEVO]** `EmployeeRepository.java`, `ProductRepository.java`, `SalesDetailRepository.java` para manejar la persistencia de las nuevas entidades.

### DTOs (Capa de Transferencia)
- **[MODIFICAR] [SalesRequestDTO.java](file:///c:/Users/jorge/Desktop/Homework_Springboot/Mini-Market/mini-market/src/main/java/com/groupadso/mini_market/DTO/SalesRequestDTO.java)**: Añadir `employeeId` y una lista de `SalesDetailRequestDTO` para recibir los productos que se quieren vender.
- **[NUEVO] `SalesDetailRequestDTO.java`**: DTO para recibir el `productId` y la `quantity` (cantidad) que se desea vender.

### Servicio y Reglas de Negocio (Capa Lógica)
- **[MODIFICAR] [SalesService.java](file:///c:/Users/jorge/Desktop/Homework_Springboot/Mini-Market/mini-market/src/main/java/com/groupadso/mini_market/Service/SalesService.java)**:
  - **Relación Obligatoria**: Validar que el `employeeId` exista en la BD.
  - **Regla 1 (Restar Stock)**: Recorrer los detalles de venta, buscar el producto, y restar la cantidad comprada al stock actual del producto.
  - **Regla 2 (Validar Stock - Error 400)**: Antes de restar, verificar si `producto.getStock() >= cantidad`. Si no es suficiente, lanzar una excepción personalizada (ej. `InsufficientStockException`).

### Controladores (Capa Web)
- **[MODIFICAR] [SalesController.java](file:///c:/Users/jorge/Desktop/Homework_Springboot/Mini-Market/mini-market/src/main/java/com/groupadso/mini_market/Controller/SalesController.java)**: Añadir un manejador de excepciones (`@ExceptionHandler`) o capturar la excepción de stock insuficiente para que retorne un HTTP Status `400 Bad Request` en lugar de un 500.

## Plan de Verificación

### Verificación Manual
1. Se crearán datos de prueba (Mocks o mediante un archivo `data.sql` / inserciones iniciales) para tener al menos un Empleado y un Producto con un stock conocido (ej. 10 unidades).
2. Se realizará una petición POST `/sales` intentando comprar 5 unidades. Se verificará que retorne un estado HTTP `201 OK/Created` y el stock en base de datos baje a 5.
3. Se realizará una petición POST `/sales` intentando comprar 20 unidades de ese mismo producto. Se verificará que retorne un estado HTTP `400 Bad Request` indicando "Stock insuficiente".
