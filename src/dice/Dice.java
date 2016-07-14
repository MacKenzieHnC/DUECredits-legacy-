package dice;

public class Dice
{

	int[][] die;
	int[] roll;
	int faceCount;
	
	public int[][] dice(String diceType)
	{
		
		if(diceType.equals("Ability"))
		{
			faceCount = 8;
			
			die = new int[faceCount][];
			die[0] = new int[]{1,0,0};
			die[1] = new int[]{1,0,0};
			die[2] = new int[]{2,0,0};
			die[3] = new int[]{0,1,0};
			die[4] = new int[]{0,1,0};
			die[5] = new int[]{0,2,0};
			die[6] = new int[]{1,1,0};
			die[7] = new int[]{0,0,0};
			
		}
		else if(diceType.equals("Proficiency"))
		{
			faceCount = 12;
			die = new int[faceCount][];
			die[0] = new int[]{1,0,0};
			die[1] = new int[]{1,0,0};
			die[2] = new int[]{2,0,0};
			die[3] = new int[]{2,0,0};
			die[4] = new int[]{0,1,0};
			die[5] = new int[]{0,2,0};
			die[6] = new int[]{0,2,0};
			die[7] = new int[]{1,1,0};
			die[8] = new int[]{1,1,0};
			die[9] = new int[]{1,1,0};
			die[10] = new int[]{0,0,1};
			die[11] = new int[]{0,0,0};
			
			
		}
		else if(diceType.equals("Boost"))
		{
			
			faceCount = 6;
			die = new int[faceCount][];
			die[0] = new int[]{1,0,0};
			die[1] = new int[]{0,1,0};
			die[2] = new int[]{0,2,0};
			die[3] = new int[]{1,1,0};
			die[4] = new int[]{0,0,0};
			die[5] = new int[]{0,0,0};
			
			
		}
		else if(diceType.equals("Difficulty"))
		{
			
			faceCount = 8;
			die = new int[faceCount][];
			die[0] = new int[]{-1, 0, 0};
			die[1] = new int[]{-2, 0, 0};
			die[2] = new int[]{0, -1, 0};
			die[3] = new int[]{0, -1, 0};
			die[4] = new int[]{0, -1, 0};
			die[5] = new int[]{0, -2, 0};
			die[6] = new int[]{-1, -1, 0};
			die[7] = new int[]{0, 0, 0};
			
		}
		else if(diceType.equals("Setback"))
		{
			
			faceCount = 6;
			die = new int[faceCount][];
			die[0] = new int[]{-1, 0, 0};
			die[1] = new int[]{-1, 0, 0};
			die[2] = new int[]{0, -1, 0};
			die[3] = new int[]{0, -1, 0};
			die[4] = new int[]{0, 0, 0};
			die[5] = new int[]{0, 0, 0};
			
		}
		
		return die;
		
		
	}
	
	public int getFaceCount()
	{
		return faceCount;
	}
	
	public int[] getFace(int faceNumber)
	{
		
		return die[faceNumber];
		
	}
	
}
