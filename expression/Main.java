package expression;

import expression.generic.GenericTabulator;
import expression.parser.ExpressionParser;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        ExpressionParser parser = new ExpressionParser();
        Object[][][] col = (new GenericTabulator()).tabulate("bi", "10 + 4 / 2 - 7", 0, 0, 0, 0, 0, 0);
        System.out.println(col[0][0][0]);
    }
}
