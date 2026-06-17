package africa.semicolon.noStrings.data.models;

import lombok.Data;

@Data
public class Message {
    private int senderId;
    private int receiversId;
    private String body;

}
