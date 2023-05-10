package com.minturtle.tdd.calculator;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class StringCalculator {


    private Stack<Integer> operandStack;
    private Stack<Character> operatorStack;

    public StringCalculator() {
        initializeStacks();
    }

    private void initializeStacks() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int evaluate(String expression) {
        try{
            return evaluateBizLogic(expression);
        }catch (EmptyStackException e){
            throw new IllegalArgumentException("잘못된 입력입니다.", e);
        }
    }

    private int evaluateBizLogic(String expression){
        initializeStacks();

        final List<Character> tokens = splitString(expression);

        for (char token : tokens) {
            if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (operatorStack.peek() != '(') {
                    int result = applyOperatorFromStack();
                    operandStack.push(result);
                }
                operatorStack.pop(); // remove '('
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token)) {
                    int result = applyOperatorFromStack();
                    operandStack.push(result);
                }
                operatorStack.push(token);
            } else {
                operandStack.push(token - '0');
            }
        }

        while (!operatorStack.isEmpty()) {
            int result = applyOperatorFromStack();
            operandStack.push(result);
        }

        return operandStack.pop();
    }


    private int applyOperatorFromStack() {
        int rightOperand = operandStack.pop();
        int leftOperand = operandStack.pop();
        char operator = operatorStack.pop();

        switch (operator) {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '*':
                return leftOperand * rightOperand;
            case '/':
                return leftOperand / rightOperand;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
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

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

}
