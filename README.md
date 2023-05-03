# TPIA
Trabajo práctico de la materia Inteligencia Artificial UTN FRSF 2023

# Etapa 2

## Resumen
  Todas las clases definidas extienden de sus respectivas clases del framework FAIA, como se observo en los ejemplos de la catedra. De esta forma PokeEnvironment extiende de Environment y asi sucesivamente con todas las clases. Los directorios agrupan las diferentes clases referentes a Actions, Agent, Environment y demas directorios auxiliares.
## Ambiente
Definimos las siguientes clases para representar los estados del ambiente y las percepciones que seran enviadas al agente.

![image](https://user-images.githubusercontent.com/44452084/235812986-c4ae2539-a96c-48a3-ae07-1711a6735b87.png)

## Agente
Definimos las siguientes clases para describir el agente. Que reflejan estados, objetivo y clases auxiliares.

![image](https://user-images.githubusercontent.com/44452084/235812592-47ab6cf8-dcce-4569-9ccb-2aedf7bca1fe.png)

### Nota
El objetivo planteado en esta etapa es trivial, llegar a "BuenosAires" desde "TierraDelFuego" mediante una unica acción. Definimos esto solo para ver correr la simulación con todo funcionando. 

## Acciones
Incluimos 2 acciones basicas, para poder correr la simulacion simplemente, son las siguientes.

![image](https://user-images.githubusercontent.com/44452084/235812480-c6519153-8238-412a-97fd-ee58417e3228.png)

## Enemigos
Contamos con la clase siguiente, donde encapsulamos los atributos y comportamientos de los pokemones enemigos.

![image](https://user-images.githubusercontent.com/44452084/235812655-b0e7d65b-f6d8-46c9-83fb-d8316eea982b.png)

## MAIN
En la clase Main.java realizamos la instanciación de el agente, ambiente y simulador, para finalmente iniciar la simulacion.

![image](https://user-images.githubusercontent.com/44452084/235812899-b20de14e-de21-4d9d-a77a-9f1f3367b7cf.png)


## File y FileReaders
Incluimos la funcionalidad de poder cargar el estado inicial del programa mediante archivos de texto.
Se pueden observar en los siguientes directorios.

![image](https://user-images.githubusercontent.com/44452084/235812309-1994c4b9-5fae-4e54-af90-adea4e5f2eda.png)
