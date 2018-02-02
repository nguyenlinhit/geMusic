package vn.edu.tdmu.repositories;

import vn.edu.tdmu.models.User;



/**
 * Created by NguyenLinh on 1/29/2018.
         *
         */
public interface UserRepository extends BaseRepository<User, Integer>{
    User findByUsername(String username);

    User findByEmail(String email);
}
