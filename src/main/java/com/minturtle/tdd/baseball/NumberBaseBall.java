package com.minturtle.tdd.baseball;

/*
기본적으로 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임이다.
같은 수가 같은 자리에 있으면 스트라이크, 다른 자리에 있으면 볼, 같은 수가 전혀 없으면 포볼 또는 낫싱이란 힌트를 얻고, 그 힌트를 이용해서 먼저 상대방(컴퓨터)의 수를 맞추면 승리한다.
e.g. 상대방(컴퓨터)의 수가 425일 때, 123을 제시한 경우 : 1스트라이크, 456을 제시한 경우 : 1볼 1스트라이크, 789를 제시한 경우 : 낫싱
위 숫자 야구 게임에서 상대방의 역할을 컴퓨터가 한다. 컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다. 게임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력하고, 컴퓨터는 입력한 숫자에 대한 결과를 출력한다.
이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.
* */


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

public class NumberBaseBall {


    private List<Integer> randomNumbers;
    private int trial = 0;

    public NumberBaseBall() {
        randomNumbers = getRandomNumbers();
    }



    //매개변수로 List를 사용하지 않은 이유? 숫자를 3개로 제한하기 위해
    public Result play(int n1, int n2, int n3){
        trial++;
        int strike = 0;
        int ball = 0;

        int[] trials = new int[]{n1, n2, n3};


        for(int i = 0; i < trials.length; i++){
            int trial = trials[i];

            // 정확한 위치에 정확한 숫자 -> strike
            if(trial == randomNumbers.get(i)) strike++;
            // strike는 아니지만 숫자 리스트 중에 trial이 있음 -> ball
            else if(randomNumbers.contains(trial)) ball++;

        }


        return new Result(strike, ball);
    }


    private List<Integer> getRandomNumbers(){
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();

        while (numbers.size() < 3) {
            int randomNumber = random.nextInt(9) + 1;
            numbers.add(randomNumber);
        }

        return Collections.unmodifiableList(numbers.stream().toList());
    }


    @Getter
    @AllArgsConstructor
    public static class Result{
        private int strike;
        private int ball;

        public boolean isNothing(){
            return (strike == 0) && (ball == 0);
        }
    }
}
