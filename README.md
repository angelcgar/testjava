## Como levantar el proyecto

1. Clonar repositorio

2. Levantar una imagen de docker engine de mysql.8.0.36

```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=123456 -d -p 3306:3306 mysql:8.0.36
```

3. Acceder a el shell de mysql y crear una base de datos, usamos zona_fit

```bash
docker exec -it mysql-container mysql -u root -p
```

```bash
CREATE DATABASE zona_fit; 
```


