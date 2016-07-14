
package settings;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import helper.Settings;

public class SourceBookSelection
{

	Settings resourceSetting = new Settings();
	int fontSize;
	String fontName;
	{
		Settings setting = new Settings();
		fontSize = setting.getSettingNumber(17);
		fontName = setting.getSettingString(26);
	}

	Font font1 = new Font(fontName, 0, (int) Math.round(fontSize * 1.5));
	Font font2 = new Font(fontName, 0, (int) Math.round(fontSize * 1.25));
	Font font3 = new Font(fontName, 0, fontSize);

	int componentPreferredWidth;

	JFrame frame = new JFrame();
	JScrollPane jScrollPane1 = new JScrollPane();
	JPanel panel = new JPanel();
	JLabel titleLabel = new JLabel();
	JLabel description = new JLabel();
	JScrollPane jScrollPane;

	sourceBooksAndItems.SourceBooks source = new sourceBooksAndItems.SourceBooks();
	int sourceBookCount = source.getSourceBookCount();
	{
		String longestName = "";
		for (int i = 0; i < sourceBookCount; i++)
		{

			if (source.getSourceBookName(i).length() > longestName.length())
			{

				longestName = source.getSourceBookName(i);

			}

		}
		JCheckBox testField = new JCheckBox(longestName);
		testField.setFont(font3);
		componentPreferredWidth = (int) testField.getPreferredSize().getWidth();
	}

	int count = 0;
	int guiColumnCount = 3;
	JButton saveButton = new JButton();
	JButton cancelButton = new JButton();

	JCheckBox showIndex = new JCheckBox();
	int eoteBookCount = 1;
	int fadBookCount = 1;
	int aorBookCount = 1;
	{
		for (int i = 0; i < sourceBookCount; i++)
		{

			if (source.getSourceBookName(i).contains("(AoR)"))
			{
				aorBookCount++;
			}
			else if (source.getSourceBookName(i).contains("(EotE)"))
			{
				eoteBookCount++;
			}
			else if (source.getSourceBookName(i).contains("(FaD)"))
			{
				fadBookCount++;
			}

		}
		
	}
	
	

	JCheckBox[] eoteCheckBox = new JCheckBox[eoteBookCount];
	JCheckBox[] fadCheckBox = new JCheckBox[fadBookCount];
	JCheckBox[] aorCheckBox = new JCheckBox[aorBookCount];

	private JCheckBox customCheckBox;
	private SequentialGroup verticalGroup;
	private ParallelGroup largestHorizParallel;
	private ParallelGroup secondSmallestHorizParallel;
	private ParallelGroup smallestHorizParallel;
	private JCheckBox[] smallestGroup;
	private JCheckBox[] secondSmallestGroup;
	private JCheckBox[] largestGroup;

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
			java.util.logging.Logger
					.getLogger(SourceBookSelection.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			java.util.logging.Logger
					.getLogger(SourceBookSelection.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			java.util.logging.Logger
					.getLogger(SourceBookSelection.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger
					.getLogger(SourceBookSelection.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		jScrollPane = new JScrollPane();

		titleLabel.setText("Source Book Selection");
		titleLabel.setFont(font1);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		description.setText("<html>"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp Select which source books items will be drawn from."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp Use the header checkboxes to select/deselect entire columns."
				+ "<br>&nbsp&nbsp&nbsp&nbsp&nbsp \"Show Index\" determines whether the indexes are shown in shops."
				+ "</html>");
		description.setFont(font3);
		description.setHorizontalAlignment(SwingConstants.CENTER);
		
		eoteCheckBox[0] = new JCheckBox("<html><u>Edge of the Empire</u></html>");
		eoteCheckBox[0].setFont(font2);
		eoteCheckBox[0].setCursor(
				new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		eoteCheckBox[0].addActionListener(new java.awt.event.ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{

						if(eoteCheckBox[0].isSelected())
						{
							
							for(int i = 0; i < eoteCheckBox.length; i++)
							{
								
								eoteCheckBox[i].setSelected(true);
								
							}
							
						}
						else
						{
							
							for(int i = 0; i < eoteCheckBox.length; i++)
							{
								
								eoteCheckBox[i].setSelected(false);
								
							}
							
						}
						
					}
			
				}
		);
		
		
		fadCheckBox[0] = new JCheckBox("<html><u>Force and Destiny");
		fadCheckBox[0].setFont(font2);
		fadCheckBox[0].setCursor(
				new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		fadCheckBox[0].addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				if(fadCheckBox[0].isSelected())
				{
					
					for(int i = 0; i < fadCheckBox.length; i++)
					{
						
						fadCheckBox[i].setSelected(true);
						
					}
					
				}
				else
				{
					
					for(int i = 0; i < fadCheckBox.length; i++)
					{
						
						fadCheckBox[i].setSelected(false);
						
					}
					
				}
				
			}
	
		});

		jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
		
		
		aorCheckBox[0] = new JCheckBox("<html><u>Age of Rebellion");
		aorCheckBox[0].setFont(font2);
		aorCheckBox[0].setCursor(
				new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		aorCheckBox[0].addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				if(aorCheckBox[0].isSelected())
				{
					
					for(int i = 0; i < aorCheckBox.length; i++)
					{
						
						aorCheckBox[i].setSelected(true);
						
					}
					
				}
				else
				{
					
					for(int i = 0; i < aorCheckBox.length; i++)
					{
						
						aorCheckBox[i].setSelected(false);
						
					}
					
				}
				
			}
	
		});

