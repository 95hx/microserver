package cn.luv2code.sample.userprovider.utils.demo.utils;


import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * 先使用CopyWantFile备份
 * 然后执行本程序还原回去
 */
public class ResetWantFileBack {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = DirFileScan.scanFilesWithRecursion("C:\\Users\\18701\\Desktop\\micro-server-demo\\user-provider\\trash\\");
        for (String s : list) {
            String destFile = s.substring(s.lastIndexOf("\\")).replace("=", ":").replace("~", "\\").substring(1);
            String dir = destFile.substring(0, destFile.lastIndexOf("\\") );
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            String fileName = destFile.substring(destFile.lastIndexOf("\\") + 1);
            FileCopyUtils.copy( new File(s),new File(destFile));
        }
    }
}
