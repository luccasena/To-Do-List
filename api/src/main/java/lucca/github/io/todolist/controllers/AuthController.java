package lucca.github.io.todolist.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.EntityDTO.LoginRequest;
import lucca.github.io.todolist.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody @Valid LoginRequest user) {
        return userServices.login(user);
    }
}
