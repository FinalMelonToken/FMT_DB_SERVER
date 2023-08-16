package finalmelontoken.sharestudying.domain.board.entity;
import finalmelontoken.sharestudying.domain.user.entity.User;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comment")
@Entity
public class Comment extends BaseEntity{

    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; // 댓글 내용

//    @Column(name = "created_date")
//    @CreatedDate
//    private LocalDateTime createdDate;
//    @Column(name = "modified_date")
//    @LastModifiedDate
//    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    public void update(String comment) {
        this.comment = comment;
    }
}