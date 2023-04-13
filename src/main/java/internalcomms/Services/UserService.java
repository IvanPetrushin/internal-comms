package internalcomms.Services;

import internalcomms.Entities.QuestionEntity;
import internalcomms.Entities.UserEntity;
import internalcomms.Exceptions.UserNotFoundException;
import internalcomms.Models.Group;
import internalcomms.Models.Question;
import internalcomms.Models.User;
import internalcomms.Repositories.QuestionRepo;
import internalcomms.Repositories.UserRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private GroupService groupService;

    public User registration(UserEntity user) {
        UserEntity entity = userRepo.save(new UserEntity(user.getUsername(), user.getPassword(), user.getMail(), user.getGroup()));
        return new User(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getMail(), groupService.get(entity.getGroup()));
    }

    public User get(Long id) {
        UserEntity user = userRepo.findById(id).get();
        return getUser(user);
    }

    public User findByUsername(String username) throws UserNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return getUser(user);
    }
    @NotNull
    private User getUser(UserEntity user) {
        List<Question> questions = new ArrayList<>();
        int len = user.getQuestions().toArray().length;
        for (int i = 0; i < len; i++) {
            QuestionEntity entity = user.getQuestions().get(i);
            questions.add(new Question(entity.getId(), entity.getName()));
        }
        Group group = groupService.get(user.getGroup());
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getMail(), user.getDescription(), group, questions);
    }
    public UserEntity addQuestion(Question board, Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        List<QuestionEntity> questions = user.getQuestions();
        if (questions == null) questions = new ArrayList<>();
        questions.add(questionRepo.findById(board.getId()).get());
        user.setQuestions(questions);
        return userRepo.save(user);
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}