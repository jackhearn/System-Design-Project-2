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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

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
	private final JTable table = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton btnSetDefault = new JButton("Set Default");

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
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"BookID", "BookName", "AuthorName", "Category", "WholesalePrice", "RetailPrice", "QOH", "MinQuant"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Object.class, Object.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(96);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		btnSetDefault.setBounds(552, 7, 131, 31);
		
		contentPane.add(btnSetDefault);
	}
}