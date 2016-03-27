package org.livecloud.ylog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.livecloud.ylog.entity.Options;

public interface OptionsMapper {

	List<Options> getOptions();

    long deleteOption(Options g);
    
    long getCount();

    Options getOption(@Param("id") long id);

    long updateOption(Options options);
    
    long addOption(Options options);
}
