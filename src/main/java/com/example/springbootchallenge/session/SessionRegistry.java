package com.example.springbootchallenge.session;

import jakarta.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

public class SessionRegistry {
    private static final ConcurrentHashMap<String, HttpSession> activeSessions = new ConcurrentHashMap<>();

    // Add a session to the registry
    public static synchronized void addSession(String userId, HttpSession session) {
        // If there's already a session for this user, invalidate it
        HttpSession existingSession = activeSessions.put(userId, session);
        if (existingSession != null) {
            existingSession.invalidate();
        }
    }

    // Remove a session from the registry
    public static synchronized void removeSession(String userId) {
        activeSessions.remove(userId);
    }

    // Check if a session exists for a given user
    public static boolean sessionExists(String userId) {
        return activeSessions.containsKey(userId);
    }
}