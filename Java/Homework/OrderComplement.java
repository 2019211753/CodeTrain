package Homework;

import java.util.Scanner;

/**
 * 输入
 * 第一行为一个整数n(1<n<100)，代表共n条命令；
 * 后边为n行字符串（长度均不超过50且只包含小写字母），代表这n条命令；
 * 第n+2行为一个字符串（长度均不超过50且只包含小写字母），为测试字符串；
 *
 * 输出
 * 若干行，为n条命令中所有以测试字符串开头的命令，每个命令占一行，按字典序依次输出。
 * 测试数据保证至少会有一条命令被输出。
 *
 * 样例输入
 * 6
 * ls
 * cd
 * cp
 * rm
 * mv
 * diff
 * c
 *
 * 样例输出
 * cd
 * cp
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-05-11
 */
public class OrderComplement {
    public static void main(String[] args) {

        OrderComplement main = new OrderComplement();

        //命令个数并吸收回车
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        scanner.nextLine();

        //输入命令
        String[] strs = new String[total];
        for (int i = 0; i < total; i++)
        {
            strs[i] = scanner.nextLine();
        }

        //输入测试字符 得到长度
        String test = scanner.nextLine();
        int len = test.length();

        String[] result = new String[total];
        int begin = 0;

        //得到满足题意的命令
        for (int j = 0; j < total; j++)
        {
            String str = strs[j];
            if (str.length() >= len)
            {
                if (str.substring(0, len ).equals(test))
                {
                    result[begin] = str;
                    begin++;
                }
            }
        }

        for (int m = 0; m < begin - 1; m++)
        {
            for (int n = 0; n < begin - 1; n++)
            {
                if (!main.bigger(result[n + 1], result[n]))
                {
                    String temp = result[n];
                    result[n] = result[n + 1];
                    result[n + 1] = temp;
                }
            }
        }

        for (String s : result) {
            if (s != null) {
                System.out.println(s);
            }
        }
    }

    //a的字典序比b大 输出true
    public boolean bigger(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();

        for (int i = 0; i < (Math.min(len2, len1)); i++)
        {
            if (a.charAt(i) > b.charAt(i))
            {
                return true;
            } else if (a.charAt(i) < b.charAt(i))
            {
                return false;
            }
        }
        return true;
    }
}
