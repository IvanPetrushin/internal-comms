package internalcomms.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Модель пользователя для отправки JSON файлом
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String username;
    @Column(name = "PASS", nullable = false)
    private String password;
    @Column(name = "MAIL", nullable = false)
    private String mail;
    @Column(name = "GROUP", nullable = false)
    private Group group;
    public User(Long id, String username, Group group) {
        this.id = id;
        this.username = username;
        this.group = group;
    }
}
