package internalcomms.Services;

import internalcomms.Entities.GroupEntity;
import internalcomms.Models.Group;
import internalcomms.Repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepo groupRepo;
    public String create(Group group) {
        groupRepo.save(new GroupEntity(group.getName(), group.getDescription(), group.getCreatable()));
        return"Group " + group.getName() + " created";
    }
    public Group get(Long id){
        GroupEntity group = groupRepo.findById(id).get();
        return group.entityToModel();
    }
    public Group findByName(String name){
        GroupEntity group = groupRepo.findByName(name);
        return group.entityToModel();
    }
    public List<Group> getAll(){
        List<Group> newGroups = new ArrayList<>();
        var groups = groupRepo.findAll();
        for(var g: groups){
            newGroups.add(g.entityToModel());
        }
        return newGroups;
    }
    public Group getTasks(Long id){
        GroupEntity group = groupRepo.findById(id).get();
        return group.entityToModel();
    }
    public Long delete(Long id) {
        groupRepo.deleteById(id);
        return id;
    }
}
