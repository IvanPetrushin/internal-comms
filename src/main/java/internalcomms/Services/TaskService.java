package internalcomms.Services;

import internalcomms.Entities.GroupEntity;
import internalcomms.Entities.TaskEntity;
import internalcomms.Models.Group;
import internalcomms.Models.Task;
import internalcomms.Repositories.GroupRepo;
import internalcomms.Repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    /* TODO
    setName
    setDescription
    setCondition
    setPriority
    setGroup
     */
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private GroupRepo groupRepo;
    public Task create(TaskEntity task, Long groupID){
        task.setGroup(groupRepo.findById(groupID).get());
        TaskEntity entity = taskRepo.save(new TaskEntity(task.getName(), task.getDescription(), task.getTime(), task.getCondition(), task.getPriority(), task.getGroup()));
        return new Task(entity.getId(),entity.getName());
    }
    public Task getOneTask(Long id){
        TaskEntity task = taskRepo.findById(id).get();
        GroupEntity entity = task.getGroup();
        Group group = new Group(entity.getId(), entity.getName());
        return new Task(task.getId(), task.getName(), task.getDescription(), task.getTime(),task.getCondition(), task.getPriority(), group);
    }
    public Long delete(Long id){
        taskRepo.findById(id);
        return id;
    }
}
