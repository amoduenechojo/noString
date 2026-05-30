package africa.semicolon.noStrings.dtos;

import africa.semicolon.noStrings.data.models.Gender;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterSeekerRequest {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Gender gender;
}


