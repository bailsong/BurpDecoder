package Cryptos;
import javax.swing.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public abstract class Crypto {
	public final String Data_Format_RAW = "RAW";
	public final String Data_Format_JSON = "JSON";
	
	
	public String Data_Position = "";
	public String Data_Format = Data_Format_RAW;
	public String self_Type="";
	public JPanel self_Panel ;
	
	
	
	public abstract String encode(String para_Request_String);
	public abstract String decode(String para_Request_String);
	public abstract boolean Check();
	
	public  String get_Data_Posi_String(String para_Data) {
		String result ="";
		switch(this.Data_Format) {
		case "JSON": 
		result = get_Data_Posi_String_DATA_JSON(para_Data,this.Data_Position); // 获取处于DataPosition 位置上的字符串
			break;
		case "RAW": 
			result = get_Data_Posi_String_DATA_RAW(para_Data,this.Data_Position);
			break;
		default: result = "";
		}
		return result;
	}
	public String set_Data_Posi_String(String para_Data,String encoded_Str) {
		String result = "";
		Object result_Object;
		switch(this.Data_Format) {
		case "JSON": 
			result_Object = set_Data_Posi_String_DATA_JSON(para_Data,this.Data_Position, encoded_Str);
			if(result_Object != null) {
				result = result_Object.toString();
			}else {
				result = para_Data;
			}
			break;
		case "RAW": result = set_Data_Posi_String_DATA_RAW(para_Data,this.Data_Position, encoded_Str);
			break;
		default: result = para_Data;
		}
		return result;
	}

	
	
	// Data_Format_RAW
	public String get_Data_Posi_String_DATA_RAW(String para_Data, String Data_Position) {
		return para_Data;
	}
	public String set_Data_Posi_String_DATA_RAW(String para_Data, String Data_Position, String encoded_Str) {
		return encoded_Str;
	}
	
	// Data_Format_JSON
	public  String get_Data_Posi_String_DATA_JSON(String para_Data,String Data_Position) {
		try {
			JSONObject json_Data = JSON.parseObject(para_Data);
			String[] tmp_DataPosi = Data_Position.split("\\.");
			Object tmp_Object = json_Data ;
			for(String itera:tmp_DataPosi) {
				if(itera.equalsIgnoreCase("*"))
					break;
				if(tmp_Object instanceof JSONObject && itera.startsWith("\"")) {
					tmp_Object = ((JSONObject)tmp_Object).get(itera.replace("\"", ""));
				}
				else if(tmp_Object instanceof JSONArray) {
					tmp_Object = ((JSONArray)tmp_Object).get(Integer.valueOf(itera));
				}
				else {
					return para_Data;
				}
			}
			return tmp_Object.toString();
		}catch(Exception e) {
			return "";
		}
		
		
	}
	
	
	public  Object set_Data_Posi_String_DATA_JSON(String para_Data,String Data_Position,String EncodeStr) {
		try {
			if(Data_Position.equals("*")) {
				return EncodeStr;
			}else {
				int first_index = Data_Position.indexOf(".");
				if(first_index == -1) {
					return null;
				}else {
					String key = Data_Position.substring(0, first_index);
					Object result_Object = JSON.parse(para_Data);
					Object tmp_Object;
					if(result_Object instanceof JSONObject) {
						try {
							tmp_Object = ((JSONObject) result_Object).get(key);
							tmp_Object = set_Data_Posi_String_DATA_JSON(tmp_Object.toString(),Data_Position.substring(first_index+1),EncodeStr);
							if(tmp_Object == null) {
								return null;
							}
							((JSONObject) result_Object).put(key, tmp_Object);
						}catch(Exception e) {
							return null;
						}
					}else if(result_Object instanceof JSONArray) {
						try {
							int index = Integer.parseInt(key);
							tmp_Object = ((JSONArray) result_Object).get(index);
							tmp_Object = set_Data_Posi_String_DATA_JSON(tmp_Object.toString(),Data_Position.substring(first_index+1),EncodeStr);
							if(tmp_Object == null) {
								return null;
							}
							((JSONArray) result_Object).set(index, tmp_Object);
						}catch(Exception e) {
							return null;
						}
						
					}
					return result_Object;
				}
			}
		}catch(Exception e){
			return para_Data;
		}
		
	}
	
	
	public boolean set_Data_Format(String para_Data_Format) {
		this.Data_Format = para_Data_Format;
		return true;
	}
	public boolean set_Data_Position(String para_DataPosition) {
		this.Data_Position = para_DataPosition;
		return true;
	}
}
