package sharestudying.sharestudying.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharestudying.sharestudying.board.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByIdDesc();
}