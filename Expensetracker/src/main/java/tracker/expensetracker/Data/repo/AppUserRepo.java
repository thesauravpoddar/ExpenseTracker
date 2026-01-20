package tracker.expensetracker.Data.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import tracker.expensetracker.Data.model.AppUser;

import java.util.Optional;

public interface AppUserRepo extends MongoRepository<AppUser , String> {
   Optional<AppUser> findByEmail(String email);
   boolean existsByEmail(String email);
}
