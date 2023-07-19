package finalmelontoken.sharestudying.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import finalmelontoken.sharestudying.domain.board.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByIdDesc();
}