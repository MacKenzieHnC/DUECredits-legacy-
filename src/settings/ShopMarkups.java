
package settings;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import helper.Settings;

public class ShopMarkups extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1139854725841035084L;
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
	private JLabel subTitleLabel;
	private JLabel titleLabel;
	private JLabel onTheLevelLabel;
	private JLabel shadyLabel;
	private JLabel blackMarketLabel;
	private JLabel otlMaxLabel;
	private JLabel otlMinLabel;
	private JLabel sMaxLabel;
	private JLabel sMinLabel;
	private JLabel bmMaxLabel;
	private JLabel bmMinLabel;


	private JTextField otlMaxText;
	private JTextField otlMinText;
	private JTextField sMaxText;
	private JTextField sMinText;
	private JTextField bmMaxText;
	private JTextField bmMinText;

	private JLabel[] labelComponent;
	private JTextField[] inputComponent;

	helper.Settings setting;
	private int fontSize;
	private String fontName;
	private Font font1;
	private Font font2;
	private Font font3;
	private JButton defaultButton;

	public void run()
	{
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
		componentCount = 6;

		columnCount = 3;

		titleLabel = new JLabel("Shop Markups/Discounts");
		titleLabel.setFont(font1);
		subTitleLabel = new JLabel(
				"<html>&nbsp&nbsp&nbsp&nbsp&nbsp These numbers affect the markup/discount range(%). "
				+ "The actual markup on a given item will be a random number in that range. "
				+ "So, if you want items in \"On the Level\" shops to be marked up between "
				+ "10% and 30%, set the minimum to 10 and the maximum to 30. "
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp You can also "
				+ "discount items by putting a negative number, though make sure that the "
				+ "minimum is less than the maximum."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp All values must be integers."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp To set a constant percentage, set Min/Max to the same value."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp To nullify this feature, set all values to 0."
				+ "</html>");

		subTitleLabel.setFont(font3);
		jScrollPane = new JScrollPane();
		jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		jPanel = new JPanel();
		jFrame = new JFrame();
		jPanel.setBorder(
				javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
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

		otlMaxLabel = new JLabel("Max");
		otlMaxText = new JTextField("" + setting.getSettingNumber(1));

		sMaxLabel = new JLabel("Max");
		sMaxText = new JTextField("" + setting.getSettingNumber(3));

		bmMaxLabel = new JLabel("Max");
		bmMaxText = new JTextField("" + setting.getSettingNumber(5));

		otlMinLabel = new JLabel("Min");
		otlMinText = new JTextField("" + setting.getSettingNumber(2));

		sMinLabel = new JLabel("Min");
		sMinText = new JTextField("" + setting.getSettingNumber(4));

		bmMinLabel = new JLabel("Min");
		bmMinText = new JTextField("" + setting.getSettingNumber(6));

		labelComponent = new JLabel[componentCount];
		labelComponent[0] = otlMaxLabel;
		labelComponent[1] = sMaxLabel;
		labelComponent[2] = bmMaxLabel;
		labelComponent[3] = otlMinLabel;
		labelComponent[4] = sMinLabel;
		labelComponent[5] = bmMinLabel;

		inputComponent = new JTextField[componentCount];
		inputComponent[0] = otlMaxText;
		inputComponent[1] = sMaxText;
		inputComponent[2] = bmMaxText;
		inputComponent[3] = otlMinText;
		inputComponent[4] = sMinText;
		inputComponent[5] = bmMinText;

		inputPreferredSize = 200;
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
		

		
		SetJLabelHeight.setJLabelHeight((labelPreferredSize + inputPreferredSize + 30)*3, "&nbsp&nbsp&nbsp&nbsp&nbsp These numbers affect the markup/discount range(%). "
				+ "The actual markup on a given item will be a random number in that range. "
				+ "So, if you want items in \"On the Level\" shops to be marked up between "
				+ "10% and 30%, set the minimum to 10 and the maximum to 30. "
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp You can also "
				+ "discount items by putting a negative number, though make sure that the "
				+ "minimum is less than the maximum."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp All values must be integers."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp To set a constant percentage, set Min/Max to the same value."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp To nullify this feature, set all values to 0."
				+ "</html>", subTitleLabel);
		// Meat and potatoes
		GroupLayout jPanelLayout = new GroupLayout(jPanel);
		jPanel.setLayout(jPanelLayout);

		// asdfffffffffffffffffffffffffffffff
		parallelMinor = jPanelLayout
				.createParallelGroup(GroupLayout.Alignment.CENTER, false)
				.addComponent(subTitleLabel, GroupLayout.Alignment.LEADING,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE);

		sequenceMinor = jPanelLayout.createSequentialGroup();

		count = 0;

		sequenceMinor.addComponent(onTheLevelLabel, 0,
				(labelPreferredSize + inputPreferredSize + 30), Short.MAX_VALUE);
		sequenceMinor.addComponent(shadyLabel, 0,
				(labelPreferredSize + inputPreferredSize + 30), Short.MAX_VALUE);
		sequenceMinor.addComponent(blackMarketLabel, 0,
				(labelPreferredSize + inputPreferredSize + 30), Short.MAX_VALUE);

		parallelMinor.addGroup(sequenceMinor).addComponent(titleLabel);

		parallelMajor = jPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
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
												GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cancelButton,
												GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(saveButton,
												GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)

						)

						).addContainerGap()));

		jPanelLayout.linkSize(SwingConstants.HORIZONTAL, labelComponent);

		jPanelLayout.linkSize(SwingConstants.HORIZONTAL, inputComponent);

		sequenceMajor = jPanelLayout.createSequentialGroup().addContainerGap()
				.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(subTitleLabel)
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

		jScrollPane.setViewportView(jPanel);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane, 300, GroupLayout.PREFERRED_SIZE,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice().getDisplayMode()
								.getWidth()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(
						jScrollPane, 650, GroupLayout.PREFERRED_SIZE,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice().getDisplayMode()
								.getHeight())
						.addGap(0, 0, 0)));
		pack();
		setAlwaysOnTop(true);
		getRootPane().setDefaultButton(saveButton);
		setLocationRelativeTo(null);
		setVisible(true);
		setAlwaysOnTop(false);

	}

	protected void defaultButtonActionPerformed(ActionEvent evt)
	{

		otlMaxText.setText("30");
        otlMinText.setText("10");
       sMaxText.setText("80");
        sMinText.setText("50");
        bmMaxText.setText("100");
        bmMinText.setText("80");
		
	}

	protected void cancelButtonActionPerformed(ActionEvent evt)
	{

		dispose();

	}

	protected void saveButtonActionPerformed(ActionEvent evt)
	{

		try
		{
		if(Integer.parseInt(otlMaxText.getText()) >= Integer.parseInt(otlMinText.getText()) 
				&& Integer.parseInt(sMaxText.getText()) >= Integer.parseInt(sMinText.getText()) 
				&& Integer.parseInt(bmMaxText.getText()) >= Integer.parseInt(bmMinText.getText()))
		{
			setting.setSetting(1, otlMaxText.getText());
		
        setting.setSetting(2, otlMinText.getText());
        setting.setSetting(3, sMaxText.getText());
        setting.setSetting(4, sMinText.getText());
        setting.setSetting(5, bmMaxText.getText());
        setting.setSetting(6, bmMinText.getText());
        setting.saveSettings();
        dispose();
		}
		else
		{
			
			
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:14pt'>"
					+ "Minimum values must be greater than maximum values for the same shop type.");}
		}
		}
		catch(NumberFormatException e)
		{
			
			{JOptionPane.showMessageDialog(new javax.swing.JFrame(), 
					"<html><span style='font-size:14pt'>"
					+ "All values must be integers.");}
			
		}
	}
}
