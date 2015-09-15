/*
Scott Campbell

Message handler

stores message in memmory

*/

import java.util.ArrayList;
import java.util.Iterator;

public class MessageHandler {
	ArrayList<String> messages = null;

	public MessageHandler() {
		messages = new ArrayList<String>();
	}

	public synchronized void addMessage(String s) {
		messages.add(s);
	}
	public synchronized void clear() {
		messages.clear();
	}
	public synchronized int getNum() {
		return messages.size();
	}

	public synchronized String getAll() {
		StringBuffer sb = new StringBuffer();
		Iterator i = messages.iterator();
		while (i.hasNext()) {
			sb.append(i.next());
			sb.append("\r\n");
		}

		return sb.toString();
	}
}
	


