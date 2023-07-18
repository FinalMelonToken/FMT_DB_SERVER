package sharestudying.sharestudying.board.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sharestudying.sharestudying.board.entity.Board;

import org.springframework.web.util.HtmlUtils;
import sharestudying.sharestudying.member.entity.Member;

@Getter
@NoArgsConstructor
public class BoardCreateRequestDto {
    private String title;
    private String content;
    private Member member;
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(HtmlUtils.htmlEscape(content)) // HTML 태그 이스케이프 처리
                .build();
    }

    @Builder
    public BoardCreateRequestDto(Member member, String title, String content) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}