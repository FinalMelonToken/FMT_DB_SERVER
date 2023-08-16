package finalmelontoken.sharestudying.domain.board.repository;

import finalmelontoken.sharestudying.domain.board.service.board.res.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import finalmelontoken.sharestudying.domain.board.entity.Board;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//    List<Board> findAllByOrderByIdDesc();
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT NEW finalmelontoken.sharestudying.domain.board.service.board.res.BoardResponseDto(b.boardId, b.title, b.content, u.name) FROM Board b LEFT JOIN b.user u")
    Page<BoardResponseDto> findAllWithUsers(Pageable pageable);
    Optional<Board> findByBoardId(Long boardId);

}