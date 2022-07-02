package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {
    MemberService memberSvc;

    @BeforeEach
    public void prepare() {
        AppConfig config = new AppConfig();
        memberSvc = config.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberSvc.join(member);
        Member findMember = memberSvc.findMember(1L);

        // then
        assertThat(member).isEqualTo(findMember);
    }
}