package tracker.expensetracker.Controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInfo {
    private String name;
    private String email;
    private String createdAt;

}
