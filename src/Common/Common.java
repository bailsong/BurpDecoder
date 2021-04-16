package Common;

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
}
