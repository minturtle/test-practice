package com.minturtle.tdd.calculator;

import java.util.Arrays;
import java.util.List;

public class StringCalculator {

    private List<String> splitString(String text){
        return Arrays.stream(text.split("")).filter(c->!c.equals(" ")).toList();
    }


    private boolean isOperator(char token) throws IllegalArgumentException{
        char[] operators = {'+', '-', '*', '/'};

        for(char operator : operators){
            if(token == operator) return true;
        }

        if(token >= '0' && token <= '9') return false;


        throw new IllegalArgumentException();
    }


}
