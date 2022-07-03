package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService svc1 = ac.getBean(StatefulService.class);
        StatefulService svc2 = ac.getBean(StatefulService.class);

        // ThreadA 에서 A가 만원 주문
        svc1.order("userA", 10000);

        // ThreadA가 완료되기 전에 ThreadB 에서 B가 2만원 주문했다고 가정
        svc1.order("userB", 20000);

        // ThreadA : 사용자 A가 주문 금액 조회
        int price = svc1.getPrice();
        System.out.println("price = " + price);
    }

    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService svc1 = ac.getBean(StatefulService.class);

        // ThreadA 에서 A가 만원 주문
        int price1 = svc1.statelessOrder("userA", 10000);

        // ThreadA가 완료되기 전에 ThreadB 에서 B가 2만원 주문했다고 가정
        int price2 = svc1.statelessOrder("userB", 20000);

        // ThreadA : 사용자 A가 주문 금액 조회
        Assertions.assertThat(price1).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}