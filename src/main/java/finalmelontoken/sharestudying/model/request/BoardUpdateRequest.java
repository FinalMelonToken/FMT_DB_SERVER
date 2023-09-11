package finalmelontoken.sharestudying.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardUpdateRequest {
    private String title;
    private String content;
    private LocalDateTime modifiedDate = LocalDateTime.now();


    @Builder
    public BoardUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}