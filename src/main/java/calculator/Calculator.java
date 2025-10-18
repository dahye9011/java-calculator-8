package calculator;

import java.util.Arrays;

public class Calculator {

    public int calculate(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        if (input.startsWith("//")) {
            // \n 뒤부터 잘라서 일반 입력 문자열로 만듦
            // 입력 문자열을 커스텀 구분자로 자름
            int newLineIndex = input.indexOf("\n");

            String inputString = input.substring(newLineIndex + 1);

            String customDelimiter = separateDelimiter(input);
            String[] splitString = inputString.replace(" ", "").split(customDelimiter);

            int result = sum(splitString);
            return result;
        }

        String[] splitString = input.replace(" ", "").split("[,:]");
        int result = sum(splitString);

        return result;
    }

    private String separateDelimiter(String input) {
        int newLineIndex = input.indexOf("\n");
        String customDelimiter = input.substring(2, newLineIndex);

        return customDelimiter;
    }

    private int sum(String[] input) {
        int sum = Arrays.stream(input)
                .mapToInt(Integer::parseInt)
                .sum();

        return sum;
    }
}
