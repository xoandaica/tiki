/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki.util;

/**
 *
 * @author soithattha
 * @date Mar 20, 2019
 * @version
 * @description
 */
public class NumbericUtil {

    /**
     * validate input is double or not, accept -number and +number
     *
     * @param number
     * @return
     */
    public static boolean isDoubleNumberic(String number) {
        return number.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * format double 2.0 to 2 or 1.2 to 1.2
     *
     * @param n
     * @return
     */
    public static String beautifierNumber(double n) {
        if ((int) n == n) {
            return Integer.toString((int) n);
        }
        return String.valueOf(n);
    }
}
