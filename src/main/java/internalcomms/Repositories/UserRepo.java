package internalcomms.Repositories;

import internalcomms.Entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username=:username")
    UserEntity findByUsername(@Param("username")String u);
    @Query("SELECT m FROM UserEntity m WHERE m.mail=:mail")
    UserEntity findByMail(@Param("mail")String m);
}
