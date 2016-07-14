package main;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SpecializedShop
{
	
	int success;
	int failure;
	int typeCount;
	String[] itemTypes;
	String shopName;
	String shopFlavor;

	public SpecializedShop(File shopType)
	{
		
		super();
		
		Scanner reader = null;
		try
		{
			reader = new Scanner(shopType);
			typeCount = 0;
			shopName = reader.nextLine ();
			shopFlavor = reader.nextLine ();
			success = Integer.parseInt ( reader.nextLine () );
			failure = Integer.parseInt ( reader.nextLine () );
			
			while(reader.hasNext())
			{
				
				reader.nextLine ();
				typeCount++;
				
			}
			
			itemTypes = new String[typeCount];
			
			reader = new Scanner(shopType);
			shopName = shopType.getName ().replace ( ".specialization" , "" );
			reader.nextLine ();
			shopFlavor = reader.nextLine ();
			success = Integer.parseInt ( reader.nextLine () );
			failure = Integer.parseInt ( reader.nextLine () );
			

			for(int i = 0; i < typeCount; i++)
			{
				
				itemTypes[i] = reader.nextLine ();
				
			}
			
			
		}
		catch(FileNotFoundException e)
		{
			{
				JOptionPane.showMessageDialog(new javax.swing.JFrame(),
						"<html>"
								+ "Error opening file \"" + shopType
								+ ".specialization.\"" + "<br>"
								+ "Make sure it is in the \"Saved Shop Specializations\" folder and try again."
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
							"<html>"
									+ "Error opening \"DUECredits Errors.txt.\""
											+ "<br>Check to make sure it exists."
											+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text.");
				}
			}
		}
		
	}
	
	public int getSuccess()
	{
		
		return success;
		
	}
	
	public String getShopName()
	{
		
		return shopName;
		
	}
	
	public String getShopFlavor()
	{
		
		return shopFlavor;
		
	}
	
	public int getFailure()
	{
		
		return failure;
		
	}
	
	public boolean isSpecial(String itemType)
	{
		
		for(int i = 0; i < itemTypes.length; i++)
		{
			
			if(itemTypes[i].equals ( itemType ))
			{
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
}
