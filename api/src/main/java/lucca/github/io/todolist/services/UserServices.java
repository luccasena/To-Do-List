package lucca.github.io.todolist.services;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.Entity.User;
import lucca.github.io.todolist.models.EntityDTO.LoginRequest;
import lucca.github.io.todolist.models.EntityDTO.LoginUserDTO;
import lucca.github.io.todolist.models.EntityDTO.UserDTO;
import lucca.github.io.todolist.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    // -----------------------------------------------------------------------------------------------------------------

    public ResponseEntity<UserDTO> getUser(Long id){
        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = foundUser.get();

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getLastname(), user.getCpf(), user.getEmail(), user.getPassword(), user.getTasks());
        return ResponseEntity.ok().body(userDTO);
    }

    public User getUserOptional(Long id){
        Optional<User> foundUser = userRepository.findById(id);

        return foundUser.orElse(null);

    }

    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<UserDTO> userDTOs = new ArrayList<>();

        for(User user : users){
            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getLastname(), user.getCpf(), user.getEmail(), user.getPassword(), user.getTasks());
            userDTOs.add(userDTO);
        }
        return ResponseEntity.ok().body(userDTOs);
    }

    public ResponseEntity<?> createUser(UserDTO userDTO){
        User user = new User(userDTO.name(), userDTO.lastname(), userDTO.cpf(), userDTO.email(), userDTO.password());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public void deleteUser(Long idUser){

        Optional<User> user = userRepository.findById(idUser);

        if(user.isEmpty()){
            ResponseEntity.notFound().build();
            return;
        }

        userRepository.deleteById(idUser);
        ResponseEntity.ok().build();
    }

    public void updateUser(Long idUser, UserDTO userDTO){
        Optional<User> foundUser = userRepository.findById(idUser);

        if(foundUser.isEmpty()){
            ResponseEntity.notFound().build();
            return;
        }

        User user = foundUser.get();

        user.setName(userDTO.name());
        user.setLastname(userDTO.lastname());
        user.setCpf(userDTO.cpf());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());

        userRepository.save(user);
        ResponseEntity.ok().build();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public ResponseEntity<?> login(LoginRequest user_request){

        Optional<User> foundUser = userRepository.findUserByEmailAndPassword(user_request.email(), user_request.password());

        if(foundUser.isEmpty()){
            return ResponseEntity.status(404).body(
                    Map.of(
                            "sucess", false,
                            "message", "E-mail não existe"
                    )
            );
        }

        User user = foundUser.get();

        if(!user_request.password().equals(user.getPassword()) && user_request.email().equals(user.getEmail())){
            return ResponseEntity.status(401).body(
                    Map.of(
                            "sucess", false,
                            "message", "Credênciais Inválidas."
                    )
            );
        }

        LoginUserDTO userDTO = new LoginUserDTO(user.getId(), user.getName());

        return ResponseEntity.ok(userDTO);

    }

    public ResponseEntity<?> findUserByEmail(String email){
        Optional<User> foundUser = userRepository.findByEmail(email);

        if(foundUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = foundUser.get();

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getLastname(), user.getCpf(), user.getEmail(), user.getPassword(), user.getTasks());
        return ResponseEntity.ok().body(userDTO);
    }

}
