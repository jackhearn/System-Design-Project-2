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

public class HearnBookFaceFrame extends JFrame {

	private JPanel contentPane;
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JMenu mnTools = new JMenu("Tools");
	private final JMenuItem mntmAddBook = new JMenuItem("Add Book");
	private final JMenuItem mntmSetSort = new JMenuItem("Set Sort");
	private final JMenuItem mntmSetFilter = new JMenuItem("Set Filter");
	private final JMenu mnHelp = new JMenu("Help");
	private final JLabel lblCurrentBookInventory = new JLabel("Current Book Face Inventory");
	private final JTable InventoryTB = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton btnSetDefault = new JButton("Set Default");
	public static String baseQuery = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, "
			+ "MinQuant FROM Inventory WHERE 1 = 1";

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
		jbInit();
	}
	private void jbInit() {
		setTitle("HearnBookFace");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 413);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		
		mnFile.add(mntmExit);
		
		menuBar.add(mnTools);
		
		mnTools.add(mntmAddBook);
		
		mnTools.add(mntmSetSort);
		
		mnTools.add(mntmSetFilter);
		
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
	}
