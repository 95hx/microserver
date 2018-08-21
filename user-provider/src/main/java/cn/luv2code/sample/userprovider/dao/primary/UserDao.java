package cn.luv2code.sample.userprovider.dao.primary;

import cn.luv2code.sample.userprovider.entity.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
}
