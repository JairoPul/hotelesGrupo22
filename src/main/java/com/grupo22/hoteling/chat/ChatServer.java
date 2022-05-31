/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.chat;

import com.grupo22.hoteling.entities.Chatlog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Jairo
 */
@ServerEndpoint("/websocket")
public class ChatServer {
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private static final Set<String> connectedUsers = Collections.synchronizedSet(new HashSet<String>());
    @PersistenceContext(unitName = "com.grupo22_hotelesGrupo22_war_v1PU")
    private EntityManager em;
    @Resource
    UserTransaction ut;
    
    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
    }
    
    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }
    
    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
                
        // Caso de que sea el mensaje de que alguien se ha conectado
        if (message.contains("se unió") && !connectedUsers.contains(message.substring(0, message.indexOf("se unió")))) {
            String user = message.substring(0, message.indexOf("se unió"));
            connectedUsers.add(user);
            
            String messageFromServer = "{\"server\": \"yes\", \"users\": \"";
            for ( String u : connectedUsers ){
                messageFromServer += u + "\\n";
            }
            
            String messageFromServerToClient =  messageFromServer + "\", \"isThereChatlog\": \"yes\", \"chatlog\": \"";
            List<String> chatlog;
            try{   
                chatlog = em.createNamedQuery("Chatlog.findLast15Messages").getResultList();
            } catch (Exception e) {
                chatlog = new ArrayList<String>();
            }
            for ( String m : chatlog ){
                messageFromServerToClient += m + "\\n";
            }
            messageFromServerToClient += "\"}";
            client.getBasicRemote().sendText(messageFromServerToClient);
            
            String messageFromServerToPeers =  messageFromServer + "\", \"isThereChatlog\": \"no\"}";
            for (Session peer : peers) {
                peer.getBasicRemote().sendText(messageFromServerToPeers);
            }
        }
        
        // Caso de que sea el mensaje de que alguien se ha desconectado
        if (message.contains("se desconectó") && connectedUsers.contains(message.substring(0, message.indexOf("se desconectó")))) {
            String user = message.substring(0, message.indexOf("se desconectó"));
            connectedUsers.remove(user);
            
            String messageFromServer = "{\"server\": \"yes\", \"users\": \"";
            for ( String u : connectedUsers ){
                messageFromServer += u + "\\n";
            }
            messageFromServer += "\", \"isThereChatlog\": \"no\"}";
            for (Session peer : peers) {
                peer.getBasicRemote().sendText(messageFromServer);
            }
        }
        
        try {
            ut.begin();
            em.persist(new Chatlog(message));
            ut.commit(); 
        } catch (Exception e){};
        
        String messageToSend = "{\"server\": \"no\", \"message\": \"" + message + "\\n\"}";
        for (Session peer : peers) {
            peer.getBasicRemote().sendText(messageToSend);
        }
        
    }
}
