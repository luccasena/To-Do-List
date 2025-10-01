package lucca.github.io.todolist.controllers;


import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.EntityDTO.UserDTO;
import lucca.github.io.todolist.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServices userServices;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userServices.findUserByID(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
       return ResponseEntity.ok(userServices.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        userServices.createUser(userDTO);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userServices.deleteUser(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userServices.updateUser(id, userDTO);
        return ResponseEntity.ok().build();}

}
