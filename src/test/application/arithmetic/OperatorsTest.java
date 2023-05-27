package test.application.arithmetic;

import application.arithmetic.Operators;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class OperatorsTest {
    public static void main(String[] args) {
        Operators operators = new Operators();
        System.out.println(operators.operate(3, 2, "+"));
    }
}
