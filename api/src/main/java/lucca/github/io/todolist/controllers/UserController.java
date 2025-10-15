package lucca.github.io.todolist.controllers;


import jakarta.validation.Valid;
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

    // ----------------------------------------------------------------------------

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getUserById(@PathVariable Long idUser) {
        return userServices.findUserByID(idUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
       return userServices.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
        return userServices.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        userServices.updateUser(id, userDTO);
        return ResponseEntity.ok().build();
    }

    // ----------------------------------------------------------------------------


}
