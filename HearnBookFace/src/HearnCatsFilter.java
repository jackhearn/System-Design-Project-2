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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		btnok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnok_actionPerformed(e);
			}
		});
		btnok.setBounds(30, 200, 131, 31);
		
		contentPane.add(btnok);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancel_actionPerformed(e);
			}
		});
		btnCancel.setBounds(229, 200, 131, 31);
		
		contentPane.add(btnCancel);
	}
	public void checkBoxCheck(){
		HearnBookFaceFrame.checked = "";
		if(chckbxHumor.isSelected() || chckbxBiography.isSelected() || chckbxAutobiography.isSelected() || chckbxLiterature.isSelected()
				|| chckbxMystery.isSelected() || chckbxGraphicnovel.isSelected() || chckbxYoungadult.isSelected() || chckbxScifi.isSelected()
				|| chckbxRomance.isSelected() || chckbxOther.isSelected()){
			HearnBookFaceFrame.checked += "AND ( 1 = 0";
		}
		
		if (chckbxHumor.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'Humor'";
			}
		if (chckbxBiography.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'Biography'";
		}
		if (chckbxAutobiography.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'Autobiography'";
			}
		if(chckbxLiterature.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'Literature'";
		}
		if(chckbxMystery.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'Mystery'";
		}
		if(chckbxGraphicnovel.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'GraphicNovel'";
		}
		if(chckbxYoungadult.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'YoungAdult'";
		}
		if(chckbxScifi.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'SciFi'";
		}
		if(chckbxRomance.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'Romance'";
		}
		if(chckbxOther.isSelected()){
			HearnBookFaceFrame.checked += " OR Category = 'Other'";
		}
		if(chckbxHumor.isSelected() || chckbxBiography.isSelected() || chckbxAutobiography.isSelected() || chckbxLiterature.isSelected()
				|| chckbxMystery.isSelected() || chckbxGraphicnovel.isSelected() || chckbxYoungadult.isSelected() || chckbxScifi.isSelected()
				|| chckbxRomance.isSelected() || chckbxOther.isSelected()){
			HearnBookFaceFrame.checked += " )";
		}
		
		
	}
	protected void do_btnok_actionPerformed(ActionEvent e) {
		checkBoxCheck();
		this.dispose();
	}
	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
