
package settings;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import helper.Settings;

public class AdvancedSettings
{

	private JButton cancelButton;
	private JButton defaultButton;
	private int columnCount;
	private int componentCount;
	private int count;
	private int labelPreferredSize;
	private int inputPreferredSize;
	private JFrame jFrame;
	private JPanel panel;
	private JScrollPane jScrollPane;
	private ParallelGroup parallelHoriz;
	private ParallelGroup parallelVert;
	private JButton saveButton;
	private SequentialGroup sequenceHoriz;
	private SequentialGroup sequenceVert;
	private JLabel description;
	private JLabel titleLabel;
	private JLabel triumphWorthLabel;
	private Settings setting = new Settings();

	private Component[] component;
	private int fontSize;
	private String fontName;
	private Font font1;
	private Font font2;
	private JLabel triumphDiscountLabel;
	private JCheckBox autoDiscountCheckBox;
	private JLabel advantageDiscountLabel;
	private JLabel advantageWorthLabel;
	private JTextField triumphDiscountText;
	private JTextField advantageWorthText;
	private JTextField advantageDiscountText;
	private JTextField triumphWorthText;
	private JCheckBox showAdvantagesCheckBox;
	private JLabel skillTypeLabel;
	private JComboBox<String> skillTypeCombo;
	private JCheckBox useVanillaWorldModifiersCheckBox;
	private JCheckBox useVanillaPricingCheckBox;
	private int titlePreferredWidth;
	private JCheckBox showSuccessesCheckBox;
	private JCheckBox showRestrictedMarkerCheckBox;

	public void run()
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

		java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addPropertyChangeListener("permanentFocusOwner",
						new java.beans.PropertyChangeListener()
						{

							public void propertyChange(
									final java.beans.PropertyChangeEvent e)
							{

								if (e.getNewValue() instanceof JTextField)
								{
									javax.swing.SwingUtilities
											.invokeLater(new Runnable()
											{

												public void run()
												{

													JTextField textField = (JTextField) e
															.getNewValue();
													textField.selectAll();
												}
											});

								}
							}
						});

		fontSize = setting.getSettingNumber(17);
		fontName = setting.getSettingString(26);

		font1 = new Font(fontName, 0, (int) Math.round(fontSize * 1.5));
		font2 = new Font(fontName, 0, fontSize);

		UIManager.put("ToolTip.font", new FontUIResource(font2));

		panel = new JPanel();

