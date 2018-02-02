package vn.edu.tdmu.repositories;

import vn.edu.tdmu.models.Role;

/**
 * Created by nguye on 1/29/2018.
 *
 */
public interface RoleRepository extends BaseRepository<Role, Integer>{
    Role findByType(String type);
}
