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
	private List<TetrisBlock> fallenBlocks;
	private int i=0;
	
	
	public GameArea() {
		
		this.setBounds(50,100,100,100);
		this.setBackground(Color.red);
		
		gridRows=20;
		gridColumns=10;
		gridCellSize=20;
		fallenBlocks = new ArrayList<TetrisBlock>();
		
		
	}
	
	public void spawnBlock()
	{
		if(i!=0)
		{
			fallenBlocks.add(block);
		}
		
		block = new TetrisBlock(new int[][]{{1,0},{1,0},{1,1}}, Color.red);
		block.spawn();
		
		i++;
		
		
	}
	
	
	public boolean checkBottom()
	{
		
		if(block.getY()+block.getHeight()==20) //if it touches the bottom
		{
			
			return false;
		}else {
		
		int[][] shape = block.getShape();
		int w = block.getWidth();
		int h = block.getHeight();
		
		for(int col = 0; col < w; col++) {
			for(int row = h - 1; row >= 0; row--) {
				if (shape[row][col] != 0)
				{
					int x = col + block.getX();
					int y = row + block.getY()+1;
					if(y<0) break;
					//if(fallenBlocks[y][x] != null) return false;
					//break;
					if(fallenBlocks.size()>0)
					{
						for(TetrisBlock elem: fallenBlocks)
						{
							int[][] gs=elem.getShape();
							int haut = elem.getX();
							int large = elem.getY();
							int width = elem.getWidth();
							int hight=elem.getHeight();
							
							
								for(int ii=0;ii<width;ii++) //On regarde pour chaque 
								{
									if(gs[0][ii]==1)
									{
										int x1 = (haut+ii);
										int y1 = (large);
										if(x==x1 && y==y1)
										{
											return false;
										}
									}
								}
							
						}
					}
				}
			}
		}
		return true;
		}
	}
	
	private boolean checkLeft()
	{
		if(block.getLeftEdge() == 0)return false;
		
		return true;
	}
	
	private boolean checkRight()
	{
		if(block.getRightEdge() == gridColumns)return false;
		
		return true;
	}
	
	public boolean moveBlockDown()
	{
		if(checkBottom() == false) 
			{
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
					
					
					g.setColor(c);
					g.fillRect(x, y, gridCellSize, gridCellSize); //fill shape is needed
					g.setColor(Color.black);
					g.drawRect(x, y, gridCellSize, gridCellSize);
				}
			}
		}
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
		
		drawBlock(g,block);
		for(TetrisBlock elem: fallenBlocks)
		{
			drawBlock(g,elem);
		}
		
	}
	
	
}