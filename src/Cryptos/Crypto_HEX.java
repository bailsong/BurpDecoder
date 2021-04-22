package Cryptos;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class Crypto_HEX extends Crypto{
	public Crypto_HEX() {
		this.Data_Format="RAW";
		this.Data_Position="";
		this.self_Type = "Cryptor_HEX";
		this.self_Panel = new Crypto_HEX_Panel();
	}
	public String encode(String para_Body_String){
		String result = para_Body_String;
		String string_to_be_encoded = get_Data_Posi_String(para_Body_String);
		try{
			string_to_be_encoded = StringToHexString(string_to_be_encoded);	
			result  = this.set_Data_Posi_String(para_Body_String, string_to_be_encoded);
			return result;
		}catch(Exception e){
			return para_Body_String;
		}
	}
	public String decode(String para_Body_String){
		String result = para_Body_String;
		String Encoded_String = get_Data_Posi_String(para_Body_String);
		
		if(!Encoded_String.matches("(\\\\x([0-9A-Fa-f]{2}))+")) {
			return Encoded_String;
		}
		try{
			Encoded_String = HexStringToString(Encoded_String);
			result = set_Data_Posi_String(para_Body_String, Encoded_String);
			return result;
		}catch(Exception e){
			return para_Body_String;
		}
	}
	public String StringToHexString(String str) {
		
		try {
			String result = "";
			for(int i = 0;i<str.length();i++) {
				result += "\\x" + Integer.toHexString(str.charAt(i));
			}
			return result;
		}catch(Exception e) {
			return str;
		}
		
	}
	public String HexStringToString(String str) {
		try {
			String[] tmp_list = str.trim().split("\\\\x");
			String result = "";
			String tmp = "";
			for (int i = 0;i<tmp_list.length;i++) {
				tmp = tmp_list[i];
				if(!tmp.equals("")){				
					int ch = (Character.digit(tmp.charAt(0), 16)<<4)+Character.digit(tmp.charAt(1),16);
					result += (char) ch;
				}
			}
			return result;
		}catch(Exception e) {
			return str;
		}
	}
	public boolean Check() {
		return true;
	}
}
