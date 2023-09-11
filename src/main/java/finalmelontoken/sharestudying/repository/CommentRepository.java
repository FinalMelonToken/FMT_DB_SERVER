package finalmelontoken.sharestudying.repository;

import finalmelontoken.sharestudying.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findByBoardId(Long boardId);
    List<Comment> findByBoardBoardId(Long boardId);

}
