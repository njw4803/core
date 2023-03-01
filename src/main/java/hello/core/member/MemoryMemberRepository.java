package hello.core.member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {
    private  static Map<Long, Member> store = new HashMap<>();
    // HashMap<>()은 동시성 문제 발생할 수 있어서 실무에선 ConcurrentHashMap<>()을 쓴다.

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
