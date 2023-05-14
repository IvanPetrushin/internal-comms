package internalcomms.Services;

import internalcomms.Entities.MessageEntity;
import internalcomms.Entities.TaskEntity;
import internalcomms.Entities.UserEntity;
import internalcomms.Models.Message;
import internalcomms.Repositories.MessageRepo;
import internalcomms.Repositories.TaskRepo;
import internalcomms.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskRepo taskRepo;
    public String create(Message message){
        TaskEntity task = taskRepo.findById(message.getTask().getId()).get();
        UserEntity user = userRepo.findById(message.getUser().getId()).get();
        messageRepo.save(new MessageEntity(message.getText(),message.getTime(),user,task));
        return "Message created";
    }
    public Message get(Long id){
        MessageEntity message = messageRepo.findById(id).get();
        return message.entityToModel();
    }
    public Long delete(Long id){
        messageRepo.deleteById(id);
        return id;
    }
}
