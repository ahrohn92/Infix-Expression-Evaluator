/*
 * Course:  	CMSC 350
 * File: 	InfixExpEval.java
 * Author: 	Andrew H. Rohn
 * Date: 	03 November 2018
 * Purpose: 	Performs the infix expression evaluation.
 */

import java.util.*;
import java.util.regex.Pattern;

public class InfixExpEval {
	
	// Initialization of Operand and Operator Stacks
	private Stack<String> operandStack = new Stack<>();
	private Stack<String> operatorStack = new Stack<>();

	String infixExpString (String infixExp) throws EmptyStackException, DivideByZero, InvalidInputException {

	    // tokenizes of string containing the expression
	    List<String> tokens = tokenize(infixExp);


	    // INFIX EXPRESSION EVALUATION
	    
	    // while there are more tokens, get the next token
	    for (String token : tokens) {
	    	
	    	    // Patterns used to determine if token is an operand or operator
	    	    Pattern operandPattern = Pattern.compile("[\\d]");
	    	    Pattern operatorPattern = Pattern.compile("[+*/\\-]");
	    	
	    	    // Throws exception if token is not an operand, operator, or parenthesis
		    if (!token.matches(String.valueOf(operandPattern))
		    		&& !token.matches(String.valueOf(operatorPattern))
		    		&& !token.equals("(")
		    		&& !token.equals(")")) {
		        		throw new InvalidInputException();
		     }
	
		    // if it is an operand,
		    if (token.matches(String.valueOf(operandPattern))) {
		    	// push it onto the operand stack
		    	operandStack.push(token);
		    }
	
		    // else if it is a left parenthesis,
		    else if (token.equals("(")) {
		    	// push it onto the operator stack
		    	operatorStack.push(token);
		    }
	
		    // else if it is a right parenthesis,
		    else if (token.equals(")")) {

		        // while top of the operator stack is not a left parenthesis
		        while (!operatorStack.peek().equals("(")) {
	
		        	// pop two operands and an operator
		        	// perform the calculation
		        	// push the result onto the operand stack
		        	operandStack.push(calculation(operandStack.pop(), operatorStack.pop(), operandStack.pop()));
		        }
			operatorStack.pop();
		    }
	
		    // else if it is an operator,
		    else if (token.matches(String.valueOf(operatorPattern))) {
	
		    	// while the operator stack is not empty and 
		    	// the operator at the top of the stack has higher 
		    	// or the same precedence than the current operator
		        while (!operatorStack.empty() && precedence(operatorStack.peek()) >= precedence(token)) {
	
		        	// pop two operands and perform the calculation
		        	// push the result onto the operand stack
		        	operandStack.push(calculation(operandStack.pop(), operatorStack.pop(), operandStack.pop()));
		        }
	
		        // push the current operator on the operator stack
		        operatorStack.push(token);
		   }
	    }

	    // while the operator stack is not empty
	    while (!operatorStack.empty()) {

	    	// pop two operands and an operator
	    	// perform the calculation
	    	// push the result onto the operand stack
	    	operandStack.push(calculation(operandStack.pop(), operatorStack.pop(), operandStack.pop()));
	    }
	    return operandStack.pop();
	}


	// Method that tokenizes infix expression
	private List<String> tokenize(String infixExp) {

	    // Initialization of tokenArrayList
	    List<String> tokenArrayList = new ArrayList<>();

	    // Adds first character of string to tokenArrayList
	    tokenArrayList.add(Character.toString(infixExp.charAt(0)));

	    // Tokenizes rest of string
	    for (int i = 1; i < infixExp.length(); i++) {

	    	// Ignores Spaces in String
	    	if (infixExp.charAt(i) == ' ') {
		        continue;
		    }
	    	
	    	// Checks to see if characters are digits
	    	// If so, they are combined into single token
	    	char prevChar = infixExp.charAt(i-1);
	    	char currentChar = infixExp.charAt(i);

	    	if (Character.isDigit(prevChar) && Character.isDigit(currentChar)) {
	    		int prevIndex = (tokenArrayList.size()-1);
	    		tokenArrayList.set(prevIndex, tokenArrayList.get(prevIndex) + currentChar);
	    		
	    	// else, added as separate token
	    	} else {
	    		tokenArrayList.add(Character.toString(currentChar));
	    	}
	    }
	    return tokenArrayList;
	}
	
	// Method that determines precedence of operators
	private int precedence(String operator) {

	    // Integer Assignment for Precedence
	    int precedence = 0;

	    switch (operator) {
	    	case "+": precedence = 1;
	    		break;
	    	case "-": precedence = 1;
    			break;
	    	case "*": precedence = 2;
				break;
	    	case "/": precedence = 2;
				break;
	    }
	    return precedence;
	}
	
	// Method that performs calculation of two operands and an operator
	private String calculation(String operandTwo, String operator, String operandOne) throws DivideByZero {
		
	    // Variables for Calculation
	    int num1 = Integer.parseInt(operandOne);
	    int num2 = Integer.parseInt(operandTwo);
	    int result = 0;

	    switch (operator) {
	    	case "+": result = num1 + num2;
	        	break;
		    case "-": result = num1 - num2;
		        break;
	
		    case "*": result = num1 * num2;
		        break;
		    case "/": if (num2 == 0) {
		    		throw new DivideByZero("Division by zero");
		              }
		        	result = num1 / num2;
		        break;
	    }
	    return Integer.toString(result);
	}
}