package cn.luv2code.sample.userprovider.utils.demo.utils;

import java.io.*;
import java.util.Optional;

/**
 * @author hx
 * @Title: TxtDeal
 * @ProjectName frame-root
 * @Description: TODO
 * @date 2018/9/1914:33
 */
public class TxtDeal {
    public static String main(String arg) {
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

            /* 写入Txt文件 */
            File writename = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            if (!writename.exists()) {
                writename.createNewFile(); // 创建新文件
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.append(string); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;

    }

    private static String dealString(String string) {
        int aNull = string.lastIndexOf("null");
        if (aNull != -1)
            return string.substring(0, aNull);
        return string;
    }
}
