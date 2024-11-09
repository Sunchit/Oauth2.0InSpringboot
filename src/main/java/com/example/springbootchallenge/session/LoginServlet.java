package com.example.springbootchallenge.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");  // Unique identifier for the user, e.g., username or user ID
        String password = request.getParameter("password");

        // Authenticate the user (basic check here, use a more secure method in production)
        if (authenticate(userId, password)) {
            HttpSession session = request.getSession();

            // Register the session in the SessionRegistry
            SessionRegistry.addSession(userId, session);

            // Store user ID in session attributes
            session.setAttribute("userId", userId);

            response.getWriter().println("Logged in successfully. Session ID: " + session.getId());
        } else {
            response.getWriter().println("Invalid credentials.");
        }
    }

    private boolean authenticate(String userId, String password) {
        // Dummy authentication logic (replace with real user authentication)
        return "user".equals(userId) && "pass".equals(password);
    }
}