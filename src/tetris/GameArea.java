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
	private List<TetrisBlock> blocksFell;
	private int i=0;
	
	
	public GameArea() {
		
		this.setBounds(50,100,100,100);
		this.setBackground(Color.red);
		
		gridRows=20;
		gridColumns=10;
		gridCellSize=20;
		blocksFell = new ArrayList<TetrisBlock>();
		
		System.out.println("salut fdp");
		
	}
	
	public void spawnBlock()
	{
		
		System.out.println(i);
		if(i!=0)
		{
			blocksFell.add(block);
			if(i>1)
			{
				for(TetrisBlock elem: blocksFell)
			       {
			       	 System.out.println (elem);
			       }
			}
		}
		block = new TetrisBlock(new int[][]{{1,0},{1,0},{1,1}}, Color.red);
		block.spawn();
		i++;
		
	}
	
	public boolean checkBottom()
	{
		if(block.getY()+block.getHeight()==20) //if it touch the bottom
		{
			return false;
		}
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
	private void drawBlock(Graphics g)
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
		//spawnBlock();
		drawBlock(g);
		
	}
	public TetrisBlock getBlock()
	{
		return block;
	}
	
}
