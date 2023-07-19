package finalmelontoken.sharestudying.domain.board.controller;

import finalmelontoken.sharestudying.domain.board.service.dto.BoardCreateRequestDto;
import finalmelontoken.sharestudying.domain.board.service.dto.BoardListResponseDto;
import finalmelontoken.sharestudying.domain.board.service.dto.BoardResponseDto;
import finalmelontoken.sharestudying.domain.board.service.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import finalmelontoken.sharestudying.domain.board.service.BoardService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write")
    public Long create(@RequestBody BoardCreateRequestDto requestDto) {
        return boardService.create(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(
            @PathVariable Long id,
            @RequestBody BoardUpdateRequestDto requestDto
    ) {
        return boardService.update(id, requestDto);
    }
    //개별 조회
    @GetMapping("/{id}")
    public BoardResponseDto searchById(@PathVariable Long id) {
        return boardService.searchById(id);
    }

    //전체 조회(목록)
    @GetMapping("/boards")
    public List<BoardListResponseDto> searchAllDesc() {
        return boardService.searchAllDesc();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        boardService.delete(id);
    }
}
