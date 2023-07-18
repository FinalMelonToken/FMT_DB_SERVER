package sharestudying.sharestudying.board.entity;

//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sharestudying.sharestudying.member.entity.Member;
//import org.hibernate.annotations.Generated;
//import org.hibernate.annotations.GenerationTime;

//import java.sql.Date;

@Table(name = "board")
@NoArgsConstructor
@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT",name="content", nullable = false)
    private String content;
//    @Generated(GenerationTime.INSERT)
//    @Temporal(TemporalType.DATE)
//    @Column(name = "CREATED_DATE", insertable = false)
//    private Date createdDate;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Member.class)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
