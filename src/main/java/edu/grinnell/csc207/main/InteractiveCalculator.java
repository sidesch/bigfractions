package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

/**
 * An interactive calculator for big fractions.
 * @author Sarah Deschamps
 */
public class InteractiveCalculator {
  /**
   * The main function running the interactive calculator.
   * @param args
   *    The arguments sent into the function.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);
    String line = eyes.nextLine();
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registers = new BFRegisterSet();
    while (!line.contains("QUIT")) {
      lineParser(pen, line, calculator, registers, false);
      line = eyes.nextLine();
    } // while the user does not want to quit the program
    eyes.close();
    pen.flush();
  } // main(String[])

  /**
   * Parses each line given and prints out the appropriate
   * result on a new line.
   * @param pen
   *    PrintWriter used to print output.
   * @param line
   *    String to parse with big fractions.
   * @param calculator
   *    Calculator to use with big fractions.
   * @param registers
   *    Registers to store results in.
   * @param arrows
   *    Whether or not to add arrows before the result.
   */

  public static void lineParser(PrintWriter pen, String line,
                                BFCalculator calculator, BFRegisterSet registers, boolean arrows) {
    if (line.equals("")) {
      System.err.println("ERROR [Invalid expression: empty string] ***");
      return;
    } // if it's an empty string
    String[] vals = line.split(" ");
    BigFraction current = calculator.get();
    String operator = "";
    String[] operators = {"+", "-", "*", "/", "STORE"};
    String[] mathStrings = {"+", "-", "*", "/"};
    if (Arrays.asList(mathStrings).contains(vals[0])
        || Arrays.asList(mathStrings).contains(vals[vals.length - 1])) {
      System.err.println("*** ERROR [Invalid expression: "
                         + "operator in beginning or end of line] ***");
      return;
    } // if there's an operator at the beginning or end of the line
    boolean lastWasNum = false;
    for (String val : vals) {
      if (Arrays.asList(operators).contains(val)) {
        operator = val;
        lastWasNum = false;
      } else if (val.matches(".*\\d.*")
                 || (val.length() == 1
                 && (val.charAt(0) >= 'a' && val.charAt(0) <= 'z'))) {
        if (lastWasNum) {
          System.err.println("*** Error [Invalid expression: "
                              + "two variables or numbers in a row] ***");
          return;
        } // if the previous value was a digit or variable
        lastWasNum = true;
        if (val.matches(".*\\d.*")) {
          current = new BigFraction(val);
        } else {
          current = registers.get(val.charAt(0));
        } // set current equal to the number or variable at hand
        if (operator.equals("STORE")) {
          if (val.charAt(0) < 'a' || val.charAt(0) > 'z') {
            System.err.println("*** ERROR [Invalid Register: " + val.charAt(0) + "] ***");
            return;
          } // if the character is not lowercase
          registers.store(val.charAt(0), calculator.get());
        } else if (operator.equals("+")) {
          calculator.add(current);
        } else if (operator.equals("-")) {
          calculator.subtract(current);
        } else if (operator.equals("*")) {
          calculator.multiply(current);
        } else if (operator.equals("/")) {
          calculator.divide(current);
        } else if (operator.equals("")) {
          calculator.clear();
          calculator.add(current);
        } // if-else operator check
      } else {
        System.err.println("*** ERROR [Invalid Expression] ***");
        return;
      } // if-else check type of argument
    } // for each value in values
    if (arrows) {
      pen.print(line + " -> ");
    } // if printing arrows before results
    pen.println(calculator.get());
  } // lineParser(PrintWriter, String, BFCalculator, BFRegisterSet, boolean)
} // class InteractiveCalculator
