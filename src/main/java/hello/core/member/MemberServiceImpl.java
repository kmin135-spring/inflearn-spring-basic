package hello.core.member;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
//    private final MemberRepository memberRepo = new MemoryMemberRepository();
    private final MemberRepository memberRepo;

    public MemberServiceImpl(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    public void join(Member member) {
        memberRepo.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepo.findById(memberId);
    }

    public MemberRepository memberRepository() {
        return memberRepo;
    }
}
