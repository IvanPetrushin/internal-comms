package internalcomms.Services;

import internalcomms.Entities.GroupEntity;
import internalcomms.Entities.UserEntity;
import internalcomms.Exceptions.UserNotFoundException;
import internalcomms.Models.User;
import internalcomms.Repositories.GroupRepo;
import internalcomms.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GroupRepo groupRepo;

    public String registration(User user) {
        GroupEntity group = groupRepo.findById(user.getGroup().getId()).get();
        userRepo.save(new UserEntity(user.getUsername(), user.getPassword(), user.getMail(), group));
        return "User "+user.getUsername()+" created";
    }

    public User get(Long id) {
        UserEntity user = userRepo.findById(id).get();
        return user.entityToModel();
    }

    public User findByUsername(String username) throws UserNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user.entityToModel();
    }

    public User login(String mail, String password) throws UserNotFoundException {
        UserEntity user = userRepo.findByMail(mail);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        if(!Objects.equals(user.getPassword(), password)){
            throw new UserNotFoundException("User not found");
        }
        return user.entityToModel();
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}