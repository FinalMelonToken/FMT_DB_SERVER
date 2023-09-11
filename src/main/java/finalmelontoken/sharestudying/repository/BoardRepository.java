package finalmelontoken.sharestudying.repository;

import finalmelontoken.sharestudying.model.response.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import finalmelontoken.sharestudying.model.entity.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//    List<Board> findAllByOrderByIdDesc();
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "SELECT NEW finalmelontoken.sharestudying.domain.board.service.board.res.BoardResponseDto(b.boardId, b.title, b.content, u.name) FROM Board b LEFT JOIN b.user u")
    Page<BoardResponseDto> findAllWithUsers(@Param("pageable") Pageable pageable);
    Optional<Board> findByBoardId(Long boardId);
    Page<Board> findByTitleContaining(@Param("searchKeyword") String searchKeyword, @Param("pageable") Pageable pageable);
}