/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soithattha
 * @date Mar 20, 2019
 * @version
 * @description
 */
public class FileInputProvider implements IInputProvider {

    private String filePath;

    public FileInputProvider(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Scanner getScanner() {
        File inputFile = new File(filePath);
        try {
            return new Scanner(inputFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
