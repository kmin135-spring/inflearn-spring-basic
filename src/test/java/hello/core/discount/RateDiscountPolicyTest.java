package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인 적용되야함")
    void vip_o() {
        // arrange
        Member member = new Member(1L, "memberA", Grade.VIP);

        // action
        int orgPrice = 10000;
        int discount = discountPolicy.discount(member, orgPrice);

        // assert
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않음")
    void vip_x() {
        // arrange
        Member member = new Member(1L, "memberA", Grade.BASIC);

        // action
        int orgPrice = 10000;
        int discount = discountPolicy.discount(member, orgPrice);

        // assert
        assertThat(discount).isEqualTo(0);
    }
}