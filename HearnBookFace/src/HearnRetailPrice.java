import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class HearnRetailPrice extends JFrame {
	NumberFormat numFormat = NumberFormat.getNumberInstance();
	private JPanel contentPane;
	private final JRadioButton rdbtnLessThan = new JRadioButton("Less Than");
	private final JRadioButton rdbtnMoreThan = new JRadioButton("More than");
	private final JRadioButton rdbtnInBetween = new JRadioButton("In Between");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JFormattedTextField lessthanFTF = new JFormattedTextField(numFormat);
	private final JFormattedTextField morethanFTF = new JFormattedTextField(numFormat);
	private final JFormattedTextField inBetween1FTF = new JFormattedTextField(numFormat);
	private final JButton btnOk = new JButton("OK");
	private final JButton btnCancel = new JButton("Cancel");
	private final JLabel lblAnd = new JLabel("And");
	private final JFormattedTextField inBetween2FTF = new JFormattedTextField();

	/**
	 * Create the frame.
	 */
	public HearnRetailPrice() {
		jbInit();
	}
	private void jbInit() {
		setTitle("Filter By Price");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		buttonGroup.add(rdbtnLessThan);
		rdbtnLessThan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnLessThan.setBounds(13, 29, 91, 31);
		
		contentPane.add(rdbtnLessThan);
		buttonGroup.add(rdbtnMoreThan);
		rdbtnMoreThan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnMoreThan.setBounds(13, 66, 91, 31);
		
		contentPane.add(rdbtnMoreThan);
		buttonGroup.add(rdbtnInBetween);
		rdbtnInBetween.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnInBetween.setBounds(13, 103, 102, 31);
		
		contentPane.add(rdbtnInBetween);
		lessthanFTF.setBounds(130, 27, 102, 29);
		
		contentPane.add(lessthanFTF);
		morethanFTF.setBounds(130, 64, 102, 29);
		
		contentPane.add(morethanFTF);
		inBetween1FTF.setBounds(130, 101, 102, 29);
		
		contentPane.add(inBetween1FTF);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnOk_actionPerformed(e);
			}
		});
		btnOk.setBounds(45, 162, 131, 31);
		
		contentPane.add(btnOk);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancel_actionPerformed(e);
			}
		});
		btnCancel.setBounds(237, 162, 131, 31);
		
		contentPane.add(btnCancel);
		lblAnd.setBounds(236, 104, 42, 23);
		
		contentPane.add(lblAnd);
		inBetween2FTF.setBounds(277, 101, 102, 29);
		
		contentPane.add(inBetween2FTF);
	}
	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	protected void do_btnOk_actionPerformed(ActionEvent e) {
		if(rdbtnLessThan.isSelected()){
			HearnBookFaceFrame.priceSort += " < " + lessthanFTF.getValue() + " ";
		} else if (rdbtnMoreThan.isSelected()){
			HearnBookFaceFrame.priceSort += " > " + morethanFTF.getValue() + " ";
		} else if (rdbtnInBetween.isSelected()) { 
			HearnBookFaceFrame.priceSort += "BETWEEN" + inBetween1FTF.getValue() + " AND " + inBetween2FTF.getValue(); 
		}
	}
}
