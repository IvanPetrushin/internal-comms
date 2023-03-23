package internalcomms.Services;

import internalcomms.Entities.QuestionEntity;
import internalcomms.Entities.UserEntity;
import internalcomms.Models.Question;
import internalcomms.Models.User;
import internalcomms.Repositories.QuestionRepo;
import internalcomms.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    /* TODO
    setName
    setText
    setRating
     */
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private UserRepo userRepo;

    public Question create(QuestionEntity question, Long userID){
        question.setUser(userRepo.findById(userID).get());
        QuestionEntity entity = questionRepo.save(new QuestionEntity(question.getName(), question.getText(), question.getRating(), question.getUser()));
        return new Question(entity.getId(),entity.getName());
    }
    public Question getOneQuestion(Long id){
        QuestionEntity question = questionRepo.findById(id).get();
        UserEntity entity = question.getUser();
        User user = new User(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getMail(), entity.getRole(), entity.getDescription());
        return new Question(question.getId(), question.getName(), question.getText(), question.getRating(), user);
    }
    public Long delete(Long id){
        questionRepo.findById(id);
        return id;
    }
}