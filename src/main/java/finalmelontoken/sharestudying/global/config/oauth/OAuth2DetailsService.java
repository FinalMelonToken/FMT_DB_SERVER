package finalmelontoken.sharestudying.global.config.oauth;

import finalmelontoken.sharestudying.domain.member.entity.Member;
import finalmelontoken.sharestudying.domain.member.entity.Role;
import finalmelontoken.sharestudying.domain.member.repository.MemberRepository;
import finalmelontoken.sharestudying.global.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth 로그인 진행중................");
        System.out.println(userRequest.getAccessToken().getTokenValue());

        // 1. AccessToken으로 회원정보를 받았다는 의미
        OAuth2User oauth2User = super.loadUser(userRequest);

        // 레트로핏 https://www.googleapis.com/drive/v2/files?access_token=userRequest.getAccessToken().getTokenValue()
        System.out.println("=========================================");
        System.out.println(oauth2User.getAttributes());
        System.out.println("=========================================");

        return processOAuth2User(userRequest, oauth2User);
    }

    // 구글 로그인 프로세스
    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
        OAuth2UserInfo oAuth2UserInfo = null;
        oAuth2UserInfo = new GoogleInfo(oauth2User.getAttributes());


        // 최초 : 회원가입 + 로그인 / 최초X : 로그인
        Optional<Member> userEntity = memberRepository.findByName(oAuth2UserInfo.getUsername());

        UUID uuid = UUID.randomUUID();
        String encPassword = new BCryptPasswordEncoder().encode(uuid.toString());

        System.out.println("OAuth2 사용자의 이메일: " + oAuth2UserInfo.getEmail());
        if (userEntity.isEmpty()) {
            System.out.println("최초 사용자입니다. 자동 회원가입을 진행 후 자동 로그인 합니다.");
            Member user = Member.builder()
                    .name(oAuth2UserInfo.getUsername())
                    .password(encPassword)
                    .email(oAuth2UserInfo.getEmail())
                    .role(Role.valueOf(String.valueOf(Role.USER)))
                    .build();
            userEntity = Optional.of(memberRepository.save(user));
            return new PrincipalDetails(userEntity.get(), oauth2User.getAttributes());
        } else {
            /*
            * 구글 회원정보를 업데이트하는 로직을 추가 구현해야함
            * */
            System.out.println("회원정보가 있습니다. 바로 로그인 합니다.");
            return new PrincipalDetails(userEntity.get(), oauth2User.getAttributes());
        }
    }
}
