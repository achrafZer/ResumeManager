package myboot.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import myboot.app.model.XUser;


@Repository
@Transactional
public interface XUserRepository extends JpaRepository<XUser, String> {

}

