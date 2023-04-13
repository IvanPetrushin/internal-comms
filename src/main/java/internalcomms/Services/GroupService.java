package internalcomms.Services;

import internalcomms.Entities.GroupEntity;
import internalcomms.Models.Group;
import internalcomms.Repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    private GroupRepo groupRepo;
    public Group create(GroupEntity group) {
        GroupEntity entity = groupRepo.save(new GroupEntity(group.getName(), group.getDescription()));
        return new Group(entity.getId(),entity.getName());
    }
    public Group get(Long id){
        GroupEntity group = groupRepo.findById(id).get();
        return new Group(group.getId(), group.getName(), group.getDescription(), group.getUsers(), group.getTasks());
    }
    public Long delete(Long id) {
        groupRepo.deleteById(id);
        return id;
    }
}
