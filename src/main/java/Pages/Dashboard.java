package Pages;

import burp.*;
import Common.Common;
import Cryptos.*;
import javax.swing.*;

import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.BevelBorder;

public class Dashboard extends JPanel {
	
	public BurpExtender myburp;
	public JToggleButton BurpStatus_Btn;
	public JList HR_Site_List;
	public DefaultListModel HR_Site_List_DFM;
	public String now_Domain="";
	public JList Request_Cryptor_List;
	public DefaultListModel Request_Cryptor_List_DFM;
	
	/**
	 * Create the panel.
	 */
	public Dashboard(BurpExtender para_burp) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.myburp = para_burp;
		this.now_Domain = "";
		setPreferredSize(new Dimension(800, 636));
		setLayout(null);

		HR_Site_List_DFM = new DefaultListModel();
		Request_Cryptor_List_DFM = new DefaultListModel();
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.setMaximumSize(new Dimension(800, 800));
		contentPanel.setPreferredSize(new Dimension(800, 800));
		contentPanel.setBounds(0, 0, 800, 636);
		add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD");
		lblNewLabel.setBounds(58, 51, 118, 41);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		BurpStatus_Btn = new JToggleButton("\u63D2\u4EF6\u5F00\u5173");
		BurpStatus_Btn.setBounds(58, 139, 118, 41);
		contentPanel.add(BurpStatus_Btn);
		
		JButton Site_Add_Btn = new JButton("Add");
		Site_Add_Btn.setBounds(296, 43, 113, 27);
		contentPanel.add(Site_Add_Btn);
		
