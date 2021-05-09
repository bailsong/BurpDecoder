package Cryptos;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Crypto_URL_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	public Crypto_URL_Panel() {
		setPreferredSize(new Dimension(400, 400));
		setLayout(null);
		
		JLabel lblUrlcryptor = new JLabel("THIS is \nURL_Cryptor");
		lblUrlcryptor.setHorizontalAlignment(SwingConstants.CENTER);
		lblUrlcryptor.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUrlcryptor.setBounds(111, 127, 169, 93);
		add(lblUrlcryptor);
		
	}

}
