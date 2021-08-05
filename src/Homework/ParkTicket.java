package Homework;


import java.util.Scanner;

/**
 * 某公园门票有两种，一种为纸质票，一种为电子票。门票的价格根据不同的人群有不同的折扣，其中军人免费，儿童减30，学生打8折，其他原价。先请你写一段出票的程序。
 *
 * 设计要求：
 * 1、定义两个抽象策略接口，一个为折扣（Discount），一个为票的类型（Ticket）。
 * 2、不同折扣均定义成实现Discount接口的类，不同类型的票均定义成实现Ticket接口的类。（各种具体的策略类）
 * 3、定义一个公园门票类（环境类），该类至少包含两个实例变量，一个是Discount的引用，一个是Ticket的引用。
 * 该类可以根据Discount引用所指向折扣类型的不同计算出折扣后的票价，可以根据Ticket引用所指向的票的类型不同返回不同类型的票。
 * 4、定义一个Main类（客户端类），该类用上述类实现题目要求。
 *
 * 这种设计方式叫策略模式，该模式的一个很重要的优点是，当有新的折扣类型（比如老年人票）或新的票的类型（比如凭身份证入场）时，只需要添加相应的类，去实现对应的策略，然后修改Main类就可以了。其他的代码均不用修改。
 * 如果使用配置文件，连Main类都不需要修改。所以检验自己的设计是否符合要求的方法就是真的去增加老年票或凭身份证入场，看是不是真的只需修改Main类，而不需要修改原有的其他任何类的代码。
 *
 * 输入
 * 第一行为一个整数，代表原始票价，第二行也是一个整数n(0<n<50)，代表测试用例组数。后边是n行，每行一组测试用例。
 * 格式为用空格分隔的两个字符串，前边的为客户类型（只可能是student、children、soldier、adult之一，依次代表学生、儿童、军人和其他），后边的为票的类型（只可能是paper和electronical之一，依次代笔纸质票和电子票）。
 * 测试用例保证合法，且原始票价为10的倍数，所以计算打折时用整数除法即可。
 *
 * 输出
 * 共2 乘 n行，其中每两行依次对应输入的测试用例，格式为第一行输出票的类型，电子票输出E_ticket，纸质票输出PaperTicket。第二行输出折扣后的票价，格式见样例。
 *
 * 样例输入
 * 100
 * 3
 * adult paper
 * children electronical
 * student electronical
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-05-26
 */

abstract class Discount
{
    //使用方法而不是属性 多态
    abstract int getDiscount(int originalPrice);
}

abstract class Ticket
{
    //使用方法而不是属性 多态
    abstract String getType();
}

class SoldierDisCount extends Discount
{
    @Override
    int getDiscount(int originalPrice) {
        return 0;
    }
}

class ChildrenDiscount extends Discount
{
    @Override
    int getDiscount(int originalPrice) {
        return originalPrice - 30;
    }
}

class StudentDiscount extends Discount
{
    @Override
    int getDiscount(int originalPrice) {
        return (int) (originalPrice * 0.8);
    }
}

class NoDiscount extends Discount
{
    @Override
    int getDiscount(int originalPrice) {
        return originalPrice;
    }
}

class PaperTicket extends Ticket
{
    @Override
    String getType() {
        return "PaperTicket";
    }
}

class ElectronicalTicket extends Ticket
{
    @Override
    String getType() {
        return "E_ticket";
    }
}

class Park
{
    Discount discount;
    Ticket ticket;

    int getTicketDiscount(int originalPrice)
    {
        return discount.getDiscount(originalPrice);
    }
    String getTicketType()
    {
        return ticket.getType();
    }


}

public class ParkTicket
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //原始票价
        int originalPrice = scanner.nextInt();
        //测试样例数
        int num = scanner.nextInt();

        for (int i = 0; i < num; i++)
        {
            //拆分为身份 票类
            String identity = scanner.next();
            String ticketType = scanner.next();
            scanner.nextLine();

            Park park = new Park();

            if (ticketType.equals("electronical"))
            {
                park.ticket = new ElectronicalTicket();
            } else if (ticketType.equals("paper"))
            {
                park.ticket = new PaperTicket();
            }

            if (identity.equals("soldier"))
            {
                park.discount = new SoldierDisCount();
            } else if (identity.equals("children"))
            {
                park.discount = new ChildrenDiscount();
            } else if (identity.equals("student"))
            {
                park.discount = new StudentDiscount();
            } else if (identity.equals("adult"))
            {
                park.discount = new NoDiscount();
            }

            System.out.println( park.getTicketType() + "\n" + "Price:" + park.getTicketDiscount(originalPrice));
        }
        scanner.close();
    }
}
