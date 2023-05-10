package com.minturtle.tdd.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StringCalculator {


    private Stack<Integer> operandStack;
    private Stack<Character> operatorStack;

    public StringCalculator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    private List<Character> splitString(String expression){
        return Arrays.stream(expression.split("")).map(s->s.charAt(0)).filter(c->c != ' ').toList();
    }


    private boolean isOperator(char token) throws IllegalArgumentException{
        char[] operators = {'+', '-', '*', '/', '(', ')'};

        for(char operator : operators){
            if(token == operator) return true;
        }

        if(token >= '0' && token <= '9') return false;


        throw new IllegalArgumentException();
    }

    private void setStack(String expression){
        List<Character> tokens = splitString(expression);

        for(Character token : tokens){
            if(isOperator(token)) operatorStack.push(token);
            else operandStack.push((int)(token - '0'));
        }

    }
}
