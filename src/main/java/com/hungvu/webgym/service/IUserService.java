package com.hungvu.webgym.service;

import com.hungvu.webgym.model.User;
import com.hungvu.webgym.request.ChangePasswordRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface IUserService {
    public void changePassword(ChangePasswordRequest request, Principal connectedUser);

    User getUser(String email);
}
