package calculator;

import java.util.Arrays;

public class Calculator {

    public int calculate(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        String[] result = input.replace(" ", "").split("[,:]");

        int sum = Arrays.stream(result)
                .mapToInt(Integer::parseInt)
                .sum();

        return sum;
    }
}
