package tetris;

import tetris.GameArea;

public class GameThread extends Thread 
{

	private GameArea ga;
	public GameThread(GameArea ga) {
		this.ga=ga;
	}
	public void run() //run is override
	{
		int i=0;
		while(1==1)
		{
			
				ga.spawnBlock();
			while(ga.moveBlockDown()==true)
			{
				
				try 
				{
					
					
					Thread.sleep(200);
				} catch (InterruptedException e) 
				{
					
					e.printStackTrace();
					
				}
			}
		}
	}
}
