package Util;


import Services.NotificationService;
import Services.PostOfficeService;
import Services.SendParcelService;
import Services.UserService;
import entities.*;

import java.sql.Timestamp;
import java.util.List;

public class CommandHandler {

    private static final String REGISTRATION = "REGISTRATION";
    private static final String CREATE_POST_OFFICE = "CREATE_POST_OFFICE";
    private static final String CREATE_PARCEL_SEND = "CREATE_PARCEL_SEND";

    public static void commandExecutor(List<String[]> list) {
        for (String[] el : list) {
            String s = el[0];
            if (s.equals(REGISTRATION)) {
               registration(el);
               FileHandler.outputWriter("The new user has been succesfully registrated and added to database!");
            } else if(s.equals(CREATE_POST_OFFICE)) {
                postOfficeCreating(el);
                FileHandler.outputWriter("The new post office has been succesfully created and added to database!");
            } else if(s.equals(CREATE_PARCEL_SEND)) {
                parcelSendCreating(el);
                FileHandler.outputWriter("The new parcel send has been succesfully created and added to database!");
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void registration(String[] el) {
        UserService userService = new UserService();
        userService.add(new User(Long.parseLong(el[1]),el[2],el[3],el[4]));
    }

    public static void postOfficeCreating(String[] el) {
        PostOfficeService postOfficeService = new PostOfficeService();
        postOfficeService.add(new PostOffice(Long.parseLong(el[1]),el[2]));
    }

    public static void parcelSendCreating(String[] el) {
        SendParcelService sendParcelService = new SendParcelService();
        Long parcelSendID = Long.parseLong(el[1]);
        Long senderID = Long.parseLong(el[2]);
        Long senderPostOfficeID = Long.parseLong(el[3]);
        Long receiverPostOfficeID = Long.parseLong(el[4]);
        String receiverPhoneNumber = el[5];
        String receiverFullName = el[6];
        ParcelSend parcelSend = new ParcelSend(parcelSendID,senderID,senderPostOfficeID,receiverPostOfficeID,
                receiverPhoneNumber, receiverFullName);
        sendParcelService.add(parcelSend);
        Notification notification = notificationCreating(parcelSend);
        parcelSend.setSendStatus();
        parcelSend.setChangeDate(new Timestamp(System.currentTimeMillis()));
        notificationUpdating(notification, parcelSend);
        sendParcelService.update(parcelSend);
    }

    public static Notification notificationCreating(ParcelSend parcelSend) {
        NotificationService notificationService = new NotificationService();
        Notification notification = new Notification(parcelSend.getParcelSendID());
        notificationService.add(notification);
        return notification;
    }

    public static synchronized void notificationUpdating(Notification notification,ParcelSend parcelSend) {
        if (parcelSend.getSendStatus().equals("Delivered")) {
            notification.setText("Your parcel has been succesfully delivered!");
        } else {
            notification.setText("Your parcel hasn't been delivered!");
        }
        NotificationService notificationService = new NotificationService();
        notificationService.update(notification);
    }

}
