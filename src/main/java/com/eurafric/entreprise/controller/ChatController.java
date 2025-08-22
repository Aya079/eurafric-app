package com.eurafric.entreprise.controller;

import com.eurafric.entreprise.entity.ChatMessage;
import com.eurafric.entreprise.repository.ChatMessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    // WebSocket → reçoit un message et le sauvegarde
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessageWebSocket(ChatMessage message) {
        message.setTimeSent(LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    // REST → envoyer un message via POST
    @PostMapping
    public ChatMessage postMessage(@RequestBody ChatMessage message) {
        message.setTimeSent(LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    // REST → récupérer l’historique des messages d’une room
    @GetMapping("/history/{room}")
    public List<ChatMessage> getChatHistory(@PathVariable String room) {
        return chatMessageRepository.findByRoomOrderByTimeSentAsc(room);
    }
}
