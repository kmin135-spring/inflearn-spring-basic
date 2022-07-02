package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member m = new Member(1L, "memberA", Grade.VIP);
        memberService.join(m);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + m.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
