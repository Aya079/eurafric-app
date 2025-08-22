package com.eurafric.entreprise.controller;

import com.eurafric.entreprise.entity.Notification;
import com.eurafric.entreprise.repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // ✅ Récupérer toutes les notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // ✅ Récupérer toutes les notifications d’un utilisateur
    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUser(@PathVariable Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // ✅ Créer une notification
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    // ✅ Marquer comme lue
    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée"));
        notification.setRead(true);
        return notificationRepository.save(notification);
    }

    // ✅ Supprimer une notification
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationRepository.deleteById(id);
    }
}
