package Cryptos;
import burp.*;
import java.util.*;
import java.net.*;
public class Crypto_AES extends Crypto{
	public Crypto_AES(){
		this.Data_Format="RAW";
		this.Data_Position="";
		this.self_Type = "Cryptor_URL";
		this.self_Panel = new Crypto_AES_Panel();
	}
	public String encode(String para_Body_String){
		String result = para_Body_String;
			return result;
	}

	public String decode(String para_Body_String){
		String result = para_Body_String;
		return result;
	}
	public boolean Check() {
		return true;
	}
}

