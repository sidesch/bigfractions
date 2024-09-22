package edu.grinnell.csc207.util;

/**
 * A simple calculator for use with BigFractions.
 *
 * @author Sarah Deschamps
 */
public class BFCalculator {

  /** The last value calculated. */
  private BigFraction prev;

  /**
   * Build a new BFCalculator with no parameters, setting prev to 0.
   */
  public BFCalculator() {
    this.prev = new BigFraction(0);
  } // BFCalculator()

  /**
   * Gets the last computed value (or 0 if there is no such value).
   *
   * @return the previous BigFraction computed
   */
  public BigFraction get() {
    return this.prev;
  } // get()

  /**
   * Adds val to the last computed value.
   *
   * @param val
   *    The addend.
   */
  public void add(BigFraction val) {
    BigFraction fraction = this.prev.add(val);
    this.prev = fraction;
  } // add(BigFraction)

  /**
   * Subtracts a val from the last computed value.
   *
   * @param val
   *    The subtrahend.
   */
  public void subtract(BigFraction val) {
    BigFraction fraction = this.prev.subtract(val);
    this.prev = fraction;
  } // subtract(BigFraction)

  /**
   * Multiplies the last computed value by val.
   *
   * @param val
   *    The multiplier.
   */
  public void multiply(BigFraction val) {
    BigFraction fraction = this.prev.multiply(val);
    this.prev = fraction;
  } // multiply(BigFraction)

  /**
   * Divides the last computed value by val.
   *
   * @param val
   *    The divisor.
   */
  public void divide(BigFraction val) {
    BigFraction fraction = this.prev.divide(val);
    this.prev = fraction;
  } // divide(BigFraction)

  /**
   * Resets the last computed value to 0.
   */
  public void clear() {
    this.prev = new BigFraction(0);
  } // clear()
} // class BFCalculator
