package org.livecloud.ylog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.livecloud.ylog.entity.Category;

public interface CategoryMapper {
	
    List<Category> getCategories(RowBounds rowBounds);
	
	List<Category> getCategories();

    Category getCategory(@Param("id") long id);

    long getCount();

    long deleteCategory(Category c);

    long addCategory(Category category);
    
    long updateCategory(Category category);
}
