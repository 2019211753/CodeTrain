package Homework;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * 请按规定转换日期。
 *
 * 出题人用到的java的类为：
 * java.time.LocalDate;
 * java.time.format.DateTimeFormatter;
 * java.time.format.DateTimeParseException;
 * 建议大家也用同样的类。
 *
 * 输入
 * 共三行，每行一个字符串，第一行代表待转换日期，第二行代表输入日期的格式
 * 第三行代表输出日期的格式。
 * 日期格式大概两类，与yyyy/MM/dd，yyyy-MM-dd类似。
 * 也就是说只有/和-两类分隔符和d，M，y种字符。
 *
 * 样例输入
 * 2021-04-13
 * yyyy-MM-dd
 * MM/dd/yyyy
 *
 * 样例输出
 * 04/13/2021
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-05-17
 */
public class DateFormatConversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();

        String format = scanner.nextLine();
        DateTimeFormatter inFormat = DateTimeFormatter.ofPattern(format);

        format = scanner.nextLine();
        DateTimeFormatter outFormat = DateTimeFormatter.ofPattern(format);

        try {
            LocalDate localDate = LocalDate.parse(date, inFormat);
            String localDateTime = outFormat.format(localDate);
            System.out.println(localDateTime);
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Text could not be parsed .");
        }
        scanner.close();
    }
}
