package vn.edu.tdmu.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vn.edu.tdmu.models.Week;
import vn.edu.tdmu.services.WeekService;

/**
 * Created by NguyenLinh on 2/2/2018.
 *
 */
@Component
public class WeekConverter implements Converter<Object, Week> {

    WeekService weekService;

    @Autowired
    public WeekConverter(WeekService weekService) {
        this.weekService = weekService;
    }

    @Override
    public Week convert(Object element) {
        if (element.equals("")) {
            return null;
        }

        if (element instanceof Week) {
            return (Week) element;
        }

        Integer id = Integer.parseInt((String) element);
        return weekService.findById(id);
    }
}
