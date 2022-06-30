package hello.core.order;

import hello.core.member.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
    private final MemberService memberSvc = new MemberServiceImpl();
    private final OrderService orderSvc = new OrderServiceImpl();

    @Test
    public void createVIPOrder() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberSvc.join(member);

        // when
        Order order = orderSvc.createOrder(member.getId(), "itemA", 10000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000L);
        assertThat(order.calculatePrice()).isEqualTo(9000L);
    }
}