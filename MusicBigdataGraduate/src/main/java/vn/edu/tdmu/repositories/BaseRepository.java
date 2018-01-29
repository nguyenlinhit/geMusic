package vn.edu.tdmu.repositories;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

/**
 * 
 */
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID>{
    
}