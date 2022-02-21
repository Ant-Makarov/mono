package entities;

import java.sql.Timestamp;

public class ParcelSend {

    private Long parcelSendID;
    private Long senderID;
    private Long senderPostOffice;
    private Long receiverPostOffice;
    private String receiverPhoneNumber;
    private String receiverFullName;
    private String sendStatus;
    private Timestamp creationDate;
    private Timestamp changeDate;

    public ParcelSend() {}

    public ParcelSend(Long parcelSendID, Long senderID, Long senderPostOffice, Long receiverPostOffice,
                      String receiverPhoneNumber, String receiverFullName) {
        this.parcelSendID = parcelSendID;
        this.senderID = senderID;
        this.senderPostOffice = senderPostOffice;
        this.receiverPostOffice = receiverPostOffice;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.receiverFullName = receiverFullName;
        sendStatus = "NEW";
        creationDate = new Timestamp(System.currentTimeMillis());
    }

    public Long getParcelSendID() {
        return parcelSendID;
    }

    public void setParcelSendID(Long parcelSendID) {
        this.parcelSendID = parcelSendID;
    }

    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public Long getSenderPostOffice() {
        return senderPostOffice;
    }

    public void setSenderPostOffice(Long senderPostOffice) {
        this.senderPostOffice = senderPostOffice;
    }

    public Long getReceiverPostOffice() {
        return receiverPostOffice;
    }

    public void setReceiverPostOffice(Long receiverPostOffice) {
        this.receiverPostOffice = receiverPostOffice;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getReceiverFullName() {
        return receiverFullName;
    }

    public void setReceiverFullName(String receiverFullName) {
        this.receiverFullName = receiverFullName;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Timestamp changeDate) {
        this.changeDate = changeDate;
    }
}
