package africa.semicolon.noStrings.data.models;

import lombok.Data;

@Data
public class Seeker {
    private String id;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;
}


