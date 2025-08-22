package com.eurafric.entreprise.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    @Lob
    private String content;

    @Column(name = "time_sent")
    private LocalDateTime timeSent;

    private String room;

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getTimeSent() { return timeSent; }
    public void setTimeSent(LocalDateTime timeSent) { this.timeSent = timeSent; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
}
