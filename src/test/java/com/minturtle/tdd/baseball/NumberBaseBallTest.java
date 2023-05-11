package com.minturtle.tdd.baseball;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;



@Slf4j
class NumberBaseBallTest {


    private NumberBaseBall game;

    @BeforeEach
    void setUp() {
        game = new NumberBaseBall();

    }

    @Test
    @DisplayName("game 객체 생성")
    void t1() throws Exception {
        //then
        assertThat(game).isNotNull();
    }

    @Test
    @DisplayName("random한 3개의 숫자 생성")
    void t3() throws Exception {
        //given

        //when
        List<Integer> randomNumbers = (List<Integer>) ReflectionTestUtils.invokeMethod(game, "getRandomNumbers");

        log.info("numbers : {}", randomNumbers);
        //then

        assertThat(randomNumbers.size()).isEqualTo(3);
    }


    @RepeatedTest(100)
    @DisplayName("3개의 숫자 중복 체크")
    void t4(){
        List<Integer> randomNumbers = (List<Integer>) ReflectionTestUtils.invokeMethod(game, "getRandomNumbers");
        int listSize = randomNumbers.size();
        int setSize = new HashSet<Integer>(randomNumbers).size();

        log.info("numbers : {}, list size : {}, set size : {}", randomNumbers, listSize, setSize);

        assertThat(listSize).isEqualTo(setSize);
     }

    @RepeatedTest(100)
    @DisplayName("숫자의 범위를 1~9까지로 제한")
    void t5() throws Exception {
        List<Integer> randomNumbers = (List<Integer>) ReflectionTestUtils.invokeMethod(game, "getRandomNumbers");

        assertThatCode(() -> {
            for(int randomNumber : randomNumbers){
                if(randomNumber <= 0 || randomNumber >= 10) throw new IllegalStateException();
            }
        }).doesNotThrowAnyException();


    }
}