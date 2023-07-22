package finalmelontoken.sharestudying.domain.member.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import finalmelontoken.sharestudying.domain.board.entity.Board;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Table(name = "member")
@Entity
@NoArgsConstructor
@DynamicUpdate
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //기본키
    @Column(nullable = false)
    private String name; //유저 이름
    @Column(nullable = false)
    private String password; //유저 비밀번호
    @Column(nullable = false)
    private String email; //유저 구글 이메일
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
//    @Column(nullable = false)
//    private String provider; //공급자 (google, facebook ...)
    @Column(nullable = false)
    private String providerId; //공급 아이디
    @OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Board> board = new ArrayList<>();
    @Builder
    public Member(String name, String password, String email, Role role,
                  String providerId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.providerId = providerId;
    }
}
