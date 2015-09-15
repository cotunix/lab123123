/*
Kyle Richardson
cse383

Remote procedures exposed to the server
All public methods are exposed
*/

import java.io.*;
import java.util.*;
public class Lab {
	static MessageHandler mess = new MessageHandler();
	static ArrayList<String> tokens = new ArrayList<String>();
	public String getAuthToken(String uid, String pass, byte[] code) throws Exception {
		int sum=0;
		String token = "";
		if (pass.contains("test123")){
			if (code.length<=0)
				throw new RuntimeException("Invalid token length");

			for (int i=0;i<code.length;i++) 
				sum += code[i];
			if (sum%13 != 0)
				throw new RuntimeException("Invalid token");

				System.out.println("uid: " + uid);
				token = uid + " 10011";
				tokens.add(token);
			


			
		}
		else {
			throw new IOException("Invalid password");
		}
		return token;
	}

	public String storeMessage(String token, String Message) {
		//validate token
		
		//store message
		if (tokens.contains(token)){
		System.out.println("msg=" + Message);
		mess.addMessage(Message);
		System.out.println("OK");
		return "OK";}
		
		else {
			System.out.println("NO");
			return "FAIL";
	}
}
	public String getMessages() {
		return mess.getAll();
	}
	
	public String clearMessages(String token) {
		if (tokens.contains(token))
			mess.clear();
		else
			return "FAIL";
	return "OK";
	}
	public int getNumMessages() {
		return mess.getNum();
}

	public boolean getPassword(String pass){
		return pass.equals("test123");
	}
}

