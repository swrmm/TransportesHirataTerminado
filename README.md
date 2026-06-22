# Transportes Hirata Terminado

Aplicacion de escritorio Java Swing + JDBC + MySQL para la gestion operativa de Transportes Hirata.

El proyecto integra:

- Gestion de flota, camiones y conductores.
- Registro de kilometraje y alerta preventiva cada 5000 km.
- Historial de mantenimiento vehicular.
- Gestion de equipos de oficina.
- Mantenimiento fisico de equipos.
- Actualizaciones de software.
- Inventario de piezas y repuestos.
- Gestion de usuarios y roles.
- Etapa 3: monitoreo conceptual de sensores IoT GPS y temperatura de carga.
- Reportes de rutas, rendimiento, temperatura y exportacion TXT.

## Tecnologias

- Java Swing
- Maven
- JDBC
- MySQL 8
- mysql-connector-j 8.0.33
- JDK 17

## Base de datos actualizada

Base requerida:

```sql
Transportes_Hirata_v2
```

Credenciales locales configuradas en `Conexion.java`:

```text
Usuario: root
Clave: admin123
Puerto: 3306
```

Archivo principal para instalar la base:

```text
Script hirata.txt
```

Migracion especifica de Etapa 3:

```text
src/main/resources/db/migration/V3__sensores_iot.sql
```

## Instalacion de la base de datos

Desde la carpeta del proyecto Java:

```powershell
cd "C:\Users\Fabiant\Desktop\Etapa 3 Integracion de Competencias\Hirata presentacion\transporterhirata-main"
```

Ejecutar el script completo:

```powershell
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -padmin123 --default-character-set=utf8mb4 < ".\Script hirata.txt"
```

Verificar tablas principales:

```powershell
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -padmin123 --database=Transportes_Hirata_v2 -e "SHOW TABLES;"
```

Verificar datos base:

```powershell
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -padmin123 --database=Transportes_Hirata_v2 -e "SELECT COUNT(*) usuarios FROM Usuario; SELECT COUNT(*) camiones FROM Camion; SELECT COUNT(*) gps FROM SensorGpsLectura; SELECT COUNT(*) temperaturas FROM SensorTemperaturaLectura;"
```

## Tablas principales

### Etapa 1 - Flota

- `Conductor`
- `Camion`
- `Mantenimiento`

### Etapa 2 - Equipos de oficina

- `EquipoOficina`
- `MantenimientoEquipo`
- `ActualizacionSoftware`
- `PiezaRepuesto`
- `MantenimientoPieza`

### Seguridad

- `Usuario`

### Etapa 3 - Sensores IoT

- `SensorGpsLectura`
- `SensorTemperaturaLectura`

## Usuarios de prueba

| RUT | Clave | Cargo |
|---|---|---|
| `9.999.999-9` | `12345678` | `administrador` |
| `11.111.111-1` | `1234` | `Administrador General` |
| `22.222.222-2` | `1234` | `Administrador IT` |
| `33.333.333-3` | `1234` | `Tecnico de soporte` |
| `44.444.444-4` | `1234` | `Administrador de mantenimiento` |
| `55.555.555-5` | `1234` | `Administrador de inventario` |
| `66.666.666-6` | `1234` | `Conductor` |

## Permisos por rol

### Administrador General / administrador

- Acceso a flota y conductores.
- Acceso a mantenimiento de camiones.
- Acceso a equipos de oficina.
- Acceso a inventario.
- Acceso a actualizaciones de software.
- Acceso a sensores IoT.
- Acceso a gestion de usuarios y roles.

### Administrador IT

- Acceso a equipos de oficina.
- Acceso a actualizaciones de software.
- Acceso a historial de equipos.
- Acceso a sensores IoT.

### Tecnico de soporte

- Acceso a mantenimiento de equipos.
- Acceso a actualizaciones de software.

### Administrador de mantenimiento

- Acceso a mantenimiento de camiones.
- Acceso a equipos de oficina.
- Acceso a sensores IoT.

### Administrador de inventario

- Acceso a inventario de piezas.

### Conductor

- Acceso al modulo de kilometraje.

## Requerimientos funcionales Etapa 3

| RF | Estado | Donde se resuelve |
|---|---|---|
| RF-10 Monitoreo GPS | Completado | `GuiMonitoreoSensores`, `SensorGps`, `SensorDao`, `Principal` |
| RF-11 Almacenamiento sensores | Completado | `SensorDao`, `SensorGpsLectura`, `SensorTemperaturaLectura`, `V3__sensores_iot.sql` |
| RF-12 Reportes sensores | Completado | `ReporteSensor`, `SensorDao.generarReporteSensores()`, exportacion TXT |
| RF-13 Temperatura carga | Completado | `SensorTemperatura`, alerta critica en `GuiMonitoreoSensores`, tabla `SensorTemperaturaLectura` |

## Compilacion

El proyecto esta configurado para Java 17:

```xml
<maven.compiler.release>17</maven.compiler.release>
```

Compilar con Maven:

```powershell
mvn clean package
```

Si Maven no esta instalado, se puede validar con `javac` usando el conector MySQL local:

```powershell
$src = Get-ChildItem -Recurse -Filter *.java "src\main\java" | ForEach-Object { $_.FullName }
$cp = "$env:USERPROFILE\.m2\repository\com\mysql\mysql-connector-j\8.0.33\mysql-connector-j-8.0.33.jar"
javac --release 17 -encoding UTF-8 -cp $cp -d "target\manual-test-classes" $src
```

## Ejecucion

Clase principal:

```text
com.mycompany.transporteshirata.GUI.GuiLoginGeneral
```

Con Maven:

```powershell
mvn exec:java
```

Desde un IDE:

1. Abrir el proyecto Maven.
2. Confirmar que MySQL este iniciado.
3. Ejecutar `GuiLoginGeneral`.
4. Ingresar con uno de los usuarios de prueba.

## Verificacion realizada

- MySQL `MySQL80` iniciado en puerto `3306`.
- Base `Transportes_Hirata_v2` instalada correctamente.
- Compilacion Java con JDK 17: OK.
- Login real con `UsuarioDao`: OK.
- Registro de lectura GPS con `SensorDao`: OK.
- Registro de temperatura critica: OK.
- Generacion de reportes RF-12/RF-13: OK.
- Pantalla `GuiMonitoreoSensores`: OK.

## Documentacion generada

La carpeta `documentacion_final` contiene:

- `ANALISIS_INTEGRAL_ETAPA_3.md`
- `MATRIZ_TRAZABILIDAD.md`
- `TESTING_CHECKLIST.md`
- `Informe_Completo_Etapa_3_RF_Transportes_Hirata.docx`

La carpeta `Diagramas Etapa 3` contiene los diagramas de arquitectura, clases, flujo, modelo de datos, componentes y secuencia de reportes.
