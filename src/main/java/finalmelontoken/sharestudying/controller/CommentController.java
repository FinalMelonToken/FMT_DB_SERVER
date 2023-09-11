package finalmelontoken.sharestudying.controller;

//import finalmelontoken.sharestudying.service.CommentService;
import finalmelontoken.sharestudying.service.CommentService;
import finalmelontoken.sharestudying.model.request.CommentRequest;
//import finalmelontoken.sharestudying.model.response.UserInfoResponse;
import finalmelontoken.sharestudying.model.request.CommentUpdateRequest;
import finalmelontoken.sharestudying.model.response.CommentGetResponse;
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
