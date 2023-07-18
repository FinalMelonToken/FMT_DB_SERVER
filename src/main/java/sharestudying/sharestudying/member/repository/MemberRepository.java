package sharestudying.sharestudying.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharestudying.sharestudying.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}