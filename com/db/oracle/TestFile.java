package db.oracle;

import java.io.*;

/**
 * @ClassName TestFile
 * @Description TODO
 * @Author boy
 * @Date 2019/12/3 11:24 PM
 */
public class TestFile {
    static int count = 0;
    static int num = 100;
    public static void main(String[] args){
        try {
            File file = new File("/Users/suboya/src/project/redis/com/db/oracle/test.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader  bufferedReader = new BufferedReader(inputStreamReader);

            BufferedWriter bufferedWriter = getFile(++num);

            String line = bufferedReader.readLine();
            while(line!=null){

                if (line==null || line.trim()==""){
                    break;
                }
                line = "'" + line + "',";
                bufferedWriter.newLine();
                bufferedWriter.write(line);
//                if (++count%1000==0){
//                    if (bufferedWriter!=null) {
//                        bufferedWriter.close();
//                    }
//                    bufferedWriter = getFile(++num);
//                }
                line = bufferedReader.readLine();

            }
            fileInputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static BufferedWriter getFile(int num){
        BufferedWriter bufferedWriter = null;
        File file = null;
        try {
            file = new File("/Users/suboya/src/project/redis/com/txt/test"+num+".txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bufferedWriter;
    }
}
