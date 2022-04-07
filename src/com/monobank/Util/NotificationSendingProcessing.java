package Util;

import Services.NotificationService;
import Util.FileHandler;
import entities.Notification;

import java.util.List;

public class NotificationSendingProcessing implements Runnable {
    private NotificationService notificationService;

    public NotificationSendingProcessing(NotificationService service) {
        notificationService = service;
    }
    @Override
    public void run() {
        while (checkNotifications()) {
            List<Notification> notificationList = notificationService.getAll();
            for (Notification notification : notificationList) {
                if (notification.getText() != null && notification.getStatus().equals("New")) {
                    notification.setStatus("Sent");
                    notificationService.update(notification);
                    FileHandler.outputWriter("The message has been successfully sent!");
                }
            }
        }
    }
    public boolean checkNotifications(){
        boolean isNew = false;
        int counterOfNews = 0;
        while(!checkList()) {
            checkList();
        }
        List<Notification> notificationList = notificationService.getAll();
        for (Notification notification : notificationList) {
            if (notification.getStatus().equals("New")) {
                counterOfNews++;
            }
        }
        if(counterOfNews > 0) {
            isNew = true;
        }
        return isNew;
    }
    public boolean checkList() {
        boolean temp = false;
        List<Notification> notificationList = notificationService.getAll();
        if (notificationList.size() > 0) {
            temp = true;
        }
        return temp;
    }
}

