package Homework;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 输入
 * 只有一行，为两个用空格分隔的整数，第一个代表年份（大于等于1900且小于等于3000）
 * 第二个代表月份（保证在1到12之间）。
 *
 * 输出
 * 该月的月历，具体格式见样例。
 * 注意：输出中每一列都是4个字符，如果不足4个则前边用空格填补。第一列也是。
 *
 * 样例输入
 * 2021 4
 *
 * 样例输出
 *  Mon Tue Wed Thu Fri Sat Sun
 *                1   2   3   4
 *    5   6   7   8   9  10  11
 *   12  13  14  15  16  17  18
 *   19  20  21  22  23  24  25
 *   26  27  28  29  30
 *
 *  * @version 1.0
 *  * @date 2021-05-17
 *  * @author 山水夜止
 */
public class PrintCalendar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();

        //这里最好整个final常量数组代替month
        //一个月有多少天
        int monthDays = new GregorianCalendar(year, month - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
        //这个月的一号是星期几
        //结果1为周日 2-7为周二到周六
        int weekDay = new GregorianCalendar(year, month - 1, 1).get(Calendar.DAY_OF_WEEK) - 1;
        weekDay = weekDay == 0? 7 : weekDay;

        System.out.println(" Mon Tue Wed Thu Fri Sat Sun");

        for (int k = 0; k < weekDay - 1; k++)
        {
            System.out.print("    ");
        }


        for (int i = 1; i <= monthDays; i++)
        {
            if (i < 10) {
                System.out.print("   " + i);
            } else {
                System.out.print("  " + i);
            }
            weekDay++;
            if (weekDay % 7 == 1 && monthDays != i)
            {
                System.out.print("\n");
            }
        }
    }
}

