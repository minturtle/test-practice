package com.minturtle.tdd.calculator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class StringCalculatorTest {

    private StringCalculator cal;
    @BeforeEach
    void setUp() {
        cal = new StringCalculator();
    }


    @Test
    @DisplayName("Calculator 객체 생성")
    void t1() throws Exception {
        //then
        assertThat(cal).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("문자열 split")
    @CsvSource(value = {"1+2:1,+,2", "1+3/2+1:1,+,3,/,2,+,1", "1 + 4:1,+,4", "(3+4):(,3,+,4,)"}, delimiter = ':')
    void t2(String testString, String resultListPlaneString) throws Exception {
        //given
        List<Character> expectedValues = Arrays.stream(resultListPlaneString.split(",")).map(s->s.charAt(0)).toList();

        //when

        List<Character> actualValues = (List<Character>) ReflectionTestUtils.invokeMethod(cal, "splitString", testString);

        boolean testResult1 = actualValues.containsAll(expectedValues);
        boolean testResult2 = expectedValues.containsAll(actualValues);

        log.info("expected : {}, actual : {}", expectedValues, actualValues);
        //then
        assertThat(testResult1).isTrue();
        assertThat(testResult2).isTrue();
    }


    @ParameterizedTest
    @DisplayName("연산자/ 피연산자 분류")
    @CsvSource(value = {"1,false" , "+,true", "5,false", "-,true", "*,true", "/,true", "3,false", "(,true", "),true"})
    void t3(char testToken, boolean expected) throws Exception {
        //given

        //when
        boolean actual = (boolean) ReflectionTestUtils.invokeMethod(cal, "isOperator", testToken);

        log.info("token : {}, expected : {}, actual : {}", testToken, expected, actual);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("연산자, 피연산자 분류 시 잘못된 값이 들어왔을때 예외 발생")
    void t4() throws Exception {
        //given
        char testToken = 'a';
        //then
        assertThatThrownBy(()->{
                boolean value = (boolean) ReflectionTestUtils.invokeMethod(cal, "isOperator", testToken);})
                .isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @DisplayName("스택에 연산자/ 피연산자 구분해 값을 넣기")
    @CsvSource(value = {"1+2/3:1,2,3@+,/", "4 * 5 - 6:4,5,6@*,-", "(7+8):7,8@(,+,)"}, delimiter = ':')
    void t5(String expression, String tokenPlaneString) throws Exception {
        //given
        String[] tokens = tokenPlaneString.split("@");
        final List<Integer> expectedOperands = Arrays.stream(tokens[0].split(",")).mapToInt(Integer::parseInt).boxed().toList();
        final List<Character> expectedOperators = Arrays.stream(tokens[1].split(",")).map(s->s.charAt(0)).toList();

        ReflectionTestUtils.invokeMethod(cal, "setStack", expression);

        //when
        Stack<Character> operatorStack = (Stack<Character>) ReflectionTestUtils.getField(cal, "operatorStack");
        Stack<Integer> operandStack = (Stack<Integer>) ReflectionTestUtils.getField(cal, "operandStack");

        log.info("expected operator: {}, actual operand : {}", expectedOperators, operatorStack);
        log.info("expected operand: {}, actual operand : {}", expectedOperands, operandStack);
        //then

        boolean operatorTestResult = operatorStack.containsAll(expectedOperators);
        boolean operandTestResult = operandStack.containsAll(expectedOperands);

        assertThat(operatorTestResult).isTrue();
        assertThat(operandTestResult).isTrue();

    }


    @ParameterizedTest
    @DisplayName("중위 표현식 계산")
    @CsvSource(value = {"1+2,3", "(4 * 6) / 3, 8", "4 * (6 + 3) , 36"})
    void t6(String expression, int expected){
        int actual = cal.evaluate(expression);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("잘못된 식 입력")
    @ValueSource(strings = {"1+", "+5/2"})
    void t7(String expression){
        assertThatThrownBy(()->cal.evaluate(expression))
                .isInstanceOf(IllegalArgumentException.class);
    }
}