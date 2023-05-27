package application.arithmetic;

import adt.Stack;
import linked.stack.LinkedStack;

/**
 * 算术表达式计算类
 * <p>
 * 将一个中缀表达式的算术字符串当作参数传入构造方法{@code ArithExpression()}，可以立即计算出数值。
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class ArithExpression {
    // 运算符集合
    private static final Operators operators;

    static {
        operators = new Operators();
    }

    /**
     * 由infix中缀表达式构造
     * @param infix 中缀表达式
     */
    public ArithExpression(String infix){
        StringBuffer postfix = toPostfix(infix); // 转换成后缀表达式
        System.out.println("postfix=\"" + postfix + "\"");
        System.out.println("value="+ toValue(postfix));
    }

    /**
     * 将中缀表达式转换为后缀表达式
     * @param infix 中缀表达式
     * @return 后缀表达式
     */
    public StringBuffer toPostfix(String infix){
        // 运算符栈
        Stack<String> stack = new LinkedStack<>();
        // 后缀表达式字符串
        StringBuffer postfix = new StringBuffer(infix.length() * 2);
        int i = 0;
        while (i < infix.length()){
            char ch = infix.charAt(i);
            // 数字，添加到后缀表达式。没有正负号。
             if (ch >= '0' && ch <='9'){
                while (i < infix.length() && (ch = infix.charAt(i)) >= '0' && ch <= '9'){
                    postfix.append(ch);
                    i++;
                }
                // 添加空格作分隔符
                postfix.append(" ");
            }
            else {
                switch (ch) {
                    // 跳过空格
                    case ' ' -> i++;
                    // 左括号，入栈。
                    case '(' -> {
                        stack.push(ch + "");
                        i++;
                    }
                    // 右括号，出栈，直到出栈运算符为左括号
                    case ')' -> {
                        String out = "";
                        while ((out = stack.pop()) != null && !out.equals("(")) // 这里已经把左括号悄悄出栈了。天才设计！！！
                            postfix.append(out);
                        i++;
                    }
                    default -> {
                        /*
                        遇到运算符，将ch运算符的优先级与栈顶运算符优先级比较大小，若栈顶运算符优先级高，则出栈。
                        使用Comparator<T>比较器的compare()方法比较对象大小。
                        注意compare(a,b)方法，当b优先级大于a时返回1.
                         */
                        while (!stack.isEmpty() && !stack.peek().equals("(") && operators.compare(ch + "", stack.peek()) > 0)
                            postfix.append(stack.pop());
                        stack.push(ch + "");
                        i++;
                    }
                }
            }
        }
        while (!stack.isEmpty())
            postfix.append(stack.pop());
        return postfix;
    }

    /**
     * 计算后缀表达式的值
     * @param postfix 后缀表达式
     * @return 计算值
     */
    public int toValue(StringBuffer postfix){
        // 操作数栈，链式栈
        Stack<Integer> stack = new LinkedStack<>();
        int value;
        // 检查后缀表达式的字符
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if (ch >= '0' && ch <= '9'){
                value = 0;
                // 将整数字符串转换为整数值，没有符号，以空格结束。
                while (ch >= '0' && ch <= '9'){
                    value = value*10 + ch-'0';
                    ch = postfix.charAt(++i);
                }
                stack.push(value); // 入栈
            }
            else {
                if (ch != ' ') { // 在toPostfix方法中约定好了，两个操作数之间有空格。
                    // 出栈两个操作数
                    int y = stack.pop();
                    int x = stack.pop();
                    // 调用operate方法计算
                    value = operators.operate(x, y, ch+"");
                    // 显示计算过程
                    System.out.println(x + (ch+"") + y + "=" + value + ", ");
                    // 计算结果入栈
                    stack.push(value);
                }
            }
        }
        return stack.pop(); // 返回计算结果。
    }

}
