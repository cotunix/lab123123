/*
   Scott Campbell

   lab 910 client

   write a message to RPC server
 */

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.net.*;
import java.io.*;

public class AddMessage {
	XmlRpcClient xmlRPCclient = null;
	int port = -1;


	public static void main(String args[]) {
		if (args.length != 5) {
			System.err.println("Usage:  java AddMessage <HOST> <PORT> <user> <password> <message>");
		}

		int port = -1;
		try {
			port = Integer.parseInt(args[1]);
		} catch (Exception err) {
			System.out.println("specify port");
			return;
		}

		try {
			AddMessage client = new AddMessage(args[0],port);
			String t= client.getToken(args[2],args[3]);
			client.put(t,args[4]);		//url to put
		}
		catch (Exception err) {
			System.err.println("error geting token " + err);
			return;
		}

	}

	/*
	   constructor
	 */
	public AddMessage(String host,int p) throws IOException {
		port = p;
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://" + host+":"+port));
		xmlRPCclient = new XmlRpcClient();
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



	public void put(String token, String msg) throws IOException,org.apache.xmlrpc.XmlRpcException {
		Object[] params = new Object[]{token,msg};
		String authToken= (String) xmlRPCclient.execute("lab.storeMessage", params);
	}

}
