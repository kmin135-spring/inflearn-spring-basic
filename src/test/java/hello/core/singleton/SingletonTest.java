package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig ac = new AppConfig();

        MemberService ms1 = ac.memberService();
        MemberService ms2 = ac.memberService();

        // 매번 객체가 만들어지니 비효율적
        assertThat(ms1).isNotSameAs(ms2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        // isEqualTo 는 equals()
        // isSameAs ==
        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService ms1 = ac.getBean("memberService", MemberService.class);
        MemberService ms2 = ac.getBean("memberService", MemberService.class);

        assertThat(ms1).isSameAs(ms2);
    }
}
