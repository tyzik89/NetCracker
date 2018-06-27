package com.netcracker.travelplanner.security.services;

import com.netcracker.travelplanner.model.entities.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void save(User user);

    public boolean checkImageURL(String url);
}
