package Pages;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Cryptos.*;
public class Add_Cryptor_JDialog extends JDialog {

	public JPanel contentPane;
	public JPanel Middle_Panel;
	public Crypto self_Crypto;
	public JTextField Data_Position_TXT;
	public JButton Data_Position_BTN;
	public String Data_Format = "RAW";
	String[] Cryptor_List = new String[] {"Cryptor_URL","Cryptor_HEX","Cryptor_AES"};
	public String[] Data_Format_List = new String[] {"RAW","JSON"};
	public JComboBox Cryptor_List_Box;
	public JComboBox DataFormat_Select_CBX;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Add_Cryptor_JDialog() {
		this.setModal(true);
		setMinimumSize(new Dimension(800, 800));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		init_Component();
		
		this.self_Crypto = new Crypto_URL();
		this.Middle_Panel.removeAll();
		this.Middle_Panel.add(this.self_Crypto.self_Panel,BorderLayout.CENTER);
		this.Middle_Panel.setVisible(true);
		this.invalidate();
		this.repaint();
		this.setVisible(true);
	}
	public Add_Cryptor_JDialog(Crypto para_Crypto) {
		this.setModal(true);
		setMinimumSize(new Dimension(800, 800));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		init_Component();
		
		int index = Common.Common.get_First_Index_String(this.Cryptor_List, para_Crypto.self_Type);
		if(index == -1) {
			this.dispose();
		}
		
		index = Common.Common.get_First_Index_String(this.Data_Format_List, para_Crypto.Data_Format);
		this.Data_Format = para_Crypto.Data_Format;
		this.DataFormat_Select_CBX.setSelectedIndex(index);
		this.Data_Position_TXT.setText(para_Crypto.Data_Position);
		if(!para_Crypto.Data_Format.equals("RAW")) {
			this.Data_Position_TXT.setEditable(true);
			this.Data_Position_BTN.setEnabled(true);
		}
		
		this.Cryptor_List_Box.setSelectedIndex(index);
		this.self_Crypto = para_Crypto;
		this.Middle_Panel.removeAll();
		this.Middle_Panel.add(this.self_Crypto.self_Panel,BorderLayout.CENTER);
		this.Middle_Panel.setVisible(true);
		this.invalidate();
		this.repaint();
		this.setVisible(true);
	}
	public void init_Component() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
	
		
		JPanel Container_Panel = new JPanel();
		contentPane.add(Container_Panel, BorderLayout.CENTER);
		Container_Panel.setLayout(new BorderLayout(0, 0));
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setPreferredSize(new Dimension(800, 150));
		Container_Panel.add(horizontalBox, BorderLayout.NORTH);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setMaximumSize(new Dimension(300, 150));
		verticalBox.setPreferredSize(new Dimension(300, 150));
		horizontalBox.add(verticalBox);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setMaximumSize(new Dimension(32767, 30));
		verticalStrut_3.setPreferredSize(new Dimension(0, 30));
		verticalBox.add(verticalStrut_3);
		
