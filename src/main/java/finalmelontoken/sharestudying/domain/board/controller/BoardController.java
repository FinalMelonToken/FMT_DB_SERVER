package finalmelontoken.sharestudying.domain.board.controller;

import finalmelontoken.sharestudying.domain.board.service.board.req.BoardCreateRequestDto;
import finalmelontoken.sharestudying.domain.board.service.board.res.BoardResponseDto;
import finalmelontoken.sharestudying.domain.board.service.board.req.BoardUpdateRequestDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import finalmelontoken.sharestudying.domain.board.service.board.BoardService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write")
    public void create(@RequestBody BoardCreateRequestDto requestDto) {
        boardService.create(requestDto);
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
    public BoardResponseDto searchById(@PathVariable Long id, HttpServletRequest req, HttpServletResponse res) {
        return boardService.searchById(id);
    }

    //전체 조회(목록)
    @GetMapping("/boards")
    public Page<BoardResponseDto> searchAllPaged(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "boardId"));
        return boardService.searchAllPaged(pageable);
    }
//    @GetMapping("/boards")
//    public List<BoardListResponse> findByBoardId(@RequestParam Long boardId, Pageable pageable) {
//        return boardRepository.searchAllDesc();
//    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        boardService.delete(id);
    }
    private void viewCountUp(Long id, HttpServletRequest req, HttpServletResponse res) {

        Cookie oldCookie = null;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("boardView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
                boardService.viewCountUp(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                res.addCookie(oldCookie);
            }
        } else {
            boardService.viewCountUp(id);
            Cookie newCookie = new Cookie("boardView","[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            res.addCookie(newCookie);
        }
    }
}
