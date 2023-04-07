package internalcomms.Services;

import internalcomms.Entities.GroupEntity;
import internalcomms.Entities.TaskEntity;
import internalcomms.Entities.UserEntity;
import internalcomms.Models.Group;
import internalcomms.Models.Task;
import internalcomms.Models.User;
import internalcomms.Repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    /* TODO
    setName
    setDescription
    addUser
    deleteUser
    addTask
     */
    @Autowired
    private GroupRepo groupRepo;
    public Group create(GroupEntity group) {
        GroupEntity entity = groupRepo.save(new GroupEntity(group.getName(), group.getDescription()));
        return new Group(entity.getId(),entity.getName());
    }
    public Group getOneGroup(Long id){
        GroupEntity group = groupRepo.findById(id).get();
        List<User> users = new ArrayList<>();
        if(group.getUsers() != null) {
            int len = group.getUsers().toArray().length;
            for (int i = 0; i < len; i++) {
                UserEntity entity = group.getUsers().get(i);
                users.add(new User(entity.getId(), entity.getUsername()));
            }
        }
        List<Task> tasks = new ArrayList<>();
        if(group.getTasks() != null) {
            int len = group.getTasks().toArray().length;
            for (int i = 0; i < len; i++) {
                TaskEntity entity = group.getTasks().get(i);
                tasks.add(new Task(entity.getId(), entity.getName()));
            }
        }
        return new Group(group.getId(), group.getName(), group.getDescription(), users, tasks);
    }
    public Long delete(Long id) {
        groupRepo.deleteById(id);
        return id;
    }
}
