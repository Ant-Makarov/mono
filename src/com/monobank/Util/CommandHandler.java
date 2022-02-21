package Util;


import Services.PostOfficeService;
import Services.SendParcelService;
import Services.UserService;
import entities.*;

import java.util.List;

public class CommandHandler {

    private static final String REGISTRATION = "REGISTRATION";
    private static final String CREATE_POST_OFFICE = "CREATE_POST_OFFICE";
    private static final String CREATE_PARCEL_SEND = "CREATE_PARCEL_SEND";

    public static void commandExecutor(List<String[]> list) {
        for (String[] el : list) {
            String s = el[0];
            if (s.equals(REGISTRATION)) {
               UserService userService = new UserService();
               userService.add(new User(Long.parseLong(el[1]),el[2],el[3],el[4]));
               FileHandler.outputWriter("The new user has been succesfully registrated and added to database!");
            } else if(s.equals(CREATE_POST_OFFICE)) {
                PostOfficeService postOfficeService = new PostOfficeService();
                postOfficeService.add(new PostOffice(Long.parseLong(el[1]),el[2]));
                FileHandler.outputWriter("The new post office has been succesfully created and added to database!");
            } else if(s.equals(CREATE_PARCEL_SEND)) {
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
                FileHandler.outputWriter("The new parcel send has been succesfully created and added to database!");
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

}
