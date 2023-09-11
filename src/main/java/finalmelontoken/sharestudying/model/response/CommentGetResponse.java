package finalmelontoken.sharestudying.model.response;

import lombok.Getter;

import java.util.List;
@Getter
public class CommentGetResponse {
    private Long CommentId;
    private String comment;
//    private String nickname;
    private Long postsId;

    public CommentGetResponse(Long CommentId, String comment,/* String nickname, */Long postsId) {
        this.CommentId = CommentId;
        this.comment = comment;
//        this.nickname = nickname;
        this.postsId = postsId;
    }
}

