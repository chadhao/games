
public class Player
{
	private String name;
	private int score;
	
	public Player(String name)
	{
		this.name = name;
		this.score = 0;
	}
	
	public Player()
	{
		this.name = "UNKNOWN";
		this.score = 0;
	}
	
	@Override
	public String toString()
	{
		return name + ": " + score;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	
}
