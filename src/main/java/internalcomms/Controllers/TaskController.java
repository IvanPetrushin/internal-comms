package internalcomms.Controllers;

import internalcomms.Entities.TaskEntity;
import internalcomms.Models.Task;
import internalcomms.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity create(@RequestBody Task task, @RequestParam(name="groupID") Long groupID){
        try{
            return ResponseEntity.ok(taskService.create(new TaskEntity(task.getName(), task.getDescription(), task.getTime(), task.getCondition(), task.getPriority()), groupID));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneTask(@PathVariable Long id){
        try{
            return ResponseEntity.ok(taskService.getOneTask(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        try {
            return ResponseEntity.ok(taskService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
