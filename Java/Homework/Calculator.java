package Homework;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 计算器
 *
 * 一、图形界面的设计和基础功能：
 *
 * 算术符号只包括+-*÷，数字包括0-9和π。其他符号包括（）、. 、BC（清除最后一个输入的字符）、AC（清空所有输入）。
 * 支持输入浮点数运算和输入负数运算。一共分为21个键。
 *
 * 二、异常提示：
 *
 * 1.首字母输入
 * 1).若输入了 + * ÷ = . ) 之一，提示首字符不能输入+ * ÷ = . )，并清除最后输入的一个字符。注意，-可以输入，代表负数。
 *
 * 2.输入(
 * 1).若前面为0-9的数字，提示数字与 ( 之间必须存在运算符，并清除。
 * 2).若前面为 )，提示 ) 与 ( 之间必须存在运算符，并清除。
 * 3).若前面为 .，提示 .与 (之间必须存在数字，并清除。
 *
 * 3.输入)
 * 1).若 ) 比 ( 多了，提示多输入了一个右括号，并清除。
 * 2).若前面为 ( ，提示一对()之间至少有一个数字，并清除。
 * 3).若前面为运算符 + - * ÷ .，提示 ) 前必须存在一个数字，并清除。
 *
 * 4.输入.
 * 1).若前面为非0-9的数字，提示小数点前必须存在一个数字，并清除。
 * 2).若前面存在了小数部分，如3.4，提示小数部分存在两个小数点，不合法，并清除。
 * 3).若刚刚进行=运算，提示不能直接对上一次的结果进行修改，并清除。
 *
 * 5.输入运算符 + * ÷
 * 1).若前面为 + - * ÷ . (之一，提示运算符输入不合法，并清除。
 *
 * 6.输入运算符 -
 * 1).若前一个是 +，提示直接输入 -并清除。
 * 2).若前一个是 -，提示直接输入 +，并清除。注意，*-或÷-合法。
 * 3).若前一个是 .，提示运算符输入不合法，并清除。
 *
 * 7.输入0-9的数字或π
 * 1).若前面为 )，提示 ) 与数字之间必须存在运算符，并清除。
 * 2).若前面为π，提示 π 与数字之间必须存在运算符，并清除（因为之前要输入的就是π，可以直接在后面加数字就失去了π的意义了）。
 * 3).若前面为0，且是在整数部分，提示数字的整数部分不能为0x样式，并清除。
 * 4).若刚刚进行=运算，提示不能直接对上一次的结果进行修改并，并清除。
 *
 * 8.输入非0-9且非AC
 * 1).若前面是. ，提示小数部分未补全，并清除。
 *
 * 9.输入π
 * 1).若前面是0-9的纯数字，提示数字与 π 之间必须存在运算符，并清除。
 * 2).若前面是) ，提示 ) 与 π 之间必须存在运算符，并清除。
 * 3).若前面是小数点，提示小数点后不允许再输入π，并清除。
 * 4).若刚刚进行=运算，提示不能直接对上一次的结果进行修改，并清除。
 *
 * 10.输入BC
 * 1).若刚刚进行=运算，提示不能直接对上一次的结果进行修改，并清除。
 *
 * 12.输入=
 * 1).若左右括号不等，提示右括号未补全，并清除。（只可能右括号少于左括号，若右括号多于左括号，在3)时就会抛出异常并提示）。
 * 2).若前面是 + - * ÷,提示最后一位不能为运算符，并清除。
 * 3).若前面是 .，提示最后小数点未补全，并清除。
 *
 * 13.若计算过程中出现了分母为0的情况，提示运算过程中出现了分母为0的情况，并全清空。
 *
 * 14.虽然可以实现-1*-1 -1÷-1的算法，但是不推荐这样使用。
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-06-08
 */
