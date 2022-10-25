package com.company.task3;

public record UserFriend(int userId, int friendId) {
    @Override
    public int userId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserFriend{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                '}';
    }
}
