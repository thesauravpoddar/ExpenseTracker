package tracker.expensetracker.service.Auth.Models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
