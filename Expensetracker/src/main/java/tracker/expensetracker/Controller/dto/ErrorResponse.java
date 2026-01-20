package tracker.expensetracker.Controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
