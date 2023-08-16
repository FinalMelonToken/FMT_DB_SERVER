package finalmelontoken.sharestudying.domain.board.repository;

import finalmelontoken.sharestudying.domain.board.entity.Board;
import finalmelontoken.sharestudying.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findByBoardId(Long boardId);
    List<Comment> findByBoardBoardId(Long boardId);

}
