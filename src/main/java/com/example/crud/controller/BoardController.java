package com.example.crud.controller;

import com.example.crud.entity.BoardEntity;
import com.example.crud.service.BoardNotFoundException;
import com.example.crud.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardEntity> createBoard(@RequestBody BoardEntity board) {
        return ResponseEntity.ok(boardService.savedBoardEntity(board));
    }

    @GetMapping
    public List<BoardEntity> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardEntity> updateBoard(@PathVariable Long id, @RequestBody BoardEntity board) {
        return ResponseEntity.ok(boardService.updateBoard(id, board));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }

    /**
     * springMVC 에서 @ExceptionHandler를 사용해 예외 처리
     * 아래처럼 핸들러를 추가
     */
    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<String> handleBoardNotFound(BoardNotFoundException boardNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(boardNotFoundException.getMessage());
    }
    /**
    이렇게 해줌으로써, BoardEntity가 존재하지 않을 경우
     BoardNotFoundException이 발생하며, 이 예외는 컨트롤러에서
     적절한 HTTP 상태 코드(404 Not Found)와 메시지로 처리하게된다.
     */
}
