# Rappi Movie
Proyecto realizado como prueba de conocimientos en android consumiendo una API.

## Requerimientos

Android Studio 3.2.1
Gradle 3.2.1
Versión mínima de Android 24 API

## Librerías utilizadas
Las librerías más relevantes utilizadas son:

| librería | Descripción |
|----------|-------------|
| Dagger | Utilizado para la inyección de dependencias |
| Retrofit | Para el consumo de la API |
| MapStruct | Para mapear los POJO, entre entidades, DTO y modelos |
| lombok | Generación automática de getter, setters y constructores |
| ButterKnife | Inyección de vistas |
| DBFlow | ORM para la base de datos |
| Glide | Carga eficiente de imágenes |

## Arquitectura
La arquitectura implementada para está aplicación se basa en Clean Architecture del Uncle Bob.Es una arquitectura en "cebolla" por la forma de la imagen que se forma de las diferentes capas. 

![Arquitectura Limpia](https://erikcaffrey.github.io/content/images/2016/1/clean_archi.png)

Basándome en eso se divide la aplicación en 4 módulos:

### 1. App
Este módulo es donde estan todas las dependencias de Android, en este se concentra la mayor parte de la configuración de inyección de dependecias (Dagger), y tambien en esta capa o módulo se implementa la V y P de patrón MVP.

| módulo | Descripción |
|----------|-------------|
| app |  |
| domain ||
| data | |
| common ||
