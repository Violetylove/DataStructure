package application.arithmetic;

import sequential.list.SeqList;

import java.util.Comparator;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class Operators implements Comparator<String> {

    /*
    运算符集合。
     */
    private final String[] operator = {"*", "/", "%", "+", "-", "&", "^", "|"};
    /*
    operator中相同下标运算符的优先级，值小的优先级高。
     */
    private final int[] priority = {3, 3, 3, 4, 4, 8, 9, 10};
    /*
    存储运算符集合。
     */
    private final SeqList<String> operList;

    public Operators(){
        operList = new SeqList<>(operator);
    }

    /**
     * 比较两个运算符的优先级大小
     * @param oper1 运算符1
     * @param oper2 运算符2
     * @return -1表示运算符1优先级高，0表示二者相等，1表示运算符2优先级高。
     */
    @Override
    public int compare(String oper1, String oper2) {
        int i = operList.search(oper1);
        int j = operList.search(oper2);
        return priority[i] - priority[j];
    }

    public int operate(int x, int y, String oper){
        int value = 0;
        switch (oper){
            case "+" -> value = x + y;
            case "-" -> value = x - y;
            case "*" -> value = x * y;
            case "/" -> value = x / y;
            case "%" -> value = x % y;
            case "&" -> value = x & y;
            case "^" -> value = x ^ y;
            case "|" -> value = x | y;
        }
        return value;
    }
}
