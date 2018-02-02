package vn.edu.tdmu.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vn.edu.tdmu.models.Role;
import vn.edu.tdmu.services.RoleService;

/**
 * Created by nguye on 2/2/2018.
 *
 */
@Component
public class RoleConverter implements Converter<Object, Role> {
    RoleService roleService;

    @Autowired
    public RoleConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role convert(Object source) {

            if (source instanceof Role) {
                return (Role) source;
            }

            Integer id = Integer.parseInt((String) source);
            return roleService.findById(id);
    }
}
