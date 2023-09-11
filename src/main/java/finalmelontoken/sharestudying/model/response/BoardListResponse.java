package finalmelontoken.sharestudying.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import finalmelontoken.sharestudying.model.entity.Board;

@Getter
@AllArgsConstructor
@Builder
public class BoardListResponse {
    private Long boardId;
    private String username;
    private String title;

    public BoardListResponse(Board board) {
        this.boardId = board.getBoardId();
        this.username = board.getUser().getName();
        this.title = board.getTitle();
    }
//    public static BoardListResponse from(Board board) {
//        return BoardListResponse.builder()
//                .boardId(board.getBoardId())
//                .title(board.getTitle())
//                .user(user.getName())
//                .build();
//    }
}



//import finalmelontoken.sharestudying.model.entity.Board;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import java.time.LocalDateTime;
//
//@Getter
//@AllArgsConstructor
//// builder는 @RequiredArgsConstructor 말고 @AllArgsConstructor 써야 함.
//// @RequiredArgsConstructor는 초기화 되지 않은 final 필드와 @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
//@Builder
//public class BoardListResponse {
//    private Long boardId;
//    private String content;
//    private String title;
//    private Long views;
//    private LocalDateTime createTime;
//    private Long userId;
//    private String nickname;
//
//    public BoardListResponse(Board board) {
//        this.boardId = board.getBoardId();
//        this.content = board.getContent();
//        this.title = board.getTitle();
//        this.views = board.getViews();
//        this.createTime = board.getCreateTime();
//        this.userId = board.getUser().getUserId();
//        this.nickname = board.getUser().getName();
//    }
//
//    public static BoardListResponse from(Board board) {
//        return BoardListResponse.builder()
//                .boardId(board.getBoardId())
//                .content(board.getContent())
//                .title(board.getTitle())
//                .views(board.getViews())
//                .createTime(board.getCreateTime())
//                .userId(board.getUser().getUserId())
//                .nickname(board.getUser().getName())
//                .build();
//    }
//}