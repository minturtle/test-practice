package com.minturtle.tdd.test1.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}