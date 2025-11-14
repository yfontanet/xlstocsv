# 🗂 xlstocsv

**Descripción:**  
Herramienta en **Java** para la Actividad 1 de la asignatura **OPT1Q - Bases de Datos Avanzadas** de la Universidad Internacional de la Rioja.  
Convierte archivos Excel (`.xls`) en CSV, ignorando las **3 primeras filas** de cada hoja.

---

## 📁 Estructura del proyecto

```text
xlstocsv/
├── pom.xml                       # Archivo central de Maven
├── datos/                         # Carpeta con archivos Excel de entrada
│   ├── preciosEESS_es.xls
│   └── embarcacionesPrecios_es.xls
├── resultados/                    # Carpeta donde se guardan los CSV generados
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── xlstocsv/
│   │   │       └── XlsToCsv.java  # Clase principal
│   │   └── resources/             # Recursos adicionales (actualmente vacío)
│   └── test/                      # Código de test (opcional)
└── target/                        # Carpeta generada por Maven con archivos compilados
    ├── classes/
    │   └── xlstocsv/
    │       ├── XlsToCsv.class
    │       └── XlsToCsv$1.class
    ├── generated-sources/
    │   └── annotations/
    └── maven-status/
        └── maven-compiler-plugin/
            └── compile/
                └── default-compile/
                    ├── createdFiles.lst
                    └── inputFiles.lst
```

Esta estructura sigue la convención oficial de Maven, asegurando que el proyecto sea claro, mantenible y fácil de ejecutar por otros.

---

## ⚙️ Dependencias

El proyecto utiliza **Apache POI** para trabajar con archivos Excel (`.xls`).  
Maven se encarga de descargar y gestionar las dependencias automáticamente.

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.2.3</version>
    </dependency>
</dependencies>
```

---

## 📝 Funcionamiento de `XlsToCsv.java`

1. Lee un archivo Excel (`.xls`) de la carpeta `datos`.
2. Ignora las **3 primeras filas** de cada hoja.
3. Convierte el resto de filas en CSV, usando `;` como separador.
4. Guarda el archivo CSV en la carpeta `resultados`.

**Fragmento clave del código:**

```java
int rowNumber = 0;
for (Row row : sheet) {
    rowNumber++;
    if (rowNumber <= 3) continue; // Ignora las 3 primeras filas

    boolean firstCell = true;
    for (Cell cell : row) {
        if (!firstCell) csvWriter.append(";");
        firstCell = false;

        switch (cell.getCellType()) {
            case STRING -> csvWriter.append(cell.getStringCellValue());
            case NUMERIC -> csvWriter.append(String.valueOf(cell.getNumericCellValue()));
            case BOOLEAN -> csvWriter.append(String.valueOf(cell.getBooleanCellValue()));
            default -> csvWriter.append("");
        }
    }
    csvWriter.append("\n");
}
```

---

## 🚀 Cómo ejecutar el programa

> **Importante:** Debes estar en la carpeta donde se encuentra `pom.xml`.

1. **Compilar el proyecto:**

```bash
mvn clean compile
```

2. **Ejecutar la clase principal:**

```bash
mvn exec:java -f "/ruta/completa/al/proyecto/xlstocsv/pom.xml"
```

---

## ⚠️ Notas importantes

- El CSV generado usa `;` como separador de columnas.  
- Ignora las 3 primeras filas de cada hoja Excel.  
- Los warnings de `sun.misc.Unsafe` en Java 25 **no afectan la ejecución**.  
