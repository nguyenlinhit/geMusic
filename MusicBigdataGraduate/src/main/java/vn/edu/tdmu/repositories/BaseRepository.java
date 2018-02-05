package vn.edu.tdmu.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;


/**
 * @author Created by NguyenLinh on 01/29/2018
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID>{
    List<T> findAll();

    Optional<T> findOne(ID id);

    T save(T persisted);

    void delete(T deleted);

    long count();

    void flush();
}