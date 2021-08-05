package Homework;

import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *题目描述
 * 现有一个zip格式的压缩文件，文件名为test.zip。它里边压缩了若干个文件（少于20个），没有目录。
 * 请写一段程序将这些文件的文件名读出来。
 *
 * 输入
 * 为一个整数n（0<n<20），代表要输出压缩文件中第n个条目的文件名。比如压缩文件共压缩了3个文件，
 * 依次为a.txt，b.txt，c.txt。那么当输入为1时输出a.txt，输入为2时输出b.txt，输入为3时输出c.txt。
 * 测试用例保证输入合法。
 *
 * 输出
 * 只有一行字符串，为对应的文件名。
 *
 * 样例输入
 * 1
 *
 * 样例输出
 * a.txt
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-06-01
 */
public class ReadCompressedFile {
    public static void main(String[] args) throws IOException {
        //打开Zip输出流
        ZipOutputStream out=new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("test.zip")));
        //我目录下的文件名 也即相对路径
        String[] strings = {"工程光学.log", "工程光学.aux", "工程光学.toc"};
        for (String string : strings) {
            System.out.println("Writing file " + string);
            //输入流
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(string));
            //把压缩文件放进去 此时是空的 还要out.write写进去
            out.putNextEntry(new ZipEntry(string));
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
        }
        out.close();

        //读取第几个文件名
        int times = new Scanner(System.in).nextInt();
        int time = 0;
        ZipInputStream in2 = new ZipInputStream(new BufferedInputStream(new FileInputStream("test.zip")));
        ZipEntry ze;
        while ((ze = in2.getNextEntry()) != null) {
            time++;
            if (time == times)
                System.out.println(ze.getName());
        }
        in2.close();
    }
}
