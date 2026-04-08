# 🏫 Sistema de Gestión Escolar "Colegio"

Este es un sistema de escritorio desarrollado en **Java** con una arquitectura orientada a objetos, diseñado para centralizar la administración académica de una institución educativa. El proyecto integra una base de datos **MySQL** para gestionar de forma relacional a estudiantes, acudientes, profesores y sus respectivas asignaciones.

## 🚀 Funcionalidades Principales

### 👨‍👩‍👧 Gestión de Acudientes y Estudiantes
* **Registro Completo:** Administración de datos maestros para acudientes (Documento, Nombre, Teléfono, etc.).
* **Relación N:M:** Vinculación dinámica de múltiples estudiantes a un solo acudiente mediante la tabla intermedia `acudientexestudiante`.
* **Consultas Inteligentes:** Visualización automática de estudiantes a cargo al buscar un acudiente mediante el uso de `INNER JOIN` en SQL.

### 👨‍🏫 Gestión de Profesores y Materias
* **Asignación Académica:** Registro de qué materias imparte cada profesor y en qué grado específico.
* **Control de Datos:** Seguimiento de la carga académica mediante la tabla `materiasxprofesor`.

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java (JDK 1.8).
* **IDE:** NetBeans.
* **Base de Datos:** MySQL con conector `mysql-connector-j-9.6.0`.
* **Interfaz:** Java Swing.

## 📁 Estructura del Proyecto
El código está organizado siguiendo estándares de desarrollo limpio:
* `modelo`: Clases de lógica y acceso a datos (`ClsAcudiente`, `ClsEstudiante`, etc.).
* `vista`: Formularios gráficos (`FrmAcudiente`, `FrmProfesor`, etc.).
* `controlador`: Clase de conexión a la base de datos (`ClsConexion`).
* `baseDatos`: Script SQL para la creación de las tablas relacionales.

## 🔧 Configuración e Instalación
1. Clona el repositorio.
2. Importa el archivo `colegio.sql` ubicado en la carpeta `baseDatos` a tu servidor MySQL.
3. Configura las credenciales de acceso en la clase `ClsConexion.java`.
4. Asegúrate de incluir el driver de MySQL en las librerías del proyecto.

---
**Desarrollado por:** [Arlhey Fabian Abadía Jaimes](https://github.com/sayaxxx)
*Aprendiz SENA - Tecnología en Análisis y Desarrollo de Software*
