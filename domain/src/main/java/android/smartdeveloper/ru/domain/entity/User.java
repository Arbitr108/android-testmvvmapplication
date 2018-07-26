package android.smartdeveloper.ru.domain.entity;

import java.io.Serializable;

public class User implements DomainModel, Serializable{
    private String name;
    private String surname;
    private String avatarUrl;
    private Gender gender;
    private int age;
    private String id;

    public User(String name, String surname, String avatarUrl, Gender gender, int age) {
       this(name, surname, avatarUrl, gender, age, null);
    }

    public User(String name, String surname, String avatarUrl, Gender gender, int age, String id) {
        this.name = name;
        this.surname = surname;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }
}
