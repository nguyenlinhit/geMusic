package vn.edu.tdmu.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vn.edu.tdmu.models.Song;
import vn.edu.tdmu.services.SongService;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
@Component
public class SongConverter implements Converter<Object, Song>{

    SongService songService;

    @Autowired
    public SongConverter(SongService songService) {
        this.songService = songService;
    }

    @Override
    public Song convert(Object element) {
        if (element.equals("")) {
            return null;
        }

        if (element instanceof Song) {
            return (Song) element;
        }

        Integer id = Integer.parseInt((String) element);
        return songService.findById(id);
    }
}
