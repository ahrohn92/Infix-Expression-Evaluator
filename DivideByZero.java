/*
 * Course:  	CMSC 350
 * File: 	DivideByZero.java
 * Author: 	Andrew H. Rohn
 * Date: 	03 November 2018
 * Purpose: 	Contains DivideByZero exception constructor.
 */

class DivideByZero extends Exception {

	// Constructor that accepts a message
  	DivideByZero(String message) {
		super(message);
  	}
}