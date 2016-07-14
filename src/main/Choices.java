/*
 * File name: Choices.java
 *
 * Programmer: Joe Killian ULID: JBKilli
 *
 * Date: May 6, 2016
 * 
 * Class: IT 168 Lecture Section: 07 Lecture Instructor: Ms. Patricia Matsuda
 * Lab Section: 09 Lab Instructor: Sahitya Atchyutuni
 */

package main;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import helper.Settings;

/**
 * <insert class description here>
 * 
 * @author Joe Killian
 *
 */
public class Choices
{

	private int skillLevel;

	private File specialFile;
	private int characteristicLevel;
	private JDialog jFrame;
	private int numBoostDice;
	private int numSetbackDice;
	private String continueOrNah = "Continue";
	JLabel titleLabel;
	JLabel boostLabel = new JLabel ( "Number of Boosts" );
	JLabel setbackLabel = new JLabel ( "Number of Setbacks" );
	JTextField boostText = new JTextField ( "0" );
	JTextField setbackText = new JTextField ( "0" );
	JButton saveButton = new JButton ( "SAVE" );
	JButton cancelButton = new JButton ( "CANCEL" );
	private int fontSize;
	private int specialCount;
	private String[] special;
	private File directory;
	private String fontName;
	private Font font2;
	private String shopType;
	private JLabel specialLabel;
	private int shopSizeMinimum;
	private int shopSizeMaximum;

	private JComboBox<String> worldModCombo;
	private JLabel worldModLabel;

	private Settings setting;

	private JDialog frame;

	private JPanel panel;

