package tracker.expensetracker.Controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VerifyTokenResponse {
    private String userId;
}
