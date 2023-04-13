package internalcomms.Controllers;

import internalcomms.Entities.UserEntity;
import internalcomms.Exceptions.UserNotFoundException;
import internalcomms.Models.User;
import internalcomms.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String Hello() {
        return "Hello";
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody User user){
        try{
            return ResponseEntity.ok(userService.registration(user.getUsername(), user.getPassword()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.get(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/username")
    public ResponseEntity findByUsername(@RequestParam(name="username") String username){
        try{
            return ResponseEntity.ok(userService.findByUsername(username));
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    //@PutMapping("/{id}/question")
    //public ResponseEntity addQuestion(@RequestBody Question question, @PathVariable Long id){
    //    try{
    //        return ResponseEntity.ok(userService.addQuestion(question, id));
    //    }catch (UserNotFoundException e){
    //        return ResponseEntity.badRequest().body(e.getMessage());
    //    }catch (Exception e){
    //        return ResponseEntity.badRequest().body("Error");
    //    }
    //}

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }


}
