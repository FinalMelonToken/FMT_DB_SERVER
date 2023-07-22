package finalmelontoken.sharestudying.domain.board.service.response;

import lombok.Getter;
import finalmelontoken.sharestudying.domain.board.entity.Board;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String member;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.member = entity.getMember().getName();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
