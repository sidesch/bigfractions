package edu.grinnell.csc207.util;

/**
 * A set of registers corresponding to lowercase letters.
 *
 * @author Sarah Deschamps
 */
public class BFRegisterSet {

  /** A list of registers, representing the 26 letters. */
  private BigFraction[] registers;

  /** The number of letters in the alphabet. */
  private static final int NUM_LETTERS = 26;

  /**
   * Build a new BFRegisterSet.
   */
  public BFRegisterSet() {
    registers = new BigFraction[NUM_LETTERS];
  } // BFRegisterSet()

  /**
   * Stores the given value in the specified register.
   *
   * @param register
   *    The register to store the value in. Takes lowercase letters.
   * @param val
   *    The BigFraction to store in the register.
   */
  public void store(char register, BigFraction val) {
    int index = (int) register - 'a';
    this.registers[index] = val;
  } // store(char, BigFraction)

  /**
   * Retrieves the value from the given register.
   *
   * @param register
   *    The register (a lowercase letter) where the BigFraction is stored.
   *
   * @return the BigFraction at the given register.
   */
  public BigFraction get(char register) {
    int index = (int) register - 'a';
    return this.registers[index];
  } // get(char)
} // class BFRegisterSet
