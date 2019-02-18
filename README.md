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

En el paquete __**view**__ se encuentran las vistas, es decir las actividades, los fragmentos y el contrato (interface) de cada una de las vistas. Estas clases son lo más "bruto" posibles, no tiene nunguna lógica y solo responden a los eventos que les indica el presenter.

| Vista | Descripción |
|----------|-------------|
| MovieListFragment | Fragmento donde se cargan las listas de las películas  |
| MovieDetaiActivity | Actividad que mustra el detalle de una pelicula |
| SearchResultActivity | Actividad en la que se muestran los resultados de una búsqueda |
| MainActivity | Actividad principal que carga los demás fragmentos |

En el paquete __**presenter**__ se encuentran los presenters asociados a cada una de las vistas y sus contratos. Los presenter son los encargados de recibir los eventos que viene de las vistas, procesarlos e indicarle paso por paso lo que la vista debe hacer. Y las solicitudes se escalan a la capa de dominio.

En el paquete __**di**__ esta toda la configuración del dagger di.

### 2. domain
En este módulo se definen los casos de uso y el contrato para el repositorio de la capa de datos.
Este módulo juega un papel muy importante por que desde este módulo, todas las accionés son asyncronas, evitando que se bloquee el hilo principal de ejecución.
Los casos de usos implementados son:

| Caso de uso | Descripción |
|----------|-------------|
| GetPopular | inicia la solicitud de las películas polulares |
| GetTopRated | inicia la solicitud de las películas con mejor ranking |
| GetUpcoming | inicia la solicitud de las películas  a salir |
| GetMovieById | inicia la solicitud de la información de 1 película |
| SearchMovie  | inicia la búsqueda de un película |

### 3. data
Esta módulo es el encargado de todo lo que tiene que ver con los datos, en este caso, con la comunicación con el servicio API de las películas y con la base de datos.
Para eso se implementa un patron repositorio, con un DataSource y un factory. El repositorio es la entrada de esta capa, los data source definen si la solicitu es local o remota y con el factory, definimos el cache de los datos, y de donde se vana traer basados en conectividad y en tiempo se solicitud.
En la base de datos, se gusrda la información de las películas y las versiones de las solicitudes.

![data](https://github.com/lautaro2385/rappimovie/blob/master/iagram.png)

### 4. Common
Y por último esta el módulo common, aqui es donde se ponen las clases u utilidades que comparten todos los módulos, y por tanto aqui es donde se definen los modelos de la app, que son comunes en las capas.

## Preguntas
1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
**R/**  hace parte del principio SOLID y es que una clase se encarge solo de una tarea, para que los cambios y detección de errores se haga de manera más sencilla. Si una clase tiene más de una responsabilidad, es posible que debido a sus multiples responsabilidades haya que modificarla amenudo y por diferentes personas, aumentando el riego de una colisión, de un error y haciendo la tarea de encontrar la causa de un error, más dificil.

2. Qué características tiene, según su opinión, un “buen” código o código limpio?
**R/** Los programadores siempre debemos hacer "buen" código y código limpio. Un código que sea fácil de entender, que sea legible, si otro programador toma nuestro codigo que sea fácil seguir lo que se se hizo, que se pueda escalár fácilmente, que tenga y se puedan hacer pruebas desacopladas y a cada módulo del código, que las clases tengan reponsabilidades únicas, que en la medida de lo posible se utilien las abtracciones y de desacople el código.
Hacer buen código para los programadores no debe ser una opción, es nuestro deber hacer código bonito.
