package xlstocsv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; // Para .xls
import org.apache.poi.ss.usermodel.*;

public class XlsToCsv {

    public static void main(String[] args) {
        // Archivos de entrada y salida
        String inputFile = "datos/embarcacionesPrecios_es.xls";
        String outputFile = "resultados/resul_mar.csv";

        try (FileInputStream fis = new FileInputStream(new File(inputFile));
             FileWriter csvWriter = new FileWriter(outputFile)) {

            // Leer el archivo .xls
            Workbook workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Primera hoja

            int rowNumber = 0; // contador de filas

            // Iterar sobre filas
            for (Row row : sheet) {
                rowNumber++;
                if (rowNumber <= 3) continue; // saltar las primeras 3 filas

                // Variable para controlar el primer valor de la fila
                boolean firstCell = true;
                // Iterar sobre celdas
                for (Cell cell : row) {
                    if (!firstCell) csvWriter.append(";");
                    firstCell = false;

                    switch (cell.getCellType()) {
                        case STRING:
                            csvWriter.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            csvWriter.append(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            csvWriter.append(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            csvWriter.append("");
                    }
                }
                // Nueva línea al final de la fila
                csvWriter.append("\n");
            }

            workbook.close();
            System.out.println("Archivo CSV generado: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
