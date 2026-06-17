package africa.semicolon.noStrings.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Hobby {
    @Id
    private int id;
    private String name;

    }


