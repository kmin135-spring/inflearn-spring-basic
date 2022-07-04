package hello.core.autowired;

import hello.core.member.Member;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class AutowiredTest {
    /**
     * Member 는 스프링빈이 아니므로 여기서는 항상 null<br>
     * Autowired 타입에 따라 어떻게 동작하는지 확인해보자
     */
    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

        TestBean testBean = ac.getBean(TestBean.class);
        assertThat(testBean).isNotNull();
        assertThat(testBean.isCalledSetNoBean1).isFalse();
        assertThat(testBean.noBean1).isNull();
        assertThat(testBean.isCalledSetNoBean2).isTrue();
        assertThat(testBean.noBean2).isNull();
        assertThat(testBean.isCalledSetNoBean3).isTrue();
        assertThat(testBean.noBean3).isEqualTo(Optional.empty());
    }

    static class TestBean {
        private boolean isCalledSetNoBean1;
        private boolean isCalledSetNoBean2;
        private boolean isCalledSetNoBean3;
        private Member noBean1;
        private Member noBean2;
        private Optional<Member> noBean3;

        @Autowired(required = false)
        public void setNoBean1(Member noBean) {
            isCalledSetNoBean1 = true;
            noBean1 = noBean;
            System.out.println("noBean1 = " + noBean);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean) {
            isCalledSetNoBean2 = true;
            noBean2 = noBean;
            System.out.println("noBean2 = " + noBean);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean) {
            isCalledSetNoBean3 = true;
            noBean3 = noBean;
            System.out.println("noBean3 = " + noBean);
        }


    }
}
