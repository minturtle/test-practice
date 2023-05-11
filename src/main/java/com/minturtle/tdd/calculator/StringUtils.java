package com.minturtle.tdd.calculator;

import java.util.Arrays;
import java.util.List;

public class StringUtils {


    public List<Character> splitString(String expression) throws IllegalArgumentException{
        List<Character> tokens = Arrays.stream(expression.split("")).map(s -> s.charAt(0)).filter(c -> c != ' ').toList();


        //validate tokens
        tokens.forEach((c)->{
            if(!isAvailableToken(c)){
                throw new IllegalArgumentException();
            }
        });

        return tokens;
    }

    public boolean isOperator(char token){
        char[] operators = {'+', '-', '*', '/', '(', ')'};

        for(char operator : operators){
            if(token == operator) return true;
        }

        return false;
    }

    private boolean isAvailableToken(char token){
        char[] operators = {'+', '-', '*', '/', '(', ')'};

        if(token >= '0' && token <= '9') return true;

        for(char operator : operators){
            if(token == operator) return true;
        }

        return false;
    }
}
