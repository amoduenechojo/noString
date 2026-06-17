package africa.semicolon.noStrings.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor

public class Profile {

    @Id
    private String profileId;
    private String username;
    private String bio;

    public Profile(String username, String bio) {
        this.username = validateUsername(username);
        this.bio = bio;
    }

    private String validateUsername(String username) {
        if (username == null || username.trim().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long.");
        }
        return username;
    }

    public void updateBio(String newBio) {
        if (newBio != null && newBio.length() > 150) {
            throw new IllegalArgumentException("Bio cannot exceed 150 characters.");
        }
        this.bio = newBio;
    }
}