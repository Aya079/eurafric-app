package com.eurafric.entreprise.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * Lit le username passé en query param (?username=...) et le place
 * dans Principal pour cette session WebSocket.
 */
public class WsUserHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {

        // ex: ws://localhost:8080/ws-chat?username=Aya
        String query = request.getURI().getQuery(); // username=Aya
        String username = "anonymous";
        if (query != null) {
            for (String p : query.split("&")) {
                String[] kv = p.split("=", 2);
                if (kv.length == 2 && kv[0].equals("username") && !kv[1].isBlank()) {
                    username = kv[1];
                    break;
                }
            }
        }
        final String finalUsername = username;

        // Principal simple
        return new Principal() {
            @Override
            public String getName() {
                return finalUsername;
            }

            @Override
            public String toString() {
                return finalUsername;
            }
        };
    }
}
