package finalmelontoken.sharestudying.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import finalmelontoken.sharestudying.model.entity.Board;

import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardCreateRequestDto {
    private String title;
    private String content;
    private String name;
    private Long userId; // 추가: user_id
    private Long boardId; // 추가: board_id
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(HtmlUtils.htmlEscape(content)) // HTML 태그 이스케이프 처리
                .build();
    }

    @Builder
    public BoardCreateRequestDto(String name, String title, String content, Long userId, Long boardId) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.userId = userId;
        this.boardId = boardId;
    }
}