package com.netcracker.travelplanner.security.services;

public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);

    void autoLogout();
}
