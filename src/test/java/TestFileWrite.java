import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.weiliangchen551.com.util.DDLMaker;
import org.weiliangchen551.com.util.PathGandW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/*
    p:尝试将整个ddl写入一个文件
 */

public class TestFileWrite {
    public static void main(String[] args) throws IOException {
        String excelPath = PathGandW.getFilePathFromConfig("file.excel.path");
        String writePath = PathGandW.getFilePathFromConfig("file.save.path");
        System.out.println(excelPath);
        FileInputStream fis = new FileInputStream(new File(excelPath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        String ddl = DDLMaker.ddlMakerOneFile(sheet);
        PathGandW.writeToFile(writePath,ddl,"allSQL.sql");
    }
}
