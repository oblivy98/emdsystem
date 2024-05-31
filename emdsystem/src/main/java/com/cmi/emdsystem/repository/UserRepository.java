package com.cmi.emdsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmi.emdsystem.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmailAddress(String emailAddress);

    public User findByUserIdAndEmailAddress(Integer userId, String emailAddress);

    public Integer countByUserId(Integer userId);

    @Query("SELECT Count(userAccess) FROM User")
    int countByUserAccess();

    @Query("SELECT Count(emailAddress) FROM User Where emailAddress = :emailAddress")
    int checkIfEmailExist(@Param("emailAddress") String emailAddress);

    @Query("SELECT Count(emailAddress) FROM User Where emailAddress = :emailAddress AND id != :userId")
    int checkIfEmailExistOnOtherAccount(@Param("emailAddress") String emailAddress, @Param("userId") Integer userId);

    public List<User> findBylinkUserIsNull();

    public List<User> findBylinkUserIsNotNull();

    // @Query("SELECT Count() FROM User WHERE )

    /* Link User Repository */
    /*
     * Check Linked User
     * 
     * @Query("SELECT DISTINCT T1.userId, T1.userName FROM User T1 " +
     * "LEFT JOIN Link T2 on T1.userId = T2.userId " +
     * "WHERE T2.userId IS NOT NULL") public List<User> listofLinkedUser();
     */

    /*
     * Check Linked User practice
     * 
     * @Query("SELECT DISTINCT FROM User T1 " +
     * "LEFT JOIN Link T2 ON T1.userId = T2.userLink(userId)") public List<User>
     * listofLinkedUser();
     */

    /*
     * Check Linked User practice
     * 
     * @Query("SELECT T1.userId, T1.userName FROM User T1") public List<UserRsp>
     * listofLinkedUser();
     */

    /*
     * Check Linked User practice
     * 
     * @Query("SELECT T1 FROM User T1 " +
     * "LEFT JOIN Link T2 ON T1.userId = T2.userId " +
     * "WHERE T2.userId IS NOT NULL") public List<User> listofLinkedUser();
     */

    // public List<User> findByLinksNotIn(List<Link> link);
}
