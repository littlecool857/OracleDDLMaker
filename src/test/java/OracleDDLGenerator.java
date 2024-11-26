import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OracleDDLGenerator {

    public static void main(String[] args) throws IOException {
        String excelFilePath = "src/file/source/test.xlsx";
        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // 假设数据在第一个sheet里

        StringBuilder ddlBuilder = new StringBuilder();
        String currentTableName = "";
        String currentSchema = "";

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // 跳过表头

            String schemaName = row.getCell(0).getStringCellValue();
            String tableName = row.getCell(1).getStringCellValue();
            String columnName = row.getCell(2).getStringCellValue();
            String columnType = row.getCell(3).getStringCellValue();
            String columnComment = row.getCell(4).getStringCellValue();
            String tableComment = row.getCell(5).getStringCellValue();

            // TODO 如果遇到新的表，开始生成DDL
            if (!tableName.equals(currentTableName)) {
                if (!currentTableName.isEmpty()) {
                    ddlBuilder.append(";\n\n");
                }
                ddlBuilder.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" (\n");
                currentTableName = tableName;
                currentSchema = schemaName;
            }

            // TODO 为当前表生成字段的DDL
            ddlBuilder.append("    ").append(columnName).append(" ").append(columnType);

            if (columnComment != null && !columnComment.isEmpty()) {
                ddlBuilder.append(" COMMENT '").append(columnComment).append("'");
            }

            ddlBuilder.append(",\n");
        }

        // TODO 添加表注释
        if (currentTableName != null && !currentTableName.isEmpty()) {
            ddlBuilder.append(");\n");
            ddlBuilder.append("COMMENT ON TABLE ").append(currentSchema).append(".").append(currentTableName)
                    .append(" IS '").append(sheet.getRow(0).getCell(5).getStringCellValue()).append("';");
        }

        System.out.println(ddlBuilder.toString());
        workbook.close();
        fis.close();
    }
}
