package africa.semicolon.noStrings.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class RegisterSeekerResponse {
    private String id;
    private String message;
    private boolean isSuccessful;
}

