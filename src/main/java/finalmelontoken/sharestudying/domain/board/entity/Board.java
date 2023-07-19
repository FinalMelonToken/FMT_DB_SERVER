package finalmelontoken.sharestudying.domain.board.entity;

import finalmelontoken.sharestudying.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
