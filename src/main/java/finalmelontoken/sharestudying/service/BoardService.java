package finalmelontoken.sharestudying.service;
import finalmelontoken.sharestudying.model.entity.Board;
import finalmelontoken.sharestudying.repository.BoardRepository;
import finalmelontoken.sharestudying.model.request.BoardCreateRequest;
import finalmelontoken.sharestudying.model.request.BoardUpdateRequest;
import finalmelontoken.sharestudying.model.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long create(BoardCreateRequest req) {
//        String htmlContent = convertToHtml(requestDto.getContent());

        return boardRepository.save(req.toEntity()).getBoardId();

    }
    @Transactional
    public Long update(Long id, BoardUpdateRequest requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        board.update(requestDto.getTitle(),
                requestDto.getContent());
        board.setModifiedDate(requestDto.getModifiedDate()); // modifiedDate 업데이트

        return id;
    }
    @Transactional
    public void delete(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        boardRepository.delete(board);
    }
    @Transactional(readOnly = true)
    public BoardResponse searchById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return BoardResponse.builder()
                .id(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
//                .user(board.getUser())
                .createTime(board.getCreatedDate())
                .modifyTime(board.getModifiedDate())
                .viewCount(board.getViewCount())
                .build();
    }
//    @Transactional(readOnly = true)
//    public Page<Board> searchAllPaged(Pageable pageable) {
//        return boardRepository.findAll(pageable);
//    }
    @Transactional(readOnly = true)
    public Page<BoardResponse> searchAllPaged(Pageable pageable) {
        return boardRepository.findAllWithUsers(pageable);
    }

//    private String convertToHtml(String markdownContent) {
//        Parser parser = Parser.builder().build();
//        Node document = parser.parse(markdownContent);
//        HtmlRenderer renderer = HtmlRenderer.builder().build();
//        return renderer.render(document);
//    }
    @Transactional
    public Page<BoardResponse> boardSearchList(String searchKeyword, Pageable pageable) {
        Page<Board> boardPage = boardRepository.findByTitleContaining(searchKeyword, pageable);

        return boardPage.map(board -> {
            BoardResponse responseDto = new BoardResponse();
            responseDto.setId(board.getBoardId());
            responseDto.setTitle(board.getTitle());
            responseDto.setContent(board.getContent());
            responseDto.setViewCount(board.getViewCount());
            responseDto.setCreateTime(board.getCreatedDate());
            responseDto.setModifyTime(board.getModifiedDate());
            return responseDto;
        });
    }
}