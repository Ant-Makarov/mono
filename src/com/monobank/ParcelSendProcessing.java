//import Services.NotificationService;
//import Util.CommandHandler;
//import entities.Notification;
//import entities.ParcelSend;
//
//public class ParcelSendProcessing extends Thread {
//    private Notification notification;
//    private boolean sendStatus;
//
//    public ParcelSendProcessing(Notification notification) {
//        this.notification = notification;
//    }
//
//    public void setSendStatus(ParcelSend parcelSend) {
//        if (parcelSend.getSendStatus().equals("New")) {
//            this.sendStatus = true;
//        } else {
//            this.sendStatus = false;
//        }
//    }
//
//    @Override
//    public void run() {
//        while(sendStatus) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        CommandHandler.notificationUpdating();
//        if (notification.getText() != null) {
//            NotificationService notificationService = new NotificationService();
//            notification.setStatus("Sended");
//            notificationService.update(notification);
//        }
//    }
//}
