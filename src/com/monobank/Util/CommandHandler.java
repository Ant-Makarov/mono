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
    private static final NotificationService SERVICE = new NotificationService();

    public static void commandExecutor(List<String[]> list) {
        for (String[] el : list) {
            String s = el[0];
            if (s.equals(REGISTRATION)) {
               registration(el);
            } else if(s.equals(CREATE_POST_OFFICE)) {
                postOfficeCreating(el);
            } else if(s.equals(CREATE_PARCEL_SEND)) {
                parcelSendCreating(el);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void registration(String[] el) {
        User user = new User(Long.parseLong(el[1]), el[2], el[3], el[4]);
        FileHandler.outputWriter(user.toString() + " has been successfully registered!");
        UserService userService = new UserService();
        userService.add(user);
        FileHandler.outputWriter("The new user has been successfully added to database!");
    }

    public static void postOfficeCreating(String[] el) {
        PostOffice postOffice = new PostOffice(Long.parseLong(el[1]), el[2]);
        FileHandler.outputWriter(postOffice.toString() + " has been successfully created!");
        PostOfficeService postOfficeService = new PostOfficeService();
        postOfficeService.add(postOffice);
        FileHandler.outputWriter("The new post office has been successfully added to database!");
    }

    public static void parcelSendCreating(String[] el) {
        Long parcelSendID = Long.parseLong(el[1]);
        Long senderID = Long.parseLong(el[2]);
        Long senderPostOfficeID = Long.parseLong(el[3]);
        Long receiverPostOfficeID = Long.parseLong(el[4]);
        String receiverPhoneNumber = el[5];
        String receiverFullName = el[6];
        ParcelSend parcelSend = new ParcelSend(parcelSendID,senderID,senderPostOfficeID,receiverPostOfficeID,
                receiverPhoneNumber, receiverFullName);
        FileHandler.outputWriter(parcelSend.toString() + " has been successfully created!");

        SendParcelService sendParcelService = new SendParcelService();
        sendParcelService.add(parcelSend);
        FileHandler.outputWriter("The new parcel has been successfully added to database!");

        Notification notification = notificationCreating(parcelSend);
        parcelSend.setSendStatus();
        parcelSend.setChangeDate(new Timestamp(System.currentTimeMillis()));
        FileHandler.outputWriter(parcelSend.toString() + " has been successfully updated!");
        notificationUpdating(notification, parcelSend);
        sendParcelService.update(parcelSend);
    }

    public static Notification notificationCreating(ParcelSend parcelSend) {
        Notification notification = new Notification(parcelSend.getParcelSendID());
        FileHandler.outputWriter(notification.toString() + " has been successfully created!");
        SERVICE.add(notification);
        FileHandler.outputWriter("The new notification has been successfully added to database!");
        return notification;
    }

    public static void notificationUpdating(Notification notification,ParcelSend parcelSend) {
        if (parcelSend.getSendStatus().equals("Delivered")) {
            notification.setText("Your parcel has been successfully delivered!");
        } else {
            notification.setText("The recepient did not pick up the parcel! :(");
        }
        SERVICE.update(notification);
        FileHandler.outputWriter(notification.toString() + " has been successfully updated!");
    }

    public static NotificationService getService() {
        return SERVICE;
    }
}
