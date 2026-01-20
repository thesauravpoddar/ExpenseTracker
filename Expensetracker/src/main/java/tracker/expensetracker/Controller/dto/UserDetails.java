package tracker.expensetracker.Controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDetails {
    private String name;
    private String email;
    private UpdateRequestType updateRequestType;
}
