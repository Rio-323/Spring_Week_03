package com.sparta.project02.controller;

import com.sparta.project02.controller.dto.MemberRequestDto;
import com.sparta.project02.controller.dto.MemberResponseDto;
import com.sparta.project02.controller.dto.TokenDto;
import com.sparta.project02.controller.dto.TokenRequestDto;
import com.sparta.project02.service.AuthService;
import com.sparta.project02.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/{email}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMemberInfo(email));
    }
}
