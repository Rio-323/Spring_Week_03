package com.sparta.project02.controller.dto;

import com.sparta.project02.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    private String title;
    private String content;
    private String email;

    public BoardResponseDto(Board board) {
        this.email = board.getMember ().getEmail ();
        this.title = board.getTitle ();
        this.content = board.getContent ();
    }
}
