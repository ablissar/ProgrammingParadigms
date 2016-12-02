package com.adambliss.game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class View extends JFrame implements ActionListener {

	// Instructions declared outside the class
	// so it can be accessed from Controller
	private JLabel instructions;
	private JLabel instructionHint; 
	
	private class MyPanel extends JPanel {
		Controller controller;

        MyPanel(Controller c) {
            controller = c;
            addMouseListener(c);
        }

        public void paintComponent(Graphics g) {
            controller.update(g);
            revalidate();
        }
    }

	public void switchInstructions() {
		instructions.setVisible(!instructions.isVisible());
		instructionHint.setVisible(!instructionHint.isVisible());
	}
	
    public View(Controller c) throws Exception{
        setTitle("Adam Bliss Honors Project");
        setSize(1000, 700);
        MyPanel panel = new MyPanel(c);
        
        // Instructions set to not visible by default,
        // switched from controller
        instructions = new JLabel(
        		"<html> Instructions <br> "
        		+ "Use the arrow keys to move the player. <br>"
        		+ "Try to collect as many tokens as quickly as possible. <br>"
        		+ "Tokens are collected by touching them then touching the bin. <br>"
        		+ "When you touch the bin, your score will be displayed, your position and the tokens will reset. <br>"
        		+ "Press 'p' to resume."
        		+ " </html>");
        instructions.setOpaque(true);
        instructions.setBackground(Color.lightGray);
        //Border used as padding
        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);

        instructions.setBorder(paddingBorder);
        instructions.setVisible(false);
        panel.add(instructions);
        
        // JLabel containing hint for displaying instructions
        instructionHint = new JLabel("Press 'p' to pause game and display instructions.");
        instructionHint.setHorizontalAlignment(SwingConstants.LEFT);
        instructionHint.setVerticalAlignment(SwingConstants.BOTTOM);
        panel.add(instructionHint);
        
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(c);
    }

    public void actionPerformed(ActionEvent evt) {
        repaint();
    }
}
