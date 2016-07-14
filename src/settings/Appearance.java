
package settings;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import helper.Settings;
import sourceBooksAndItems.EditItems;

public class Appearance extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5418919849466779913L;
	private JButton cancelButton;
	private JButton saveButton;
	private Color fontColor;
	private int fontSize;
	private String fontName;
	private Font font1;
	private Font font2;
	private JPanel panel;
	private String[] fonts;
	private JComboBox<String> fontChooser;
	private JLabel fontLabel;
	private JLabel fontSizeLabel;
	private JTextField fontSizeText;
	private JLabel titleLabel;
	private JLabel description;
	private JButton fontColorButton;
	private JButton backgroundColorButton;
	private Settings setting;
	private Color backgroundColor;
	private JScrollPane jScrollPane;

	public void run()
	{

		try
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(EditItems.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(EditItems.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(EditItems.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(EditItems.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}

		{
			Settings setting = new Settings();
			fontSize = setting.getSettingNumber(17);
			fontName = setting.getSettingString(26);
			fontColor = new Color(setting.getSettingNumber(27),
					setting.getSettingNumber(28), setting.getSettingNumber(29));
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

		jScrollPane = new JScrollPane();
		font1 = new Font(fontName, 0, (int) Math.round(fontSize * 1.5));
		font2 = new Font(fontName, 0, fontSize);

		{
			Settings setting = new Settings();
			String color = setting.getSettingString(18).replaceAll("[^0-9,]",
					"");
			Scanner reader = new Scanner(color);
			reader.useDelimiter(",");

			fontColor = new Color(reader.nextInt(), reader.nextInt(),
					reader.nextInt());

			reader.close();

			color = setting.getSettingString(16).replaceAll("[^0-9,]", "");
			reader = new Scanner(color);
			reader.useDelimiter(",");

			backgroundColor = new Color(reader.nextInt(), reader.nextInt(),
					reader.nextInt());
			reader.close();

		}

		panel = new JPanel();
		fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
		fontChooser = new JComboBox<String>();
		fontLabel = new JLabel("Font");
		fontSizeLabel = new JLabel("Size");
		fontSizeText = new JTextField();
		titleLabel = new JLabel("Change Appearance");
		description = new JLabel("This is a description.");
		fontColorButton = new JButton("Font Color");
		backgroundColorButton = new JButton("Background Color");
		fontColorButton.setFont(font2);
		panel.setForeground(fontColor);
		panel.setBackground(backgroundColor);

		titleLabel.setForeground(fontColor);
		fontLabel.setForeground(fontColor);
		fontSizeLabel.setForeground(fontColor);
		description.setForeground(fontColor);
		panel.setBackground(backgroundColor);

		fontColorButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				fontColor = JColorChooser.showDialog(new JFrame(),
						"Set Font Color", fontColor);

				titleLabel.setForeground(fontColor);
				fontLabel.setForeground(fontColor);
				fontSizeLabel.setForeground(fontColor);
				description.setForeground(fontColor);
				panel.setBackground(backgroundColor);

			}
		});
		backgroundColorButton.setFont(font2);
		backgroundColorButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				backgroundColor = JColorChooser.showDialog(new JFrame(),
						"Set Background Color", backgroundColor);

				titleLabel.setForeground(fontColor);
				fontLabel.setForeground(fontColor);
				fontSizeLabel.setForeground(fontColor);
				description.setForeground(fontColor);
				panel.setBackground(backgroundColor);
			}
		});

		setting = new Settings();

		fontChooser
				.setModel(new javax.swing.DefaultComboBoxModel<String>(fonts));
		fontChooser.setFont(font2);
		fontChooser.setEditable(false);

		fontChooser.setSelectedItem(setting.getSettingString(26));
		fontChooser.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				// Execute when a selection has been made
				fontName = fontChooser.getSelectedItem().toString();
				try
				{
					fontSize = Integer.parseInt(fontSizeText.getText());
					font1 = new Font(fontName, 0,
							(int) Math.round(fontSize * 1.5));
					font2 = new Font(fontName, 0, fontSize);
					fontChooser.setFont(font2);
					fontLabel.setFont(font2);
					fontSizeLabel.setFont(font2);
					fontSizeText.setFont(font2);
					fontSizeText.setText(setting.getSettingString(17));
					titleLabel.setFont(font1);
					description.setFont(font2);
					saveButton.setFont(font2);
					cancelButton.setFont(font2);
					fontColorButton.setFont(font2);
					backgroundColorButton.setFont(font2);
					{
						String longestName = "";
						for (int i = 0; i < fonts.length; i++)
						{

							if (fonts[i].length() > longestName.length())
							{

								longestName = fonts[i];

							}

						}
						JLabel testField = new JLabel(longestName);
						testField.setFont(font2);
						fontChooser.setSize(testField.getPreferredSize());
					}
					pack();
					setLocationRelativeTo(null);
				}
				catch (NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null,
							"<html><span style='font-size:" + fontSize
									+ "'>Font size must be an integer value.");
				}
			}
		});

		{
			String longestName = "";
			for (int i = 0; i < fonts.length; i++)
			{

				if (fonts[i].length() > longestName.length())
				{

					longestName = fonts[i];

				}

			}
			JLabel testField = new JLabel(longestName);
			testField.setFont(font2);
			fontChooser.setSize(testField.getPreferredSize());
		}
		fontLabel.setFont(font2);
		fontSizeLabel.setFont(font2);
		fontSizeText.setFont(font2);
		fontSizeText.setText(setting.getSettingString(17));
		titleLabel.setFont(font1);
		description.setFont(font2);

		saveButton = new JButton("SAVE");
		saveButton.setBackground(new java.awt.Color(0, 255, 255));
		saveButton.setFont(font2);
		saveButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				saveButtonActionPerformed(evt);
			}
		});

		cancelButton = new JButton("CANCEL");
		cancelButton.setFont(font2);
		cancelButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				cancelButtonActionPerformed(evt);
			}
		});

		add(panel);

		javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(
				panel);
		panel.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(panelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup().addContainerGap()
						.addGroup(panelLayout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.CENTER)
								.addGroup(panelLayout.createSequentialGroup()
										.addGroup(panelLayout
												.createParallelGroup(
														javax.swing.GroupLayout.Alignment.CENTER)
												.addComponent(titleLabel,
														javax.swing.GroupLayout.Alignment.CENTER,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(description,
														javax.swing.GroupLayout.Alignment.CENTER,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.CENTER,
														panelLayout
																.createSequentialGroup()
																.addGap(0, 0,
																		Short.MAX_VALUE)
																.addComponent(
																		fontLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		fontChooser,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		fontSizeLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		fontSizeText,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addContainerGap(
																		10, 10))
												.addGroup(
														javax.swing.GroupLayout.Alignment.CENTER,
														panelLayout
																.createSequentialGroup()
																.addGap(0, 0,
																		Short.MAX_VALUE)
																.addComponent(
																		fontColorButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		backgroundColorButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addContainerGap(
																		10, 10))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														panelLayout
																.createSequentialGroup()
																.addComponent(
																		cancelButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		saveButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap(
																		0,
																		0)))))));
		panelLayout.setVerticalGroup(panelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						panelLayout.createSequentialGroup().addContainerGap()
								.addComponent(titleLabel)
								.addComponent(description)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panelLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(fontLabel)
										.addComponent(fontChooser)
										.addComponent(fontSizeLabel)
										.addComponent(fontSizeText))
								.addContainerGap(10, 10)
								.addGroup(panelLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(fontColorButton)
										.addComponent(backgroundColorButton))
								.addContainerGap(10, 10)
								.addGroup(panelLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(saveButton)
										.addComponent(cancelButton))
								.addContainerGap(10, 10)));
		
		panelLayout.linkSize(cancelButton, saveButton);
		panelLayout.linkSize(fontColorButton, backgroundColorButton);

		jScrollPane.setViewportView(panel);

		panel.setBorder(
				javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

		panel.revalidate();

		if (jScrollPane.getPreferredSize().getWidth() <= GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDisplayMode().getWidth()
				&&
				jScrollPane.getPreferredSize().getHeight() <= GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDisplayMode().getHeight()
				)
		{
			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jScrollPane, 1, GroupLayout.PREFERRED_SIZE,
							GraphicsEnvironment.getLocalGraphicsEnvironment()
									.getDefaultScreenDevice().getDisplayMode()
									.getWidth()));
			layout.setVerticalGroup(layout
					.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup().addComponent(
							jScrollPane, 1, GroupLayout.PREFERRED_SIZE,
							GraphicsEnvironment.getLocalGraphicsEnvironment()
									.getDefaultScreenDevice().getDisplayMode()
									.getHeight())));

			pack();
			setAlwaysOnTop(true);
			setVisible(true);
			setLocationRelativeTo(null);
			setAlwaysOnTop(false);
		}
		else
		{

			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
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
			
			pack();
			setAlwaysOnTop(true);
			setVisible(true);
			setLocationRelativeTo(null);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setAlwaysOnTop(false);
		}

		getRootPane().setDefaultButton(saveButton);

	}

	protected void cancelButtonActionPerformed(ActionEvent evt)
	{

		dispose();

	}

	protected void saveButtonActionPerformed(ActionEvent evt)
	{

		if (fontSizeText.getText().equals(""))
		{

			JOptionPane.showMessageDialog(this, "<html><span style='font-size:"
					+ fontSize
					+ "'>No fields may be left blank (trust me, it matters).");

		}
		else
		{

			setting.setSetting(26, fontChooser.getSelectedItem().toString());
			setting.setSetting(17, fontSizeText.getText());
			setting.setSetting(16,
					"rgb(" + backgroundColor.getRed() + ","
							+ backgroundColor.getGreen() + ","
							+ backgroundColor.getBlue() + ")");
			setting.setSetting(18, "rgb(" + fontColor.getRed() + ","
					+ fontColor.getGreen() + "," + fontColor.getBlue() + ")");
			setting.saveSettings();
			dispose();

		}

	}

}
