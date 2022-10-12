package com.sparta.project02.controller;

import com.sparta.project02.controller.dto.BoardRequestDto;
import com.sparta.project02.entity.Board;
import com.sparta.project02.repository.BoardRepository;
import com.sparta.project02.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public Long createBoard(@RequestBody BoardRequestDto requestDto){
        return boardService.post ( requestDto );
    }

    @GetMapping("/api/boards")
    public List<Board> getBoard() {return boardService.get ();}

    @GetMapping("api/boards/{id}")
    public Optional<Board> findById(@PathVariable Long id){
        return boardRepository.findById ( id );
    }

    @PutMapping("api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        boardService.update ( id, requestDto );
        return id;
    }


    @DeleteMapping("api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id){
        boardRepository.deleteById ( id );
        return id;
    }
}
