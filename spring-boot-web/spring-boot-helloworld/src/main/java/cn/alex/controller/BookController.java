package cn.alex.controller;

import cn.alex.bean.Book;
import cn.alex.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WCY on 2021/7/29
 */
@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("findBookList")
    public List<Book> findBookList() {
        List<Book> bookList = bookService.findBookList();
        return bookList;
    }

    @PostMapping("saveBook")
    public String saveBook(@RequestBody Book book) {
        int result = bookService.saveBook(book);
        if (result > 0) {
            return "保存图书成功";
        }
        return "保存图书失败";
    }

}
