package dice;

import java.util.Random;

public class DicePool
{

	int numProficiencyDice;
	int numAbilityDice;
	int numDifficultyDice;
	int[][][] dicePool;
	int diceCount;
	int count;
	int leftoverSuccesses;
	int leftoverAdvantages;
	int leftoverTriumphs;
	int[] totalRoll;
	int[] dieFaceCount;
	Random rn = new Random();
	private Dice dice;

	int difficultyDieFaceCount = 8;
	
	public DicePool(int skillLevel, 
					int characteristicLevel, 
					int numBoostDice,
					int numSetbackDice)
	{
		
		if(characteristicLevel >= skillLevel)
		{
			
			numAbilityDice = characteristicLevel - skillLevel;
			numProficiencyDice = skillLevel;
			
		}
		else
		{
			
			numAbilityDice = skillLevel - characteristicLevel;
			numProficiencyDice = characteristicLevel;
			
		}
		
		diceCount = numAbilityDice + numProficiencyDice 
				+ numBoostDice + numSetbackDice; 
		
		dicePool = new int[diceCount][][];
		dieFaceCount = new int[diceCount];
		count = 0;
		for(int i = 0; i < numAbilityDice; i++)
		{
			dice = new Dice();
			dicePool[i] = dice.dice("Ability");
			dieFaceCount[i] = dice.getFaceCount();
			
		}
		count = numAbilityDice;

		for(int i = count; i < count + numProficiencyDice; i++)
		{
			
			dice = new Dice();
			dicePool[i] = dice.dice("Proficiency");
			dieFaceCount[i] = dice.getFaceCount();
		}
		count += numProficiencyDice;

		for(int i = count; i < count + numBoostDice; i++)
		{
			
			dice = new Dice();
			dicePool[i] = dice.dice("Boost");
			dieFaceCount[i] = dice.getFaceCount();
		}
		count += numBoostDice;

		for(int i = count; i < count + numSetbackDice; i++)
		{
			
			dice = new Dice();
			dicePool[i] = dice.dice("Setback");
			dieFaceCount[i] = dice.getFaceCount();
		}
		count += numSetbackDice;
		
	}
	
	public void roll(int itemRarity)
	{
		leftoverSuccesses = 0;
		leftoverAdvantages = 0;
		leftoverTriumphs = 0;
		numDifficultyDice = itemRarity/2;
		
		int[][] difficultyDice = dice.dice("Difficulty");
		
		for(int i = 0; i < diceCount; i++)
		{
			
			leftoverSuccesses += dicePool[i][rn.nextInt(dieFaceCount[i] - 1)][0];
			
		}
		for(int i = 0; i < numDifficultyDice; i++)
		{
			
			leftoverSuccesses += difficultyDice[rn.nextInt(difficultyDieFaceCount - 1)][0];
			
		}
		
		for(int i = 0; i < diceCount; i++)
		{
			
			leftoverAdvantages += dicePool[i][rn.nextInt(dieFaceCount[i] - 1)][1];
			
		}
		for(int i = 0; i < numDifficultyDice; i++)
		{
			
			leftoverAdvantages += difficultyDice[rn.nextInt(difficultyDieFaceCount - 1)][1];
			
		}
		
		for(int i = 0; i < diceCount; i++)
		{
			
			leftoverTriumphs += dicePool[i][rn.nextInt(dieFaceCount[i] - 1)][2];
			
		}
	
		totalRoll = new int[3];
		totalRoll[0] = leftoverSuccesses;
		totalRoll[1] = leftoverAdvantages;
		totalRoll[2] = leftoverTriumphs;
		
	}
	
	public int[] getTotalRoll()
	{
		
		return totalRoll;
		
	}
	
	
}
