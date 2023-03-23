package internalcomms.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Task {
    @NotNull private Long id;
    @NotNull private String name;
    private String description;
    private String time; // Нужен класс для времени
    private String condition; // Нужен класс для состояния задания
    private String priority; // Нужен класс для приоритета
    private Group group;
}
