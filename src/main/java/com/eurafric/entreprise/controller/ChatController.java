package com.eurafric.entreprise.controller;

import com.eurafric.entreprise.entity.ChatMessage;
import com.eurafric.entreprise.repository.ChatMessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    // WebSocket → le serveur pose l'expéditeur à partir du Principal
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessageWebSocket(ChatMessage message, Principal principal) {
        String username = (principal != null) ? principal.getName() : "anonymous";
        message.setSender(username);
        message.setTimeSent(LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    // REST → on garde l’API (si tu continues à l’utiliser côté Angular)
    @PostMapping
    public ChatMessage postMessage(@RequestBody ChatMessage message) {
        if (message.getSender() == null || message.getSender().isBlank()) {
            message.setSender("anonymous");
        }
        message.setTimeSent(LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    @GetMapping("/history/{room}")
    public List<ChatMessage> getChatHistory(@PathVariable String room) {
        return chatMessageRepository.findByRoomOrderByTimeSentAsc(room);
    }
}
