# EvaluacionJonnathanValdez3
Consumo de un microservicio

1. Clonar el proyecto: Clone el repositorio desde GitHub utilizando Git o descargando el código fuente como archivo ZIP:
   ```git clone https://github.com/Jonathan2703/EvaluacionJonnathanValdez3.git```
3. Crear una base de datos en postgresql con el nombre de ```posts```
4. Dirigirse al archivo ```application.properties``` y modificar los datos de conexión a la base de datos
5. Dirigirse al archivo ```application.properties``` y modificar los datos de relacionados con el JWT token como el secret key
6. Ejecutar el proyecto en su IDE de preferencia (como IntelliJ IDEA, Eclipse, etc.)
7. Una vez que el proyecto esta corriendo puede ingresar postman y exportar el archivo llamado ```RetoBanco.postman_collection.json```
8. Ahi encontrara 3 endpoints el primero de ellos y es el llamdo Register: el mismo que sirve para registrar usuarios, el segundo endPoint sirve para logearse con los usuarios registrados y el ultimo endpoint sirve para consultar los posts por id y/o title
9. Para comprobar el funcionamiento primero se debe crear un usuario en el endpoint Register donde el body del JSON debe tener la siguiente estructura:
```json
{
    "username": "iva@gmail.com",
    "password": "123456",
    "lastName": "valdez",
    "firstName": "jona",
    "country": "Ecuador"
}
```
Eso nos devuelve un response en donde vamos a obtener el JWT, el mismo que tiene que ser utilizado en el header(Bearer Token) de las peticiones que se realicen a los endpoints que estan protegidos con el JWT token. Y ya se puede utilizar el resto de endpoints de la API.
10. Una vez que ya tenemos un JWT podemos realizar la consulta al endpoint Search que esta protegido, añadir y verificar que el apartado de Authorization de postam este seleccionado Bearer Token, y en el apartado de Params podemos agregar el ID a buscar, el Title a bucar o ambos.

# Requisitos
* Java 17
* Postgresql 15

# Stack Tecnológico Usado
* Spring Boot 3.1.5
* Spring Security
* Spring Data JPA
* Spring Web
* Spring Validation
* JWT Token
* Postgresql
* Lombok

# Validaciones
Se realizaron algunas validaciones que a continuación se mostraran.
Evaluacion 1:
Utilizando el endpoint para buscar por id y/o title, en el caso de que el ID no sea un numero:

![Captura de pantalla 2023-11-01 112919](https://github.com/Jonathan2703/EvaluacionJonnathanValdez3/assets/52688416/4175c885-0b3a-457f-9f6b-5eed07fc234a)

En el caso de que el ID o el title sean nulos o vacios:

![Captura de pantalla 2023-11-01 113205](https://github.com/Jonathan2703/EvaluacionJonnathanValdez3/assets/52688416/e902b9ae-b050-46f2-b5f9-1a28184b6303)
