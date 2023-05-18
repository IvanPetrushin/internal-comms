package internalcomms.Controllers;

import internalcomms.Models.Task;
import internalcomms.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest-контроллер запросов для сообщений
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * POST-запрос на создание задания. На вход название, описание, дедлайн, приоритет, ID группы-создателя и групп-исполнителей
     */
    @PostMapping
    public ResponseEntity create(@RequestBody Task task){
        try{
            return ResponseEntity.ok(taskService.create(task));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * GET-запрос на получение задания по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(taskService.get(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * PUT-запрос на изменения состояния выполнения задания у группы
     */
    @PutMapping("/{id}")
    public ResponseEntity putCondition(@PathVariable Long id, @RequestParam("groupID") Long groupID, @RequestParam("condition") Boolean cond){
        try{
            return ResponseEntity.ok(taskService.putCond(id,groupID, cond));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * DELETE-запрос на удаление задания по ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        try {
            return ResponseEntity.ok(taskService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