		customCheckBox = new JCheckBox("Custom");
		customCheckBox.setCursor(
				new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		for (int i = 1; i < eoteBookCount; i++)
		{

			eoteCheckBox[i] = new JCheckBox();

		}
		for (int i = 1; i < fadBookCount; i++)
		{

			fadCheckBox[i] = new JCheckBox();

		}
		for (int i = 1; i < aorBookCount; i++)
		{

			aorCheckBox[i] = new JCheckBox();

		}
		eoteBookCount = 1;
		fadBookCount = 1;
		aorBookCount = 1;
		for (int i = 0; i < sourceBookCount; i++)
		{

			if (source.getSourceBookName(i).contains("(AoR) "))
			{
				aorCheckBox[aorBookCount].setText(
						source.getSourceBookName(i).replace("(AoR) ", ""));
				aorCheckBox[aorBookCount].setFont(font3);
				aorCheckBox[aorBookCount].setCursor(
						new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				try
				{
					aorCheckBox[aorBookCount].setSelected(Boolean.parseBoolean(
							resourceSetting.getResourceSettingString(
									(i + 1) * 2 + 1)));
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					aorCheckBox[aorBookCount].setSelected(true);
				}
				aorBookCount++;
			}
			else if (source.getSourceBookName(i).contains("(EotE) "))
			{
				eoteCheckBox[eoteBookCount].setText(
						source.getSourceBookName(i).replace("(EotE) ", ""));
				eoteCheckBox[eoteBookCount].setFont(font3);
				eoteCheckBox[eoteBookCount].setCursor(
						new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				try
				{
					eoteCheckBox[eoteBookCount]
							.setSelected(Boolean.parseBoolean(
									resourceSetting.getResourceSettingString(
											(i + 1) * 2 + 1)));
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					eoteCheckBox[eoteBookCount].setSelected(true);
				}
				eoteBookCount++;
			}
			else if (source.getSourceBookName(i).contains("(FaD) "))
			{
				fadCheckBox[fadBookCount].setText(
						source.getSourceBookName(i).replace("(FaD) ", ""));
				fadCheckBox[fadBookCount].setFont(font3);
				fadCheckBox[fadBookCount].setCursor(
						new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				try
				{
					fadCheckBox[fadBookCount].setSelected(Boolean.parseBoolean(
							resourceSetting.getResourceSettingString(
									(i + 1) * 2 + 1)));
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					fadCheckBox[fadBookCount].setSelected(true);
				}
				fadBookCount++;
			}
		}
			
			customCheckBox.setFont(font3);
			customCheckBox.setCursor(
					new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			try
			{
				customCheckBox.setSelected(Boolean.parseBoolean(
						resourceSetting.getResourceSettingString(
								(sourceBookCount * 2) + 1)));
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				customCheckBox.setSelected(true);
			}
			

			showIndex.setFont(font3);
			showIndex.setText("Show Index in Shop");
			showIndex.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			showIndex.setSelected(Boolean
					.parseBoolean(resourceSetting.getResourceSettingString(1)));

		saveButton.setBackground(new java.awt.Color(0, 204, 204));
		saveButton.setFont(font2);
		saveButton.setText("SAVE");
		saveButton.setCursor(
				new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		saveButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				saveButtonActionPerformed(evt);
			}
		});

		cancelButton.setFont(font2);
		cancelButton.setText("CANCEL");
		cancelButton.setCursor(
				new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		cancelButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				cancelButtonActionPerformed(evt);
			}
		});
		
		for(int i = 0; i < eoteCheckBox.length; i++)
		{
			
			if(eoteCheckBox[i].isSelected())
			{
				
				eoteCheckBox[0].setSelected(true);
				break;
				
			}
			
		}
		for(int i = 0; i < fadCheckBox.length; i++)
		{
			
			if(fadCheckBox[i].isSelected())
			{
				
				fadCheckBox[0].setSelected(true);
				break;
				
			}
			
		}
		for(int i = 0; i < aorCheckBox.length; i++)
		{
			
			if(aorCheckBox[i].isSelected())
			{
				
				aorCheckBox[0].setSelected(true);
				break;
				
			}
			
		}

		GroupLayout panelLayout = new GroupLayout(panel);
		panel.setLayout(panelLayout);

		largestHorizParallel = panelLayout.createParallelGroup();
		secondSmallestHorizParallel = panelLayout.createParallelGroup();
		smallestHorizParallel = panelLayout.createParallelGroup();
		
		
		
		
		
		if(eoteCheckBox.length <= aorCheckBox.length && eoteCheckBox.length <= fadCheckBox.length)
		{
			
			if(aorCheckBox.length <= fadCheckBox.length)
			{
				
				smallestGroup = eoteCheckBox;
				secondSmallestGroup = aorCheckBox;
				largestGroup = fadCheckBox;
				
			}
			else
			{
				
				
				smallestGroup = eoteCheckBox;
				secondSmallestGroup = fadCheckBox;
				largestGroup = aorCheckBox;	
				
			}
			
		}
		else if(aorCheckBox.length <= fadCheckBox.length && aorCheckBox.length <= eoteCheckBox.length)
		{
			
			if(fadCheckBox.length <= eoteCheckBox.length)
			{
				
				
				smallestGroup = aorCheckBox;
				secondSmallestGroup = fadCheckBox;
				largestGroup = eoteCheckBox;
				
			}
			else
			{
				smallestGroup = aorCheckBox;
				secondSmallestGroup = eoteCheckBox;
				largestGroup = fadCheckBox;
			}
		}
		else
		{
			
			if(aorCheckBox.length < eoteCheckBox.length)
			{
				
				smallestGroup = fadCheckBox;
				secondSmallestGroup = aorCheckBox;
				largestGroup = eoteCheckBox;
				
			}
			else
			{
				
				smallestGroup = fadCheckBox;
				secondSmallestGroup = eoteCheckBox;
				largestGroup = aorCheckBox;
				
			}
			
		}

		for (int i = 0; i < largestGroup.length; i++)
		{

			largestHorizParallel.addComponent(largestGroup[i], 
											GroupLayout.DEFAULT_SIZE, 
											componentPreferredWidth, 
											GroupLayout.PREFERRED_SIZE);

		}
		for (int i = 0; i < secondSmallestGroup.length; i++)
		{

			secondSmallestHorizParallel.addComponent(secondSmallestGroup[i], 
					GroupLayout.DEFAULT_SIZE, 
					componentPreferredWidth, 
					GroupLayout.PREFERRED_SIZE);

		}
		for (int i = 0; i < smallestGroup.length; i++)
		{

			smallestHorizParallel.addComponent(smallestGroup[i], 
					GroupLayout.DEFAULT_SIZE, 
					componentPreferredWidth, 
					GroupLayout.PREFERRED_SIZE);

		}

		panelLayout.setHorizontalGroup
		(
				panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(titleLabel, 
						GroupLayout.Alignment.LEADING, 
						GroupLayout.DEFAULT_SIZE, 
						(componentPreferredWidth * 3) + 10, 
						GroupLayout.PREFERRED_SIZE)
				.addComponent(description, 
						GroupLayout.Alignment.LEADING, 
						GroupLayout.DEFAULT_SIZE,
						(componentPreferredWidth * 3) + 10, 
						GroupLayout.PREFERRED_SIZE)
				.addGroup
				(
						panelLayout.createSequentialGroup()
						.addGroup(largestHorizParallel)
						.addGroup(secondSmallestHorizParallel)
						.addGroup(smallestHorizParallel)
				)
				.addGroup
				(GroupLayout.Alignment.LEADING,
						panelLayout.createSequentialGroup()
						.addComponent(customCheckBox, 
								GroupLayout.DEFAULT_SIZE, 
								componentPreferredWidth, 
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 
								1, 
								componentPreferredWidth)
				)
						
				.addGroup
				(
				
						panelLayout.createSequentialGroup()
						.addComponent(cancelButton, 
										GroupLayout.DEFAULT_SIZE, 
										200, 
										GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(saveButton, 
										GroupLayout.DEFAULT_SIZE, 
										200, 
										GroupLayout.PREFERRED_SIZE)
						.addContainerGap(20,20)
						
				)
		);
		
		
		
		verticalGroup = panelLayout.createSequentialGroup()
				.addComponent(titleLabel)
				.addComponent(description);
		
		for(int i = 0; i < smallestGroup.length; i++)
		{
			
			verticalGroup.addGroup
			(
					
					panelLayout.createParallelGroup()
					.addComponent(smallestGroup[i])
					.addComponent(secondSmallestGroup[i])
					.addComponent(largestGroup[i])
					
			);		
					
			
		}
		
		for(int i = smallestGroup.length; i < secondSmallestGroup.length; i++)
{
			
			verticalGroup.addGroup
			(
					
					panelLayout.createParallelGroup()
					.addComponent(secondSmallestGroup[i])
					.addComponent(largestGroup[i])
					
			);		
					
			
		}
		
		for(int i = secondSmallestGroup.length; i < largestGroup.length; i++)
		{
			
			verticalGroup.addGroup
			(
					
					panelLayout.createParallelGroup()
					.addComponent(largestGroup[i])
					
			);		
					
			
		}
		
		
		panelLayout.setVerticalGroup
		(

				verticalGroup
				.addGroup
				(
						
						panelLayout.createParallelGroup()
						.addComponent(customCheckBox)
				)
				.addGroup
				(
						
						panelLayout.createParallelGroup()
						.addComponent(cancelButton)
						.addComponent(saveButton)
				)
				.addContainerGap(20,20)
				
		);
		
		
		jScrollPane.setViewportView(panel);
		jScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
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
							jScrollPane, 1, GroupLayout.PREFERRED_SIZE,
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

	protected void cancelButtonActionPerformed(ActionEvent evt)
	{

		frame.dispose();

	}

	protected void saveButtonActionPerformed(ActionEvent evt)
	{

		resourceSetting.setResourceSetting(1, Boolean.toString(showIndex.isSelected()));
		int count = 0;
		for(int i = 1; i < aorBookCount; i++)
		{
			
			resourceSetting.setResourceSetting(((count + 1) * 2) + 1, Boolean.toString(aorCheckBox[i].isSelected()));
			
			if(aorCheckBox[i].isSelected())
			{
				
				resourceSetting.setResourceSetting(((count + 1) * 2), source.getSourceBookAbbreviation(count));
				
			}
			else
			{
				
				resourceSetting.setResourceSetting(((count + 1) * 2), "Not Selected");
				
			}
			count++;
			
		}

		for(int i = 1; i < eoteBookCount; i++)
		{
			
			resourceSetting.setResourceSetting(((count + 1) * 2) + 1, Boolean.toString(eoteCheckBox[i].isSelected()));
			
			if(eoteCheckBox[i].isSelected())
			{
				
				resourceSetting.setResourceSetting(((count + 1) * 2), source.getSourceBookAbbreviation(count));
				
			}
			else
			{
				
				resourceSetting.setResourceSetting(((count + 1) * 2), "Not Selected");
				
			}
			count++;
			
		}
		
		for(int i = 1; i < fadBookCount; i++)
		{
			
			resourceSetting.setResourceSetting(((count + 1) * 2) + 1, Boolean.toString(fadCheckBox[i].isSelected()));
			
			if(fadCheckBox[i].isSelected())
			{
				
				resourceSetting.setResourceSetting(((count + 1) * 2), source.getSourceBookAbbreviation(count));
				
			}
			else
			{
				
				resourceSetting.setResourceSetting(((count + 1) * 2), "Not Selected");
				
			}
			count++;
			
		}
		if(customCheckBox.isSelected())
		{
			
			resourceSetting.setResourceSetting(((count + 1) * 2), "Custom");
			
		}
		else
		{
			
			resourceSetting.setResourceSetting(((count + 1) * 2), "Not Selected");
			
		}
		resourceSetting.setResourceSetting(((count + 1) * 2) + 1, Boolean.toString(customCheckBox.isSelected()));
		resourceSetting.saveResourceSettings();
		frame.dispose();

	}
}
