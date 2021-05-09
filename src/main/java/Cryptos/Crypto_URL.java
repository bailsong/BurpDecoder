package Cryptos;
import java.net.*;
public class Crypto_URL extends Crypto{
	public Crypto_URL(){
		this.Data_Format="RAW";
		this.Data_Position="";
		this.self_Type = "Cryptor_URL";
		this.self_Panel = new Crypto_URL_Panel();
	}
	public String encode(String para_Body_String){
		String result = para_Body_String;
		String string_to_be_encoded = get_Data_Posi_String(para_Body_String);
		try{
			string_to_be_encoded = URLEncoder.encode(string_to_be_encoded,"utf-8");	
		}catch(Exception e){
			
		}
		result = set_Data_Posi_String(para_Body_String,string_to_be_encoded);
		return result;
	}

	public String decode(String para_Body_String){
		String result = para_Body_String;
		String string_to_be_encoded = get_Data_Posi_String(para_Body_String);
		String Encoded_String = string_to_be_encoded.replaceAll("%(![0-9a-fA-F]{2})","%25");
		try{
			Encoded_String = URLDecoder.decode(Encoded_String,"utf-8");
		}catch(Exception e){
		}
		result = set_Data_Posi_String(para_Body_String,Encoded_String);
		return result;
	}
	public boolean Check() {
		return true;
	}
}

