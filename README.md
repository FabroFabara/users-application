# users-application

Descripcion
El siguiente proyecto esta desarrollado para poder gestionar usuarios para ello se creo un CRUD

Para solucionar este problema se ha planteado el uso de las siguientes tecnologias

Para solucionar este problema se ha planteado el uso de las siguientes tecnologias
- Java 17
- SpringBoot version 3.3.1
- H2 como base de datos
- Liquibase para gestionar la base de datos
- Docker para contenerizar la aplicaicon
- JUnit 5 para los test unitarios
- OpenApi 3.0 para la documentacion de los APIs

NOTA: Con el uso de liquibase no es necesario generar scripts para la creacion de base de datos ni de tablas ya que este gestiona los cambios de base de datos

## Pasos Ejecucion

- Clonar el repositorio https://github.com/FabroFabara/users-application.git
- Una vez clonado el repositorio el proyecto se puede ejecutar de las siguientes maneras:
  
## Via Docker

- Para ejecutar via Docker debemos contar con la instalcion de docker desktop para poder ejecutar el siguiente comando
- Una vez verificado esto en nuestro equipo no debos colocar dentro de la carpeta clonada users-application
- Abrimos un terminal o un cmd ubicados dentro de esta carpeta
- Luego ejecutamos el siguiente comando

```
docker-compose up --build 
```

![image](https://github.com/user-attachments/assets/071b02cd-8ad5-423b-ae34-56592b337f6b)

![image](https://github.com/user-attachments/assets/c2bdb6d9-ee7a-4a8c-add9-71c9e2fb65c5)

## Validacion


## Via Swagger

- Para la validacion se puede acceder a un navegador y colocar la siguiente url

```
http://localhost:8080/swagger-ui/index.html
```

![image](https://github.com/user-attachments/assets/8addc327-2b9e-4a23-aa5e-18ca659498ba)

## Test de la aplicacion

- Creacion de Usuarios

```
{
  "name": "John Doe",
  "email": "aaaa@dominio.cl",
  "password": "Password123",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "56"
    }
  ]
}
```
![image](https://github.com/user-attachments/assets/23d99835-835b-4f5c-a894-c72e630f7a58)

- Validacion si el correo ya existe para esta prueba se ejecuta el mismo body

![image](https://github.com/user-attachments/assets/7d330cc9-1555-4dbf-8402-a6890164d2b1)

- Validacion si el correo no cumple con el patron se manda el siguiente body 


```
{
  "name": "John Doe",
  "email": "aaaadominio.cl",
  "password": "Password123",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "56"
    }
  ]
}
```
![image](https://github.com/user-attachments/assets/ceeb63b6-038e-4334-91c1-5d13a65e9601)

- Get para recuperar todos los usuarios

  ![image](https://github.com/user-attachments/assets/f23ddc17-815d-4328-af12-b6444c8acb87)

- Get usuarios por id

![image](https://github.com/user-attachments/assets/ae5e5abd-c657-49ce-b81e-ea28425631f0)

- Update de usuario se recibe el id y el cuerpo a cambiar

id: 98345837-16ec-44a6-9ee3-993751831647

```
{
  "name": "Fabricio Fabara",
  "email": "ffabara@dominio.cl",
  "password": "Password123",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "56"
    }
  ]
}
```

![image](https://github.com/user-attachments/assets/18d2987e-d41d-4b0f-b67a-b78b71c19c26)

![image](https://github.com/user-attachments/assets/846bcf85-4fc7-4dd1-85b0-b6cf58ae7ff5)

- Con el get by id podemos ver que la fecha de modificacion a cambiado

id: 98345837-16ec-44a6-9ee3-993751831647


![image](https://github.com/user-attachments/assets/05ff86d1-9895-4438-a0aa-2424db2ca4f1)

- Para la eliminacion se considero un borrado logico que ponga como false el campo isActive para este caso solo pasamos el id

id: 98345837-16ec-44a6-9ee3-993751831647

![image](https://github.com/user-attachments/assets/8451f95b-5bb2-49fb-b198-1994ad7df25e)

- El campo ahora esta false 

![image](https://github.com/user-attachments/assets/4ef5c1bc-0839-451c-aec0-f22f92f6a2b0)

