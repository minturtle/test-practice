package com.minturtle.tdd.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


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
    @CsvSource(value = {"1+2:1,+,2", "1+3/2+1:1,+,3,/,2,+,1", "1 + 4:1,+,4"}, delimiter = ':')
    void t2(String testString, String resultListPlaneString) throws Exception {
        //given
        List<String> expectedValues = Arrays.stream(resultListPlaneString.split(",")).toList();

        //when

        List<String> actualValues = (List<String>) ReflectionTestUtils.invokeMethod(cal, "splitString", testString);

        boolean testResult1 = actualValues.containsAll(expectedValues);
        boolean testResult2 = expectedValues.containsAll(actualValues);
        //then
        assertThat(testResult1).isTrue();
        assertThat(testResult2).isTrue();
    }




}