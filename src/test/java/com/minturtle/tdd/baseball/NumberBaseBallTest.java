package com.minturtle.tdd.baseball;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;



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
}