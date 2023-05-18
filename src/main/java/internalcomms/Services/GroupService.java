package internalcomms.Services;

import internalcomms.Entities.GroupEntity;
import internalcomms.Models.Group;
import internalcomms.Repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для групп
 */
@Service
public class GroupService {
    @Autowired
    private GroupRepo groupRepo;

    /**
     * Сохраняет группу в базу данных
     * @return Строка, информирующая о создании группы
     */
    public String create(Group group) {
        groupRepo.save(new GroupEntity(group.getName(), group.getDescription(), group.getCreatable()));
        return "Group " + group.getName() + " created";
    }

    /**
     * Возвращает группу по ID
     * @return Group
     */
    public Group get(Long id){
        GroupEntity group;
        if(groupRepo.existsById(id)) group = groupRepo.findById(id).get();
        else return null;
        return group.entityToModel();
    }

    /**
     * Возвращает группу по названию
     * @return Group
     */
    public Group findByName(String name){
        GroupEntity group = groupRepo.findByName(name);
        return group.entityToModel();
    }

    /**
     * Возвращает все группы сохраненные из базы данных
     * @return List<Group>
     */
    public List<Group> getAll(){
        List<Group> newGroups = new ArrayList<>();
        var groups = groupRepo.findAll();
        for(var g: groups){
            newGroups.add(g.entityToModel());
        }
        return newGroups;
    }

    /**
     * Удаляет группу
     * @return id
     */
    public Long delete(Long id) {
        groupRepo.deleteById(id);
        return id;
    }
}
