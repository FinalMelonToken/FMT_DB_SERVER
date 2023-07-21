package finalmelontoken.sharestudying.global.config.auth;


import finalmelontoken.sharestudying.domain.member.entity.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements OAuth2User {
    private Member member;
    private Map<String, Object> attributes;

    public PrincipalDetails(Member member) {
        this.member=member;
    }

    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member=member;
        this.attributes=attributes;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getName() {
        return "name";
    }
}