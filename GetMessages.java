/*
   Scott Campbell

   lab 910 client

   Get messages from shared server
 */

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.net.*;
import java.io.*;

public class GetMessages {
	XmlRpcClient xmlRPCclient = null;
	int port = -1;


	public static void main(String args[]) {
		if (args.length != 2) {
			System.out.println("Useage java GetMessages <host> <port>");
		}
		
		int port = -1;
		try {
			port = Integer.parseInt(args[1]);
		} catch (Exception err) {
			System.out.println("specify port");
			return;
		}

		try {
			GetMessages client = new GetMessages(args[0],port);
			String msg = client.getMessages();		//url to put
			System.out.println("Messages:");
			System.out.println(msg);
		}
		catch (Exception err) {
			System.err.println("error geting token " + err);
			return;
		}
	}

	/*
	   constructor
	 */
	public GetMessages(String host,int p) throws IOException {
		port = p;
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://" + host+":"+port));
		xmlRPCclient = new XmlRpcClient();
		xmlRPCclient.setConfig(config);
	}

	/*
	the server requires a token since it can not maintain state
	*/
	public String getMessages() throws IOException,org.apache.xmlrpc.XmlRpcException {
		Object[] params = new Object[]{};
		String result= (String) xmlRPCclient.execute("lab.getMessages", params);
		return result;
	}


}
