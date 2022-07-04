package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * Qualifier 는 하드코딩된 스트링이 쓰이므로 오타에 의한 오류가 날 가능성이 있는데
 * 자체적인 애노테이션을 정의할 수 있다.
 * 타입기반으로 검색하기 용이한 장점도 있다.
 *
 * 기본은 스프링이 제공하는 기본기능을 활용하되
 * 필요에 따라 이와 같은 커스텀 기능을 사용하자
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
