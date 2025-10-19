package calculator;

public class DelimiterParser {
    public static String extractCustomDelimiter(String input) {
        int newLineIndex = input.indexOf("\n");

        Validator.validateCustomHeader(newLineIndex);
        String delimiter = input.substring(2, newLineIndex);
        Validator.validateDelimiterFormat(delimiter);

        return delimiter;
    }

    public static int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 외의 문자가 포함되어 있습니다.");
        }
    }
}
