# Transportes Hirata

Programa de escritorio desarrollado en Java Swing con conexion a MySQL para la gestion de flota, mantenimiento, equipos de oficina, usuarios y sensores IoT.

## Ejecutar base de datos

Crear e instalar la base de datos ejecutando el script:

```powershell
mysql -u root -p --default-character-set=utf8mb4 < "Script hirata.txt"
```

Base de datos:

```text
Transportes_Hirata_v2
```

## Ejecutar programa

Clase principal:

```text
com.mycompany.transporteshirata.GUI.GuiLoginGeneral
```

Con Maven:

```powershell
mvn exec:java
```

## Credenciales por defecto

Base de datos:

```text
Usuario: root
Clave: admin123
```

Usuario del sistema:

```text
RUT: 11.111.111-1
Clave: 1234
Rol: Administrador General
```
