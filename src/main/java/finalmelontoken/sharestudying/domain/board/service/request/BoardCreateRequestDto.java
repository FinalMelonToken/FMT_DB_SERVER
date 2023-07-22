package finalmelontoken.sharestudying.domain.board.service.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import finalmelontoken.sharestudying.domain.board.entity.Board;

import org.springframework.web.util.HtmlUtils;

@Getter
@NoArgsConstructor
public class BoardCreateRequestDto {
    private String title;
    private String content;
    private Long memberId;
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(HtmlUtils.htmlEscape(content)) // HTML 태그 이스케이프 처리
                .build();
    }

    @Builder
    public BoardCreateRequestDto(Long memberId, String title, String content) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }
}