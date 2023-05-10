package com.minturtle.tdd.test1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    // 요구사항 2
    // Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트를 구현하려한다.
    // 구현하고 보니 다음과 같이 중복 코드가 계속해서 발생한다.
    // JUnit의 ParameterizedTest를 활용해 중복 코드를 제거해 본다.

    // ParameterizedTest를 사용하면 동일한 테스트에 대해 여러가지 값으로 테스트를 수행할 수 있다.
    @ParameterizedTest
    @DisplayName("set contain_number test")
    @ValueSource(ints = {1, 2, 3})
    void t2(int testNumber){
        assertThat(numbers).contains(testNumber);
        assertThat(numbers).containsExactly(1, 2, 3);
    }
    
}
