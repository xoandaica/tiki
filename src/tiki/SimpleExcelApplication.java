/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import tiki.util.RPNCalculator;
import tiki.util.NumbericUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import tiki.input.IInputProvider;

/**
 *
 * @author soithattha
 * @date Mar 20, 2019
 * @version
 * @description
 */
public class SimpleExcelApplication {

    /**
     * map output be sorted by asc
     */
    Map<String, Double> mOutputSorted;

    /**
     *
     */
    Map<String, Double> mContentOfCell;

    /**
     * map hold input
     */
    Map<String, String> mInput;

    /**
     * input provider maybe system.in or from file
     */
    IInputProvider inputProvider;

    public SimpleExcelApplication() {
        mOutputSorted = new TreeMap<>();
        mContentOfCell = new ConcurrentHashMap<>();
        mInput = new HashMap<>();
    }

    public void setInputProvider(IInputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    /**
     * read input from stdin
     *
     * @throws Exception
     */
    public void readInput() throws Exception {
        if (inputProvider == null) {
            throw new Exception("Input Provider not found!!");
        }
        Scanner sc = inputProvider.getScanner();
        int N = sc.nextInt();
        int begin = 0;
        sc.nextLine();
        while (begin++ < N) {
            String cellId = sc.nextLine();
            String cellContent = sc.nextLine();
            if (NumbericUtil.isDoubleNumberic(cellContent)) {
                mContentOfCell.put(cellId, Double.parseDouble(cellContent));
            }
            mInput.put(cellId, cellContent);

        }
        detectCicular();
        // validate  circular dependencies

    }

    public void detectCicular() {
        mInput.forEach((cellId, cellContent) -> {
            if (!NumbericUtil.isDoubleNumberic(cellContent)) {
                String arr[] = cellContent.split(" ");
                for (String token : arr) {
                    if (mInput.containsKey(token)) {
                        String contentOfToken = mInput.get(token);
                        if (contentOfToken.contains(cellId)) {
                            System.out.println(String.format("Circular dependency between %s and %s detected", token, cellId));
                        }
                    }
                }
            }
        });

    }

    public void process() {
        RPNCalculator calculator = new RPNCalculator();
        mInput.forEach((cellId, cellContent) -> {
            if (!NumbericUtil.isDoubleNumberic(cellContent)) {
                calculator.setmContentOfCell(mContentOfCell);
                calculator.setInput(cellContent);
                mOutputSorted.put(cellId, calculator.calculateRPN());
            } else {
                mOutputSorted.put(cellId, Double.parseDouble(cellContent));
            }
        });

    }

    /**
     * print output stdout
     */
    public void printOutPut() {
        mOutputSorted.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(NumbericUtil.beautifierNumber(value));
        });

        inputProvider.getScanner().close();
    }

    /**
     * print output to file
     */
    public void printOutPutToFile(String filePath) throws IOException {
        PrintStream streamOutput = new PrintStream(new File(filePath));
        System.setOut(streamOutput);
        mOutputSorted.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(NumbericUtil.beautifierNumber(value));
        });

        inputProvider.getScanner().close();
    }

}
