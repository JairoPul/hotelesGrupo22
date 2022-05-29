/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo22.hoteling.chat;

import com.grupo22.hoteling.entities.ChatUsers;
import com.grupo22.hoteling.entities.Chatlog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.RollbackException;
import javax.transaction.Transaction;
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
        List<String> users;
        try {
            users = em.createNamedQuery("ChatUsers.findAllUsers").getResultList();
        } catch (Exception e){
            users = new ArrayList<String>();
        }
        
        // Caso de que sea el mensaje de que alguien se ha conectado
        if (message.contains("se unió") && !users.contains(message.substring(0, message.indexOf("se unió")))) {
            String user = message.substring(0, message.indexOf("se unió"));
            try {
                users.add(user);
                ut.begin();
                em.persist(new ChatUsers(user));
                ut.commit();
            } catch (Exception e) {};
            String messageFromServer = "{\"server\": \"yes\", \"users\": \"";
            for ( String u : users ){
                messageFromServer += u + "\\n";
            }
            messageFromServer += "\", \"isThereChatlog\": \"yes\", \"chatlog\": \"";
            List<String> chatlog;
            try{   
                chatlog = em.createNamedQuery("Chatlog.findLast15Messages").getResultList();
            } catch (Exception e) {
                chatlog = new ArrayList<String>();
            }
            for ( String m : chatlog ){
                messageFromServer += m + "\\n";
            }
            messageFromServer += "\"}";
            client.getBasicRemote().sendText(messageFromServer);
        }
        
        // Caso de que sea el mensaje de que alguien se ha desconectado
        if (message.contains("se desconectó") && users.contains(message.substring(0, message.indexOf("se desconectó")))) {
            String user = message.substring(0, message.indexOf("se desconectó"));
            try {
                users.remove(user);
                ut.begin();
                em.remove(em.createNamedQuery("ChatUsers.findByName")
                        .setParameter("name", user).getSingleResult());
                ut.commit();
            } catch (Exception e) {};
            String messageFromServer = "{\"server\": \"yes\", \"users\": \"";
            for ( String u : users ){
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
