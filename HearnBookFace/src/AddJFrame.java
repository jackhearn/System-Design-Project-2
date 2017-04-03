import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddJFrame extends JFrame {
	NumberFormat numFormat = NumberFormat.getNumberInstance();
	private JPanel contentPane;
	private final JLabel lblBookId = new JLabel("Book ID:");
	private final JLabel lblBookName = new JLabel("Book Name:");
	private final JLabel lblAuthorName = new JLabel("Author Name:");
	private final JLabel lblCategory = new JLabel("Category:");
	private final JLabel lblWholeSalePrice = new JLabel("Whole Sale Price:");
	private final JLabel lblRetailPrice = new JLabel("Retail Price:");
	private final JLabel lblQoh = new JLabel("QOH:");
	private final JLabel lblMinQuantity = new JLabel("Min Quantity:");
	private final JFormattedTextField bookIDFTF = new JFormattedTextField(numFormat);
	private final JFormattedTextField bookNameFTF = new JFormattedTextField();
	private final JFormattedTextField authornameFTF = new JFormattedTextField();
	private final JComboBox categoryCB = new JComboBox();
	private final JFormattedTextField wholeSalePriceFTF = new JFormattedTextField(numFormat);
	private final JFormattedTextField retailPriceFTF = new JFormattedTextField(numFormat);
	private final JFormattedTextField qohFTF = new JFormattedTextField(numFormat);
	private final JFormattedTextField minQuantityFTF = new JFormattedTextField(numFormat);
	private final JButton btnOk = new JButton("OK");
	private final JButton btnCancel = new JButton("Cancel");

	/**
	 * Create the frame.
	 */
	public AddJFrame() {
		jbInit();
	}
	private void jbInit() {
		setTitle("Add Books");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBookId.setBounds(17, 19, 82, 23);
		
		contentPane.add(lblBookId);
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBookName.setBounds(17, 43, 82, 23);
		
		contentPane.add(lblBookName);
		lblAuthorName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAuthorName.setBounds(17, 67, 82, 23);
		
		contentPane.add(lblAuthorName);
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(17, 91, 82, 23);
		
		contentPane.add(lblCategory);
		lblWholeSalePrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWholeSalePrice.setBounds(17, 115, 102, 23);
		
		contentPane.add(lblWholeSalePrice);
		lblRetailPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRetailPrice.setBounds(17, 139, 82, 23);
		
		contentPane.add(lblRetailPrice);
		lblQoh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQoh.setBounds(17, 163, 82, 23);
		
		contentPane.add(lblQoh);
		lblMinQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMinQuantity.setBounds(17, 187, 82, 23);
		
		contentPane.add(lblMinQuantity);
		bookIDFTF.setBounds(125, 17, 113, 23);
		
		contentPane.add(bookIDFTF);
		bookNameFTF.setBounds(125, 43, 113, 23);
		
		contentPane.add(bookNameFTF);
		authornameFTF.setBounds(125, 67, 113, 23);
		
		contentPane.add(authornameFTF);
		categoryCB.setModel(new DefaultComboBoxModel(new String[] {"Humor", "Biography", "Autobiography", "Literature", "Mystery", "GraphicNovel", "YoungAdult", "Romance", "SciFi", "Other"}));
		categoryCB.setBounds(125, 91, 122, 23);
		
		contentPane.add(categoryCB);
		wholeSalePriceFTF.setBounds(125, 115, 113, 23);
		
		contentPane.add(wholeSalePriceFTF);
		retailPriceFTF.setBounds(125, 139, 113, 23);
		
		contentPane.add(retailPriceFTF);
		qohFTF.setBounds(125, 163, 113, 23);
		
		contentPane.add(qohFTF);
		minQuantityFTF.setBounds(125, 187, 113, 23);
		
		contentPane.add(minQuantityFTF);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnOk_actionPerformed(e);
			}
		});
		btnOk.setBounds(17, 217, 131, 31);
		
		contentPane.add(btnOk);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancel_actionPerformed(e);
			}
		});
		btnCancel.setBounds(279, 217, 131, 31);
		
		contentPane.add(btnCancel);
	}

	public void addfunction(){
		ResultSet rs = null;
		Statement stmt = null;
		String insertQuery = null;
		String duplicateCheck = null;
		String duplCheck = null;
		String addQuery = null;
		// make sure to check emty
		
			
		// establish the connection
		try {
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/HearnBookFace.accdb");
			// Create the Statement
			stmt = conn.createStatement();
			//dublicate check
			duplCheck = "SELECT * FROM Inventory WHERE Inventory.BookName = " + "'" + bookNameFTF.getText().trim() + "' " +
					" OR Inventory.BookID = " + bookIDFTF.getValue();
			// Print statement to console 
			System.out.println(duplCheck);
			// Execute the statement
			rs = stmt.executeQuery(duplCheck);
			// Process your result
			//Pop up that says that you entered in duplicate data
			if(rs.next()) {
				JOptionPane.showMessageDialog(this, "A BookID Or BookName has already been entered");
			} else {
				//time to insert data
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				addQuery = "INSERT INTO Inventory (BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant) VALUES ( ";
				addQuery += bookIDFTF.getValue() + ",";
				addQuery += "'" + bookNameFTF.getText().trim() + "',";
				addQuery += "'" + authornameFTF.getText().trim() + "',";
				addQuery += "'" + categoryCB.getSelectedItem().toString() + "',";
				addQuery += wholeSalePriceFTF.getValue() + ", ";
				addQuery += retailPriceFTF.getValue() + ", ";
				addQuery += qohFTF.getValue() + ", ";
				addQuery += minQuantityFTF.getValue() + ")";
				
				System.out.println(duplCheck);
				
				//Run Query
				
				if(stmt.executeUpdate(addQuery) != 0) {
					System.out.println("Insert Good");
					} else {
						System.out.println("Insert Bad");
					}
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
	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	protected void do_btnOk_actionPerformed(ActionEvent e) {
		addfunction();
		this.dispose();
	}
}
