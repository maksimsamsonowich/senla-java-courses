package com.github.controller;

import com.github.dto.UserDto;
import com.github.service.IItemsSecurityExpressions;
import com.github.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user-management")
public class UserController {

    private final IUserService iUserService;

    private final IItemsSecurityExpressions iItemsSecurityExpressions;

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(iUserService.createUser(userDto));
    }

    @GetMapping("{userId}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedAccount(#userId, authentication)")
    public ResponseEntity<UserDto> readUser(@PathVariable Long userId) {
        return ResponseEntity.ok(iUserService.readUser(userId));
    }

    @PutMapping("{userId}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedAccount(#userId, authentication)")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(iUserService.update(userId, userDto));
    }

    @DeleteMapping("{userId}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedAccount(#userId, authentication)")
    public void deleteUser(@PathVariable Long userId) {
        iUserService.deleteUser(userId);
    }

}
