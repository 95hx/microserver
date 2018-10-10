package cn.luv2code.sample.userprovider.utils.demo.utils;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * 在CopyWantFile操作后
 * 把备份的文件还原回去
 *
 */
// TODO: 2018/10/10 待测试验证
public class ResetWantFileBack {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = DirFileScan.scanFilesWithRecursion("C:\\Users\\18701\\Desktop\\micro-server-demo\\user-provider\\trash\\");
        for (String s : list) {
            String destFile = s.substring(s.lastIndexOf("\\")).replace("=", ":").replace("~", "\\").substring(1);
            String fileName = destFile.substring(destFile.lastIndexOf("\\")+1);
            String sourceFile=s.substring(0,s.lastIndexOf("\\"));
            FileCopyUtils.copy( new File(destFile),new File(sourceFile+"\\"+fileName));
            System.out.println();
        }
    }
}
