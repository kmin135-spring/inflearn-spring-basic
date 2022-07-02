package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // 직접 생성 버전
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 스프링 컨테이너 사용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member m = new Member(1L, "memberA", Grade.VIP);
        memberService.join(m);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + m.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
