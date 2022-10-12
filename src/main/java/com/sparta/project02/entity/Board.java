package com.sparta.project02.entity;

import com.sparta.project02.controller.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne // 사람 한명이 여러개의 글을 가질 수 있음 - 영속성 컨텍스트가 인지함, DB에는 저장X
    @JoinColumn(nullable = false)// DB에 실제로 테이블을 만들기 위함
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle ();
        this.content = requestDto.getContent ();
    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle ();
        this.content = requestDto.getContent ();
    }
}
