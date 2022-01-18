package expression.exceptions;

public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    static protected String printPlace(String s, int index, int length) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < Math.min(s.length(), index); ++i) {
            res.append(s.charAt(i));
        }
        res.append("|-->");
        for (int i = index; i < Math.min(index + length, s.length()); ++i) {
            res.append(s.charAt(i));
        }
        res.append("<--|");
        for (int i = index + length; i < s.length(); ++i) {
            res.append(s.charAt(i));
        }

        return res.toString();
    }
}