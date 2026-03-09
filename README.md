# 🚀 ForoHub - API de Gestión de Discusiones
¡Bienvenido a **ForoHub!** Este es un proyecto Backend desarrollado en Java con Spring Boot, diseñado para funcionar como el corazón lógico de un foro de discusión.

La aplicación permite gestionar tópicos de consulta de forma dinámica, asegurando la persistencia de los datos en PostgreSQL y protegiendo cada operación mediante un sistema de seguridad de vanguardia basado en Tokens JWT.

## 🛠️ Tecnologías Utilizadas
Este proyecto fue construido utilizando las siguientes tecnologías y herramientas:

Java 17 (o superior)

Spring Boot 3 (Framework principal)

Spring Data JPA (Para la persistencia de datos mediante Hibernate)

Spring Security (Para el blindaje de la API y gestión de autenticación)

PostgreSQL (Motor de base de datos relacional)

Maven (Gestor de dependencias)

JSON Web Token (JWT) (Para la generación de credenciales de acceso seguras)

## ⚙️ Características y Funcionalidades
La aplicación es una API REST profesional que ofrece las siguientes capacidades mediante peticiones HTTP:

Gestión de Tópicos (CRUD): Permite crear, listar, actualizar y eliminar tópicos de discusión. Cada ingreso es validado para evitar datos nulos o tópicos duplicados en la base de datos.

Autenticación de Usuarios: Sistema de login que valida credenciales contra la base de datos utilizando encriptación BCrypt para las contraseñas.

Seguridad Stateless: Implementación de un escáner de seguridad que exige un Token JWT válido para acceder a las rutas de escritura, edición o borrado.

Tratamiento de Errores: Panel de control global que captura excepciones y devuelve respuestas JSON limpias y profesionales ante cualquier fallo del sistema.


## 🔌 Endpoints Principales
   Método     | Endpoint       | Descripción
## POST	         /login 	        Autentica usuario y devuelve el JWT.
## GET	         /topicos	        Lista todos los tópicos registrados.
## POST          /topicos       	Registra un nuevo tópico (Requiere Token).
## PUT	         /topicos/{id}    Actualiza un tópico existente (Requiere Token).
## DELETE	       /topicos/{id}	  Elimina un tópico por ID (Requiere Token).


## 🚀 Arquitectura del Proyecto
El sistema está diseñado siguiendo buenas prácticas de Cyber Defense y separación de responsabilidades:

model: Contiene los Records (DTOs) para el filtrado de entrada de datos y las @Entity (Clase Topico y Usuario) que definen las tablas en PostgreSQL.

repository: Interfaces que gestionan la comunicación con la base de datos y la búsqueda de usuarios por login.

infra: Contiene la lógica de seguridad (Filtros, Configuración de BCrypt y Fábrica de Tokens) y el tratador de errores global.

controller: Los puntos de entrada (Endpoints) que gestionan las peticiones /topicos y la ruta de /login.

## 🔧 Cómo ejecutar el proyecto localmente
Clonar este repositorio.

Tener instalado PostgreSQL y crear una base de datos local llamada forohub.

Configurar tus credenciales en el archivo src/main/resources/application.properties:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/forohub
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

api.security.secret=TU_CLAVE_SECRETA_PARA_JWT
Ejecutar la aplicación desde IntelliJ y realizar las pruebas mediante Postman o Insomnia.

👤 Autor


<img src="assets/autor.jpg" alt="Agus" width="120" style="border:4px solid #4CAF50;">

Agustín Negri Hrytezuk

GitHub <img src="assets/github.png" alt="git" width="30"/> | LinkedIn <img src="assets/linkedin.png" alt="linkedin" width="30"/>
