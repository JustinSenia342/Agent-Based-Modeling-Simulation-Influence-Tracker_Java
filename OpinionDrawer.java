import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

//Program draws array of two types of opinions
public class OpinionDrawer extends JFrame
{
	private final int PLUS = 1;		//type one vehicle
	private final int MINUS = 2;	//type two vehicle
	
	private int[][] array;			//array of vehicles
	private int size;				//size of array
	
	/*******************************************************************/
	
	//constructor of opinionDrawer class
	public OpinionDrawer(int[][] array, int size)
	{
		setSize(5*size, 5*size);		//set window size to 5*size
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
										//standard window settings
		setVisible(true);
		
		this.array = array;				//set array to be drawn
		
		this.size = size;				//set array size
	}
	
	/*******************************************************************/
	
	//method paints window
	public void paint(Graphics g)
	{
		//go through array
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				if (array[i][j] == PLUS)		//draw type one opinion
				{								//as red square
					g.setColor(Color.WHITE);
					g.fillRect(5*j, 5*i, 5, 5);
				}
				else							//draw type two opinion
				{								//as Black square
					g.setColor(Color.BLACK);
					g.fillRect(5*j, 5*i, 5, 5);
				}
			}
		}
	}
	
	/*******************************************************************/
	
}
