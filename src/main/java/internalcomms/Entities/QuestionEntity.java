package internalcomms.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_entity")
public class QuestionEntity {
     private Long id;
    private String name;
    private String text;
    private int rating;
    private UserEntity user;

    public QuestionEntity(String name, String text, int rating) {
        this.name = name;
        this.text = text;
        this.rating = rating;
    }

    public QuestionEntity(String name, String text, int rating, UserEntity user) {
        this.name = name;
        this.text = text;
        this.rating = rating;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    public UserEntity getUser() {
        return user;
    }
    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }
    @Column
    public String getName() {
        return name;
    }
    @Column
    public String getText() {
        return text;
    }
    @Column
    public int getRating() {
        return rating;
    }
}
