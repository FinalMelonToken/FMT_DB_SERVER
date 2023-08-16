package finalmelontoken.sharestudying.domain.board.entity;

import finalmelontoken.sharestudying.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "board")
@NoArgsConstructor
@Entity
@Data
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id", nullable = false)
    private Long boardId;

    @Column(name="title", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", name="content", nullable = false)
    private String content;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // user_id 컬럼과 연결
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user; // User 엔티티와 연결
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
