package Homework;

import java.util.Scanner;

/**
 * 在上次作业的学生类基础上：
 * 定义一个异常类ScoreException，当输入的学生成绩不在[0,100]区间时，抛出该异常。
 * 定义一个异常类StudentNumberException，当输入的学号不满足下述条件，则抛出该异常。条件为：
 * 学号为10位，第1位为2，第2位为0，其余位为数字0~9.
 *
 *输入
 * 只有一行，先是2个字符串，依次代表number, name,
 * 然后是三个整数，依次代表  maths, english, science 的成绩。
 *
 * 输出
 * 如果成绩非法，则输出  Illegal score format  ；如果学号非法则输出 Illegal number format  。
 * 如果正常，则打印学生信息，平均分保留一位小数。具体格式见输出样例。
 *
 * 样例输入
 * 2011211301 Tom 88 79 90
 *
 * 样例输出
 * Student ID:2011211301
 * Name:Tom
 * Math:88
 * English:79
 * Science:90
 * Average Score:85.7
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-05-11
 */
public class Complex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opration = scanner.next();
        int lines = scanner.nextInt();

        for (int i = 0; i < lines; i++)
        {
            double x1 = scanner.nextDouble();
            double y1 = scanner.nextDouble();
            double x2 = scanner.nextDouble();
            double y2 = scanner.nextDouble();

            double zx =0, zy = 0;

            if ("add".equals(opration))
            {
                zx = x1 + x2;
                zy = y1 + y2;
            }

            if ("sub".equals(opration))
            {
                zx = x1 - x2;
                zy = y1 - y2;
            }

            if ("mul".equals(opration))
            {
                zx = (x1*x2 - y1* y2);
                zy = (x2*y1 + x1*y2);
            }

            if ("div".equals(opration))
            {
                try {
                    if (x2 < 0.0000000000000000001 && x2 > -0.0000000000000000001 && y2 < 0.000000000000000000001 && y2 > -0.0000000000000000001)
                    {
                        throw new ComplexDivException();
                    }
                } catch (ComplexDivException c)
                {
                    System.out.println("Error No : 1001\n" +
                            "Error Message : Divide by zero.");
                    continue;
                }

                zx = (x1*x2 + y1* y2) / (x2*x2 + y2 *y2);
                zy = (x2*y1 - x1*y2) / (x2*x2 + y2 *y2);
            }

            if ((zy > 0) || (zy < 0.0000000000001 && zy > -0.00000000000001))
            {
                System.out.printf("%.1f+%.1fi\n", zx, zy);
            } else {
                System.out.printf("%.1f%.1fi\n", zx, zy);
            }
        }
    }
}

class ComplexDivException extends Exception
{
    private String err;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

}
