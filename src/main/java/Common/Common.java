package Common;
import burp.*;
public class Common {
	public static String get_Domain_Protocol(String domain) {
		String target_string = domain.toLowerCase();
		if(target_string.startsWith("http://")) {
			return "http://";
		}
		else if(target_string.startsWith("https://")) {
			return "https://";
		}
		else {
			return "";
		}
	}
	public static String get_Domain_Host(String domain) {
		String target_string = domain.toLowerCase().replace(get_Domain_Protocol(domain),"");
		int coma = target_string.indexOf(":");
		if(coma != -1)
		{
			return target_string.substring(0, coma);
		}
		coma  = target_string.indexOf("/");
		if(coma != -1)
		{
			return target_string.substring(0, coma);
		}
		return target_string;
	}
	public static String get_Domain_Port(String domain) {
		String target_string;
		if(domain.startsWith(get_Domain_Protocol(domain)+get_Domain_Host(domain)+":"))
			target_string = domain.toLowerCase().replace(get_Domain_Protocol(domain)+get_Domain_Host(domain)+":","");
		else
			return "FALSE";
		String port = "";
		int port_num =0 ;
		for(int i = 0;i<target_string.length() && '0' <= target_string.charAt(i) && target_string.charAt(i) <= '9';i++) {
			port += target_string.charAt(i);
			port_num *= 10;
			port_num += target_string.charAt(i)-'0';
		}
		if(port_num <= 65535)
			return ":"+new String(port);
		else
			return "FALSE";
	}

	public static String get_Domain_URL(String domain) {
		String tmp_pro = get_Domain_Protocol(domain);
		if(tmp_pro.equals(""))
			return "";
		String tmp_host = get_Domain_Host(domain);
		String tmp_port = get_Domain_Port(domain);
		if(tmp_port.equals("FALSE"))
			tmp_port = "";
		if(tmp_pro.equals("http://") && tmp_port.equals(":80") || tmp_pro.equals("https://") && tmp_port.equals(":443")) {
			tmp_port = "";
		}
		return tmp_pro+tmp_host+tmp_port;
	}
	public static String get_Doamin_URL_From_message(IHttpRequestResponse messageInfo) {
		String service = messageInfo.getHttpService().toString();
		if(service.startsWith("http://") && service.endsWith(":80")) {
			return service.replace(":80", "");
		}else if(service.startsWith("https://") && service.endsWith(":443")) {
			return service.replace(":443", "");
		}else {
			return service;
		}
	}
	public static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
 
		for (int i = 0; i < buf.length; ++i) {
			String hex = Integer.toHexString(buf[i] & 255);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
 
		return sb.toString();
	}
	public static byte[] parseHexStr2Byte(String para_Str) {
		if(para_Str.length() % 2 != 0) {
			return para_Str.getBytes();
		}
		
		String hexDigits = "0123456789abcdef";
		para_Str = para_Str.toLowerCase();
		
		byte[] result = new byte[para_Str.length()/2];
		
		try {
			for(int i = 0;i<para_Str.length();i +=2) {
				result[i/2] = (byte)(hexDigits.indexOf(para_Str.charAt(i)) << 4|(hexDigits.indexOf(para_Str.charAt(i+1))));
			}
		}catch(Exception e){
			return null;
		}
		
		return result;
	}
	public static int get_First_Index_String(String[] para_List,String para_Str) {
		int index = -1;
		for(int i =0;i< para_List.length;i++) {
			if(para_List[i].equals(para_Str)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
