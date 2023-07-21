package finalmelontoken.sharestudying.domain.member.service;


import finalmelontoken.sharestudying.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;


}