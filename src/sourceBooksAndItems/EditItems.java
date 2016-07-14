
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */

package sourceBooksAndItems;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import helper.Settings;
import settings.SetJLabelHeight;

/**
 *
 * @author Lepre
 */
public class EditItems extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5603344541865387369L;
	/**
	 * 
	 */
	private JButton addItemButton;
	private JPanel panel;
	private JButton cancelButton;
	private JButton editItemButton;
	private JComboBox<String> jComboBox1;
	private JInternalFrame jInternalFrame;
	private JLabel titleLabel;
	private JLabel description;
	private JScrollPane jScrollPane1;
	private JTable table;
	private JButton removeItemButton;
	private JButton saveButton;
	private JTextField searchBar;
	private JButton searchButton;
	private int rowCount;
	private int columnCount;
	private String[][] list;
	private String[] headers;
	private String[][] temporaryList;
	private String[] selectedRow;
	private int temporaryListCount;
	private String searchColumn;
	private String searchParams;
	private String itemType;
	private String lastIndex;
	String[] sourceBooks;
	int indexColumnNumber;;
	int classColumnNumber;;
	int legalityColumnNumber;;
	int extraColumnToRemove;
	int componentCount;
	int frameLastHeight;
	int frameNewHeight;

	JScrollPane addItemJScrollPane1;
	JPanel addItemJPanel2;
	JLabel addItemTitleLabel;
	JLabel addItemDescriptionLabel;
	JLabel addItemIndexLabel;
	JComboBox<String> addItemJComboBox0;
	JLabel addItemLegalityLabel;
	JComboBox<String> addItemJComboBox1;
	JLabel[] addItemJLabel;
	JTextField[] addItemJTextBox;

	JButton addItemSaveButton;
	JButton addItemCancelButton;
	JButton editItemSaveButton;
	JFrame addItemFrame;
	String[] newItem;

	int fontSize;
	String fontName;
	{
		Settings setting = new Settings();
		fontSize = setting.getSettingNumber(17);
		fontName = setting.getSettingString(26);
	}

	Font font1 = new Font(fontName, 0, (int) Math.round(fontSize * 1.5));
	Font font2 = new Font(fontName, 0, fontSize);

	main.Reader items;
	private JButton fullListButton;
	private ParallelGroup parallelMajor;
	private ParallelGroup parallelMinor;
	private SequentialGroup sequenceMajor;
	private int count;
	private Component[] inputComponent;
	private JLabel[] labelComponent;
	private JLabel addItemIndexPageLabel;
	private JTextField addItemIndexPageText;
	private SourceBooks source;
	private int rowNumber;
	private String[] searchHeaders;
	private JScrollPane jScrollPane;
	private int labelPreferredWidth;
	public void run(String itemType)
	{

		java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addPropertyChangeListener("permanentFocusOwner",
						new java.beans.PropertyChangeListener()
						{

							@Override
							public void propertyChange(
									final java.beans.PropertyChangeEvent e)
							{

								if (e.getNewValue() instanceof JTextField)
								{
									javax.swing.SwingUtilities
											.invokeLater(new Runnable()
											{

												@Override
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
		this.itemType = itemType;
		
		

		lastIndex = "Custom";
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
		

		titleLabel = new JLabel();
		description = new JLabel();
		jInternalFrame = new JInternalFrame();
		jScrollPane1 = new JScrollPane();
		table = new JTable();
		searchBar = new JTextField();
		searchButton = new JButton();
		jComboBox1 = new JComboBox<>();
		removeItemButton = new JButton();
		editItemButton = new JButton();
		addItemButton = new JButton();
		saveButton = new JButton();
		cancelButton = new JButton();
		fullListButton = new JButton();
		items = new main.Reader(itemType);
		items.loadArray();
		rowCount = items.getRowCount();
		columnCount = items.getColumnCount();
		items.sortBy("Name");
		items.sortType();
		list = items.getFullItemList();
		temporaryList = list;
		headers = items.getListHeaders();
		searchColumn = "Class";
		searchParams = itemType;

		setResizable(true);
		setLocation(0, 0);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		titleLabel.setFont(font1); // NOI18N
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setText(itemType + " List Editor");

		description.setFont(font2); // NOI18N
		description.setText("<html><p>&nbsp&nbsp&nbsp&nbsp This menu allows you to view, edit, "
				+ "add, and remove items from the " + itemType.toLowerCase() + " list."
						+ "<br>&nbsp&nbsp&nbsp&nbsp When editing items, note that the index function does not yet work. It will keep whatever index text the item already has. "
						+ "<br>&nbsp&nbsp&nbsp&nbsp It is functional for adding items, so feel free to add whatever source books you see fit."
				+ "");

		jInternalFrame.setResizable(false);
		jInternalFrame.setFocusable(false);
		jInternalFrame.setEnabled(false);
		table.setFocusable(false);
		table.setFont(font2);
		table.getTableHeader().setFont(font2);
		table.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		        	editItem();
		        }
		    }
		});
		panel = new JPanel();
		table.getTableHeader().addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{

				int col = table.columnAtPoint(e.getPoint());
				String name = table.getColumnName(col);
				items.sortBy(name);
				updateList();
			}
		});
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent e)
			{

				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				editItemButton.setEnabled(!lsm.isSelectionEmpty());
				removeItemButton.setEnabled(!lsm.isSelectionEmpty());
			}
		});

		addComponentListener(new ComponentListener()
		{

			@Override
			public void componentResized(ComponentEvent e)
			{

				componentResizeActionPerformed();

			}

			@Override
			public void componentMoved(ComponentEvent e)
			{

				componentResizeActionPerformed();

			}

			@Override
			public void componentShown(ComponentEvent e)
			{

				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e)
			{

				// TODO Auto-generated method stub

			}

		});

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setTable(list, headers);


		jScrollPane1.setViewportView(table);

		searchBar.setFont(font2); // NOI18N

		searchButton.setText("Search");
		searchButton.setFont(font2);
		searchButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				searchButtonActionPerformed(evt);
			}
		});
		{
			
			searchHeaders = new String[headers.length + 1];
			searchHeaders[0] = "All";
			for(int i = 0; i < headers.length; i++)
			{
				
				searchHeaders[i + 1] = headers[i];
				
			}
			
		}
		jComboBox1.setModel(new DefaultComboBoxModel<String>(searchHeaders));
		jComboBox1.setFont(font2);
		
		int centerPoint = Math.round((GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDisplayMode().getWidth()) / 5);

		GroupLayout jInternalFrameLayout = new GroupLayout(
				jInternalFrame.getContentPane());
		jInternalFrame.getContentPane().setLayout(jInternalFrameLayout);
		jInternalFrameLayout.setHorizontalGroup(jInternalFrameLayout
				.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(jScrollPane1)

				.addGroup(GroupLayout.Alignment.CENTER, jInternalFrameLayout.createSequentialGroup()
						.addGap(centerPoint, centerPoint, centerPoint)
						.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(searchBar, GroupLayout.PREFERRED_SIZE,
								546, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(searchButton)));
		jInternalFrameLayout.setVerticalGroup(jInternalFrameLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jInternalFrameLayout.createSequentialGroup()
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE,
								400,
								GraphicsEnvironment
										.getLocalGraphicsEnvironment()
										.getDefaultScreenDevice()
										.getDisplayMode().getWidth())
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jInternalFrameLayout
								.createParallelGroup(
										GroupLayout.Alignment.LEADING)
								.addGroup(jInternalFrameLayout
										.createParallelGroup(
												GroupLayout.Alignment.TRAILING)
										.addComponent(searchBar,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GraphicsEnvironment
														.getLocalGraphicsEnvironment()
														.getDefaultScreenDevice()
														.getDisplayMode()
														.getWidth())
										.addComponent(jComboBox1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(searchButton)))));

		jInternalFrameLayout.linkSize(SwingConstants.VERTICAL, new Component[]
		{ searchBar, jComboBox1, searchButton });
		jInternalFrame.pack();
		jInternalFrame.setVisible(true);

		removeItemButton.setText("Remove Item");
		removeItemButton.setEnabled(false);
		removeItemButton.setFont(font2);
		removeItemButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				removeItemButtonActionPerformed(evt);
			}
		});

		editItemButton.setText("Edit Item");
		editItemButton.setEnabled(false);
		editItemButton.setFont(font2);
		editItemButton.setToolTipText("(Double-Click Item)");
		editItemButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				editItem();
			}
		});

		addItemButton.setText("Add Item");
		addItemButton.setFont(font2);
		editItemButton.setToolTipText("(Ctrl + Enter)");
		addItemButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				addItem();
			}
		});

		saveButton.setBackground(new java.awt.Color(0, 255, 255));
		saveButton.setText("SAVE");
		saveButton.setFont(font2);
		saveButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				saveButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("CANCEL");
		cancelButton.setFont(font2);
		cancelButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				cancelButtonActionPerformed(evt);
			}
		});

		fullListButton.setText("FULL LIST");
		fullListButton.setFont(font2);
		fullListButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				fullListButtonActionPerformed(evt);
			}
		});
		packFrame();

		frameLastHeight = 799;
	}// </editor-fold>

	

	public void resizeColumnWidth(JTable table)
	{
		for (int row = 0; row < table.getRowCount(); row++)
		{
			int rowHeight = table.getRowHeight();

			for (int column = 0; column < table.getColumnCount(); column++)
			{
				Component comp = table.prepareRenderer(
						table.getCellRenderer(row, column), row, column);
				rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
			}

			table.setRowHeight(row, rowHeight + 10);
		}
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++)
		{
			TableColumn col = columnModel.getColumn(column);
			TableCellRenderer renderer = col.getHeaderRenderer();
			if (renderer == null)
			{
				renderer = table.getTableHeader().getDefaultRenderer();
			}
			Component comp = renderer.getTableCellRendererComponent(table,
					col.getHeaderValue(), false, false, 0, 0);
			int width = comp.getPreferredSize().width;
			for (int row = 0; row < table.getRowCount(); row++)
			{
				renderer = table.getCellRenderer(row, column);
				comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width + 10);
			columnModel.getColumn(column).setMinWidth(20);
		}

	}

	private void searchButtonActionPerformed(java.awt.event.ActionEvent evt)
	{

		searchForItems(jComboBox1.getSelectedItem().toString(),
				searchBar.getText());
	}

	private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt)
	{

		int rowNumber = table.getSelectedRow();
		String nameOfItemToRemove = table
				.getValueAt(rowNumber, items.getColumnNumber("Name"))
				.toString();
		JFrame frame = new JFrame();
		Object[] options =
		{ "<html><span style='font-size:" + fontSize + "'>Yes, please",
				"<html><span style='font-size:" + fontSize + "'>No, thanks" };
		int n = JOptionPane.showOptionDialog(frame,
				"<html><span style='font-size:" + fontSize
						+ "'>Are you sure you want to remove \""
						+ nameOfItemToRemove
						+ "\"? <br>Note: changes will not be saved until you press the \"SAVE\" button.",
				"Are you sure, though?", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, isAlwaysOnTop());

		if (n == 0)
		{
			items.removeItem(nameOfItemToRemove);
			rowCount = items.getRowCount();
			updateList();
		}
	}

	private void addItem()
	{

		indexColumnNumber = items.getColumnNumber("Index");
		classColumnNumber = items.getColumnNumber("Class");
		legalityColumnNumber = items.getColumnNumber("Legality");
		source = new SourceBooks();
		int sourceBookCount = source.getSourceBookCount();
		if (itemType.equals("Weapons"))
		{

			extraColumnToRemove = items.getColumnNumber("Type");

		}
		else
		{

			extraColumnToRemove = -2;

		}

		addItemJScrollPane1 = new JScrollPane();
		addItemJPanel2 = new JPanel();
		addItemJPanel2.setBorder(
				javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
		addItemTitleLabel = new JLabel();
		addItemDescriptionLabel = new JLabel();
		addItemIndexLabel = new JLabel();
		addItemIndexPageLabel = new JLabel("Page");
		addItemIndexPageText = new JTextField();
		addItemJComboBox0 = new JComboBox<String>();
		addItemLegalityLabel = new JLabel();
		addItemJComboBox1 = new JComboBox<String>();
		componentCount = 2;
		for (int i = 0; i < columnCount; i++)
		{

			if (i != indexColumnNumber && i != classColumnNumber
					&& i != legalityColumnNumber && i != extraColumnToRemove)
			{

				componentCount++;

			}

		}

		inputComponent = new Component[componentCount];
		labelComponent = new JLabel[componentCount];
		count = 2;

		addItemSaveButton = new JButton();
		editItemSaveButton = new JButton();
		addItemCancelButton = new JButton();
		addItemFrame = new JFrame();

		newItem = new String[columnCount];

		addItemFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addItemFrame.setResizable(false);
		addItemFrame.setLocation(100, 100);

		addItemTitleLabel.setFont(font1); // NOI18N
		addItemTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addItemTitleLabel.setText("Title");

		addItemDescriptionLabel.setFont(font2); // NOI18N
		addItemDescriptionLabel.setText("Description");

		sourceBooks = new String[sourceBookCount];

		for (int i = 0; i < sourceBookCount; i++)
		{

			sourceBooks[i] = source.getSourceBookName(i);

		}

		addItemIndexPageLabel.setFont(font2); // NOI18N
		addItemIndexPageLabel.setHorizontalAlignment(SwingConstants.TRAILING);

		addItemIndexPageText.setFont(font2); // NOI18N

		addItemIndexLabel.setFont(font2); // NOI18N
		addItemIndexLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addItemIndexLabel.setText("Index");

		addItemJComboBox0.setFont(font2); // NOI18N
		addItemJComboBox0
				.setModel(new DefaultComboBoxModel<String>(sourceBooks));
		addItemJComboBox0.setSelectedItem(lastIndex);

		addItemLegalityLabel.setFont(font2); // NOI18N
		addItemLegalityLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addItemLegalityLabel.setText("Legality");

		addItemJComboBox1.setFont(font2); // NOI18N
		addItemJComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]
		{ "Nonrestricted", "Restricted", "Lightsaber" }));
		addItemJLabel = new JLabel[columnCount];
		addItemJTextBox = new JTextField[columnCount];
		for (int i = 0; i < columnCount; i++)
		{

			if (i != indexColumnNumber && i != classColumnNumber
					&& i != legalityColumnNumber && i != extraColumnToRemove)
			{
				addItemJLabel[i] = new JLabel();
				addItemJLabel[i].setFont(font2); // NOI18N
				addItemJLabel[i]
						.setHorizontalAlignment(SwingConstants.TRAILING);
				addItemJLabel[i].setText(items.getArrayValue(0, i));
				addItemJTextBox[i] = new JTextField();
				addItemJTextBox[i].setFont(font2);
				addItemJTextBox[i].setText("");

			}
		}

		labelComponent[0] = addItemIndexLabel;
		labelComponent[1] = addItemLegalityLabel;
		inputComponent[0] = addItemJComboBox0;
		inputComponent[1] = addItemJComboBox1;
		count = 2;
		for (int i = 0; i < columnCount; i++)
		{

			if (i != indexColumnNumber && i != classColumnNumber
					&& i != legalityColumnNumber && i != extraColumnToRemove)
			{

				labelComponent[count] = addItemJLabel[i];
				labelComponent[count].setName(addItemJLabel[i].getText());
				inputComponent[count] = addItemJTextBox[i];
				inputComponent[count].setName(addItemJLabel[i].getText());
				count++;

			}

		}

		{
			String longestName = "";

			for(int i = 0; i < labelComponent.length; i++)
			{
				if(labelComponent[i].getText ().length () > longestName
						.length ())
				{
					longestName = labelComponent[i].getText ();
				}
			}
			
			JLabel test = new JLabel(longestName);
			test.setFont ( font2 );
			
			labelPreferredWidth = test.getPreferredSize ().width;
		}
		
		editItemSaveButton.setBackground(new java.awt.Color(0, 255, 255));
		editItemSaveButton.setText("SAVE");
		editItemSaveButton.setFont(font2);
		editItemSaveButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				editItemSaveButtonActionPerformed(evt);
			}
		});
		addItemSaveButton.setBackground(new java.awt.Color(0, 255, 255));
		addItemSaveButton.setText("SAVE");
		addItemSaveButton.setFont(font2);
		addItemSaveButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				addItemSaveButtonActionPerformed(evt);
			}
		});

		addItemCancelButton.setText("CANCEL");
		addItemCancelButton.setFont(font2);
		addItemCancelButton
				.addActionListener(new java.awt.event.ActionListener()
				{

					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt)
					{

						addItemCancelButtonActionPerformed(evt);
					}
				});

		
		GroupLayout addItemJPanel2Layout = new GroupLayout(addItemJPanel2);
		addItemJPanel2.setLayout(addItemJPanel2Layout);

		// asdfffffffffffffffffffffffffffffff
		parallelMinor = addItemJPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
				.addComponent(description, GroupLayout.Alignment.LEADING,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE,
						Short.MAX_VALUE)

				.addGroup(addItemJPanel2Layout.createSequentialGroup()

						.addComponent(labelComponent[1])
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(inputComponent[1])
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(labelComponent[0])
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(inputComponent[0])
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(addItemIndexPageLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(addItemIndexPageText,
								GroupLayout.PREFERRED_SIZE, 30,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								LayoutStyle.ComponentPlacement.RELATED))
				.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

		parallelMajor = addItemJPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(parallelMinor);

		count = 2;

		while (count < componentCount)
		{

			sequenceMajor = addItemJPanel2Layout.createSequentialGroup();

			for (int j = 0; j < 2; j++)
			{

				if (count >= componentCount)
				{

				}
				else
				{
					sequenceMajor.addComponent(labelComponent[count],
							GroupLayout.PREFERRED_SIZE, labelPreferredWidth,
							GroupLayout.PREFERRED_SIZE)
					.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )

							.addComponent(inputComponent[count],
									GroupLayout.PREFERRED_SIZE, 200,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED );
					count++;
				}

			}

			parallelMajor.addGroup(sequenceMajor);

		}
		addItemJPanel2Layout.setHorizontalGroup(addItemJPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(addItemJPanel2Layout.createSequentialGroup()
						.addGroup(parallelMajor.addGroup(
								GroupLayout.Alignment.TRAILING,
								addItemJPanel2Layout.createSequentialGroup()

										.addComponent(addItemCancelButton,
												GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(addItemSaveButton,
												GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)

						)

						)));

		addItemJPanel2Layout.linkSize(SwingConstants.HORIZONTAL,
				labelComponent);

		addItemJPanel2Layout.linkSize(SwingConstants.HORIZONTAL,
				inputComponent);

		sequenceMajor = addItemJPanel2Layout.createSequentialGroup()
				.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 132,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(description, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

				.addGroup(addItemJPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(addItemJPanel2Layout
								.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
								.addComponent(labelComponent[1])
								.addComponent(inputComponent[1])
								.addComponent(labelComponent[0])
								.addComponent(inputComponent[0])
								.addComponent(addItemIndexPageLabel)
								.addComponent(addItemIndexPageText)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);

		int i = 2;
		while (i < componentCount)
		{

			parallelMajor = addItemJPanel2Layout
					.createParallelGroup(GroupLayout.Alignment.BASELINE);

			for (int j = 0; j < 2; j++)
			{

				if (i >= componentCount)
				{

				}
				else
				{

					parallelMajor.addComponent(labelComponent[i]);
					parallelMajor.addComponent(inputComponent[i]);
					i++;

				}
				sequenceMajor.addGroup(parallelMajor).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED);

			}

		}

		addItemJPanel2Layout.setVerticalGroup(addItemJPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(sequenceMajor

						.addGroup(addItemJPanel2Layout
								.createParallelGroup(
										GroupLayout.Alignment.TRAILING)
								.addComponent(addItemCancelButton)
								.addComponent(addItemSaveButton))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)));

		addItemJPanel2Layout.linkSize(SwingConstants.VERTICAL, inputComponent);

		addItemJPanel2Layout.linkSize(SwingConstants.VERTICAL, labelComponent);
		

		addItemJScrollPane1.setViewportView(addItemJPanel2);

		GroupLayout layout = new GroupLayout(addItemFrame.getContentPane());
		addItemFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(addItemJScrollPane1, 0,

						GroupLayout.PREFERRED_SIZE,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice().getDisplayMode()
								.getWidth()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(
						addItemJScrollPane1, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice().getDisplayMode()
								.getHeight())));

		addItemFrame.pack();
		addItemFrame.setAlwaysOnTop(true);
		addItemFrame.setLocationRelativeTo(null);
		addItemFrame.getRootPane().setDefaultButton(addItemSaveButton);
		addItemFrame.setVisible(true);
		
		
		if(addItemJPanel2.getPreferredSize().width >= GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDisplayMode().getWidth())
		{
			addItemFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}

	}

	private void updateList()
	{

		list = items.getFullItemList();
		searchForItems(searchColumn, searchParams);


	}

	private void editItem()
	{

	

		String[] sourceBooks;
		rowNumber = items.findItem(temporaryList[table.getSelectedRow()][items.getColumnNumber("Name")]);
		if(rowNumber >= 0)
		{
			selectedRow = list[rowNumber];
		}
		else
		{
			
			javax.swing.JFrame frame = new javax.swing.JFrame();
			javax.swing.JOptionPane.showMessageDialog(frame, "Item not found.");
			frame.setVisible(true);
			selectedRow = list[0];
		}
		
		indexColumnNumber = items.getColumnNumber("Index");
		classColumnNumber = items.getColumnNumber("Class");
		legalityColumnNumber = items.getColumnNumber("Legality");
		

		SourceBooks source = new SourceBooks();
		int sourceBookCount = source.getSourceBookCount();
		if (itemType.equals("Weapons"))
		{

			extraColumnToRemove = items.getColumnNumber("Type");

		}
		else
		{

			extraColumnToRemove = -2;

		}

		addItemJScrollPane1 = new JScrollPane();
		addItemJPanel2 = new JPanel();
		addItemJPanel2.setBorder(
				javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
		addItemTitleLabel = new JLabel();
		addItemDescriptionLabel = new JLabel();
		addItemIndexLabel = new JLabel();
		addItemIndexPageLabel = new JLabel("Page");
		addItemIndexPageText = new JTextField();
		addItemJComboBox0 = new JComboBox<>();
		addItemLegalityLabel = new JLabel();
		addItemJComboBox1 = new JComboBox<>();
		componentCount = 2;
		for (int i = 0; i < columnCount; i++)
		{

			if (i != indexColumnNumber && i != classColumnNumber
					&& i != legalityColumnNumber && i != extraColumnToRemove)
			{

				componentCount++;

			}

		}

		inputComponent = new Component[componentCount];
		labelComponent = new JLabel[componentCount];
		count = 2;

		addItemSaveButton = new JButton();
		editItemSaveButton = new JButton();
		addItemCancelButton = new JButton();
		addItemFrame = new JFrame();

		newItem = new String[columnCount];

		addItemFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addItemFrame.setResizable(false);
		addItemFrame.setLocation(100, 100);

		addItemTitleLabel.setFont(font1); // NOI18N
		addItemTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addItemTitleLabel.setText("Title");

		addItemDescriptionLabel.setFont(font2); // NOI18N
		addItemDescriptionLabel.setText("Description");

		sourceBooks = new String[sourceBookCount];

		for (int i = 0; i < sourceBookCount; i++)
		{

			sourceBooks[i] = source.getSourceBookName(i);

		}

		addItemIndexPageLabel.setFont(font2); // NOI18N
		addItemIndexPageLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addItemIndexPageLabel.setText("Page");

		addItemIndexPageText.setFont(font2); // NOI18N

		addItemIndexLabel.setFont(font2); // NOI18N
		addItemIndexLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addItemIndexLabel.setText("Index");

		addItemJComboBox0.setFont(font2); // NOI18N
		addItemJComboBox0
				.setModel(new DefaultComboBoxModel<String>(sourceBooks));
		addItemJComboBox0.setSelectedItem(lastIndex);

		addItemLegalityLabel.setFont(font2); // NOI18N
		addItemLegalityLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		addItemLegalityLabel.setText("Legality");
		
		
		rowNumber = items.findItem(temporaryList[table.getSelectedRow()][items.getColumnNumber("Name")]);
		if(rowNumber >= 0)
		{
			selectedRow = list[rowNumber];
		}
		else
		{
			
			javax.swing.JFrame frame = new javax.swing.JFrame();
			javax.swing.JOptionPane.showMessageDialog(frame, "Item not found.");
			frame.setVisible(true);
			selectedRow = list[0];
		}
		addItemJComboBox1.setFont(font2); // NOI18N
		addItemJComboBox1.setModel(new DefaultComboBoxModel<String>(new String[]
		{ "Nonrestricted", "Restricted", "Lightsaber" }));
		addItemJLabel = new JLabel[columnCount];
		addItemJTextBox = new JTextField[columnCount];
		for (int i = 0; i < columnCount; i++)
		{

			if (i != indexColumnNumber && i != classColumnNumber
					&& i != legalityColumnNumber && i != extraColumnToRemove)
			{
				addItemJLabel[i] = new JLabel();
				addItemJLabel[i].setFont(font2); // NOI18N
				addItemJLabel[i]
						.setHorizontalAlignment(SwingConstants.TRAILING);
				addItemJLabel[i].setText(items.getArrayValue(0, i));
				addItemJTextBox[i] = new JTextField();
				addItemJTextBox[i].setText(selectedRow[i]);
				addItemJTextBox[i].setFont(font2);

			}
		}

		labelComponent[0] = addItemIndexLabel;
		labelComponent[1] = addItemLegalityLabel;
		inputComponent[0] = addItemJComboBox0;
		inputComponent[1] = addItemJComboBox1;
		count = 2;
		for (int i = 0; i < columnCount; i++)
		{

			if (i != indexColumnNumber && i != classColumnNumber
					&& i != legalityColumnNumber && i != extraColumnToRemove)
			{

				labelComponent[count] = addItemJLabel[i];
				labelComponent[count].setName(addItemJLabel[i].getText());
				inputComponent[count] = addItemJTextBox[i];
				inputComponent[count].setName(addItemJLabel[i].getText());
				count++;

			}

		}
		{
			String longestName = "";

			for(int i = 0; i < labelComponent.length; i++)
			{
				if(labelComponent[i].getText ().length () > longestName
						.length ())
				{
					longestName = labelComponent[i].getText ();
				}
			}
			
			JLabel test = new JLabel(longestName);
			test.setFont ( font2 );
			
			labelPreferredWidth = test.getPreferredSize ().width;
		}
		editItemSaveButton.setBackground(new java.awt.Color(0, 255, 255));
		editItemSaveButton.setText("SAVE");
		editItemSaveButton.setFont(font2);
		editItemSaveButton.addActionListener(new java.awt.event.ActionListener()
		{

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{

				editItemSaveButtonActionPerformed(evt);
			}
		});

		addItemCancelButton.setText("CANCEL");
		addItemCancelButton.setFont(font2);
		addItemCancelButton
				.addActionListener(new java.awt.event.ActionListener()
				{

					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt)
					{

						addItemCancelButtonActionPerformed(evt);
					}
				});

		SetJLabelHeight.setJLabelHeight(description.getPreferredSize().width, description.getText(), description);
		GroupLayout addItemJPanel2Layout = new GroupLayout(addItemJPanel2);
		addItemJPanel2.setLayout(addItemJPanel2Layout);

		// asdfffffffffffffffffffffffffffffff
		parallelMinor = addItemJPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
				.addComponent(description, GroupLayout.Alignment.LEADING,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE,
						Short.MAX_VALUE)

				.addGroup(addItemJPanel2Layout.createSequentialGroup()

						.addComponent(labelComponent[1])
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(inputComponent[1])
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(labelComponent[0])
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(inputComponent[0])
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(addItemIndexPageLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

						.addComponent(addItemIndexPageText,
								GroupLayout.PREFERRED_SIZE, 30,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								LayoutStyle.ComponentPlacement.RELATED))
				.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

		parallelMajor = addItemJPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(parallelMinor);

		count = 2;

		while (count < componentCount)
		{

			sequenceMajor = addItemJPanel2Layout.createSequentialGroup();

			for (int j = 0; j < 2; j++)
			{

				if (count >= componentCount)
				{

				}
				else
				{
					sequenceMajor.addComponent(labelComponent[count],
							GroupLayout.PREFERRED_SIZE, labelPreferredWidth,
							GroupLayout.PREFERRED_SIZE)
					.addPreferredGap ( LayoutStyle.ComponentPlacement.RELATED )

							.addComponent(inputComponent[count],
									GroupLayout.PREFERRED_SIZE, 200,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap ( LayoutStyle.ComponentPlacement.UNRELATED );
					count++;
				}

			}

			parallelMajor.addGroup(sequenceMajor);

		}

		addItemJPanel2Layout.setHorizontalGroup(addItemJPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(addItemJPanel2Layout.createSequentialGroup()
						.addGroup(parallelMajor.addGroup(
								addItemJPanel2Layout.createSequentialGroup()

										.addComponent(addItemCancelButton,
												GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(editItemSaveButton,
												GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)

						)

						)));

		addItemJPanel2Layout.linkSize(SwingConstants.HORIZONTAL,
				labelComponent);

		addItemJPanel2Layout.linkSize(SwingConstants.HORIZONTAL,
				inputComponent);

		sequenceMajor = addItemJPanel2Layout.createSequentialGroup()
				.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 132,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(description, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

				.addGroup(addItemJPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(addItemJPanel2Layout
								.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
								.addComponent(labelComponent[1])
								.addComponent(inputComponent[1])
								.addComponent(labelComponent[0])
								.addComponent(inputComponent[0])
								.addComponent(addItemIndexPageLabel)
								.addComponent(addItemIndexPageText)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);

		int i = 2;
		while (i < componentCount)
		{

			parallelMajor = addItemJPanel2Layout
					.createParallelGroup(GroupLayout.Alignment.BASELINE);

			for (int j = 0; j < 2; j++)
			{

				if (i >= componentCount)
				{

				}
				else
				{

					parallelMajor.addComponent(labelComponent[i]);
					parallelMajor.addComponent(inputComponent[i]);
					i++;

				}
				sequenceMajor.addGroup(parallelMajor).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED);

			}

		}

		addItemJPanel2Layout.setVerticalGroup(addItemJPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(sequenceMajor

						.addGroup(addItemJPanel2Layout
								.createParallelGroup(
										GroupLayout.Alignment.TRAILING)
								.addComponent(addItemCancelButton)
								.addComponent(editItemSaveButton))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)));

		addItemJPanel2Layout.linkSize(SwingConstants.VERTICAL, inputComponent);

		addItemJPanel2Layout.linkSize(SwingConstants.VERTICAL, labelComponent);

		addItemJScrollPane1.setViewportView(addItemJPanel2);

		GroupLayout layout = new GroupLayout(addItemFrame.getContentPane());
		addItemFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(addItemJScrollPane1, 0,
						GroupLayout.PREFERRED_SIZE,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice().getDisplayMode()
								.getWidth()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(
						addItemJScrollPane1, GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice().getDisplayMode()
								.getHeight())));

		addItemFrame.pack();
		addItemFrame.setAlwaysOnTop(true);
		addItemFrame.setLocationRelativeTo(null);
		addItemFrame.getRootPane().setDefaultButton(editItemSaveButton);
		addItemFrame.setVisible(true);
		
		if(addItemJPanel2.getPreferredSize().width >= GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDisplayMode().getWidth())
		{
			addItemFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		
	}

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt)
	{

		Object[] options =
		{ "<html><span style='font-size:" + fontSize + "'>Yes, please",
				"<html><span style='font-size:" + fontSize + "'>No, thanks" };
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"<html><span style='font-size:" + fontSize
						+ "'>Are you sure you want to overwrite \"" + itemType
						+ ".csv\"?",
				"Are you sure, though?", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, isAlwaysOnTop());

		if (n == 0)
		{
			items.saveList();
			dispose();
		}
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)
	{

		dispose();

	}

	private void editItemSaveButtonActionPerformed(
			java.awt.event.ActionEvent evt)
	{

		newItem[0] = itemType;
		if (addItemContainsBlanks())
		{
			JOptionPane.showMessageDialog(addItemFrame,
					"<html><span style='font-size:" + fontSize
							+ "'>Only \"Special\" and \"Page\" may be left blank. <br>To have a null value, use \"-\" instead.");
		}
		else
		{
			count = 1;
			newItem[1] = addItemJComboBox1.getSelectedItem().toString();

			count = 2;

			

			for (int i = 0; i < columnCount; i++)
			{

				if (i != indexColumnNumber && i != classColumnNumber
						&& i != legalityColumnNumber)
				{
					if (count != extraColumnToRemove)
					{

						newItem[i] = addItemJTextBox[i].getText();

						count++;

					}
					else
					{
						newItem[i] = newItem[items.getColumnNumber("Skill")];

						count++;
					}
				}

			}
			newItem[count] = selectedRow[count];
			

			items.editRow(selectedRow, newItem);
			lastIndex = (String)addItemJComboBox0.getSelectedItem();
			addItemFrame.dispose();

			items.sortBy("Index");
			items.sortBy("Type");
			items.sortBy("Name");
			updateList();

		}

	}

	private void addItemSaveButtonActionPerformed(
			java.awt.event.ActionEvent evt)
	{

		newItem[0] = itemType;
		if (addItemContainsBlanks())
		{
			JOptionPane.showMessageDialog(addItemFrame,
					"<html><span style='font-size:" + fontSize
							+ "'>Only \"Special\" and \"Page\" may be left blank. <br>To have a null value, use \"-\" instead.");
		}
		else
		{
			count = 1;
			newItem[1] = addItemJComboBox1.getSelectedItem().toString();

			count = 2;

			

			for (int i = 0; i < columnCount; i++)
			{

				if (i != indexColumnNumber && i != classColumnNumber
						&& i != legalityColumnNumber)
				{
					if (count != extraColumnToRemove)
					{

						newItem[i] = addItemJTextBox[i].getText();

						count++;

					}
					else
					{
						newItem[i] = newItem[items.getColumnNumber("Skill")];

						count++;
					}
				}

			}
			newItem[count] = source.getSourceBookAbbreviation(source.getRowNumber(addItemJComboBox0.getSelectedItem().toString()));
			if (!addItemIndexPageText.getText().equals(""))
			{

				newItem[count] += ": " + addItemIndexPageText.getText();

			}

			items.addItem(newItem);
			lastIndex = newItem[count];
			addItemFrame.dispose();

			items.sortBy("Index");
			items.sortBy("Type");
			items.sortBy("Name");
			updateList();

		}

	}

	private void addItemCancelButtonActionPerformed(
			java.awt.event.ActionEvent evt)
	{

		addItemFrame.dispose();

	}

	private void fullListButtonActionPerformed(java.awt.event.ActionEvent evt)
	{

		searchColumn = "Class";
		searchParams = itemType;
		setTable(list, headers);

		temporaryList = list;

	}

	private void searchForItems(String columnHeader, String searchParameters)
	{

		searchColumn = columnHeader;
		searchParams = searchParameters;

		int columnNumber = items.getColumnNumber(columnHeader);
		temporaryListCount = 0;

		if(columnHeader.equals("All"))
		{
			
			for(int i = 0; i < rowCount - 1; i++)
			{
				
				for(int j = 0; j < columnCount; j++)
				{
					
					if(list[i][j].toLowerCase().contains(searchParameters.toLowerCase()))
					{
						
						temporaryListCount++;
						break;
						
					}
					
				}
				
			}
			
			temporaryList = new String[temporaryListCount][columnCount];
			temporaryListCount = 0;
			
			for(int i = 0; i < rowCount - 1; i++)
			{
				
				for(int j = 0; j < columnCount; j++)
				{
					
					if(list[i][j].toLowerCase().contains(searchParameters.toLowerCase()))
					{
						
						temporaryList[temporaryListCount] = list[i];
						temporaryListCount++;
						break;
						
					}
					
				}
				
			}
			
		}
		else if (columnHeader.equals("Index") || columnHeader.equals("Name"))
		{
			for (int i = 0; i < rowCount - 1; i++)
			{

				if (list[i][columnNumber].toLowerCase()
						.contains(searchParameters.toLowerCase()))
				{

					temporaryList[temporaryListCount] = list[i];
					temporaryListCount++;

				}

			}

			temporaryList = new String[temporaryListCount][columnCount];
			temporaryListCount = 0;
			for (int i = 0; i < rowCount - 1; i++)
			{

				if (list[i][columnNumber].toLowerCase()
						.contains(searchParameters.toLowerCase()))
				{

					temporaryList[temporaryListCount] = list[i];
					temporaryListCount++;

				}

			}
		}
		else
		{
			for (int i = 0; i < rowCount - 1; i++)
			{

				if (list[i][columnNumber].toLowerCase()
						.equals(searchParameters.toLowerCase()))
				{

					temporaryListCount++;

				}

			}

			temporaryList = new String[temporaryListCount][columnCount];
			temporaryListCount = 0;
			for (int i = 0; i < rowCount - 1; i++)
			{

				if (list[i][columnNumber].toLowerCase()
						.equals(searchParameters.toLowerCase()))
				{

					temporaryList[temporaryListCount] = list[i];
					temporaryListCount++;

				}

			}
		}

		setTable(temporaryList, headers);

	}

	public void setTable(String[][] itemList, String[] headers)
	{

		table.setModel(
				new javax.swing.table.DefaultTableModel(itemList, headers

				){/**
					 * 
					 */
					private static final long serialVersionUID = 5740900368798721225L;

				@Override
					public boolean isCellEditable(int row, int col) {
				    return false;

				}});

		resizeColumnWidth(table);
	}

	public boolean addItemContainsBlanks()
	{

		boolean containsBlanks = false;

		for (int i = 0; i < columnCount; i++)
		{

			if (i != indexColumnNumber && i != classColumnNumber
					&& i != legalityColumnNumber && i != extraColumnToRemove
					&& i != items.getColumnNumber("Special"))
			{
				if (addItemJTextBox[i].getText().replace("\"", "\\\"")
						.equals(""))
				{

					containsBlanks = true;

				}

			}

		}

		return containsBlanks;

	}

	public void componentResizeActionPerformed()
	{


	}

	public void packFrame()
	{

		GroupLayout panelLayout = new GroupLayout(panel);
		panel.setLayout(panelLayout);
		
		parallelMajor = panelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(titleLabel,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice().getDisplayMode().getWidth() - 50,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice().getDisplayMode().getWidth() - 50,
						GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice().getDisplayMode().getWidth() - 50)
				.addComponent(description,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addComponent(jInternalFrame)
				.addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
						.addComponent(fullListButton,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(removeItemButton,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(editItemButton,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(addItemButton,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
						.addGap(40,40,40)
						.addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
								.addComponent(saveButton,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cancelButton,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE));
		
		panelLayout.setHorizontalGroup(parallelMajor);
		
		panelLayout.linkSize(saveButton, cancelButton);
		panelLayout.linkSize(addItemButton, editItemButton, fullListButton, removeItemButton);
		
		sequenceMajor = panelLayout.createSequentialGroup()
				.addComponent(titleLabel)
				.addComponent(description)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(jInternalFrame)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(panelLayout.createParallelGroup()
						.addComponent(fullListButton)
						.addComponent(editItemButton)
						.addComponent(addItemButton)
						.addComponent(removeItemButton))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(panelLayout.createParallelGroup()
						.addComponent(saveButton)
						.addComponent(cancelButton));
		
		panelLayout.setVerticalGroup(sequenceMajor);
		
		jScrollPane = new JScrollPane();
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
					.createParallelGroup(GroupLayout.Alignment.CENTER)
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
			setVisible(true);
			setAlwaysOnTop(true);
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
			setAlwaysOnTop(false);
		}

		setExtendedState(Frame.MAXIMIZED_BOTH);
		getRootPane().setDefaultButton(saveButton);
		jInternalFrame.getRootPane().setDefaultButton(searchButton);

		addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{

				if(e.equals(KeyEvent.VK_SPACE))
				{
					
					addItem();
					System.out.println("SPACEBAR PRESSED");
					
				}
				
			}

			@Override
			public void keyPressed(KeyEvent e)
			{

				if(e.equals(KeyEvent.VK_SPACE))
				{
					
					addItem();
					System.out.println("SPACEBAR PRESSED");
					
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e)
			{

				if(e.equals(KeyEvent.VK_SPACE))
				{
					
					addItem();
					System.out.println("SPACEBAR PRESSED");
					
				}
				
			}
	});
		
	}
}
