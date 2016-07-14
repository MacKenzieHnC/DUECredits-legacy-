
package settings;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import helper.Settings;

public class ShopSpecializationSettings
{

	private JCheckBox[][] compiledCheckList;
	private JCheckBox[] weaponsTypes;
	private JCheckBox[] armorTypes;
	private JCheckBox[] gearTypes;
	private JCheckBox[] attachmentsTypes;
	private JCheckBox weaponsCheckBox;
	private JCheckBox armorCheckBox;
	private JCheckBox gearCheckBox;
	private JCheckBox attachmentsCheckBox;
	private JLabel titleLabel;
	private JLabel description;
	private JLabel shopName;
	private JLabel successLabel;
	private JLabel failureLabel;
	private JTextField successText;
	private JTextField failureText;

	private Settings setting;
	private int fontSize;
	private String fontName;
	private Font font2;
	private Font font1;
	private JPanel innerPanel;
	private GroupLayout innerPanelLayout;
	private ParallelGroup parallelMinor;
	private SequentialGroup sequenceMajor;
	private int inputPreferredWidth;
	private ParallelGroup parallelMajor;
	private SequentialGroup sequentialMinor;
	private JScrollPane scrollPane;
	private JFrame frame;
	private GroupLayout layout;
	private JButton saveButton;
	private JButton cancelButton;
	private JMenuBar menuBar;
	private JMenuItem load;
	private JPanel panel;
	private GroupLayout panelLayout;
	private String type;
	private JCheckBox[] transportationTypes;
	private JCheckBox[] starshipsTypes;
	private JCheckBox transportationCheckBox;
	private JCheckBox starshipsCheckBox;

	JLabel flavorLabel;
	JTextPane flavorText;
	private JScrollPane flavorTextScroll;

	public void createTypeLists()
	{

		{
			setting = new Settings ();
			fontSize = setting.getSettingNumber ( 17 );
			fontName = setting.getSettingString ( 26 );
			font1 = new Font ( fontName , 0 ,
					(int) Math.round ( fontSize * 1.25 ) );
			font2 = new Font ( fontName , 0 , fontSize );

			weaponsCheckBox = new JCheckBox ( "<html><b>Weapons" );
			weaponsCheckBox.setFont ( font1 );
			armorCheckBox = new JCheckBox ( "<html><b>Armor" );
			armorCheckBox.setFont ( font1 );
			gearCheckBox = new JCheckBox ( "<html><b>Gear" );
			gearCheckBox.setFont ( font1 );
			attachmentsCheckBox = new JCheckBox ( "<html><b>Attachments" );
			attachmentsCheckBox.setFont ( font1 );
			transportationCheckBox = new JCheckBox (
					"<html><b>Transportation" );
			transportationCheckBox.setFont ( font1 );
			starshipsCheckBox = new JCheckBox ( "<html><b>Starships" );
			starshipsCheckBox.setFont ( font1 );

			main.Reader weapons = new main.Reader ( "Weapons" );
			weapons.loadArray ();
			String[] weaponsTypeList = weapons.getTypeList ();
			weaponsTypes = new JCheckBox[weaponsTypeList.length + 1];
			weaponsTypes[0] = weaponsCheckBox;
			for(int i = 1; i < weaponsTypeList.length + 1; i++)
			{
				weaponsTypes[i] = new JCheckBox ( weaponsTypeList[i - 1] );
				weaponsTypes[i].setFont ( font2 );
			}

			main.Reader armor = new main.Reader ( "Armor" );
			armor.loadArray ();
			String[] armorTypeList = armor.getTypeList ();
			armorTypes = new JCheckBox[armorTypeList.length + 1];
			armorTypes[0] = armorCheckBox;
			for(int i = 1; i < armorTypeList.length + 1; i++)
			{
				armorTypes[i] = new JCheckBox ( armorTypeList[i - 1] );
				armorTypes[i].setFont ( font2 );
			}

			main.Reader gear = new main.Reader ( "Gear" );
			gear.loadArray ();
			String[] gearTypeList = gear.getTypeList ();
			gearTypes = new JCheckBox[gearTypeList.length + 1];
			gearTypes[0] = gearCheckBox;
			for(int i = 1; i < gearTypeList.length + 1; i++)
			{
				gearTypes[i] = new JCheckBox ( gearTypeList[i - 1] );
				gearTypes[i].setFont ( font2 );
			}

			main.Reader attachments = new main.Reader ( "Attachments" );
			attachments.loadArray ();
			String[] attachmentsTypeList = attachments.getTypeList ();
			attachmentsTypes = new JCheckBox[attachmentsTypeList.length + 1];
			attachmentsTypes[0] = attachmentsCheckBox;
			for(int i = 1; i < attachmentsTypeList.length + 1; i++)
			{
				attachmentsTypes[i] = new JCheckBox (
						attachmentsTypeList[i - 1] );
				attachmentsTypes[i].setFont ( font2 );
			}

			main.Reader transportation = new main.Reader ( "Transportation" );
			transportation.loadArray ();
			String[] transportationTypeList = transportation.getTypeList ();
			transportationTypes = new JCheckBox[transportationTypeList.length
					+ 1];
			transportationTypes[0] = transportationCheckBox;
			for(int i = 1; i < transportationTypeList.length + 1; i++)
			{
				transportationTypes[i] = new JCheckBox (
						transportationTypeList[i - 1] );
				transportationTypes[i].setFont ( font2 );
			}

			main.Reader starships = new main.Reader ( "Starships" );
			starships.loadArray ();
			String[] starshipsTypeList = starships.getTypeList ();
			starshipsTypes = new JCheckBox[starshipsTypeList.length + 1];
			starshipsTypes[0] = starshipsCheckBox;
			for(int i = 1; i < starshipsTypeList.length + 1; i++)
			{
				starshipsTypes[i] = new JCheckBox ( starshipsTypeList[i - 1] );
				starshipsTypes[i].setFont ( font2 );
			}
		}

	}

