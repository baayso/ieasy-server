package com.baayso.springboot.netty.session;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session {

    private Long   userId;
    private String username;

    public Session() {
    }

    public Session(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(userId, session.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return this.userId + ":" + this.username;
    }

}
