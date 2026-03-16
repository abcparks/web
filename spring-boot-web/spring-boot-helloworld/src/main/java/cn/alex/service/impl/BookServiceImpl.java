package cn.alex.service.impl;

import cn.alex.bean.Book;
import cn.alex.mapper.BookMapper;
import cn.alex.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WCY on 2021/7/29
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findBookList() {
        List<Book> bookList = bookMapper.findBookList();
        return bookList;
    }

    @Override
    public int saveBook(Book book) {
        return bookMapper.insertBook(book);
    }
}
