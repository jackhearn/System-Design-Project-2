import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AddJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AddJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public void addfunction(){
		ResultSet rs = null;
		Statement stmt = null;
		String insertQuery = null;
		String duplicateCheck = null;
		// make sure to check emty
		
			
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
}
