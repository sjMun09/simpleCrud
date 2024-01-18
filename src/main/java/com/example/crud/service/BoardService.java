package com.example.crud.service;

import com.example.crud.entity.BoardEntity;
import com.example.crud.repository.BoardJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;

    public BoardService(BoardJpaRepository boardJpaRepository) {
        this.boardJpaRepository = boardJpaRepository;
    }

    public BoardEntity savedBoardEntity(BoardEntity boardEntity) {
        return boardJpaRepository.save(boardEntity);
    }

    public List<BoardEntity> getAllBoards() {
        return boardJpaRepository.findAll();
    }

    public BoardEntity getBoardById(Long id) {
        return boardJpaRepository
                .findById(id)
                .orElseThrow(() -> new BoardNotFoundException(id));// 예외처리
    }

    public BoardEntity updateBoard(Long id, BoardEntity boardUpdate) {
        BoardEntity board = boardJpaRepository
                .findById(id)
                .orElseThrow(()-> new BoardNotFoundException(id)); // 예외처리
        board.setTitle(boardUpdate.getTitle());
        board.setContent(boardUpdate.getContent());
        return boardJpaRepository.save(board);
    }

    public void deleteBoard(Long id) {
        boardJpaRepository.deleteById(id);
    }
}
