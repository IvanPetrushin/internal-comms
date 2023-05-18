package internalcomms.Controllers;

import internalcomms.Models.Group;
import internalcomms.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest-контроллер запросов для групп
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    /**
     * POST-запрос на создание группы
     */
    @PostMapping
    public ResponseEntity create(@RequestBody Group group){
        try{
            return ResponseEntity.ok(groupService.create(group));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * GET-запрос на получение группы по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(groupService.get(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * GET-запрос на получение группы по названию
     */
    @GetMapping("/name")
    public ResponseEntity findByName(@RequestParam("name") String name){
        try{
            return ResponseEntity.ok(groupService.findByName(name));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * GET-запрос на получение всех групп сохраненных в базе данных
     */
    @GetMapping("/")
    public ResponseEntity getAll(){
        try{
            return ResponseEntity.ok(groupService.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * GET-запрос на получение всех заданий конкретной группы
     */
    @GetMapping("/{id}/tasks")
    public ResponseEntity getTasks(@PathVariable Long id){
        try{
            return ResponseEntity.ok(groupService.getTasks(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * DELETE-запрос на удаление группы по ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(groupService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
