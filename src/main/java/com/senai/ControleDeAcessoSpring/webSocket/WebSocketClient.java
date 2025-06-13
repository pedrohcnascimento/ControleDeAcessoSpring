package com.senai.ControleDeAcessoSpring.webSocket;

import jakarta.websocket.*;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

@ClientEndpoint
public class WebSocketClient {

    private static Session session;
    private static Timer pingTimer;

    @OnOpen
    public void onOpen(Session s) {
        session = s;
        System.out.println("Conexão efetuada com sucesso!");
    }

    @OnMessage
    public void onMessage (String msg) {
        System.out.println("Mensagem recebida por WebSocket: " + msg);
    }

    @OnClose
    public void onClose(Session s, CloseReason reason) {
        System.out.println("Sessão encerrada, motivo: " + reason);
    }

    public static void conectar() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            URI uri = new URI("ws://localhost:8080/ws");
            container.connectToServer(WebSocketClient.class, uri);

            pingTimer = new Timer(true);
            pingTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (session != null) {
                        try {
                            session.getBasicRemote().sendText("ping");
                        } catch (Exception ignored) {}
                    }
                };
            }, 20_000, 20_000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void desconectar() {
        try {
            if (pingTimer != null) {
                pingTimer.cancel();
            }
            if (session != null && session.isOpen()) {
                session.close();
                System.out.println("Desconectado do WebSocket.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao fechar WebSocket: " + e.getMessage());
        }
    }
}
