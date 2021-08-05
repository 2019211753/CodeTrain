package Homework;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


/**
 * 输入
 * 第一行为一个整数n(0<n)，代表有n个老师，后边n行每一行代表一个老师的数据，
 * 格式为若干个正整数（至少有2个），
 * 第一个整数为m(0<m<1000)，代表该老师所教的学生数量，后边为m个正整数代表每个学生的成绩。
 *
 * 目的
 * 按每个老师的学生的平均成绩由低到高输出。
 *
 * 输出
 * 共n行，每行为一个老师的数据（只包含学生的成绩不包含前边那个学生的个数，这些成绩按输入顺序输出）。
 * 也就是说先输出学生平均成绩低的老师的数据，后输出学生平均成绩高的老师的数据（计算平均成绩时用C语言的整数除法即可，也就是说所有计算过程中不需要用到浮点数）。
 *
 * 测试数据保证没有平均分相同的老师，且运算中所有整数可以用 int 存储。
 *
 * @version 1.0
 * @date 2021-05-11
 * @author 山水夜止
 */
public class AbnormalMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int teachers = scanner.nextInt();
        int[][] scores = new int[teachers][];
        for (int i = 0; i < teachers; i++)
        {
            int students = scanner.nextInt();
            int[] score = new int[students + 1];
            score[0] = 0;
            for (int j = 1; j < students + 1; j++)
            {
                score[j] = scanner.nextInt();
                score[0] += score[j];
            }
            score[0] /= students;
            scores[i] = score;
        }
        Arrays.sort(scores , Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i<teachers; i++)
        {
            for (int j = 1; j < scores[i].length - 1; j++)
            {
                System.out.print(scores[i][j] + " ");
            }
            System.out.println(scores[i][scores[i].length - 1]);
        }
        scanner.close();
    }
}
