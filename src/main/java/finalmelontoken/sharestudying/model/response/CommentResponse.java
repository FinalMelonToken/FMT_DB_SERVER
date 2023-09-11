package finalmelontoken.sharestudying.model.response;

import finalmelontoken.sharestudying.model.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {

    private Long id;
    private String comment;
//    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
private String nickname;
private Long postsId;

    /* Entity -> Dto*/
    public CommentResponse(Comment comment) {
        this.id = comment.getCommentId();
        this.comment = comment.getComment();
//    this.createdDate = comment.getCreatedDate();
//    this.modifiedDate = comment.getModifiedDate();
        this.nickname = comment.getUser().getName();
        this.postsId = comment.getBoard().getBoardId();
    }
}