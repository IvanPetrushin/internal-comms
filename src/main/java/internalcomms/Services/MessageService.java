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

/**
 * Сервис для сообщений
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskRepo taskRepo;

    /**
     * Сохранение файла. На вход ожидается
     * @return String
     */
    public String create(Message message){
        TaskEntity task;
        UserEntity user;
        if(taskRepo.existsById(message.getTask().getId())&&
                userRepo.existsById(message.getUser().getId())) {
            task = taskRepo.findById(message.getTask().getId()).get();
            user = userRepo.findById(message.getUser().getId()).get();
            messageRepo.save(new MessageEntity(message.getText(), message.getTime(), user, task));
            return "Message created";
        }
        return "Message not created";
    }
    public Message get(Long id){
        MessageEntity message;
        if(messageRepo.existsById(id)) message =  messageRepo.findById(id).get();
        else return null;
        return message.entityToModel();
    }
    public Long delete(Long id){
        messageRepo.deleteById(id);
        return id;
    }
}
