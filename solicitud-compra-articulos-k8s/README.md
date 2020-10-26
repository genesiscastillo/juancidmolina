
## Ejecutar con Minikube desde imagen docker

ejecute el siguiente comando:
`kubectl run --generator=run-pod/v1 pod-solicitud-compra-articulos --image=genesiscastillo/solicitud-compra-articulos`

ejecute el siguiente comando:
`kubectl port-forward pod-solicitud-compra-articulos 8080:8080`


## Ejecutar con Minikube desde yaml

entrar a la carpeta `solicitud-compra-artciulos-k8s/src`

ejecute el siguiente comando: `kubectl apply -f solicitud-compra-articulos-deploy.yaml`

luego aplique el siguiente comando: `kubectl get pod`

y luego desplegue el prot-forward con el siguiente comando: [reemplaze el nombre del pod]
`kubectl port-forward solicitud-compra-articulos-deployment-5b896779b9-5htj7 8080:8080`


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
