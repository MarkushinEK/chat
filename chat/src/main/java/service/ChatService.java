package service;

import websocket.ChatWebSocket;

public interface ChatService {

    public void sendMessage(String data);

    public void add(ChatWebSocket webSocket);

    public void remove(ChatWebSocket webSocket);
}
