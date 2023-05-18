package internalcomms.Repositories;

import internalcomms.Entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    /**
     * SQL запрос на получение пользователя по имени
     * @return UserEntity
     */
    @Query("SELECT u FROM UserEntity u WHERE u.username=:username")
    UserEntity findByUsername(@Param("username")String user);

    /**
     * SQL запрос на получение пользователя по почте
     * @return UserEntity
     */
    @Query("SELECT m FROM UserEntity m WHERE m.mail=:mail")
    UserEntity findByMail(@Param("mail")String mail);
}
