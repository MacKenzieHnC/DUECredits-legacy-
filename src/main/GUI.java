
package main;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import helper.Settings;

public class GUI implements ActionListener
{

	private JFrame frame, welcomeFrame;
	private JEditorPane editor, welcomeEditor;
	private JScrollPane scrollPane, welcomeScrollPane;
	private JMenuBar menuBar;
	private JMenu runMenu, settingsMenu, helpMenu;
	private JMenuItem rerollShop, welcome;

	private Settings setting = new Settings ();
	int fontSize = setting.getSettingNumber ( 17 );
	private Reader weapons;
	private Reader armor;
	private Reader gear;
	private Choices choice;

	String html = null;
	private JMenu editMenu;
	private JMenuItem addSource;
	private JMenuItem editWeapons;
	private AbstractButton editArmor;
	private JMenuItem editGear;
	private JMenuItem appearance;
	private JMenuItem advanced;
	private JMenuItem shopOdds;
	private JMenuItem markups;
	private JMenuItem sourceBooks;
	private JCheckBox welcomeCheckBox;
	private JMenuBar welcomeMenu;
	private GroupLayout layout;
	private JTable weaponsTable;
	private JTable armorTable;
	private JTable gearTable;
	private JPanel tablePanel;
	private JScrollPane weaponsScroll;
	private JScrollPane armorScroll;
	private JScrollPane gearScroll;
	private JTabbedPane tabbedPane;
	private JTextField searchBar;
	private JButton searchButton;
	private GroupLayout tablePanelLayout;
	private String fontName;
	private Font font2;
	private JMenuItem saveShop;
	private JMenuItem loadShop;
	private DefaultTableModel weaponsTableModel;
	private DefaultTableModel armorTableModel;
	private DefaultTableModel gearTableModel;
	private JButton fullListButton;
	private JFileChooser chooser;
	private String titleLine;
	private String[][] weaponsList;
	private String[][] armorList;
	private String[][] gearList;
	private Reader attachments;
	private JMenuItem editAttachments;
	private JTable attachmentsTable;
	private DefaultTableModel attachmentsTableModel;
	private JScrollPane attachmentsScroll;
	private JMenuItem index;
	private JFrame indexFrame;
	private JEditorPane indexEditor;
	private JScrollPane indexScrollPane;
	private String[][] attachmentsList;
	private JMenuItem shopSpecialization;
	private int maxShopSize;
	private int minShopSize;
	private JMenuItem editTransportation;
	private JMenuItem editStarships;
	private Reader transportation;
	private Reader starships;
	private JTable transportationTable;
	private DefaultTableModel transportationTableModel;
	private JScrollPane transportationScroll;
	private JTable starshipsTable;
	private DefaultTableModel starshipsTableModel;
	private JScrollPane starshipsScroll;
	private String shopFlavor;
	private String shopName;
	private JLabel shopNameLabel;
	private Font font1;
	private JLabel shopFlavorLabel;
	private JPanel shopFlavorPanel;
	private JScrollPane shopFlavorScroll;
	private String[][] transportationList;
	private String[][] starshipsList;
	private JMenuItem rollIndividually;

	private boolean shouldIRollIndividually;
	private JDialog changeShopFlavorDialog;
	private JLabel flavorLabel;
	private JTextArea flavorText;
	private JScrollPane flavorScroll;
	private JButton flavorSaveButton;
	private JPanel flavorPanel;
	private boolean shopLoaded;
	private JMenuItem restoreLastShop;

