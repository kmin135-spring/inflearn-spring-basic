package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void protoTypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);

        Assertions.assertThat(bean1).isNotSameAs(bean2);

        ac.close();
    }

    /*
    프로토타입은 스프링 컨테이너가 생성, 초기화, 의존성 주입까지만 해주고 더 이상 어떤 관리도 하지 않는다.
    종료메서드가 실행되지 않는다. 즉, 프로토타입 빈은 클라이언트가 종료의 책임을 가진다.
     */
    @Scope("prototype")
    static class ProtoTypeBean {
        @PostConstruct
        public void init() {
            System.out.println("prototype init : " + this);
        }

        /* prototype 은 호출되지 않음! */
        @PreDestroy
        public void destroy() {
            System.out.println("prototype destroy" + this);
        }
    }
}
