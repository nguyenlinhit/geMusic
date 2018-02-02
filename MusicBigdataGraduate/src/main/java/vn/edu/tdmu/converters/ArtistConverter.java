package vn.edu.tdmu.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vn.edu.tdmu.models.Artist;
import vn.edu.tdmu.services.ArtistService;

/**
 * @author Created by NguyenLinh on 02/02/2018
 */
@Component
public class ArtistConverter implements Converter<Object, Artist> {

    final
    ArtistService artistService;

    @Autowired
    public ArtistConverter(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public Artist convert(Object source) {
        if (source.equals("")){
            return null;
        }

        if (source instanceof Artist){
            return (Artist) source;
        }

        Integer id = Integer.parseInt((String) source);

        return this.artistService.findById(id);
    }
}