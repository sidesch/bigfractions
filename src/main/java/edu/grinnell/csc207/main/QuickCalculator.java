package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import java.io.PrintWriter;

/**
 * A quick command-line calculator to
 * use with big fractions.
 * @author Sarah Deschamps
 */
public class QuickCalculator {
  /**
   * Takes math calculations with big fractions
   * as arguments and prints results.
   * @param args
   *    Strings with expressions to solve sequentially.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registers = new BFRegisterSet();
    for (String line : args) {
      InteractiveCalculator.lineParser(pen, line, calculator, registers, true);
    } // for each string in arguments
  } // main(String[])
} // class QuickCalculator
