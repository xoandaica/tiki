/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki.util;

import tiki.util.NumbericUtil;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author soithattha
 * @date Mar 20, 2019
 * @version
 * @description
 */
public class RPNCalculator {

    private static String OPERATOR_ALLOWED = "+-*/";

    /**
     * content of cell in case operand is cellID
     */
    private Map<String, Double> mContentOfCell;
    /**
     * rpn expression
     */
    private String expresion;

    public RPNCalculator() {
    }

    public void setInput(String input) {
        this.expresion = input;
    }

    public Map<String, Double> getmContentOfCell() {
        return mContentOfCell;
    }

    public void setmContentOfCell(Map<String, Double> mContentOfCell) {
        this.mContentOfCell = mContentOfCell;
    }

    public double calculateRPN() {
        String rpnInput[] = expresion.split(" ");
        Stack<String> stack = new Stack<String>();
        for (String token : rpnInput) {
            if (OPERATOR_ALLOWED.contains(token)) {
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                double operand1Value, operand2Value;
                if (NumbericUtil.isDoubleNumberic(operand1)) {
                    operand1Value = Double.parseDouble(operand1);
                } else {
                    operand1Value = mContentOfCell.get(operand1);
                }

                if (NumbericUtil.isDoubleNumberic(operand2)) {
                    operand2Value = Double.parseDouble(operand2);
                } else {
                    operand2Value = mContentOfCell.get(operand2);
                }

                switch (token) {
                    case "+":
                        stack.push(String.valueOf(operand1Value + operand2Value));
                        break;
                    case "-":
                        stack.push(String.valueOf(operand2Value - operand1Value));
                        break;
                    case "*":
                        stack.push(String.valueOf(operand1Value * operand2Value));
                        break;
                    case "/":
                        stack.push(String.valueOf(operand2Value / operand1Value));
                        break;
                }
            } else {
                stack.push(token);
            }
        }
        return Double.valueOf(stack.pop());
    }

}
