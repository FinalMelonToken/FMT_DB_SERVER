package finalmelontoken.sharestudying.domain.board.controller;

import finalmelontoken.sharestudying.domain.board.entity.Comment;
//import finalmelontoken.sharestudying.domain.board.service.comment.CommentService;
import finalmelontoken.sharestudying.domain.board.service.board.req.BoardUpdateRequestDto;
import finalmelontoken.sharestudying.domain.board.service.comment.CommentService;
import finalmelontoken.sharestudying.domain.board.service.comment.req.CommentRequest;
//import finalmelontoken.sharestudying.domain.user.service.res.UserInfoResponse;
import finalmelontoken.sharestudying.domain.board.service.comment.req.CommentUpdateRequest;
import finalmelontoken.sharestudying.domain.board.service.comment.res.CommentGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/board/{id}/comments")
    public ResponseEntity commentSave(@PathVariable("id") Long id, @RequestBody CommentRequest req/*, UserInfoResponse res*/){
//        return ResponseEntity.ok(commentService.commentSave(res.getName(), id, req));
        return ResponseEntity.ok(commentService.commentSave(/*res.getName(), */id, req));
    }
    @GetMapping("/board/{id}/comments")
    public ResponseEntity<List<CommentGetResponse>> getCommentsByBoardId(@PathVariable("id") Long id) {
        List<CommentGetResponse> commentGetResponses = commentService.getCommentsByBoardId(id);
        return ResponseEntity.ok(commentGetResponses);
    }
    @PutMapping("/board/{boardId}/comments/{commentId}")
    public Long update(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest req
    ) {
        return commentService.update(commentId, req);
    }
    @DeleteMapping("/board/{boardId}/comments/{commentId}")
    public void delete(@PathVariable Long commentId){
        commentService.delete(commentId);
    }
}
