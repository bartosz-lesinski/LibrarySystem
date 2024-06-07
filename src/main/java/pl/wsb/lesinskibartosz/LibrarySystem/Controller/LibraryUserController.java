package pl.wsb.lesinskibartosz.LibrarySystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.lesinskibartosz.LibrarySystem.model.LibraryUser;
import pl.wsb.lesinskibartosz.LibrarySystem.service.LibraryUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class LibraryUserController {

    private final LibraryUserService libraryUserService;

    public LibraryUserController(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    //get all users
    @GetMapping("")
    public ResponseEntity<List<LibraryUser>> getAllUsers() {
        List<LibraryUser> users = libraryUserService.findAll();
        return ResponseEntity.ok(users);
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        LibraryUser user = libraryUserService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this ID not found");
        }
    }

    // get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        LibraryUser user = libraryUserService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this email not found");
        }
    }

    //get user by phone number
    @GetMapping("/phonenumber/{phonenumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable String phonenumber) {
        LibraryUser user = libraryUserService.findByEmail(phonenumber);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this phone number not found");
        }
    }

    //add user
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody LibraryUser user) {
        LibraryUser addedUser = libraryUserService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
    }

    //delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = libraryUserService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this ID not found");
        }
    }
}
