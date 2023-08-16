package finalmelontoken.sharestudying.domain.board.service.comment.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentUpdateRequest {
    private String comment;
    private LocalDateTime modifiedDate = LocalDateTime.now();
    @Builder
    public CommentUpdateRequest(String comment) {
        this.comment = comment;
    }
}