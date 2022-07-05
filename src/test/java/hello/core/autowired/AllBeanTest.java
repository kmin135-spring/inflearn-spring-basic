package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static hello.core.autowired.AllBeanTest.DiscountService.*;

public class AllBeanTest {
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = discountService.discount(member, 10000, Policy.fixDiscountPolicy);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        discountPrice = discountService.discount(member, 20000, Policy.rateDiscountPolicy);
        Assertions.assertThat(discountPrice).isEqualTo(2000);
    }

    /**
    Map 으로 모든 DiscountPolicy 빈을 받아둠으로써
    간단하게 전략패턴을 사용할 수 있음
    비즈니스 로직에서 동적으로 빈을 사용하고 싶을 때 유용
     */
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("map = " + policyMap);
            System.out.println("list = " + policies);

        }

        public int discount(Member member, int price, Policy discountPolicy) {
            return policyMap.get(discountPolicy.toString()).discount(member, price);
        }

        public enum Policy {
            fixDiscountPolicy, rateDiscountPolicy
        }
    }
}
