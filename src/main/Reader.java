/*
 * File name: Reader.java
 *
 * Programmer: Joe Killian ULID: JBKilli
 *
 * Date: May 3, 2016
 * 
 * Class: IT 168 Lecture Section: 07 Lecture Instructor: Ms. Patricia Matsuda
 * Lab Section: 09 Lab Instructor: Sahitya Atchyutuni
 */

package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import helper.Settings;

/**
 * <insert class description here>
 * 
 * @author Joe Killian
 *
 */
public class Reader
{

	private int rowCount, columnCount, typeCount;
	private Scanner reader, rowReader;
	private String[][] fullList;
	private TypeMinimizer[] type;
	private Settings setting = new Settings ();
	private File file;
	private boolean categoryIsMinimized = true, toBeReversed = false;
	private String lastSort;
	private int fontSize = setting.getSettingNumber ( 17 );
	private int advantagesShown;

	private int[] roll;
	private int markup;
	private double markupFromAdvantages;
	private double markupFromTriumphs;
	private int rarityFromWorld;
	private int worldPriceMultiplier;

	private int sourcedListCount;
	private String[][] sourcedList;
	private String fontName;
	private Font font2;
	private int finalColumnCount;
	private int listedColumnCount;
	private int sourcedColumnCount;
	private String[][] displayedList;
	private DefaultTableModel tableModel;
	private JTable table;
	private SpecializedShop shop;
	private int typeColumnNumber;
	private int specializationModifier;

	private String shopName;
	private String shopFlavor;

	private boolean rollIndividually = false;
	private JDialog individualRollFrame;
	private JTextField successText;
	private JTextField failureText;
	private JTextField advantageText;
	private JTextField threatText;
	private JTextField triumphText;
	private JTextField despairText;
	private int resizeCount = 0;
	private int temporaryListCount;
	private String[][] temporaryList;
	private String lastSearch = "FreeThinkersAreDangerous";

