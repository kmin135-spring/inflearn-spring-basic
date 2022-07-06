package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);

        ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class, ClientBean.class);
        ClientBean clientBean = ac.getBean(ClientBean.class);

        assertThat(clientBean.logic()).isEqualTo(1);
        assertThat(clientBean.logic()).isEqualTo(1);
    }

    static class ClientBean {
        // Dependency Lookup 에 특화된 기능
//        @Autowired private ObjectProvider<ProtoTypeBean> pbProvider;

        // ObjectProvider 대신 표준 기술 사용 (JSR330)
        @Autowired
        private Provider<ProtoTypeBean> pbProvider;

        public int logic() {
//            ProtoTypeBean protoTypeBean = pbProvider.getObject();
            ProtoTypeBean protoTypeBean = pbProvider.get();
            protoTypeBean.addCount();
            return protoTypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class ProtoTypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototype init : " + this);
        }
        @PreDestroy
        public void destroy() {
            System.err.println("prototype close : " + this);
        }
    }
}
