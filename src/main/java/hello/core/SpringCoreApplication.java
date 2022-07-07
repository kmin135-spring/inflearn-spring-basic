package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringCoreApplication {
    // AnnotationConfigServletWebServerApplicationContext
    // AnnotationConfigApplicationContext 대비 웹 관련 내용이 포함된 컨테이너
    private final ApplicationContext ac;

    public SpringCoreApplication(ApplicationContext ac) {
        this.ac = ac;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println(ac);
    }
}
