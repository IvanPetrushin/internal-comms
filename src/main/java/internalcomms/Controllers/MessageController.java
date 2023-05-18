package internalcomms.Controllers;

import internalcomms.Models.Message;
import internalcomms.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest-контроллер запросов для сообщений
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * POST-запрос на создание сообщения. На вход ожидается текст, время, ID пользователя и группы
     */
    @PostMapping
    public ResponseEntity create(@RequestBody Message message){
        try{
            return ResponseEntity.ok(messageService.create(message));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * GET-запрос на получение сообщения по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        try{
            return ResponseEntity.ok(messageService.get(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    /**
     * DELETE-запрос на удаление сообщения по ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(messageService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
