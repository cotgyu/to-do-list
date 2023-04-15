package com.toy.study.domain;


public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private int count = 0;

    public Member save(MemberDto dto) {
        count++;
        return memberRepository.save(new Member(dto.getName()));
    }

    public int getCount() {
        return count;
    }
}