	public Reader(String itemType)
	{
		super ();
		file = new File ( "resources/" + itemType + ".csv" );

		if(Boolean.parseBoolean ( setting.getSettingString ( 35 ) ))
		{

			rarityFromWorld = setting.getSettingNumber ( 38 );
			if(Boolean.parseBoolean ( setting.getSettingString ( 36 ) ))
			{

				worldPriceMultiplier = setting.getSettingNumber ( 39 );

			}
			else
			{

				worldPriceMultiplier = 1;
			}

		}
		else
		{

			rarityFromWorld = 0;
			worldPriceMultiplier = 1;

		}

		if(Boolean.parseBoolean ( setting.getSettingString ( 30 ) )
				|| Boolean.parseBoolean ( setting.getSettingString ( 34 ) ))
		{
			advantagesShown = 1;
		}
		else
		{
			advantagesShown = 0;
		}

		categoryIsMinimized = true;
		toBeReversed = false;

		try
		{
			reader = new Scanner ( file );
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace ();
			{
				JOptionPane.showMessageDialog ( new javax.swing.JFrame () ,
						"<html><span style='font-size: " + fontSize + "'>"
								+ "Error opening file \"" + file.getName ()
								+ ".\" in class \"Reader.\"" + "<br>"
								+ "Make sure it is in the \"resources\" folder and try again."
								+ "<br>"
								+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text." );
			}

			System.exit ( -1 );
		}

		rowReader = new Scanner ( reader.nextLine () );
		rowReader.useDelimiter ( "," );
		while(rowReader.hasNext ())
		{
			rowReader.next ();
			columnCount++;
		}
		rowCount++;

		while(reader.hasNext ())
		{
			reader.nextLine ();
			rowCount++;
		}

		fullList = new String[rowCount][columnCount + 1];

		reader.close ();
		rowReader.close ();

	}

	public void loadArray()
	{

		try
		{
			reader = new Scanner ( file );
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace ();

			System.exit ( -1 );
		}

		fullList[0][columnCount] = "Show Successes/Advantages";
		for(int i = 0; i < rowCount; i++)
		{
			rowReader = new Scanner ( reader.nextLine ()
					.replace ( "\"\"" , "DERPADERP" ).replace ( "\"" , "" )
					.replace ( "DERPADERP" , "\"" ) );
			rowReader.useDelimiter ( "," );
			for(int j = 0; j < columnCount; j++)
			{
				try
				{
					fullList[i][j] = rowReader.next ();
					if(fullList[i][j].contains ( ( " // " ) ))
					{

						fullList[i][j] = "<html>"
								+ fullList[i][j].replaceAll ( " // " , "<br>" )
								+ "</html>";

					}

				}
				catch(NoSuchElementException e)
				{
					{
						JOptionPane.showMessageDialog (
								new javax.swing.JFrame () ,
								"<html><span style='font-size: " + fontSize
										+ "'>"
										+ "NoSuchElementException called in \"LoadArray\" method of class \"Reader.\""
										+ "<br>"
										+ "This should not be happening. Please email me at MacKenzieHnC@gmail.com with the error text." );
					}
					System.exit ( -1 );
				}
			}
			if(i != 0)
			{

				fullList[i][columnCount] = "";

			}

		}

		reader.close ();
		rowReader.close ();

		int columnNumber = getColumnNumber ( "Type" );
		String lastType = fullList[1][columnNumber];
		typeCount = 1;
		for(int i = 1; i < rowCount; i++)
		{
			if(!fullList[i][columnNumber].equals ( lastType ))
			{
				lastType = fullList[i][columnNumber];
				typeCount++;
			}
		}

		type = new TypeMinimizer[typeCount];
		lastType = fullList[1][columnNumber];
		int count = 1;
		type[0] = new TypeMinimizer ( lastType );

		for(int i = 1; i < typeCount; i++)
		{

			while(fullList[count][columnNumber].equals ( lastType )
					&& count < rowCount - 1)
			{
				count++;
			}
			lastType = fullList[count][columnNumber];
			type[i] = new TypeMinimizer ( lastType );
		}

	}

	public String getArrayValue(int rowNumber, int columnNumber)
	{

		String temp = null;

		if(rowNumber < rowCount && columnNumber < columnCount)
		{
			temp = fullList[rowNumber][columnNumber];
		}
		else
		{
			temp = "ERROR";
		}

		return temp;
	}

	public int getColumnNumber(String columnHeader)
	{

		int columnNumber = 0;

		try
		{
			while(!fullList[0][columnNumber].equals ( columnHeader ))
			{
				columnNumber++;
			}
			return columnNumber;
		}
		catch(NullPointerException | ArrayIndexOutOfBoundsException e)
		{
			return -1;
		}

	}

	public int getRowCount()
	{

		return rowCount;
	}

	public void editRow(String[] original, String[] replacement)
	{

		for(int i = 0; i < rowCount; i++)
		{

			if(fullList[i].equals ( original ))
			{

				fullList[i] = replacement;
				break;

			}

		}
	}

	public int getColumnCount()
	{

		return columnCount;
	}

	public boolean isNumeric(String string)
	{

		try
		{
			Integer.parseInt ( string );
			return true;
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
	}

	public boolean columnIsNumeric(String columnHeader)
	{

		try
		{
			int columnNumber = getColumnNumber ( columnHeader );
			for(int i = 1; i < fullList.length; i++)
			{

				Integer.parseInt ( fullList[i][columnNumber] );

			}
			return true;
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}

	}

	public void sortBy(String columnHeader)
	{

		if(fullList.length > 1)
		{
			if(columnHeader.equals ( lastSort ))
			{
				toBeReversed = !toBeReversed;
			}
			else
			{
				toBeReversed = false;
			}

			if(!columnHeader.equals ( "Type" ))
			{
				lastSort = columnHeader;
			}
			int i = 1;
			while(fullList[i][getColumnNumber ( columnHeader )].equals ( "" ))
			{
				i++;
			}

			if( ( columnIsNumeric ( columnHeader )
					|| columnHeader.equals ( "Price" ) )
					&& toBeReversed == false)
			{
				sortNumeric ( columnHeader );
			}

			else if( ( columnIsNumeric ( columnHeader )
					|| columnHeader.equals ( "Price" ) )
					&& toBeReversed == true)
			{
				reverseSortNumeric ( columnHeader );
			}

			else if(toBeReversed == false)
			{
				sortString ( columnHeader );
			}
			else
			{
				reverseSortString ( columnHeader );
			}

		}
	}

	public void sortNumeric(String columnHeader)
	{

		int columnNumber = getColumnNumber ( columnHeader ), rowNumber = 0;
		String[][] tempArray = new String[rowCount][columnCount];
		int lowestValue;
		int rowValue;

		int count = 1;
		int lowestValidRow = 1;

		tempArray[0] = fullList[0];
		while(count < rowCount)
		{
			try
			{
				if(fullList[lowestValidRow][columnNumber]
						.replaceAll ( "[^\\d]" , "" ).equals ( "" ))
				{
					lowestValue = -1;
				}
				else
				{
					lowestValue = Integer
							.parseInt ( fullList[lowestValidRow][columnNumber]
									.replaceAll ( "[^\\d]" , "" ) );
				}

				rowNumber = lowestValidRow;
				for(int j = 1; j < rowCount; j++)
				{
					try
					{
						if(fullList[j][columnNumber]
								.replaceAll ( "[^\\d]" , "" ).equals ( "" ))
						{
							rowValue = -1;
						}
						else
						{
							rowValue = Integer
									.parseInt ( fullList[j][columnNumber]
											.replaceAll ( "[^\\d]" , "" ) );
						}
						if(rowValue < lowestValue)

						{
							lowestValue = rowValue;
							rowNumber = j;
						}
					}
					catch(NullPointerException e)
					{

					}
				}
				tempArray[count] = fullList[rowNumber];
				count++;
				fullList[rowNumber] = null;
			}
			catch(NullPointerException e)
			{
				lowestValidRow++;
			}
		}

		fullList = tempArray;

	}

	public void sortType()
	{

		int columnNumber = getColumnNumber ( "Type" ), rowNumber = 0;
		String[][] tempArray = new String[rowCount][columnCount];
		String lowestValue;

		int count = 1;
		int lowestValidRow = 1;

		tempArray[0] = fullList[0];
		while(count < rowCount)
		{
			try
			{

				lowestValue = fullList[lowestValidRow][columnNumber]
						.replaceAll ( "[^A-Za-z\\d]" , "" );

				rowNumber = lowestValidRow;
				for(int j = 1; j < rowCount; j++)
				{
					try
					{
						if(fullList[j][columnNumber]
								.replaceAll ( "[^A-Za-z\\d]" , "" )
								.compareTo ( lowestValue ) < 0)

						{
							lowestValue = fullList[j][columnNumber]
									.replaceAll ( "[^A-Za-z\\d]" , "" );
							rowNumber = j;
						}
					}
					catch(NullPointerException e)
					{

					}
				}
				tempArray[count] = fullList[rowNumber];
				count++;
				fullList[rowNumber] = null;
			}
			catch(NullPointerException e)
			{
				lowestValidRow++;
			}
		}

		fullList = tempArray;

	}

	public void sortString(String columnHeader)
	{

		int columnNumber = getColumnNumber ( columnHeader ), rowNumber = 0;
		String[][] tempArray = new String[rowCount][columnCount];
		String lowestValue;

		int count = 1;
		int lowestValidRow = 1;

		tempArray[0] = fullList[0];
		while(count < rowCount)
		{
			try
			{

				lowestValue = fullList[lowestValidRow][columnNumber]
						.replaceAll ( "[^A-Za-z\\d]" , "" ).toLowerCase ();

				rowNumber = lowestValidRow;
				for(int j = 1; j < rowCount; j++)
				{
					try
					{
						if(fullList[j][columnNumber]
								.replaceAll ( "[^A-Za-z\\d]" , "" )
								.toLowerCase ().compareTo ( lowestValue ) < 0)

						{
							lowestValue = fullList[j][columnNumber]
									.replaceAll ( "[^A-Za-z\\d]" , "" )
									.toLowerCase ();
							rowNumber = j;
						}
					}
					catch(NullPointerException e)
					{

					}
				}
				tempArray[count] = fullList[rowNumber];
				count++;
				fullList[rowNumber] = null;
			}
			catch(NullPointerException e)
			{
				lowestValidRow++;
			}
		}

		fullList = tempArray;

	}

	public void reverseSortNumeric(String columnHeader)
	{

		int columnNumber = getColumnNumber ( columnHeader ), rowNumber = 0;
		String[][] tempArray = new String[rowCount][columnCount];
		int highestValue;
		int rowValue;

		int count = 1;
		int lowestValidRow = 1;

		tempArray[0] = fullList[0];
		while(count < rowCount)
		{
			try
			{
				if(fullList[lowestValidRow][columnNumber]
						.replaceAll ( "[^\\d]" , "" ).equals ( "" ))
				{
					highestValue = -1;
				}
				else
				{
					highestValue = Integer
							.parseInt ( fullList[lowestValidRow][columnNumber]
									.replaceAll ( "[^\\d]" , "" ) );
				}

				rowNumber = lowestValidRow;
				for(int j = 1; j < rowCount; j++)
				{
					try
					{
						if(fullList[j][columnNumber]
								.replaceAll ( "[^\\d]" , "" ).equals ( "" ))
						{
							rowValue = -1;
						}
						else
						{
							rowValue = Integer
									.parseInt ( fullList[j][columnNumber]
											.replaceAll ( "[^\\d]" , "" ) );
						}
						if(rowValue > highestValue)

						{
							highestValue = rowValue;
							rowNumber = j;
						}
					}
					catch(NullPointerException e)
					{

					}
				}
				tempArray[count] = fullList[rowNumber];
				count++;
				fullList[rowNumber] = null;
			}
			catch(NullPointerException e)
			{
				lowestValidRow++;
			}
		}

		fullList = tempArray;

	}

	public void reverseSortString(String columnHeader)
	{

		int columnNumber = getColumnNumber ( columnHeader ), rowNumber = 0;
		String[][] tempArray = new String[rowCount][columnCount];
		String highestValue;

		int count = 1;
		int lowestValidRow = 1;

		tempArray[0] = fullList[0];
		while(count < rowCount)
		{
			try
			{

				highestValue = fullList[lowestValidRow][columnNumber]
						.toLowerCase ();

				rowNumber = lowestValidRow;
				for(int j = 1; j < rowCount; j++)
				{
					try
					{
						if(fullList[j][columnNumber]
								.replaceAll ( "[^A-Za-z\\d ]" , "" )
								.toLowerCase ().compareTo ( highestValue ) > 0)

						{
							highestValue = fullList[j][columnNumber]
									.replaceAll ( "[^A-Za-z\\d ]" , "" )
									.toLowerCase ();
							rowNumber = j;
						}
					}
					catch(NullPointerException e)
					{

					}
				}
				tempArray[count] = fullList[rowNumber];
				count++;
				fullList[rowNumber] = null;
			}
			catch(NullPointerException e)
			{
				lowestValidRow++;
			}
		}

		fullList = tempArray;

	}

	public void setCategoryMinimized()
	{

		categoryIsMinimized = !categoryIsMinimized;

	}

	public int getType(String typeYouWant)
	{

		int i = 0;
		while(!type[i].getType ().equals ( typeYouWant ))
		{
			i++;
		}

		return i;

	}

	public void setTypeMinimized(String typeToMinimize)
	{

		type[getType ( typeToMinimize )].setMinimized ();

	}

	public boolean getTypeMinimized(String typeYoureLookingFor)
	{

		int i = 0;
		while(!type[i].getType ().equals ( typeYoureLookingFor ))
		{
			i++;
		}

		return type[i].getMinimized ();

	}

	public void randomize(String shopType, int numBoostDice, int numSetbackDice,
			int characteristicLevel, int skillLevel)
	{

		String[][] temp = new String[rowCount][columnCount + advantagesShown];
		Random rn = new Random ();
		int shopTypeModifier = 0;
		int count = 1;
		String restriction;
		dice.DicePool dicePool = new dice.DicePool ( skillLevel ,
				characteristicLevel , numBoostDice , numSetbackDice );
		typeColumnNumber = getColumnNumber ( "Type" );

		for(int i = 0; i < columnCount; i++)
		{

			temp[0][i] = fullList[0][i];
		}

		if(advantagesShown > 0)
		{
			temp[0][columnCount] = "";
		}
		for(int i = 1; i < rowCount; i++)
		{
			if(checkIndex ( i ))
			{
				restriction = fullList[i][getColumnNumber ( "Legality" )];

				if(shopType.equals ( "On the Level" ))
				{

					if(restriction.equals ( "Nonrestricted" ))
					{

						shopTypeModifier = setting.getSettingNumber ( 7 );

					}
					else if(restriction.equals ( "Restricted" ))
					{

						shopTypeModifier = setting.getSettingNumber ( 8 );

					}
					else
					{

						shopTypeModifier = setting.getSettingNumber ( 9 );

					}

				}

				else if(shopType.equals ( "Shady" ))
				{

					if(restriction.equals ( "Nonrestricted" ))
					{

						shopTypeModifier = setting.getSettingNumber ( 10 );

					}
					else if(restriction.equals ( "Restricted" ))
					{

						shopTypeModifier = setting.getSettingNumber ( 11 );

					}
					else
					{

						shopTypeModifier = setting.getSettingNumber ( 12 );

					}

				}

				else
				{

					if(restriction.equals ( "Nonrestricted" ))
					{

						shopTypeModifier = setting.getSettingNumber ( 13 );

					}
					else if(restriction.equals ( "Restricted" ))
					{

						shopTypeModifier = setting.getSettingNumber ( 14 );

					}
					else
					{

						shopTypeModifier = setting.getSettingNumber ( 15 );

					}

				}

				if(shop.isSpecial ( fullList[i][typeColumnNumber] ))
				{

					specializationModifier = shop.getSuccess ();

				}
				else
				{

					specializationModifier = shop.getFailure ();
				}

				dicePool.roll ( rarityFromWorld + Integer.parseInt (
						fullList[i][getColumnNumber ( "Rarity" )] ) );
				roll = dicePool.getTotalRoll ();
				roll[0] += shopTypeModifier;

				if(roll[0] + ( roll[1] * setting.getSettingDouble ( 25 ) )
						+ ( roll[2] * setting.getSettingDouble ( 24 ) )
						+ specializationModifier > 0)
				{

					int a = setting.getSettingNumber ( 1 );
					int b = setting.getSettingNumber ( 2 );
					String restrictedMarker = "";
					if(fullList[i][getColumnNumber ( "Legality" )]
							.equals ( "Restricted" )
							|| fullList[i][getColumnNumber ( "Legality" )]
									.equals ( "Lightsaber" ))
					{
						restrictedMarker = setting.getSettingString ( 20 );
					}

					for(int j = 0; j < columnCount; j++)
					{
						if(j != getColumnNumber ( "Index" )
								&& j != getColumnNumber ( "Special" ))
						{
							temp[count][j] = fullList[i][j];
						}
						else
						{
							temp[count][j] = "<html>"
									+ fullList[i][j].replace ( " // " , "<br>" )
									+ "</html>";
						}
					}
					if(Boolean.parseBoolean ( setting.getSettingString ( 30 ) )
							|| Boolean.parseBoolean (
									setting.getSettingString ( 34 ) ))
					{
						temp[count][columnCount] = "<html>";

						if(Boolean.parseBoolean (
								setting.getSettingString ( 34 ) ))
						{
							temp[0][columnCount] = "Listed Successes/Advantages";
							if(roll[0] > 0)
							{
								for(int j = 0; j < roll[0] / 2; j++)
								{

									temp[count][columnCount] += "<img src=\"file:"
											+ new File (
													"resources/Successes.gif" )
															.getAbsolutePath ()
											+ "\" alt=\"Smiley face\" height=\""
											+ fontSize * 2 + "\" width=\""
											+ fontSize * 2 + "\">";

								}

								for(int j = 0; j < roll[0] % 2; j++)
								{

									temp[count][columnCount] += "<img src=\"file:"
											+ new File (
													"resources/Success.gif" )
															.getAbsolutePath ()
											+ "\" alt=\"Smiley face\" height=\""
											+ fontSize * 2 + "\" width=\""
											+ fontSize * 2 + "\">";

								}
							}
							else
							{

								for(int j = 0; j > roll[0] / 2; j--)
								{

									temp[count][columnCount] += "<img src=\"file:"
											+ new File (
													"resources/Failures.gif" )
															.getAbsolutePath ()
											+ "\" alt=\"Smiley face\" height=\""
											+ fontSize * 2 + "\" width=\""
											+ fontSize * 2 + "\">";

								}
								if(roll[0] % 2 != 0)
								{

									temp[count][columnCount] += "<img src=\"file:"
											+ new File (
													"resources/Failure.gif" )
															.getAbsolutePath ()
											+ "\" alt=\"Smiley face\" height=\""
											+ fontSize * 2 + "\" width=\""
											+ fontSize * 2 + "\">";

								}

							}

						}

						if(Boolean.parseBoolean (
								setting.getSettingString ( 30 ) ))
						{

							if(roll[1] < 0)
							{

								if(roll[1] / 2 != 0)
								{

									for(int j = 0; j < ( -1 * roll[1]
											/ 2 ); j++)
									{
										temp[count][columnCount] += "<img src=\"file:"
												+ new File (
														"resources/Threats.gif" )
																.getAbsolutePath ()
												+ "\" alt=\"Smiley face\" height=\""
												+ fontSize * 2 + "\" width=\""
												+ fontSize * 2 + "\">";
									}
									if(roll[1] % 2 != 0)
									{
										temp[count][columnCount] += "<img src=\"file:"
												+ new File (
														"resources/Threat.gif" )
																.getAbsolutePath ()
												+ "\" alt=\"Smiley face\" height=\""
												+ fontSize * 2 + "\" width=\""
												+ fontSize * 2 + "\">";
									}
								}
								else
								{
									for(int j = 0; j < -1 * roll[1]; j++)
									{

										temp[count][columnCount] += "<img src=\"file:"
												+ new File (
														"resources/Threat.gif" )
																.getAbsolutePath ()
												+ "\" alt=\"Smiley face\" height=\""
												+ fontSize * 2 + "\" width=\""
												+ fontSize * 2 + "\">";

									}
								}
							}
							else
							{
								if(roll[1] / 2 != 0)
								{

									for(int j = 0; j < ( roll[1] / 2 ); j++)
									{
										temp[count][columnCount] += "<img src=\"file:"
												+ new File (
														"resources/Advantages.gif" )
																.getAbsolutePath ()
												+ "\" alt=\"Smiley face\" height=\""
												+ fontSize * 2 + "\" width=\""
												+ fontSize * 2 + "\">";
									}
									if(roll[1] % 2 != 0)
									{
										temp[count][columnCount] += "<img src=\"file:"
												+ new File (
														"resources/Advantage.gif" )
																.getAbsolutePath ()
												+ "\" alt=\"Smiley face\" height=\""
												+ fontSize * 2 + "\" width=\""
												+ fontSize * 2 + "\">";
									}
								}
								else
								{
									for(int j = 0; j < roll[1]; j++)
									{

										temp[count][columnCount] += "<img src=\"file:"
												+ new File (
														"resources/Advantage.gif" )
																.getAbsolutePath ()
												+ "\" alt=\"Smiley face\" height=\""
												+ fontSize * 2 + "\" width=\""
												+ fontSize * 2 + "\">";

									}
								}
							}

							for(int j = 0; j < roll[2]; j++)
							{

								temp[count][columnCount] += "<img src=\"file:"
										+ new File ( "resources/Triumph.gif" )
												.getAbsolutePath ()
										+ "\" alt=\"Smiley face\" height=\""
										+ fontSize * 2 + "\" width=\""
										+ fontSize * 2 + "\">";

							}
						}
						temp[count][columnCount] += "</html>";
					}
					markup = rn.nextInt ( a - b + 1 ) + b;
					if(Boolean.parseBoolean ( setting.getSettingString ( 22 ) ))
					{
						markupFromAdvantages = -1 * roll[1]
								* setting.getSettingNumber ( 23 );
						markupFromTriumphs = -1 * roll[2]
								* setting.getSettingNumber ( 21 );
					}
					else
					{
						markupFromAdvantages = 0;
						markupFromTriumphs = 0;
					}
					temp[count][getColumnNumber ( "Price" )] = restrictedMarker
							+ Math.round ( worldPriceMultiplier
									* Integer.parseInt (
											temp[count][getColumnNumber (
													"Price" )] )
									* ( 1 + ( ( markup + markupFromAdvantages
											+ markupFromTriumphs )
											/ 100.0 ) ) );

					count++;
				}
			}

		}

		fullList = temp;
		temp = new String[count][columnCount + advantagesShown];

		for(int i = 0; i < count; i++)
		{
			temp[i] = fullList[i];

		}

		fullList = temp;
		rowCount = count;

	}

	public boolean checkIndex(int rowNumber)
	{

		String sourceBook;
		for(int i = 0; i < setting.getResourceSettingsCount (); i++)
		{
			sourceBook = setting.getResourceSettingString ( i + 1 );

			if(fullList[rowNumber][getColumnNumber ( "Index" )]
					.contains ( sourceBook ))
			{

				return true;

			}

		}

		return false;

	}

	public String[][] getFullItemList()
	{

		String[][] tempArray = new String[rowCount - 1][columnCount];

		for(int i = 1; i < rowCount; i++)
		{

			tempArray[i - 1] = fullList[i];

		}

		return tempArray;

	}

	public void setFullItemList(String[][] newList)
	{

		String[][] temp = new String[newList.length + 1][];
		temp[0] = fullList[0];

		for(int i = 0; i < newList.length; i++)
		{

			temp[i + 1] = newList[i];

		}

		fullList = temp;
		rowCount = newList.length + 1;

	}

	public String[] getListHeaders()
	{

		return fullList[0];

	}

	public String[] getSourcedListHeaders()
	{

		String[] sourcedHeaders = new String[fullList[0].length - 3];
		sourcedColumnCount = 0;
		for(int i = 0; i < fullList[0].length; i++)
		{
			if(i != getColumnNumber ( "Class" )
					&& i != getColumnNumber ( "Legality" )
					&& i != getColumnNumber ( "Type" ))
			{
				sourcedHeaders[sourcedColumnCount] = fullList[0][i];
				sourcedColumnCount++;
			}
		}

		return sourcedHeaders;

	}

	public void addItem(String[] itemToAdd)
	{

		String[][] tempArray = new String[rowCount + 1][columnCount];

		for(int i = 0; i < rowCount; i++)
		{

			tempArray[i] = fullList[i];

		}

		tempArray[rowCount] = itemToAdd;
		rowCount++;
		fullList = tempArray;

	}

	public void removeItem(String nameOfItemToRemove)
	{

		int rowNumber = -1;

		for(int i = 0; i < rowCount; i++)
		{

			if(fullList[i][getColumnNumber ( "Name" )]
					.equals ( nameOfItemToRemove ))
			{

				rowNumber = i;

			}

		}

		if(rowNumber == -1)
		{
			javax.swing.JFrame frame = new javax.swing.JFrame ();
			javax.swing.JOptionPane.showMessageDialog ( frame ,
					"Item not found." );
			frame.setVisible ( true );

		}
		else
		{

			for(int i = rowNumber; i < rowCount - 1; i++)
			{

				fullList[i] = fullList[i + 1];

			}

			rowCount--;

			String[][] tempArray = new String[rowCount][columnCount];

			for(int i = 0; i < rowCount; i++)
			{

				tempArray[i] = fullList[i];

			}

			fullList = tempArray;

		}

	}

	public int findItem(String itemName)
	{

		int rowNumber = -1;

		for(int i = 0; i < rowCount; i++)
		{

			if(fullList[i][getColumnNumber ( "Name" )].equals ( itemName ))
			{

				rowNumber = i;

			}

		}

		if(rowNumber == -1)
		{
			javax.swing.JFrame frame = new javax.swing.JFrame ();
			javax.swing.JOptionPane.showMessageDialog ( frame ,
					"Item not found." );
			frame.setVisible ( true );

		}

		return rowNumber;

	}

	public void saveList()
	{

		int count = 0;
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter ( file );
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog ( new javax.swing.JFrame () ,
					"There was an error writing to \"" + file.getName ()
							+ ".\"" );
			e.printStackTrace ();

		}

		for(int i = 0; i < rowCount; i++)
		{
			count = 0;

			for(int j = 0; j < columnCount - 1; j++)
			{

				pw.print ( fullList[i][j] + "," );
				count++;

			}

			pw.println ( fullList[i][count] );

		}

		pw.close ();
	}

	public void maximizeEverything()
	{

		categoryIsMinimized = false;

		for(int i = 0; i < type.length; i++)
		{

			type[i].setMaximized ();

		}

	}

	public void createSourcedList()
	{

		listedColumnCount = 0;
		typeCount = 0;
		if(Boolean.parseBoolean ( setting.getResourceSettingString ( 1 ) ))
		{
			listedColumnCount = columnCount;
		}
		else
		{

			listedColumnCount = columnCount - 1;

		}
		finalColumnCount = listedColumnCount + advantagesShown;

		sourcedColumnCount = 0;
		for(int i = 0; i < finalColumnCount; i++)
		{

			if(i != getColumnNumber ( "Class" )
					&& i != getColumnNumber ( "Legality" )
					&& i != getColumnNumber ( "Type" ))
			{
				sourcedColumnCount++;
			}

		}

		sourcedListCount = 0;
		int columnNumber = getColumnNumber ( "Type" );
		String lastType = "Free Thinkers Are Dangerous";
		typeCount = 0;
		for(int i = 1; i < rowCount; i++)
		{

			sourcedListCount++;
			if(!fullList[i][columnNumber].equals ( lastType ))
			{
				lastType = fullList[i][columnNumber];
				typeCount++;
			}

		}
		sourcedListCount += typeCount;
		sourcedList = new String[sourcedListCount][sourcedColumnCount];
		sourcedListCount = 0;
		lastType = "Free Thinkers Are Dangerous";
		String[] typeRow;
		for(int i = 1; i < rowCount; i++)
		{

			if(!fullList[i][columnNumber].equals ( lastType ))
			{
				lastType = fullList[i][columnNumber];
				typeRow = new String[sourcedColumnCount];
				typeRow[0] = "<html><font size=\"" + (int)Math.round(fontSize / 4.0)
						+ "\"><a href = \"Derp\">" + lastType + "</a></html>";
				for(int j = 1; j < sourcedColumnCount; j++)
				{
					typeRow[j] = " ";
				}
				sourcedList[sourcedListCount] = typeRow;
				sourcedListCount++;
			}
			sourcedColumnCount = 0;
			for(int j = 0; j < finalColumnCount; j++)
			{
				if(j != getColumnNumber ( "Class" )
						&& j != getColumnNumber ( "Legality" )
						&& j != getColumnNumber ( "Type" ))
				{
					sourcedList[sourcedListCount][sourcedColumnCount] = fullList[i][j];

					sourcedColumnCount++;
				}
			}
			sourcedListCount++;

		}

		createDisplayedList ();

	}

	public int getTypeCount()
	{

		return typeCount;

	}

	public void createDisplayedList()
	{

		displayedList = new String[typeCount][sourcedColumnCount];
		typeCount = 0;
		for(int i = 0; i < sourcedListCount; i++)
		{

			if(isTypeRow ( sourcedList[i] ))
			{

				displayedList[typeCount] = sourcedList[i];
				typeCount++;

			}

		}

	}

	public String[][] maximizeGroup(String typeSelected)
	{

		String[][] newList;

		int[] positionAndCount = getItemOfTypePositionAndCount ( typeSelected );
		int newListCount = 0;

		type[getType ( typeSelected )].setMinimized ();

		newList = new String[displayedList.length
				+ positionAndCount[1]][sourcedColumnCount];

		int i = 0;
		while(!isTypeRequested (
				displayedList[i][0]
						.replace ( "<html><font size=\"" + (int)Math.round(fontSize / 4.0)
								+ "\"><a href = \"Derp\">" , "" )
						.replace ( "</a></html>" , "" ) ,
				typeSelected ))
		{

			newList[i] = displayedList[i];
			i++;

		}

		newList[i] = displayedList[i];
		i++;

		newListCount = i;

		for(int j = 0; j < positionAndCount[1]; j++)
		{

			newList[newListCount] = sourcedList[positionAndCount[0] + j];
			newListCount++;

		}

		while(i < displayedList.length)
		{

			newList[newListCount] = displayedList[i];
			newListCount++;
			i++;

		}

		return newList;

	}

	public JTable createJTable()
	{

		table = new JTable ();
		table.setFocusable ( false );
		tableModel = new DefaultTableModel ( displayedList ,
				getSourcedListHeaders ()

		)

		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 4437948630892410159L;

			@Override
			public boolean isCellEditable(int row, int col)
			{

				return false;

			}
		};
		table.setModel ( tableModel );
		table.setColumnSelectionAllowed ( false );
		table.getTableHeader ().setReorderingAllowed ( false );
		table.setRowSelectionAllowed ( true );
		setting = new Settings ();
		fontSize = setting.getSettingNumber ( 17 );
		fontName = setting.getSettingString ( 26 );

		font2 = new Font ( fontName , 0 , fontSize );
		table.setFont ( font2 );
		table.setGridColor ( setting.getFontColor () );
		table.setForeground ( setting.getFontColor () );
		table.setBackground ( setting.getBackgroundColor () );
		table.getSelectionModel ()
				.addListSelectionListener ( new ListSelectionListener ()
				{

					String typeSelected;
					private int[] positionAndCount;
					private String[][] newList;
					private int newListCount;

					public void valueChanged(ListSelectionEvent event)
					{

						try
						{
							table.getParent ()
									.setCursor ( Cursor.getPredefinedCursor (
											Cursor.WAIT_CURSOR ) );

							if(!event.getValueIsAdjusting ())
							{
								if(table.getSelectedRow () != -1
										&& isTypeRow ( displayedList[table
												.getSelectedRow ()] ))
								{
									typeSelected = displayedList[table
											.getSelectedRow ()][0]
													.replace (
															"<html><font size=\""
																	+ (int)Math.round(fontSize / 4.0)
																	+ "\"><a href = \"Derp\">" ,
															"" )
													.replace ( "</a></html>" ,
															"" );

									positionAndCount = getItemOfTypePositionAndCount (
											typeSelected );

									if(type[getType ( typeSelected )]
											.getMinimized ())
									{
										newList = maximizeGroup (
												typeSelected );

									}
									else
									{
										type[getType ( typeSelected )]
												.setMinimized ();

										newList = new String[displayedList.length
												- positionAndCount[1]][sourcedColumnCount];

										int i = 0;
										while(!isTypeRequested (
												displayedList[i][0] ,
												displayedList[table
														.getSelectedRow ()][0] ))
										{

											newList[i] = displayedList[i];
											i++;

										}

										newList[i] = displayedList[i];
										i++;

										newListCount = i;

										for(int j = 0; j < positionAndCount[1]; j++)
										{

											i++;

										}

										while(i < displayedList.length)
										{

											newList[newListCount] = displayedList[i];
											newListCount++;
											i++;

										}
									}

									displayedList = newList;
									tableModel.fireTableDataChanged ();
									resizeColumnWidth ( table );
								}
								else if(table.getSelectedRow () != -1
										&& rollIndividually
										&& !isTypeRow ( displayedList[table
												.getSelectedRow ()] ))
								{
									int columnNumber = 0;
									for(int i = 0; i < table
											.getColumnCount (); i++)
									{

										if(table.getColumnName ( i )
												.equals ( "Price" ))
										{

											columnNumber = i;
											break;

										}

									}
									if(displayedList[table
											.getSelectedRow ()][columnNumber]
													.equals ( "-----" ))
									{
										String apology = "<html><span style='font-size: "
												+ fontSize + "'>"
												+ "I'm sorry, we're all out of "
												+ displayedList[table
														.getSelectedRow ()][0];

										if(displayedList[table
												.getSelectedRow ()][0].charAt (
														displayedList[table
																.getSelectedRow ()][0]
																		.length ()
																- 1 ) != 's')
										{

											apology += "s";

										}

										apology += "."
												+ "<br>Can I help you find anything else?";
										JOptionPane.showMessageDialog (
												new javax.swing.JFrame () ,
												apology );

									}
									else
									{

										rollIndividualItem (
												table.getSelectedRow () );
									}

								}
							}
						}
						finally
						{
							EventQueue.invokeLater ( new Runnable ()
							{

								@Override
								public void run()
								{

									table.getParent ().setCursor (
											Cursor.getDefaultCursor () );
								}
							} );

						}
					}
				} );

		table.getTableHeader ().addMouseListener ( new MouseAdapter ()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{

				try
				{

					table.getParent ().setCursor (
							Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );
					int col = table.columnAtPoint ( e.getPoint () );
					String name = table.getColumnName ( col );
					sortBy ( name );
					sortType ();
					updateList ();

					if(!lastSearch.equals ( "FreeThinkersAreDangerous" ))
					{

						searchForItems ( lastSearch );

					}
				}
				finally
				{
					EventQueue.invokeLater ( new Runnable ()
					{

						@Override
						public void run()
						{

							table.getParent ()
									.setCursor ( Cursor.getDefaultCursor () );
						}
					} );

				}
			}
		} );

