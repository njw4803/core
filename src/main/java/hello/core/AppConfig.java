package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 구성영역
 * AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
 * 프로그램에 대한 제어 흐름에 대한 권한은 모두 AppConfig가 가지고 있다.
 * AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 DI 컨테이너라고 한다. 또는 어샘블러, 오브젝트 팩토리 등으로 불리기도 한다.
 */
@Configuration // 싱글톤을 보장해준다.
public class AppConfig {

    /**
     * AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
     */

    /**
     * Bean memberService -> new MemoryMemberRepository()
     * Bean orderService -> new MemoryMemberRepository()
     * memberService 빈을 만드는 코드를 보면 memberRepository() 를 호출한다.
     * 이 메서드를 호출하면 new MemoryMemberRepository() 를 호출한다.
     * orderService 빈을 만드는 코드도 동일하게 memberRepository() 를 호출한다.
     * 이 메서드를 호출하면 new MemoryMemberRepository() 를 호출한다.
     * 결과적으로 각각 다른 2개의 MemoryMemberRepository 가 생성되면서 싱글톤이 깨지는 것 처럼 보인다.
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRespository());
    }

    @Bean
    public MemberRepository memberRespository() {
        System.out.println("call AppConfig.memberRespository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRespository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
