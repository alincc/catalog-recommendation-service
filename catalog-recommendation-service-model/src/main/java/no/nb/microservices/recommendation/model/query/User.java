package no.nb.microservices.recommendation.model.query;

import java.util.Collection;

public class User {
    private String userId;
    private int age;
    private String gender;
    private Collection<String> interests;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Collection<String> getInterests() {
        return interests;
    }

    public void setInterests(Collection<String> interests) {
        this.interests = interests;
    }
}