		JLabel Add_Crypto_LOGO = new JLabel(" Add Cryptor");
		Add_Crypto_LOGO.setPreferredSize(new Dimension(88, 30));
		Add_Crypto_LOGO.setAlignmentX(Component.CENTER_ALIGNMENT);
		Add_Crypto_LOGO.setMaximumSize(new Dimension(150, 30));
		verticalBox.add(Add_Crypto_LOGO);
		Add_Crypto_LOGO.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		

		
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_4);
		Cryptor_List_Box = new JComboBox(Cryptor_List);
		Cryptor_List_Box.setMaximumSize(new Dimension(150, 30));
		Cryptor_List_Box.setPreferredSize(new Dimension(150, 50));
		verticalBox.add(Cryptor_List_Box);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setMaximumSize(new Dimension(400, 180));
		verticalBox_1.setAlignmentX(0.5f);
		horizontalBox.add(verticalBox_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox_1.add(verticalStrut);
		
		JLabel label = new JLabel("\u8BF7\u6C42\u5305\u53C2\u6570\u4F4D\u7F6E");
		label.setAlignmentX(0.5f);
		verticalBox_1.add(label);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setMaximumSize(new Dimension(32767, 5));
		verticalBox_1.add(verticalStrut_1);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setMaximumSize(new Dimension(400, 50));
		horizontalBox_1.setAlignmentY(0.5f);
		verticalBox_1.add(horizontalBox_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut);
		
		JLabel label_1 = new JLabel("\u6570\u636E\u683C\u5F0F");
		label_1.setAlignmentX(0.5f);
		horizontalBox_1.add(label_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);
		String[] Data_Format_List = new String[] {"RAW","JSON"};
		DataFormat_Select_CBX = new JComboBox(Data_Format_List);
		DataFormat_Select_CBX.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					Change_DataFormat(DataFormat_Select_CBX.getSelectedItem().toString());
				}
			}
		});
		DataFormat_Select_CBX.setPreferredSize(new Dimension(80, 30));
		DataFormat_Select_CBX.setMinimumSize(new Dimension(80, 30));
		DataFormat_Select_CBX.setMaximumSize(new Dimension(200, 30));
		horizontalBox_1.add(DataFormat_Select_CBX);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_2);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setMaximumSize(new Dimension(10, 5));
		verticalBox_1.add(verticalStrut_2);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setMaximumSize(new Dimension(400, 50));
		verticalBox_1.add(horizontalBox_2);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_3);
		
		JLabel label_2 = new JLabel("\u5BC6\u6587\u4F4D\u7F6E");
		horizontalBox_2.add(label_2);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_4);
		
		Data_Position_TXT = new JTextField();
		Data_Position_TXT.setMaximumSize(new Dimension(200, 30));
		Data_Position_TXT.setEditable(false);
		Data_Position_TXT.setColumns(10);
		horizontalBox_2.add(Data_Position_TXT);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setMaximumSize(new Dimension(10, 32767));
		horizontalBox_2.add(horizontalStrut_5);
		
		Data_Position_BTN = new JButton("\u786E\u8BA4");
		Data_Position_BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Data_Position_Submit();
			}
		});
		Data_Position_BTN.setEnabled(false);
		horizontalBox_2.add(Data_Position_BTN);
		
		//设置监听事件门
		Cryptor_List_Box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED ) {
					Change_Cryptor_Panel(Cryptor_List_Box.getSelectedItem().toString());
					
				}
			}
		});
		
		Middle_Panel = new JPanel();
		Middle_Panel.setMinimumSize(new Dimension(400, 400));
		Middle_Panel.setMaximumSize(new Dimension(400, 400));
		Middle_Panel.setPreferredSize(new Dimension(400, 400));
		Middle_Panel.setLayout(new BorderLayout());
		Container_Panel.add(Middle_Panel,BorderLayout.CENTER);
		Middle_Panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel footer_Panel = new JPanel();
		Container_Panel.add(footer_Panel,BorderLayout.SOUTH);
		
		JButton Comfirm_Btn = new JButton("确认");
		
		footer_Panel.add(Comfirm_Btn);
		
		JButton Cancel_Btn = new JButton("取消");
		
		footer_Panel.add(Cancel_Btn);
		Comfirm_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Submit_Cryptor();
			}
		});
		Cancel_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close_Frame();
			}
		});
	}
	
	public void Change_Cryptor_Panel(String para_Cryptor) {
		switch(para_Cryptor) {
		case "Cryptor_URL":
			this.self_Crypto = new Crypto_URL();
			this.Middle_Panel.removeAll();
			this.Middle_Panel.add(this.self_Crypto.self_Panel,BorderLayout.CENTER);
			this.Middle_Panel.setVisible(true);
			this.invalidate();
			this.repaint();
			this.setVisible(true);
			break;
		case "Cryptor_AES":
			this.self_Crypto = new Crypto_AES();
			this.Middle_Panel.removeAll();
			this.Middle_Panel.add(this.self_Crypto.self_Panel,BorderLayout.CENTER);
			this.Middle_Panel.setVisible(true);
			this.invalidate();
			this.repaint();
			this.setVisible(true);
			break;
		case "Cryptor_HEX":
			this.self_Crypto = new Crypto_HEX();
			this.Middle_Panel.removeAll();
			this.Middle_Panel.add(this.self_Crypto.self_Panel);
			this.Middle_Panel.setVisible(true);
			this.invalidate();
			this.repaint();
			this.setVisible(true);
		default:
			break;
		}
	}
	public void Submit_Cryptor() {
		if(this.self_Crypto.Check() && this.self_Crypto != null) {
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Cyptor配置错误");
		}
	}
	public void Close_Frame() {
		this.self_Crypto = null;
		this.dispose();
	}
	public void Change_DataFormat(String para_DataFormat) {
		this.Data_Format = para_DataFormat;
		this.self_Crypto.set_Data_Format("Data_Format_"+para_DataFormat);
		
		if(para_DataFormat.equals("RAW")) {
			this.Data_Position_TXT.setEditable(false);
			this.Data_Position_BTN.setEnabled(false);
		}else {
			this.Data_Position_TXT.setEditable(true);
			this.Data_Position_BTN.setEnabled(true);
		}
	}
	public void Data_Position_Submit() {
		this.self_Crypto.set_Data_Position(this.Data_Position_TXT.getText().trim());
	}
}
