package com.study.dto.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter // getter 어노테이션
@Setter // setter 어노테이션
@NoArgsConstructor // 파라미터가 없는 기본 생성자 어노테이션
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 어노테이션
@Builder // 빌더 어노테이션 - 빌더를 통해 해당 객체의 필드 값을 재생성 한다.
@ToString // 객체를 불러올때 주솟값이 아닌 String 타입으로 변경해주는 어노테이션
@Entity(name="Member") // Entity 어노테이션 - 괄호안에는 테이블명과 똑같이 작성한다.
public class Member {
    // 기본키
    @Id // 기본키 어노테이션 - 기본키 설정 (PRIMARY KEY)
    // // @GeneratedValue(strategy = GenerationType.IDENTITY) - AUTO_INCREMENT - MySQL에서 시퀀스 역할을 담당한다.
    @Column(length = 50) // 컬럼 어노테이션 - 기본키 제외 나머지 컬럼 설정 - 기본키랑 같이 쓰이면 기본키의 설정값들을 잡아줄 수 있다.
    private String emailId;

    @Column(length = 255, nullable = false)
    private String pwd;

    @Column(length = 10, nullable = false)
    private String name;

    // length = 길이, unique = (기본값)false:유니크 해제 / true:유니크 설정, nullable = (기본값)true:눌값 허용 / false:눌값 불가
    @Column(length = 20, unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false) // DATE 사용
    private String birthday;

    @Column(length = 1, nullable = false)
    private String gender;

    @Column(length = 15, unique = true, nullable = false)
    private String phoneNumber;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 10, nullable = false)
    private String studyType;

    @Column(length = 10, nullable = false)
    private String platform;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DTO 구역

    // 회원가입 Request DTO
    @Getter // getter 어노테이션
    @Setter // setter 어노테이션
    @NoArgsConstructor // 파라미터가 없는 기본 생성자 어노테이션
    @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 어노테이션
    @Builder // 빌더 사용 어노테이션
    @ToString // 객체를 불러올때 주솟값이 아닌 String 타입으로 변경해주는 어노테이션
    public static class rqJoinMember {
        private String emailId;
        private String pwd;
        private String name;
        private String nickname;
        private String birthday;
        private String gender;
        private String phoneNumber;
        private String address;
        private String studyType;
        private String platform;

        // DTO를 Entity로 변환 (빌더 방식)
        public Member toEntity() {
            return Member.builder()
                    .emailId(emailId) // .Entity 변수명(DTO 변수명)
                    .pwd(pwd)
                    .name(name)
                    .nickname(nickname)
                    .birthday(birthday)
                    .gender(gender)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .studyType(studyType)
                    .platform(platform)
                    .build();
        }
    }

    // 회원가입 Response DTO
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class rpJoinMember {
        private String nickname;

        // Entity를 DTO로 변환 (생성자 방식)
        public rpJoinMember(Member member) {
            this.nickname = member.getNickname();
        }
    }

    // 로그인 Request DTO
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class rqLoginMemeber {
        private String emailId;
        private String pwd;

        // DTO를 Entity로 변환 (빌더 방식)
        public Member toEntity() {
            return Member.builder()
                    .emailId(emailId)
                    .pwd(pwd)
                    .build();
        }
    }

    // 로그인 Response DTO
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class rpLoginMember {
        private String emailId;
        private String pwd;

        // Entity를 DTO로 변환 (생성자 방식)
        public rpLoginMember(Member member) {
            this.emailId = member.emailId;
            this.pwd = member.pwd;
        }
    }
}
