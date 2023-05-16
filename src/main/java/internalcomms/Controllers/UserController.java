package internalcomms.Controllers;

import internalcomms.Exceptions.UserNotFoundException;
import internalcomms.Models.User;
import internalcomms.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody User user){
        try{
            return ResponseEntity.ok(userService.registration(user));
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
    public ResponseEntity findByUsername(@RequestParam("username") String username){
        try{
            return ResponseEntity.ok(userService.findByUsername(username));
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/mail")
    public ResponseEntity findByMail(@RequestParam("mail") String mail){
        try{
            return ResponseEntity.ok(userService.findByMail(mail));
        }catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }


}
