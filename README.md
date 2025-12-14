# Java Swing Refactoring Portfolio

## Descripción General
Este repositorio contiene una colección de aplicaciones de escritorio desarrolladas en Java Swing. El proyecto consiste en la refactorización de ejercicios de programación estándar, transformándolos en soluciones de software profesional mediante la implementación de Arquitectura Limpia, Patrones de Diseño y Estándares de la Industria.

El objetivo principal es demostrar la evolución desde código procedimental básico hacia estructuras orientadas a objetos, escalables y mantenibles.

## Entorno de Desarrollo y Herramientas

### IDE: IntelliJ IDEA
Se seleccionó IntelliJ IDEA en lugar de NetBeans por las siguientes capacidades técnicas:
* **Gestión de Dependencias:** Integración nativa robusta con Maven para gestionar librerías externas (como OpenPDF).
* **Análisis Estático de Código:** Detección proactiva de errores y sugerencias de optimización para versiones modernas de Java.
* **Refactorización Avanzada:** Herramientas automatizadas para la extracción de métodos e interfaces, facilitando la limpieza del código.

### Arquitectura: Modelo-Vista-Controlador (MVC)
Cada módulo del proyecto implementa estrictamente el patrón MVC para desacoplar responsabilidades:
1.  **Modelo (Lógica):** Clases Java puras (POJOs) que contienen reglas de negocio, validaciones y cálculos matemáticos. No tienen dependencia de la interfaz gráfica.
2.  **Vista (Interfaz):** Clases `JFrame` y `JPanel` encargadas únicamente de la presentación visual y captura de eventos.
3.  **Controlador (Coordinación):** Intermediario que gestiona la comunicación entre la Vista y el Modelo, procesando eventos y actualizando el estado de la aplicación.

## Módulos del Proyecto

A continuación se detallan los ejercicios implementados y las mejoras técnicas aplicadas:

| Módulo                 | Concepto Original           | Mejora Técnica (Refactorización)                                                                                 |
| :--------------------- | :-------------------------- | :--------------------------------------------------------------------------------------------------------------- |
| **Nómina Salarial**    | Arreglos fijos y Popups     | Implementación de POO (Clase Empleado), Colecciones Dinámicas (`ArrayList`) y JTable reactivo.                   |
| **Seguridad MDI**      | Lógica en botones           | Arquitectura MDI (Escritorio Virtual), Servicio de Autenticación (`AuthService`) y control de intentos fallidos. |
| **Amortización**       | Datos primitivos (`double`) | Uso de `BigDecimal` para precisión financiera, JTable y exportación de reportes a PDF y CSV.                     |
| **Centro de Llamadas** | Condicionales anidados      | Uso de `Enums` para gestión de tarifas y cálculo de totales en tiempo real.                                      |
| **Agenda CRUD**        | Almacenamiento volátil      | Búsqueda y filtrado en tiempo real (`Live Search`) y validación de integridad de datos (no duplicados).          |
| **Laboratorio Lógico** | Salida por consola          | Interfaces visuales para la demostración interactiva de operadores booleanos y comparativos.                     |
| **Calendario**         | Switch clásico              | Implementación de `Enhanced Switch` (Java 14+) para simplificación de código.                                    |
| **Bucle While**        | Interfaz bloqueante         | Implementación de interfaz no bloqueante con historial de eventos para simular ciclos de ejecución.              |

## Características Técnicas Destacadas

### Manejo de Datos
* **BigDecimal:** Utilizado para todas las operaciones monetarias para evitar errores de precisión de punto flotante.
* **Java Streams API:** Utilizado para operaciones de búsqueda, filtrado y cálculo sobre colecciones de manera declarativa.
* **Listas Dinámicas:** Reemplazo de vectores estáticos por `List` y `ArrayList` para permitir escalabilidad.

### Interfaz de Usuario (UX)
* Eliminación de ventanas emergentes bloqueantes (`JOptionPane`) en flujos principales.
* Implementación de retroalimentación visual mediante códigos de colores semánticos (Verde/Rojo) para estados de éxito o error.

## Instrucciones de Ejecución

Este proyecto utiliza Maven para la gestión de dependencias.

1.  Clonar el repositorio o descargar el código fuente.
2.  Abrir el proyecto en IntelliJ IDEA.
3.  Permitir que Maven descargue las dependencias (específicamente `OpenPDF`).
4.  Navegar al paquete `org.example` dentro de `src/main/java`.
5.  Ejecutar la clase `Main` correspondiente al módulo que se desea probar.