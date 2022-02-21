package entities;

public class Notification {

    private Long sendID;
    private String status;
    private String text;

    public Notification() {}

    public Notification(Long sendID) {
        this.sendID = sendID;
        this.status = "New";
    }

    public Long getSendID() {
        return sendID;
    }

    public void setSendID(Long sendID) {
        this.sendID = sendID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
