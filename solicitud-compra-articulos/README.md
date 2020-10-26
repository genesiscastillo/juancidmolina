# Desarrollo microservicios con PubSub de GCP

## Testing sobre el entorno local

copiar el archivo que viene adjunto
y copiar en la ruta `[PATH_GCP_CREDENTIALS]/Sing-In-Dev-06ac867e8d5c.json`

setear la variable de entorno `set GOOGLE_APPLICATION_CREDENTIALS=[PATH_GCP_CREDENTIALS]/Sing-In-Dev-06ac867e8d5c.json`

descargar el repositorio desde github `git clone https://github.com/genesiscastillo/juancidmolina.git`

entrar a la carpeta de trabajo `cd solicitud-compra-articulos`

ejecute el test con maven `mvn clean test`

## Ejecutar con SpringBoot 

correr el servidor con `mvn clean spring-boot:run`

## Pruebas de servicios con Postman
importar el archivo collections de Postman `JuanCid-Fb.postman_collection.json`

Seguir los siguientes pasos:

#### ActualizarStock
POST: http://localhost:8080/actualizarStock
`{
    "articulo":{
        "id":9,
        "stock":10
    }
}`

#### SolicitudCompra
POST: http://localhost:8080/solicitudCompra 
`{
    "id_articulo" : 9,
    "cantidad" : 9,
    "precio_unitario" : 7500,
    "fecha" : "26-10-2020"
}`

#### Obtener solicitudes
GET: http://localhost:8080/solicitudes


## Ejecutar con Docker

descargar la imagen docker con el siguiente commando `docker pull genesiscastillo/solicitud-compra-articulos`

correr la imagen con docker usar el siguiente commando `docker run -d -p 8080:8080 -t genesicastillo/solicitud-compra-articulos`

seguir los pasos anteriores de la seccion "Pruebas de servicios con Postman"

## PubSub GCP - crearemos un topic y susbscription

gcloud pubsub topics create TOPIC_ID
`por ejemplo: > gcloud pubsub topics create testTopic`

gcloud pubsub subscriptions create SUBSCRIPTION_ID -topic=TOPIC_ID 
`por ejemplo: > gcloud pubsub subscriptions testSubscription -topic testTopic`

generar una cuenta credential de pubsub y descargue el json.

## Patrones de Diseño para el microservicios
Aplicamos el `Patron de construccion CORE`. 

Estos patrones nos ayudan a:
* Establecer el adecuado tamaño y responsabilidad de cada uno de los servicios.
* Definir los protocolos de comunicación de los servicios
* Establecer los mecanismos para gestionar la configuración de los servicios.

