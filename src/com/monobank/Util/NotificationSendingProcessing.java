package Util;

import Services.NotificationService;
import entities.Notification;

import java.util.List;

public class NotificationSendingProcessing implements Runnable {
    private NotificationService notificationService;

    public NotificationSendingProcessing(NotificationService service) {
        notificationService = service;
    }

    @Override
    public void run() {
        while (checkNewNotifications()) {
            List<Notification> notificationList = notificationService.getAll();
            for (Notification notification : notificationList) {
                if (notification.getText() != null && notification.getStatus().equals("New")) {
                    notification.setStatus("Sent");
                    notificationService.update(notification);
                    FileHandler.outputWriter(notification.toString() + "message has been successfully sent!");
                }
            }
        }
    }

    public boolean checkNewNotifications() {
        boolean hasNew = false;
        while (!checkListSize()) {
            checkListSize();
        }
        List<Notification> notificationList = notificationService.getAll();
        for (Notification notification : notificationList) {
            if (notification.getStatus().equals("New")) {
                hasNew = true;
            }
        }
        return hasNew;
    }

    public boolean checkListSize() {
        boolean hasElements = false;
        try {
            Thread.sleep(1000);
            List<Notification> notificationList = notificationService.getAll();
            if (notificationList.size() > 0) {
                hasElements = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return hasElements;
        }
    }
}

