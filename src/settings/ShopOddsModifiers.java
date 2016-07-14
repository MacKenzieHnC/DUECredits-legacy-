
package settings;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import helper.Settings;

import javax.swing.JButton;
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

public class ShopOddsModifiers extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7531267312172805299L;
	private JButton cancelButton;
	private int columnCount;
	private int componentCount;
	private int count;
	private int labelPreferredSize;
	private int inputPreferredSize;
	@SuppressWarnings("unused")
	private JFrame jFrame;
	private JPanel jPanel;
	private JScrollPane jScrollPane;
	private ParallelGroup parallelMajor;
	private ParallelGroup parallelMinor;
	private JButton saveButton;
	private SequentialGroup sequenceMajor;
	private SequentialGroup sequenceMinor;
	private JLabel description;
	private JLabel titleLabel;
	private JLabel onTheLevelLabel;
	private JLabel shadyLabel;
	private JLabel blackMarketLabel;
	private JLabel otlNonrestrictedLabel;
	private JLabel otlRestrictedLabel;
	private JLabel otlLightsaberLabel;
	private JLabel sNonrestrictedLabel;
	private JLabel sRestrictedLabel;
	private JLabel sLightsaberLabel;
	private JLabel bmNonrestrictedLabel;
	private JLabel bmRestrictedLabel;
	private JLabel bmLightsaberLabel;

	private JTextField otlNonrestrictedText;
	private JTextField otlRestrictedText;
	private JTextField otlLightsaberText;
	private JTextField sNonrestrictedText;
	private JTextField sRestrictedText;
	private JTextField sLightsaberText;
	private JTextField bmNonrestrictedText;
	private JTextField bmRestrictedText;
	private JTextField bmLightsaberText;

	private JLabel[] labelComponent;
	private JTextField[] inputComponent;

	helper.Settings setting;
	private int fontSize;
	private String fontName;
	private Font font1;
	private Font font2;
	private Font font3;
	private JButton defaultButton;

	private JFrame frame;
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
	    .addPropertyChangeListener("permanentFocusOwner", new java.beans.PropertyChangeListener()
	{
	    public void propertyChange(final java.beans.PropertyChangeEvent e)
	    {
	    	if (e.getNewValue() instanceof JTextField)
	    	{
	    		javax.swing.SwingUtilities.invokeLater(new Runnable()
	    		{
	    			public void run()
	    			{
	    				JTextField textField = (JTextField)e.getNewValue();
	    				textField.selectAll();
	    			}
	    		});

	    	}
	    }
	});
		
		setting = new Settings();
		fontSize = setting.getSettingNumber(17);
		fontName = setting.getSettingString(26);
		font1 = new Font(fontName, 0, (int) Math.round(fontSize * 1.5));
		font2 = new Font(fontName, 0, (int) Math.round(fontSize * 1.25));
		font3 = new Font(fontName, 0, fontSize);
		frame = new JFrame();

		componentCount = 9;

		columnCount = 3;

		titleLabel = new JLabel("Shop Odds Modifiers");
		titleLabel.setFont(font1);
		description = new JLabel(
				"<html>&nbsp&nbsp&nbsp&nbsp&nbsp These numbers add or subtract successes directly from "
				+ "the dice roll. Positive numbers add, negative numbers subtract."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp Since even with 5 in the skill and 6 in the characteristic the biggest roll you can get is 12 successes"
				+ "(and 10 failures on a rarity 10 item), these numbers have a big effect, so use sparingly unless"
				+ " you're trying to guarantee that an item type does or does not show up in a given shop..</html>");

		description.setFont(font3);
		jScrollPane = new JScrollPane();
		jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		jPanel = new JPanel();
		jFrame = new JFrame();

		saveButton = new JButton();
		saveButton.setFont(font3);
		saveButton.setBackground(new java.awt.Color(0, 255, 255));
		saveButton.setText("SAVE");
		saveButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				saveButtonActionPerformed(evt);
			}
		});

		cancelButton = new JButton();
		cancelButton.setFont(font3);
		cancelButton.setText("CANCEL");
		cancelButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				cancelButtonActionPerformed(evt);
			}
		});
		
		defaultButton = new JButton();
		defaultButton.setFont(font3);
		defaultButton.setText("DEFAULT");
		defaultButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				defaultButtonActionPerformed(evt);
			}
		});

		onTheLevelLabel = new JLabel("On The Level");
		onTheLevelLabel.setFont(font2);
		onTheLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		shadyLabel = new JLabel("Shady");
		shadyLabel.setFont(font2);
		shadyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		blackMarketLabel = new JLabel("Black Market");
		blackMarketLabel.setFont(font2);
		blackMarketLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		onTheLevelLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		shadyLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		blackMarketLabel.setBorder(BorderFactory.createLineBorder(Color.black));

		otlNonrestrictedLabel = new JLabel("Nonrestricted");
		otlNonrestrictedText = new JTextField("" + setting.getSettingNumber(7));

		sNonrestrictedLabel = new JLabel("Nonrestricted");
		sNonrestrictedText = new JTextField("" + setting.getSettingNumber(10));

		bmNonrestrictedLabel = new JLabel("Nonrestricted");
		bmNonrestrictedText = new JTextField("" + setting.getSettingNumber(13));

		otlRestrictedLabel = new JLabel("Restricted");
		otlRestrictedText = new JTextField("" + setting.getSettingNumber(8));

		sRestrictedLabel = new JLabel("Restricted");
		sRestrictedText = new JTextField("" + setting.getSettingNumber(11));

		bmRestrictedLabel = new JLabel("Restricted");
		bmRestrictedText = new JTextField("" + setting.getSettingNumber(14));

		otlLightsaberLabel = new JLabel("Lightsaber");
		otlLightsaberText = new JTextField("" + setting.getSettingNumber(9));

		sLightsaberLabel = new JLabel("Lightsaber");
		sLightsaberText = new JTextField("" + setting.getSettingNumber(12));

		bmLightsaberLabel = new JLabel("Lightsaber");
		bmLightsaberText = new JTextField("" + setting.getSettingNumber(15));

		labelComponent = new JLabel[componentCount];
		labelComponent[0] = otlNonrestrictedLabel;
		labelComponent[1] = sNonrestrictedLabel;
		labelComponent[2] = bmNonrestrictedLabel;
		labelComponent[3] = otlRestrictedLabel;
		labelComponent[4] = sRestrictedLabel;
		labelComponent[5] = bmRestrictedLabel;
		labelComponent[6] = otlLightsaberLabel;
		labelComponent[7] = sLightsaberLabel;
		labelComponent[8] = bmLightsaberLabel;

		inputComponent = new JTextField[componentCount];
		inputComponent[0] = otlNonrestrictedText;
		inputComponent[1] = sNonrestrictedText;
		inputComponent[2] = bmNonrestrictedText;
		inputComponent[3] = otlRestrictedText;
		inputComponent[4] = sRestrictedText;
		inputComponent[5] = bmRestrictedText;
		inputComponent[6] = otlLightsaberText;
		inputComponent[7] = sLightsaberText;
		inputComponent[8] = bmLightsaberText;

		inputPreferredSize = 120;
		labelPreferredSize = 0;
		for (int i = 0; i < componentCount; i++)
		{

			labelComponent[i].setFont(font3);
			inputComponent[i].setFont(font3);
			if (labelComponent[i].getPreferredSize()
					.getWidth() > labelPreferredSize)
			{

				labelPreferredSize = (int) Math.round(
						labelComponent[i].getPreferredSize().getWidth() + .5);

			}

		}
		// Meat and potatoes
		GroupLayout jPanelLayout = new GroupLayout(jPanel);
		jPanel.setLayout(jPanelLayout);

		// asdfffffffffffffffffffffffffffffff
		parallelMinor = jPanelLayout
				.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(description, GroupLayout.Alignment.LEADING,
						GroupLayout.DEFAULT_SIZE,
						((labelPreferredSize * columnCount)
								+ (inputPreferredSize * columnCount)
								+ ((columnCount - 1) * 20)),
						((labelPreferredSize * columnCount)
								+ (inputPreferredSize * columnCount)
								+ ((columnCount - 1) * 20)));

		sequenceMinor = jPanelLayout.createSequentialGroup();

		count = 0;

		sequenceMinor.addComponent(onTheLevelLabel, GroupLayout.PREFERRED_SIZE,
				(labelPreferredSize + inputPreferredSize + 20), Short.MAX_VALUE);
		sequenceMinor.addComponent(shadyLabel, GroupLayout.PREFERRED_SIZE,
				(labelPreferredSize + inputPreferredSize + 20), Short.MAX_VALUE);
		sequenceMinor.addComponent(blackMarketLabel, GroupLayout.PREFERRED_SIZE,
				(labelPreferredSize + inputPreferredSize + 20), Short.MAX_VALUE);

		parallelMinor.addGroup(GroupLayout.Alignment.CENTER, sequenceMinor).addComponent(titleLabel);

		parallelMajor = jPanelLayout
				.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(parallelMinor);

		while (count < componentCount)
		{

			sequenceMajor = jPanelLayout.createSequentialGroup();

			for (int j = 0; j < columnCount; j++)
			{

				if (count >= componentCount)
				{

				}
				else
				{
					sequenceMajor
							.addComponent(labelComponent[count],
									GroupLayout.PREFERRED_SIZE,
									labelPreferredSize,
									labelPreferredSize)
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED)

							.addComponent(inputComponent[count],
									GroupLayout.PREFERRED_SIZE,
									inputPreferredSize,
									inputPreferredSize)
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.UNRELATED);
					count++;
				}

			}

			parallelMajor.addGroup(sequenceMajor);

		}
		

		jPanelLayout.setHorizontalGroup(jPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(parallelMajor.addGroup(
								GroupLayout.Alignment.TRAILING, jPanelLayout
										.createSequentialGroup()

										.addComponent(defaultButton,
												GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cancelButton,
												GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(saveButton,
												GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE)

						)

						).addContainerGap()));

		jPanelLayout.linkSize(SwingConstants.HORIZONTAL, labelComponent);

		jPanelLayout.linkSize(SwingConstants.HORIZONTAL, inputComponent);

		sequenceMajor = jPanelLayout.createSequentialGroup().addContainerGap()
				.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(description, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED);
		

		parallelMajor = jPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING);

		parallelMinor = jPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING);
		count = 0;

		parallelMinor.addComponent(onTheLevelLabel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,
				GroupLayout.PREFERRED_SIZE);
		parallelMinor.addComponent(shadyLabel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,
				GroupLayout.PREFERRED_SIZE);
		parallelMinor.addComponent(blackMarketLabel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,
				GroupLayout.PREFERRED_SIZE);

		sequenceMajor.addGroup(parallelMajor.addGroup(parallelMinor));

		while (count < componentCount)
		{

			parallelMajor = jPanelLayout
					.createParallelGroup(GroupLayout.Alignment.LEADING);

			for (int j = 0; j < columnCount; j++)
			{

				if (count >= componentCount)
				{

				}
				else
				{

					parallelMajor.addComponent(labelComponent[count], GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,
							GroupLayout.PREFERRED_SIZE);
					parallelMajor.addComponent(inputComponent[count], GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,
							GroupLayout.PREFERRED_SIZE);
					count++;

				}
				sequenceMajor.addGroup(parallelMajor);

			}

		}

		jPanelLayout.setVerticalGroup(
				jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(sequenceMajor.addPreferredGap(
								LayoutStyle.ComponentPlacement.UNRELATED)

								.addGroup(jPanelLayout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(defaultButton)
										.addComponent(cancelButton)
										.addComponent(saveButton))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)

								.addContainerGap(30, 30)));

		jPanelLayout.linkSize(SwingConstants.VERTICAL, inputComponent);

		jPanelLayout.linkSize(SwingConstants.VERTICAL, labelComponent);
		
		jPanelLayout.linkSize(cancelButton, defaultButton, saveButton);
		
		jPanelLayout.linkSize(onTheLevelLabel, shadyLabel, blackMarketLabel);
		jPanel.setBorder(
				javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
		jScrollPane.setViewportView(jPanel);

		if (jScrollPane.getPreferredSize().getWidth() <= GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDisplayMode().getWidth()
				&&
				(int)(Math.round(jScrollPane.getPreferredSize().getHeight() * 1.2)) <= GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDisplayMode().getHeight()
				)
		{
			GroupLayout layout = new GroupLayout(frame.getContentPane());
			frame.getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jScrollPane, 1, GroupLayout.PREFERRED_SIZE,
							GraphicsEnvironment.getLocalGraphicsEnvironment()
									.getDefaultScreenDevice().getDisplayMode()
									.getWidth()));
			layout.setVerticalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addComponent(
							jScrollPane, 1, (int)(Math.round(jScrollPane.getPreferredSize().getHeight() * 1.2)),
							GraphicsEnvironment.getLocalGraphicsEnvironment()
									.getDefaultScreenDevice().getDisplayMode()
									.getHeight())));

			frame.pack();
			frame.setAlwaysOnTop(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.setAlwaysOnTop(false);
		}
		else
		{

			GroupLayout layout = new GroupLayout(frame.getContentPane());
			frame.getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
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
			
			frame.pack();
			frame.setAlwaysOnTop(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setAlwaysOnTop(false);
		}

		frame.getRootPane().setDefaultButton(saveButton);

	}

	protected void defaultButtonActionPerformed(ActionEvent evt)
	{

		otlNonrestrictedText.setText("0");
        otlRestrictedText.setText("-100");
        otlLightsaberText.setText("-100");
       sNonrestrictedText.setText("0");
        sRestrictedText.setText("0");
        sLightsaberText.setText("-1");
        bmNonrestrictedText.setText("1");
        bmRestrictedText.setText("1");
        bmLightsaberText.setText("-1");
		
	}

	protected void cancelButtonActionPerformed(ActionEvent evt)
	{

		frame.dispose();

	}

	protected void saveButtonActionPerformed(ActionEvent evt)
	{

		setting.setSetting(7, otlNonrestrictedText.getText());
        setting.setSetting(8, otlRestrictedText.getText());
        setting.setSetting(9, otlLightsaberText.getText());
        setting.setSetting(10, sNonrestrictedText.getText());
        setting.setSetting(11, sRestrictedText.getText());
        setting.setSetting(12, sLightsaberText.getText());
        setting.setSetting(13, bmNonrestrictedText.getText());
        setting.setSetting(14, bmRestrictedText.getText());
        setting.setSetting(15, bmLightsaberText.getText());
        setting.saveSettings();
        frame.dispose();

	}
}
