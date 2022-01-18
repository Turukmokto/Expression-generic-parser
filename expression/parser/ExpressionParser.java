package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.generic.Operations;

public class ExpressionParser<T> {
    private String expression;
    private int currPos;

    private enum type {
        START,
        L_BRACKET,
        R_BRACKET,
        PLUS,
        MINUS,
        NEGATE,
        STAR,
        SLASH,
        MOD,
        NUMBER,
        VARIABLE,
        END,
        UNKNOWN
    }

    private type currLexeme;
    private int currBalance;
    private T currValue;
    private char currVar;
    private Operations<T> operations;

    public ScalarTripleExpression<T> parse(String expression, Operations<T> operations) throws ParseException {
        this.expression = expression;
        this.operations = operations;
        currPos = currBalance = 0;
        currLexeme = type.START;
        return addAndSubtract();
    }

    private void noWhiteSpaces() {
        while (currPos < expression.length() && Character.isWhitespace(expression.charAt(currPos))) {
            ++currPos;
        }
    }

    private void updatePosition() {
        if (currPos == expression.length()) {
            ++currPos;
        }

        --currPos;
    }

    private String getNextNumber() {
        int leftPos = currPos;
        while (currPos < expression.length() && operations.isPartOfNumber(expression.charAt(currPos))) {
            ++currPos;
        }
        int rightPos = currPos;

        updatePosition();

        return expression.substring(leftPos, rightPos);
    }

    private boolean isOperation() {
        switch (currLexeme) {
            case PLUS:
            case MINUS:
            case STAR:
            case SLASH:
            case MOD:
                return true;
            default:
                return false;
        }
    }

    private void checkPrevOperand() throws OperandNotFoundException {
        if (isOperation() || currLexeme == type.L_BRACKET || currLexeme == type.START) {
            throw new OperandNotFoundException(expression, typeToString(), currPos);
        }
    }

    private void checkPrevOperation() throws OperationNotFoundException {
        if (currLexeme == type.R_BRACKET || currLexeme == type.NUMBER || currLexeme == type.VARIABLE) {
            throw new OperationNotFoundException(expression, typeToString(), currPos);
        }
    }

    private String getNextWord() {
        StringBuilder res = new StringBuilder();
        while (currPos < expression.length() && Character.isLetterOrDigit(expression.charAt(currPos))) {
            res.append(expression.charAt(currPos));
            ++currPos;
        }

        updatePosition();

        return res.toString();
    }

    private String typeToString() {
        switch (currLexeme) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case STAR:
                return "*";
            case SLASH:
                return "/";
            case L_BRACKET:
                return "(";
            case R_BRACKET:
                return ")";
            case MOD:
                return "Mod";
            case NUMBER:
                return "Number";
            case VARIABLE:
                return "Variable";
            case START:
                return "Begin of expression";
            case END:
                return "End of expression";
            default:
                throw new AssertionError();
        }
    }

    private void getNextLexeme() throws ParseException {
        noWhiteSpaces();

        if (currPos >= expression.length()) {
            checkPrevOperand();
            if (currBalance != 0) {
                throw new WrongBracketSequenceException(expression, currPos);
            }
            currLexeme = type.END;
            return;
        }

        char currSymbol = expression.charAt(currPos);
        if (currSymbol == '(') {
            checkPrevOperation();
            ++currBalance;
            currLexeme = type.L_BRACKET;
        } else if (currSymbol == ')') {
            checkPrevOperand();
            if (currBalance <= 0) {
                throw new WrongBracketSequenceException(expression, currPos);
            }
            --currBalance;
            currLexeme = type.R_BRACKET;
        } else if (currSymbol == '+') {
            checkPrevOperand();
            currLexeme = type.PLUS;
        } else if (currSymbol == '-') {
            if (currLexeme == type.NUMBER || currLexeme == type.VARIABLE || currLexeme == type.R_BRACKET) {
                currLexeme = type.MINUS;
            } else {
                if (currPos + 1 >= expression.length()) {
                    throw new OperandNotFoundException(expression, "-", 1);
                }
                if (Character.isDigit(expression.charAt(currPos + 1))) {
                    currLexeme = type.NUMBER;
                    ++currPos;
                    String currNumber = "-" + getNextNumber();
                    try {
                        currValue = operations.parseT(currNumber);
                    } catch (NumberFormatException e) {
                        throw new WrongConstException(expression, currNumber, Math.max(0, currPos - currNumber.length()));
                    }
                } else {
                    currLexeme = type.NEGATE;
                }
            }
        } else if (currSymbol == '*') {
            checkPrevOperand();
            currLexeme = type.STAR;
        } else if (currSymbol == '/') {
            checkPrevOperand();
            currLexeme = type.SLASH;
        } else if (operations.isPartOfNumber(currSymbol)) {
            checkPrevOperation();
            currLexeme = type.NUMBER;
            int startPos = currPos;
            String currNumber = getNextNumber();
            try {
                currValue = operations.parseT(currNumber);
            } catch (NumberFormatException e) {
                throw new WrongConstException(expression, currNumber, Math.max(0, startPos));
            }
        } else if (Character.isLetter(currSymbol)) {
            int startPos = currPos;
            String word = getNextWord();
            switch (word) {
                case "x":
                case "y":
                case "z":
                    checkPrevOperation();
                    currLexeme = type.VARIABLE;
                    currVar = word.charAt(0);
                    break;
                case "mod":
                    checkPrevOperand();
                    currLexeme = type.MOD;
                    break;
                default:
                    currLexeme = type.UNKNOWN;
                    throw new UnknownLexemeException(expression, word, startPos);
            }
        } else {
            throw new UnknownLexemeException(expression, Character.toString(currSymbol), currPos);
        }
        ++currPos;
    }

    private ScalarTripleExpression<T> addAndSubtract() throws ParseException {
        ScalarTripleExpression<T> res = multiplyAndDivide();

        while (true) {
            if (currLexeme == type.PLUS) {
                res = new Add<>(res, multiplyAndDivide(), operations);
            } else if (currLexeme == type.MINUS) {
                res = new Subtract<>(res, multiplyAndDivide(), operations);
            } else {
                break;
            }
        }

        return res;
    }

    private ScalarTripleExpression<T> multiplyAndDivide() throws ParseException {
        ScalarTripleExpression<T> res = unaryAndOther();

        while (true) {
            if (currLexeme == type.STAR) {
                res = new Multiply<>(res, unaryAndOther(), operations);
            } else if (currLexeme == type.SLASH) {
                res = new Divide<>(res, unaryAndOther(), operations);
            } else if (currLexeme == type.MOD) {
                res = new Mod<>(res, unaryAndOther(), operations);
            } else {
                break;
            }
        }

        return res;
    }

    private ScalarTripleExpression<T> unaryAndOther() throws ParseException {
        ScalarTripleExpression<T> res;
        getNextLexeme();

        switch (currLexeme) {
            case NEGATE:
                return new Negate<>(unaryAndOther(), operations);
            case NUMBER:
                res = new Const<>(currValue);
                getNextLexeme();
                break;
            case VARIABLE:
                res = new Variable<>(Character.toString(currVar), operations);
                getNextLexeme();
                break;
            case L_BRACKET:
                res = addAndSubtract();
                getNextLexeme();
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }
}