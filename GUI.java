/*
 * Course:  	CMSC 350
 * File: 	GUI.java
 * Author: 	Andrew H. Rohn
 * Date: 	03 November 2018
 * Purpose: 	Contains the Main Method, GUI, and ActionListeners.
 */

import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame {

    // Initialization of TextFields, Labels, & Button
    JLabel inputLabel = new JLabel("Enter Infix Expression");
    JLabel resultLabel = new JLabel("Result");
    JTextField inputTextField = new JTextField("", 20);
    JTextField resultTextField = new JTextField("", 20);
    JButton button = new JButton("Evaluate");

	// Creates Object of InfixExpEval Class
	private InfixExpEval infixExpEval = new InfixExpEval();

	// Main Constructor Method for GUI
	public GUI() {

	    // Creation of inputPanel
	    JPanel inputPanel = new JPanel();
	    inputPanel.add(inputLabel);
	    inputPanel.add(inputTextField);
	    
	    // Creation of buttonPanel
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.add(button);
	    
	    // Creation of resultPanel
	    JPanel resultPanel = new JPanel();
	    resultPanel.add(resultLabel);
	    resultPanel.add(resultTextField);
	    resultTextField.setEditable(false);
	    
	    // Create of mainPanel that consists of other panels
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.add(inputPanel);
	    mainPanel.add(buttonPanel);
	    mainPanel.add(resultPanel);
	    add(mainPanel);

	    // ActionListener for Button
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		// Initialization of Infix Expression
	    		String infixExp = inputTextField.getText();

	    		// Checked Exception Methods
		  	    try {
		  	    	if (infixExp.isEmpty()) {
		  	    		throw new NullPointerException();
			  	    } else {
			  	    	String result = (infixExpEval.infixExpString(infixExp));
			  	    	resultTextField.setText(result);
			  	    }
		  	    } catch (NullPointerException nullPointer) {
		  	    	  JOptionPane.showMessageDialog(null, "Please enter an Infix Expression.", "ERROR!",
		  	    			  JOptionPane.ERROR_MESSAGE);
		  	    } catch (EmptyStackException emptyStack) {
			  	      JOptionPane.showMessageDialog(null, "Invalid input.\nCheck for correct syntax of parentheses, operators, and operands.", "ERROR!",
			  	    		  JOptionPane.ERROR_MESSAGE);
		  	    } catch (InvalidInputException invalidInput) {
			  	      JOptionPane.showMessageDialog(null, "Invalid input.\nThe Infix Expression can only contain "
			  	    		  + "integer operands (0-9)\n and the following arithmetic operators:\n +   -   *   /   ().", "ERROR!",
			  	    		  JOptionPane.ERROR_MESSAGE);
		  	    } catch (DivideByZero divideByZero) {
			  	      JOptionPane.showMessageDialog(null, "Division by zero.", "ERROR!",
			  	    		  JOptionPane.ERROR_MESSAGE);
		  	    }  
		    }
	    });	
	}

	// Main Method
	public static void main(String[] args) {

	    // Object for GUI Class
	    GUI frame =	new GUI();

	        // Frame Parameters
		frame.setTitle("Infix Expression Evaluator");
		frame.setSize(400, 145);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