		JButton Site_Remove_Btn = new JButton("Remove");
		Site_Remove_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Site_List_Remove();
			}
		});
		Site_Remove_Btn.setBounds(297, 103, 113, 27);
		contentPanel.add(Site_Remove_Btn);
		
		JButton Site_Clear_Btn = new JButton("Clear");
		Site_Clear_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Site_List_Clear();
			}
		});
		Site_Clear_Btn.setBounds(299, 158, 113, 27);
		contentPanel.add(Site_Clear_Btn);
		
		
		
		HR_Site_List = new JList();
		HR_Site_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		HR_Site_List.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					Site_List_Changed();
				}
			}
		});
		HR_Site_List.setModel(HR_Site_List_DFM);
		
		JScrollPane Site_List_Pane = new JScrollPane(HR_Site_List);
		Site_List_Pane.setBounds(470, 41, 287, 184);
		contentPanel.add(Site_List_Pane);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 266, 754, 346);
		contentPanel.add(panel);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(null);
		
		
		
		Request_Cryptor_List = new JList();
		Request_Cryptor_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Request_Cryptor_List.setModel(Request_Cryptor_List_DFM);
		
		JScrollPane Request_Cryptors_Pane = new JScrollPane(Request_Cryptor_List);
		Request_Cryptors_Pane.setBounds(257, 126, 483, 162);
		panel.add(Request_Cryptors_Pane);
		
		
		JLabel label = new JLabel("\u8BF7\u6C42\u5305 \u7B97\u6CD5\u6808");
		label.setBounds(298, 50, 369, 38);
		panel.add(label);
		label.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton Request_Cryptor_Add_Btn = new JButton("Add");
		Request_Cryptor_Add_Btn.setBounds(59, 124, 113, 27);
		panel.add(Request_Cryptor_Add_Btn);
		
		JButton Reqeust_Cryptor_Edit_Btn = new JButton("Edit");
		Reqeust_Cryptor_Edit_Btn.setBounds(59, 198, 113, 27);
		panel.add(Reqeust_Cryptor_Edit_Btn);
		
		JButton Reqeust_Cryptor_Remove_Btn = new JButton("Remove");
		Reqeust_Cryptor_Remove_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Request_Cryptor_Remove();
			}
		});
		Reqeust_Cryptor_Remove_Btn.setBounds(59, 264, 113, 27);
		panel.add(Reqeust_Cryptor_Remove_Btn);
		Reqeust_Cryptor_Edit_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request_Crytor_Edit();
			}
		});
		Request_Cryptor_Add_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request_Crytor_Add();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\u7AD9\u70B9\u5217\u8868");
		lblNewLabel_1.setBounds(471, 13, 287, 18);
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		Site_Add_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Site_List_Add();
			}
		});
		BurpStatus_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeBurpStatus(((JToggleButton) e.getSource()).isSelected());
			}
		});
		this.setVisible(true);
	}
	public boolean ChangeBurpStatus(boolean para_Status) {
		this.myburp.BurpStatus = para_Status;
		this.BurpStatus_Btn.setText(this.BurpStatus_Btn.isSelected() ? "插件状态 / ON" : "插件状态 / OFF");
		return true;
	}
	public void Site_List_Add() {
		String input_domain = JOptionPane.showInputDialog(null, "输入URL", "如: http://baidu.com或https://192.168.1.1:8888");
		input_domain = Common.get_Domain_URL(input_domain);
		if(input_domain == ""){
			JOptionPane.showMessageDialog(null, "URL错误");
			return ;
		}
		if(this.myburp.Site_Add(input_domain)) {
			this.HR_Site_List_DFM.addElement(input_domain);
			this.now_Domain = input_domain;
			int index = this.HR_Site_List_DFM.getSize();
			this.HR_Site_List.setSelectedIndex(index-1);
		}else{
			JOptionPane.showMessageDialog(null, "URL已存在");
			return;
		}
		return;
	}
	public void Site_List_Remove() {
		int index = this.HR_Site_List.getSelectedIndex();
		if(index != -1) {
			String domain = this.HR_Site_List_DFM.getElementAt(index).toString();
			this.myburp.Site_Remove(domain);
			this.HR_Site_List_DFM.remove(index);
			if(index == 0) {
				if(this.HR_Site_List_DFM.getSize() != 0) {
					this.HR_Site_List.setSelectedIndex(index);
				}else {
					this.now_Domain = "";
				}
			}else if(this.HR_Site_List_DFM.getSize() == index) {
				this.HR_Site_List.setSelectedIndex(index -1);
			}
		}
	}
	public void Site_List_Clear() {
		this.myburp.Site_Clear();
		this.HR_Site_List_DFM.clear();
		this.now_Domain = "";
	}
	public void Site_List_Changed() {
		int Size = this.HR_Site_List_DFM.getSize();
		this.Request_Cryptor_List_DFM.removeAllElements();
		if(Size != 0) {
			int index = this.HR_Site_List.getSelectedIndex();
			this.now_Domain = this.HR_Site_List_DFM.get(index).toString();
			for(String tmp_cryptor_name: this.myburp.Site_List.get(this.now_Domain).Request_Cryptor_Name_Stack) {
				this.Request_Cryptor_List_DFM.addElement(tmp_cryptor_name);
			}
		}
	}
	public void Request_Crytor_Add() {
		Crypto tmp_Crypto ;
		Add_Cryptor_JDialog Add_Cryptor_Dialog = new Add_Cryptor_JDialog();
		if(Add_Cryptor_Dialog.self_Crypto == null) {
				return;
		}
		
		// 临时赋值
		tmp_Crypto = Add_Cryptor_Dialog.self_Crypto;
		
		int Reqeust_index = this.Request_Cryptor_List.getSelectedIndex();
		this.myburp.Site_List.get(this.now_Domain).add_Request_Cryptor(Reqeust_index,tmp_Crypto);
		if(this.Request_Cryptor_List_DFM.getSize() == Reqeust_index +1) {
			this.Request_Cryptor_List_DFM.addElement(tmp_Crypto.self_Type);
			this.Request_Cryptor_List.setSelectedIndex(Reqeust_index + 1);
		}else {
			this.Request_Cryptor_List_DFM.add(Reqeust_index+1, tmp_Crypto.self_Type);
			this.Request_Cryptor_List.setSelectedIndex(Reqeust_index + 1);	
		}
		
	}
	public void Request_Crytor_Edit() {
		Crypto tmp_Crypto ;
		
		int Reqeust_index = this.Request_Cryptor_List.getSelectedIndex();
		tmp_Crypto = this.myburp.Site_List.get(this.now_Domain).Request_Cryptor_Stack.get(Reqeust_index);
		
		Add_Cryptor_JDialog Edit_Cryptor_Dialog = new Add_Cryptor_JDialog(tmp_Crypto);
		
		tmp_Crypto = Edit_Cryptor_Dialog.self_Crypto;
		if(tmp_Crypto == null) {
			return;
		}
		
		this.myburp.Site_List.get(this.now_Domain).edit_Request_Cryptor(Reqeust_index,tmp_Crypto);
		
		this.Request_Cryptor_List_DFM.set(Reqeust_index, tmp_Crypto.self_Type);
	}
	public void Request_Cryptor_Remove() {
		int Request_index = this.Request_Cryptor_List.getSelectedIndex();
		if(Request_index != -1) {
			this.Request_Cryptor_List_DFM.remove(Request_index);
			// 如果不是最后一个 Cryptor，选中 下一个Cryptor
			if(Request_index == this.Request_Cryptor_List_DFM.getSize()) {
				if(Request_index != 0) {
					this.Request_Cryptor_List.setSelectedIndex(Request_index -1); 
				}
			}else {
				this.Request_Cryptor_List.setSelectedIndex(Request_index);
			}
		}
	}
}
