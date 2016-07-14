
package helper;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Settings
{

	private String settingString;
	private int settingNumber;
	private String settingTemp;
	int settingsCount, resourceCount;
	private String[] settings, resourceSettings;

	public Settings()
	{

		Scanner reader = null;
		settingsCount = 0;
		resourceCount = 0;
		File file = new File("resources/Settings.txt");
		try
		{
			reader = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:14pt'>"
					+ "File \"Settings.txt\" not found. "
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
							"<html><span style='font-size: " + new Settings().getSettingNumber(17)
									+ "'>"
									+ "Error opening \"DUECredits Errors.txt.\""
											+ "<br>Check to make sure it exists."
											+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");
				}
			}
			System.exit(-1);
		}

		while (reader.hasNextLine())
		{

			settingTemp = reader.nextLine();

			if (!settingTemp.equals(""))
			{

				settingsCount++;

			}

		}

		settings = new String[settingsCount];

		try
		{
			reader = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:14pt'>"
					+ "File \"Settings.txt\" not found. "
					+ "<br>"
					+ "Make sure it is in the \"resources\" folder and try again."
					+ "<br>"
					+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");}
			System.exit(-1);
			e.printStackTrace();
		}

		for (int i = 0; i < settingsCount; i++)
		{

			settings[i] = reader.nextLine();

		}

		try
		{
			reader = new Scanner(new File("resources/SourceSettings.txt"));
		}
		catch (FileNotFoundException e1)
		{
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:14pt'>"
					+ "File \"SourceSettings.txt\" not found. "
					+ "<br>"
					+ "Make sure it is in the \"resources\" folder and try again."
					+ "<br>"
					+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");}	
			System.exit(-1);
			e1.printStackTrace();
		}

		while (reader.hasNextLine())
		{
			if (!settingTemp.equals(""))
			{

				settingTemp = reader.nextLine();

				resourceCount++;
			}

		}

		resourceSettings = new String[resourceCount];

		try
		{
			reader = new Scanner(new File("resources/SourceSettings.txt"));
		}
		catch (FileNotFoundException e)
		{
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:14pt'>"
					+ "File \"SourceSettings.txt\" not found. "
					+ "<br>"
					+ "Make sure it is in the \"resources\" folder and try again."
					+ "<br>"
					+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");}
			System.exit(-1);
			e.printStackTrace();
		}

		for (int i = 0; i < resourceCount; i++)
		{

			resourceSettings[i] = reader.nextLine();

		}

		reader.close();

	}

	public String getSettingString(int m)
	{

		settingString = settings[m - 1];
		return settingString;
	}

	public int getSettingNumber(int m)
	{

		settingTemp = getSettingString(m);

		settingTemp = settingTemp.replaceAll("[^0-9-]", "").replace(" ", "");
		settingNumber = Integer.parseInt(settingTemp);

		return settingNumber;

	}
	
	public double getSettingDouble(int m)
	{

		settingTemp = getSettingString(m);

		settingTemp = settingTemp.replaceAll("[^0-9-]", "").replace(" ", "");
		

		return Double.parseDouble(settingTemp);

	}

	public void setSetting(int m, String newValue)
	{

		settings[m - 1] = newValue;

	}

	public void saveSettings()
	{

		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(new File("resources/Settings.txt"));
		}
		catch (FileNotFoundException e)
		{
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:14pt'>"
					+ "Error saving \"Settings.txt.\""
					+ "<br>"
					+ "Make sure it is in the \"resources\" folder and try again."
					+ "<br>"
					+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");}
			System.exit(-1);
			e.printStackTrace();
		}

		for (int i = 0; i < settingsCount; i++)
		{

			pw.println(settings[i]);

		}

		pw.close();

	}

	public String getResourceSettingString(int m)
	{

		return resourceSettings[m - 1];
	}

	public int getResourceSettingNumber(int m)
	{

		String resourceSettingTemp = getResourceSettingString(m);

		resourceSettingTemp = resourceSettingTemp.replaceAll("[^0-9-]", "")
				.replaceAll(" ", "");
		int resourceSettingNumber = Integer.parseInt(resourceSettingTemp);

		return resourceSettingNumber;

	}

	public void setResourceSetting(int m, String newValue)
	{

		resourceSettings[m - 1] = newValue;

	}

	public void saveResourceSettings()
	{

		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(new File("resources/SourceSettings.txt"));
		}
		catch (FileNotFoundException e)
		{
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:14pt'>"
					+ "Error saving \"SourceSettings.txt.\""
					+ "<br>"
					+ "Make sure it is in the \"resources\" folder and try again."
					+ "<br>"
					+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");}
			System.exit(-1);
			e.printStackTrace();
		}

		for (int i = 0; i < resourceCount; i++)
		{

			pw.println(resourceSettings[i]);

		}

		pw.close();

	}

	public int getResourceSettingsCount()
	{
		
		return resourceCount;
		
	}
	
	public Color getBackgroundColor()
	{
		
		Color color = null;
		Scanner scanner = new Scanner(getSettingString(16).replaceAll("[^0-9,]", ""));
		scanner.useDelimiter(",");
		
		color = new Color(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
		
		scanner.close();
		
		return color;
		
	}
	
	public Color getFontColor()
	{
		
		Color color = null;
		Scanner scanner = new Scanner(getSettingString(18).replaceAll("[^0-9,]", ""));
		scanner.useDelimiter(",");
		
		color = new Color(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
		
		scanner.close();
		
		return color;
		
	}
}