# Proyecto: Flujo Bancario

## Descripción
Este proyecto implementa un sistema de flujo bancario compuesto por microservicios para la gestión de clientes, cuentas y transacciones. Utiliza tecnologías modernas para backend y frontend, así como herramientas de configuración y descubrimiento de servicios.

## Tecnologías Utilizadas

### **Backend**
- **JDK:** 17
- **Spring Boot:** 3.4.2
- **Spring Cloud:**
  - `spring-cloud-config-server`
  - `spring-cloud-starter-netflix-eureka-server`
- **Mensajería:** RabbitMQ
- **Base de datos:** PostgreSQL en producción, H2 en desarrollo
- **Lombok:** Anotaciones para simplificar el desarrollo de Java automatizando la generación de código

## Patrones de Desarrollo y Diseño Utilizados
Este proyecto sigue un enfoque modular basado en microservicios, aplicando los siguientes patrones de desarrollo y diseño:

### **Patrones de Desarrollo**
- **Repository:** Manejo de acceso a datos a través de interfaces JPA.
- **Services:** Separación de lógica de negocio mediante servicios especializados.
- **DAO (Data Access Object):** Patrón para la abstracción del acceso a datos.
- **DTO (Data Transfer Object):** Transferencia de datos eficiente entre capas del sistema.

### **Patrones de Diseño**
- **API Gateway:** Manejo centralizado de las solicitudes hacia los microservicios.
- **Mesh Architecture:** Gestión de comunicación entre microservicios con patrones de resiliencia y escalabilidad.
- **Microservices:** Arquitectura basada en servicios independientes y desplegables individualmente.
- **Microrepo Maven:** Modularidad en Maven con generación de reportes como submódulos.

## Arquitectura
El sistema se compone de los siguientes módulos:

1. **Frontend** (React, Axios, Bootstrap) - Interfaz gráfica de usuario.
2. **Config Server** (Spring Cloud Config Server) - Centraliza la configuración de los microservicios.
3. **Eureka Server** (Spring Cloud Netflix Eureka) - Registro y descubrimiento de microservicios.
4. **Microservicio Cliente** - Maneja la información de clientes.
5. **Microservicio Transacción** - Gestiona cuentas y movimientos bancarios.
6. **Base de Datos** - Se utiliza PostgreSQL en producción y H2 en desarrollo.
7. **RabbitMQ** - Mensajería utilizada para la comunicación entre microservicios.
8. **API Gateway** - Enrutador de peticiones centralizado para los microservicios.

## Ejecución del Proyecto

### **Pasos para Construcción y Despliegue**
Para comenzar, colóquese en la raíz del proyecto:
```sh
cd /flujobancario-parent/
```

#### **1️⃣ Construcción y Despliegue de Eureka Server**
```sh
cd infraestructura/eureka-server
./mvnw package
```
Para construir la imagen y subir al repositorio local de docker:
```sh
docker build -t eurekaserver_image --no-cache --build-arg JAR_FILE=target/*.jar .
```

#### **2️⃣ Construcción y Despliegue de Config Server**
```sh
cd ../config-server
./mvnw package
```
Para construir la imagen y subir al repositorio local de docker:
```sh
docker build -t config-server_image --no-cache --build-arg JAR_FILE=target/*.jar .
```

#### **3️⃣ Construcción y Despliegue del Microservicio Cliente**
```sh
cd ../../corebancario/cliente
./mvnw package
```
Para construir la imagen y subir al repositorio local de docker:
```sh
docker build -t cliente_image --no-cache --build-arg JAR_FILE=target/*.jar .
```

#### **4️⃣ Construcción y Despliegue del Microservicio Transacción**
```sh
cd ../transaccion
./mvnw package
```
Para construir la imagen y subir al repositorio local de docker:
```sh
docker build -t transaccion_image --no-cache --build-arg JAR_FILE=target/*.jar .
```

## Configuración con Docker y la orquestacion con Docker Compose
El proyecto incluye un archivo `docker-compose.yml` para la orquestación de servicios. Para levantar toda la infraestructura (incluyendo RabbitMQ):
Se debe ir a la raiz del proyecto
```sh
cd ./flujobancario-parent/
docker compose up --build
```

### **Ejecución en Ambiente de Desarrollo (Dev)**
Para ejecutar los microservicios en un ambiente de desarrollo utilizando el perfil `dev`, use el siguiente comando dentro de cada microservicio:
```sh
cd ./flujobancario-parent/corebancario/cliente
./mvnw clean package
java -jar target/cliente-1.0.0-SNAPSHOT.jar --spring.config.location=file:src/main/resources/application-dev.properties
```
```sh
cd ./flujobancario-parent/corebancario/transaccion
./mvnw clean package
java -jar target/transaccion-1.0.0-SNAPSHOT.jar --spring.config.location=file:src/main/resources/application-dev.properties
```
Esto asegurará que la aplicación utilice el archivo `application-dev.properties` y la base de datos en memoria H2.

### **RabbitMQ en Desarrollo**
Si desea ejecutar RabbitMQ localmente en lugar de Docker, asegúrese de tenerlo instalado y ejecutándose con:
```sh
rabbitmq-server
```

## Autor
Desarrollado por [Francisco Fernando Valarezo Benítez]
