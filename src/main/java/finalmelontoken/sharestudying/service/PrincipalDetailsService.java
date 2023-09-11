package finalmelontoken.sharestudying.service;

import finalmelontoken.sharestudying.model.entity.PrincipalDetails;
import finalmelontoken.sharestudying.model.entity.User;
import finalmelontoken.sharestudying.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("/login is called, automatically executed to check if the username exists in the DB.");
        Optional<User> optionalMember = memberRepository.findByName(username);

        if (optionalMember.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User member = optionalMember.get();
        return (UserDetails) new PrincipalDetails(member);
    }

}
