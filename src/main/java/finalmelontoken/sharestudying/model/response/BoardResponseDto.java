package finalmelontoken.sharestudying.model.response;

import finalmelontoken.sharestudying.model.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Integer viewCount;

    @Builder
    public BoardResponseDto(Long id, String title, String content, LocalDateTime createTime, LocalDateTime modifyTime, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.viewCount = viewCount;
    }

    // Board 객체로부터 생성하는 생성자 추가
    public BoardResponseDto(Board board) {
        this.id = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createTime = board.getCreatedDate();
        this.modifyTime = board.getModifiedDate();
        this.viewCount = board.getViewCount();
    }
}
