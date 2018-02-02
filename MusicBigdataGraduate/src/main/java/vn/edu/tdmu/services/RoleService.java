package vn.edu.tdmu.services;

import vn.edu.tdmu.models.Role;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
public interface RoleService extends BaseService<Role, Integer>{
    Role findByType(String type);

    Role update(Role update);

    boolean isTypeUnique(Integer id, String type);
}
