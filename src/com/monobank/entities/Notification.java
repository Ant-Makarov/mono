package entities;

public class Notification {

    private Long sendID;
    private String text;
    private String status;

    public Notification() {}

    public Notification(Long sendID, String text, String status) {
        this.sendID = sendID;
        this.text = text;
        this.status = status;
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
