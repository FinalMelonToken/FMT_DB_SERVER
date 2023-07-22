package finalmelontoken.sharestudying.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import finalmelontoken.sharestudying.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByName(String name);
}