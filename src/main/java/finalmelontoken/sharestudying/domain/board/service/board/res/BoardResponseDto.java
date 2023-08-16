package finalmelontoken.sharestudying.domain.board.service.board.res;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
//    private String user;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private int viewCount;

    @Builder
    public BoardResponseDto(Long id, String title, String content, String user, LocalDateTime createTime, LocalDateTime modifyTime, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
//        this.user = user;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.viewCount = viewCount;
    }
}
