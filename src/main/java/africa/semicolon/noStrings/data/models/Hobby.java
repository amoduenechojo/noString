package africa.semicolon.noStrings.data.models;

import java.util.Random;

public class Hobby {
    private int id;
    private String name;


    public int getId() {
        return id;
    }

    public String getName(String name) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId() {
        Random random = new Random();
        this.id = random.nextInt();
    }
}


