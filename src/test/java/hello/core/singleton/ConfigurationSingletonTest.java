package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        assertThat(memberService.memberRepository()).isSameAs(orderService.memberRepository());
        assertThat(orderService.memberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // bean = class hello.core.AppConfig 순수한 클래스라면 이렇게 나와야함
        // 실제결과는
        // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$6e6a9b38
        // EnhancerBySpringCGLIB 는 어디서 튀어나온걸까?

        // 스프링이 CGLIB라는 바이트코드 조작 라이브러리로
        // AppConfig 클래스를 상속받은 임의의 클래스를 만들고 (AppConfig@CGLIB)
        // 그 클래스를 스프링 빈으로 등록한 것
        System.out.println("bean = " + bean.getClass());

        /*
        AppConfig에서 @Configuration 을 빼고 돌려보면 순수클래스로서 실행되고
        memberRepository() 가 호출되는 횟수만큼 실행됨을 확인할 수 있음
        즉, CGLIB가 개입하지 않고 순수 자바 코드대로 실행되기 때문임
         */
    }
}