	public void organizeTypeLists()
	{

		int rowNumber = 0;
		JCheckBox[][] sorter = new JCheckBox[6][];
		sorter[0] = weaponsTypes;
		sorter[1] = armorTypes;
		sorter[2] = gearTypes;
		sorter[3] = attachmentsTypes;
		sorter[4] = transportationTypes;
		sorter[5] = starshipsTypes;

		compiledCheckList = new JCheckBox[6][];

		for(int i = 0; i < 6; i++)
		{

			compiledCheckList[i] = new JCheckBox[0];

			for(int j = 0; j < 6; j++)
			{

				if(sorter[j].length > compiledCheckList[i].length)
				{

					compiledCheckList[i] = sorter[j];
					rowNumber = j;

				}

			}

			sorter[rowNumber] = new JCheckBox[0];

		}

		inputPreferredWidth = 0;
		for(int i = 0; i < compiledCheckList.length; i++)
		{

			for(int j = 0; j < compiledCheckList[i].length; j++)
			{

				if(compiledCheckList[i][j]
						.getPreferredSize ().width > inputPreferredWidth)
				{

					inputPreferredWidth = compiledCheckList[i][j]
							.getPreferredSize ().width;

				}

			}

		}

		compiledCheckList[0][0]
				.addActionListener ( new java.awt.event.ActionListener ()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{

						if(compiledCheckList[0][0].isSelected ())
						{

							for(int i = 0; i < compiledCheckList[0].length; i++)
							{

								compiledCheckList[0][i].setSelected ( true );

							}

						}
						else
						{

							for(int i = 0; i < compiledCheckList[0].length; i++)
							{

								compiledCheckList[0][i].setSelected ( false );

							}

						}

					}

				} );

