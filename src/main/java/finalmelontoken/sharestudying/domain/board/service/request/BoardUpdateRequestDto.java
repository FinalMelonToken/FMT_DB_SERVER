package finalmelontoken.sharestudying.domain.board.service.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public BoardUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}