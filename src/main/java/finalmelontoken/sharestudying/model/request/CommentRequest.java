package finalmelontoken.sharestudying.model.request;

import finalmelontoken.sharestudying.model.entity.Board;
import finalmelontoken.sharestudying.model.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

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
