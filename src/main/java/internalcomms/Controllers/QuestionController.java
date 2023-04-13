package internalcomms.Controllers;

import internalcomms.Entities.QuestionEntity;
import internalcomms.Models.Question;
import internalcomms.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity create(@RequestBody Question question, @RequestParam(name="userID") Long userID){
        try{
            return ResponseEntity.ok(questionService.create(new QuestionEntity(question.getName(), question.getText(), question.getRating()), userID));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneQuestion(@PathVariable Long id){
        try{
            return ResponseEntity.ok(questionService.get(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        try {
            return ResponseEntity.ok(questionService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
