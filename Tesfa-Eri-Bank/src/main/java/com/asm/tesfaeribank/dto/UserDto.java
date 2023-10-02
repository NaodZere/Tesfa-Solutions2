package august.bank.app.bankproject.dto;

import lombok.Data;
import august.bank.app.bankproject.entity.Role;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private String id;

    private String username;

    private String email;


    private String password;

    private List<Role> roles;
}
