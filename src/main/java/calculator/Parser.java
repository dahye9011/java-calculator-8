package calculator;

public class Parser {
    public static String extractCustomDelimiter(String input) {
        int newLineIndex = getNewLineIndex(input);
        String delimiter = input.substring(2, newLineIndex);
        Validator.validateDelimiterFormat(delimiter);

        return delimiter;
    }

    public static String extractPayload(String input) {
        int newLineIndex = getNewLineIndex(input);
        return input.substring(newLineIndex + 1);
    }

    public static int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 외의 문자가 포함되어 있습니다.");
        }
    }

    private static int getNewLineIndex(String input) {
        int newLineIndex = input.indexOf("\n");
        Validator.validateCustomHeader(newLineIndex);
        return newLineIndex;
    }
}
