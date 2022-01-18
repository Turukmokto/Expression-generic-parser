package expression.generic;

import expression.ScalarTripleExpression;
import expression.exceptions.CalculateException;
import expression.exceptions.ParseException;
import expression.parser.ExpressionParser;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        final Operations<?> operations;
        switch (mode) {
            case "i":
                operations = new IntegerMode(true);
                break;
            case "d":
                operations = new DoubleMode();
                break;
            case "bi":
                operations = new BigIntegerMode();
                break;
            case "u":
                operations = new IntegerMode(false);
                break;
            case "l":
                operations = new LongMode();
                break;
            case "s":
                operations = new ShortMode();
                break;
            default:
                throw new AssertionError();
        }

        return createTable(operations, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] createTable(Operations<T> operations, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        try {
            ScalarTripleExpression<T> result = new ExpressionParser<T>().parse(expression, operations);
            for (int i = x1; i <= x2; ++i) {
                for (int j = y1; j <= y2; ++j) {
                    for (int k = z1; k <= z2; ++k) {
                        try {
                            table[i - x1][j - y1][k - z1] = result.evaluate(i, j, k);
                        } catch (CalculateException | NumberFormatException ignored) {
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return table;
    }
}