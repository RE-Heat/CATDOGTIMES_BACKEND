package com.catdogtimes.Backend.service;

import com.catdogtimes.Backend.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void signup(MemberDTO memberDTO) {

    }
}
