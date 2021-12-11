package com.github.controller;

import com.github.dto.UserDto;
import com.github.metamodel.Roles;
import com.github.service.ICredentialService;
import com.github.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user-management")
public class UserController {

    private final IUserService iUserService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(iUserService.createUser(userDto));
    }

    @Secured( Roles.ADMIN )
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> readUser(@PathVariable Long userId) {
        return ResponseEntity.ok(iUserService.readUser(userId));
    }

    @PutMapping("{userId}")
    @Secured({ Roles.ADMIN, Roles.USER })
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(iUserService.update(userId, userDto));
    }

    @DeleteMapping("{userId}")
    @Secured({ Roles.ADMIN, Roles.USER })
    public void deleteUser(@PathVariable Long userId) {
        iUserService.deleteUser(userId);
    }

}
