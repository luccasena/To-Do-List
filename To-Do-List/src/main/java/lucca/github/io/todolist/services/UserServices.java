package lucca.github.io.todolist.services;

import lombok.RequiredArgsConstructor;
import lucca.github.io.todolist.models.Entity.User;
import lucca.github.io.todolist.models.EntityDTO.UserDTO;
import lucca.github.io.todolist.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    public UserDTO createUserDTO(User user){
        return new UserDTO(user.getId(), user.getName(), user.getLastname(), user.getAge(), user.getEmail(), user.getPassword(), user.getTasks());

    }

    public ResponseEntity<UserDTO> findUserByID(Long id){
        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = createUserDTO(foundUser.get());
        return ResponseEntity.ok().body(userDTO);
    }

    public ResponseEntity<List<UserDTO>>  getAllUsers(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<UserDTO> userDTOs = new ArrayList<>();

        for(User user : users){
            UserDTO userDTO = createUserDTO(user);
            userDTOs.add(userDTO);
        }
        return ResponseEntity.ok().body(userDTOs);
    }

    public void createUser(UserDTO userDTO){
        User user = new User(userDTO.name(), userDTO.lastname(), userDTO.age(), userDTO.email(), userDTO.password());
        userRepository.save(user);
    }

    public ResponseEntity<?> deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<?> updateUser(Long id, UserDTO userDTO){
        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = foundUser.get();

        user.setName(userDTO.name());
        user.setLastname(userDTO.lastname());
        user.setAge(userDTO.age());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}
