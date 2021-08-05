package Homework;

import java.text.DecimalFormat;
import java.util.Scanner;


/**
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-05-11
 */
class Student {

    String studentNumber;
    String studentName;
    int markForMaths = -1;
    int markForEnglish = -1;
    int markForScience = -1;

    public Student(String number, String name) {
        this.studentNumber = number;
        this.studentName = name;
    }

    void enterMarks(int markForMaths, int markForEnglish, int markForScience)
    {
        this.markForMaths = markForMaths;
        this.markForEnglish = markForEnglish;
        this.markForScience = markForScience;
    }

    int getMathsMark()
    {
        return this.markForMaths;
    }

    int getEnglishMark()
    {
        return this.markForEnglish;
    }

    int getScienceMark()
    {
        return this.markForScience;
    }

    String calculateAverage()
    {
        double average = (this.getMathsMark() + this.getScienceMark() + this.getEnglishMark())/3.0;
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(average);
    }

    @Override
    public String toString() {
        return "Student ID:" + studentNumber + '\n' +
                "Name:" + studentName + '\n' +
                "Math:" + markForMaths + '\n' +
                "English:" + markForEnglish + '\n' +
                "Science:" + markForScience + '\n' +
                "Average Score:" + calculateAverage();
    }
}

class StudentList
{
    Student[] list;
    int total = 0;

    StudentList(int length)
    {
        this.list = new Student[length];
    }

    boolean add(Student stu)
    {
        if (total == this.list.length)
        {
            return false;
        } else {
            this.list[total] = stu;
            total++;
            return true;
        }
    }

    boolean remove(String number)
    {
        int size = this.total;
        while (size >= 1 && !(number.equals(this.list[size - 1].studentNumber)))
        {
            size--;
        }
        this.list[size - 1] = null;
        if (this.list[size - 1] == null)
        {
            if(size < total) {
                if (total - (size - 1) >= 0)
                    System.arraycopy(this.list, size - 1 + 1, this.list, size - 1, total - (size - 1));
            }
            total --;
            return true;
        } else {
            return false;
        }
    }

    boolean updateItem(String number, int math, int english, int science)
    {
        Student student = this.getItem(number);
        student.enterMarks(math, english, science);
        boolean b = this.add(student);
        this.total--;
        return b;
    }

    boolean isEmpty()
    {
        return this.total == 0;
    }

    int getTotal()
    {
        return this.total;
    }

    Student getItem(String number)
    {
        int size = this.total;
        while (size >= 1 && !(number.equals(this.list[size - 1].studentNumber)))
        {
            size--;
        }
        if (size == 0)
        {
            return null;
        }
        return this.list[size - 1];
    }
}

public class StudentManage {

    void addStudent(StudentList list, String number, String name, int math, int english, int science) {
        if (list.getItem(number) == null) {
            Student student = new Student(number, name);
            student.enterMarks(math, english, science);
            list.add(student);
            if (list.add(student)) {
                System.out.println("Add success");
            }
        } else {
            System.out.println("Students already exist");
        }
    }

    void removeStudent(StudentList list, String number) {
        if (list.getItem(number) == null) {
            System.out.println("Students do not exist");
        } else {
            if (list.remove(number)) {
                System.out.println("Delete success");
            }
        }
    }

    void updateStudent(StudentList list, String number, int math, int english, int science) {
        if (list.getItem(number) == null) {
            System.out.println("Students do not exist");
        } else {
            if (list.updateItem(number, math, english, science)) {
                System.out.println("Update success");
            }
        }
    }

    void getInfo(StudentList list, String number) {
        Student student = list.getItem(number);
        if (student == null) {
            System.out.println("Students do not exist");
        } else {
            System.out.println("Student ID:" + student.studentNumber + "\n" +
                    "Name:" + student.studentName + "\n" +
                    "Average Score:" + student.calculateAverage());
        }

    }

    boolean empty(StudentList list) {
        return list.isEmpty();
    }

    int printfTotal(StudentList list) {
        return list.getTotal();
    }

    public static void studentManage(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManage studentManage = new StudentManage();
        int num = scanner.nextInt();
        StudentList studentLists = new StudentList(num);
        for (int i = 0; i < num; i++)
        {
            try {
                int op = scanner.nextInt();

                if (op == 1)
                {
                    String number = scanner.next();
                    String name = scanner.next();
                    int math = Integer.parseInt(scanner.next());
                    int english = Integer.parseInt(scanner.next());
                    int science = Integer.parseInt(scanner.next());

                    if (!((number.matches("\\p{Digit}{10}")) && number.charAt(0) == '2' &&  number.charAt(1) == '0'))
                    {
                        throw new StudentNumberException();
                    }

                    if (!((math <= 100 && math >= 0) && (english <= 100 && english >= 0) && (science <= 100 && science >= 0))) {
                        throw new ScoreException();
                    }

                    studentManage.addStudent(studentLists, number, name, math, english, science);
                    continue;
                }

                if (op == 2)
                {
                    String number = scanner.next();
                    studentManage.removeStudent(studentLists, number);
                    continue;
                }

                if (op == 3)
                {
                    String number = scanner.next();
                    int math = Integer.parseInt(scanner.next());
                    int english = Integer.parseInt(scanner.next());
                    int science = Integer.parseInt(scanner.next());

                    if (!((number.matches("\\p{Digit}{10}")) && number.charAt(0) == '2' &&  number.charAt(1) == '0'))
                    {
                        throw new StudentNumberException();
                    }

                    if (!((math <= 100 && math >= 0) && (english <= 100 && english >= 0) && (science <= 100 && science >= 0))) {
                        throw new ScoreException();
                    }

                    studentManage.updateStudent(studentLists, number, math, english, science);
                    continue;
                }

                if (op == 4)
                {
                    String number = scanner.next();
                    studentManage.getInfo(studentLists, number);
                    continue;
                }

                if (op == 5)
                {
                    if(studentManage.empty(studentLists))
                    {
                        System.out.println("List is empty");
                    } else {
                        System.out.println("List is not empty");
                    }
                    continue;
                }

                if (op == 6)
                {
                    System.out.println(studentManage.printfTotal(studentLists));
                }
            }
            catch (ScoreException scoreException)
            {
                System.out.println(scoreException.getErrorMessage());
            }
            catch (StudentNumberException studentNumberException)
            {
                System.out.println(studentNumberException.getErrorMessage());
            }
        }
        scanner.close();
    }
}

class ScoreException extends Exception
{
    private String errorMessage;

    ScoreException()
    {
        setErrorMessage("Illegal score format");
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

class StudentNumberException extends Exception
{
    private String errorMessage;

    StudentNumberException()
    {
        setErrorMessage("Illegal number format");
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
