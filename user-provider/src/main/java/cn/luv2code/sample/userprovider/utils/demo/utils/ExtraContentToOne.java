package cn.luv2code.sample.userprovider.utils.demo.utils;

import java.util.ArrayList;
/**
 * @author hx
 * @Title: ExtraContentToOne
 * @ProjectName micro-server-demo
 * @Description: 把多个文本内容合并到一个文本
 * @date 2018/9/1917:30
 */
public class ExtraContentToOne {

    public static void main(String[] args) throws Exception{
        //文本地址
        ArrayList<String> arrayList =DirFileScan.scanFilesWithNoRecursion("C:\\Users\\18701\\Desktop\\resource");
        for (String filePath:arrayList) {
            //所有文本内容输出
            System.out.println(TxtDeal.fileToString(filePath));
        }
    }
}
