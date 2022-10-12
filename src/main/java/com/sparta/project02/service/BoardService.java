package com.sparta.project02.service;

import com.sparta.project02.controller.dto.BoardRequestDto;
import com.sparta.project02.controller.dto.BoardResponseDto;
import com.sparta.project02.entity.Board;
import com.sparta.project02.entity.Member;
import com.sparta.project02.repository.BoardRepository;
import com.sparta.project02.repository.MemberRepository;
import com.sparta.project02.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long post(BoardRequestDto requestDto) {
        Board post = new Board (requestDto);
        Long currentMemberId = SecurityUtil.getCurrentMemberId ();
        Member member = memberRepository.findById ( currentMemberId ).orElseThrow (() -> new IllegalArgumentException ("아이디가 없습니다."));
        post.setMember ( member );
        boardRepository.save ( post );
        return post.getId ();
    }

    @Transactional // 글 전체목록 가져오기
    public List<Board> get(){return boardRepository.findAllByOrderByCreatedAtDesc ();}

    @Transactional
    public Board getOne(Long id) {
        return boardRepository.findById ( id ).orElseThrow (() -> new IllegalArgumentException ("아이디가 존재하지 않습니다."));
    }
    @Transactional
    public Long update(Long updateId, BoardRequestDto requestDto){
        Board updateBoard = boardRepository.findById ( updateId ).orElseThrow (() -> new IllegalArgumentException ("아이디가 존재하지 않습니다."));

        Long authorId = updateBoard.getMember ().getId ();
        Long currentId = SecurityUtil.getCurrentMemberId ();

        if(authorId == currentId) {
            updateBoard.update ( requestDto );
        } else {
            throw new IllegalArgumentException ("수정 권한이 없습니다."); // orElseThrow에는 이미 Illegal이 함수가 정의되어 있지만, if else 구문으로 작성할 때에는 throw 해주어야 함.
        }
        return updateId;
    }

    @Transactional
    public Long deleteBoard(Long postId) {
        Board deleteBoard = boardRepository.findById ( postId ).orElseThrow (() -> new IllegalArgumentException ("게시글이 존재하지 않습니다."));

        Long authorId = deleteBoard.getMember ().getId (); // 글의 작성자의 아이디(글에 담겨있는 member에서 꺼내온 것)
        Long currentId = SecurityUtil.getCurrentMemberId (); // 현재로그인하고 있는 사람의 아이디

        if(authorId == currentId) {
            boardRepository.deleteById ( postId );
        }else {
            throw new IllegalArgumentException ("아이디가 다릅니다.");
        }

        return postId; // void 여도 우리가 짠 로직은 다 수행되었기 때문에 어떤 값을 리턴하여도 크게 상관 없음.
        // 만약에 프론트에서 성공하였을 때 리턴값으로 받고 싶은 내용이 있으면 수정해야 함.
    }

}