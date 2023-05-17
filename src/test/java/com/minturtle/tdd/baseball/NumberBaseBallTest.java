package com.minturtle.tdd.baseball;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
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


    @Test
    @DisplayName("맞추기 시도시 trial을 1 증가")
    void t6() throws Exception {
        Integer beforeTrial = (Integer) ReflectionTestUtils.getField(game, "trial");

        game.play(1,2,3);

        Integer afterTrial = (Integer) ReflectionTestUtils.getField(game, "trial");

        assertThat(beforeTrial).isEqualTo(0);
        assertThat(afterTrial).isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("맞추기 시도시 결과를 리턴-strike")
    @CsvSource(value = {"6,7,8:0", "1,2,3:3", "2,4,3:1"}, delimiter = ':')
    void t7(String trialString, int expectedStrike){
        //given
        ReflectionTestUtils.setField(game, "randomNumbers",List.of(1,2,3));
        List<Integer> trialIntegers = Arrays.stream(trialString.split(","))
                .mapToInt(Integer::parseInt).boxed().toList();

        NumberBaseBall.Result actualResult = game.play(trialIntegers.get(0), trialIntegers.get(1), trialIntegers.get(2));

        assertThat(actualResult.getStrike()).isEqualTo(expectedStrike);
    }


    @ParameterizedTest
    @DisplayName("맞추기 시도시 결과를 리턴-ball")
    @CsvSource(value = {"6,7,8:0", "1,3,2:2", "1,2,4:0", "1,2,3:0", "2,4,6:1"}, delimiter = ':')
    void t8(String trialString, int expectedBall){
        //given
        ReflectionTestUtils.setField(game, "randomNumbers",List.of(1,2,3));
        List<Integer> trialIntegers = Arrays.stream(trialString.split(","))
                .mapToInt(Integer::parseInt).boxed().toList();

        NumberBaseBall.Result actualResult = game.play(trialIntegers.get(0), trialIntegers.get(1), trialIntegers.get(2));

        assertThat(actualResult.getBall()).isEqualTo(expectedBall);
    }


    @ParameterizedTest
    @DisplayName("맞추기 시도시 결과를 리턴-isNothing")
    @CsvSource(value = {"6,7,8:true", "1,2,3:false", "2,4,3:false"}, delimiter = ':')
    void t9(String trialString, boolean expectedNothing){
        ReflectionTestUtils.setField(game, "randomNumbers",List.of(1,2,3));
        List<Integer> trialIntegers = Arrays.stream(trialString.split(","))
                .mapToInt(Integer::parseInt).boxed().toList();

        NumberBaseBall.Result actualResult = game.play(trialIntegers.get(0), trialIntegers.get(1), trialIntegers.get(2));

        assertThat(actualResult.isNothing()).isEqualTo(expectedNothing);
    }

    @ParameterizedTest
    @DisplayName("맞추기 시도시 결과를 리턴-통합 테스트")
    @CsvSource(value = {"6,7,8:0,0,true", "1,2,3:3,0,false", "2,4,3:1,1,false"}, delimiter = ':')
    void t10(String trialString, String expectedResultString) throws Exception {
        //given
        ReflectionTestUtils.setField(game, "randomNumbers",List.of(1,2,3));
        List<Integer> trialIntegers = Arrays.stream(trialString.split(","))
                .mapToInt(Integer::parseInt).boxed().toList();

        int expectedStrike = Integer.parseInt(expectedResultString.split(",")[0]);
        int expectedBall = Integer.parseInt(expectedResultString.split(",")[1]);
        boolean expectedNothing = Boolean.parseBoolean(expectedResultString.split(",")[2]);

        //when
        NumberBaseBall.Result actualResult = game.play(trialIntegers.get(0), trialIntegers.get(1), trialIntegers.get(2));

        //then
        assertThat(actualResult.getStrike()).isEqualTo(expectedStrike);
        assertThat(actualResult.getBall()).isEqualTo(expectedBall);
        assertThat(actualResult.isNothing()).isEqualTo(expectedNothing);
    }


    @Test
    @DisplayName("재시작 시 초기화")
    void t11() throws Exception {
        //given
        game.play(1,2,3);
        Integer beforeTrial = (Integer) ReflectionTestUtils.getField(game, "trial");

        //when
        game.restart();
        Integer afterTrial = (Integer) ReflectionTestUtils.getField(game, "trial");
        //then
        assertThat(beforeTrial).isEqualTo(1);
        assertThat(afterTrial).isEqualTo(0);
    }
}