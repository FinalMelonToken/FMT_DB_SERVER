package finalmelontoken.sharestudying.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1518357621L;

    public static final QUser user = new QUser("user");

    public final ListPath<finalmelontoken.sharestudying.domain.board.entity.Board, finalmelontoken.sharestudying.domain.board.entity.QBoard> board = this.<finalmelontoken.sharestudying.domain.board.entity.Board, finalmelontoken.sharestudying.domain.board.entity.QBoard>createList("board", finalmelontoken.sharestudying.domain.board.entity.Board.class, finalmelontoken.sharestudying.domain.board.entity.QBoard.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

