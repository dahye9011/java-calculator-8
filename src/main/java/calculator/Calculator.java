package calculator;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Calculator {
    public int calculate(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        input = input.replace("\\n", "\n");

        if (input.startsWith("//")) {
            String customDelimiter = Parser.extractCustomDelimiter(input);

            String payload = Parser.extractPayload(input);

            Validator.validateCustomDelimiter(payload, customDelimiter);
            String[] splitNumbers = splitByCustomDelimiter(payload, customDelimiter);

            return sum(splitNumbers);
        }

        Validator.validateDefaultDelimiter(input);
        String[] splitNumbers = splitByDefaultDelimiter(input);
        return sum(splitNumbers);
    }

    private int sum(String[] input) {
        return Arrays.stream(input)
                .mapToInt(token -> {
                    int number = Parser.parseNumber(token);
                    Validator.validatePositiveNumber(number);
                    return number;
                })
                .sum();
    }

    private String[] splitByCustomDelimiter(String payload, String customDelimiter) {
        return Arrays.stream(payload.split(Pattern.quote(customDelimiter)))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    private String[] splitByDefaultDelimiter(String input) {
        return Arrays.stream(input.split("[,:]"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }
}
