package entities;

public class PostOffice {

    private Long postOfficeID;
    private String description;

    public PostOffice() {}
    public PostOffice(Long postOfficeID, String description) {
        this.postOfficeID = postOfficeID;
        this.description = description;
    }

    public Long getPostOfficeID() {
        return postOfficeID;
    }

    public void setPostOfficeID(Long postOfficeID) {
        postOfficeID = postOfficeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PostOffice{" +
                "postOfficeID=" + postOfficeID +
                ", description='" + description + '\'' +
                '}';
    }
}
