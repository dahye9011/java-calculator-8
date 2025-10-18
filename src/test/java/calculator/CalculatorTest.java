package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {
    @Test
    void 기본_구분자_사용1() {
        // given
        Calculator calculator = new Calculator();
        String input = "1,2,3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void 기본_구분자_사용2() {
        // given
        Calculator calculator = new Calculator();
        String input = "1:2:3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void 기본_구분자_혼합_사용() {
        // given
        Calculator calculator = new Calculator();
        String input = "1,2:3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void 기본_구분자_혼합_공백_사용() {
        // given
        Calculator calculator = new Calculator();
        String string = "1, 2:3";

        // when
        int result = calculator.calculate(string);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void 빈_문자열() {
        // given
        Calculator calculator = new Calculator();
        String input = "";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void 공백_문자열() {
        // given
        Calculator calculator = new Calculator();
        String input = " ";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void 구분자_없는_문자열() {
        // given
        Calculator calculator = new Calculator();
        String input = "123";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(123);
    }
}
