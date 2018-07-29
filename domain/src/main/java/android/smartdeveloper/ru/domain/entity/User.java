package android.smartdeveloper.ru.domain.entity;

import java.io.Serializable;

public class User implements DomainModel, Serializable {
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

    public User(User user){
        this.name = user.getName();
        this.surname = user.getSurname();
        this.avatarUrl = user.getAvatarUrl();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.id = user.getId();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        User other = (User) obj;

        if (id == null || other.getId() == null) {
            return false;
        } else if (!id.equals(other.getId())) {
            return false;
        }

        return true;
    }
}
