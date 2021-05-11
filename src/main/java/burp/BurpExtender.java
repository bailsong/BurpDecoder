package burp;

import java.util.*;
import Cryptos.*;
import com.alibaba.fastjson.*;
import java.awt.Component;
import java.io.*;
import javax.swing.*;
import Pages.*;



public class BurpExtender implements IBurpExtender,IProxyListener,IHttpListener,ITab{
	public IBurpExtenderCallbacks cbs;
	public IExtensionHelpers hps;
	public Map<String,Site> Site_List;
	public PrintWriter stdout;
	public JPanel dashboard;
	public boolean BurpStatus = false;
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		callbacks.setExtensionName("BurpDecoder");
		callbacks.registerProxyListener(this);
		callbacks.registerHttpListener(this);
		this.cbs = callbacks;
		this.hps = callbacks.getHelpers();
		this.stdout = new PrintWriter(callbacks.getStdout(),true);
		this.Site_List = new Hashtable<String,Site>();
		this.BurpStatus = false;
		
		this.stdout.println("Hello");
		this.stdout.println("==========================================");
		this.stdout.println("Coded by bailsong");
		this.stdout.println("此插件仅用作学习交流，任何组织或个人不得用于违法行为，否则由使用者自行承担法律后果");
		this.stdout.println("==========================================");
		this.dashboard = new Dashboard(this);
		callbacks.addSuiteTab(this);
		callbacks.customizeUiComponent(this.dashboard);
	}
	public String getTabCaption() {
		return "Burp Decoder";
	}
	public Component getUiComponent() {
		return this.dashboard;
	}
	@Override
	public void processProxyMessage(boolean messageIsRequest,IInterceptedProxyMessage messageInfo) {
		String domain = Common.Common.get_Doamin_URL_From_message(messageInfo.getMessageInfo());
		if(this.Site_List.containsKey(domain) && this.BurpStatus) {
			if(messageIsRequest) {
				String request_String = new String(messageInfo.getMessageInfo().getRequest());
				String body_String = get_Request_Body_String(request_String);
				this.stdout.println("=============================================");
				this.stdout.println("Proxy Got Request: "+body_String);
				for(Crypto tmp_Cryptor: this.Site_List.get(domain).Request_Cryptor_Stack) {
					this.stdout.println("=== Dealed with "+tmp_Cryptor.self_Type);
					this.stdout.println("=== Data Format: " + tmp_Cryptor.Data_Format);
					this.stdout.println("=== Data position: "+tmp_Cryptor.Data_Position);
					this.stdout.println("=== Cryptor EncodedString: ");
					this.stdout.println("++++++++++++++++++++++++++++++++++++++++++++++");
					//this.stdout.println(tmp_Cryptor.get_Data_Posi_String(body_String));
					body_String = tmp_Cryptor.decode(body_String);
				}
				this.stdout.println("Decoded body: "+body_String);
				this.stdout.println("=============================================");
				request_String = set_Request_Body_String(request_String,body_String);
				messageInfo.getMessageInfo().setRequest(request_String.getBytes());
				messageInfo.setInterceptAction(IInterceptedProxyMessage.ACTION_DO_INTERCEPT);
			}
		}
	}
	
	public void processHttpMessage(int toolFlag,boolean messageIsRequest, IHttpRequestResponse messageInfo) {

		String domain = Common.Common.get_Doamin_URL_From_message(messageInfo);
		if(this.Site_List.containsKey(domain) && this.BurpStatus){
			if(toolFlag == IBurpExtenderCallbacks.TOOL_PROXY && this.BurpStatus){
				if(messageIsRequest){
					String request_String  = new String(messageInfo.getRequest());
					String body_String = get_Request_Body_String(request_String);
					Vector<Crypto> tmp_reverse_Request_Cryptor_Stack = this.Site_List.get(domain).Request_Cryptor_Stack;
					Collections.reverse(tmp_reverse_Request_Cryptor_Stack);	

					this.stdout.println("=============================================");
					this.stdout.println("HTTP Got Request: "+body_String);
					for(Crypto tmp_Cryptor: tmp_reverse_Request_Cryptor_Stack){
						this.stdout.println("=== Dealed with "+tmp_Cryptor.self_Type);
						this.stdout.println("=== Data Format: " + tmp_Cryptor.Data_Format);
						this.stdout.println("=== Data position: "+tmp_Cryptor.Data_Position);
						this.stdout.println("=== Cryptor EncodedString: ");
						this.stdout.println("++++++++++++++++++++++++++++++++++++++++++++++");
						body_String  = tmp_Cryptor.encode(body_String);
					}
					Collections.reverse(tmp_reverse_Request_Cryptor_Stack);
					this.stdout.println("Encoded body: "+body_String);
					this.stdout.println("=============================================");
					
					request_String = set_Request_Body_String(request_String,body_String);	
					messageInfo.setRequest(request_String.getBytes());
				}
			}
		}
	}
	
	public String get_Request_Body_String(String para_Request_String) {
		String result = "";
		int index = this.hps.analyzeRequest(para_Request_String.getBytes()).getBodyOffset();
		String body_String = para_Request_String.substring(index);
		result = body_String;
		return result;
	}
	public String set_Request_Body_String(String para_Request_String,String para_Body_String) {
		String result = "";
		int index = this.hps.analyzeRequest(para_Request_String.getBytes()).getBodyOffset();
		result = para_Request_String.substring(0,index) + para_Body_String;
		return result;
	}
	public boolean Site_Add(String para_domain) {
		if(this.Site_List.containsKey(para_domain)) {
			return false;
		}else {
			this.Site_List.put(para_domain, new Site(para_domain));
			return true;
		}
	}
	public boolean Site_Remove(String para_domain) {
		if(this.Site_List.containsKey(para_domain)) {
			this.Site_List.remove(para_domain);
			return true;
		}else {
			return false;
		}
	}
	public boolean Site_Clear() {
		this.Site_List.clear();	
		return true;
	}
}
