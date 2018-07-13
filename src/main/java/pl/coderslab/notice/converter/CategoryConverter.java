package pl.coderslab.notice.converter;

import pl.coderslab.notice.entity.Category;
import pl.coderslab.notice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.notice.repository.CategoryRepository;


public class CategoryConverter implements Converter<String, Category> {


    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category convert(String s) {
        return categoryRepository.getOne(Long.parseLong(s));
    }
}


