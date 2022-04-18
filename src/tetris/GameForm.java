package tetris;
import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.event.ActionEvent;

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
		
		initControls();
		startGame();
	}
	
	private void initControls()
	{
		InputMap im = this.getRootPane().getInputMap();
		ActionMap am = this.getRootPane().getActionMap();
		
		im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		im.put(KeyStroke.getKeyStroke("LEFT"), "left");
		im.put(KeyStroke.getKeyStroke("UP"), "up");
		im.put(KeyStroke.getKeyStroke("DOWN"), "down");

		
		am.put("right", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ga.moveBlockRight();
				}
			});	
		
		am.put("left", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ga.moveBlockLeft();
				}
			});	
		
		am.put("up", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ga.rotateBlock();
				}
			});	
		
		am.put("down", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ga.dropBlock();
				}
			});	
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

