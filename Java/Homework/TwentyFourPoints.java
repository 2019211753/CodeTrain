package Homework;

import java.util.Scanner;

/**
 * 输入：需要得到的点数；给定的牌面。
 * 目的：通过牌面的+ - 得到给定点数。
 * 输出：YES/NO是否能得到。
 *
 * @version 1.0
 * @date 2021-05-11
 * @author 山水夜止
 */
public class TwentyFourPoints {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int need = scanner.nextInt();

        int[] array = new int[5];
        for (int i = 0; i < 5; i++)
        {
            array[i] = scanner.nextInt();
        }

        b:
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                for (int k = -1; k <= 1; k++)
                {
                    for (int m = -1; m <=1; m++)
                    {
                        for (int g = -1; g <= 1; g++)
                        {
                            int sum = i * array[0] + j * array[1] + k * array[2] + m * array[3] + g * array[4];
                            if (sum == need)
                            {
                                System.out.println("YES");
                                break b;
                            } else {
                                if (i * j * k * m * g == 1 & i + j + k + m + g != 1 & i + j + k + m + g != -3)
                                {
                                    System.out.println("NO");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
