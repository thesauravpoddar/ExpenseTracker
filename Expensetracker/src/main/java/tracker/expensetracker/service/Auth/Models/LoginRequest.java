package tracker.expensetracker.service.Auth.Models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginRequest {
    private String email;
    private String password;
}
