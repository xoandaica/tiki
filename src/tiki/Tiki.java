/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki;

import tiki.input.FileInputProvider;
import java.io.FileNotFoundException;
import tiki.input.IInputProvider;
import tiki.input.SystemInputProvider;

/**
 *
 * @author soithattha
 */
public class Tiki {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
//            IInputProvider inputProvider = new FileInputProvider("G:\\test\\input.txt");
            IInputProvider inputProvider = new SystemInputProvider();
            SimpleExcelApplication app = new SimpleExcelApplication();
            app.setInputProvider(inputProvider);
            app.readInput();
            app.process();
            app.printOutPut();
        } catch (Exception e) {
        }

    }

}
