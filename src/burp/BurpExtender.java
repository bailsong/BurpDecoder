package burp;

import java.util.*;
import Cryptos.*;

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
		this.stdout = new PrintWriter(callbacks.getStdout());
		this.Site_List = new Hashtable<String,Site>();
		this.BurpStatus = false;
		// ¡Ÿ ±ÃÌº”
		
		//
		
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
		IRequestInfo requestInfo = this.hps.analyzeRequest(messageInfo.getMessageInfo());
		String domain = Common.Common.get_Doamin_URL_From_message(messageInfo.getMessageInfo());
		if(this.Site_List.containsKey(domain) && this.BurpStatus) {
			if(messageIsRequest) {
				String request_String = new String(messageInfo.getMessageInfo().getRequest());
				String body_String = get_Request_Body_String(request_String);
				this.stdout.println("Proxy Got Request: "+body_String);
				for(Crypto tmp_Cryptor: this.Site_List.get(domain).Request_Cryptor_Stack) {
					body_String = tmp_Cryptor.decode(body_String);
				}

				request_String = set_Request_Body_String(request_String,body_String);
				messageInfo.getMessageInfo().setRequest(request_String.getBytes());
				messageInfo.setInterceptAction(IInterceptedProxyMessage.ACTION_DO_INTERCEPT);
			}
		}
	}
	
	public void processHttpMessage(int toolFlag,boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		IRequestInfo requestInfo = this.hps.analyzeRequest(messageInfo);
		String domain = Common.Common.get_Doamin_URL_From_message(messageInfo);
		if(this.Site_List.containsKey(domain)){
			if(toolFlag == IBurpExtenderCallbacks.TOOL_PROXY && this.BurpStatus){
				if(messageIsRequest){
					String request_String  = new String(messageInfo.getRequest());
					String body_String = get_Request_Body_String(request_String);
					Vector<Crypto> tmp_reverse_Request_Cryptor_Stack = this.Site_List.get(domain).Request_Cryptor_Stack;
					Collections.reverse(tmp_reverse_Request_Cryptor_Stack);	
					for(Crypto tmp_Cryptor: tmp_reverse_Request_Cryptor_Stack){
						body_String  = tmp_Cryptor.encode(body_String);
					}
					Collections.reverse(tmp_reverse_Request_Cryptor_Stack);
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
}
