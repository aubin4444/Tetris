package tetris;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class GameForm extends JFrame{
	private GameArea ga;
	
	public GameForm()
	{
		new JFrame();
		this.setSize(552, 534);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		//initComponents();
		ga = new GameArea();
		springLayout.putConstraint(SpringLayout.NORTH, ga, 20, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ga, 175, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ga,420 , SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ga, -163, SpringLayout.EAST, getContentPane());
		ga.setBorder(new LineBorder(new Color(0, 0, 0)));
		ga.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(ga);
		startGame();
	}
	
	public void startGame()
	{
		new GameThread(ga).start(); //start is a method of Thread class
	}
	
	public static void main(String args[]) {
		
		
		//Create and display de form
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				new GameForm().setVisible(true);
			}
		});
	}
}

