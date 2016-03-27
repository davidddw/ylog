package org.livecloud.ylog.service;

import java.util.HashMap;
import java.util.List;

import org.livecloud.ylog.entity.Options;
import org.livecloud.ylog.mapper.OptionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("optionsService")
public class OptionsService {

    @Autowired
	private OptionsMapper optionsMapper;

	public Options findById(long optionId){
		return optionsMapper.getOption(optionId);
	}
	
	public long addNewOption(Options options){
		return optionsMapper.addOption(options);
	}
	
	public long updateOption(Options options){
		return optionsMapper.updateOption(options);
	}
	
	public HashMap<String, String> findAllOptions() {
		HashMap<String, String> options = new HashMap<String, String>();
		for (Options option : optionsMapper.getOptions()){
			options.put(option.getName(), option.getValue());
		}
		return options;
	}
	
	public List<Options> findOptions() {
		return optionsMapper.getOptions();
	}
	
	public boolean removeOption(Options options){
		Options option = optionsMapper.getOption(options.getId());
		if(option == null){
			return false;
		} else {
		    optionsMapper.deleteOption(option);
			return true;
		}
	}
	
	public boolean removeOptionById(long optionId){
		Options g = optionsMapper.getOption(optionId);
		if(g == null){
			return false;
		} else {
		    optionsMapper.deleteOption(g);
			return true;
		}
	}

}
