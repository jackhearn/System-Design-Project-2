import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class HearnBookFaceFrame extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JMenu mnTools = new JMenu("Tools");
	private final JMenuItem mntmAddBook = new JMenuItem("Add Book");
	private final JMenu mnHelp = new JMenu("Help");
	private final JLabel lblCurrentBookInventory = new JLabel("Current Book Face Inventory");
	private final JTable InventoryTB = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton btnSetDefault = new JButton("Set Default");
	public static String baseQuery = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, "
			+ "MinQuant FROM Inventory WHERE 1 = 1";
	private final JMenu mnSetSort = new JMenu("Set Sort");
	private final JMenuItem mntmByBookName = new JMenuItem("By Book Name");
	private final JMenuItem mntmByRetailPrice = new JMenuItem("By Retail Price");
	private final JMenuItem mntmByCategory = new JMenuItem("By Category");
	private final JMenu mnSetFilter = new JMenu("Set Filter");
	private final JMenuItem mntmByRetailPrice_1 = new JMenuItem("By Retail Price");
	private final JMenuItem mntmByCategory_1 = new JMenuItem("By Category");
	public static String priceSort = " ";
	public static String finalQuery = "";
	public static String checked = " ";
	public static String sortBook = "";
	public static String retailPrice = "";
	public static String category = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HearnBookFaceFrame frame = new HearnBookFaceFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HearnBookFaceFrame() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				do_this_windowGainedFocus(arg0);
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		jbInit();
		queryUpdate(baseQuery);
	}
	private void jbInit() {
		setTitle("HearnBookFace");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 413);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmExit_actionPerformed(e);
			}
		});
		
		mnFile.add(mntmExit);
		
		menuBar.add(mnTools);
		mntmAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmAddBook_actionPerformed(e);
			}
		});
		
		mnTools.add(mntmAddBook);
		
		mnTools.add(mnSetSort);
		mntmByBookName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmByBookName_actionPerformed(e);
			}
		});
		
		mnSetSort.add(mntmByBookName);
		mntmByRetailPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmByRetailPrice_actionPerformed(e);
			}
		});
		
		mnSetSort.add(mntmByRetailPrice);
		mntmByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmByCategory_actionPerformed(e);
			}
		});
		
		mnSetSort.add(mntmByCategory);
		
		mnTools.add(mnSetFilter);
		mntmByRetailPrice_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmByRetailPrice_1_actionPerformed(e);
			}
		});
		
		mnSetFilter.add(mntmByRetailPrice_1);
		mntmByCategory_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmByCategory_1_actionPerformed(e);
			}
		});
		
		mnSetFilter.add(mntmByCategory_1);
		
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblCurrentBookInventory.setFont(new Font("Arial", Font.BOLD, 22));
		lblCurrentBookInventory.setBounds(187, 0, 303, 44);
		
		contentPane.add(lblCurrentBookInventory);
		scrollPane.setBounds(10, 50, 703, 292);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(InventoryTB);
		InventoryTB.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"BookID", "BookName", "AuthorName", "Category", "WholesalePrice", "RetailPrice", "QOH", "MinQuant"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Double.class, Double.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		InventoryTB.getColumnModel().getColumn(0).setPreferredWidth(55);
		InventoryTB.getColumnModel().getColumn(1).setPreferredWidth(96);
		InventoryTB.getColumnModel().getColumn(2).setPreferredWidth(100);
		btnSetDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnSetDefault_actionPerformed(arg0);
			}
		});
		btnSetDefault.setBounds(552, 7, 131, 31);
		
		contentPane.add(btnSetDefault);
	}
	public void queryUpdate(String s) {
		ResultSet rs = null;
		Statement stmt = null;
		// establish the connection
		try {
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/HearnBookFace.accdb");
			// Create the Statement
			stmt = conn.createStatement();
			// Print statement to console 
			System.out.println(s);
			// Execute the statement
			rs = stmt.executeQuery(s);
			// Process your result
			// remove previously added rows
			while(InventoryTB.getRowCount() > 0) {
				((DefaultTableModel) InventoryTB.getModel()).removeRow(0);
			}
			
			int col = rs.getMetaData().getColumnCount();
			while (rs.next()){
				Object[] entry = new Object[col];
				//get record fields 
				for (int i = 0; i < col; i++) {
					entry[i] = rs.getObject(i + 1);
				}
				//insert entry into food 
				((DefaultTableModel) InventoryTB.getModel()).insertRow(rs.getRow() - 1, entry);
			}
			// clean up 
			rs.close();
			conn.close();
			
		} catch (SQLException ex)
		{
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("Vendor Error: " + ex.getErrorCode());
			ex.printStackTrace();
		} 
			
	}	
	protected void do_btnSetDefault_actionPerformed(ActionEvent arg0) {
		queryUpdate(baseQuery);
	}
	protected void do_mntmByRetailPrice_1_actionPerformed(ActionEvent e) {
		HearnRetailPrice newFrame = new HearnRetailPrice();
		newFrame.setVisible(true);
	}
	protected void do_mntmAddBook_actionPerformed(ActionEvent e) {
		AddJFrame newFrame = new AddJFrame();
		newFrame.setVisible(true);
	}
	protected void do_mntmByCategory_1_actionPerformed(ActionEvent e) {
		HearnCatsFilter newFrame = new HearnCatsFilter();
		newFrame.setVisible(true);
	}
	protected void do_this_windowGainedFocus(WindowEvent arg0) {
		finalQuery();
	}
	protected void do_mntmExit_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	protected void do_mntmByBookName_actionPerformed(ActionEvent e) {
		HearnBookFaceFrame.sortBook = "";
		HearnBookFaceFrame.retailPrice = "";
		HearnBookFaceFrame.category = "";
		HearnBookFaceFrame.sortBook = " ORDER BY BookName ";
		finalQuery();
	}
	protected void do_mntmByRetailPrice_actionPerformed(ActionEvent e) {
		HearnBookFaceFrame.sortBook = "";
		HearnBookFaceFrame.retailPrice = "";
		HearnBookFaceFrame.category = "";
		HearnBookFaceFrame.retailPrice = " ORDER BY RetailPrice ";
		finalQuery();
	}
	protected void do_mntmByCategory_actionPerformed(ActionEvent e) {
		HearnBookFaceFrame.sortBook = "";
		HearnBookFaceFrame.retailPrice = "";
		HearnBookFaceFrame.category = "";
		HearnBookFaceFrame.retailPrice = " ORDER BY Category ";
		finalQuery();
	}
	private void finalQuery(){
		finalQuery = baseQuery + priceSort + checked + sortBook + retailPrice + category;
		queryUpdate(finalQuery);
		System.out.println(finalQuery);
	}
	
	}
