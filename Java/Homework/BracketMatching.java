package Homework;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目描述
 * 假设表达式中包含三种括号：(),[],{}。其嵌套顺序随意，即 () 或 {[{}]} 均为正确格式， [(])或 (()]均为不正确格式。
 * 现给出一个只包含这三种括号的括号序列，试判断该括号序列是否合法。
 *
 * 输入
 * 只包含一行，为只包含(),[],{}的括号序列。（序列长度不超过100个字符）
 *
 * 输出
 * 若括号序列合法，输出”YES”，反之输出”NO”。
 *
 * 样例输入
 * {([]())}
 *
 * 样例输出
 * YES
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-06-04
 */
public class BracketMatching {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String matches = scanner.nextLine();

        LinkedList<Character> chars = new LinkedList<>();
        for (int i = 0; i < matches.length(); i++)
        {
            char c = matches.charAt(i);
            //是左边括号就入栈 插入到头结点之前
            if (c == '{') {
                chars.push(c);
                continue;
            } else if (c == '}') {
                if (chars.size() != 0 && chars.getFirst() == '{')
                {
                    //如果栈不为空且头结点正好是左括号 则出栈
                    chars.removeFirst();
                    continue;
                }
                else {
                    //如果栈为空或头结点不对应左括号 直接输出NO 暂停程序
                    System.out.println("NO");
                    System.exit(0);
                }
            }
            if (c == '(') {
                chars.push(c);
                continue;
            } else if (c == ')') {
                if (chars.size() != 0 && chars.getFirst() == '(')
                {
                    chars.removeFirst();
                    continue;
                }
                else {
                    System.out.println("NO");
                    System.exit(0);
                }
            }
            if (c == '[') {
                chars.push(c);
            } else if (c == ']') {
                if (chars.size() != 0 && chars.getFirst() == '[')
                {
                    chars.removeFirst();
                }
                else {
                    System.out.println("NO");
                    System.exit(0);
                }
            }
        }

        if (chars.size() == 0)
        {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
