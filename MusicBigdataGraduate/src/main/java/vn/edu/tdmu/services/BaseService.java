package vn.edu.tdmu.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Creted by NguyenLinh on 01/29/2018
 */
public interface BaseService<T, ID extends Serializable>{
    List<T> findAll();

    T findById(ID id);

    T create(T newEntry);

    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    void delete(ID id);
}