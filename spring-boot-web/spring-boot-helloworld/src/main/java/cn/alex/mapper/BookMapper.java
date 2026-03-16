package cn.alex.mapper;

import cn.alex.bean.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by WCY on 2021/7/29
 */
@Mapper
public interface BookMapper {
    /**
     * 查询图书
     * @return 图书列表
     */
    List<Book> findBookList();

    /**
     * 录入图书
     * @param book 图书
     * @return 影响行数
     */
    int insertBook(Book book);
}
