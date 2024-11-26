import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.weiliangchen551.com.util.DDLMaker;
import org.weiliangchen551.com.util.PathGandW;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Final {
    public static void main(String[] args) throws IOException {
        String excelPath = PathGandW.getFilePathFromConfig("file.excel.path");
        String savePath = PathGandW.getFilePathFromConfig("file.save.path");
        FileInputStream fis = new FileInputStream(new File(excelPath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);



        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数字：");
        System.out.println("1: 生成单个sqlDDL文件（所有建表语句都在一个文件里）");
        System.out.println("2: 生成多个sqlDDL文件（每个表的建表语句拥有独立的文件）");
        System.out.println("3: 都要（同时生成1，2）");
        System.out.print("请输入你的选择（1 || 2 || 3）：");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("你选择了操作 1");
                String ddl = DDLMaker.ddlMakerOneFile(sheet);
                PathGandW.writeToFile(savePath,ddl,"allDDL.sql");
                break;
            case 2:
                System.out.println("你选择了操作 2");
                DDLMaker.ddlMakerManyFile(sheet,savePath);
                break;
            case 3:
                System.out.println("你选择了操作 3");
                String ddl2 = DDLMaker.ddlMakerOneFile(sheet);
                PathGandW.writeToFile(savePath,ddl2,"allDDL.sql");
                DDLMaker.ddlMakerManyFile(sheet,savePath);
                break;
            default:
                System.out.println("无效的输入，请输入 1 或 2 或 3。");
                break;
        }

        // 关闭Scanner对象
        scanner.close();
    }
}
