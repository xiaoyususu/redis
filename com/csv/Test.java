package csv;

/**
 * @ClassName Test
 * @Description TODO
 * @Author boy
 * @Date 2020/10/1 6:59 AM
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.opencsv.CSVReader;


public class Test {
    public static String string1 = "发给陈大姐";
    public static String string4 = "转给陈大姐";
    public static String string2 = "发给陈大姐";
    public static String string3 = "陈大姐";
    public static String string5 = "张旭";
    public static String string6 = "都市先生";
    public static String string7 = "张ty";





    public static void getFile(String path){
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            String[] strs;
            while ((strs = csvReader.readNext()) != null) {
                String s = Arrays.deepToString(strs);

//                System.out.println(s);
                String[] sArray = s.split(",");
                for (int i=0;i<sArray.length;i++){
                    if (sArray[i].contains(string7)){
                        System.out.println(Arrays.deepToString(strs));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvReader != null) {
                    csvReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

    }

}
