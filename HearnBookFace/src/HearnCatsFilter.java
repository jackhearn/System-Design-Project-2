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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class HearnCatsFilter extends JFrame {

	private JPanel contentPane;
	private final JLabel lblWhatCategorysWould = new JLabel("What category(s) would you like to fliter by?");
	private final JCheckBox chckbxHumor = new JCheckBox("Humor");
	private final JCheckBox chckbxBiography = new JCheckBox("Biography");
	private final JCheckBox chckbxAutobiography = new JCheckBox("Autobiography");
	private final JCheckBox chckbxLiterature = new JCheckBox("Literature");
	private final JCheckBox chckbxMystery = new JCheckBox("Mystery");
	private final JCheckBox chckbxGraphicnovel = new JCheckBox("GraphicNovel");
	private final JCheckBox chckbxYoungadult = new JCheckBox("YoungAdult");
	private final JCheckBox chckbxScifi = new JCheckBox("SciFi");
	private final JCheckBox chckbxOther = new JCheckBox("Other");
	private final JCheckBox chckbxRomance = new JCheckBox("Romance");
	private final JButton btnok = new JButton("OK");
	private final JButton btnCancel = new JButton("Cancel");
	
	/**
	 * Create the frame.
	 */
	public HearnCatsFilter() {
		jbInit();
	}
	private void jbInit() {
		setTitle("Category Filter By");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblWhatCategorysWould.setBounds(17, 0, 383, 23);
		
		contentPane.add(lblWhatCategorysWould);
		chckbxHumor.setBounds(13, 40, 129, 36);
		
		contentPane.add(chckbxHumor);
		chckbxBiography.setBounds(13, 70, 129, 36);
		
		contentPane.add(chckbxBiography);
		chckbxAutobiography.setBounds(13, 100, 155, 36);
		
		contentPane.add(chckbxAutobiography);
		chckbxLiterature.setBounds(13, 130, 129, 36);
		
		contentPane.add(chckbxLiterature);
		chckbxMystery.setBounds(13, 160, 129, 36);
		
		contentPane.add(chckbxMystery);
		chckbxGraphicnovel.setBounds(240, 40, 160, 36);
		
		contentPane.add(chckbxGraphicnovel);
		chckbxYoungadult.setBounds(240, 70, 129, 36);
		
		contentPane.add(chckbxYoungadult);
		chckbxScifi.setBounds(240, 130, 129, 36);
		
		contentPane.add(chckbxScifi);
		chckbxOther.setBounds(240, 160, 129, 36);
		
		contentPane.add(chckbxOther);
		chckbxRomance.setBounds(240, 100, 129, 36);
		
		contentPane.add(chckbxRomance);
		btnok.setBounds(30, 200, 131, 31);
		
		contentPane.add(btnok);
		btnCancel.setBounds(229, 200, 131, 31);
		
		contentPane.add(btnCancel);
	}
	public void checkBoxCheck(){
		String checked = null;
		if (chckbxHumor.isSelected()){
			checked += " OR Category = Humor";
			}
		if (chckbxBiography.isSelected()){
			checked += " OR Category = Biography";
		}
		if (chckbxAutobiography.isSelected()){
			checked += " OR Category = Autobiography";
		}
		if (chckbxHumor.isSelected() == false && chckbxBiography.isSelected() == false && chckbxAutobiography.isSelected() == false){
			checked = "";
		}
		
	}
}
