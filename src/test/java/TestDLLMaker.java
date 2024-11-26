import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.weiliangchen551.com.util.DDLMaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDLLMaker {
    public static void main(String[] args) throws IOException {
//        String excelFilePath = "src/file/source/test.xlsx";
//        FileInputStream fis = new FileInputStream(new File(excelFilePath));
//        Workbook workbook = new XSSFWorkbook(fis);
//        Sheet sheet = workbook.getSheetAt(0);
//        String ddl = DDLMaker.ddlMakerOneFile(sheet);
//        System.out.println(ddl);
        String excelFilePath = "src/file/source/test.xlsx";
        String savePath = "src/file/target";
        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        DDLMaker.ddlMakerManyFile(sheet,savePath);
    }
}
