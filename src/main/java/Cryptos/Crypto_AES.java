package Cryptos;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import javax.swing.*;

public class Crypto_AES extends Crypto{
	public SecretKeySpec key;
	public IvParameterSpec iv;
	public String mode;
	public String padding;
	public String  coding;
	public Crypto_AES(){
		this.Data_Format="RAW";
		this.Data_Position="";
		this.self_Type = "Cryptor_AES";
		this.self_Panel =new Crypto_AES_Panel();
		
		
		this.mode = ((Crypto_AES_Panel)this.self_Panel).mode_box.getSelectedItem().toString();
		this.padding = ((Crypto_AES_Panel)this.self_Panel).padding_box.getSelectedItem().toString();
		this.coding = ((Crypto_AES_Panel)this.self_Panel).coding.getSelectedItem().toString();
	}
	public String encode(String para_Body_String){
		String plain = get_Data_Posi_String(para_Body_String);
		String[] Padding_List = {"ZeroPadding","NoPadding","ISO10126Padding","PKCS1Padding","PKCS5Padding","SSL3Padding"};
		String[] Algor_Mode_List = {"ECB","CBC","CTR","CFB","OFB"};
		String[] out_Format_List = {"HEX","Base64"};
		if(! Arrays.asList(Padding_List).contains(this.padding) || !Arrays.asList(Algor_Mode_List).contains(this.mode) ||!Arrays.asList(out_Format_List).contains(this.coding)) {
			return para_Body_String;
		}
		try {
			
			
			byte[] dataBytes = plain.getBytes();
			Cipher cipher;
			if(this.padding.equals("ZeroPadding")) {
				cipher  = Cipher.getInstance("AES/"+this.mode+"/"+"NoPadding");
			}else {
				cipher  = Cipher.getInstance("AES/"+this.mode+"/"+this.padding);
			}
			
			int blockSize = cipher.getBlockSize();
			
			
			
			if(this.padding.equals("ZeroPadding")) {
				int length = dataBytes.length;
				if (length % blockSize != 0) {
					length = length + (blockSize - (length % blockSize));
				}
				byte[] plaintext = new byte[length];
				System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
				dataBytes = plaintext;
			}
			SecretKeySpec key = this.key;
			switch(this.mode) {
			case "ECB":
				cipher.init(Cipher.ENCRYPT_MODE, key);
				break;
			case "CBC":
			case "CFB":
			case "OFB":
				IvParameterSpec iv = this.iv;
				cipher.init(Cipher.ENCRYPT_MODE, key,iv);
				break;
			default:
				return para_Body_String;
			}
				
			
			byte[] encryped = cipher.doFinal(dataBytes);
			if(this.coding.equals("HEX")) {
				return set_Data_Posi_String(para_Body_String,Common.Common.parseByte2HexStr(encryped));
			}else {
				return set_Data_Posi_String(para_Body_String,Base64.getEncoder().encodeToString(encryped));
			}
		}catch(Exception e) {
			return para_Body_String;
		}
	}

	public String decode(String para_Body_String){
		
		String plain = get_Data_Posi_String(para_Body_String);
		
		String[] Padding_List = {"ZeroPadding","NoPadding","ISO10126Padding","PKCS1Padding","PKCS5Padding","SSL3Padding"};
		String[] Algor_Mode_List = {"ECB","CBC","CTR","CFB","OFB"};
		String[] out_Format_List = {"HEX","Base64"};
		if(! Arrays.asList(Padding_List).contains(this.padding) || !Arrays.asList(Algor_Mode_List).contains(this.mode) ||!Arrays.asList(out_Format_List).contains(this.coding)) {
			return para_Body_String;
		}
		try {
			
			byte[] dataBytes = plain.getBytes();
			Cipher cipher;
			if(this.padding.equals("ZeroPadding")) {
				cipher  = Cipher.getInstance("AES/"+this.mode+"/"+"NoPadding");
			}else {
				cipher  = Cipher.getInstance("AES/"+this.mode+"/"+this.padding);
			}
			
			
			if(this.coding.equals("HEX")) {
				dataBytes = Common.Common.parseHexStr2Byte(plain);
			}else {
				dataBytes = Base64.getDecoder().decode(plain);
			}
			
			
			switch(this.mode) {
			case "ECB":
				cipher.init(Cipher.DECRYPT_MODE, this.key);
				break;
			case "CBC":
			case "CFB":
			case "OFB":
			case "CTR":
				cipher.init(Cipher.DECRYPT_MODE, this.key,this.iv);
				break;
			default:
				return para_Body_String;
			}
			byte[] encryped = cipher.doFinal(dataBytes);
			return set_Data_Posi_String(para_Body_String, new String(encryped).trim());
		}catch(Exception e) {
			return para_Body_String;
		}
	}
	public boolean Check() {
		try {
			this.key = new SecretKeySpec(((Crypto_AES_Panel)this.self_Panel).key_text.getText().getBytes(),"AES");
			this.iv = new IvParameterSpec(((Crypto_AES_Panel)this.self_Panel).offset_text.getText().getBytes());
			this.mode = ((Crypto_AES_Panel)this.self_Panel).mode_box.getSelectedItem().toString();
			this.padding = ((Crypto_AES_Panel)this.self_Panel).padding_box.getSelectedItem().toString();
			this.coding = ((Crypto_AES_Panel)this.self_Panel).coding.getSelectedItem().toString();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Key或iv错误，请重新检查");
			return false;
		}
		return true;
	}
}

