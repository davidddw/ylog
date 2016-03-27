package org.livecloud.ylog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.livecloud.ylog.entity.Tag;

public interface TagMapper {
	
	List<Tag> getByName(String name);
	
	Tag getOneTagByName(@Param("tagValue") String tagValue);

    List<Tag> getAllTags();

    long deleteTag(long id);

    long updateTag(Tag tag);
    
    long addTag(Tag tag);

    Tag getTag(long tagId);

    boolean exists(long id);

}
