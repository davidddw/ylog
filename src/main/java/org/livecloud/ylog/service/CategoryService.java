package org.livecloud.ylog.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.livecloud.ylog.entity.Category;
import org.livecloud.ylog.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service("categoryService")
public class CategoryService {
    
    @Autowired
	private CategoryMapper categoryMapper;
		
	public long getCategoryCount() {
		return categoryMapper.getCount();
	}
	
	public Category findById(long categoryId) {
		return categoryMapper.getCategory(categoryId);
	}
	
	public Category findByClass(Category category) {
		return categoryMapper.getCategory(category.getId());
	}
	
	public List<Category> findAllCategories() {
		return categoryMapper.getCategories();
	}
	
	public PageInfo<Category> findAllCategories(int pageIndex, int pageSize) {
	    List<Category> list = categoryMapper.getCategories(new RowBounds(pageIndex, pageSize));
	    return new PageInfo<Category>(list); 
	}
	
	public long addNewCategory(Category category){
		return categoryMapper.addCategory(category);
	}
	
	public long updateCategory(Category category){
		return categoryMapper.updateCategory(category);
	}
	
	public boolean removeCategory(Category category){
		Category c = categoryMapper.getCategory(category.getId());
		if(c==null){
			return false;
		} else {
		    categoryMapper.deleteCategory(c);
			return true;
		}
	}
	
	public boolean removeCategoryById(long categoryId) {
		Category c = categoryMapper.getCategory(categoryId);
		if(c==null){
			return false;
		} else {
		    categoryMapper.deleteCategory(c);
			return true;
		}
	}
	
}
