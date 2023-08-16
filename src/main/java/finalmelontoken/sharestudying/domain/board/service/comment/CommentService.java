package finalmelontoken.sharestudying.domain.board.service.comment;
import finalmelontoken.sharestudying.domain.board.entity.Board;
import finalmelontoken.sharestudying.domain.board.entity.Comment;
import finalmelontoken.sharestudying.domain.board.repository.BoardRepository;
import finalmelontoken.sharestudying.domain.board.repository.CommentRepository;
import finalmelontoken.sharestudying.domain.board.service.board.req.BoardUpdateRequestDto;
import finalmelontoken.sharestudying.domain.board.service.comment.req.CommentRequest;
import finalmelontoken.sharestudying.domain.board.service.comment.req.CommentUpdateRequest;
import finalmelontoken.sharestudying.domain.board.service.comment.res.CommentGetResponse;
import finalmelontoken.sharestudying.domain.board.service.comment.res.CommentResponse;
import finalmelontoken.sharestudying.domain.user.entity.User;
import finalmelontoken.sharestudying.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long commentSave(/*String name,*/Long id, CommentRequest req) {
        /*User user = userRepository.getUserByName(name);*/
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        /*req.setUser(user);*/
        req.setBoard(board);

        Comment comment = req.toEntity();
        commentRepository.save(comment);

        return req.getId();
    }

    @Transactional
    public List<CommentGetResponse> getCommentsByBoardId(Long boardId) {
        List<CommentGetResponse> commentGetResponses = commentRepository.findByBoardBoardId(boardId).stream()
                .map(comment -> new CommentGetResponse(
                        comment.getCommentId(),
                        comment.getComment(),
//                       BoardUpdateRequestDto comment.getUser().getName(),
                        comment.getBoard().getBoardId()
                ))
                .collect(Collectors.toList());
        return commentGetResponses;
    }
    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        commentRepository.delete(comment);
    }
    @Transactional
    public Long update(Long id, CommentUpdateRequest req) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        comment.update(req.getComment());
        comment.setModifiedDate(req.getModifiedDate()); // modifiedDate 업데이트

        return id;
    }
}