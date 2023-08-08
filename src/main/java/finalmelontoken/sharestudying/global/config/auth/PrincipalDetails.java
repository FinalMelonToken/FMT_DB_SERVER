package finalmelontoken.sharestudying.global.config.auth;


import finalmelontoken.sharestudying.domain.user.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class PrincipalDetails implements OAuth2User {
    private User member;
    private Map<String, Object> attributes;

    public PrincipalDetails(User member) {
        this.member=member;
    }

    public PrincipalDetails(User member, Map<String, Object> attributes) {
        this.member=member;
        this.attributes=attributes;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().name()));
        return authorities;
    }


    @Override
    public String getName() {
        return "name";
    }
}