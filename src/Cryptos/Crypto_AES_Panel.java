package Cryptos;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Component;

public class Crypto_AES_Panel extends JPanel {
	public JTextField offset_text;
	public JTextField key_text;
	public JComboBox mode_box;
	public JLabel offset_Label;
	public JComboBox coding;
	public JComboBox padding_box;

	/**
	 * Create the panel.
	 */
	public Crypto_AES_Panel() {
		setPreferredSize(new Dimension(800, 400));
		setLayout(null);
		int y1 = 90;
		int y2 = 130;
		int x1 = 75;
		int x2 = 140;
		
		JLabel key_label = new JLabel("\u5BC6\u94A5");
		key_label.setBounds(59, 90, 75, 20);
		add(key_label);
		
		offset_Label = new JLabel("\u504F\u79FB\u91CF");
		offset_Label.setBounds(59, 130, 75, 20);
		add(offset_Label);
		
		offset_text = new JTextField();
		offset_text.setText("ECB \u6A21\u5F0F\u4E0B\u65E0\u504F\u79FB\u91CF");
		offset_text.setEditable(false);
		offset_text.setBackground(Color.LIGHT_GRAY);
		offset_text.setBounds(122, 130, 130, 20);
		add(offset_text);
		offset_text.setColumns(10);
		
		key_text = new JTextField();
		key_text.setBounds(122, 90, 130, 20);
		add(key_text);
		key_text.setColumns(10);
		
		JLabel mode_label = new JLabel("\u6A21\u5F0F");
		mode_label.setBounds(284, y1, 75, 20);
		add(mode_label);
		
		JLabel padding_label = new JLabel("\u586B\u5145");
		padding_label.setBounds(284, y2, 75, 20);
		add(padding_label);
		
		String[] padding_list = {"ZeroPadding","NoPadding","ISO10126Padding","PKCS1Padding","PKCS5Padding","SSL3Padding"};
		
		padding_box = new JComboBox(padding_list);
		padding_box.setBounds(330, 130, 130, 24);
		add(padding_box);
		
		
		String[] mode_list = {"ECB","CBC","CTR","CFB","OFB"};
		
		mode_box = new JComboBox(mode_list);
		mode_box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					Mode_Changed();
				}
			}
		});
		mode_box.setBounds(330, 90, 130, 24);
		add(mode_box);
		
		
		JLabel coding_label = new JLabel("\u7F16\u7801");
		coding_label.setBounds(504, 90, 45, 20);
		add(coding_label);
		
		String[] coding_list =  {"HEX","Base64"};
		
		coding = new JComboBox(coding_list);
		coding.setBounds(549, 90, 100, 24);
		add(coding);

	}
	public void Mode_Changed() {
		if(this.mode_box.getSelectedItem().toString().equals("ECB")) {
			this.offset_text.setEditable(false);
			this.offset_text.setText("ECB模式无偏移量参数");
			this.offset_text.setBackground(Color.LIGHT_GRAY);
			this.offset_Label.setText("偏移量");
		}else if(this.mode_box.getSelectedItem().toString().equals("CTR")) {
			this.offset_Label.setText("随机数");
			this.offset_text.setEditable(true);
			this.offset_text.setText("");
			this.offset_text.setBackground(Color.white);
		}else {
			this.offset_Label.setText("偏移量");
			this.offset_text.setEditable(true);
			this.offset_text.setText("");
			this.offset_text.setBackground(Color.white);
		}
	}
}
