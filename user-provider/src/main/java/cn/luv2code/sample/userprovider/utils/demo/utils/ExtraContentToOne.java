package cn.luv2code.sample.userprovider.utils.demo.utils;

import java.util.ArrayList;

public class ExtraContentToOne {

    public static void main(String[] args) throws Exception{
        ArrayList arrayList =DirFileScan.scanFilesWithRecursion("C:\\Users\\18701\\Desktop\\ecxj\\prana-dev2.0-0d94d98d64ad25e0b57d341e669aff4a2ea40b35\\XMALL\\xmall-master\\xmall-manager\\xmall-manager-pojo\\src\\main\\java\\cn\\exrick\\manager\\dto");
        for (Object obj:arrayList) {
            TxtDeal.main((String) obj);
        }
        System.out.println();
    }
}
