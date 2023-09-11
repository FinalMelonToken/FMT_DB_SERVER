package finalmelontoken.sharestudying.model.response;

import finalmelontoken.sharestudying.model.entity.User;
import lombok.Getter;

@Getter
public class UserInfoResponse {

    private final Long userId;
    private final String name;
//    private final Role role;

    /* Entity -> Dto*/
    public UserInfoResponse(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
//        this.role = user.getRole();
    }
}