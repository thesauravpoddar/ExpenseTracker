package tracker.expensetracker.Controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
