package calculator;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Calculator {

    public int calculate(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        if (input.startsWith("//")) {
            int newLineIndex = input.indexOf("\n");

            // 커스텀 구분자 뒤 \n 없는 경우
            if (newLineIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
            }

            String inputString = input.substring(newLineIndex + 1);
            String customDelimiter = separateDelimiter(input);

            if (!inputString.contains(customDelimiter)) {
                throw new IllegalArgumentException("커스텀 구분자를 사용하지 않았습니다.");
            }

            String repeatedDelimiter = customDelimiter + customDelimiter;
            if (inputString.contains(repeatedDelimiter)) {
                throw new IllegalArgumentException("연속된 구분자가 포함되어 있습니다.");
            }

            if (inputString.endsWith(customDelimiter)) {
                throw new IllegalArgumentException("입력이 구분자로 끝날 수 없습니다.");
            }

            // customDelimiter가 정규식과 겹치는 경우에도 정규식으로 인식하지 않게
            String pattern = Pattern.quote(customDelimiter);
            String combinedPattern = pattern + "|\\s+";

            String[] patternSplitString = inputString.split(combinedPattern);
            int result = sum(patternSplitString);

            return result;
        }

        if (input.contains(",,") || input.contains("::") ||
                input.contains(":,") || input.contains(",:")) {
            throw new IllegalArgumentException("연속된 구분자가 포함되어 있습니다.");
        }

        if (input.endsWith(",") || input.endsWith(":")) {
            throw new IllegalArgumentException("입력이 구분자로 끝날 수 없습니다.");
        }

        String[] splitString = input.split("[,:\\s]+");
        int result = sum(splitString);

        return result;
    }

    private String separateDelimiter(String input) {
        int newLineIndex = input.indexOf("\n");
        String customDelimiter = input.substring(2, newLineIndex);

        if (customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
        }

        return customDelimiter;
    }

    private int sum(String[] input) {
        int sum = Arrays.stream(input)
                .filter(s -> !s.isBlank())
                .mapToInt(s -> {
                    int number;
                    try {
                        number = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("숫자 외의 문자가 포함되어 있습니다.");
                    }
                    validateNumber(number);
                    return number;
                })
                .sum();

        return sum;
    }

    // 숫자 범위 검증
    private void validateNumber(int input) {
        if (input <= 0)  {
            throw new IllegalArgumentException("0보다 큰 자연수만 입력 가능합니다.");
        }
    }

}
