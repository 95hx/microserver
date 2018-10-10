package cn.luv2code.sample.userprovider.utils.demo.utils;

import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 备份指定目录的指定后缀文件到指定目录,文件名为原来目录位置(: \\ 分别用= ~替换 适用于windows)
 */
public class CopyWantFile {
    private static ArrayList<String> needFiles = new ArrayList();
    private static LinkedList<File> queueFiles = new LinkedList();

    public static void main(String[] args) throws Exception {
        //源目录
        ArrayList<String> strings = scanFilesWithRecursion("C:\\Users\\18701\\Desktop\\micro-server-demo");
        for (String s : strings) {
            //目标目录
            String outDir = "C:\\Users\\18701\\Desktop\\micro-server-demo\\user-provider\\trash\\";
            File outFile = new File(outDir + s.replace("\\", "~").replace(":", "="));
            File outDirFile = new File(outDir);
            //不存在备份目录则创建
            if (!outDirFile.exists()) {
                outDirFile.mkdir();
            }
//            FileChannels拷贝大文件
            copyFileUsingFileStreams(new File(s), outFile);
//            Files.copy()
//            FileCopyUtils.copy(new File(s), out);
        }
        System.out.println();
    }

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
                    //暂时将指定文件名放入scanFiles中
                    if (files[i].getAbsolutePath().endsWith(".yml")) {
                        needFiles.add(files[i].getAbsolutePath());
                    }
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
                        if (currentFiles[j].getAbsolutePath().endsWith(".yml")) {
                            needFiles.add(currentFiles[j].getAbsolutePath());
                        }
                    }
                }
            }
        }

        return needFiles;
    }

    /**
     * 复制文件source到dest
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
                output.flush();
            }
        } finally {
            input.close();
            output.close();
        }
    }
}
