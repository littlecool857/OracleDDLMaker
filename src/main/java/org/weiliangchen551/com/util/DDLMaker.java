package org.weiliangchen551.com.util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class DDLMaker {

    public static String ddlMakerOneFile(Sheet sheet){
        String beforeTableName = "";
        StringBuilder ddlBuilder = new StringBuilder();
        int LastRow = sheet.getLastRowNum();
        // 注释语句存放器
        StringBuilder commentBuilder = new StringBuilder();
        for (Row row : sheet) {
            int LineRow = row.getRowNum();
            if (LineRow == 0) continue; // 跳过表头



            String schemaName = row.getCell(0).getStringCellValue();
            String tableName = row.getCell(1).getStringCellValue();
            String columnName = row.getCell(2).getStringCellValue();
            String columnType = row.getCell(3).getStringCellValue();
            String columnComment = row.getCell(4).getStringCellValue();
            String tableComment = row.getCell(5).getStringCellValue();
            int prKey = 1;
            if (row.getCell(6) == null || row.getCell(6).getCellType() == CellType.BLANK) {
                prKey = 0;
            }


            // TODO 建表语句初始化
            if(beforeTableName.equals("")){
                ddlBuilder.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" (\n");
                if(prKey == 1){
                ddlBuilder.append("    ").append(columnName).append(" ").append(columnType).append(" ")
                        .append(",constraint pk_").append(columnName).append("_01 primary key(").append(columnName).append(")");
                }else{
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType);
                }
                commentBuilder.append("comment on table ").append(schemaName).append(".").append(tableName)
                        .append(" is '").append(tableComment).append("';\n");

                commentBuilder.append("comment on column ").append(schemaName).append(".").append(tableName)
                        .append(".").append(columnName).append(" is '").append(columnComment).append("';\n");

                if (LineRow <= LastRow) beforeTableName = tableName;
                continue;
            }

            // TODO 表名变化时 完成上一张表的建表语句  开始下一张表的建表语句
            if (!tableName.equals(beforeTableName) && !beforeTableName.equals("")){
                ddlBuilder.append("\n");
                ddlBuilder.append(");");
                ddlBuilder.append("\n");
                ddlBuilder.append(commentBuilder.toString());
                commentBuilder.setLength(0);
                ddlBuilder.append("\n");
                ddlBuilder.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" (\n");
                if(prKey == 1){
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType).append(" ")
                            .append(",constraint pk_").append(columnName).append("_01 primary key(").append(columnName).append(")");
                }else{
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType);
                }
                commentBuilder.append("comment on table ").append(schemaName).append(".").append(tableName)
                                .append(" is '").append(tableComment).append("';\n");
                commentBuilder.append("comment on column ").append(schemaName).append(".").append(tableName)
                        .append(".").append(columnName).append(" is '").append(columnComment).append("';\n");


            }else // TODO 添加字段信息
            {
                ddlBuilder.append(",\n");
                if(prKey == 1){
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType).append(" ")
                            .append(",constraint pk_").append(columnName).append("_01 primary key(").append(columnName).append(")");
                }else{
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType);
                }
                commentBuilder.append("comment on column ").append(schemaName).append(".").append(tableName)
                        .append(".").append(columnName).append(" is '").append(columnComment).append("';\n");

            }
            // TODO 当是最后一张表时 补全最后一张表的建表语句
            if(LineRow == LastRow){

                ddlBuilder.append("\n");
                ddlBuilder.append(");");
                ddlBuilder.append("\n");
                ddlBuilder.append(commentBuilder.toString());
                commentBuilder.setLength(0);
            }

            // TODO 表名继承
            if (LineRow <= LastRow) beforeTableName = tableName;




        }
        return ddlBuilder.toString();
    }

    public static void ddlMakerManyFile(Sheet sheet,String savePath){
        String beforeTableName = "";
        StringBuilder ddlBuilder = new StringBuilder();
        int LastRow = sheet.getLastRowNum();
        // 注释语句存放器
        StringBuilder commentBuilder = new StringBuilder();
        for (Row row : sheet) {
            int LineRow = row.getRowNum();
            if (LineRow == 0) continue; // 跳过表头



            String schemaName = row.getCell(0).getStringCellValue();
            String tableName = row.getCell(1).getStringCellValue();
            String columnName = row.getCell(2).getStringCellValue();
            String columnType = row.getCell(3).getStringCellValue();
            String columnComment = row.getCell(4).getStringCellValue();
            String tableComment = row.getCell(5).getStringCellValue();
            int prKey = 1;
            if (row.getCell(6) == null || row.getCell(6).getCellType() == CellType.BLANK) {
                prKey = 0;
            }


            // TODO 建表语句初始化
            if(beforeTableName.equals("")){
                ddlBuilder.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" (\n");
                if(prKey == 1){
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType).append(" ")
                            .append(",constraint pk_").append(columnName).append("_01 primary key(").append(columnName).append(")");
                }else{
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType);
                }
                commentBuilder.append("comment on table ").append(schemaName).append(".").append(tableName)
                        .append(" is '").append(tableComment).append("';\n");
                commentBuilder.append("comment on column ").append(schemaName).append(".").append(tableName)
                        .append(".").append(columnName).append(" is '").append(columnComment).append("';\n");
                if (LineRow <= LastRow) beforeTableName = tableName;
                continue;
            }

            // TODO 表名变化时 完成上一张表的建表语句  开始下一张表的建表语句
            if (!tableName.equals(beforeTableName) && !beforeTableName.equals("")){
                ddlBuilder.append("\n");
                ddlBuilder.append(");");
                ddlBuilder.append("\n");
                ddlBuilder.append(commentBuilder.toString());
                PathGandW.writeToFile(savePath,ddlBuilder.toString(),beforeTableName+".sql");
                ddlBuilder.setLength(0);
                commentBuilder.setLength(0);

//                ddlBuilder.append("\n");
                ddlBuilder.append("CREATE TABLE ").append(schemaName).append(".").append(tableName).append(" (\n");
                if(prKey == 1){
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType).append(" ")
                            .append(",constraint pk_").append(columnName).append("_01 primary key(").append(columnName).append(")");
                }else{
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType);
                }
                commentBuilder.append("comment on table ").append(schemaName).append(".").append(tableName)
                        .append(" is '").append(tableComment).append("';\n");
                commentBuilder.append("comment on column ").append(schemaName).append(".").append(tableName)
                        .append(".").append(columnName).append(" is '").append(columnComment).append("';\n");


            }else // TODO 添加字段信息
            {
                ddlBuilder.append(",\n");
                if(prKey == 1){
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType).append(" ")
                            .append(",constraint pk_").append(columnName).append("_01 primary key(").append(columnName).append(")");
                }else{
                    ddlBuilder.append("    ").append(columnName).append(" ").append(columnType);
                }
                commentBuilder.append("comment on column ").append(schemaName).append(".").append(tableName)
                        .append(".").append(columnName).append(" is '").append(columnComment).append("';\n");

            }
            // TODO 当是最后一张表时 补全最后一张表的建表语句
            if(LineRow == LastRow){

                ddlBuilder.append("\n");
                ddlBuilder.append(");");
                ddlBuilder.append("\n");
                ddlBuilder.append(commentBuilder.toString());
                PathGandW.writeToFile(savePath,ddlBuilder.toString(),beforeTableName+".sql");
                commentBuilder.setLength(0);
            }

            // TODO 表名继承
            if (LineRow <= LastRow) beforeTableName = tableName;




        }

    }



}
