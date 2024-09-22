package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Sarah Deschamps
 * @author Sam Rebelsky for the original starter code
 */
public class BigFraction {
  /*
   * Denominators are always positive. Therefore, negative fractions
   * are represented with a negative numerator. Similarly, if a fraction
   * has a negative numerator, it is negative.
   */

  /** The numerator of the fraction. Can be positive, zero or negative. */
  private BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  private BigInteger denom;

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
    this.simplify();
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
    this.simplify();
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * @param str
   *   The fraction in string form, given a '/' character.
   */
  public BigFraction(String str) {
    String[] numbers = str.split("/");
    if (numbers.length > 1) {
      this.num = new BigInteger(numbers[0]);
      this.denom = new BigInteger(numbers[1]);
    } else {
      this.num = new BigInteger(numbers[0]);
      this.denom = BigInteger.valueOf(1);
    } // if the string only contains a whole number
      // or contains a fraction
    this.simplify();
  } // BigFraction

  /**
   * Build a new fraction from a whole number.
   *
   * @param numerator
   *    The whole number the fraction will represent.
   */
  public BigFraction(int numerator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(1);
  } // BigFraction(int)

  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    BigFraction frac = new BigFraction(resultNumerator, resultDenominator);
    frac.simplify();
    return frac;
  } // add(BigFraction)

  /**
   * Subtract another fraction to this fraction.
   *
   * @param subtrahend
   *    The fraction to subtract.
   *
   * @return the result of the subtraction.
   */
  public BigFraction subtract(BigFraction subtrahend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(subtrahend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(subtrahend.denom)).subtract(subtrahend.num.multiply(this.denom));

    // Return the computed value
    BigFraction frac =  new BigFraction(resultNumerator, resultDenominator);
    frac.simplify();
    return frac;
  } // subtract(BigFraction)

  /**
   * Multiply another fraction to this fraction.
   *
   * @param multiplier
   *    The fraction to multiply.
   *
   * @return the result of multiplication.
   */
  public BigFraction multiply(BigFraction multiplier) {
    BigInteger resultNumerator = this.num.multiply(multiplier.num);
    BigInteger resultDenominator = this.denom.multiply(multiplier.denom);

    BigFraction frac = new BigFraction(resultNumerator, resultDenominator);
    frac.simplify();
    return frac;
  } // multiply(BigFraction)

  /**
   * Divides this fraction by another fraction.
   *
   * @param divisor
   *    The fraction to divide this fraction by.
   *
   * @return the result of division.
   */
  public BigFraction divide(BigFraction divisor) {
    BigFraction reciprocal = new BigFraction(divisor.denom, divisor.num);
    BigFraction result = this.multiply(reciprocal);
    if (result.denom.compareTo(BigInteger.valueOf(0)) < 0) {
      result.num = result.num.multiply(BigInteger.valueOf(-1));
      result.denom = result.denom.multiply(BigInteger.valueOf(-1));
    } // if denominator is negative
    return result;
  } // divide(BigFraction)

  /**
   * Simplifies this fraction.
   */
  public void simplify() {
    BigInteger a = this.num.abs();
    BigInteger b = this.denom.abs();
    while (b != BigInteger.valueOf(0)) {
      BigInteger oldB = b;
      b = a.mod(b);
      a = oldB;
    } // while b is not 0
    BigInteger gcd = a;
    BigInteger numerator = this.num;
    BigInteger denominator = this.denom;
    numerator = numerator.divide(gcd);
    denominator = denominator.divide(gcd);
    this.num = numerator;
    this.denom = denominator;
  } // simplify()

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero
    if (this.denom.equals(BigInteger.valueOf(1))) {
      return this.num.toString();
    } // if it's a whole number
    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()
} // class BigFraction
