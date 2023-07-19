package finalmelontoken.sharestudying.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import finalmelontoken.sharestudying.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}