		saveButton = new JButton();
		saveButton.setBackground(new java.awt.Color(0, 255, 255));
		saveButton.setText("SAVE");
		saveButton.setFont(font2);
		saveButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				saveButtonActionPerformed(evt);
			}
		});

		cancelButton = new JButton();
		cancelButton.setText("CANCEL");
		cancelButton.setFont(font2);
		cancelButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				cancelButtonActionPerformed(evt);
			}
		});

		defaultButton = new JButton();
		defaultButton.setText("SET TO VANILLA");
		defaultButton.setFont(font2);
		defaultButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				defaultButtonActionPerformed(evt);
			}
		});

		titleLabel = new JLabel("Advanced Settings");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(font1);
		description = new JLabel("<html><p>"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp These numbers affect the value that advantages/threats and triumphs contribute to the dice roll. The vanilla rules"
				+ " state that advantages do not count as successes, but I find that this can cause situations where all your positive rolls "
				+ "are successes and all your negative rolls are threats, which makes it so that even rare items show up more often than"
				+ " I personally think is appropriate (albeit with lots of threats attached."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp Entered values must be a nonnegative integer or decimal (i.e. 1.5 and 0 are valid entries)."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp The second set of entries are a shorthand I'm adding in where you can set advantages/threats to cause an automatic "
				+ "discount/markup (so you can be lazy and not worry too much about what effects they cause)."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp Lastly, the \"Show Advantages\" checkbox determines whether or not the leftover advantages will be displayed on the shop menu."
				+ "</p></html>");
		description.setFont(font2);
		
		
		triumphDiscountLabel = new JLabel("Triumph Discount(%)");
		triumphDiscountLabel.setFont(font2);
		triumphDiscountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		triumphDiscountLabel.setToolTipText(
				"Determines the percentage discount added by each triumph");

		autoDiscountCheckBox = new JCheckBox("Automatic Discount/Markup");
		autoDiscountCheckBox.setFont(font2);
		autoDiscountCheckBox.setSelected(
				Boolean.parseBoolean(setting.getSettingString(22)));
		autoDiscountCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		autoDiscountCheckBox.setToolTipText(
				"Determines whether an automatic discount is applied based on leftover advantages/threats/triumphs.");

		showSuccessesCheckBox = new JCheckBox("Show Successes");
		showSuccessesCheckBox.setFont(font2);
		showSuccessesCheckBox.setSelected(
				Boolean.parseBoolean(setting.getSettingString(30)));
		showSuccessesCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		showSuccessesCheckBox.setToolTipText(
				"Determines whether leftover successes are displayed on the shop screen.");
		showSuccessesCheckBox.setSelected(Boolean.parseBoolean(setting.getSettingString(34)));

		showAdvantagesCheckBox = new JCheckBox("Show Advantages");
		showAdvantagesCheckBox.setFont(font2);
		showAdvantagesCheckBox.setSelected(
				Boolean.parseBoolean(setting.getSettingString(30)));
		showAdvantagesCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		showAdvantagesCheckBox.setToolTipText(
				"Determines whether leftover advantages/threats/triumphs are displayed on the shop screen.");

		advantageDiscountLabel = new JLabel("Advantage Discount(%)");
		advantageDiscountLabel.setFont(font2);
		advantageDiscountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		advantageDiscountLabel.setToolTipText(
				"Determines the percentage discount/markup added by each advantage/threat.");

		triumphWorthLabel = new JLabel("Triumph Value");
		triumphWorthLabel.setFont(font2);
		triumphWorthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		triumphWorthLabel.setToolTipText(
				"<html>Determines how many successes each triumph is worth."
						+ "<br>Can be a decimal value.</html>");

		advantageWorthLabel = new JLabel("Advantage Value");
		advantageWorthLabel.setFont(font2);
		advantageWorthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		advantageWorthLabel.setToolTipText(
				"<html>Determines how many successes/failures each advantage/threat is worth."
						+ "<br>Can be a decimal value.</html>");

		triumphDiscountText = new JTextField("" + setting.getSettingNumber(21));
		triumphDiscountText.setFont(font2);

		advantageWorthText = new JTextField("" + setting.getSettingNumber(25));
		advantageWorthText.setFont(font2);

		advantageDiscountText = new JTextField(
				"" + setting.getSettingNumber(23));
		advantageDiscountText.setFont(font2);
		triumphWorthText = new JTextField("" + setting.getSettingNumber(24));
		triumphWorthText.setFont(font2);

		skillTypeLabel = new JLabel("Skill Used For Rolls");
		skillTypeLabel.setFont(font2);
		skillTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		skillTypeLabel.setToolTipText(
				"<html>Determines which skill is used to roll for items.</html>");

		useVanillaWorldModifiersCheckBox = new JCheckBox("Use World Modifiers");
		useVanillaWorldModifiersCheckBox.setFont(font2);
		useVanillaWorldModifiersCheckBox.setSelected(
				Boolean.parseBoolean(setting.getSettingString(22)));
		useVanillaWorldModifiersCheckBox
				.setHorizontalAlignment(SwingConstants.CENTER);
		useVanillaWorldModifiersCheckBox.setToolTipText(
				"<html>Determines whether or not to use the vanilla rules about "
						+ "<br>rarity modifiers due to world location."
						+ "<br>See EotE-CRB pg150.</html>");
		useVanillaWorldModifiersCheckBox.setSelected(Boolean.parseBoolean(setting.getSettingString(35)));

		useVanillaPricingCheckBox = new JCheckBox(
				"Use Rarity Price Multipliers");
		useVanillaPricingCheckBox.setFont(font2);
		useVanillaPricingCheckBox.setSelected(
				Boolean.parseBoolean(setting.getSettingString(22)));
		useVanillaPricingCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		useVanillaPricingCheckBox.setToolTipText(
				"<html>Determines whether or not to use the vanilla rules about "
						+ "<br>pricing multipliers due to rarity increases"
						+ "<br>(x1 for 0-1 increased rarity"
						+ "<br> x2 for 2 increaseed rarity"
						+ "<br> x3 for 3 increased rarity"
						+ "<br> x4 for 4 increased rarity)" + "</html>");
		useVanillaPricingCheckBox.setSelected(Boolean.parseBoolean(setting.getSettingString(36)));

		String[] skills = new String[]
		{ "(Ag) Coordination", "(Ag) Gunnery", "(Ag) Piloting - Planetary",
				"(Ag) Piloting - Space", "(Ag) Ranged - Light",
				"(Ag) Ranged - Heavy", "(Ag) Stealth", "(Br) Athletics",
				"(Br) Brawl", "(Br) Melee", "(Br) Resilience", "(Cun) Deceit",
				"(Cun) Perception", "(Cun) Skullduggery", "(Cun) Streetwise",
				"(Cun) Survival", "(Int) Astrogation", "(Int) Computers",
				"(Int) Core Worlds", "(Int) Education", "(Int) Lore",
				"(Int) Mechanics", "(Int) Medicine", "(Int) Outer Rim",
				"(Int) Underworld", "(Int) Xenology", "(Pr) Charm", "(Pr) Cool",
				"(Pr) Leadership", "(Pr) Negotiation", "(Will) Coercion",
				"(Will) Discipline", "(Will) Vigilance" };
		
		skillTypeCombo = new JComboBox<String>(skills);

		showRestrictedMarkerCheckBox = new JCheckBox("Show Restricted Marker");
		showRestrictedMarkerCheckBox.setFont(font2);
		showRestrictedMarkerCheckBox.setSelected(
				!setting.getSettingString(20).equals(""));
		showRestrictedMarkerCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		showRestrictedMarkerCheckBox.setToolTipText(
				"Determines whether or not (R) will be displayed next to the price of an item.");
		

		int skillNumber = 0;
		for (int i = 0; i < skills.length; i++)
		{

			if (skills[i].contains(setting.getSettingString(31)))
			{

				skillNumber = i;
				break;

			}

		}

		skillTypeCombo.setSelectedIndex(skillNumber);
		skillTypeCombo.setFont(font2);

		inputPreferredSize = (int) Math
				.round(skillTypeCombo.getPreferredSize().getWidth() + .5);

		// Initial setup
		columnCount = 2;
		componentCount = 8;

		jScrollPane = new JScrollPane();
		jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		panel = new JPanel();
		panel.setForeground(Color.WHITE);
		jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create Component Lists
		component = new Component[componentCount];
		component[0] = advantageWorthLabel;
		component[1] = advantageWorthText;
		component[2] = triumphWorthLabel;
		component[3] = triumphWorthText;
		component[4] = advantageDiscountLabel;
		component[5] = advantageDiscountText;
		component[6] = triumphDiscountLabel;
		component[7] = triumphDiscountText;
		;

		labelPreferredSize = 0;
		for (int i = 0; i < componentCount; i++)
		{
			if (component[i].getPreferredSize().getWidth() > labelPreferredSize
					&& component[i] instanceof JLabel)
			{

				labelPreferredSize = (int) Math
						.round(component[i].getPreferredSize().getWidth() + .5);

			}

		}
		// Meat and potatoes
		GroupLayout panelLayout = new GroupLayout(panel);
		panel.setLayout(panelLayout);

		parallelHoriz = panelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING);

		if (((labelPreferredSize + inputPreferredSize) * columnCount)
				+ (10 * (columnCount - 1)) <= GraphicsEnvironment
						.getLocalGraphicsEnvironment().getDefaultScreenDevice()
						.getDisplayMode().getWidth())
		{

			titlePreferredWidth = ((labelPreferredSize + inputPreferredSize)
						* columnCount) + (10 * (columnCount - 1));
			
		}
		else
		{

			titlePreferredWidth = (GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDisplayMode().getWidth() - 20);
		}
		
		parallelHoriz
				.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE,
						titlePreferredWidth, titlePreferredWidth)
				.addComponent(description, GroupLayout.PREFERRED_SIZE,
						titlePreferredWidth, titlePreferredWidth);

		sequenceVert = panelLayout.createSequentialGroup();
		sequenceVert.addComponent(titleLabel).addComponent(description)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED);
		count = 0;
		while (count < component.length)
		{

			sequenceHoriz = panelLayout.createSequentialGroup();
			parallelVert = panelLayout.createParallelGroup();

			for (int i = 0; i < 2 * columnCount; i++)
			{
				if (count < component.length)
				{
					if (component[count] instanceof JLabel)
					{
						sequenceHoriz
								.addComponent(component[count],
										GroupLayout.PREFERRED_SIZE,
										labelPreferredSize, labelPreferredSize)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED);
						parallelVert.addComponent(component[count]);
						count++;
					}
					else
					{

						sequenceHoriz
								.addComponent(component[count],
										GroupLayout.PREFERRED_SIZE,
										inputPreferredSize, inputPreferredSize)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED);
						parallelVert.addComponent(component[count]);
						count++;
					}

				}

			}

			parallelHoriz.addGroup(sequenceHoriz);
			sequenceVert.addGroup(parallelVert)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);

		}

		parallelHoriz
				.addComponent(autoDiscountCheckBox,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.PREFERRED_SIZE)
				.addGroup(GroupLayout.Alignment.TRAILING,
						panelLayout.createSequentialGroup()
						.addComponent(showSuccessesCheckBox)		
						.addComponent(showAdvantagesCheckBox))
				.addGroup(GroupLayout.Alignment.TRAILING,
						panelLayout.createSequentialGroup()
								.addComponent(skillTypeLabel,
										GroupLayout.PREFERRED_SIZE,
										labelPreferredSize, labelPreferredSize)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(skillTypeCombo,
										GroupLayout.PREFERRED_SIZE,
										inputPreferredSize, inputPreferredSize))
				.addGroup(GroupLayout.Alignment.TRAILING, panelLayout
						.createSequentialGroup()
						.addComponent(useVanillaWorldModifiersCheckBox)
						.addComponent(useVanillaPricingCheckBox))
				.addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
						.addComponent(showRestrictedMarkerCheckBox))

				.addGroup(GroupLayout.Alignment.TRAILING, panelLayout
						.createSequentialGroup()
						.addComponent(defaultButton, GroupLayout.PREFERRED_SIZE,
								GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE,
								GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(saveButton, GroupLayout.PREFERRED_SIZE,
								GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addPreferredGap(
								LayoutStyle.ComponentPlacement.RELATED));

		sequenceVert
				.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(autoDiscountCheckBox))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(showSuccessesCheckBox)
								.addComponent(showAdvantagesCheckBox))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(skillTypeLabel)
						.addComponent(skillTypeCombo))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(useVanillaWorldModifiersCheckBox)
						.addComponent(useVanillaPricingCheckBox))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(showRestrictedMarkerCheckBox))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(defaultButton).addComponent(cancelButton)
						.addComponent(saveButton));

		panelLayout.setHorizontalGroup(parallelHoriz);

		panelLayout.setVerticalGroup(sequenceVert);

		panelLayout.linkSize(component[0], component[2], component[4],
				component[6], skillTypeLabel);
		panelLayout.linkSize(component[1], component[3], component[5],
				component[7], skillTypeCombo);
		panelLayout.linkSize(autoDiscountCheckBox, showAdvantagesCheckBox,  showSuccessesCheckBox, useVanillaWorldModifiersCheckBox, useVanillaPricingCheckBox);
		panelLayout.linkSize(saveButton, cancelButton, defaultButton);

		jScrollPane.setViewportView(panel);
		
		panel.setBorder(
				javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));


		
		
		if (fontSize * 29 <= GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDisplayMode().getHeight()

				&& (((labelPreferredSize + inputPreferredSize) * columnCount)
						+ (10 * (columnCount - 1)) < GraphicsEnvironment
								.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice().getDisplayMode()
								.getWidth()))
		{
			GroupLayout layout = new GroupLayout(jFrame.getContentPane());
			jFrame.getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jScrollPane, 1, GroupLayout.PREFERRED_SIZE,
							GraphicsEnvironment
							.getLocalGraphicsEnvironment().getDefaultScreenDevice()
							.getDisplayMode().getWidth()));
			layout.setVerticalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addComponent(
							jScrollPane, GroupLayout.DEFAULT_SIZE, (jScrollPane.getPreferredSize().height + fontSize*10),
							GraphicsEnvironment
							.getLocalGraphicsEnvironment().getDefaultScreenDevice()
							.getDisplayMode().getHeight())));

			jFrame.pack();
			jFrame.setAlwaysOnTop(true);
			jFrame.setVisible(true);
			jFrame.setLocationRelativeTo(null);
			jFrame.setAlwaysOnTop(false);
		}
		else
		{

			GroupLayout layout = new GroupLayout(jFrame.getContentPane());
			jFrame.getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(jScrollPane, 1, GroupLayout.PREFERRED_SIZE,
							GraphicsEnvironment.getLocalGraphicsEnvironment()
									.getDefaultScreenDevice().getDisplayMode()
									.getWidth()));
			layout.setVerticalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addComponent(
							jScrollPane, 1,
							GraphicsEnvironment.getLocalGraphicsEnvironment()
									.getDefaultScreenDevice().getDisplayMode()
									.getHeight(),
							GraphicsEnvironment.getLocalGraphicsEnvironment()
									.getDefaultScreenDevice().getDisplayMode()
									.getHeight())));

			jFrame.pack();
			jFrame.setAlwaysOnTop(true);
			jFrame.setVisible(true);
			jFrame.setLocationRelativeTo(null);
			jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			jFrame.setAlwaysOnTop(false);

		}

		jFrame.getRootPane().setDefaultButton(saveButton);

	}

	protected void defaultButtonActionPerformed(ActionEvent evt)
	{

		advantageWorthText.setText("0");
		advantageDiscountText.setText("5");
		showSuccessesCheckBox.setSelected(true);
		showAdvantagesCheckBox.setSelected(true);
		skillTypeCombo.setSelectedIndex(29);
		useVanillaPricingCheckBox.setSelected(true);
		useVanillaWorldModifiersCheckBox.setSelected(true);

	}

	protected void cancelButtonActionPerformed(ActionEvent evt)
	{

		jFrame.dispose();

	}

	protected void saveButtonActionPerformed(ActionEvent evt)
	{

		try
		{

			Integer.parseInt(advantageDiscountText.getText());
			Double.parseDouble(triumphWorthText.getText());
			Double.parseDouble(advantageWorthText.getText());

			setting.setSetting(21, triumphDiscountText.getText());
			setting.setSetting(25, advantageWorthText.getText());
			setting.setSetting(22,
					Boolean.toString(autoDiscountCheckBox.isSelected()));
			setting.setSetting(23,
					"" + (Integer.parseInt(advantageDiscountText.getText())));
			setting.setSetting(24, triumphWorthText.getText());
			setting.setSetting(30,
					Boolean.toString(showAdvantagesCheckBox.isSelected()));
			if(showRestrictedMarkerCheckBox.isSelected())
			{
				setting.setSetting(20, "(R) ");
			}
			else
			{
				setting.setSetting(20, "");
			}

			Scanner reader = new Scanner(
					(String) skillTypeCombo.getSelectedItem());

			String characteristic = reader.next();

			if (characteristic.contains("Br"))
			{

				characteristic = "Brawl";

			}
			else if (characteristic.contains("Cun"))
			{

				characteristic = "Cunning";

			}
			else if (characteristic.contains("Ag"))
			{

				characteristic = "Agility";

			}
			else if (characteristic.contains("Pr"))
			{

				characteristic = "Presence";

			}
			else if (characteristic.contains("Int"))
			{

				characteristic = "Intellect";

			}
			else if (characteristic.contains("Will"))
			{

				characteristic = "Willpower";

			}

			String skill = reader.next();
			reader.close();
			
			setting.setSetting(34, Boolean.toString(showSuccessesCheckBox.isSelected()));
			setting.setSetting(35, Boolean.toString(useVanillaWorldModifiersCheckBox.isSelected()));
			if(!useVanillaWorldModifiersCheckBox.isSelected())
			{
				useVanillaPricingCheckBox.setSelected(false);
			}
			setting.setSetting(36,  Boolean.toString(useVanillaPricingCheckBox.isSelected()));
			
			

			
			
			reader.close();
			setting.setSetting(31, skill);
			setting.setSetting(32, characteristic);
			setting.saveSettings();
			jFrame.dispose();

		}
		catch (NumberFormatException e)
		{

			{
				JOptionPane.showMessageDialog(new javax.swing.JFrame(),
						"<html><span style='font-size:14pt'>"
								+ "All input values must be numeric. Trust me, it's important.");
			}

		}

	}
}
