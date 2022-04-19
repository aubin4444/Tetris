package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class GameArea extends JPanel
{
	private  int gridRows ;
	private  int gridColumns;
	private  int gridCellSize;
	private TetrisBlock block;
	

	private Color[][] background;
	
	
	public GameArea() {
		
		this.setBounds(50,100,100,100);
		this.setBackground(Color.red);
		
		gridRows=20;
		gridColumns=10;
		gridCellSize=20;
		
		background = new Color[gridRows][gridColumns];
		
	}
	
	public void spawnBlock()
	{
		
	
		block = new TetrisBlock(new int[][]{{1,0},{1,0},{1,1}}, Color.red);
		block.spawn();
		
		
		
	}
	
	
	public boolean checkBottom()
	{
		if(block.getY()+block.getHeight()==20) //if it touch the bottom
		{
			return false;
		}
		
		int[][] shape = block.getShape();
		int w = block.getWidth();
		int h = block.getHeight();
		
		for(int col = 0; col < w; col++) {
			for(int row = h - 1; row >= 0; row--) {
				if (shape[row][col] != 0)
				{
					int x = col + block.getX();
					int y = row + block.getY()+1;
					if(y < 0)break;
					if(background[y][x] != null)return false;
					break;
				}
			}
		}
		return true;
	}
	
	private boolean checkLeft()
	{
		if(block.getLeftEdge() == 0)return false;
		
		int[][] shape = block.getShape();
		int w = block.getWidth();
		int h = block.getHeight();
		
		for(int row = 0; row < h; row++) {
			for(int col = 0; col < w; col--) {
				if (shape[row][col] != 0)
				{
					int x = col + block.getX() - 1;
					int y = row + block.getY();
					if(y < 0)break;
					if(background[y][x] != null)return false;
					break;
				}
			}
		}
		
		return true;
	}
	
	private boolean checkRight()
	{
		if(block.getRightEdge() == gridColumns)return false;
		
		int[][] shape = block.getShape();
		int w = block.getWidth();
		int h = block.getHeight();
		
		for(int row = 0; row < h; row++) {
			for(int col = w - 1; col >= 0; col--) {
				if (shape[row][col] != 0)
				{
					int x = col + block.getX() + 1;
					int y = row + block.getY();
					if(y < 0)break;
					if(background[y][x] != null)return false;
					break;
				}
			}
		}
		
		return true;
	}
	
	public boolean moveBlockDown()
	{
		if(checkBottom() == false) 
			{
			moveBlockToBackground();
			return false;
			}
		
		block.moveDown();
		
		repaint();
		return true;
		
	}
	
	public void moveBlockRight()
	{
		if(!checkRight())return;
		
		block.moveRight();
		repaint();
	}
	
	public void moveBlockLeft()
	{
		if(!checkLeft())return;

		
		block.moveLeft();
		repaint();
	}
	
	public void dropBlock()
	{
		while(checkBottom())
		{
			block.moveDown();
		}
		repaint();
	}
	
	public void rotateBlock()
	{
		block.rotate();
		repaint();
	}
	
	private void drawBlock(Graphics g,TetrisBlock block)
	{
		Color c = block.getColor();
		int h = block.getHeight();
		int w = block.getWidth();
		int[][] shape = block.getShape();
		
		
		
		for(int row=0;row<h;row++) // hight of the square that contain the block	
		{
			for(int col=0;col<w;col++)// width of the square that contain the block
			{
				if(shape[row][col]==1)
				{
					int x = (block.getX()+col)*gridCellSize;
					int y = (block.getY()+row)*gridCellSize;
					
					
					drawGridSquare(g, c, x, y);
				}
			}
		}
	}
	
	private void moveBlockToBackground()
	{
		int [][] shape = block.getShape();
		int h = block.getHeight();
		int w = block.getWidth();
		
		int xPos = block.getX();
		int yPos = block.getY();
		
		Color color = block.getColor();
		
		for (int r = 0; r < h; r++)
		{
			for(int c = 0; c < w; c++)
			{
				if (shape[r][c] ==1)
				{
					background[r + yPos][c + xPos] = color;
				}
			}
		}
	}
	
	private void drawBackground(Graphics g)
	{
		Color color;
		
		for(int r=0;r<gridRows;r++)
		{
			for(int c=0;c<gridColumns;c++)
			{
				color = background[r][c];
				
				if (color != null)
				{
					int x =c * gridCellSize;
					int y = r * gridCellSize;
					
					drawGridSquare(g, color, x, y);
				}
			}
		}
	}
	
	private void drawGridSquare(Graphics g, Color color, int x, int y)
	{
		g.setColor(color);
		g.fillRect(x, y, gridCellSize, gridCellSize); //fill shape is needed
		g.setColor(Color.black);
		g.drawRect(x, y, gridCellSize, gridCellSize);
	}
	
	@Override
	protected void paintComponent(Graphics g) // overide paintcomponent of JPanel
	{
		
		super.paintComponent(g); //call paintcomponent method in JFrame
		for(int col=0;col<gridRows;col=col+1)
		{
			for(int row=0;row<gridColumns;row=row+1)
			{
				g.drawRect(row*gridCellSize,col*gridCellSize,gridCellSize,gridCellSize); //only outline is needed
			}
		}
		
		drawBackground(g);
		drawBlock(g,block);
		
		
	}
	
	
}