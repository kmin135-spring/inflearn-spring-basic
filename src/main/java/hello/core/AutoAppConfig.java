package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 스캔 시작위치 지정
        // basePackages = "hello.core",
        // 기본값은 이 @ComponentScan 이 붙은 config 클래스가 속한 패키지 이하
        // 스프링부트 메인클래스가 패키지 최상단에 위치한 것도 이 관례를 이용하는 것

        // AppConfig, 테스트코드에서 만들어둔 @Configuration 를 제외 (기존 코드 동작 유지를 위해)
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
