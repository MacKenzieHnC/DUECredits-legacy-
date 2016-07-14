package sourceBooksAndItems;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import helper.Settings;

public class SourceBooks
{

	Scanner reader;
	String[][] sourceBooks;
	int sourceBookCount = 0;
    
    int fontSize;
    {
    	Settings setting = new Settings();
    	fontSize = setting.getSettingNumber(17);
    }
	
	public SourceBooks()
	{
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			{
				JOptionPane.showMessageDialog(new javax.swing.JFrame(),
						"<html><span style='font-size:" + fontSize + "'>"
								+ "Error establishing UI in class \"GUI.\""
								+ "<br>"
								+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");
			}
			e.printStackTrace();
			try
			{
				Desktop.getDesktop().open(new File("resources/DUECredits Errors.txt"));
			}
			catch (IOException e1)
			{
				{
					JOptionPane.showMessageDialog(new javax.swing.JFrame(),
							"<html><span style='font-size: " + fontSize
									+ "'>"
									+ "Error opening \"DUECredits Errors.txt.\""
											+ "<br>Check to make sure it exists."
											+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");
				}
			}
		}
		

	
		
		try
		{
			reader = new Scanner(new File("resources/SourceBooks.txt"));
		}
		catch (FileNotFoundException e)
		{
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:" + fontSize + "'>"
					+ "Error opening file \"SourceBooks.txt.\" in class \"SourceBooks.\""
					+ "<br>"
					+ "Make sure it is in the \"resources\" folder and try again."
					+ "<br>"
					+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");}
			e.printStackTrace();
			try
			{
				Desktop.getDesktop().open(new File("resources/DUECredits Errors.txt"));
			}
			catch (IOException e1)
			{
				{
					JOptionPane.showMessageDialog(new javax.swing.JFrame(),
							"<html><span style='font-size: " + fontSize
									+ "'>"
									+ "Error opening \"DUECredits Errors.txt.\""
											+ "<br>Check to make sure it exists."
											+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");
				}
			}
			System.exit(-1);
		}
		
		String temp = "Radical";
		
		while(!temp.equals("Custom"))
		{
			
			temp = reader.nextLine();
			reader.nextLine();
			sourceBookCount++;
			
		}
		
		sourceBookCount--;
		
		sourceBooks = new String[sourceBookCount][2];
			
		
		try
		{
			reader = new Scanner(new File("resources/SourceBooks.txt"));
		}
		catch (FileNotFoundException e)
		{
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:" + fontSize + "'>"
					+ "Error opening file \"SourceBooks.txt.\" in class \"SourceBooks.\""
					+ "<br>"
					+ "Make sure it is in the \"resources\" folder and try again."
					+ "<br>"
					+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");}
			e.printStackTrace();
			try
			{
				Desktop.getDesktop().open(new File("resources/DUECredits Errors.txt"));
			}
			catch (IOException e1)
			{
				{
					JOptionPane.showMessageDialog(new javax.swing.JFrame(),
							"<html><span style='font-size: " + fontSize
									+ "'>"
									+ "Error opening \"DUECredits Errors.txt.\""
											+ "<br>Check to make sure it exists."
											+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");
				}
			}
			System.exit(-1);
		}
		
		for(int i = 0; i < sourceBookCount; i++)
		{
			
			sourceBooks[i][0] = reader.nextLine();
			sourceBooks[i][1] = reader.nextLine();
			
		}
		
		reader.close();
		
		sortString();
		
		addCustom();
		
	}
	
	public void sortString()

	{

		int columnNumber = 0, rowNumber = 0;
		String[][] tempArray = new String[sourceBookCount][2];
		String lowestValue;

		int count = 0;
		int lowestValidRow = 0;

		while (count < sourceBookCount)
		{
			try
			{

				lowestValue = sourceBooks[lowestValidRow][columnNumber]
						.replaceAll("[^A-Za-z\\d]", "");

				rowNumber = lowestValidRow;
				for (int j = 1; j < sourceBookCount; j++)
				{
					try
					{
						if (sourceBooks[j][columnNumber]
								.replaceAll("[^A-Za-z\\d]", "")
								.compareTo(lowestValue) < 0)

						{
							lowestValue = sourceBooks[j][columnNumber]
									.replaceAll("[^A-Za-z\\d]", "");
							rowNumber = j;
						}
					}
					catch (NullPointerException e)
					{

					}
				}
				tempArray[count] = sourceBooks[rowNumber];
				count++;
				sourceBooks[rowNumber] = null;
			}
			catch (NullPointerException e)
			{
				lowestValidRow++;
			}
		}

		sourceBooks = tempArray;

	}
	
	public void addCustom()
	{
		
		String[][] tempArray = new String[(1 + sourceBookCount)][2];
		
		for(int i = 0; i < sourceBookCount; i++)
		{
			
			tempArray[i] = sourceBooks[i];
			
		}
		
		
		tempArray[sourceBookCount][0] = "Custom";
		tempArray[sourceBookCount][1] = "Custom";
		
		sourceBookCount++;
		
		sourceBooks = tempArray;
		
	}
	
	public int getSourceBookCount()
	{
		
		return sourceBookCount;
		
	}
	
	public int getRowNumber(String sourceBookName)
	{
		
		int rowNumber = -1;
		
		try
		{
			
			
			for(int i = 0; i < sourceBookCount; i++)
			{
				
				if(sourceBooks[i][0].equals(sourceBookName))
				{
					
					rowNumber = i;
					
				}
				
			}
			
			return rowNumber;
		}
		catch(IndexOutOfBoundsException e)
		{
			
			return -1;
			
		}
		
	}
	
	public String getSourceBookName(int number)
	{
		
		if(number < sourceBookCount)
		{
			
			return sourceBooks[number][0];
			
		}
		else
		{
			
			return "ERROR";
			
		}
		
	}
	
	public String getSourceBookAbbreviation(int number)
	{
		
		if(number < sourceBookCount)
		{
			
			return sourceBooks[number][1];
			
		}
		else
		{
			
			return "ERROR";
			
		}
		
	}

	public void saveSourceBooks()
	{
		
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(new File("resources/SourceBooks.txt"));
		}
		catch (FileNotFoundException e)
		{
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:" + fontSize + "'>"
					+ "Error opening file \"SourceBooks.txt.\" in class \"SourceBooks.\""
					+ "<br>"
					+ "Make sure it is in the \"resources\" folder and try again."
					+ "<br>"
					+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");}
			e.printStackTrace();
			try
			{
				Desktop.getDesktop().open(new File("resources/DUECredits Errors.txt"));
			}
			catch (IOException e1)
			{
				{
					JOptionPane.showMessageDialog(new javax.swing.JFrame(),
							"<html><span style='font-size: " + fontSize
									+ "'>"
									+ "Error opening \"DUECredits Errors.txt.\""
											+ "<br>Check to make sure it exists."
											+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");
				}
			}
		}
		
		for(int i = 0; i < sourceBookCount; i++)
		{
			
			pw.println(sourceBooks[i][0]);
			pw.println(sourceBooks[i][1]);
			
		}
		
		pw.close();
		
		
	}

	public void addSourceBook(String sourceName, String sourceAbbreviation)
	{
		sourceBookCount++;
		String[] newSource = new String[] {sourceName, sourceAbbreviation};
		String[][] temp = new String[sourceBookCount][2];
		
		temp[0] = newSource;
		
		for(int i = 1; i < sourceBookCount; i++)
		{
			
			temp[i] = sourceBooks[i - 1];
			
		}
		
		FileWriter fw = null;
		
			
			try
			{
				fw = new FileWriter(new File("resources/SourceSettings.txt"),true);
				fw.append(newSource[1] + "\ntrue\n");
				fw.close();
			}
			catch (IOException e)
			{
				System.out.println("RESOURCE LEAK! PANIC!!!!");
				e.printStackTrace();
				try
				{
					Desktop.getDesktop().open(new File("resources/DUECredits Errors.txt"));
				}
				catch (IOException e1)
				{
					{
						JOptionPane.showMessageDialog(new javax.swing.JFrame(),
								"<html><span style='font-size: " + fontSize
										+ "'>"
										+ "Error opening \"DUECredits Errors.txt.\""
												+ "<br>Check to make sure it exists."
												+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");
					}
				}
			}
			
		
		
		sourceBooks = temp;
		
    	
		
	}
	
}
