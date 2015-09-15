/*
   Kyle Richardson 

   lab 910 client

   Clears all messages in the server

   Adapted from Scott Campbell's code

 */

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.net.*;
import java.io.*;

public class ClearMessages {
	XmlRpcClient xmlRPCclient = null;
	int port = -1;


	public static void main(String args[]) {
		if (args.length != 4) {
			System.err.println("Usage:  java ClearMessages <HOST> <PORT> <user> <password>");
		}

		int port = -1;
		try {
			port = Integer.parseInt(args[1]);
		} catch (Exception err) {
			System.out.println("specify port");
			return;
		}

		try {
			ClearMessages client = new ClearMessages(args[0],port);
			String t= client.getToken(args[2],args[3]);
			client.clear(t);		//url to put
		}
		catch (Exception err) {
			System.err.println("error geting token " + err);
			return;
		}

	}

	/*
	   constructor
	 */
	public ClearMessages(String host,int p) throws IOException {
		port = p;
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://" + host+":"+port))
	;	xmlRPCclient = new XmlRpcClient();
		xmlRPCclient.setConfig(config);
	}

	/*
	   the server requires a token since it can not maintain state
	 */
	public String getToken(String uid, String pass) throws IOException,org.apache.xmlrpc.XmlRpcException {
		byte code[] = new byte[]{10,3};
		Object[] params = new Object[]{new String(uid),new String(pass), code};
		String authToken= (String) xmlRPCclient.execute("lab.getAuthToken", params);
		System.out.println("token = " + authToken);
		return authToken;
	}



	public void clear(String token) throws IOException,org.apache.xmlrpc.XmlRpcException {
		Object[] params = new Object[]{token};
		String auth= (String) 	xmlRPCclient.execute("lab.clearMessages", params);
	}

}
