package Cryptos;
import java.util.*;

public class Site {
	public Vector <Crypto> Request_Cryptor_Stack;
	public Vector <String> Request_Cryptor_Name_Stack;
	public String Domain;
	public Site(String para_Domain) {
		this.Request_Cryptor_Stack = new Vector<Crypto>();
		this.Request_Cryptor_Name_Stack = new Vector<String>();
		this.Domain = para_Domain;
	}
	public boolean add_Request_Cryptor(int index,Crypto para_Cryptor){
		// ��β��׷��������Ŀ
		if(this.Request_Cryptor_Name_Stack.size() == index + 1) {
			this.Request_Cryptor_Stack.add(para_Cryptor);
			this.Request_Cryptor_Name_Stack.add(para_Cryptor.self_Type);	
		}else {
			// �ڵ��ѡ�����Ŀ�󷽲���������Ŀ
			this.Request_Cryptor_Stack.add(index,para_Cryptor);
			this.Request_Cryptor_Name_Stack.add(index,para_Cryptor.self_Type);
		}
		return true;
	}
	public boolean edit_Request_Cryptor(int index,Crypto para_Cryptor) {
		this.Request_Cryptor_Stack.set(index+1, para_Cryptor);
		this.Request_Cryptor_Name_Stack.set(index+1, para_Cryptor.self_Type);
		return true;
	}
	public boolean remove_Request_Cryptor(int index) {
		try {
			this.Request_Cryptor_Name_Stack.remove(index);
			this.Request_Cryptor_Stack.remove(index);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
