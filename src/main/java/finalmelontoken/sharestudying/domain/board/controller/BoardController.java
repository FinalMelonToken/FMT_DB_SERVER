       package finalmelontoken.sharestudying.domain.board.controller;

import finalmelontoken.sharestudying.domain.board.entity.Board;
import finalmelontoken.sharestudying.domain.board.repository.BoardRepository;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import finalmelontoken.sharestudying.domain.board.service.board.BoardService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

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
    @GetMapping("/{id}")
    public BoardResponseDto searchById(@PathVariable("id") Long id,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       Model model) {
        Board board;
        board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        // 중복 조회 여부를 확인하여 조회수 증가 및 쿠키 생성
        boolean hasViewed = handleViewCountAndCookie(board, request, response);

        // 조회수가 증가되었다면 데이터베이스 업데이트
        if (hasViewed) {
            boardRepository.save(board);
        }

        // BoardResponseDto 객체 생성 및 리턴
        return new BoardResponseDto(board);
    }

    private boolean handleViewCountAndCookie(Board board, HttpServletRequest request, HttpServletResponse response) {
        String cookieName = "postView_" + board.getBoardId();

        // 해당 게시글의 조회 쿠키 확인
        Cookie[] cookies = request.getCookies();
        boolean hasViewed = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    hasViewed = true;
                    break;
                }
            }
        }

        if (!hasViewed) {
            // 조회수 증가 및 쿠키 생성
            board.addViewCount();
            Cookie viewCookie = new Cookie(cookieName, "true");
            viewCookie.setPath("/");
            viewCookie.setMaxAge(24 * 60 * 60); // 24 hours in seconds
            response.addCookie(viewCookie);
        }

        return !hasViewed; // 조회수가 증가되었는지 여부 반환
    }



    //전체 조회(목록)
    @GetMapping("/boards")
    public ResponseEntity<Map<String, Object>> boardList(
            @PageableDefault(page = 0, size = 10, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable,
            String searchKeyword) {

        Page<BoardResponseDto> list;

        if (searchKeyword == null) {
            list = boardService.searchAllPaged(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getNumber() + 1;
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        Map<String, Object> response = new HashMap<>();
        response.put("list", list);
        response.put("nowPage", nowPage);
        int startPage = Math.max(nowPage - 4, 1);
        response.put("startPage", startPage);
        response.put("endPage", endPage);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        boardService.delete(id);
    }

//    private void viewCountValidation(Board board, HttpServletRequest request, HttpServletResponse response) {
//        Cookie[] cookies = request.getCookies();
//        Cookie cookie = null;
//        boolean isCookie = false;
//
//        for (int i = 0; cookies != null && i < cookies.length; i++) {
//            if (cookies[i].getName().equals("postView")) {
//                cookie = cookies[i];
//                if (!cookie.getValue().contains("[" + board.getBoardId() + "]")) {
//                    board.addViewCount();
//                    cookie.setValue(cookie.getValue() + "[" + board.getBoardId() + "]");
//                }
//                isCookie = true;
//
//                break;
//            }
//        }
//
//        if (!isCookie) {
//            board.addViewCount();
//            cookie = new Cookie("postView", "[" + board.getBoardId() + "]");
//        }
//
//        cookie.setPath("/");
////        cookie.setMaxAge(24 * 60 * 60);
//        cookie.setMaxAge(30);
//        response.addCookie(cookie);
//    }
//
}



