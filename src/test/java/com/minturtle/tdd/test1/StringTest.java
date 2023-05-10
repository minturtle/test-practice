package com.minturtle.tdd.test1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class StringTest {

    /*
    요구사항 1
       - "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
       - "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.
    힌트
       - 배열로 반환하는 값의 경우 assertj의 contains()를 활용해 반환 값이 맞는지 검증한다.
       - 배열로 반환하는 값의 경우 assertj의 containsExactly()를 활용해 반환 값이 맞는지 검증한다.
    * */
    @Test
    @DisplayName("split 테스트")
    void t1() throws Exception {
        //given
        String testString = "1,2";
        //when
        String[] strings = testString.split(",");
        //then
        assertThat(strings).contains("1", "2");
        assertThat(strings).containsExactly("1", "2"); // 배열이 정확하게 순서까지 지켜서 리턴해야 함.
    }

    /*
    요구사항 2
        - "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.
    * */
    @Test
    @DisplayName("substring test")
    void t2() throws Exception {
        //given
        String testString = "(1,2)";
        //when
        final String resultString = testString.substring(1, testString.length() - 1);
        //then
        assertThat(resultString).isEqualTo("1,2");

    }

    /*
    요구사항 3
        - "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.
        - String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다.
        - JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.
    * */
    @Test
    @DisplayName("charAt Test-IndexOutOfBoundsException 예외 발생")
    void t3() throws Exception {
        //given
        String testString = "abc";
        //when
        //then
        assertThatThrownBy(()->testString.charAt(testString.length()))
                .isInstanceOf(StringIndexOutOfBoundsException.class);

    }


    /*
    요구사항 4
        - charAt을 사용해 문자열 abc의 문자들의 각 위치를 정확히 리턴하는지 테스트한다.
        - @ParameterizedTest를 사용한다.
    * */
    @ParameterizedTest
    @DisplayName("charAt- 정확한 위치")
    @CsvSource(value = {"0,a", "1,b", "2,c"})
    void t4(int idx, char testChar){
        String testString = "abc";

        assertThat(testString.charAt(idx)).isEqualTo(testChar);
    }
}
