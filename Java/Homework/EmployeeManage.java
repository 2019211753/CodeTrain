package Homework;


import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 定义下述5个类， 其中SalaridEmployee, HourlyEmployee, CommisionEmployee 继承自Employee，basePlusCommisionEmployee继承自 CommisionEmployee。
 * 类属性如下：
 * Employee: firstName,lastName,socialSecurityNumber
 * SalaridEmployee: weeklySalary(周薪）
 * HourlyEmployee: wage(每小时的工钱),hours（月工作小时数）
 * CommisionEmployee: grossSales(销售额),commissionRate(提成比率)
 * basePlusCommisionEmployee: baseSalary（月基本工资）
 *
 * Employee类中定义了抽象方法earning，用于计算员工的月工资。
 * SalaridEmployee月工资计算为：weeklySalary*4
 * HourlyEmployee月工资计算为：wage*hours
 * CommisionEmployee月工资计算为：grossSales*commissionRate
 * basePlusCommisionEmployee月工资计算为：grossSales*commissionRate+baseSalary
 * 类还应该包括构造方法，toString方法，属性的get/set方法。
 * firstName,lastName,socialSecurityNumber的初始化在构造方法中完成。其中对firstName,lastName也要提供get/set方法，对socialSecurityNumber只提供get方法。
 * 其他属性要提供get和set方法。
 *
 *
 * 类中用到的数值建议用double存储。
 *
 * 在Main类中利用这5个类完成相应查询要求。
 *
 * 输入
 * 第一行为一个整数n(0<n<=100)，代表共n个雇员。后边是n行，每行一个雇员的数据，数据格式见样例。
 * 其中0表示SalaridEmployee，1表示HourlyEmployee，2表示CommissionEmployee，3表示basePlusCommisionEmployee。紧跟着的三个字符串依次代表firstName,lastName,socialSecurityNumber。
 * 后边的数字，如果是SalaridEmployee则代表周薪，如果是HourlyEmployee，则依次代表wage,hours，如果是CommissionEmployee则依次代表grossSales,commissionRate，如果是basePlusCommisionEmployee则依次代表grossSales，commissionRate，baseSalary。
 * 这n行后的第一行为一个整数，m(0<m<100)，代表测试用例条数。后边为m行，每行一条测试用例，数据格式见样例。其中0代表根据firstName（其后边跟的即为firstName）），1代表根据socialSecurityNumber查询（其后边跟的即为firstName）。
 *
 * 输出
 * 若干行，每行表示一个雇员的信息。具体格式见样例（注意各类数据之间都有一个空格）。建议依次调用对象的toString方法输出对象的信息，调用earning方法来输出对象的月工资。
 * 如果一条查询有多条结果（firstName有可能相同），则按月工资从低到高的顺序输出。
 *
 * java.lang包有一个接口叫Comparable，该接口只有一个方法，即int  compareTo(T  o)方法；如果我们在自己的类（比如Employee）中实现该接口，那么就可以使用使用Arrays.sort()方法对Employee类的数组进行排序。
 * int  compareTo(T  o)方法：
 * 如： obj1.compareTo(obj2)：obj1小于、等于、大于obj2时，分别返回负整数、零、正整数。
 *
 * 样例输入
 * 4
 * 0 Ai Meng 2012673901 4312
 * 1 NanXiong Qimu 2016782340 15.2 200
 * 2 Guo Yang 2017672347 46781.3 0.1
 * 3 Rong Huang 2018768901 7854.4 0.28 7098
 * 4
 * 0 Ai
 * 1 2016782340
 * 1 2018768901
 * 0 Guo
 *
 * 样例输出 Copy
 * firstName:Ai; lastName:Meng; socialSecurityNumber:2012673901; earning:17248.00
 * firstName:NanXiong; lastName:Qimu; socialSecurityNumber:2016782340; earning:3040.00
 * firstName:Rong; lastName:Huang; socialSecurityNumber:2018768901; earning:9297.23
 * firstName:Guo; lastName:Yang; socialSecurityNumber:2017672347; earning:4678.13
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-05-26
 */

abstract class Employee implements Comparable<Employee>
{
    String firstName;
    String lastName;
    long socialSecurityNumber;

    public Employee(String firstName, String lastName, long socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    //模板设计模式
    @Override
    public int compareTo(Employee employee) {
        if (Math.abs(this.earning() - employee.earning()) < 0.000000000001)
        {
            return 0;
        } else if (this.earning() > employee.earning())
        {
            return 1;
        }
        return -1;
    }

    //为什么声明为静态方法而不是属性？因为静态绑定！
    abstract double earning();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat( "0.00");

        return "firstName:" + getFirstName() + "; " + "lastName:" + getLastName() +"; " +  "socialSecurityNumber:" + getSocialSecurityNumber() + "; " +  "earning:" + df.format(earning());
    }
}

class SalaridEmployee extends Employee
{
    double weeklySalary;

    public SalaridEmployee(String firstName, String lastName, long socialSecurityNumber, double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber);
        setWeeklySalary(weeklySalary);
    }

    @Override
    double earning() {
        return getWeeklySalary() * 4;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }
}

