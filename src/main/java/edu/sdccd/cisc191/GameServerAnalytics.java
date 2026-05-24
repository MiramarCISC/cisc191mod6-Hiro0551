package edu.sdccd.cisc191;

import java.util.*;
import java.util.stream.Collectors;

public class GameServerAnalytics {

    public static List<String> findTopNUsernamesByRating(Collection<PlayerAccount> players, int n) {
        // use a stream pipeline
        return players.stream()
                .sorted(Comparator.comparingInt(PlayerAccount::rating).reversed())
                .limit(n).map(PlayerAccount::username).toList();
    }

    public static Map<String, Double> averageRatingByRegion(Collection<PlayerAccount> players) {
        // use groupingBy + averagingInt
        return players.stream().collect(Collectors.groupingBy(PlayerAccount::region,
                Collectors.averagingInt(PlayerAccount::rating)));
    }

    public static Set<String> findDuplicateUsernames(Collection<PlayerAccount> players) {
        // use collections and/or streams
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (PlayerAccount player : players) {
            if (!seen.add(player.username())) {
                duplicates.add(player.username());
            }
        }

        return duplicates;
    }

    public static Map<String, List<String>> groupUsernamesByTier(Collection<PlayerAccount> players) {
        // use groupingBy and mapping
        return players.stream().collect(Collectors.groupingBy(GameServerAnalytics::tierFor,
                Collectors.mapping(PlayerAccount::username, Collectors.toList())));
    }

    public static Map<String, List<String>> buildRecentMatchSummariesByPlayer(Collection<MatchRecord> matches) {
        // TODO: use a Map + collection logic or a stream-based approach
        Map<String, List<String>> result = new java.util.HashMap<>();

        for (MatchRecord match : matches) {
            String summary = match.summary();

            result.computeIfAbsent(
                    match.playerOne().username(), k -> new java.util.ArrayList<>()).add(summary);

            result.computeIfAbsent(
                    match.playerTwo().username(), k -> new java.util.ArrayList<>()).add(summary);
        }

        return result;
    }

    public static <T> T pickHigherRated(T first, T second, Comparator<T> comparator) {
        // TODO: implement using the comparator
        if (comparator.compare(first, second) >= 0) {
            return first;
        }

        return second;
    }

    public static String tierFor(PlayerAccount player) {
        if (player.rating() < 1000) return "Bronze";
        if (player.rating() < 1400) return "Silver";
        return "Gold";
    }
}
