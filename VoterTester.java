//Program tests imitation opinion dynamics
public class VoterTester
{
	public static void main(String[] args)
	{
		//create a voter object
		Voter v = new Voter(100, 10000, .1, 46235, 5);
		
		//run simulation
		v.run();
	}
}