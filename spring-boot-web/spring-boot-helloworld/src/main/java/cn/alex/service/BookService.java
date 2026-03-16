package cn.alex.service;

import cn.alex.bean.Book;

import java.util.List;

/**
 * Created by WCY on 2021/7/29
 */
public interface BookService {

    /**
     * 查询所有图书
     */
    List<Book> findBookList();

    /**
     * 保存图书
     */
    int saveBook(Book book);
}
