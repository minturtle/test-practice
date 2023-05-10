package com.minturtle.tdd.test1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;


public class SetTest {

    private Set<Integer> numbers;


    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

/*
* 요구사항 1
       - Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.
* */
    @Test
    @DisplayName("set size test")
    void t1() throws Exception {
        //when
        int setSize = numbers.size();
        //then
        assertThat(setSize).isEqualTo(3); // set은 중복되어있는 데이터를 허용하지 않는다.

    }
}