public class Calculator extends JFrame implements ActionListener {
    //二十一个键位
    private final String[] KEYBOARD= {"7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "π", "(", "÷",
            ")", "BC", "AC", "=",
            ".",
            };

    //二十一个键位对应的button
    private final JButton Buttons[] = new JButton[KEYBOARD.length];
    //得到输入或输出的文本框 取七位小数
    private final JTextArea ResultText = new JTextArea("0.0000000");
    private final JLabel ResultLabel = new JLabel("当前结果");
    private final JPanel ResultPanel = new JPanel();
    private final JScrollPane ResultScroll =new JScrollPane(ResultText);
    //历史记录
    private final JTextArea RecordText = new JTextArea();//
    private final JLabel RecordLabel = new JLabel("历史记录");
    private final JPanel RecordPanel = new JPanel();
    private final JScrollPane RecordScroll =new JScrollPane(RecordText);

    //存储输入表达式
    private StringBuilder input = new StringBuilder();

    //输入长度-1
    int inputLength = -1;

    //左括号右括号数量，检查括号形式
    int leftBracket = 0;
    int rightBracket = 0;

    //因为转换到input pi变成了3.1415926 不再具有pi的形式 所以当判断数字是否是pi时有必要在这里加个flag
    //当上一个输入为pi时，BC会直接清除3.1415926
    boolean lastPi = false;

    //检查某数字整数部分是不是大于一位且第一位为0
    //输入π/=/.都会使前面有小数点 此外都会清除状态
    boolean afterPoint = false;
    //前面有0且是小数点前
    boolean hasZero = false;

    //检查是不是按=得到的结果
    boolean afterOp = false;

    public Calculator() {
        //得到一个框
        super("计算器");
        setLayout(null);

        //设置当前结果框
        //文本框大小
        ResultText.setBounds(20,20,255,100);
        //文本框内容右对齐
        ResultText.setAlignmentX(RIGHT_ALIGNMENT);
        //文本框不允许修改结果
        ResultText.setEditable(false);
        //其他配置
        ResultText.setLineWrap(true);
        ResultText.setWrapStyleWord(true);
        ResultText.setSelectedTextColor(Color.RED);
        //标签位置
        ResultLabel.setBounds(100, 0, 180, 20);
        //窗口位置
        ResultPanel.setBounds(20,20,255,100);
        ResultPanel.setLayout(new GridLayout());
        //垂直滚动条
        ResultScroll.setViewportView(ResultText);
        ResultScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ResultPanel.add(ResultScroll);
        //添加面板
        add(ResultPanel);
        //添加标签
        add(ResultLabel);

        //历史记录框
        RecordText.setBounds(300, 20, 270,394);
        RecordText.setAlignmentX(LEFT_ALIGNMENT);
        RecordText.setEditable(false);
        RecordText.setLineWrap(true);
        RecordText.setWrapStyleWord(true);
        RecordText.setSelectedTextColor(Color.blue);
        RecordLabel.setBounds(400, 0, 180, 20);
        RecordPanel.setBounds(300, 20, 270,394);
        RecordPanel.setLayout(new GridLayout());
        RecordScroll.setViewportView(RecordText);
        RecordScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        RecordPanel.add(RecordScroll);
        add(RecordPanel);
        add(RecordLabel);

        //设置按钮位置
        int x = 20, y = 150;
        for (int i = 0; i < KEYBOARD.length; i++) {
            //初始化按钮
            Buttons[i] = new JButton();
            Buttons[i].setText(KEYBOARD[i]);
            Buttons[i].setBounds(x, y, 60, 40);
            //让按钮是4*n的
            if (x < 215) {
                x += 65;
            } else {
                x = 20;
                y += 45;
            }
            //加入按钮
            add(Buttons[i]);
        }
        //对按钮加入监听器
        for (int i = 0; i < KEYBOARD.length; i++) {
            Buttons[i].addActionListener(this);
        }
        //基本设置
        setResizable(false);
        setBounds(500, 200, 600, 460);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    /**
     * 事件处理
     * @param e 绑定的事件
     */
    public void actionPerformed(ActionEvent e) {
        try {
            //得到当前输入的字符
            String label = e.getActionCommand();
            //首字母输入
            if (inputLength == -1) {
                //首字母输入不规范 抛出异常
                if ("+*÷)=.".contains(label)) {
                    throw new BackException("首字符不能输入+ * ÷ = . )，将进行退格");
                    //让输入BC和AC无效
                } else if (!label.equals("BC") && !label.equals("AC")){
                    //输入pi的特殊处理
                    switch (label) {
                        case "π":
                            input.append(3.1415926);
                            //注意 此时长度加了9
                            inputLength += 9;
                            //标志上一个输入的是pi
                            lastPi = true;
                            //标志前面就是小数
                            afterPoint = true;
                            ResultText.setText(String.valueOf(input));
                            break;
                        case "(":
                            //输出左括号 标记左括号数+1
                            leftBracket++;
                            input.append("(");
                            inputLength++;
                            ResultText.setText(String.valueOf(input));
                            break;
                        case "0":
                            hasZero = true;
                            input.append("0");
                            inputLength++;
                            ResultText.setText(String.valueOf(input));
                            break;
                        default:
                            input.append(label);
                            inputLength++;
                            ResultText.setText(String.valueOf(input));
                            break;
                    }
                }
            } else {
                //得到之前输入的最后一个字符
                char last = input.charAt(inputLength);
                //如果输入为左括号
                if (label.equals("(")) {
                    //如果前面是数字
                    if ("0123456789".indexOf(last) >= 0) {
                        throw new BackException("数字与(之间必须存在运算符，将进行退格");
                    }
                    //如果前面是)
                    if (')' == last) {
                        throw new BackException(")与(之间必须存在运算符，将进行退格");
                    }
                    if (last == '.') {
                        throw new BackException(".与(之间必须存在数字，将进行退格");
                    }
                    leftBracket++;
                }

                //如果输入为右括号
                if (label.equals(")")) {
                    //右括号多了一个
                    if (leftBracket < rightBracket + 1) {
                        throw new BackException("多输入了一个右括号，将进行退格");
                    }
                    //右括号前面就是左括号
                    if (last == '(') {
                        throw new BackException("一对()之间至少有一个数字，将进行退格");
                    }
                    //前面是运算符
                    if ("+-*÷.".indexOf(last) >= 0) {
                        throw new BackException(")前必须存在一个数字，将进行退格");
                    }
                    rightBracket++;
                }

                //输入.
                if (".".equals(label)) {
                    //前面没有数字
                    if ("0123456789".indexOf(last) < 0) {
                        throw new BackException("小数点前必须存在一个数字，将进行退格");
                    }
                    if (afterPoint)
                    {
                        throw new BackException("小数部分存在两个小数点，不合法，将进行退格");
                    }
                    afterPoint = true;
                    hasZero = false;
                }

                //如果连续输入两个运算符
                //注意 *-或÷-合法
                if ("+*÷".contains(label) && "+-*÷(.".indexOf(last) >= 0) {
                    throw new BackException("运算符输入不合法，将进行退格");
                }

                //若输入三个-或者在开头就输入了两个-或者输入的是+-
                if ("-".equals(label))
                {
                    if (last == '-'){
                        throw new BackException("请直接输入 +，将进行退格");
                    }
                    if (last == '+') {
                        throw new BackException("请直接输入 -，将进行退格");
                    }
                    if (last == '.')
                    {
                        throw new BackException("运算符输入不合法，将进行退格");
                    }
                }

                //输入0
                if (label.equals("0")) {
                    //在整数部分且是第一个0 标志
                    if ("0123456789".indexOf(last) < 0 && !afterPoint) {
                        hasZero = true;
                    }
                }

                //如果数字字符前直接加右括号或pi
                if ("0123456789π".contains(label)) {
                    if (last == ')') {
                        throw new BackException(")与数字之间必须存在运算符，将进行退格");
                    }
                    if (lastPi) {
                        throw new BackException("π与数字之间必须存在运算符，将进行退格");
                    }
                    if (hasZero && !afterPoint && last == '0')
                    {
                        throw new BackException("数字的整数部分不能为0x样式，将进行退格");
                    }
                }

                //输入pi
                if (label.equals("π")) {
                    if ("0123456789".indexOf(last) >= 0) {
                        throw new BackException("数字与π之间必须存在运算符，将进行退格");
                    }
                    if (last == ')') {
                        throw new BackException(")与π之间必须存在运算符，将进行退格");
                    }
                    if (afterPoint)
                    {
                        throw new BackException("小数点后不允许再输入π，将进行退格");
                    }
                    afterPoint = true;
                    lastPi = true;
                    input.append(3.1415926);
                    //注意 此时长度加了9
                    inputLength += 9;
                    ResultText.setText(String.valueOf(input));
                }

                //退格
                if (label.equals("BC")) {
                    //检验是否对结果直接退格
                    if (afterOp) {
                        throw new BackException("不能直接对上一次的结果进行修改，将进行退格");
                    }
                    //对在小数点后这个状态变量的处理
                    if (last == '.')
                    {
                        //如果删除了小数点，说明回到了整数部分
                        afterPoint = false;
                    } else if (".+-*÷".indexOf(last) >= 0 && input.charAt(inputLength - 1) == '0' && (inputLength == 1 || "+-*÷".indexOf(input.charAt(inputLength - 2)) >= 0))
                    {
                                           //此时在这个位置↓
                        //回退时可能会退到[+-*÷][啥也没有]?0?[.0-9][+-*÷]这种情况 只可能发生在 小数点前 且 上一个字符为0 且 这是第一个字符或前一个是+-*÷
                        hasZero = true;
                    }
                    //其他特殊退格
                    if (lastPi) {
                        input.delete(inputLength - 8, inputLength + 1);
                        inputLength -= 9;
                        ResultText.setText(input.toString());
                        //清除标记
                        afterPoint = false;
                        lastPi = false;
                    } else if (last == '(')
                    {
                        input.deleteCharAt(inputLength);
                        inputLength--;
                        ResultText.setText(input.toString());
                        //左括号少一个
                        leftBracket--;
                    } else if (last == ')') {
                        input.deleteCharAt(inputLength);
                        inputLength--;
                        ResultText.setText(input.toString());
                        //右括号少一个
                        rightBracket--;
                    } else  {
                        input.deleteCharAt(inputLength);
                        inputLength--;
                        ResultText.setText(input.toString());
                    }
                }

                //全清
                if (label.equals("AC")) {
                    input = new StringBuilder();
                    inputLength = -1;
                    ResultText.setText("0.0000000");
                    leftBracket = 0;
                    rightBracket = 0;
                }

                //输入=
                if (label.equals("=")) {
                    if (leftBracket != rightBracket) {
                        throw new BackException("右括号未补全，将进行退格");
                    } else if (last == '+' || last == '-' || last == '*' || last == '÷') {
                        throw new BackException("最后一位不能为运算符，将进行退格");
                    } else if (last == '.')
                    {
                        //除了数字字符 其他前面不允许存在.
                        throw new BackException("小数部分未补全，将进行退格");
                    } else {
                        //原算式
                        String equation = input.toString();
                        //得到结果(input)
                        String[] s = infixToPostfix(input.toString());
                        String result = getResult(s);
                        input = new StringBuilder(result);
                        inputLength = input.length() - 1;
                        ResultText.setText(result);
                        //这是输出的结果 标志为true
                        afterOp = true;
                        RecordText.setText(RecordText.getText()+ equation + " = " + ResultText.getText()+"\n");
                    }
                }

                //如果有这些操作符 说明下次输入不在小数点后了
                if ("+-*÷".contains(label) || "AC".equals(label)) {
                    //清除标记
                    afterPoint = false;
                    hasZero = false;
                }

                //检验是否对结果直接修改了 没有的话 清除标志
                if (afterOp) {
                    if ("0123456789π.".contains(label)) {
                        throw new BackException("不能直接对上一次的结果进行修改，将进行退格");
                    }
                    //输入了操作符或者AC 标志清除
                    if ("+-*÷".contains(label) || "AC".equals(label)) {
                        afterOp = false;
                    }
                }

                //如果是正常运算中出现的操作 (除了输入pi)
                if (!label.equals("=") && !label.equals("AC") && !label.equals("BC") && !label.equals("π")) {
                    input.append(label);
                    inputLength++;
                    //清除标记 下一个输入的前一个输入不是pi了
                    lastPi = false;
                    ResultText.setText(String.valueOf(input));
                }
            }
        } catch (BackException ex) {
            Panel jPanel = new Panel();
            JOptionPane.showMessageDialog(jPanel, ex.getMsg(), "输入错误", JOptionPane.WARNING_MESSAGE);
        } catch (ACException ex) {
            Panel jPanel = new Panel();
            input = new StringBuilder();
            inputLength = -1;
            ResultText.setText("0.0000000");
            JOptionPane.showMessageDialog(jPanel, ex.getMsg(), "输入错误", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * 将中缀表达式转换为后缀表达式
     *
     * @param str 中缀表达式
     * @return 后缀表达式
     */
    private String[] infixToPostfix(String str) {
        //存放数值或运算符
        StringBuilder s;
        //运算符栈
        char[] opStack = new char[50];
        //后缀表达式栈
        String[] postFix = new String[50];
        //运算符栈的栈顶指针
        int top = -1;
        //后缀表达式的栈顶指针
        int j = 0;
        //记录负号 处理输入为负数的情况
        boolean minus = false;

        //遍历中缀表达式
        for (int i = 0; i < str.length(); i++)
        {
            char last = str.charAt(i);
            //截取第一个符号 特殊处理
            if (i == 0)
            {
                //看看是不是-
                if (last == '-')
                {
                    i = 1;
                    s = new StringBuilder();
                    i = getNumbers(i, str, s) - 1;
                    //如果-后面有数字字符串 就将数字字符串取反后放入后缀表达式
                    if (s.length() != 0) {
                        postFix[j] = "-" + s;
                        j++;
                    } else {
                        //说明-后面是（
                        //更改str为-1*(.. 从头开始循环 如 -(3+2) 转换为 -1*(3+2) 转化为上面那个if的情况了
                        str = str.substring(1);
                        str = "-1*" + str;
                        i = -1;
                    }
                    continue;
                }
            }
            if ("0123456789.".indexOf(last) >= 0)
            {
                s = new StringBuilder();
                //遇到数字字符 取整体 保留循环中i达到的数
                i = getNumbers(i, str, s);
                //跳出上方for循环时 1.此时i超出字符串长度 i-- 下次外层for循环i++ 扔跳出循环 2.此时i处非数字 i--后i++ 仍判断此处字符
                i--;
                last = str.charAt(i);
                //如果前面的-代表的是负号而不是减号 取反后入栈
                if (minus) {
                    postFix[j] = "-" + s;
                } else {
                    //否则就直接入栈
                    postFix[j] = s.toString();
                }
                //后缀表达式栈顶+1
                j++;
                //下次循环前面不是负数了
                minus = false;
            } else if ("(".indexOf(last) >= 0) {
                top++;
                opStack[top] = last;
                //下次循环前面不是负数了
                minus = false;
            } else if (")".indexOf(last) >= 0) {
                //遇到右括号，运算符栈栈顶元素循环出栈，直到遇到左括号为止
                for (; ; )
                {
                    //运算符栈栈顶元素不是左括号 栈顶元素出栈 入后缀表达式
                    if (opStack[top] != '(') {
                        postFix[j] = opStack[top] + "";
                        //后缀表达式栈顶+1
                        j++;
                        //运算符栈顶-1
                        top--;
                    } else {
                        //找到栈顶元素是左括号 出栈  不入后缀表达式
                        //运算符栈顶-1
                        top--;
                        break;
                    }
                }
                //下次循环前面不是负数了
                minus = false;
            }
            //遇到高优先级运算符
            if ("*÷".indexOf(last) >= 0)
            {
                //若运算符栈为空则直接入栈
                if (top == -1) {
                    top++;
                    opStack[top] = last;
                } else {
                    //栈不为空，把栈中弹出的元素入后缀表达式，直到栈顶元素优先级小于x或者栈为空
                    if ("*÷".indexOf(opStack[top]) >= 0) {
                        //栈顶元素也为高优先级运算符 出栈进入后缀表达式
                        postFix[j] = opStack[top] + "";
                        j++;
                        //当前元素入运算符栈
                        opStack[top] = last;
                    } else if ("(".indexOf(opStack[top]) >= 0) {
                        //栈顶元素为左括号，当前运算符入栈
                        top++;
                        opStack[top] = last;
                    } else if ("+-".indexOf(opStack[top]) >= 0) {
                        //遇到低优先级运算符，当前运算符入栈
                        top++;
                        //当前元素入运算符栈
                        opStack[top] = last;
                    }
                }
                //下次循环前面不是负数了
                minus = false;
            } else if ("+-".indexOf(last) >= 0) {

                //若前面 不是 0-9或) 则为负号 否则为减号
                if ((i > 0 && last == '-' && !(str.charAt(i - 1) == ')' || "0123456789".indexOf(str.charAt(i - 1)) >= 0)))
                {
                    minus = true;
                } else {
                    minus = false;
                    if (top == -1) {
                        top++;
                        opStack[top] = last;
                    } else {
                        if ("*÷".indexOf(opStack[top]) >= 0) {
                            //栈顶元素也为高优先级运算符 出栈进入后缀表达式
                            postFix[j] = opStack[top] + "";
                            j++;
                            //当前元素入运算符栈
                            opStack[top] = last;
                        } else if ("(".indexOf(opStack[top]) >= 0) {
                            //栈顶元素为左括号，当前运算符入栈
                            top++;
                            opStack[top] = last;
                        } else if ("+-".indexOf(last) >= 0) {
                            // 遇到低优先级运算符 栈顶元素出栈进入后缀表达式
                            postFix[j] = opStack[top] + "";
                            j++;
                            //当前元素入运算符栈
                            opStack[top] = last;
                        }
                    }
                }
            }
        }
        //遍历结束后将运算符栈清空 依次出栈进入后缀表达式
        while (top != -1) {
            postFix[j] = opStack[top] + "";
            j++;
            top--;
        }
        return postFix;
    }

    /**
     * 处理后缀表达式，并返回最终结果
     *
     * @param str 后缀表达式
     * @return 最终结果
     */
    public String getResult(String[] str) {
        String[] result = new String[50];
        //规范输出格式
        DecimalFormat df = new DecimalFormat("0.0000000");
        //后缀表达式的栈顶指针
        int top = -1;
        for (int i = 0; str[i] != null; i++) {
            String s = str[i];
            //遇到数字即入栈
            if (!"+-*÷".contains(s)) {
                top++;
                result[top] = s;
            }
            //遇到运算符字符，将栈顶两个元素出栈计算并将结果返回栈顶
            if ("+-*÷".contains(s))
            {
                double x, y, n;
                x = Double.parseDouble(result[top]);
                top--;
                y = Double.parseDouble(result[top]);
                top--;

                //减法
                if ("-".contains(s)) {
                    n = y - x;
                    top++;
                    result[top] = df.format(n);
                }
                //加法
                if ("+".contains(s)) {
                    n = y + x;
                    top++;
                    result[top] = df.format(n);
                }
                //乘法
                if ("*".contains(s)) {
                    n = y * x;
                    top++;
                    result[top] = df.format(n);
                }
                //除法
                if ("÷".contains(s)) {
                    //被除数不允许为0
                    if (x == 0)
                    {
                        throw new ACException("运算过程中出现了分母为0的情况，将清空输入");
                    } else {
                        n = y / x;
                        top++;
                        result[top] = df.format(n);
                    }
                }
            }
        }
        writeToFile(result[top]);

        //返回栈顶 即最终结果
        return result[top];
    }

    /**
     * 在目标字符串的起始查找最长的连续数字
     *
     * @param i 字符起始
     * @param str 被查找的字符串
     * @param s 输出的数字字符串
     * @return 将s输出
     */
    public int getNumbers(int i, String str, StringBuilder s)
    {
        for (; i < str.length() && "0123456789.".indexOf(str.charAt(i)) >= 0; i++) {
            s.append(str.charAt(i));
        }
        return i;
    }

    /**
     * 把结果写入文件
     *
     * @param s 输出文件名
     */
    private void writeToFile(String s) {
        //向文件中输出 且每次输出不清除之前的
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output", true))) {
          bufferedWriter.write("结果：" + s);
          bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg)
    {
        Calculator c = new Calculator();
    }
}

/**
 * 发生该异常 只需退格
 */
class BackException extends RuntimeException
{
    String msg;

    public BackException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

/**
 * 发生该异常 需要清空
 */
class ACException extends RuntimeException
{
    String msg;

    public ACException(String msg) {
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
