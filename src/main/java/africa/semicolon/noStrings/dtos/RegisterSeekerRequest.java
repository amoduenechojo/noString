package africa.semicolon.noStrings.dtos;

import africa.semicolon.noStrings.data.models.Gender;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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


