package internalcomms.Models;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Question {
    @NonNull private final Long id;
    private final String name;
    private String text;
    private int rating;
    //private User user;
}
