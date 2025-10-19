package calculator;

public class Validator {
    // 커스텀 구분자 형식 검증 (\n)
    public static void validateCustomHeader(int newLineIndex) {
        if (newLineIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
        }
    }

    // 커스텀 구분자 자체 내용 검증
    public static void validateDelimiterFormat(String customDelimiter) {
        if (customDelimiter.isBlank()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
        }
        if (customDelimiter.length() > 1) {
            throw new IllegalArgumentException("커스텀 구분자는 한 글자만 입력 가능합니다.");
        }
    }

    // 커스텀 구분자 사용 본문 검증
    public static void validateCustomDelimiter(String numberString, String customDelimiter) {
        if (numberString.contains(customDelimiter + customDelimiter)) {
            throw new IllegalArgumentException("연속된 구분자가 포함되어 있습니다.");
        }

        if (numberString.endsWith(customDelimiter)) {
            throw new IllegalArgumentException("입력이 구분자로 끝날 수 없습니다.");
        }
    }

    // 기본 구분자 사용 본문 검증
    public static void validateDefaultDelimiter(String input) {
        if (input.contains(",,") || input.contains("::") ||
                input.contains(":,") || input.contains(",:")) {
            throw new IllegalArgumentException("연속된 구분자가 포함되어 있습니다.");
        }

        if (input.endsWith(",") || input.endsWith(":")) {
            throw new IllegalArgumentException("입력이 구분자로 끝날 수 없습니다.");
        }
    }

    // 숫자 범위 검증
    public static void validatePositiveNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("0보다 큰 자연수만 입력 가능합니다.");
        }
    }
}
