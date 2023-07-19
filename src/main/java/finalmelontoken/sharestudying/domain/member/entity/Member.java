package finalmelontoken.sharestudying.domain.member.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import finalmelontoken.sharestudying.domain.board.entity.Board;

import java.util.ArrayList;
import java.util.List;

@Table(name = "member")
@Entity
@NoArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name="user_id", nullable = false)
    private String user_id;
    @Column(name="pw", nullable = false)
    private String pw;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="email", nullable = false)
    private String email;
    @OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Board> board = new ArrayList<>();

    @Builder
    public Member(String user_id, String pw, String email, String name) {
        this.user_id = user_id;
        this.pw = pw;
        this.email = email;
        this.name = name;
    }
}
