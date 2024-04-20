package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.CartItem;
import com.hungvu.webgym.model.User;
import com.hungvu.webgym.request.ChangePasswordRequest;
import com.hungvu.webgym.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request,
                                            Principal connectedUser){
        try {
            iUserService.changePassword(request, connectedUser);
            return ResponseEntity.ok("Đổi mật khẩu thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            User user = iUserService.getUser(email);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }
}