	public void boot()
	{

		setting = new Settings ();

		try
		{

			choice = new Choices ();

			if(choice.choices ( shouldIRollIndividually ).equals ( "Continue" ))
			{

				try

				{
					frame.setCursor (
							Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );

					weapons = new Reader ( "Weapons" );
					weapons.loadArray ();
					weapons.sortType ();
					armor = new Reader ( "Armor" );
					armor.loadArray ();
					armor.sortType ();
					gear = new Reader ( "Gear" );
					gear.loadArray ();
					gear.sortType ();
					attachments = new Reader ( "Attachments" );
					attachments.loadArray ();
					attachments.sortType ();
					transportation = new Reader ( "Transportation" );
					transportation.loadArray ();
					transportation.sortType ();
					starships = new Reader ( "Starships" );
					starships.loadArray ();
					starships.sortType ();

					weapons.setRollIndividually ( shouldIRollIndividually );

					if(!weapons.getRollIndividually ())
					{
						if(choice.getCharacteristicLevel () > 0
								|| choice.getSkillLevel () > 0)
						{
							weapons.setShopSpecialization (
									choice.getSpecialization () );
							weapons.randomize ( choice.getShopType () ,
									choice.getNumBoostDice () ,
									choice.getNumSetbackDice () ,
									choice.getCharacteristicLevel () ,
									choice.getSkillLevel () );
							gear.setShopSpecialization (
									choice.getSpecialization () );
							gear.randomize ( choice.getShopType () ,
									choice.getNumBoostDice () ,
									choice.getNumSetbackDice () ,
									choice.getCharacteristicLevel () ,
									choice.getSkillLevel () );
							armor.setShopSpecialization (
									choice.getSpecialization () );
							armor.randomize ( choice.getShopType () ,
									choice.getNumBoostDice () ,
									choice.getNumSetbackDice () ,
									choice.getCharacteristicLevel () ,
									choice.getSkillLevel () );
							attachments.setShopSpecialization (
									choice.getSpecialization () );
							attachments.randomize ( choice.getShopType () ,
									choice.getNumBoostDice () ,
									choice.getNumSetbackDice () ,
									choice.getCharacteristicLevel () ,
									choice.getSkillLevel () );
							transportation.setShopSpecialization (
									choice.getSpecialization () );
							transportation.randomize ( choice.getShopType () ,
									choice.getNumBoostDice () ,
									choice.getNumSetbackDice () ,
									choice.getCharacteristicLevel () ,
									choice.getSkillLevel () );
							starships.setShopSpecialization (
									choice.getSpecialization () );
							starships.randomize ( choice.getShopType () ,
									choice.getNumBoostDice () ,
									choice.getNumSetbackDice () ,
									choice.getCharacteristicLevel () ,
									choice.getSkillLevel () );
							shopFlavor = weapons.getShopFlavor ();
							shopName = weapons.getShopName ();

							shrinkShop ();
						}
						else
						{

							{
								JOptionPane.showMessageDialog (
										new javax.swing.JFrame () ,
										"<html><span style='font-size: "
												+ fontSize + "'>"
												+ "Either skill or characteristic must be greater than zero."
												+ "<br>Otherwise, you could not possibly get a positive roll on any items." );
							}

						}
					}

					weapons.createSourcedList ();
					armor.createSourcedList ();
					gear.createSourcedList ();
					attachments.createSourcedList ();
					transportation.createSourcedList ();
					starships.createSourcedList ();

					shopLoaded = false;
					runDUECredits ();
					
					if(!shouldIRollIndividually)
					{
						
						EventQueue.invokeLater ( new Runnable ()
						{

							

							@Override
							public void run()
							{
								{
									File directory = new File ( "temp" );
									

									for(int i = 0; i < directory.listFiles ().length; i++)
									{

										try
										{
											Files.delete ( directory.listFiles ()[i].toPath ());
										}
										catch(IOException e)
										{
											e.printStackTrace();
										}

									}

								}
								saveShop(new File("temp/" + shopName + ".shop"));
							}
						} );
						
					}
					

				}
				finally
				{
					EventQueue.invokeLater ( new Runnable ()
					{

						@Override
						public void run()
						{
							frame.setCursor ( Cursor.getDefaultCursor () );
						}
					} );

				}

			}
			editor.setCaretPosition ( 0 );

		}
		catch(NullPointerException e)
		{

		}
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
						"<html><span style='font-size: " + fontSize + "'>"
								+ "Error establishing UI in class \"GUI.\""
								+ "<br>"
								+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text." );
			}
			e.printStackTrace ();

		}

		frame = new JFrame ();
		editor = new JEditorPane ();
		scrollPane = new JScrollPane ( editor );

		frame.setTitle ( "D.U.E. Credits beta v1.0 (13 July 2016)" );
		frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		editor.setEditable ( false );
		editor.setContentType ( "text/html" );
		setHTML ();
		editor.setCaretPosition ( 0 );

		// Adds a basic menu bar
		menuBar = new JMenuBar ();

		// Creates "File" Menu
		runMenu = new JMenu ( "File" );
		runMenu.setMnemonic ( KeyEvent.VK_R );
		menuBar.add ( runMenu );

		// Creates "Roll" button
		rerollShop = new JMenuItem ( "Roll/Reroll Shop" );
		rerollShop.addActionListener ( this );
		rerollShop.setActionCommand ( "RerollShop" );
		runMenu.add ( rerollShop );

		// Creates "Roll for Items Individually" button
		rollIndividually = new JMenuItem ( "Generate Full List" );
		rollIndividually.addActionListener ( this );
		rollIndividually.setActionCommand ( "RollIndividually" );
		runMenu.add ( rollIndividually );

		// Creates "Save Shop" button
		saveShop = new JMenuItem ( "Save Shop" );
		saveShop.addActionListener ( this );
		saveShop.setActionCommand ( "saveShop" );
		runMenu.add ( saveShop );
		saveShop.setEnabled ( false );

		// Creates "Load Shop" button
		loadShop = new JMenuItem ( "Load Shop" );
		loadShop.addActionListener ( this );
		loadShop.setActionCommand ( "loadShop" );
		runMenu.add ( loadShop );

		// Creates "Restore last shop" button
		restoreLastShop = new JMenuItem ( "Restore the last shop" );
		restoreLastShop.addActionListener ( this );
		restoreLastShop.setActionCommand ( "Restore" );
		runMenu.add ( restoreLastShop );

		// Creates "Edit" Menu
		editMenu = new JMenu ( "Edit" );
		editMenu.setMnemonic ( KeyEvent.VK_E );
		menuBar.add ( editMenu );

		// Creates "Add Sourcebook" option
		addSource = new JMenuItem ( "Add Sourcebook" );
		addSource.addActionListener ( this );
		addSource.setActionCommand ( "AddSource" );
		addSource.setToolTipText ( "Add a source book to draw items from." );
		editMenu.add ( addSource );

		// Creates "Edit Weapons" option
		editWeapons = new JMenuItem ( "Edit Weapons List" );
		editWeapons.addActionListener ( this );
		editWeapons.setActionCommand ( "EditWeapons" );
		editWeapons.setToolTipText ( "Edit the list of \"Weapon\" items." );
		editMenu.add ( editWeapons );

		// Creates "Edit Armor" option
		editArmor = new JMenuItem ( "Edit Armor List" );
		editArmor.addActionListener ( this );
		editArmor.setActionCommand ( "EditArmor" );
		editArmor.setToolTipText ( "Edit the list of \"Armor\" items." );
		editMenu.add ( editArmor );

		// Creates "Edit Gear" option
		editGear = new JMenuItem ( "Edit Gear List" );
		editGear.addActionListener ( this );
		editGear.setActionCommand ( "EditGear" );
		editGear.setToolTipText ( "Edit the list of \"Gear\" items." );
		editMenu.add ( editGear );

		// Creates "Edit Attachments" option
		editAttachments = new JMenuItem ( "Edit Attachments List" );
		editAttachments.addActionListener ( this );
		editAttachments.setActionCommand ( "EditAttachments" );
		editAttachments
				.setToolTipText ( "Edit the list of \"Attachments\" items." );
		editMenu.add ( editAttachments );

		// Creates "Edit Transportation" option
		editTransportation = new JMenuItem ( "Edit Transportation List" );
		editTransportation.addActionListener ( this );
		editTransportation.setActionCommand ( "EditTransportation" );
		editTransportation.setToolTipText (
				"Edit the list of \"Transportation\" items." );
		editMenu.add ( editTransportation );

		// Creates "Edit Starships" option
		editStarships = new JMenuItem ( "Edit Starships List" );
		editStarships.addActionListener ( this );
		editStarships.setActionCommand ( "EditStarships" );
		editStarships
				.setToolTipText ( "Edit the list of \"Starships\" items." );
		editMenu.add ( editStarships );

		// Creates "Settings" Menu
		settingsMenu = new JMenu ( "Settings" );
		settingsMenu.setMnemonic ( KeyEvent.VK_S );
		menuBar.add ( settingsMenu );

		// Creates "Shop Odds Modifiers" option
		shopOdds = new JMenuItem ( "Shop Odds Modifiers" );
		shopOdds.addActionListener ( this );
		shopOdds.setActionCommand ( "Odds" );
		shopOdds.setToolTipText ( "Edit item odds for specific shop types." );
		settingsMenu.add ( shopOdds );

		// Creates "Markups" option
		markups = new JMenuItem ( "Shop Markups/Discounts" );
		markups.addActionListener ( this );
		markups.setActionCommand ( "Markups" );
		markups.setToolTipText ( "Edit shop markups/disconts" );
		settingsMenu.add ( markups );

		// Creates "Source Books" option
		sourceBooks = new JMenuItem ( "Source Book Selection" );
		sourceBooks.addActionListener ( this );
		sourceBooks.setActionCommand ( "SourceBook" );
		sourceBooks.setToolTipText (
				"Select which source books items are drawn from." );
		settingsMenu.add ( sourceBooks );

		// Creates "Appearance" option
		appearance = new JMenuItem ( "Appearance" );
		appearance.addActionListener ( this );
		appearance.setActionCommand ( "Appearance" );
		appearance.setToolTipText ( "Change the appearance." );
		settingsMenu.add ( appearance );

		// Creates "Advanced Settings" option
		advanced = new JMenuItem ( "Advanced Settings" );
		advanced.addActionListener ( this );
		advanced.setActionCommand ( "Advanced" );
		advanced.setToolTipText ( "Change the rules of dice rolls." );
		settingsMenu.add ( advanced );

		// Creates "Shop Specialization Settings" option
		shopSpecialization = new JMenuItem ( "Shop Specializations" );
		shopSpecialization.addActionListener ( this );
		shopSpecialization.setActionCommand ( "Special" );
		shopSpecialization.setToolTipText ( "Add or Edit Shop Types." );
		settingsMenu.add ( shopSpecialization );

		// Creates "Help" Menu
		helpMenu = new JMenu ( "Help" );
		helpMenu.setMnemonic ( KeyEvent.VK_H );
		menuBar.add ( helpMenu );

		// Creates "Welcome" option for "Help" Menu
		welcome = new JMenuItem ( "Welcome" );
		welcome.addActionListener ( this );
		welcome.setActionCommand ( "Welcome" );
		helpMenu.add ( welcome );

		// Creates "Welcome" frame and sets it to invisible
		welcomeFrame = new JFrame ();
		welcomeFrame.setTitle ( "Welcome!" );
		welcomeFrame.setVisible ( false );

		// Creates "index" option for "Help" Menu
		index = new JMenuItem ( "Index Key" );
		index.addActionListener ( this );
		index.setActionCommand ( "Index" );
		helpMenu.add ( index );

		// Creates "index" frame and sets it to invisible
		indexFrame = new JFrame ();
		indexFrame.setTitle ( "Index Key" );
		indexFrame.setVisible ( false );

		welcomeCheckBox = new JCheckBox ( "Do not show this message again." );
		welcomeCheckBox.setSelected (
				Boolean.parseBoolean ( setting.getSettingString ( 33 ) ) );
		welcomeCheckBox.addActionListener ( new java.awt.event.ActionListener ()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				welcomeCheckBoxActionPerformed ( evt );
			}
		} );
		welcomeEditor = new JEditorPane ();
		welcomeEditor.setEditable ( false );
		welcomeEditor.setContentType ( "text/html" );
		welcomeEditor.setBorder ( javax.swing.BorderFactory
				.createEmptyBorder ( 20 , 20 , 20 , 20 ) );
		welcomeMenu = new JMenuBar ();
		welcomeMenu.add ( welcomeCheckBox );
		welcomeFrame.setJMenuBar ( welcomeMenu );
		welcomeScrollPane = new JScrollPane ();
		welcomeScrollPane.setViewportView ( welcomeEditor );
		welcomeFrame.add ( welcomeScrollPane );
		welcomeFrame.pack ();
		welcomeFrame.setSize ( 800 , 600 );
		welcomeFrame.setLocation ( 100 , 0 );

		indexEditor = new JEditorPane ();
		indexEditor.setEditable ( false );
		indexEditor.setContentType ( "text/html" );
		indexEditor.setBorder ( javax.swing.BorderFactory
				.createEmptyBorder ( 20 , 20 , 20 , 20 ) );
		indexScrollPane = new JScrollPane ( indexEditor );
		indexFrame.add ( indexScrollPane );
		indexFrame.pack ();
		indexFrame.setSize ( 800 , 600 );
		indexFrame.setLocation ( 100 , 0 );

		frame.setJMenuBar ( menuBar );

		// Allows use of hyperlinks
		editor.addHyperlinkListener ( new HyperlinkListener ()
		{

			public void hyperlinkUpdate(HyperlinkEvent e)
			{

				if(e.getEventType () == HyperlinkEvent.EventType.ACTIVATED)
				{

					Scanner reader = new Scanner ( e.getDescription () );
					reader.useDelimiter ( "/" );
					String action = reader.next ();
					reader.close ();
					try
					{
						frame.setCursor ( Cursor
								.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );
						if(action.equals ( "DUECredits" ))
						{
							boot ();
							
						}

					}
					finally
					{
						EventQueue.invokeLater ( new Runnable ()
						{

							@Override
							public void run()
							{

								frame.setCursor ( Cursor.getDefaultCursor () );
							}
						} );

					}
				}
			}
		} );

		layout = new GroupLayout ( frame.getContentPane () );
		frame.getContentPane ().setLayout ( layout );

		layout.setHorizontalGroup (
				layout.createParallelGroup ().addComponent ( scrollPane ) );
		layout.setVerticalGroup (
				layout.createSequentialGroup ().addComponent ( scrollPane ) );

		frame.pack ();
		frame.setVisible ( true );
		frame.setExtendedState ( JFrame.MAXIMIZED_BOTH );

		if(!Boolean.parseBoolean ( setting.getSettingString ( 33 ) ))
		{

			welcome.doClick ();

		}

	}

	protected void welcomeCheckBoxActionPerformed(ActionEvent evt)
	{

		try
		{
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );

			setting.setSetting ( 33 ,
					Boolean.toString ( welcomeCheckBox.isSelected () ) );
			setting.saveSettings ();
		}
		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}

	}

	public void setHTML()
	{

		html = "<html>\n<head>" + "<style>" + "body {\n\tbackground-color:"
				+ setting.getSettingString ( 16 ) + ";}" + "</style>"
				+ "\n</head>";
		html += "\n<body style=\"font-size: " + setting.getSettingString ( 17 )
				+ "; color:" + setting.getSettingString ( 18 ) + ";\">\n";

		html += "<a href=\"DUECredits/run\"><img src=\"file:resources/Background.jpg\" alt=\"Smiley face\" height=\""
				+ ( GraphicsEnvironment.getLocalGraphicsEnvironment ()
						.getDefaultScreenDevice ().getDisplayMode ()
						.getHeight () - 30 )
				+ "\" width = \""
				+ GraphicsEnvironment.getLocalGraphicsEnvironment ()
						.getDefaultScreenDevice ().getDisplayMode ().getWidth ()
				+ "\"></a>";

		html += "\n</body>\n</html>";
		editor.setText ( html.replace ( " // " , "<br>" ) );
		scrollPane.setViewportView ( editor );
		layout = new GroupLayout ( frame.getContentPane () );
		frame.getContentPane ().setLayout ( layout );

		layout.setHorizontalGroup (
				layout.createParallelGroup ().addComponent ( scrollPane ) );
		layout.setVerticalGroup (
				layout.createSequentialGroup ().addComponent ( scrollPane ) );

		frame.pack ();
		frame.setExtendedState ( JFrame.MAXIMIZED_BOTH );

	}

	public void actionPerformed(ActionEvent actionEvent)
	{

		try
		{
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );

			if(!actionEvent.getActionCommand ().equals ( "Index" )
					&&
					!actionEvent.getActionCommand ().equals ( "Welcome" ))
			{
				setHTML ();
			}
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					if(actionEvent.getActionCommand ().equals ( "Welcome" ))
					{
						String welcomeHTML = "";
						welcomeHTML = "<html>\n<head>\n</head>";
						welcomeHTML += "\n<body style=\"background-color:"
								+ setting.getSettingString ( 16 )
								+ "; font-size: "
								+ setting.getSettingString ( 17 ) + "; color:"
								+ setting.getSettingString ( 18 ) + ";\">\n";
						welcomeHTML += "Welcome! Thanks for trying out my program!"
								+ "<br>"
								+ "<br>&nbsp&nbsp&nbsp&nbsp So the basic usage of the program is pretty simple: just click "
								+ "on Watto and tell him where you're shopping and it'll go."
								+ "<br>"
								+ "<br>&nbsp&nbsp&nbsp&nbsp I've also added in a lot of cool features for editing items and "
								+ "sourcebooks, so be sure to check those out."
								+ "<br>"
								+ "<br>&nbsp&nbsp&nbsp&nbsp When you load up a shop, it's gonna look like no items showed up. That's because"
								+ " I set it up to have collapsable/expandable categories for each item type. Just click on the hyperlink "
								+ "(e.g. \"<u>Brawl</u>\") and it'll open up the list of Brawl items that were successfully rolled for."
								+ "<br>"
								+ "<br>&nbsp&nbsp&nbsp&nbsp The program goes through and does a dice roll "
								+ "(based on a given character's stats) for every item from every sourcebook that you have "
								+ "selected, so my hope is that it'll save you a <i>lot</i> of time on the shopping portion "
								+ "of your roleplaying. No more \"Do they have this?\" \"Roll for it... No...\" \"What about "
								+ "this?\" \"Roll for it... No...\" so you can spend more time actually playing."
								+ "<br>"
								+ "<br>&nbsp&nbsp&nbsp&nbsp For the most part, I think it's all pretty straightforward. Mess with the settings to "
								+ "see what you like." + "<br>"
								+ "<br>&nbsp&nbsp&nbsp&nbsp Oh! One more thing! If you have any suggestions/complaints/errors, send me an email at "
								+ "<b><u>MacKenzieHnC@gmail.com</b></u>. I usually respond the same day. "
								+ "Please do contact me with suggestions, especially if you have ideas for entire other programs"
								+ " that would make roleplayers' lives easier. I won't guarantee that I'll work on your program "
								+ "specifically, but I'm always looking for new ideas and projects."
								+ "<br>"
								+ "<br>&nbsp&nbsp&nbsp&nbsp Anyway, I think that's about it."
								+ "<br>"
								+ "<br>&nbsp&nbsp&nbsp&nbsp <b><i>Happy Gaming!</i></b>"
								+ "<br><br>beta v1.0 (13 July 2016)";
						welcomeHTML += "</body></html>";
						welcomeEditor.setText ( welcomeHTML );
						welcomeEditor.setCaretPosition ( 0 );
						welcomeFrame.setVisible ( true );
					}

					else if(actionEvent.getActionCommand ().equals ( "Index" ))
					{

						String indexHTML = "";
						indexHTML = "<html>\n<head>\n</head>";
						indexHTML += "\n<body style=\"background-color:"
								+ setting.getSettingString ( 16 )
								+ "; font-size: "
								+ setting.getSettingString ( 17 ) + "; color:"
								+ setting.getSettingString ( 18 ) + ";\">\n";
						{

							sourceBooksAndItems.SourceBooks source = new sourceBooksAndItems.SourceBooks ();
							indexHTML += "<table border = 1 width = \""
									+ setting.getSettingString ( 19 )
									+ "\" style=\"font-size: " + fontSize
									+ "; color: "
									+ setting.getSettingString ( 18 )
									+ ";\">\n\t<tr>";

							indexHTML += "<td align = \"center\" colspan=\"2\"><u><b>Index Key</b></u></td></tr>";

							for(int i = 0; i < source
									.getSourceBookCount (); i++)
							{

								indexHTML += "<tr><td align = \"right\" ><b>"
										+ source.getSourceBookAbbreviation ( i )
										+ "</b></td><td>"
										+ source.getSourceBookName ( i )
										+ "</td></tr>";

							}

							indexHTML += "</table>";
						}
						indexHTML += "</body></html>";
						indexEditor.setText ( indexHTML );
						indexEditor.setCaretPosition ( 0 );
						indexFrame.setVisible ( true );

					}

					if(actionEvent.getActionCommand ().equals ( "RerollShop" ))
					{
						shouldIRollIndividually = false;
						boot ();
						saveShop.setEnabled ( true );
						
					}

					else if(actionEvent.getActionCommand ()
							.equals ( "RollIndividually" ))
					{
						shouldIRollIndividually = true;
						boot ();
						saveShop.setEnabled ( false );
					}

					else if(actionEvent.getActionCommand ()
							.equals ( "Restore" ))
					{
						shouldIRollIndividually = false;
						saveShop.setEnabled ( true );
						File directory = new File("temp");
						
						loadShop(directory.listFiles ()[0]);
					}

					else
					{
						if(actionEvent.getActionCommand ()
								.equals ( "EditWeapons" ))
						{

							new sourceBooksAndItems.EditItems ()
									.run ( "Weapons" );

						}

						else if(actionEvent.getActionCommand ()
								.equals ( "EditArmor" ))
						{

							new sourceBooksAndItems.EditItems ()
									.run ( "Armor" );

						}

						else if(actionEvent.getActionCommand ()
								.equals ( "EditGear" ))
						{

							new sourceBooksAndItems.EditItems ().run ( "Gear" );

						}

						else if(actionEvent.getActionCommand ()
								.equals ( "EditAttachments" ))
						{

							new sourceBooksAndItems.EditItems ()
									.run ( "Attachments" );

						}

						else if(actionEvent.getActionCommand ()
								.equals ( "EditTransportation" ))
						{

							new sourceBooksAndItems.EditItems ()
									.run ( "Transportation" );

						}

						else if(actionEvent.getActionCommand ()
								.equals ( "EditStarships" ))
						{

							new sourceBooksAndItems.EditItems ()
									.run ( "Starships" );

						}
						else if(actionEvent.getActionCommand ()
								.equals ( "AddSource" ))
						{

							sourceBooksAndItems.AddSourceBook addSource = new sourceBooksAndItems.AddSourceBook ();
							addSource.addSourceBook ();

						}
						else if(actionEvent.getActionCommand ()
								.equals ( "Appearance" ))
						{

							settings.Appearance changeAppearance = new settings.Appearance ();
							changeAppearance.run ();

						}
						else if(actionEvent.getActionCommand ()
								.equals ( "Advanced" ))
						{

							settings.AdvancedSettings advancedSettings = new settings.AdvancedSettings ();
							advancedSettings.run ();

						}
						else if(actionEvent.getActionCommand ()
								.equals ( "Special" ))
						{

							settings.ShopSpecializationSettings special = new settings.ShopSpecializationSettings ();
							special.generateGUI ();
						}
						else if(actionEvent.getActionCommand ()
								.equals ( "SourceBook" ))
						{

							settings.SourceBookSelection source = new settings.SourceBookSelection ();
							source.run ();

						}
						else if(actionEvent.getActionCommand ()
								.equals ( "Odds" ))
						{

							settings.ShopOddsModifiers odds = new settings.ShopOddsModifiers ();
							odds.run ();

						}
						else if(actionEvent.getActionCommand ()
								.equals ( "Markups" ))
						{

							settings.ShopMarkups markups = new settings.ShopMarkups ();
							markups.run ();

						}
						else if(actionEvent.getActionCommand ()
								.equals ( "saveShop" ))
						{
							changeShopFlavor ();

							JFileChooser chooser = new JFileChooser ();
							chooser.setAcceptAllFileFilterUsed ( false );
							chooser.setFileFilter (
									new FileNameExtensionFilter (
											"SWRPG Shop files" , "shop" ) );
							chooser.setCurrentDirectory (
									new File ( "Saved Shops" ) );
							int retrival = chooser.showSaveDialog ( null );
							if(retrival == JFileChooser.APPROVE_OPTION)
							{
								saveShop ( chooser.getSelectedFile () );
							}

						}
						else if(actionEvent.getActionCommand ()
								.equals ( "loadShop" ))
						{

							try

							{
								frame.setCursor ( Cursor.getPredefinedCursor (
										Cursor.WAIT_CURSOR ) );

								chooser = new JFileChooser ();
								chooser.setCurrentDirectory (
										new File ( "Saved Shops" ) );
								chooser.setAcceptAllFileFilterUsed ( false );
								chooser.setFileFilter (
										new FileNameExtensionFilter (
												"SWRPG Shop files" , "shop" ) );
							}
							finally
							{
								EventQueue.invokeLater ( new Runnable ()
								{

									@Override
									public void run()
									{

										frame.setCursor (
												Cursor.getDefaultCursor () );
									}
								} );

							}
							int retrival = chooser.showOpenDialog ( null );
							if(retrival == JFileChooser.APPROVE_OPTION)
							{
								loadShop ( chooser.getSelectedFile () );
							}

						}

					}

				}

			} );
		}

		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}
	}

	public void runDUECredits()
	{

		try
		{
			fontSize = setting.getSettingNumber ( 17 );
			fontName = setting.getSettingString ( 26 );

			font1 = new Font ( fontName , 0 ,
					(int) ( Math.round ( fontSize * 1.25 ) ) );
			font2 = new Font ( fontName , 0 , fontSize );
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );
			tabbedPane = new JTabbedPane ();
			weaponsTable = weapons.createJTable ();
			weaponsTableModel = weapons.getTableModel ();
			weaponsTableModel.addTableModelListener ( new TableModelListener ()
			{

				@Override
				public void tableChanged(TableModelEvent e)
				{

					updateWeaponsTable ();

				}

			} );

			weaponsScroll = new JScrollPane ();

			weaponsScroll.setViewportView ( weaponsTable );

			searchBar = new JTextField ();
			searchBar.setFont ( font2 );
			searchButton = new JButton ( "SEARCH" );
			searchButton.setFont ( font2 );
			searchButton
					.addActionListener ( new java.awt.event.ActionListener ()
					{

						public void actionPerformed(
								java.awt.event.ActionEvent evt)
						{

							searchButtonActionPerformed ( evt );
						}
					} );
			fullListButton = new JButton ( "fullList" );
			fullListButton.setFont ( font2 );
			fullListButton
					.addActionListener ( new java.awt.event.ActionListener ()
					{

						public void actionPerformed(
								java.awt.event.ActionEvent evt)
						{

							fullList ();
						}
					} );

			tablePanel = new JPanel ();
			tablePanel.setBorder ( javax.swing.BorderFactory
					.createEmptyBorder ( 20 , 20 , 20 , 20 ) );
			frame.getRootPane ().setDefaultButton ( searchButton );

			armorTable = armor.createJTable ();
			armorScroll = new JScrollPane ();

			armorTableModel = armor.getTableModel ();
			armorTableModel.addTableModelListener ( new TableModelListener ()
			{

				@Override
				public void tableChanged(TableModelEvent e)
				{

					updateArmorTable ();

				}

			} );

			if(armor.getDisplayedList ().length > 0)
			{
				armorScroll.setViewportView ( armorTable );
			}
			else
			{

				JLabel armorLabel = new JLabel ();
				String html = null;
				html = "<html>";
				html += "<body>";
				html += "<table width = \"" + setting.getSettingString ( 19 )
						+ "\" " + "height = \""
						+ setting.getSettingString ( 19 ) + "\" "
						+ "style=\"color: " + setting.getSettingString ( 18 )
						+ ";\">" + "<tr>";
				html += "<td  align = \"center\">"

						+ "I'm sorry, we don't currently have any armor in stock."
						+ "<br>Please check back again later."

						+ "</td>" + "</tr>" + "</table>";
				html += "</body></html>";
				armorLabel.setText ( html );
				armorLabel.setFont ( font2 );
				armorLabel.setHorizontalAlignment ( SwingConstants.CENTER );
				armorScroll.setViewportView ( armorLabel );

			}
			gearTable = gear.createJTable ();
			gearTableModel = gear.getTableModel ();
			gearTableModel.addTableModelListener ( new TableModelListener ()
			{

				@Override
				public void tableChanged(TableModelEvent e)
				{

					updateGearTable ();

				}

			} );
			gearScroll = new JScrollPane ();

			if(gear.getDisplayedList ().length > 0)
			{
				gearScroll.setViewportView ( gearTable );
			}
			else
			{

				JLabel gearLabel = new JLabel ();
				String html = null;
				html = "<html>";
				html += "<body>";
				html += "<table width = \"" + setting.getSettingString ( 19 )
						+ "\" " + "height = \""
						+ setting.getSettingString ( 19 ) + "\" "
						+ "style=\"color: " + setting.getSettingString ( 18 )
						+ ";\">" + "<tr>";
				html += "<td  align = \"center\">"

						+ "I'm sorry, we don't currently have any gear in stock."
						+ "<br>Please check back again later."

						+ "</td>" + "</tr>" + "</table>";
				html += "</body></html>";
				gearLabel.setText ( html );
				gearLabel.setFont ( font2 );
				gearLabel.setHorizontalAlignment ( SwingConstants.CENTER );
				gearScroll.setViewportView ( gearLabel );

			}

			attachmentsTable = attachments.createJTable ();
			attachmentsTableModel = attachments.getTableModel ();
			attachmentsTableModel
					.addTableModelListener ( new TableModelListener ()
					{

						@Override
						public void tableChanged(TableModelEvent e)
						{

							updateAttachmentsTable ();

						}

					} );
			attachmentsScroll = new JScrollPane ();

			if(attachments.getDisplayedList ().length > 0)
			{
				attachmentsScroll.setViewportView ( attachmentsTable );
			}
			else
			{

				JLabel attachmentsLabel = new JLabel ();
				String html = null;
				html = "<html>";
				html += "<body>";
				html += "<table width = \"" + setting.getSettingString ( 19 )
						+ "\" " + "height = \""
						+ setting.getSettingString ( 19 ) + "\" "
						+ "style=\"color: " + setting.getSettingString ( 18 )
						+ ";\">" + "<tr>";
				html += "<td  align = \"center\">"

						+ "I'm sorry, we don't currently have any attachments in stock."
						+ "<br>Please check back again later."

						+ "</td>" + "</tr>" + "</table>";
				html += "</body></html>";
				attachmentsLabel.setText ( html );
				attachmentsLabel.setFont ( font2 );
				attachmentsLabel
						.setHorizontalAlignment ( SwingConstants.CENTER );
				attachmentsScroll.setViewportView ( attachmentsLabel );

			}
			transportationTable = transportation.createJTable ();
			transportationTableModel = transportation.getTableModel ();
			transportationTableModel
					.addTableModelListener ( new TableModelListener ()
					{

						@Override
						public void tableChanged(TableModelEvent e)
						{

							updateTransportationTable ();

						}

					} );
			transportationScroll = new JScrollPane ();

			if(transportation.getDisplayedList ().length > 0)
			{
				transportationScroll.setViewportView ( transportationTable );
			}
			else
			{

				JLabel transportationLabel = new JLabel ();
				String html = null;
				html = "<html>";
				html += "<body>";
				html += "<table width = \"" + setting.getSettingString ( 19 )
						+ "\" " + "height = \""
						+ setting.getSettingString ( 19 ) + "\" "
						+ "style=\"color: " + setting.getSettingString ( 18 )
						+ ";\">" + "<tr>";
				html += "<td  align = \"center\">"

						+ "I'm sorry, we don't currently have any transportation in stock."
						+ "<br>Please check back again later."

						+ "</td>" + "</tr>" + "</table>";
				html += "</body></html>";
				transportationLabel.setText ( html );
				transportationLabel.setFont ( font2 );
				transportationLabel
						.setHorizontalAlignment ( SwingConstants.CENTER );
				transportationScroll.setViewportView ( transportationLabel );

			}
			starshipsTable = starships.createJTable ();
			starshipsTableModel = starships.getTableModel ();
			starshipsTableModel
					.addTableModelListener ( new TableModelListener ()
					{

						@Override
						public void tableChanged(TableModelEvent e)
						{

							updateStarshipsTable ();

						}

					} );
			starshipsScroll = new JScrollPane ();

			if(starships.getDisplayedList ().length > 0)
			{
				starshipsScroll.setViewportView ( starshipsTable );
			}
			else
			{

				JLabel starshipsLabel = new JLabel ();
				String html = null;
				html = "<html>";
				html += "<body>";
				html += "<table width = \"" + setting.getSettingString ( 19 )
						+ "\" " + "height = \""
						+ setting.getSettingString ( 19 ) + "\" "
						+ "style=\"color: " + setting.getSettingString ( 18 )
						+ ";\">" + "<tr>";
				html += "<td  align = \"center\">"

						+ "I'm sorry, we don't currently have any starships in stock."
						+ "<br>Please check back again later."

						+ "</td>" + "</tr>" + "</table>";
				html += "</body></html>";
				starshipsLabel.setText ( html );
				starshipsLabel.setFont ( font2 );
				starshipsLabel.setHorizontalAlignment ( SwingConstants.CENTER );
				starshipsScroll.setViewportView ( starshipsLabel );

			}
			if(!shouldIRollIndividually)
			{
				if(!shopLoaded)
				{
					changeShopFlavor ();
				}
				shopNameLabel = new JLabel ( "<html><b>" + shopName );
				shopNameLabel.setFont ( font1 );
				shopFlavorLabel = new JLabel ( shopFlavor );
				shopFlavorLabel.setFont ( font2 );
				shopFlavorPanel = new JPanel ();
				shopFlavorScroll = new JScrollPane ();
				shopFlavorLabel
						.setHorizontalAlignment ( SwingConstants.CENTER );
				shopFlavorLabel.setVerticalAlignment ( SwingConstants.CENTER );
				shopNameLabel.setHorizontalAlignment ( SwingConstants.CENTER );
				shopNameLabel.setVerticalAlignment ( SwingConstants.CENTER );

				GroupLayout shopFlavorPanelLayout = new GroupLayout (
						shopFlavorPanel );
				shopFlavorPanel.setLayout ( shopFlavorPanelLayout );
				shopFlavorPanelLayout.setHorizontalGroup ( shopFlavorPanelLayout
						.createParallelGroup ( GroupLayout.Alignment.CENTER )
						.addComponent ( shopNameLabel ).addComponent (
								shopFlavorLabel , GroupLayout.DEFAULT_SIZE ,
								GraphicsEnvironment
										.getLocalGraphicsEnvironment ()
										.getDefaultScreenDevice ()
										.getDisplayMode ().getWidth () / 3 ,
								GraphicsEnvironment
										.getLocalGraphicsEnvironment ()
										.getDefaultScreenDevice ()
										.getDisplayMode ().getWidth () / 3 ) );
				shopFlavorPanelLayout.setVerticalGroup ( shopFlavorPanelLayout
						.createParallelGroup ( GroupLayout.Alignment.CENTER )
						.addGroup ( GroupLayout.Alignment.CENTER ,
								shopFlavorPanelLayout.createSequentialGroup ()
										.addGap ( 0 , 0 , Short.MAX_VALUE )
										.addComponent ( shopNameLabel )
										.addComponent ( shopFlavorLabel )
										.addGap ( 0 , 0 , Short.MAX_VALUE ) ) );

				shopFlavorScroll.setViewportView ( shopFlavorPanel );

				tabbedPane.addTab ( "Flavor" , shopFlavorScroll );
			}
			if(weapons.getDisplayedList ().length > 0)
				tabbedPane.addTab ( "Weapons" , weaponsScroll );
			if(armor.getDisplayedList ().length > 0)
				tabbedPane.addTab ( "Armor" , armorScroll );
			if(gear.getDisplayedList ().length > 0)
				tabbedPane.addTab ( "Gear" , gearScroll );
			if(attachments.getDisplayedList ().length > 0)
				tabbedPane.addTab ( "Attachments" , attachmentsScroll );
			if(transportation.getDisplayedList ().length > 0)
				tabbedPane.addTab ( "Transportation" , transportationScroll );
			if(starships.getDisplayedList ().length > 0)
				tabbedPane.addTab ( "Starships" , starshipsScroll );
			tabbedPane.setFont ( armorTable.getFont () );

			tablePanelLayout = new GroupLayout ( tablePanel );
			tablePanel.setLayout ( tablePanelLayout );

			tablePanelLayout.setHorizontalGroup ( tablePanelLayout
					.createParallelGroup ().addComponent ( tabbedPane )
					.addGroup ( GroupLayout.Alignment.TRAILING ,
							tablePanelLayout.createSequentialGroup ()
									.addGap ( 0 , 0 , Short.MAX_VALUE )
									.addComponent ( fullListButton ,
											GroupLayout.PREFERRED_SIZE ,
											GroupLayout.PREFERRED_SIZE ,
											GroupLayout.PREFERRED_SIZE )
									.addPreferredGap (
											LayoutStyle.ComponentPlacement.RELATED )
									.addComponent ( searchBar ,
											GroupLayout.DEFAULT_SIZE , 400 ,
											400 )
									.addPreferredGap (
											LayoutStyle.ComponentPlacement.RELATED )
									.addComponent ( searchButton ,
											GroupLayout.PREFERRED_SIZE ,
											GroupLayout.PREFERRED_SIZE ,
											GroupLayout.PREFERRED_SIZE ) ) );
			tablePanelLayout.setVerticalGroup ( tablePanelLayout
					.createSequentialGroup ().addComponent ( tabbedPane )
					.addPreferredGap (
							LayoutStyle.ComponentPlacement.UNRELATED )
					.addGroup ( tablePanelLayout.createParallelGroup ()
							.addComponent ( fullListButton )
							.addComponent ( searchBar )
							.addComponent ( searchButton ) ) );
			tablePanelLayout.linkSize ( SwingConstants.VERTICAL , searchButton ,
					searchBar );

			scrollPane.remove ( editor );
			scrollPane.setViewportView ( tablePanel );

			layout = new GroupLayout ( frame.getContentPane () );
			frame.getContentPane ().setLayout ( layout );

			layout.setHorizontalGroup (
					layout.createParallelGroup ().addComponent ( scrollPane ) );
			layout.setVerticalGroup ( layout.createSequentialGroup ()
					.addComponent ( scrollPane ) );

			frame.pack ();
			frame.setVisible ( true );
			frame.setExtendedState ( JFrame.MAXIMIZED_BOTH );
			


			
			

		}
		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}

	}

	protected void updateStarshipsTable()
	{

		try

		{
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );

			starshipsTable.setModel (
					new DefaultTableModel ( starships.getDisplayedList () ,
							starships.getSourcedListHeaders ()

					)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = -6350606163682249440L;

						@Override
						public boolean isCellEditable(int row, int col)
						{

							return false;

						}
					} );
		}
		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}

	}

	protected void updateTransportationTable()
	{

		try

		{
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );

			transportationTable.setModel (
					new DefaultTableModel ( transportation.getDisplayedList () ,
							transportation.getSourcedListHeaders ()

					)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = -6350606163682249440L;

						@Override
						public boolean isCellEditable(int row, int col)
						{

							return false;

						}
					} );
		}
		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}

	}

	protected void updateWeaponsTable()
	{

		try

		{
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );

			weaponsTable.setModel (
					new DefaultTableModel ( weapons.getDisplayedList () ,
							weapons.getSourcedListHeaders ()

					)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = -6350606163682249440L;

						@Override
						public boolean isCellEditable(int row, int col)
						{

							return false;

						}
					} );
		}
		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}
	}

	protected void updateAttachmentsTable()
	{

		try

		{
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );
			attachmentsTable.setModel (
					new DefaultTableModel ( attachments.getDisplayedList () ,
							attachments.getSourcedListHeaders ()

					)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = -6350606163682249440L;

						@Override
						public boolean isCellEditable(int row, int col)
						{

							return false;

						}
					} );
		}
		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}
	}

	protected void updateArmorTable()
	{

		try

		{
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );
			armorTable.setModel ( new DefaultTableModel (
					armor.getDisplayedList () , armor.getSourcedListHeaders ()

			)
			{

				/**
				 * 
				 */
				private static final long serialVersionUID = -6350606163682249440L;

				@Override
				public boolean isCellEditable(int row, int col)
				{

					return false;

				}
			} );
		}
		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}
	}

	protected void updateGearTable()
	{

		try

		{
			frame.setCursor (
					Cursor.getPredefinedCursor ( Cursor.WAIT_CURSOR ) );
			gearTable.setModel ( new DefaultTableModel (
					gear.getDisplayedList () , gear.getSourcedListHeaders ()

			)
			{

				/**
				 * 
				 */
				private static final long serialVersionUID = 2043797785070446062L;

				@Override
				public boolean isCellEditable(int row, int col)
				{

					return false;

				}
			} );
		}
		finally
		{
			EventQueue.invokeLater ( new Runnable ()
			{

				@Override
				public void run()
				{

					frame.setCursor ( Cursor.getDefaultCursor () );
				}
			} );

		}
	}

	protected void fullList()
	{

		String listName = tabbedPane
				.getTitleAt ( tabbedPane.getSelectedIndex () );
		if(listName.equals ( "Weapons" ))
		{

			weaponsTable.setModel (
					new DefaultTableModel ( weapons.getDisplayedList () ,
							weapons.getSourcedListHeaders ()

					)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = 6941909982531564839L;

						@Override
						public boolean isCellEditable(int row, int col)
						{

							return false;

						}
					} );
			weapons.resizeColumnWidth ( weaponsTable );
			weapons.resetSearch ();
		}
		else if(listName.equals ( "Armor" ))
		{

			armorTable.setModel ( new DefaultTableModel (
					armor.getDisplayedList () , armor.getSourcedListHeaders ()

			)
			{

				/**
				 * 
				 */
				private static final long serialVersionUID = -3801358503129134569L;

				@Override
				public boolean isCellEditable(int row, int col)
				{

					return false;

				}
			} );
			armor.resizeColumnWidth ( armorTable );
			armor.resetSearch ();
		}
		else if(listName.equals ( "Gear" ))
		{

			gearTable.setModel ( new DefaultTableModel (
					gear.getDisplayedList () , gear.getSourcedListHeaders ()

			)
			{

				/**
				 * 
				 */
				private static final long serialVersionUID = 3406530670207294923L;

				@Override
				public boolean isCellEditable(int row, int col)
				{

					return false;

				}
			} );
			gear.resizeColumnWidth ( gearTable );
			gear.resetSearch ();
		}
		else if(listName.equals ( "Attachments" ))
		{

			attachmentsTable.setModel (
					new DefaultTableModel ( attachments.getDisplayedList () ,
							attachments.getSourcedListHeaders ()

					)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = 3406530670207294923L;

						@Override
						public boolean isCellEditable(int row, int col)
						{

							return false;

						}
					} );
			attachments.resizeColumnWidth ( attachmentsTable );
			attachments.resetSearch ();
		}
		else if(listName.equals ( "Transportation" ))
		{

			transportationTable.setModel (
					new DefaultTableModel ( transportation.getDisplayedList () ,
							transportation.getSourcedListHeaders ()

					)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = 3406530670207294923L;

						@Override
						public boolean isCellEditable(int row, int col)
						{

							return false;

						}
					} );
			transportation.resizeColumnWidth ( transportationTable );
			transportation.resetSearch ();
		}
		else
		{

			starshipsTable.setModel (
					new DefaultTableModel ( starships.getDisplayedList () ,
							starships.getSourcedListHeaders ()

					)
					{

						/**
						 * 
						 */
						private static final long serialVersionUID = 3406530670207294923L;

						@Override
						public boolean isCellEditable(int row, int col)
						{

							return false;

						}
					} );
			starships.resizeColumnWidth ( starshipsTable );
			starships.resetSearch ();
		}

	}

	protected void searchButtonActionPerformed(ActionEvent evt)
	{

		String listName = tabbedPane
				.getTitleAt ( tabbedPane.getSelectedIndex () );
		searchBar.getText ();
		if(listName.equals ( "Weapons" ))
		{

			weapons.searchForItems ( searchBar.getText () );

		}
		else if(listName.equals ( "Armor" ))
		{

			armor.searchForItems ( searchBar.getText () );

		}
		else if(listName.equals ( "Gear" ))
		{

			gear.searchForItems ( searchBar.getText () );

		}
		else if(listName.equals ( "Attachments" ))
		{

			attachments.searchForItems ( searchBar.getText () );

		}
		else if(listName.equals ( "Transportation" ))
		{

			transportation.searchForItems ( searchBar.getText () );

		}
		else
		{

			starships.searchForItems ( searchBar.getText () );

		}

	}

	public void saveShop(File file)
	{

		PrintStream ps = null;
		weaponsList = weapons.getSourcedList ();
		armorList = armor.getSourcedList ();
		gearList = gear.getSourcedList ();
		transportationList = transportation
				.getSourcedList ();
		starshipsList = starships.getSourcedList ();
		attachmentsList = attachments.getSourcedList ();
		

		try
		{
			ps = new PrintStream (
					file.toString ().replace ( ".shop" , "" ) + ".shop" );
			ps.println ( shopName );
			ps.println ( shopFlavor );
			ps.println ( "Weapons" + weapons.getSourcedListCount () + ","
					+ weapons.getSourcedColumnCount () + ","
					+ weapons.getTypeCount () );
			for(int i = 0; i < weaponsList.length; i++)
			{
				ps.print ( weaponsList[i][0] );
				for(int j = 1; j < weaponsList[0].length; j++)
				{

					ps.print ( "," + weaponsList[i][j] );

				}
				ps.print ( System.getProperty ( "line.separator" ) );

			}
			ps.println ( "Armor" + armor.getSourcedListCount () + ","
					+ armor.getSourcedColumnCount () + ","
					+ armor.getTypeCount () );
			for(int i = 0; i < armorList.length; i++)
			{
				ps.print ( armorList[i][0] );
				for(int j = 1; j < armorList[0].length; j++)
				{

					ps.print ( "," + armorList[i][j] );

				}
				ps.print ( System.getProperty ( "line.separator" ) );

			}
			ps.println ( "Gear" + gear.getSourcedListCount () + ","
					+ gear.getSourcedColumnCount () + ","
					+ gear.getTypeCount () );
			for(int i = 0; i < gearList.length; i++)
			{
				ps.print ( gearList[i][0] );
				for(int j = 1; j < gearList[0].length; j++)
				{

					ps.print ( "," + gearList[i][j] );

				}
				ps.print ( System.getProperty ( "line.separator" ) );

			}
			ps.println ( "Attachments" + attachments.getSourcedListCount ()
					+ "," + attachments.getSourcedColumnCount () + ","
					+ attachments.getTypeCount () );
			for(int i = 0; i < attachmentsList.length; i++)
			{
				ps.print ( attachmentsList[i][0] );
				for(int j = 1; j < attachmentsList[0].length; j++)
				{

					ps.print ( "," + attachmentsList[i][j] );

				}
				ps.print ( System.getProperty ( "line.separator" ) );

			}
			ps.println (
					"Transportation" + transportation.getSourcedListCount ()
							+ "," + transportation.getSourcedColumnCount ()
							+ "," + transportation.getTypeCount () );
			for(int i = 0; i < transportationList.length; i++)
			{
				ps.print ( transportationList[i][0] );
				for(int j = 1; j < transportationList[0].length; j++)
				{

					ps.print ( "," + transportationList[i][j] );

				}
				ps.print ( System.getProperty ( "line.separator" ) );

			}
			ps.println ( "Starships" + starships.getSourcedListCount () + ","
					+ starships.getSourcedColumnCount () + ","
					+ starships.getTypeCount () );
			for(int i = 0; i < starshipsList.length; i++)
			{
				ps.print ( starshipsList[i][0] );
				for(int j = 1; j < starshipsList[0].length; j++)
				{

					ps.print ( "," + starshipsList[i][j] );

				}
				ps.print ( System.getProperty ( "line.separator" ) );

			}

			ps.close ();
		}
		catch(IOException e)
		{
			{
				JOptionPane.showMessageDialog ( new javax.swing.JFrame () ,
						"<html><span style='font-size: " + fontSize + "'>"
								+ "Unable to create shop. If you are using a Mac, you must create the file "
								+ "<br>(copy the \"Watto's Closet.shop\" and rename it to what you want). "
								+ "<br>Unfortunately for me, and fortunately for you, Mac won't let me create "
								+ "<br>files on your system. " + "<br>"
								+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text." );
			}
			e.printStackTrace ();

		}
	}

	public void loadShop(File file)
	{
		weapons = new Reader ( "Weapons" );
		weapons.loadArray ();
		weapons.sortType ();
		armor = new Reader ( "Armor" );
		armor.loadArray ();
		armor.sortType ();
		gear = new Reader ( "Gear" );
		gear.loadArray ();
		gear.sortType ();
		attachments = new Reader ( "Attachments" );
		attachments.loadArray ();
		attachments.sortType ();
		transportation = new Reader (
				"Transportation" );
		transportation.loadArray ();
		transportation.sortType ();
		starships = new Reader ( "Starships" );
		starships.loadArray ();
		starships.sortType ();

		
		Scanner lineReader = null;

		Scanner reader = null;
		try
		{
			reader = new Scanner ( file );

			reader.nextLine ();
			shopName = file.getName ().replace ( ".shop" , "" );
			shopFlavor = reader.nextLine ();

			titleLine = reader.nextLine ();
			titleLine = titleLine.replaceAll ( "[^0-9,]" , "" );
			lineReader = new Scanner ( titleLine );
			lineReader.useDelimiter ( "," );
			weaponsList = new String[Integer
					.parseInt ( lineReader.next () )][Integer
							.parseInt ( lineReader.next () )];

			weapons.setTypeCount ( Integer.parseInt ( lineReader.next () ) );

			for(int i = 0; i < weaponsList.length; i++)
			{

				lineReader = new Scanner ( reader.nextLine () );
				lineReader.useDelimiter ( "," );
				for(int j = 0; j < weaponsList[0].length; j++)
				{

					weaponsList[i][j] = lineReader.next ();

				}

			}

			titleLine = reader.nextLine ();
			titleLine = titleLine.replaceAll ( "[^0-9,]" , "" );
			lineReader = new Scanner ( titleLine );
			lineReader.useDelimiter ( "," );
			armorList = new String[Integer
					.parseInt ( lineReader.next () )][Integer
							.parseInt ( lineReader.next () )];

			armor.setTypeCount ( Integer.parseInt ( lineReader.next () ) );

			for(int i = 0; i < armorList.length; i++)
			{

				lineReader = new Scanner ( reader.nextLine () );
				lineReader.useDelimiter ( "," );
				for(int j = 0; j < armorList[0].length; j++)
				{

					armorList[i][j] = lineReader.next ();

				}

			}

			titleLine = reader.nextLine ();
			titleLine = titleLine.replaceAll ( "[^0-9,]" , "" );
			lineReader = new Scanner ( titleLine );
			lineReader.useDelimiter ( "," );
			gearList = new String[Integer
					.parseInt ( lineReader.next () )][Integer
							.parseInt ( lineReader.next () )];

			gear.setTypeCount ( Integer.parseInt ( lineReader.next () ) );

			for(int i = 0; i < gearList.length; i++)
			{

				lineReader = new Scanner ( reader.nextLine () );
				lineReader.useDelimiter ( "," );
				for(int j = 0; j < gearList[0].length; j++)
				{

					gearList[i][j] = lineReader.next ();

				}

			}

			titleLine = reader.nextLine ();
			titleLine = titleLine.replaceAll ( "[^0-9,]" , "" );
			lineReader = new Scanner ( titleLine );
			lineReader.useDelimiter ( "," );
			attachmentsList = new String[Integer
					.parseInt ( lineReader.next () )][Integer
							.parseInt ( lineReader.next () )];

			attachments
					.setTypeCount ( Integer.parseInt ( lineReader.next () ) );

			for(int i = 0; i < attachmentsList.length; i++)
			{

				lineReader = new Scanner ( reader.nextLine () );
				lineReader.useDelimiter ( "," );
				for(int j = 0; j < attachmentsList[0].length; j++)
				{

					attachmentsList[i][j] = lineReader.next ();
				}

			}

			titleLine = reader.nextLine ();
			titleLine = titleLine.replaceAll ( "[^0-9,]" , "" );
			lineReader = new Scanner ( titleLine );
			lineReader.useDelimiter ( "," );
			transportationList = new String[Integer
					.parseInt ( lineReader.next () )][Integer
							.parseInt ( lineReader.next () )];

			transportation
					.setTypeCount ( Integer.parseInt ( lineReader.next () ) );

			for(int i = 0; i < transportationList.length; i++)
			{

				lineReader = new Scanner ( reader.nextLine () );
				lineReader.useDelimiter ( "," );
				for(int j = 0; j < transportationList[0].length; j++)
				{

					transportationList[i][j] = lineReader.next ();
				}

			}

			titleLine = reader.nextLine ();
			titleLine = titleLine.replaceAll ( "[^0-9,]" , "" );
			lineReader = new Scanner ( titleLine );
			lineReader.useDelimiter ( "," );
			starshipsList = new String[Integer
					.parseInt ( lineReader.next () )][Integer
							.parseInt ( lineReader.next () )];

			starships.setTypeCount ( Integer.parseInt ( lineReader.next () ) );

			for(int i = 0; i < starshipsList.length; i++)
			{

				lineReader = new Scanner ( reader.nextLine () );
				lineReader.useDelimiter ( "," );
				for(int j = 0; j < starshipsList[0].length; j++)
				{

					starshipsList[i][j] = lineReader.next ();
				}

			}

			reader.close ();
			weapons.setSourcedList ( weaponsList );
			armor.setSourcedList ( armorList );
			gear.setSourcedList ( gearList );
			attachments.setSourcedList ( attachmentsList );
			transportation.setSourcedList ( transportationList );
			starships.setSourcedList ( starshipsList );

			weapons.createDisplayedList ();
			armor.createDisplayedList ();
			gear.createDisplayedList ();
			attachments.createDisplayedList ();
			transportation.createDisplayedList ();
			starships.createDisplayedList ();

			shopLoaded = true;

			runDUECredits ();

		}
		catch(FileNotFoundException e)
		{
			{
				JOptionPane.showMessageDialog ( new javax.swing.JFrame () ,
						"<html><span style='font-size: " + fontSize + "'>"
								+ "Error loading from file \"" + file.getName ()
								+ ".\"" + "<br>Check to make sure it exists."
								+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text." );
			}
			e.printStackTrace ();

		}

	}

	public void shrinkShop()
	{

		maxShopSize = choice.getShopSizeMax ();
		minShopSize = choice.getShopSizeMin ();
		int potentialShopSize = weapons.getFullItemList ().length
				+ armor.getFullItemList ().length
				+ gear.getFullItemList ().length
				+ attachments.getFullItemList ().length;
		if(maxShopSize < potentialShopSize)
		{

			Random rn = new Random ();
			int shopSize = rn.nextInt ( maxShopSize - minShopSize + 1 )
					+ minShopSize;
			int[] newShop = new int[shopSize];
			int nextItem = 0;
			boolean alreadyInShop;

			int newShopCount = 0;

			String[][] oldWeaponsList = weapons.getFullItemList ();
			String[][] newWeaponsList;
			int newWeaponsCount = 0;

			String[][] oldArmorList = armor.getFullItemList ();
			String[][] newArmorList;
			int newArmorCount = 0;

			String[][] oldGearList = gear.getFullItemList ();
			String[][] newGearList;
			int newGearCount = 0;

			String[][] oldAttachmentsList = attachments.getFullItemList ();
			String[][] newAttachmentsList;
			int newAttachmentsCount = 0;

			String[][] potentialShop = new String[potentialShopSize][];
			for(int i = 0; i < oldWeaponsList.length; i++)
			{

				potentialShop[i] = oldWeaponsList[i];

			}

			for(int i = 0; i < oldArmorList.length; i++)
			{

				potentialShop[i + oldWeaponsList.length] = oldArmorList[i];

			}

			for(int i = 0; i < oldGearList.length; i++)
			{

				potentialShop[i + oldWeaponsList.length
						+ oldArmorList.length] = oldGearList[i];

			}

			for(int i = 0; i < oldAttachmentsList.length; i++)
			{

				potentialShop[i + oldWeaponsList.length + oldArmorList.length
						+ oldGearList.length] = oldAttachmentsList[i];

			}
			for(int i = 0; i < shopSize; i++)
			{
				alreadyInShop = true;
				while(alreadyInShop)
				{

					alreadyInShop = false;
					nextItem = rn.nextInt ( potentialShop.length );

					for(int j = 0; j < newShopCount; j++)
					{

						if(newShop[j] == nextItem)
						{

							alreadyInShop = true;
							break;

						}

					}
				}

				newShop[i] = nextItem;
				if(potentialShop[newShop[i]][0].equals ( "Weapons" ))
				{
					newWeaponsCount++;
				}
				else if(potentialShop[newShop[i]][0].equals ( "Armor" ))
				{
					newArmorCount++;
				}
				else if(potentialShop[newShop[i]][0].equals ( "Gear" ))
				{
					newGearCount++;
				}
				else
				{
					newAttachmentsCount++;
				}
				newShopCount++;
			}

			newWeaponsList = new String[newWeaponsCount][];
			newWeaponsCount = 0;
			newArmorList = new String[newArmorCount][];
			newArmorCount = 0;
			newGearList = new String[newGearCount][];
			newGearCount = 0;
			newAttachmentsList = new String[newAttachmentsCount][];
			newAttachmentsCount = 0;
			for(int i = 0; i < newShopCount; i++)
			{

				if(potentialShop[newShop[i]][0].equals ( "Weapons" ))
				{
					newWeaponsList[newWeaponsCount] = potentialShop[newShop[i]];
					newWeaponsCount++;
				}
				else if(potentialShop[newShop[i]][0].equals ( "Armor" ))
				{
					newArmorList[newArmorCount] = potentialShop[newShop[i]];
					newArmorCount++;
				}
				else if(potentialShop[newShop[i]][0].equals ( "Gear" ))
				{
					newGearList[newGearCount] = potentialShop[newShop[i]];
					newGearCount++;
				}
				else
				{
					newAttachmentsList[newAttachmentsCount] = potentialShop[newShop[i]];
					newAttachmentsCount++;
				}

			}

			weapons.setFullItemList ( newWeaponsList );
			weapons.sortBy ( "Price" );
			weapons.sortType ();
			armor.setFullItemList ( newArmorList );
			armor.sortBy ( "Price" );
			armor.sortType ();
			gear.setFullItemList ( newGearList );
			gear.sortBy ( "Price" );
			gear.sortType ();
			attachments.setFullItemList ( newAttachmentsList );
			attachments.sortBy ( "Price" );
			attachments.sortType ();

		}
	}

	public void changeShopFlavor()
	{

		changeShopFlavorDialog = new JDialog ();
		flavorLabel = new JLabel (
				"<html>&nbsp&nbsp&nbsp&nbsp Change the flavor text your players will see when they boot up your shop." );
		flavorLabel.setFont ( font2 );
		flavorText = new JTextArea ();
		flavorText.setFont ( font2 );
		flavorText.setTabSize ( 4 );
		flavorText.setText ( shopFlavor.replace ( "<html>" , "" )
				.replace ( "</html>" , "" ).replace ( "<br>" , "\n" )
				.replace ( "&nbsp&nbsp&nbsp&nbsp&nbsp " , "\t" ) );
		flavorText.setLineWrap ( true );
		flavorText.setWrapStyleWord ( true );
		flavorScroll = new JScrollPane ();
		flavorPanel = new JPanel ();
		flavorSaveButton = new JButton ( "OKAY" );
		flavorSaveButton.setFont ( font2 );
		flavorSaveButton.addActionListener ( new ActionListener ()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				shopFlavor = ( ( "<html>" + flavorText.getText ()
						.replaceAll ( "\\t" , "&nbsp&nbsp&nbsp&nbsp&nbsp " )
						.replaceAll ( "\\n" , "<br>" )
						.replaceAll ( "\"" , "\\\"" ) + "</html>" ) );

				changeShopFlavorDialog.dispose ();

			}
		} );

		flavorScroll.setViewportView ( flavorText );

		GroupLayout flavorPanelLayout = new GroupLayout ( flavorPanel );
		flavorPanel.setLayout ( flavorPanelLayout );

		flavorPanelLayout.setHorizontalGroup ( flavorPanelLayout
				.createParallelGroup ( GroupLayout.Alignment.CENTER )
				.addComponent ( flavorLabel , GroupLayout.DEFAULT_SIZE ,
						GraphicsEnvironment.getLocalGraphicsEnvironment ()
								.getDefaultScreenDevice ().getDisplayMode ()
								.getWidth () / 3 ,
						GraphicsEnvironment.getLocalGraphicsEnvironment ()
								.getDefaultScreenDevice ().getDisplayMode ()
								.getWidth () / 3 )
				.addComponent ( flavorScroll , GroupLayout.DEFAULT_SIZE ,
						GraphicsEnvironment.getLocalGraphicsEnvironment ()
								.getDefaultScreenDevice ().getDisplayMode ()
								.getWidth () / 3 ,
						GraphicsEnvironment.getLocalGraphicsEnvironment ()
								.getDefaultScreenDevice ().getDisplayMode ()
								.getWidth () / 3 )
				.addComponent ( flavorSaveButton , GroupLayout.DEFAULT_SIZE ,
						GroupLayout.PREFERRED_SIZE ,
						GroupLayout.PREFERRED_SIZE ) );

		flavorPanelLayout.setVerticalGroup ( flavorPanelLayout
				.createSequentialGroup ().addComponent ( flavorLabel )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )
				.addComponent ( flavorScroll , GroupLayout.DEFAULT_SIZE ,
						flavorScroll.getPreferredSize ().height * 4 ,
						flavorScroll.getPreferredSize ().height * 4 )
				.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED )
				.addComponent ( flavorSaveButton ) );

		flavorPanel.setBorder (
				BorderFactory.createEmptyBorder ( 10 , 10 , 10 , 10 ) );

		changeShopFlavorDialog.add ( flavorPanel );
		changeShopFlavorDialog.setName ( "Edit Shop Flavor" );
		changeShopFlavorDialog.pack ();
		changeShopFlavorDialog.setModal ( true );
		changeShopFlavorDialog.setLocationRelativeTo ( null );
		changeShopFlavorDialog.setVisible ( true );
		changeShopFlavorDialog.getRootPane ()
				.setDefaultButton ( flavorSaveButton );

	}
}
