package com.minturtle.tdd.calculator;

import java.util.*;

public class StringCalculator {

    private final StringUtils stringUtils;

    public StringCalculator(StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    private Stack<Integer> operandStack;
    private Stack<Character> operatorStack;


    public int evaluate(String expression) {
        try{
            return evaluateBizLogic(expression);
        }catch (EmptyStackException e){
            throw new IllegalArgumentException("잘못된 입력입니다.", e);
        }
    }

    private int evaluateBizLogic(String expression){
        initializeStacks();

        // 문자열에서 연산자와 피연산자 분류
        List<Character> tokens = stringUtils.splitString(expression);
        
        
        // 계산 수행
        for (char token : tokens) {
            if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (operatorStack.peek() != '(') {
                    int result = applyOperatorFromStack();
                    operandStack.push(result);
                }
                operatorStack.pop(); // remove '('
            } else if (stringUtils.isOperator(token)) {
                while (!operatorStack.isEmpty() && getPrecedence(operatorStack.peek()) >= getPrecedence(token)) {
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

    private void initializeStacks() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
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


    private int getPrecedence(char operator) {
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
