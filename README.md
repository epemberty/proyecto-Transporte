# 🚚 Sistema de Gestión de Transporte

Sistema web integral para el control de transporte de carga, gestión de tiquetes de viaje. Diseñado con una arquitectura desacoplada, utilizando el patrón de diseño **Facade** en el Backend y desplegado mediante contenedores con **Docker**.

---

## 🚀 Características Principales

* **Gestión de Tiquetes y Rutas:** Control centralizado de venta de pasajes, planillas de viaje y asignación de vehículos.
* **Despliegue Multi-contenedor:** Entorno completamente dockerizado para ejecutar Frontend, Backend y Base de Datos con un solo comando.

---

## 🛠️ Tecnologías Utilizadas

* **Frontend:** Angular, TypeScript, Bootstrap / HTML5 / CSS3
* **Backend:** Java, Spring Boot (Spring Data JPA, REST APIs)
* **Patrones de Diseño:** Facade Pattern, DTOs, Repository Pattern
* **DevOps & Despliegue:** Docker, Docker Compose
* **Control de Versiones:** Git & GitHub

---

## 📁 Estructura del Proyecto

```text
proyecto-Transporte/
├── frontend/           # Aplicación cliente desarrollada en Angular
├── backend/            # API REST, servicios de dominio 
├── docker-compose.yml  # Configuración para la orquestación de contenedores
└── README.md           # Documentación del proyecto
