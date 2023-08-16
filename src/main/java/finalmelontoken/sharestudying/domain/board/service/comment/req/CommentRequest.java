package finalmelontoken.sharestudying.domain.board.service.comment.req;

import finalmelontoken.sharestudying.domain.board.entity.Board;
import finalmelontoken.sharestudying.domain.board.entity.Comment;
import finalmelontoken.sharestudying.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {
    private Long id;
    private String comment;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
    //private User user;
    private Board board;

    /* Dto -> Entity */
    public Comment toEntity() {
        Comment comments = Comment.builder()
            .commentId(id)
            .comment(comment)
//            .createdDate(createdDate)
//            .modifiedDate(modifiedDate)
    //        .user(user)
            .board(board)
            .build();
        return comments;
        }
}
