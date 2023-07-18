package sharestudying.sharestudying.board.service.dto;

import lombok.Getter;
import sharestudying.sharestudying.board.entity.Board;

@Getter
public class BoardListResponseDto {
    private Long id;
    private String member;
    private String title;

    public BoardListResponseDto(Board entity) {
        this.id = entity.getId();
        this.member = entity.getMember().getName();
        this.title = entity.getTitle();
    }
}