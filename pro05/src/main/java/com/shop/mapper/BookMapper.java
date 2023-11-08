package com.shop.mapper;

import com.shop.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    // Mapper는 public이 아닌 default로 주어 여기에서만 컨트롤 가능하게
    int createBook(Book book);
    List<Book> readBookAll();
    Book readBook(Long seq);

}
