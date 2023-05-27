package test.application.arithmetic;

import application.arithmetic.ArithExpression;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class ArithExpressionTest {
    public static void main(String[] args) {
        // 中缀表达式，整数；算术运算和位运算，双目，单字符运算，没有正负号，忽略空格。
        String infix = "125*4 + 3 * (15 - 5) - 10";
        new ArithExpression(infix);
    }
}