		table.getTableHeader ().setFont ( font2 );

		return resizeColumnWidth ( table );

	}

	protected void rollIndividualItem(int selectedRow)
	{

		individualRollFrame = new JDialog ();
		JPanel panel = new JPanel ();
		panel.setBorder (
				BorderFactory.createEmptyBorder ( 10 , 10 , 10 , 10 ) );
		individualRollFrame.setTitle ( "Results of Roll" );
		JLabel successLabel = new JLabel ( "Successes" );
		JLabel failureLabel = new JLabel ( "Failures" );
		JLabel advantageLabel = new JLabel ( "Advantages" );
		JLabel threatLabel = new JLabel ( "Threats" );
		JLabel triumphLabel = new JLabel ( "Triumphs" );
		JLabel despairLabel = new JLabel ( "Despair" );
		successLabel.setFont ( font2 );
		failureLabel.setFont ( font2 );
		advantageLabel.setFont ( font2 );
		threatLabel.setFont ( font2 );
		triumphLabel.setFont ( font2 );
		despairLabel.setFont ( font2 );
		JLabel difficultyLabel = new JLabel ();
		difficultyLabel.setFont ( font2 );

		successText = new JTextField ( "0" );
		failureText = new JTextField ( "0" );
		advantageText = new JTextField ( "0" );
		threatText = new JTextField ( "0" );
		triumphText = new JTextField ( "0" );
		despairText = new JTextField ( "0" );
		successText.setHorizontalAlignment ( SwingConstants.CENTER );
		failureText.setHorizontalAlignment ( SwingConstants.CENTER );
		advantageText.setHorizontalAlignment ( SwingConstants.CENTER );
		threatText.setHorizontalAlignment ( SwingConstants.CENTER );
		triumphText.setHorizontalAlignment ( SwingConstants.CENTER );
		despairText.setHorizontalAlignment ( SwingConstants.CENTER );

		JButton saveButton = new JButton ( "SAVE" );
		saveButton.setFont ( font2 );
		saveButton.addActionListener ( new java.awt.event.ActionListener ()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				individualRollActionPerformed ();
				individualRollFrame.dispose ();

			}
		} );
		JButton cancelButton = new JButton ( "CANCEL" );
		cancelButton.setFont ( font2 );
		cancelButton.addActionListener ( new java.awt.event.ActionListener ()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				individualRollFrame.dispose ();

			}
		} );

		int rarity = Integer
				.parseInt ( (String) table.getValueAt ( selectedRow ,
						table.getColumn ( "Rarity" ).getModelIndex () ) );

		if(Boolean.parseBoolean ( setting.getSettingString ( 35 ) ))
		{

			rarity += Integer.parseInt ( setting.getSettingString ( 38 ) );

		}

		difficultyLabel
				.setText ( ( "<html>Perform a " + rarity / 2 + " difficulty "
						+ setting.getSettingString ( 31 ) + " roll." ) );
		difficultyLabel.setBorder ( BorderFactory.createCompoundBorder (
				new LineBorder ( Color.black ) ,
				new EmptyBorder ( 10 , 10 , 10 , 10 ) ) );

		GroupLayout panelLayout = new GroupLayout ( panel );
		panel.setLayout ( panelLayout );

		panelLayout.setHorizontalGroup ( panelLayout.createParallelGroup ()
				.addComponent ( difficultyLabel )
				.addGroup ( panelLayout.createSequentialGroup ()
						.addGroup ( panelLayout.createParallelGroup ()
								.addComponent ( successLabel )
								.addComponent ( advantageLabel )
								.addComponent ( triumphLabel ) )
						.addPreferredGap (
								LayoutStyle.ComponentPlacement.RELATED )
						.addGroup ( panelLayout.createParallelGroup ()
								.addComponent ( successText ,
										successText.getPreferredSize ().width
												* 4 ,
										successText.getPreferredSize ().width
												* 4 ,
										successText.getPreferredSize ().width
												* 4 )
								.addComponent ( advantageText )
								.addComponent ( triumphText ) )
						.addPreferredGap (
								LayoutStyle.ComponentPlacement.UNRELATED )
						.addGroup ( panelLayout.createParallelGroup ()
								.addComponent ( failureLabel )
								.addComponent ( threatLabel )
								.addComponent ( despairLabel ) )
						.addPreferredGap (
								LayoutStyle.ComponentPlacement.RELATED )
						.addGroup ( panelLayout.createParallelGroup ()
								.addComponent ( failureText )
								.addComponent ( threatText )
								.addComponent ( despairText ) )
						.addPreferredGap (
								LayoutStyle.ComponentPlacement.UNRELATED ) )
				.addGroup ( GroupLayout.Alignment.TRAILING ,
						panelLayout.createSequentialGroup ()
								.addComponent ( cancelButton )
								.addPreferredGap (
										LayoutStyle.ComponentPlacement.RELATED )
								.addComponent ( saveButton ) )

		);
		panelLayout.linkSize ( SwingConstants.HORIZONTAL , new Component[]
		{
				successLabel, advantageLabel, triumphLabel, failureLabel,
				threatLabel, despairLabel
		} );
		panelLayout.linkSize ( SwingConstants.HORIZONTAL , new Component[]
		{
				successText, advantageText, triumphText, failureText,
				threatText, despairText
		} );
		panelLayout.linkSize ( SwingConstants.HORIZONTAL , new Component[]
		{
				cancelButton, saveButton
		} );

		panelLayout.setVerticalGroup ( panelLayout.createSequentialGroup ()
				.addComponent ( difficultyLabel )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
				.addGroup ( panelLayout
						.createParallelGroup ( GroupLayout.Alignment.CENTER )
						.addComponent ( successLabel )
						.addComponent ( successText )
						.addComponent ( failureLabel )
						.addComponent ( failureText ) )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
				.addGroup ( panelLayout
						.createParallelGroup ( GroupLayout.Alignment.CENTER )
						.addComponent ( advantageLabel )
						.addComponent ( advantageText )
						.addComponent ( threatLabel )
						.addComponent ( threatText ) )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
				.addGroup ( panelLayout
						.createParallelGroup ( GroupLayout.Alignment.CENTER )
						.addComponent ( triumphLabel )
						.addComponent ( triumphText )
						.addComponent ( despairLabel )
						.addComponent ( despairText ) )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED )
				.addGroup ( panelLayout.createParallelGroup ()
						.addComponent ( cancelButton )
						.addComponent ( saveButton ) ) );

		individualRollFrame.add ( panel );

		individualRollFrame.pack ();
		individualRollFrame.setLocationRelativeTo ( null );
		individualRollFrame.setVisible ( true );
		individualRollFrame.setModal ( true );

		// asdf
	}

	protected void individualRollActionPerformed()
	{

		roll = new int[3];
		roll[0] = Integer.parseInt ( successText.getText () )
				- Integer.parseInt ( failureText.getText () );
		roll[1] = Integer.parseInt ( advantageText.getText () )
				- Integer.parseInt ( threatText.getText () );
		roll[2] = Integer.parseInt ( triumphText.getText () )
				- Integer.parseInt ( despairText.getText () );

		String html = "<html>";

		if(roll[0] / 2 > 0)
		{
			for(int j = 0; j < roll[0] / 2; j++)
			{

				html += "<img src=\"file:"
						+ new File ( "resources/Successes.gif" )
								.getAbsolutePath ()
						+ "\" alt=\"Smiley face\" height=\"" + fontSize * 2
						+ "\" width=\"" + fontSize * 2 + "\">";

			}

			for(int j = 0; j < roll[0] % 2; j++)
			{

				html += "<img src=\"file:"
						+ new File ( "resources/Success.gif" )
								.getAbsolutePath ()
						+ "\" alt=\"Smiley face\" height=\"" + fontSize * 2
						+ "\" width=\"" + fontSize * 2 + "\">";

			}
		}
		else
		{

			for(int j = 0; j > roll[0] / 2; j--)
			{

				html += "<img src=\"file:"
						+ new File ( "resources/Failures.gif" )
								.getAbsolutePath ()
						+ "\" alt=\"Smiley face\" height=\"" + fontSize * 2
						+ "\" width=\"" + fontSize * 2 + "\">";

			}

			for(int j = 0; j > roll[0] % 2; j--)
			{

				html += "<img src=\"file:"
						+ new File ( "resources/Failure.gif" )
								.getAbsolutePath ()
						+ "\" alt=\"Smiley face\" height=\"" + fontSize * 2
						+ "\" width=\"" + fontSize * 2 + "\">";

			}

		}

		if(Boolean.parseBoolean ( setting.getSettingString ( 30 ) ))
		{

			if(roll[1] < 0)
			{

				if(roll[1] / 2 != 0)
				{

					for(int j = 0; j < ( -1 * roll[1] / 2 ); j++)
					{
						html += "<img src=\"file:"
								+ new File ( "resources/Threats.gif" )
										.getAbsolutePath ()
								+ "\" alt=\"Smiley face\" height=\""
								+ fontSize * 2 + "\" width=\"" + fontSize * 2
								+ "\">";
					}
					if(roll[1] % 2 != 0)
					{
						html += "<img src=\"file:"
								+ new File ( "resources/Threat.gif" )
										.getAbsolutePath ()
								+ "\" alt=\"Smiley face\" height=\""
								+ fontSize * 2 + "\" width=\"" + fontSize * 2
								+ "\">";
					}
				}
				else
				{
					for(int j = 0; j < -1 * roll[1]; j++)
					{

						html += "<img src=\"file:"
								+ new File ( "resources/Threat.gif" )
										.getAbsolutePath ()
								+ "\" alt=\"Smiley face\" height=\""
								+ fontSize * 2 + "\" width=\"" + fontSize * 2
								+ "\">";

					}
				}
			}
			else
			{
				if(roll[1] / 2 != 0)
				{

					for(int j = 0; j < ( roll[1] / 2 ); j++)
					{
						html += "<img src=\"file:"
								+ new File ( "resources/Advantages.gif" )
										.getAbsolutePath ()
								+ "\" alt=\"Smiley face\" height=\""
								+ fontSize * 2 + "\" width=\"" + fontSize * 2
								+ "\">";
					}
					if(roll[1] % 2 != 0)
					{
						html += "<img src=\"file:"
								+ new File ( "resources/Advantage.gif" )
										.getAbsolutePath ()
								+ "\" alt=\"Smiley face\" height=\""
								+ fontSize * 2 + "\" width=\"" + fontSize * 2
								+ "\">";
					}
				}
				else
				{
					for(int j = 0; j < roll[1]; j++)
					{

						html += "<img src=\"file:"
								+ new File ( "resources/Advantage.gif" )
										.getAbsolutePath ()
								+ "\" alt=\"Smiley face\" height=\""
								+ fontSize * 2 + "\" width=\"" + fontSize * 2
								+ "\">";

					}
				}
			}
			if(roll[2] > 0)
			{
				for(int j = 0; j < roll[2]; j++)

				{

					html += "<img src=\"file:"
							+ new File ( "resources/Triumph.gif" )
									.getAbsolutePath ()
							+ "\" alt=\"Smiley face\" height=\"" + fontSize * 2
							+ "\" width=\"" + fontSize * 2 + "\">";

				}
			}
			else
			{

				for(int j = 0; j > roll[2]; j--)

				{

					html += "<img src=\"file:"
							+ new File ( "resources/Despair.gif" )
									.getAbsolutePath ()
							+ "\" alt=\"Smiley face\" height=\"" + fontSize * 2
							+ "\" width=\"" + fontSize * 2 + "\">";

				}

			}
		}
		html += "</html>";

		fullList[findItem (
				displayedList[table.getSelectedRow ()][0] )][getColumnNumber (
						"Show Successes/Advantages" )] = html;

		for(int i = 0; i < table.getColumnCount (); i++)
		{

			if(table.getColumnName ( i ).equals ( "Price" ))
			{

				break;

			}

		}
		int rowNumber;
		int price;
		if(roll[0] + ( roll[1] * setting.getSettingDouble ( 25 ) )
				+ ( roll[2] * setting.getSettingDouble ( 24 ) ) > 0)
		{
			rowNumber = findItem ( displayedList[table.getSelectedRow ()][0] );
			price = Integer.parseInt (
					fullList[rowNumber][getColumnNumber ( "Price" )] );
			if(Boolean.parseBoolean ( setting.getSettingString ( 36 ) ))
			{

				price *= setting.getSettingNumber ( 39 );

			}

			if(Boolean.parseBoolean ( setting.getSettingString ( 22 ) ))
			{
				price = (int) Math.round ( price * ( 1 - ( roll[1]
						* setting.getSettingNumber ( 23 ) / 100 ) ) );
				price += (int) Math.round ( price * ( 1 - ( roll[2]
						* setting.getSettingNumber ( 21 ) / 100 ) ) );

			}

			fullList[rowNumber][getColumnNumber ( "Price" )] = "" + price;
		}
		else
		{
			fullList[findItem ( displayedList[table
					.getSelectedRow ()][0] )][getColumnNumber (
							"Price" )] = "-----";
		}

		updateList ();

	}

	protected void updateList()
	{

		String[] maximizedTypes;
		int maximizedTypesCount = 0;

		for(int i = 0; i < displayedList.length; i++)
		{

			if(isTypeRow ( displayedList[i] )
					&& !type[getType (
							displayedList[i][0]
									.replace (
											"<html><font size=\"" + (int)Math.round(fontSize / 4.0)
													+ "\"><a href = \"Derp\">" ,
											"" )
									.replace ( "</a></html>" , "" ) )]
											.getMinimized ())
			{

				maximizedTypesCount++;

			}

		}

		maximizedTypes = new String[maximizedTypesCount];
		maximizedTypesCount = 0;
		for(int i = 0; i < displayedList.length; i++)
		{

			if(isTypeRow ( displayedList[i] )
					&& !type[getType (
							displayedList[i][0]
									.replace (
											"<html><font size=\"" + (int)Math.round(fontSize / 4.0)
													+ "\"><a href = \"Derp\">" ,
											"" )
									.replace ( "</a></html>" , "" ) )]
											.getMinimized ())
			{

				maximizedTypes[maximizedTypesCount] = displayedList[i][0]
						.replace ( "<html><font size=\"" + (int)Math.round(fontSize / 4.0)
								+ "\"><a href = \"Derp\">" , "" )
						.replace ( "</a></html>" , "" );
				type[getType ( maximizedTypes[maximizedTypesCount] )]
						.setMinimized ();
				maximizedTypesCount++;

			}

		}

		createSourcedList ();
		createDisplayedList ();

		for(int j = 0; j < maximizedTypesCount; j++)
		{

			displayedList = maximizeGroup ( maximizedTypes[j] );

		}

		tableModel.fireTableDataChanged ();
		resizeColumnWidth ( table );

	}

	public JTable resizeColumnWidth(JTable table)
	{

		for(int row = 0; row < table.getRowCount (); row++)
		{
			int rowHeight = table.getRowHeight ();

			for(int column = 0; column < table.getColumnCount (); column++)
			{
				Component comp = table.prepareRenderer (
						table.getCellRenderer ( row , column ) , row , column );
				rowHeight = Math.max ( rowHeight ,
						comp.getPreferredSize ().height );
			}

			table.setRowHeight ( row , rowHeight + 10 );
		}
		final TableColumnModel columnModel = table.getColumnModel ();
		for(int column = 0; column < table.getColumnCount (); column++)
		{

			TableColumn col = columnModel.getColumn ( column );
			TableCellRenderer renderer = col.getHeaderRenderer ();
			if(renderer == null)
			{
				renderer = table.getTableHeader ().getDefaultRenderer ();
			}
			Component comp = renderer.getTableCellRendererComponent ( table ,
					col.getHeaderValue () , false , false , 0 , 0 );
			int width = comp.getPreferredSize ().width;
			for(int row = 0; row < table.getRowCount (); row++)
			{
				renderer = table.getCellRenderer ( row , column );
				comp = table.prepareRenderer ( renderer , row , column );
				width = Math.max ( comp.getPreferredSize ().width + 1 , width );
			}

			width += 10;

			columnModel.getColumn ( column ).setPreferredWidth ( width );
			columnModel.getColumn ( column ).setMinWidth ( width );
		}
		resizeCount++;
		if(resizeCount > 1)
		{

			if(table.getPreferredSize ().width < table.getParent ().getWidth ())
			{
				table.setAutoResizeMode ( JTable.AUTO_RESIZE_ALL_COLUMNS );
			}
			else
			{
				table.setAutoResizeMode ( JTable.AUTO_RESIZE_OFF );
			}

		}
		return table;

	}

	public int getSourcedColumnCount()
	{

		return sourcedColumnCount;

	}

	public String[][] getSourcedList()
	{

		return sourcedList;

	}

	public int getSourcedListCount()
	{

		return sourcedListCount;

	}

	public void setSourcedList(String[][] newList)
	{

		sourcedList = newList;
		if(sourcedList.length > 0)
		{
			sourcedColumnCount = newList[0].length;
		}
		else
		{
			sourcedColumnCount = 0;
		}
		sourcedListCount = newList.length;

	}

	public boolean isTypeRow(String[] row)
	{

		boolean isTypeRow = false;

		for(int i = 0; i < row.length; i++)
		{

			if(row[i].contains ( "a href" ))
			{

				isTypeRow = true;
				break;

			}

		}

		return isTypeRow;

	}

	public boolean isTypeRequested(String typeYouWant, String typeYouHave)
	{

		if(typeYouWant.equals ( typeYouHave ))
		{

			return true;

		}
		else
		{
			return false;
		}

	}

	public int[] getItemOfTypePositionAndCount(String typeYouWant)
	{

		int i = 0;
		int[] positionAndCount = new int[2];
		while(!isTypeRequested (
				sourcedList[i][0]
						.replace ( "<html><font size=\"" + (int)Math.round(fontSize / 4.0)
								+ "\"><a href = \"Derp\">" , "" )
						.replace ( "</a></html>" , "" ) ,
				typeYouWant ))
		{

			i++;

		}
		i++;
		positionAndCount[0] = i;
		int itemOfTypeCount = 0;
		while(i < sourcedList.length && !isTypeRow ( sourcedList[i] ))
		{
			itemOfTypeCount++;
			i++;
		}

		positionAndCount[1] = itemOfTypeCount;

		return positionAndCount;

	}

	public DefaultTableModel getTableModel()
	{

		return tableModel;

	}

	public String[][] getDisplayedList()
	{

		return displayedList;

	}

	public void setTypeCount(int i)
	{

		typeCount = i;

	}

	public String[] getTypeList()
	{

		String[] typeList = new String[type.length];
		for(int i = 0; i < typeList.length; i++)
		{

			typeList[i] = type[i].getType ();

		}

		return typeList;

	}

	public void setShopSpecialization(File shopType)
	{

		shop = new SpecializedShop ( shopType );
		shopName = shop.getShopName ();
		shopFlavor = shop.getShopFlavor ();

	}

	public void setShopName(String shopName)
	{

		this.shopName = shopName;

	}

	public void setShopFlavor(String shopFlavor)
	{

		this.shopFlavor = shopFlavor;

	}

	public String getShopFlavor()
	{

		return shopFlavor;

	}

	public String getShopName()
	{

		return shopName;

	}

	public void setRollIndividually(boolean hmmm)
	{

		this.rollIndividually = hmmm;

	}

	public boolean getRollIndividually()
	{

		return rollIndividually;

	}

	public void searchForItems(String searchParams)
	{

		temporaryListCount = 0;

		String[][] list = getSourcedList ();
		for(int i = 0; i < getSourcedListCount (); i++)
		{

			for(int j = 0; j < getSourcedColumnCount () - 1; j++)
			{
				if(list[i][j].toLowerCase ()
						.contains ( searchParams.toLowerCase () )
						|| list[i][0].contains ( "<a" ))
				{

					temporaryListCount++;
					break;

				}

			}

		}

		temporaryList = new String[temporaryListCount][getSourcedColumnCount ()];
		temporaryListCount = 0;

		for(int i = 0; i < getSourcedListCount (); i++)
		{

			for(int j = 0; j < getSourcedColumnCount () - 1; j++)
			{

				if(list[i][j].toString ().toLowerCase ()
						.contains ( searchParams.toLowerCase () )
						|| list[i][0].contains ( "<a" ))
				{

					temporaryList[temporaryListCount] = list[i];
					temporaryListCount++;
					break;

				}

			}

		}

		table.setModel ( new javax.swing.table.DefaultTableModel (
				temporaryList , getSourcedListHeaders ()

		)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = -5787604852351412928L;

			@Override
			public boolean isCellEditable(int row, int col)
			{

				return false;

			}
		} );

		resizeColumnWidth ( table );
		lastSearch = searchParams;

	}

	public void resetSearch()
	{

		lastSearch = "FreeThinkersAreDangerous";

	}
}
