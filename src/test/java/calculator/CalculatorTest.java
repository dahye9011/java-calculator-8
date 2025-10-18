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

    @Test
    void 커스텀_구분자_사용() {
        // gien
        Calculator calculator = new Calculator();
        String input = "//;\n1;2;3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void 커스텀_구분자_여러_글자_사용() {
        // gien
        Calculator calculator = new Calculator();
        String input = "//;;;\n1;;;2;;;3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void 커스텀_구분자_공백_사용() {
        // gien
        Calculator calculator = new Calculator();
        String input = "//;\n1 ;2 ;3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    void 커스텀_구분자_정규_표현식() {
        // given
        Calculator calculator = new Calculator();
        String input = "//|\n1|2|3";
        String input2 = "//[]\n1[]2[]3";

        // when
        int result = calculator.calculate(input);
        int result2 = calculator.calculate(input2);

        // then
        assertThat(result).isEqualTo(6);
        assertThat(result2).isEqualTo(6);
    }
}
