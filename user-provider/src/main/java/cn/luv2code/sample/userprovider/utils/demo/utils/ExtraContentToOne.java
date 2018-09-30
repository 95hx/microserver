package cn.luv2code.sample.userprovider.utils.demo.utils;

import java.util.ArrayList;
/**
 * @author hx
 * @Title: ExtraContentToOne
 * @ProjectName micro-server-demo
 * @Description: TODO
 * @date 2018/9/1917:30
 */
public class ExtraContentToOne {

    public static void main(String[] args) throws Exception{
        ArrayList<String> arrayList =DirFileScan.scanFilesWithNoRecursion("C:\\Users\\18701\\Desktop\\resource");
        for (String filePath:arrayList) {
            TxtDeal.fileToString(filePath);
        }
        System.out.println();
    }
}
