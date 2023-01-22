package com.study.dto.service;

import com.study.dto.entity.Member;
import com.study.dto.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    SignUpRepository signUpRepository;

    public Member.rpLoginMember loginEmailId(Member.rqLoginMemeber rqLoginMemeber) {
        // 3. 가져온 DTO를 Entity로 변환한다.
        Member loginMember = rqLoginMemeber.toEntity();
        System.out.println("1 : " + loginMember);
        // 4. 변환된 Entity로 DB 조회한다.
        Member member = signUpRepository.findByEmailId(loginMember.getEmailId());
        System.out.println("2 : " + member);
        // 5. 조회된 값이 있는지 체크한다.
        // 5-1. 조회된 아이디가 없는 경우
        if (member == null) {
            // 눌값을 반환한다.
            return null;
        }
        // 5-2. 조회된 아이디가 있는 경우
        // 6. 조회된 Entity를 사용할 값들만 따로 빼서 만들어둔 DTO로 변환한다.
        Member.rpLoginMember rpLoginMember = new Member.rpLoginMember(member);
        System.out.println("3 : " + rpLoginMember);
        // 7. 변환된 DTO를 반환한다.
        return rpLoginMember;
    }

    public Member.rpJoinMember join(Member.rqJoinMember rqJoinMember) {
        // 3. 가져온 DTO를 Entity로 변환한다.
        Member joinMember = rqJoinMember.toEntity();
        // 4. 변환된 Entity로 DB에 저장한다.
        Member member = signUpRepository.save(joinMember);
        // 5. 저장된 Entity를 사용할 값들만 따로 빼서 만들어둔 DTO로 변환한다.
        Member.rpJoinMember rpJoinMember = new Member.rpJoinMember(member);
        System.out.println(rpJoinMember);
        // 6. 변환된 DTO를 반환한다.
        return rpJoinMember;
    }
}
