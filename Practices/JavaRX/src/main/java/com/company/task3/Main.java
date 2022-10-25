package com.company.task3;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static List<UserFriend> userFriends;

    public static void main(String[] args) {
        initArr();

        System.out.println("All user-friend relationships");
        getObservableUserFriends().subscribe(e -> System.out.println(e.toString()));

        System.out.println("Find by user id");
        getFriends(3).subscribe(e -> System.out.println(e.toString()));
    }

    private static Observable<UserFriend> getFriends(int userId) {
        UserFriend[] filteredArr = userFriends
                .stream()
                .filter(userFriend -> userFriend.userId() == userId)
                .toArray(UserFriend[]::new);

        return Observable.fromArray(filteredArr);
    }

    private static Observable<UserFriend> getObservableUserFriends() {
        return Observable.fromArray(userFriends.toArray(UserFriend[]::new));
    }

    private static void initArr() {
        Set<UserFriend> userFriendSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            int userId = randomInt(1, 10);
            int friendId = randomInt(1, 10);

            if (userId != friendId) {
                userFriendSet.add(new UserFriend(userId, friendId));
                userFriendSet.add(new UserFriend(friendId, userId));
            }
        }

        userFriends = new ArrayList<>(userFriendSet);
    }

    private static Integer randomInt(int min, int max) {
        return (int) Math.round(Math.random() * (max - min) + min);
    }
}
