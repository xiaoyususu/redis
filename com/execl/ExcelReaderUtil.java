package execl;

/**
 * @ClassName ExcelReaderUtil
 * @Description TODO
 * @Author boy
 * @Date 2019/8/27 3:23 PM
 */

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * POI解析Excel
 */
public class ExcelReaderUtil {
    static Map<String, String> mapAll = new HashMap<>();
    static int count = 0;
    static int count1 = 0;
    static int count11 = 0;

    /**
     * 根据fileType不同读取excel文件
     *
     * @param path
     * @param path
     * @throws IOException
     */
    public static List<List<String>> readExcel(String path) {
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        // return a list contains many list
        List<List<String>> lists = new ArrayList<List<String>>();
        //读取excel文件
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            //获取工作薄
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }

            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            //第一行为标题
            for (Row row : sheet) {
                ArrayList<String> list = new ArrayList<String>();
                //身份证号
                Cell cell1 = row.getCell(5);
                cell1.setCellType(Cell.CELL_TYPE_STRING);
                String idnum = cell1.getStringCellValue();
                //名字
                Cell cell2 = row.getCell(0);
                cell1.setCellType(Cell.CELL_TYPE_STRING);
                String name = cell2.getStringCellValue();
//                System.out.println(idnum);
                String s = mapAll.get(idnum);
                count++;
                if (null != s && !s.equals(name)) {
                    for (Cell cell : row) {
                        //根据不同类型转化成字符串
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        list.add(cell.getStringCellValue());
                    }
                    lists.add(list);
                    count11++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }

    public static List<List<String>> readExcel1(String path) {
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        // return a list contains many list
        List<List<String>> lists = new ArrayList<List<String>>();
        //读取excel文件
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            //获取工作薄
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }

            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            //第一行为标题
            for (Row row : sheet) {
                ArrayList<String> list = new ArrayList<String>();
                //身份证号
                Cell cell1 = row.getCell(4);
                cell1.setCellType(Cell.CELL_TYPE_STRING);
                String idnum = cell1.getStringCellValue();
                //名字
                Cell cell2 = row.getCell(1);
                cell1.setCellType(Cell.CELL_TYPE_STRING);
                String name = cell2.getStringCellValue();
                System.out.println(name);
                mapAll.put(idnum, name);
                count1++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }


    /**
     * 创建Excel.xls
     *
     * @param lists  需要写入xls的数据
     * @param titles 列标题
     * @param name   文件名
     * @return
     * @throws IOException
     */
    public static Workbook creatExcel(List<List<String>> lists, String[] titles, String name) throws IOException {
        System.out.println(lists);
        //创建新的工作薄
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(name);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int i = 0; i < titles.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(cs);
        }
        if (lists == null || lists.size() == 0) {
            return wb;
        }
        //设置每行每列的值
        for (short i = 1; i <= lists.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            for (short j = 0; j < titles.length; j++) {
                // 在row行上创建一个方格
                Cell cell = row1.createCell(j);
                if (cell != null) {
                    cell.setCellValue(lists.get(i - 1).get(j));
                    cell.setCellStyle(cs2);
                }
            }
        }
        return wb;
    }

    public static void main(String[] args) {


        String path1 = "/Users/suboya/Downloads/Book19.xlsx";
        List<List<String>> listss = readExcel1(path1);

        System.out.println("count1:" + count1);

        String path = "/Users/suboya/Downloads/2018年参合名单.xlsx";
        List<List<String>> lists = readExcel(path);

        System.out.println("count:" + count);
        System.out.println("count11:" + count11);
        String[] titles = {"1", "1", "1", "1", "1", "1", "1", "1", "1"};
        try {
            Workbook workbook = creatExcel(lists, titles, "111");
            File file = new File("/Users/suboya/Downloads/Book999991.csv");
            OutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
