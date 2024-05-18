# DBMS

# Guía de Uso

Este documento proporciona una guía paso a paso para ejecutar los componentes del proyecto. A continuación, se describen las instrucciones necesarias para poner en marcha el servidor de base de datos, compilar y ejecutar el robot Karel, y realizar solicitudes individuales.

## Pasos para ejecutar el proyecto

### 1. Ejecutar el Servidor de Base de Datos (dbServer)

Primero, debes correr el servidor dbServer, el cual es responsable de escuchar y recibir peticiones a través de un socket.

1. Navega a la carpeta `src/` el que esta en la raiz del proyecto.
2. Ejecuta el archivo `main.java` para iniciar el servidor.

### 2. Ejecutar minero de Karel

Después de iniciar el servidor de base de datos, debes compilar y ejecutar el robot Karel.

1. Navega al directorio raíz del proyecto de karel y ubicate en la carpera `trabajos/`.
2. Ejecuta el archivo `compilar.bat` para compilar el código del robot Karel.
3. Una vez compilado, ejecuta el archivo `run.bat` para correr el robot Karel.

### 3. Realizar Solicitudes Individuales

En caso de que necesites hacer solicitudes individuales al servidor, puedes hacerlo mediante el archivo `main.java` en la ruta `src/example/main.java` de la raiz del proyecto.

1. Navega a la carpeta `src/example`.
2. ingresa al archivo `main.java`
3. Ejecuta `main.java` para enviar las solicitudes al servidor.