		compiledCheckList[1][0]
				.addActionListener ( new java.awt.event.ActionListener ()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{

						if(compiledCheckList[1][0].isSelected ())
						{

							for(int i = 0; i < compiledCheckList[1].length; i++)
							{

								compiledCheckList[1][i].setSelected ( true );

							}

						}
						else
						{

							for(int i = 0; i < compiledCheckList[1].length; i++)
							{

								compiledCheckList[1][i].setSelected ( false );

							}

						}

					}

				} );

		compiledCheckList[2][0]
				.addActionListener ( new java.awt.event.ActionListener ()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{

						if(compiledCheckList[2][0].isSelected ())
						{

							for(int i = 0; i < compiledCheckList[2].length; i++)
							{

								compiledCheckList[2][i].setSelected ( true );

							}

						}
						else
						{

							for(int i = 0; i < compiledCheckList[2].length; i++)
							{

								compiledCheckList[2][i].setSelected ( false );

							}

						}

					}

				} );

		compiledCheckList[3][0]
				.addActionListener ( new java.awt.event.ActionListener ()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{

						if(compiledCheckList[3][0].isSelected ())
						{

							for(int i = 0; i < compiledCheckList[3].length; i++)
							{

								compiledCheckList[3][i].setSelected ( true );

							}

						}
						else
						{

							for(int i = 0; i < compiledCheckList[3].length; i++)
							{

								compiledCheckList[3][i].setSelected ( false );

							}

						}

					}

				} );

		compiledCheckList[4][0]
				.addActionListener ( new java.awt.event.ActionListener ()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{

						if(compiledCheckList[4][0].isSelected ())
						{

							for(int i = 0; i < compiledCheckList[4].length; i++)
							{

								compiledCheckList[4][i].setSelected ( true );

							}

						}
						else
						{

							for(int i = 0; i < compiledCheckList[4].length; i++)
							{

								compiledCheckList[4][i].setSelected ( false );

							}

						}

					}

				} );

		compiledCheckList[5][0]
				.addActionListener ( new java.awt.event.ActionListener ()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{

						if(compiledCheckList[5][0].isSelected ())
						{

							for(int i = 0; i < compiledCheckList[5].length; i++)
							{

								compiledCheckList[5][i].setSelected ( true );

							}

						}
						else
						{

							for(int i = 0; i < compiledCheckList[5].length; i++)
							{

								compiledCheckList[5][i].setSelected ( false );

							}

						}

					}

				} );

	}

	public void generateGUI()
	{

		try
		{
			UIManager.setLookAndFeel (
					UIManager.getSystemLookAndFeelClassName () );
		}
		catch(ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			{
				JOptionPane.showMessageDialog ( new javax.swing.JFrame () ,
						"<html><span style='font-size:" + fontSize + "'>"
								+ "Error establishing UI in class \"GUI.\""
								+ "<br>"
								+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text." );
			}
			e.printStackTrace ();
			try
			{
				Desktop.getDesktop ().open (
						new File ( "resources/DUECredits Errors.txt" ) );
			}
			catch(IOException e1)
			{
				{
					JOptionPane.showMessageDialog ( new javax.swing.JFrame () ,
							"<html><span style='font-size: " + fontSize + "'>"
									+ "Error opening \"DUECredits Errors.txt.\""
									+ "<br>Check to make sure it exists."
									+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text." );
				}
			}
		}

		createTypeLists ();
		organizeTypeLists ();

		frame = new JFrame ();
		frame.setName ( "Shop Specializations" );
		shopName = new JLabel ( "Unsaved Shop Type" );
		shopName.setFont ( font1 );
		shopName.setHorizontalAlignment ( SwingConstants.CENTER );
		shopName.setBorder ( BorderFactory.createCompoundBorder (
				new LineBorder ( Color.black ) ,
				new EmptyBorder ( 10 , 10 , 10 , 10 ) ) );

		menuBar = new JMenuBar ();
		load = new JMenuItem ();
		load = new JMenuItem ( "Load" );
		load.setFont ( font2 );
		load.setBorder (
				javax.swing.BorderFactory.createLineBorder ( Color.black ) );
		load.setCursor ( new java.awt.Cursor ( java.awt.Cursor.HAND_CURSOR ) );
		load.addActionListener ( new java.awt.event.ActionListener ()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				loadShop ();
			}
		} );
		menuBar.add ( load );
		frame.setJMenuBar ( menuBar );
		frame.setTitle ( "Shop Specialization Settings" );

		scrollPane = new JScrollPane ();
		scrollPane.getVerticalScrollBar ().setUnitIncrement ( 16 );
		innerPanel = new JPanel ();
		innerPanel.setBorder ( javax.swing.BorderFactory.createCompoundBorder (
				new LineBorder ( Color.black ) ,
				new EmptyBorder ( 20 , 20 , 20 , 20 ) ) );
		panel = new JPanel ();
		panel.setBorder ( javax.swing.BorderFactory.createEmptyBorder ( 20 ,
				20 , 20 , 20 ) );
		titleLabel = new JLabel ( "Shop Specializations" );
		titleLabel.setFont ( font1 );
		description = new JLabel (
				"<html>This menu lets you create custom shop types."
						+ "<br>Just pick which item types the shop specializes in, how "
						+ "many successes that specialization adds to each roll, and how "
						+ "many failures result from it not being a specialty item. Then save it."
						+ "<br>\n"
						+ "<br>\nMac users be aware that you'll have to create your own file first by "
						+ "copying one of the presets and renaming it (Mac won't let my program create files on your computer)."
						+ "</html>" );
		description.setFont ( font2 );
		titleLabel.setHorizontalAlignment ( SwingConstants.CENTER );
		description.setHorizontalAlignment ( SwingConstants.CENTER );

		saveButton = new JButton ();
		saveButton.setBackground ( new java.awt.Color ( 0 , 204 , 204 ) );
		saveButton.setFont ( font2 );
		saveButton.setText ( "SAVE" );
		saveButton.setCursor (
				new java.awt.Cursor ( java.awt.Cursor.HAND_CURSOR ) );

		saveButton.addActionListener ( new java.awt.event.ActionListener ()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				saveShopSpecialization ();
			}
		} );

		cancelButton = new JButton ();
		cancelButton.setFont ( font2 );
		cancelButton.setText ( "CANCEL" );
		cancelButton.setCursor (
				new java.awt.Cursor ( java.awt.Cursor.HAND_CURSOR ) );
		cancelButton.addActionListener ( new java.awt.event.ActionListener ()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				cancelButtonActionPerformed ( evt );
			}
		} );

		successLabel = new JLabel ( "Successes Added" );
		successLabel.setFont ( font2 );
		successLabel.setToolTipText (
				"How many successes are added to an item the shop specializes in."
						+ "<br>I reccommend leaving at 0 or 1." );
		successText = new JTextField ( "0" );
		successText.setFont ( font2 );

		failureLabel = new JLabel ( "Failures Added" );
		failureLabel.setFont ( font2 );
		failureLabel.setToolTipText (
				"How many failures are added to an item the shop does not specialize in." );
		failureText = new JTextField ( "-10" );
		failureText.setFont ( font2 );

		flavorLabel = new JLabel ( "<html><b>Flavor Text" );
		flavorLabel.setHorizontalAlignment ( SwingConstants.CENTER );
		flavorLabel.setFont ( font2 );

		flavorText = new JTextPane ();
		flavorText.setFont ( font2 );
		flavorText.setBorder ( BorderFactory.createCompoundBorder (
				new LineBorder ( Color.BLACK ) ,
				new EmptyBorder ( 10 , 10 , 10 , 10 ) ) );
		flavorText.setText (
				"Welcome! Have a look around! \nLet me know if I can help you find anything!" );

		flavorTextScroll = new JScrollPane ();
		flavorTextScroll.setViewportView ( flavorText );

		SetJLabelHeight.setJLabelHeight ( ( ( inputPreferredWidth * 6 ) ) ,
				description.getText () , description );

		innerPanelLayout = new GroupLayout ( innerPanel );
		innerPanel.setLayout ( innerPanelLayout );

		sequentialMinor = innerPanelLayout.createSequentialGroup ();
		parallelMajor = innerPanelLayout
				.createParallelGroup ( GroupLayout.Alignment.CENTER )
				.addComponent ( shopName )

				.addGroup ( innerPanelLayout.createSequentialGroup ()
						.addComponent ( flavorLabel ,
								GroupLayout.PREFERRED_SIZE ,
								GroupLayout.PREFERRED_SIZE ,
								GroupLayout.PREFERRED_SIZE )
						.addPreferredGap (
								LayoutStyle.ComponentPlacement.RELATED )
						.addComponent ( flavorTextScroll ,
								GroupLayout.DEFAULT_SIZE ,
								GraphicsEnvironment.getLocalGraphicsEnvironment ()
								.getDefaultScreenDevice ()
								.getDisplayMode ()
								.getWidth () / 2 ,
								GraphicsEnvironment.getLocalGraphicsEnvironment ()
								.getDefaultScreenDevice ()
								.getDisplayMode ()
								.getWidth () / 2 ) );

		parallelMinor = innerPanelLayout.createParallelGroup ();

		for(int i = 0; i < compiledCheckList[0].length; i++)
		{

			parallelMinor.addComponent ( compiledCheckList[0][i] ,
					inputPreferredWidth , inputPreferredWidth ,
					inputPreferredWidth );

		}

		sequentialMinor.addGroup ( parallelMinor )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED );

		parallelMinor = innerPanelLayout.createParallelGroup ();

		for(int i = 0; i < compiledCheckList[1].length; i++)
		{

			parallelMinor.addComponent ( compiledCheckList[1][i] ,
					inputPreferredWidth , inputPreferredWidth ,
					inputPreferredWidth );

		}

		sequentialMinor.addGroup ( parallelMinor )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED );

		parallelMinor = innerPanelLayout.createParallelGroup ();

		for(int i = 0; i < compiledCheckList[2].length; i++)
		{

			parallelMinor.addComponent ( compiledCheckList[2][i] ,
					inputPreferredWidth , inputPreferredWidth ,
					inputPreferredWidth );

		}

		sequentialMinor.addGroup ( parallelMinor )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED );

		parallelMinor = innerPanelLayout.createParallelGroup ();

		for(int i = 0; i < compiledCheckList[3].length; i++)
		{

			parallelMinor.addComponent ( compiledCheckList[3][i] ,
					inputPreferredWidth , inputPreferredWidth ,
					inputPreferredWidth );

		}

		sequentialMinor.addGroup ( parallelMinor )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED );

		parallelMinor = innerPanelLayout.createParallelGroup ();

		for(int i = 0; i < compiledCheckList[4].length; i++)
		{

			parallelMinor.addComponent ( compiledCheckList[4][i] ,
					inputPreferredWidth , inputPreferredWidth ,
					inputPreferredWidth );

		}

		sequentialMinor.addGroup ( parallelMinor )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED );

		parallelMinor = innerPanelLayout.createParallelGroup ();

		for(int i = 0; i < compiledCheckList[5].length; i++)
		{

			parallelMinor.addComponent ( compiledCheckList[5][i] ,
					inputPreferredWidth , inputPreferredWidth ,
					inputPreferredWidth );

		}

		sequentialMinor.addGroup ( parallelMinor )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED );

		parallelMajor.addGroup ( sequentialMinor );

		parallelMajor.addGroup ( innerPanelLayout.createSequentialGroup ()
				.addComponent ( successLabel , GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
				.addComponent ( successText , inputPreferredWidth ,
						inputPreferredWidth , inputPreferredWidth )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
				.addComponent ( failureLabel , GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
				.addComponent ( failureText , inputPreferredWidth ,
						inputPreferredWidth , inputPreferredWidth ) );

		innerPanelLayout.setHorizontalGroup ( parallelMajor );

		sequenceMajor = innerPanelLayout.createSequentialGroup ()
				.addComponent ( shopName )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED )
				.addGroup ( innerPanelLayout
						.createParallelGroup ( GroupLayout.Alignment.CENTER )
						.addComponent ( flavorLabel )
						.addComponent ( flavorTextScroll ,
								flavorText.getPreferredSize ().height * 3 ,
								flavorText.getPreferredSize ().height * 3 ,
								flavorText.getPreferredSize ().height * 3 ) );

		for(int i = 0; i < compiledCheckList[5].length; i++)
		{

			sequenceMajor
					.addGroup ( innerPanelLayout.createParallelGroup ()
							.addComponent ( compiledCheckList[0][i] )
							.addComponent ( compiledCheckList[1][i] )
							.addComponent ( compiledCheckList[2][i] )
							.addComponent ( compiledCheckList[3][i] )
							.addComponent ( compiledCheckList[4][i] )
							.addComponent ( compiledCheckList[5][i] ) )
					.addPreferredGap (
							LayoutStyle.ComponentPlacement.UNRELATED );

		}

		for(int i = compiledCheckList[5].length; i < compiledCheckList[4].length; i++)
		{

			sequenceMajor
					.addGroup ( innerPanelLayout.createParallelGroup ()
							.addComponent ( compiledCheckList[0][i] )
							.addComponent ( compiledCheckList[1][i] )
							.addComponent ( compiledCheckList[2][i] )
							.addComponent ( compiledCheckList[3][i] )
							.addComponent ( compiledCheckList[4][i] ) )
					.addPreferredGap (
							LayoutStyle.ComponentPlacement.UNRELATED );

		}

		for(int i = compiledCheckList[4].length; i < compiledCheckList[3].length; i++)
		{

			sequenceMajor
					.addGroup ( innerPanelLayout.createParallelGroup ()
							.addComponent ( compiledCheckList[0][i] )
							.addComponent ( compiledCheckList[1][i] )
							.addComponent ( compiledCheckList[2][i] )
							.addComponent ( compiledCheckList[3][i] ) )
					.addPreferredGap (
							LayoutStyle.ComponentPlacement.UNRELATED );

		}

		for(int i = compiledCheckList[3].length; i < compiledCheckList[2].length; i++)
		{

			sequenceMajor
					.addGroup ( innerPanelLayout.createParallelGroup ()
							.addComponent ( compiledCheckList[0][i] )
							.addComponent ( compiledCheckList[1][i] )
							.addComponent ( compiledCheckList[2][i] ) )
					.addPreferredGap (
							LayoutStyle.ComponentPlacement.UNRELATED );

		}

		for(int i = compiledCheckList[2].length; i < compiledCheckList[1].length; i++)
		{

			sequenceMajor
					.addGroup ( innerPanelLayout.createParallelGroup ()
							.addComponent ( compiledCheckList[0][i] )
							.addComponent ( compiledCheckList[1][i] ) )
					.addPreferredGap (
							LayoutStyle.ComponentPlacement.UNRELATED );

		}

		for(int i = compiledCheckList[1].length; i < compiledCheckList[0].length; i++)
		{

			sequenceMajor
					.addGroup ( innerPanelLayout.createParallelGroup ()
							.addComponent ( compiledCheckList[0][i] ) )
					.addPreferredGap (
							LayoutStyle.ComponentPlacement.UNRELATED );

		}

		sequenceMajor.addGroup ( innerPanelLayout
				.createParallelGroup ( GroupLayout.Alignment.CENTER )
				.addComponent ( successLabel )
				.addComponent ( successText , GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE )
				.addComponent ( failureLabel ).addComponent ( failureText ,
						GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE ) )
				.addContainerGap ();

		innerPanelLayout.setVerticalGroup ( sequenceMajor );

		panelLayout = new GroupLayout ( panel );
		panel.setLayout ( panelLayout );

		panelLayout.setHorizontalGroup ( panelLayout.createParallelGroup ()
				.addComponent ( titleLabel , ( ( inputPreferredWidth * 6 ) ) ,
						( ( inputPreferredWidth * 6 ) ) ,
						( ( inputPreferredWidth * 6 ) ) )
				.addComponent ( description , ( ( inputPreferredWidth * 6 ) ) ,
						( ( inputPreferredWidth * 6 ) ) ,
						( ( inputPreferredWidth * 6 ) ) )
				.addComponent ( innerPanel )
				.addGroup ( GroupLayout.Alignment.TRAILING ,
						panelLayout.createSequentialGroup ()
								.addComponent ( cancelButton )
								.addPreferredGap (
										LayoutStyle.ComponentPlacement.RELATED )
								.addComponent ( saveButton ) ) );
		panelLayout.setVerticalGroup ( panelLayout.createSequentialGroup ()
				.addComponent ( titleLabel )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED )
				.addComponent ( description , GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED )
				.addComponent ( innerPanel )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED )
				.addGroup ( panelLayout.createParallelGroup ()
						.addComponent ( cancelButton )
						.addComponent ( saveButton ) ) );

		scrollPane.setViewportView ( panel );

		layout = new GroupLayout ( frame.getContentPane () );
		frame.getContentPane ().setLayout ( layout );

		layout.setHorizontalGroup (
				layout.createParallelGroup ( GroupLayout.Alignment.CENTER )
						.addComponent ( scrollPane ) );
		layout.setVerticalGroup (
				layout.createSequentialGroup ().addComponent ( scrollPane ) );

		frame.pack ();
		frame.setVisible ( true );

		if(panel.getPreferredSize ().width > GraphicsEnvironment
				.getLocalGraphicsEnvironment ().getDefaultScreenDevice ()
				.getDisplayMode ().getWidth () * .9
				|| panel.getPreferredSize ().height > GraphicsEnvironment
						.getLocalGraphicsEnvironment ()
						.getDefaultScreenDevice ().getDisplayMode ()
						.getHeight () * .9)
		{

			frame.setExtendedState ( JFrame.MAXIMIZED_BOTH );

		}
		else
		{

			frame.setLocationRelativeTo ( null );

		}
	}

	

	protected void loadShop()
	{

		JFileChooser chooser = new JFileChooser ();
		chooser.setAcceptAllFileFilterUsed ( false );
		chooser.setFileFilter ( new FileNameExtensionFilter (
				"SWRPG Shop Specialization files" , "specialization" ) );
		chooser.setCurrentDirectory (
				new File ( "Saved Shop Specializations" ) );
		int retrival = chooser.showSaveDialog ( null );
		if(retrival == JFileChooser.APPROVE_OPTION)
		{
			Scanner reader = null;

			try
			{

				for(int i = 0; i < compiledCheckList.length; i++)
				{

					for(int j = 0; j < compiledCheckList[i].length; j++)
					{

						compiledCheckList[i][j].setSelected ( false );

					}

				}
				reader = new Scanner ( chooser.getSelectedFile () );
				reader.nextLine ();
				flavorText.setText ( reader.nextLine ()
						.replace ( "<html>" , "" ).replace ( "</html>" , "" )
						.replace ( "<br>" , "\n" )
						.replace ( "&nbsp&nbsp&nbsp&nbsp&nbsp " , "\t" ) );
				
			

				successText.setText ( reader.next () );
				failureText.setText ( reader.next () );
				boolean foundIt = false;
				while(reader.hasNext ())
				{
					foundIt = false;
					type = reader.nextLine ();
					for(int i = 0; i < compiledCheckList.length; i++)
					{

						for(int j = 1; j < compiledCheckList[i].length; j++)
						{

							if(compiledCheckList[i][j].getText ()
									.equals ( type ))
							{

								compiledCheckList[i][j].setSelected ( true );
								foundIt = true;
								break;

							}

						}

						if(foundIt)
						{
							break;
						}

					}

				}
				shopName.setText ( "<html><b>" + chooser.getSelectedFile ()
						.getName ().replace ( ".specialization" , "" ) );
			}
			catch(FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
		}

	}

	protected void cancelButtonActionPerformed(ActionEvent evt)
	{

		frame.dispose ();

	}

	protected void saveShopSpecialization()
	{

		PrintStream ps = null;
		JFileChooser chooser = new JFileChooser ();
		chooser.setAcceptAllFileFilterUsed ( false );
		chooser.setFileFilter ( new FileNameExtensionFilter (
				"SWRPG Shop Specialization files" , "specialization" ) );
		chooser.setCurrentDirectory (
				new File ( "Saved Shop Specializations" ) );
		int retrival = chooser.showSaveDialog ( null );
		if(retrival == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				ps = new PrintStream (
						chooser.getSelectedFile ().toString ().replace (
								".specialization" , "" ) + ".specialization" );
				ps.println ( shopName.getText () );
				flavorText.setText ( ( "<html>" + flavorText.getText ()
						.replaceAll ( "\\t" , "&nbsp&nbsp&nbsp&nbsp&nbsp " )
						.replaceAll ( "\\n" , "<br>" ).replaceAll ( "\"" , "\\\"" ) + "</html>" ));
				ps.println(flavorText.getText ());

				flavorText.setText ( flavorText.getText()
						.replace ( "<html>" , "" ).replace ( "</html>" , "" )
						.replace ( "<br>" , "\n" )
						.replace ( "&nbsp&nbsp&nbsp&nbsp&nbsp " , "\t" ) );
				

				ps.println ( successText.getText () );
				ps.println ( failureText.getText () );

				for(int i = 0; i < compiledCheckList.length; i++)
				{

					for(int j = 1; j < compiledCheckList[i].length; j++)
					{

						if(compiledCheckList[i][j].isSelected ())
						{

							ps.println ( compiledCheckList[i][j].getText () );
							ps.flush ();

						}

					}

				}

				ps.close ();
				shopName.setText ( "<html><b>" + chooser.getSelectedFile ()
						.getName ().replace ( ".specialization" , "" ) );
			}
			catch(FileNotFoundException e)
			{
				{
					JOptionPane.showMessageDialog ( new javax.swing.JFrame () ,
							"<html><span style='font-size: " + fontSize + "'>"
									+ "Unable to create shop. If you are using a Mac, you must create the file "
									+ "<br>(copy the \"General Store.specialization\" and rename it to what you want). "
									+ "<br>Unfortunately for me, and fortunately for you, Mac won't let me create "
									+ "<br>files on your system. " + "<br>"
									+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text." );
				}
				e.printStackTrace ();
			}

		}

	}
	
	
}
