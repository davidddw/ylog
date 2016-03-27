package org.livecloud.ylog.service;

import java.util.List;
import java.util.Set;

import org.livecloud.ylog.entity.Article;
import org.livecloud.ylog.entity.Tag;
import org.livecloud.ylog.mapper.TagMapper;
import org.livecloud.ylog.utils.SetOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagService {
    @Autowired
	private TagMapper tagMapper;
	
	public void addNewTag(String tagValue){
		Tag tag = new Tag(tagValue);
		tagMapper.addTag(tag);
	}
	
	public boolean deleteByTagId(long tagId){
		Tag tag = tagMapper.getTag(tagId);
		if(tag==null){
			return false;
		}else {
			for (Article article : tag.getArticles()){
				article.setTags(null);
			}
			tag.setArticles(null);
			tagMapper.deleteTag(tagId);
			return true;
		}
	}
	
	public Tag findById(long tagId) {
		return tagMapper.getTag(tagId);
	}
	
	public Tag findByName(String tagName) {
		List<Tag> tags = tagMapper.getByName(tagName);
		return tags.size()>0 ? tags.get(0): null;
	}
	
	public boolean checkTagExistById(long id) {
		return tagMapper.exists(id);
	}
	
	public boolean checkTagExistByName(String tagValue) {
		return tagMapper.getByName(tagValue).size()>0 ? true:false;
	}
	
	public void addTagFromStrings(String[] tagValues) {
		List<Tag> list = tagMapper.getAllTags();
		Set<Tag> newTag = SetOpt.diff(list, tagValues, Tag.class);
		for(Tag tag : newTag){
		    tagMapper.addTag(tag);
		}
	}
}
