/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki.input;

import java.util.Scanner;

/**
 *
 * @author soithattha
 * @date Mar 20, 2019
 * @version
 * @description
 */
public class SystemInputProvider implements IInputProvider {

    @Override
    public Scanner getScanner() {
        return new Scanner(System.in);
    }

}
