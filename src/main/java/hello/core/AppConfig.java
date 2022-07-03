package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
이 파일만 보면 애플리케이션에 필요한 역할(인터페이스)와 구현(구체클래스) 가 한눈에 보인다.
 */
@Configuration
public class AppConfig {

    // 메서드이름을 기본 빈 이름으로 사용함
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // 자바 코드로만 보면 3번 호출되야할 거 같은데 1번만 호출된다.
        // 비밀은 @Configuration
        System.out.println("call AppConfig memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig discountPolicy");
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }
}
