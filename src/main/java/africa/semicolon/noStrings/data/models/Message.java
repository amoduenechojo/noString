package africa.semicolon.noStrings.data.models;

import java.util.Random;

public class Message {
    private int senderId;
    private int receiversId;
    private String body;

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId() {
        Random random = new Random();
        this.senderId = random.nextInt();
    }

    public int getReceiversId() {
        return receiversId;
    }

    public void setReceiversId(int receiversId) {
        this.receiversId = receiversId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
