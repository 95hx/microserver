package cn.luv2code.sample.userprovider.utils.demo.utils;

import java.io.*;
import java.util.Optional;

/**
 * @author hx
 * @Title: TxtDeal
 * @ProjectName frame-root
 * @Description: TODO
 * @date 2018/9/1914:33
 *
 */
public class TxtDeal {

    private static File outPutFile;
    private static BufferedWriter out;

    public static void main(String[] args) {
        System.out.println(fileToString("C:\\Users\\18701\\Desktop\\resource\\need.txt"));
    }
    /**
     *
     * @apiNote 文本内容提取为字符串
     * @param arg 文件路径
     * @return 字符串
     */
    public static String fileToString(String arg) {
        String string = "";
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            /* 读入TXT文件 */
            if (arg == null) {
                throw new FileNotFoundException();
            }
            String pathname = Optional.ofNullable(arg).orElseThrow(FileNotFoundException::new); // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            if (!filename.isDirectory()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(filename)); // 建立一个输入流对象reader
                BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                String line = "";
                line = br.readLine();
                string += line + "\n";
                while (line != null) {
                    line = br.readLine(); // 一次读入一行数据
                    string += line + "\n";
                }
                string = dealString(string);
                System.out.println(string);
            }
            convertToFile(string);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;

    }

    /**
     *
     * @param string
     * @throws IOException
     * @apiNote string写入classpath 下的 output.txt
     */
    private static void convertToFile(String string) throws IOException {
        /* 写入Txt文件 */
        // 相对路径，如果没有则要建立一个新的output。txt文件
        if (outPutFile == null) {
            outPutFile = new File("output.txt");
        }
        if (out == null) {
            out = new BufferedWriter(new FileWriter(outPutFile));
        }


        if (!outPutFile.exists()) {
            outPutFile.createNewFile(); // 创建新文件
        }
        out.append(string); // \r\n即为换行
        out.flush(); // 把缓存区内容压入文件
//        out.close(); // 最后记得关闭文件
    }

    /**
     *
     * @param string
     * @return 字符串
     * @apiNote bug处理去掉结尾null    还是会多一个回车
     */
    private static String dealString(String string) {
        int aNull = string.lastIndexOf("null");
        if (aNull != -1)
            return string.substring(0, aNull);
        return string;
    }
}
