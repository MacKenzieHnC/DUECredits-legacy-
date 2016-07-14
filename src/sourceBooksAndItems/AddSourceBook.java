
package sourceBooksAndItems;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import helper.Settings;

public class AddSourceBook extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3375599700922742554L;
	JLabel titleLabel = new JLabel("Add SourceBook");
	JLabel nameLabel = new JLabel("Source Book Name");
	JLabel abbrevLabel = new JLabel("Abbreviation");
	JLabel gameName = new JLabel("Game Set");
	JTextField nameText = new JTextField();
	JTextField abbrevText = new JTextField();
	JComboBox<String> gameCombo;
	JButton saveButton = new JButton("SAVE");
	JButton cancelButton = new JButton("CANCEL");

	int fontSize;
	String fontName;
	{
		Settings setting = new Settings();
		fontSize = setting.getSettingNumber(17);
		fontName = setting.getSettingString(26);
	}

	Font font1 = new Font(fontName, 0, (int) Math.round(fontSize * 1.5));
	Font font2 = new Font(fontName, 0, fontSize);
	private int labelPreferredWidth;
	private JPanel panel;
	private JScrollPane scrollPane;

	public void addSourceBook()
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
		
		titleLabel.setFont(font1);
		titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nameLabel.setFont(font2);
		abbrevLabel.setFont(font2);
		gameName.setFont(font2);
		nameText.setFont(font2);
		abbrevText.setFont(font2);
		
		gameCombo = new JComboBox<String>(new String[]{"Age of Rebellion", "Edge of the Empire", "Force and Destiny"});
		gameCombo.setFont(font2);

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

		cancelButton.setText("CANCEL");
		cancelButton.setFont(font2);
		cancelButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				cancelButtonActionPerformed(evt);
			}
		});
		panel = new JPanel();
		scrollPane = new JScrollPane();
		labelPreferredWidth = nameLabel.getPreferredSize().width;
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
												.addGroup(
														javax.swing.GroupLayout.Alignment.CENTER,
														panelLayout.createSequentialGroup()
																.addGap(0, 0,
																		Short.MAX_VALUE)
																.addComponent(
																		nameLabel,
																		labelPreferredWidth,
																		labelPreferredWidth,
																		labelPreferredWidth)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		nameText,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		labelPreferredWidth*2,
																		labelPreferredWidth*2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		abbrevLabel,
																		labelPreferredWidth,
																		labelPreferredWidth,
																		labelPreferredWidth)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		abbrevText,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		labelPreferredWidth*2,
																		labelPreferredWidth*2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addContainerGap(0,0))
												.addGroup(
														javax.swing.GroupLayout.Alignment.CENTER,
														panelLayout.createSequentialGroup()
																.addComponent(
																		gameName)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		gameCombo,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														panelLayout.createSequentialGroup()
																.addComponent(
																		cancelButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		saveButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap(0,0)))))));
		panelLayout.setVerticalGroup(panelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout
						.createSequentialGroup().addContainerGap()
						.addComponent(titleLabel)
						.addPreferredGap(
								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelLayout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(nameLabel).addComponent(nameText)
								.addComponent(abbrevLabel)
								.addComponent(abbrevText))
						.addContainerGap(10,10)
						.addGroup(panelLayout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(gameName)
								.addComponent(gameCombo))
						.addGroup(panelLayout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(saveButton)
								.addComponent(cancelButton))
						.addContainerGap(10,10)));
		
		panelLayout.linkSize(SwingConstants.HORIZONTAL, saveButton, cancelButton);
		
		scrollPane.setViewportView(panel);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(scrollPane));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(scrollPane));

		setResizable(false);
		pack();
		setAlwaysOnTop(true);
		getRootPane().setDefaultButton(saveButton);
		setLocationRelativeTo(null);
		setVisible(true);

		if(scrollPane.getPreferredSize().width >=  GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDisplayMode().getWidth())
		{
			
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			
		}
	}

	protected void cancelButtonActionPerformed(ActionEvent evt)
	{

		dispose();

	}

	protected void saveButtonActionPerformed(ActionEvent evt)
	{

		if(nameText.getText().equals("") || abbrevText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,
					"<html><span style='font-size:" + fontSize
							+ "'>Neither field may be left blank (trust me, it matters).");
		}
		else
		{
			
			SourceBooks source = new SourceBooks();
			
			String sourceName = "";
			
			if(gameCombo.getSelectedItem().equals("Age of Rebellion"))
			{
				
				sourceName += "(AoR) ";
				
			}
			else if(gameCombo.getSelectedItem().equals("Edge of the Empire"))
			{
				
				sourceName += "(EotE) ";
				
			}
			else
			{
				
				sourceName += "(FaD) ";
				
			}
			
			sourceName += nameText.getText();
			
			source.addSourceBook(sourceName, abbrevText.getText());
			source.saveSourceBooks();
			source = new SourceBooks();
			source.saveSourceBooks();
			
			dispose();
			
		}

	}

}
