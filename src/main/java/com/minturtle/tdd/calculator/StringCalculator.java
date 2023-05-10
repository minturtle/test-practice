package com.minturtle.tdd.calculator;

import java.util.Arrays;
import java.util.List;

public class StringCalculator {

    private List<String> splitString(String text){
        return Arrays.stream(text.split("")).filter(c->!c.equals(" ")).toList();
    }


    private boolean isOperator(char token){
        char[] operators = {'+', '-', '*', '/'};

        for(char operator : operators){
            if(token == operator) return true;
        }

        return false;
    }


}
