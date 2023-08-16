package finalmelontoken.sharestudying.domain.board.service.board;
import finalmelontoken.sharestudying.domain.board.entity.Board;
import finalmelontoken.sharestudying.domain.board.repository.BoardRepository;
import finalmelontoken.sharestudying.domain.board.service.board.req.BoardCreateRequestDto;
import finalmelontoken.sharestudying.domain.board.service.board.req.BoardUpdateRequestDto;
import finalmelontoken.sharestudying.domain.board.service.board.res.BoardResponseDto;
import finalmelontoken.sharestudying.domain.user.repository.UserRepository;
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
    public Long create(BoardCreateRequestDto req) {
//        String htmlContent = convertToHtml(requestDto.getContent());

        return boardRepository.save(req.toEntity()).getBoardId();

    }
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto) {
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
    public BoardResponseDto searchById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return BoardResponseDto.builder()
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
    public Page<BoardResponseDto> searchAllPaged(Pageable pageable) {
        return boardRepository.findAllWithUsers(pageable);
    }


    @Transactional
    public void viewCountUp(Long boardId) {

    }
//    private String convertToHtml(String markdownContent) {
//        Parser parser = Parser.builder().build();
//        Node document = parser.parse(markdownContent);
//        HtmlRenderer renderer = HtmlRenderer.builder().build();
//        return renderer.render(document);
//    }
}