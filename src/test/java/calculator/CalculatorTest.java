package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    @DisplayName("기본 구분자 쉼표를 기준으로 문자열을 계산한다.")
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
    @DisplayName("기본 구분자 콜론을 기준으로 문자열을 계산한다.")
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
    @DisplayName("기본 구분자 쉼표와 콜론을 혼합 사용해도 정상 동작한다.")
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
    @DisplayName("기본 구분자와 숫자 사이 공백이 있어도 정상 동작한다.")
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
    @DisplayName("문자열이 비어있는 경우, 0을 반환한다.")
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
    @DisplayName("문자열이 공백인 경우, 0을 반환한다.")
    void 공백_문자열() {
        // given
        Calculator calculator = new Calculator();
        String input = "  ";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("문자열에 구분자가 없는 경우, 숫자를 그대로 반환한다.")
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
    @DisplayName("커스텀 구분자를 기준으로 문자열을 계산한다.")
    void 커스텀_구분자_사용() {
        // given
        Calculator calculator = new Calculator();
        String input = "//;\n1;2;3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자로 여러 글자의 문자열을 사용할 수 있다.")
    void 커스텀_구분자_여러_글자_사용() {
        // given
        Calculator calculator = new Calculator();
        String input = "//;;;\n1;;;2;;;3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자와 숫자 사이 공백이 있어도 정상 동작한다.")
    void 커스텀_구분자_공백_사용() {
        // given
        Calculator calculator = new Calculator();
        String input = "//;\n1 ;2 ;3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자로 공백을 사용할 수 있다.")
    void 커스텀_구분자_공백_사용2() {
        // given
        Calculator calculator = new Calculator();
        String input = "// \n1 2 3";

        // when
        int result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자가 정규 표현식과 겹치더라도 정상 동작한다.")
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

    @Test
    @DisplayName("0이 포함되어 있는 경우, IllegalArgumentException이 발생한다.")
    void 영_입력_예외() {
        // given
        Calculator calculator = new Calculator();
        String input = "0,0,0";

        // when
        IllegalArgumentException e =
                assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(input));

        // then
        assertEquals("0보다 큰 자연수만 입력 가능합니다.", e.getMessage());
    }

    @Test
    @DisplayName("음수가 포함되어 있는 경우, IllegalArgumentException이 발생한다.")
    void 음수_입력_예외() {
        // given
        Calculator calculator = new Calculator();
        String input = "-1,2,3";

        // when
        IllegalArgumentException e =
                assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(input));

        // then
        assertEquals("0보다 큰 자연수만 입력 가능합니다.", e.getMessage());
    }

    @Test
    @DisplayName("커스텀 구분자가 //와 \n 사이에 오지 않는 경우, IllegalArgumentException이 발생한다.")
    void 커스텀_구분자_예외() {
        // given
        Calculator calculator = new Calculator();
        String input = "//;1;2;3";

        // when
        IllegalArgumentException e =
                assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(input));

        // then
        assertEquals("커스텀 구분자 형식이 잘못되었습니다.", e.getMessage());
    }

    @Test
    @DisplayName("지정한 커스텀 구분자를 사용하지 않은 경우, IllegalArgumentException이 발생한다.")
    void 커스텀_구분자_예외2() {
        // given
        Calculator calculator = new Calculator();
        String input = "//;\n1&2&3";

        // when
        IllegalArgumentException e =
                assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(input));

        // then
        assertEquals("커스텀 구분자를 사용하지 않았습니다.", e.getMessage());
    }

    @Test
    @DisplayName("커스텀 구분자가 비어 있는 경우, IllegalArgumentException이 발생한다. (공백 구분자는 허용)")
    void 커스텀_구분자_예외3() {
        // given
        Calculator calculator = new Calculator();
        String input = "//\n1&2&3";

        // when
        IllegalArgumentException e =
                assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(input));

        // then
        assertEquals("커스텀 구분자가 비어있습니다.", e.getMessage());
    }

    @Test
    @DisplayName("연속된 구분자가 포함되는 경우, IllegalArgumentException이 발생한다.")
    void 구분자_연속_예외() {
        // given
        Calculator calculator = new Calculator();
        String input = "1,,2";

        // when
        IllegalArgumentException e =
                assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(input));

        // then
        assertEquals("연속된 구분자가 포함되어 있습니다.", e.getMessage());
    }

    @Test
    @DisplayName("문자열 마지막에 구분자가 나오는 경우, IllegalArgumentException이 발생한다.")
    void 문자열_마지막_구분자_예외() {
        // given
        Calculator calculator = new Calculator();
        String input = "1,2,";

        // when
        IllegalArgumentException e =
                assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(input));

        // then
        assertEquals("입력이 구분자로 끝날 수 없습니다.", e.getMessage());
    }

    @Test
    @DisplayName("숫자 외의 문자가 포함되어 있는 경우, IllegalArgumentException이 발생한다.")
    void 숫자_외의_문자열_예외() {
        // given
        Calculator calculator = new Calculator();
        String input = "1,a,b";

        // when
        IllegalArgumentException e =
                assertThrowsExactly(IllegalArgumentException.class, () -> calculator.calculate(input));

        // then
        assertEquals("숫자 외의 문자가 포함되어 있습니다.", e.getMessage());
    }
}