	public String choices(boolean rollIndividually)
	{

		java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager ()
				.addPropertyChangeListener ( "permanentFocusOwner" ,
						new java.beans.PropertyChangeListener ()
						{

							@Override
							public void propertyChange(
									final java.beans.PropertyChangeEvent e)
							{

								if(e.getNewValue () instanceof JTextField)
								{
									javax.swing.SwingUtilities
											.invokeLater ( new Runnable ()
											{

												@Override
												public void run()
												{

													JTextField textField = (JTextField) e
															.getNewValue ();
													textField.selectAll ();
												}
											} );

								}
							}
						} );
		Settings setting = new Settings ();

		fontSize = setting.getSettingNumber ( 17 );
		fontName = setting.getSettingString ( 26 );

		font2 = new Font ( fontName , 0 , fontSize );

		{
			directory = new File ( "Saved Shop Specializations" );
			specialCount = directory.listFiles ().length;

			special = new String[specialCount];

			for(int i = 0; i < specialCount; i++)
			{

				special[i] = directory.listFiles ()[i].getName ()
						.replace ( ".specialization" , "" );

			}

		}

		try
		{
			UIManager.setLookAndFeel (
					UIManager.getSystemLookAndFeelClassName () );
		}
		catch(ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace ();
			
		}
		try
		{
			if(!rollIndividually)
			{
				shopTypeDialog ();

				String streetwise = JOptionPane.showInputDialog (
						"What is the highest " + setting.getSettingString ( 31 )
								+ " among present party members? (Enter an integer 0-5)" );

				while(!streetwise.equals ( null ))
				{
					try
					{
						skillLevel = Integer.parseInt ( streetwise );
						if(skillLevel < 6 && skillLevel >= 0)
						{
							break;
						}
						else
						{

							streetwise = JOptionPane.showInputDialog (
									"Please enter an integer (0-5): What is the highest "
											+ setting.getSettingString ( 31 )
											+ " among present party members?" );

						}
					}
					catch(NumberFormatException e)
					{
						{
							JOptionPane.showMessageDialog ( new JFrame () ,
									"<html><span style='font-size:" + fontSize
											+ "'>"
											+ "Entered value must be an integer between 0 and 5 inclusive." );
						}
						streetwise = JOptionPane
								.showInputDialog ( "What is the highest "
										+ setting.getSettingString ( 31 )
										+ " among present party members?" );

					}
				}

				String characteristic = JOptionPane
						.showInputDialog ( "What is their "
								+ setting.getSettingString ( 32 ) + " level?" );

				while(!characteristic.equals ( null ))
				{
					try
					{
						characteristicLevel = Integer
								.parseInt ( characteristic );
						if(characteristicLevel < 7 && characteristicLevel >= 0)
						{
							break;
						}
						else
						{

							characteristic = JOptionPane.showInputDialog (
									"Please enter an integer (0-6): What is their "
											+ setting.getSettingString ( 32 )
											+ " level?" );

						}
					}
					catch(NumberFormatException e)
					{
						{
							JOptionPane.showMessageDialog ( new JFrame () ,
									"<html><span style='font-size:" + fontSize
											+ "'>"
											+ "Entered value must be an integer between 0 and 6 inclusive." );
						}
						characteristic = JOptionPane.showInputDialog (
								"Please enter an integer (0-6): What is their "
										+ setting.getSettingString ( 32 )
										+ " level?" );

					}
				}
				jFrame = new JDialog ();
				titleLabel = new JLabel ( "Boosts/Setbacks" );
				titleLabel.setFont ( font2 );
				titleLabel.setHorizontalAlignment ( SwingConstants.CENTER );

				saveButton
						.setBackground ( new java.awt.Color ( 0 , 255 , 255 ) );
				saveButton.setFont ( font2 );
				saveButton.setText ( "SAVE" );
				saveButton.addActionListener (
						new java.awt.event.ActionListener ()
						{

							public void actionPerformed(
									java.awt.event.ActionEvent evt)
							{

								saveButtonActionPerformed ( evt );
							}
						} );

				cancelButton.setText ( "CANCEL" );
				cancelButton.setFont ( font2 );
				cancelButton.addActionListener (
						new java.awt.event.ActionListener ()
						{

							@Override
							public void actionPerformed(ActionEvent e)
							{

								continueOrNah = "Cancel";
								jFrame.dispose ();

							}

						} );
				boostLabel.setFont ( font2 );
				boostText.setHorizontalAlignment ( SwingConstants.CENTER );
				boostText.setFont ( font2 );
				boostText.addFocusListener ( new java.awt.event.FocusAdapter ()
				{

					public void focusGained(java.awt.event.FocusEvent e)
					{

						boostText.selectAll ();

					}

				} );

				setbackLabel.setFont ( font2 );
				setbackText.setHorizontalAlignment ( SwingConstants.CENTER );
				setbackText.setFont ( font2 );
				setbackText
						.addFocusListener ( new java.awt.event.FocusAdapter ()
						{

							public void focusGained(java.awt.event.FocusEvent e)
							{

								setbackText.selectAll ();

							}

						} );

				GroupLayout layout = new GroupLayout (
						jFrame.getContentPane () );
				jFrame.getContentPane ().setLayout ( layout );
				layout.setHorizontalGroup ( layout
						.createParallelGroup ( GroupLayout.Alignment.LEADING )
						.addGroup ( layout.createSequentialGroup ()
								.addContainerGap ()
								.addGroup ( layout
										.createParallelGroup (
												GroupLayout.Alignment.CENTER )
										.addGroup ( layout
												.createSequentialGroup ()
												.addGroup ( layout
														.createParallelGroup (
																GroupLayout.Alignment.CENTER )
														.addComponent (
																titleLabel ,
																GroupLayout.Alignment.CENTER ,
																GroupLayout.DEFAULT_SIZE ,
																GroupLayout.DEFAULT_SIZE ,
																Short.MAX_VALUE )
														.addGroup (
																GroupLayout.Alignment.CENTER ,
																layout.createSequentialGroup ()
																		.addGap (
																				0 ,
																				0 ,
																				Short.MAX_VALUE )
																		.addComponent (
																				boostLabel )
																		.addPreferredGap (
																				LayoutStyle.ComponentPlacement.RELATED )
																		.addComponent (
																				boostText,
																				GroupLayout.DEFAULT_SIZE,
																				boostText.getPreferredSize ().width * 4,
																				boostText.getPreferredSize ().width * 4)
																		.addPreferredGap (
																				LayoutStyle.ComponentPlacement.RELATED )
																		.addComponent (
																				setbackLabel )
																		.addPreferredGap (
																				LayoutStyle.ComponentPlacement.RELATED )
																		.addComponent (
																				setbackText,
																				GroupLayout.DEFAULT_SIZE,
																				boostText.getPreferredSize ().width * 4,
																				boostText.getPreferredSize ().width * 4)
																		.addPreferredGap (
																				LayoutStyle.ComponentPlacement.RELATED )
																		.addContainerGap (
																				10 ,
																				10 ) )
														.addGroup (
																GroupLayout.Alignment.TRAILING ,
																layout.createSequentialGroup ()
																		.addComponent (
																				cancelButton ,
																				GroupLayout.PREFERRED_SIZE ,
																				GroupLayout.PREFERRED_SIZE ,
																				GroupLayout.PREFERRED_SIZE )
																		.addPreferredGap (
																				LayoutStyle.ComponentPlacement.RELATED )
																		.addComponent (
																				saveButton ,
																				GroupLayout.PREFERRED_SIZE ,
																				GroupLayout.PREFERRED_SIZE ,
																				GroupLayout.PREFERRED_SIZE )
																		.addContainerGap (
																				0 ,
																				0 ) ) ) ) ) ) );
				layout.setVerticalGroup ( layout
						.createParallelGroup ( GroupLayout.Alignment.LEADING )
						.addGroup ( GroupLayout.Alignment.TRAILING ,
								layout.createSequentialGroup ()
										.addContainerGap ()
										.addComponent ( titleLabel )
										.addPreferredGap (
												LayoutStyle.ComponentPlacement.RELATED )
										.addGroup ( layout
												.createParallelGroup (
														GroupLayout.Alignment.BASELINE )
												.addComponent ( boostLabel )
												.addComponent ( boostText)
												.addComponent ( setbackLabel )
												.addComponent ( setbackText ) )
										.addContainerGap ( 10 , 10 )
										.addGroup ( layout
												.createParallelGroup (
														GroupLayout.Alignment.BASELINE )
												.addComponent ( saveButton )
												.addComponent ( cancelButton ) )
										.addContainerGap ( 10 , 10 ) ) );
				jFrame.getRootPane ().setDefaultButton ( saveButton );
				jFrame.setModal ( true );
				jFrame.setResizable ( false );
				jFrame.pack ();
				jFrame.setLocationRelativeTo ( null );
				jFrame.setVisible ( true );

			}
			else
			{
				
				frame = new JDialog();
				frame.setTitle ( "What world are you on?" );
				panel = new JPanel();
				panel.setBorder ( BorderFactory.createEmptyBorder (10,10,10,10) );
				String[] worldModifiers = new String[]
						{
								"Primary Core world such as Coruscant, Duro, or Corellia"
										+ " / " + "Rarity: -2" + " / " + "Price: x1",

								"Other Core world" + " / " + "Rarity: -1" + " / " + "Price: x1",

								"World on primary trade line" + " / " + "Rarity: -1" + " / "
										+ "Price: x1",

								"Colony or inner Rim world" + " / " + "Rarity: +0" + " / "
										+ "Price: x1",

								"Civilized world" + " / " + "Rarity: +0" + " / " + "Price: x1"

								,

								"Mid Rim world" + " / " + "Rarity: +1" + " / " + "Price: x1"

								,

								"<html><table><tr>" + "<td>"
										+ "Recently settled world/out of the way world" + " / "
										+ "Rarity: +1" + " / " + "Price: x1"

								,

								"Outer Rim world" + " / " + "Rarity: +2" + " / " + "Price: x2"

								,

								"Frontier world" + " / " + "Rarity: +2" + " / " + "Price: x2"

								,

								"Wild Space world" + " / " + "Rarity: +3" + " / " + "Price: x3"

								,

								"Uncivilized world" + " / " + "Rarity: +4" + " / " + "Price: x4"
						};
						worldModCombo = new JComboBox<String> ( worldModifiers );
						worldModCombo.setFont ( font2 );

						setting = new Settings ();
						worldModCombo.setSelectedItem ( setting.getSettingString ( 37 ) );

						worldModLabel = new JLabel ( "<html><b><u>World Modifiers" );
						worldModLabel.setFont ( font2 );
						worldModLabel.setHorizontalAlignment ( SwingConstants.CENTER );
						worldModLabel.setToolTipText (
								"Determines rarity modifier based on world location and/or level of civilization." );
						
						saveButton.setBackground ( new java.awt.Color ( 0 , 255 , 255 ) );
						saveButton.setText ( "SAVE" );
						saveButton.setFont ( font2 );
						saveButton.addActionListener ( new java.awt.event.ActionListener ()
						{

							private Scanner reader;

							public void actionPerformed(java.awt.event.ActionEvent evt)
							{

								
									String world = (String) worldModCombo.getSelectedItem ();
									Settings setting = new Settings();
									setting.setSetting ( 37 , world );
									reader = new Scanner (
											world.replaceAll ( "[^0-9-/]" , "" ) );
									reader.useDelimiter ( "/" );
									setting.setSetting ( 38 ,
											Integer.toString ( reader.nextInt () ) );
									setting.setSetting ( 39 ,
											Integer.toString ( reader.nextInt () ) );
									reader.close ();
									setting.saveSettings ();
									frame.dispose ();
								

							}
						} );

						cancelButton.setText ( "CANCEL" );
						cancelButton.setFont ( font2 );
						cancelButton.addActionListener ( new java.awt.event.ActionListener ()
						{

							@Override
							public void actionPerformed(ActionEvent e)
							{

								continueOrNah = "Cancel";
								frame.dispose ();

							}

						} );
						
						GroupLayout panelLayout = new GroupLayout(panel);
						panel.setLayout ( panelLayout );
						
						panelLayout.setHorizontalGroup ( panelLayout.createParallelGroup ()
								.addComponent ( worldModLabel )
									.addComponent ( worldModCombo)
								.addGroup ( GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup ()
										.addComponent ( cancelButton )
										.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
										.addComponent ( saveButton )));
						panelLayout.linkSize ( SwingConstants.HORIZONTAL, new Component[]{cancelButton, saveButton} );

						panelLayout.setVerticalGroup ( panelLayout.createSequentialGroup ()
								.addComponent ( worldModLabel )
								.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
								.addComponent ( worldModCombo )
								.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED )
								.addGroup ( panelLayout.createParallelGroup ()
										.addComponent ( cancelButton )
										.addComponent ( saveButton )));
						
						frame.add ( panel );
						frame.setModal ( true );
						frame.pack ();
						frame.setLocationRelativeTo ( null );
						frame.setVisible ( true );
						frame.getRootPane ().setDefaultButton ( saveButton );
				
			}
			return continueOrNah;

		}
		catch(NullPointerException e)
		{

			return "Cancel";

		}
	}

	protected void saveButtonActionPerformed(ActionEvent evt)
	{

		try
		{

			numBoostDice = Integer.parseInt ( boostText.getText () );
			numSetbackDice = Integer.parseInt ( setbackText.getText () );
			jFrame.dispose ();

		}
		catch(NumberFormatException e)
		{

			JOptionPane.showMessageDialog ( new JFrame () ,
					"<html><span style='font-size:" + fontSize + "'>"
							+ "Boost and setback fields must be integers." );

		}

	}

	public int getNumBoostDice()
	{

		return numBoostDice;
	}

	public int getNumSetbackDice()
	{

		return numSetbackDice;

	}

	public int getSkillLevel()
	{

		return skillLevel;
	}

	public int getCharacteristicLevel()
	{

		return characteristicLevel;

	}

	public String getShopType()
	{

		return shopType;

	}

	public File getSpecialization()
	{

		return specialFile;

	}

	public int getShopSizeMax()
	{

		return shopSizeMaximum;

	}

	public int getShopSizeMin()
	{

		return shopSizeMinimum;

	}

	public void shopTypeDialog()
	{

		JLabel legalityLabel = new JLabel ( "Legality" );
		legalityLabel.setFont ( font2 );
		JComboBox<String> legalityCombo = new JComboBox<String> ( new String[]
		{
				"On the Level", "Shady", "Black Market"
		} );
		legalityCombo.setFont ( font2 );
		specialLabel = new JLabel ( "Shop Specialization" );
		specialLabel.setFont ( font2 );
		JComboBox<String> specialCombo = new JComboBox<String> ( special );
		specialCombo.setSelectedItem ( "General Store" );
		specialCombo.setFont ( font2 );
		JLabel shopSize = new JLabel (
				"<html>" + "<b><u>Shop Size</b> (# of items)</u>" + "</html>" );
		shopSize.setFont ( font2 );
		shopSize.setHorizontalAlignment ( SwingConstants.CENTER );
		JLabel shopSizeMinLabel = new JLabel ( "Minimum" );
		shopSizeMinLabel.setFont ( font2 );
		JTextField shopSizeMinText = new JTextField ( "10" );
		shopSizeMinText.setFont ( font2 );
		JLabel shopSizeMaxLabel = new JLabel ( "Maximum" );
		shopSizeMaxLabel.setFont ( font2 );
		JTextField shopSizeMaxText = new JTextField ( "10,000" );
		shopSizeMaxText.setFont ( font2 );

		jFrame = new JDialog ();
		jFrame.setTitle ( "Shop Type" );
		titleLabel = new JLabel ( "<html><u><b>Legality/Specialization" );
		titleLabel.setFont ( font2 );
		titleLabel.setHorizontalAlignment ( SwingConstants.CENTER );

		saveButton.setBackground ( new java.awt.Color ( 0 , 255 , 255 ) );
		saveButton.setText ( "SAVE" );
		saveButton.setFont ( font2 );
		saveButton.addActionListener ( new java.awt.event.ActionListener ()
		{

			private Scanner reader;

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				try
				{
					shopSizeMinimum = Integer.parseInt (
							shopSizeMinText.getText ().replace ( "," , "" ) );
					shopSizeMaximum = Integer.parseInt (
							shopSizeMaxText.getText ().replace ( "," , "" ) );

					if(shopSizeMaximum < shopSizeMinimum)
					{

						shopSizeMaximum = shopSizeMinimum;

					}

					shopType = (String) legalityCombo.getSelectedItem ();
					for(int i = 0; i < specialCount; i++)
					{
						if(directory.listFiles ()[i].toString ().contains (
								(String) specialCombo.getSelectedItem () ))
						{

							specialFile = directory.listFiles ()[i];

						}

					}

					String world = (String) worldModCombo.getSelectedItem ();
					setting.setSetting ( 37 , world );
					reader = new Scanner (
							world.replaceAll ( "[^0-9-/]" , "" ) );
					reader.useDelimiter ( "/" );
					setting.setSetting ( 38 ,
							Integer.toString ( reader.nextInt () ) );
					setting.setSetting ( 39 ,
							Integer.toString ( reader.nextInt () ) );
					reader.close ();
					setting.saveSettings ();
					jFrame.dispose ();
				}
				catch(NumberFormatException e)

				{
					JOptionPane.showMessageDialog ( new JFrame () ,
							"<html><span style='font-size: " + fontSize + "'>"
									+ "Shop size Min/Max must be integers." );
				}

			}
		} );

		cancelButton.setText ( "CANCEL" );
		cancelButton.setFont ( font2 );
		cancelButton.addActionListener ( new java.awt.event.ActionListener ()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				continueOrNah = "Cancel";
				jFrame.dispose ();

			}

		} );

		String[] worldModifiers = new String[]
		{
				"Primary Core world such as Coruscant, Duro, or Corellia"
						+ " / " + "Rarity: -2" + " / " + "Price: x1",

				"Other Core world" + " / " + "Rarity: -1" + " / " + "Price: x1",

				"World on primary trade line" + " / " + "Rarity: -1" + " / "
						+ "Price: x1",

				"Colony or inner Rim world" + " / " + "Rarity: +0" + " / "
						+ "Price: x1",

				"Civilized world" + " / " + "Rarity: +0" + " / " + "Price: x1"

				,

				"Mid Rim world" + " / " + "Rarity: +1" + " / " + "Price: x1"

				,

				"<html><table><tr>" + "<td>"
						+ "Recently settled world/out of the way world" + " / "
						+ "Rarity: +1" + " / " + "Price: x1"

				,

				"Outer Rim world" + " / " + "Rarity: +2" + " / " + "Price: x2"

				,

				"Frontier world" + " / " + "Rarity: +2" + " / " + "Price: x2"

				,

				"Wild Space world" + " / " + "Rarity: +3" + " / " + "Price: x3"

				,

				"Uncivilized world" + " / " + "Rarity: +4" + " / " + "Price: x4"
		};
		worldModCombo = new JComboBox<String> ( worldModifiers );
		worldModCombo.setFont ( font2 );

		setting = new Settings ();
		worldModCombo.setSelectedItem ( setting.getSettingString ( 37 ) );

		worldModLabel = new JLabel ( "<html><b><u>World Modifiers" );
		worldModLabel.setFont ( font2 );
		worldModLabel.setHorizontalAlignment ( SwingConstants.CENTER );
		worldModLabel.setToolTipText (
				"Determines rarity modifier based on world location and/or level of civilization." );

		GroupLayout layout = new GroupLayout ( jFrame.getContentPane () );
		jFrame.getContentPane ().setLayout ( layout );
		layout.setHorizontalGroup ( layout
				.createParallelGroup ( GroupLayout.Alignment.LEADING )
				.addGroup ( layout.createSequentialGroup ().addContainerGap ()
						.addGroup ( layout
								.createParallelGroup (
										GroupLayout.Alignment.CENTER )
								.addGroup ( layout.createSequentialGroup ()
										.addGroup ( layout
												.createParallelGroup (
														GroupLayout.Alignment.CENTER )
												.addComponent ( titleLabel ,
														GroupLayout.Alignment.CENTER ,
														GroupLayout.DEFAULT_SIZE ,
														GroupLayout.DEFAULT_SIZE ,
														Short.MAX_VALUE )
												.addGroup (
														GroupLayout.Alignment.CENTER ,
														layout.createSequentialGroup ()
																.addComponent (
																		legalityLabel )
																.addPreferredGap (
																		LayoutStyle.ComponentPlacement.RELATED )
																.addComponent (
																		legalityCombo ,
																		GroupLayout.PREFERRED_SIZE ,
																		GroupLayout.PREFERRED_SIZE ,
																		GroupLayout.PREFERRED_SIZE )
																.addPreferredGap (
																		LayoutStyle.ComponentPlacement.UNRELATED )
																.addComponent (
																		specialLabel )
																.addPreferredGap (
																		LayoutStyle.ComponentPlacement.RELATED )
																.addComponent (
																		specialCombo ,
																		GroupLayout.PREFERRED_SIZE ,
																		GroupLayout.PREFERRED_SIZE ,
																		GroupLayout.PREFERRED_SIZE )
																.addPreferredGap (
																		LayoutStyle.ComponentPlacement.RELATED )
																.addContainerGap (
																		10 ,
																		10 ) )
												.addComponent ( shopSize ,
														GroupLayout.Alignment.CENTER ,
														GroupLayout.DEFAULT_SIZE ,
														GroupLayout.DEFAULT_SIZE ,
														Short.MAX_VALUE )
												.addGroup (
														layout.createSequentialGroup ()
																.addComponent (
																		shopSizeMinLabel )
																.addPreferredGap (
																		LayoutStyle.ComponentPlacement.RELATED )
																.addComponent (
																		shopSizeMinText )
																.addPreferredGap (
																		LayoutStyle.ComponentPlacement.UNRELATED )
																.addComponent (
																		shopSizeMaxLabel )
																.addPreferredGap (
																		LayoutStyle.ComponentPlacement.RELATED )
																.addComponent (
																		shopSizeMaxText )
																.addContainerGap () )
												.addComponent ( worldModLabel )
												.addGroup ( layout
														.createSequentialGroup ()
														.addComponent (
																worldModCombo ,
																GroupLayout.PREFERRED_SIZE ,
																GroupLayout.PREFERRED_SIZE ,
																GroupLayout.PREFERRED_SIZE )
														.addContainerGap () )

												.addGroup (
														GroupLayout.Alignment.TRAILING ,
														layout.createSequentialGroup ()
																.addComponent (
																		cancelButton ,
																		GroupLayout.PREFERRED_SIZE ,
																		GroupLayout.PREFERRED_SIZE ,
																		GroupLayout.PREFERRED_SIZE )
																.addPreferredGap (
																		LayoutStyle.ComponentPlacement.RELATED )
																.addComponent (
																		saveButton ,
																		GroupLayout.PREFERRED_SIZE ,
																		GroupLayout.PREFERRED_SIZE ,
																		GroupLayout.PREFERRED_SIZE )
																.addContainerGap (
																		0 ,
																		0 ) ) ) ) ) ) );
		layout.setVerticalGroup ( layout
				.createParallelGroup ( GroupLayout.Alignment.LEADING )
				.addGroup ( GroupLayout.Alignment.TRAILING ,
						layout.createSequentialGroup ().addContainerGap ()
								.addComponent ( titleLabel )
								.addPreferredGap (
										LayoutStyle.ComponentPlacement.RELATED )
								.addGroup ( layout
										.createParallelGroup (
												GroupLayout.Alignment.BASELINE )
										.addComponent ( legalityLabel )
										.addComponent ( legalityCombo )
										.addComponent ( specialLabel )
										.addComponent ( specialCombo ) )
								.addPreferredGap (
										LayoutStyle.ComponentPlacement.UNRELATED )
								.addComponent ( shopSize )
								.addPreferredGap (
										LayoutStyle.ComponentPlacement.RELATED )
								.addGroup ( layout
										.createParallelGroup (
												GroupLayout.Alignment.CENTER )
										.addComponent ( shopSizeMinLabel )
										.addComponent ( shopSizeMinText )
										.addComponent ( shopSizeMaxLabel )
										.addComponent ( shopSizeMaxText ) )
								.addPreferredGap (
										LayoutStyle.ComponentPlacement.UNRELATED )
								.addComponent ( worldModLabel )
								.addPreferredGap (
										LayoutStyle.ComponentPlacement.RELATED )
								.addComponent ( worldModCombo ,
										GroupLayout.PREFERRED_SIZE ,
										GroupLayout.PREFERRED_SIZE ,
										GroupLayout.PREFERRED_SIZE )
								.addPreferredGap (
										LayoutStyle.ComponentPlacement.UNRELATED )
								.addGroup ( layout
										.createParallelGroup (
												GroupLayout.Alignment.BASELINE )
										.addComponent ( saveButton )
										.addComponent ( cancelButton ) )
								.addContainerGap ( 10 , 10 ) ) );
		jFrame.getRootPane ().setDefaultButton ( saveButton );
		jFrame.setModal ( true );
		jFrame.setResizable ( false );
		jFrame.pack ();
		jFrame.setLocationRelativeTo ( null );
		jFrame.setVisible ( true );

	}

}
