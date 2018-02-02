package vn.edu.tdmu.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vn.edu.tdmu.models.Genre;
import vn.edu.tdmu.services.GenreService;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
@Component
public class GenreConverter implements Converter<Object, Genre>{


    GenreService genreService;

    @Autowired
    public GenreConverter(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public Genre convert(Object source) {
        if (source.equals("")){
            return null;
        }

        if (source instanceof  Genre){
            return (Genre) source;
        }

        Integer id = Integer.parseInt((String) source);
        return genreService.findById(id);
    }
}
