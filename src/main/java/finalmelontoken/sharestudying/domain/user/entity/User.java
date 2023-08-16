package finalmelontoken.sharestudying.domain.user.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import finalmelontoken.sharestudying.domain.board.entity.Board;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@Entity
@NoArgsConstructor
@DynamicUpdate
@Data
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; //기본키

    @Column(nullable = false)
    private String name; //유저 이름

    @Column(nullable = false)
    private String password; //유저 비밀번호

    @Column(nullable = false)
    private String email; //유저 구글 이메일

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "user")
    private List<Board> board = new ArrayList<>();

    @Builder
    public User(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
