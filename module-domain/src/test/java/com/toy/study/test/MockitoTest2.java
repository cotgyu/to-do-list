package com.toy.study.test;


import com.toy.study.domain.Member;
import com.toy.study.domain.MemberDto;
import com.toy.study.domain.MemberRepository;
import com.toy.study.domain.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockitoTest2 {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    /**
     * 예시를 위한 테스트
     * 이 테스트에서는 db 에 저장되는 로직이 아니라 다른 로직이 동작하는 지 확인하고 싶음
     * service 의 save 가 호출되면 count 가 증가된다. 이 count 만 정상 동작하는 지 확인해본다
     */
    @Test
    void memberService_count_test() {
        Member member = new Member("name");
        when(memberRepository.save(any())).thenReturn(member);

        MemberDto dto = new MemberDto("name");
        MemberDto dto2 = new MemberDto("name2");

        Member saveMember = memberService.save(dto);
        Member saveMember2 = memberService.save(dto2);

        // dto2 에 name2 를 저장했지만, mock repository 에 의해 name 가 됨
        Assertions.assertEquals(saveMember.getName(), dto.getName());
        Assertions.assertEquals(saveMember2.getName(), dto.getName());

        // mock 객체에 대해서 발생한 동작에 대해서 확인 가능
        verify(memberRepository, times(2)).save(any());

        // save 를 호출하였을 때 count 가 증가되는 지 확인한다
        Assertions.assertEquals(memberService.getCount(), 2);
    }
}
