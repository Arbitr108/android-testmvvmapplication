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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
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
