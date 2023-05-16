package internalcomms.Models;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long id;
    private String text;
    private String time;
    private User user;
    private Task task;

    public Message(Long id, String text, String time, User user) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.user = user;
    }
}
