import java.util.Random;

/*****************************************************************************/

//Program simulates opinion dynamics based on imitation
public class Voter
{
	private final int PLUS = 1;		//opinion one
	private final int MINUS = 2;	//opinion two
	
	private int[][] array;			//array of opinions
	private int[][] stubAgentArray;	//array of stubborn agent locations
	private int size;				//size of array
	private int iterations;			//number iterations
	private double density;			//initial density of plus option
	private int stubAgents;			//number of stubborn agents in plus population
	private Random random;			//random number generator
	private OpinionDrawer drawer;	//drawing object
	
	/*****************************************************************************/

	//Constructor of Voter class
	public Voter(int size, int iterations, double density, int seed, int stbAgnt)
	{
		this.array = new int[size][size];		//create array
		this.stubAgentArray = new int[size][size];		//create stuffborn agent location array
		this.size = size;						//set size
		this.iterations = iterations;			//set iterations
		this.density = density;					//set density
		this.random = new Random(seed);			//create random number generator
		this.drawer = new OpinionDrawer(array, size);	//create drawing object
		this.stubAgents = stbAgnt;
	}
	
	/*****************************************************************************/

	//Method runs simulation
	public void run()
	{
		//initialize opinions
		initialize();
		
		//run iterations
		for (int n = 0; n < iterations; n++)
		{
			//draw array
			draw();
			
			//update population
			for (int m = 0; m < size * size; m++)
			{
				//pick an agent randomly
				int i = random.nextInt(size);
				int j = random.nextInt(size);
				
				//change opinion of agent to neighbor's opinion
				array[i][j] = majority(i, j);
			}
		}
	}
	
	/*****************************************************************************/

	//Method initializes opinions
	private void initialize()
	{
		//go through all agents
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				//assign plus or minus opinion
				if (random.nextDouble() < density)
				{
					array[i][j] = PLUS;
				}
				else
				{
					array[i][j] = MINUS;
				}
				
				//initializing stubborn agent array
				stubAgentArray[i][j] = 0;
			}
		}
		
		//initializes stubborn Agents (for PLUS)
		while (stubAgents > 0)
		{
			int n = random.nextInt(size);
			int m = random.nextInt(size);
			
			if (array[n][m] == PLUS)
			{
				stubAgentArray[n][m] = 1;
				stubAgents = stubAgents - 1;
			}
		}
	}
	
	/*****************************************************************************/
	
	//Method finds the opinion of a random neighbor
	private int majority(int i, int j)
	{
		//value for saving majority opinion
		int opinionCountPLUS = 0;
		int opinionCountMINUS = 0;
		
		//the code below adds up the target agent's neighbors and compares the final value
		//to determine which opinion the agent should change to.
		
		//counts itself
		if (array[i][j] == PLUS)
		{
			opinionCountPLUS = opinionCountPLUS + 1;
		}
		else if (array[i][j] == MINUS)
		{
			opinionCountMINUS = opinionCountMINUS + 1;
		}
		
		//counts North
		if (array[(i-1+size)%size][j] == PLUS)
		{
			opinionCountPLUS = opinionCountPLUS + 1;
		}
		else if (array[(i-1+size)%size][j] == MINUS)
		{
			opinionCountMINUS = opinionCountMINUS + 1;
		}
		
		//counts East
		if (array[i][(j+1)%size] == PLUS)
		{
			opinionCountPLUS = opinionCountPLUS + 1;
		}
		else if (array[i][(j+1)%size] == MINUS)
		{
			opinionCountMINUS = opinionCountMINUS + 1;
		}
		
		//counts South
		if (array[(i+1)%size][j] == PLUS)
		{
			opinionCountPLUS = opinionCountPLUS + 1;
		}
		else if (array[(i+1)%size][j] == MINUS)
		{
			opinionCountMINUS = opinionCountMINUS + 1;
		}
		
		//counts West
		if (array[i][(j-1+size)%size] == PLUS)
		{
			opinionCountPLUS = opinionCountPLUS + 1;
		}
		else if (array[i][(j-1+size)%size] == MINUS)
		{
			opinionCountMINUS = opinionCountMINUS + 1;
		}
		
		if (opinionCountMINUS < opinionCountPLUS)
		{
			return PLUS;
		}
		else
		{
			return MINUS;
		}
	}
	
	/*****************************************************************************/

	//method draws array of opinions
	private void draw()
	{
		drawer.repaint();		//repaint array
		
		try {Thread.sleep(100);}	catch(Exception e){}	//pause
	}
	
	/*****************************************************************************/

}