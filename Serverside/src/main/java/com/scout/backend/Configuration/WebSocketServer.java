package com.scout.backend.Configuration;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{username}")
@Component 
public class WebSocketServer {

	private Session session;
	private static Set<WebSocketServer> chatEndpoints = new CopyOnWriteArraySet<>();
	private static Map<String, String> users = new HashMap<>();
	private static Map<String, Session> usernameSessionMap = new HashMap<>();
	private static Map<Session, String> sessionToUserNameMap = new HashMap<>();
	private static Map<String, Integer> communicationMap = new HashMap<>();
	private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	
	@OnOpen 
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		logger.info("Entered into open");
		this.session = session;
		chatEndpoints.add(this);
		users.put(session.getId(), username);
		usernameSessionMap.put(username, session);
		logger.info(session.getId());
		String message = "User:" + username + "Has Joined the Chat";
		sessionToUserNameMap.put(session, username);
		broadcast(message);

	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws IOException{
		logger.info("Entered into Message: Got message"+ message);
		
		// TODO "email {message content}"
		String toUser = message.substring(0, message.indexOf(' '));
		String content = message.substring(message.indexOf(' ')+1);
		String fromUser= sessionToUserNameMap.get(session);
				
		sendMessageToParticularUser(usernameSessionMap.get(toUser),content);
		//broadcast(echo);
	}
	
	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Error");
		String username = sessionToUserNameMap.get(session);
		chatEndpoints.remove(this);
		session.close();
		
	}
	private void sendMessageToParticularUser(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message);
			
		}catch(IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}
	
	private static void broadcast(String message) throws IOException{
		chatEndpoints.forEach(endpoint -> {
			synchronized (endpoint) {
				try {
					endpoint.session.getBasicRemote().sendText(message);
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
