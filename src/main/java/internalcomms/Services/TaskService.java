package internalcomms.Services;

import internalcomms.Entities.GroupEntity;
import internalcomms.Entities.TaskEntity;
import internalcomms.Models.Task;
import internalcomms.Repositories.GroupRepo;
import internalcomms.Repositories.TaskCondRepo;
import internalcomms.Repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис задания
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private TaskCondRepo taskCondRepo;

    /**
     * Сохранения задания
     * @return Task
     */
    public Task create(Task task){
        var savedTask = taskRepo.save(new TaskEntity(task.getName(), task.getDescription(), task.getDeadline(), task.getPriority()));
        List<TaskEntity.GroupForTask> groups = new ArrayList<>();
        List<GroupEntity> groupEntities = new ArrayList<>();
        var gft = taskCondRepo.save(new TaskEntity.GroupForTask(true, false, task.getOwner().getId(),savedTask));
        groups.add(gft);
        if(groupRepo.existsById(task.getOwner().getId()))
        groupEntities.add(groupRepo.findById(task.getOwner().getId()).get());
        for (var id:task.getGroupsId()) {
            gft = taskCondRepo.save(new TaskEntity.GroupForTask(false, false, id, savedTask));
            groups.add(gft);
            if(groupRepo.existsById(id)) groupEntities.add(groupRepo.findById(id).get());
        }
        savedTask.setGroupsForTask(groups);
        savedTask.setGroups(groupEntities);
        taskRepo.save(savedTask); //Дополнительное сохранение с добавленными полями для групп-исполнителей и группы-создателя
        return new Task(savedTask.getId(), savedTask.getName());
    }
    public Task get(Long id){
        TaskEntity task = taskRepo.findById(id).get();
        return task.entityToModel();
    }

    /**
     * Изменения состояния выполнения задания у группы
     * @return String
     */
    public String putCond(Long id, Long groupID, Boolean cond){
        TaskEntity task = null;
        if(taskRepo.existsById(id)) taskRepo.findById(id).get();
        var groups = task.getGroupsForTask();
        TaskEntity.GroupForTask group = groups.stream().filter(x -> x.getGroupID().equals(groupID)).findFirst().get();
        groups.remove(group);
        group.setCondition(cond);
        groups.add(group);
        taskRepo.save(task);
        return "Состояние задания " +task.getName()+" изменено.";
    }
    public Long delete(Long id){
        taskRepo.deleteById(id);
        return id;
    }
}
