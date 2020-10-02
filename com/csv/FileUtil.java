package csv;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author boy
 * @Date 2020/10/1 7:20 AM
 */

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;

/**
 * @author https://blog.csdn.net/chen_2890
 * @description FileUtil
 * @date 2019/6/14 17:29
 */
public class FileUtil {

    /**
     * @description 不使用递归的方法调用
     * @param path 文件夹路径
     * @return java.util.List<java.io.File>
     * @author https://blog.csdn.net/chen_2890
     * @date 2019/6/14 17:34
     * @version V1.0
     */
    public static List<File> traverseFolder1(String path) {
        LinkedList<File> fileList = new LinkedList<File>();
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                    fileList.add(file2);
//                    System.out.println("文件:" + file2.getAbsolutePath());
                    String fileName = file2.getAbsolutePath();
                    if (fileName.length()>46) {
                        String dateStr = fileName.substring(38, 46);
//                        System.out.println(dateStr);

                        Test.getFile(file2.getAbsolutePath());
                        fileNum++;
                    }
                }
            }



            File temp_file1;
            while (!list.isEmpty()) {
                temp_file1 = list.removeFirst();
                files = temp_file1.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    } else {
                        fileList.add(file2);
                        System.out.println("文件:" + file2.getAbsolutePath());
                        fileNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
        return fileList;
    }
    /**
     * @description 使用递归的方法调用
     * @param path 文件夹路径
     * @return java.util.List<java.io.File>
     * @author https://blog.csdn.net/chen_2890
     * @date 2019/6/14 17:35
     * @version V1.0
     */
    public static List<File> traverseFolder2(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        fileList.add(file2);
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return fileList;
    }

    /**
     * @description 使用递归的方法调用，并判断文件名是否以.jpg结尾
     * @param path 文件夹路径
     * @return java.util.List<java.io.File>
     * @author https://blog.csdn.net/chen_2890
     * @date 2019/6/14 17:35
     * @version V1.0
     */
    public static List<File> getFileList(String path) {
        List<File> fileList = new ArrayList<>();
        File dir = new File(path);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                // 判断是文件还是文件夹
                if (files[i].isDirectory()) {
                    // 获取文件绝对路径
                    getFileList(files[i].getAbsolutePath());
                    // 判断文件名是否以.jpg结尾
                } else if (fileName.endsWith(".jpg")) {
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    fileList.add(files[i]);
                } else {
                    continue;
                }
            }
        }
        return fileList;
    }
    public static void main(String[] args){
        traverseFolder1("/Users/suboya/Downloads/jiashi");
    }
}


