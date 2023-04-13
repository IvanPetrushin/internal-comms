package internalcomms.Services;

import internalcomms.Entities.TaskEntity;
import internalcomms.Models.Group;
import internalcomms.Models.Task;
import internalcomms.Repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private GroupService groupService;
    public Task create(TaskEntity task, List<Long> groupsID){
        Map<Long, Boolean> groups = new HashMap();
        for (Long l: groupsID) {
            groups.put(l, false);
        }
        TaskEntity entity = taskRepo.save(new TaskEntity(task.getName(), task.getDescription(), task.getDeadline(), task.getPriority(),task.getOwner(), groups));
        return new Task(entity.getId(),entity.getName());
    }
    public Task get(Long id){
        TaskEntity task = taskRepo.findById(id).get();
        Group group =  groupService.get(task.getOwner());
        return new Task(task.getId(),task.getName(), task.getDescription(), task.getDeadline(), task.getPriority(), group, task.getGroups());
    }
    public Long delete(Long id){
        taskRepo.deleteById(id);
        return id;
    }
}