class HourlyEmployee extends Employee
{
    double wage;
    double hours;

    public HourlyEmployee(String firstName, String lastName, long socialSecurityNumber, double wage, double hours) {
        super(firstName, lastName, socialSecurityNumber);
        setWage(wage);
        setHours(hours);
    }

    @Override
    double earning() {
        return getWage() * getHours();
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
}
class CommissionEmployee extends Employee
{
    double grossSales;
    double commissionRate;

    public CommissionEmployee(String firstName, String lastName, long socialSecurityNumber, double grossSales, double commissionRate) {
        super(firstName, lastName, socialSecurityNumber);
        setGrossSales(grossSales);
        setCommisionRate(commissionRate);
    }

    @Override
    double earning() {
        return getCommissionRate() * getGrossSales();
    }

    public double getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommisionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
}

class basePlusCommisonEmployee extends CommissionEmployee {
    double baseSalary;

    public basePlusCommisonEmployee(String firstName, String lastName, long socialSecurityNumber, double grossSales, double commissionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
        setBaseSalary(baseSalary);
    }

    @Override
    double earning() {
        return super.earning() + getBaseSalary();
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}

public class EmployeeManage
{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //接送雇员数 吸收换行
        int employeeNumber = scanner.nextInt();
        Employee[] employees = new Employee[employeeNumber];

        for (int i = 0; i < employeeNumber; i++)
        {
            //接收雇员类型 吸收空格
            int type = scanner.nextInt();

            if (type == 0)
            {
                //接受雇员属性 不吸收换行
                employees[i] = new SalaridEmployee(scanner.next(), scanner.next(), scanner.nextLong(), scanner.nextDouble());
            } else if (type == 1)
            {
                employees[i] = new HourlyEmployee(scanner.next(), scanner.next(), scanner.nextLong(), scanner.nextDouble(), scanner.nextDouble());
            } else if (type == 2)
            {
                employees[i] = new CommissionEmployee(scanner.next(), scanner.next(), scanner.nextLong(), scanner.nextDouble(), scanner.nextDouble());
            } else if (type == 3)
            {
                employees[i] = new basePlusCommisonEmployee(scanner.next(), scanner.next(), scanner.nextLong(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
            }
            //吸收换行
            scanner.nextLine();
        }

        //接收搜索条件个数 吸收换行
        int search = scanner.nextInt();

        for (int j = 0; j < search; j++)
        {
            //接受查询类型 吸收空格
            int type = scanner.nextInt();
            //满足条件的雇员数
            int num = 0;
            //存放输出的雇员信息
            Employee[] newEmployees = new Employee[employeeNumber];

            if (type == 0)
            {
                String firstName = scanner.next();
                //对所有雇员搜索
                for (int k = 0; k < employeeNumber; k++)
                {
                    if (employees[k].firstName.equals(firstName))
                    {
                        newEmployees[num++] = employees[k];
                    }
                }
                //输入用scanner.next时才吸收换行
                scanner.nextLine();

            } else if (type == 1)
            {
                long number = scanner.nextLong();
                for (int k = 0; k < employeeNumber; k++)
                {
                    if (employees[k].socialSecurityNumber == number)
                    {
                        newEmployees[num++] = employees[k];
                    }
                }
            }

            newEmployees = Arrays.copyOf(newEmployees, num);
            Arrays.sort(newEmployees);

            for (int n = 0; n < num; n++)
            {
                System.out.println(newEmployees[n].toString());
            }
        }
        scanner.close();
    }
}
