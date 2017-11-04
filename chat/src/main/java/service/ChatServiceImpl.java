package service;

import websocket.ChatWebSocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServiceImpl implements ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatServiceImpl() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        for (ChatWebSocket user : webSockets) {
            try {
                user.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }
}
