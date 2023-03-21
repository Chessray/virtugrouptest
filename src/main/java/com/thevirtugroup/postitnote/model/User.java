package com.thevirtugroup.postitnote.model;

import java.util.Objects;

public class User {

    private Long id;
    private String username;
    private String password;
    private String name;


    public Long getId() {
        return id;
    }

    public User withId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
