package tracker.expensetracker.Data.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collation = "app-users")
public class AppUser {
    @Id
    private String userId;
    private String name;
    @Indexed
    private String email;
    private String password;
    @CreatedDate
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime Modified_at;
}
