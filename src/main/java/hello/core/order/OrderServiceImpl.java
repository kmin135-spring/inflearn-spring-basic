package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
//    private final MemberRepository memberRepo = new MemoryMemberRepository();
    private final MemberRepository memberRepo;

    // 구체 클래스에 의존하여 OCP, DIP 위반 (정책을 바꾸려면 코드 변경 발생!)
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final DiscountPolicy discountPolicy;

    // lombok 의 @RequiredArgsConstructor 로 대체하면 더 편리하다.
//    public OrderServiceImpl(MemberRepository memberRepo, DiscountPolicy discountPolicy) {
//        this.memberRepo = memberRepo;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepo.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository memberRepository() {
        return memberRepo;
    }
}
