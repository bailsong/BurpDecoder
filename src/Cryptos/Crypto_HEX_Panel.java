package Cryptos;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Crypto_HEX_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	public Crypto_HEX_Panel() {
		setPreferredSize(new Dimension(400, 400));
		setLayout(null);
		
		JLabel lblUrlcryptor = new JLabel("THIS is \nHEX_Cryptor");
		lblUrlcryptor.setHorizontalAlignment(SwingConstants.CENTER);
		lblUrlcryptor.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUrlcryptor.setBounds(111, 127, 169, 93);
		add(lblUrlcryptor);
	}

}
