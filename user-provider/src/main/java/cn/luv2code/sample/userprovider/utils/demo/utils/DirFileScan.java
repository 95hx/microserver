package cn.luv2code.sample.userprovider.utils.demo.utils;

import java.util.ArrayList;
import java.io.File;
import java.util.LinkedList;

/**
 * @author hx
 * @Title: DirFileScan
 * @ProjectName micro-server-demo
 * @Description: TODO
 * @date 2018/9/1916:37
 */
public class DirFileScan {

    private static ArrayList<String> scanFiles = new ArrayList();
    private static LinkedList<File> queueFiles = new LinkedList();


    /**
     * @param 路径
     * @return 文件
     * @apiNote 非递归扫描指定文件夹下面的指定文件
     */
    // TODO: 2018/9/30 有bug
    public static ArrayList<String> scanFilesWithNoRecursion(String folderPath) throws Exception {
        ArrayList<String> dirctorys = new ArrayList<String>();
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
            throw new Exception('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        if (directory.isDirectory()) {
            File[] filelist = directory.listFiles();
            for (int i = 0; i < filelist.length; i++) {
                /**如果当前是文件夹跳过**/
                if (filelist[i].isDirectory()) {

                }
                /**非文件夹**/
                else {
                    scanFiles.add(filelist[i].getAbsolutePath());
                }
            }
        }
        return scanFiles;
    }

    /**
     * @param 路径
     * @return 文件
     * @apiNote 递归扫描指定文件夹下面的指定文件
     */
    public static ArrayList<String> scanFilesWithRecursion(String folderPath) throws Exception {
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
            throw new Exception('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        } else {
            //首先将第一层目录扫描一遍
            File[] files = directory.listFiles();
            //遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    queueFiles.add(files[i]);
                } else {
                    //暂时将文件名放入scanFiles中
                    scanFiles.add(files[i].getAbsolutePath());
                }
            }

            //如果linkedList非空遍历linkedList
            while (!queueFiles.isEmpty()) {
                //移出linkedList中的第一个
                File headDirectory = queueFiles.removeFirst();
                File[] currentFiles = headDirectory.listFiles();
                for (int j = 0; j < currentFiles.length; j++) {
                    if (currentFiles[j].isDirectory()) {
                        //如果仍然是文件夹，将其放入linkedList中
                        queueFiles.add(currentFiles[j]);
                    } else {
                        scanFiles.add(currentFiles[j].getAbsolutePath());
                    }
                }
            }
        }

        return scanFiles;
    }

    /**
     * @apiNote 测试主类
     */
    public static void main(String[] args) throws Exception {

        ArrayList arrayList=scanFilesWithRecursion("C:\\Users\\18701\\Desktop\\供应链bug图片");
//        ArrayList arrayList = scanFilesWithNoRecursion("C:\\Users\\18701\\Desktop\\供应链bug图片");
        System.out.println();
    }
}


