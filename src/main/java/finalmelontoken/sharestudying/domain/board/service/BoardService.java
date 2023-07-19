package finalmelontoken.sharestudying.domain.board.service;

import finalmelontoken.sharestudying.domain.board.repository.BoardRepository;
import finalmelontoken.sharestudying.domain.board.service.dto.BoardCreateRequestDto;
import finalmelontoken.sharestudying.domain.board.service.dto.BoardListResponseDto;
import finalmelontoken.sharestudying.domain.board.service.dto.BoardResponseDto;
import finalmelontoken.sharestudying.domain.board.service.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import finalmelontoken.sharestudying.domain.board.entity.Board;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long create(BoardCreateRequestDto requestDto) {
        String htmlContent = convertToHtml(requestDto.getContent());
        return boardRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        board.update(requestDto.getTitle(),
                requestDto.getContent());

        return id;
    }
    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        boardRepository.delete(board);
    }
    @Transactional(readOnly = true)
    public BoardResponseDto searchById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new BoardResponseDto(board);
    }

    @Transactional(readOnly = true)
    public List<BoardListResponseDto> searchAllDesc() {
        return boardRepository.findAllByOrderByIdDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }
    private String convertToHtml(String markdownContent) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}