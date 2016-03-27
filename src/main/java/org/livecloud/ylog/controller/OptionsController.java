package org.livecloud.ylog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.livecloud.ylog.entity.Options;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("optionsController")  
@RequestMapping("/admin")
public class OptionsController extends BaseController {
	
	/** 保存新增 */  
	@RequestMapping(method=RequestMethod.POST, value="/global")
	public @ResponseBody long addOption(@RequestBody Options options) {
		return optionsService.addNewOption(options);
	}
	
	/** 显示 **/ 
	@RequestMapping(method=RequestMethod.GET, value="/global/{id}")
	public @ResponseBody Options getGlobalSetting(@PathVariable String id) {
		Options options = optionsService.findById(Integer.parseInt(id));
		return options;
	}
	
	/** 显示全部  **/
	@RequestMapping(method=RequestMethod.GET, value="/global")
	public @ResponseBody List<Options> getAllGlobalSettings(HttpServletRequest request) {
		return optionsService.findOptions();
	}
	
	/** 保存更新 */ 
	@RequestMapping(method=RequestMethod.PUT, value="/global/{id}")
	public @ResponseBody long updateGlobalSetting(@RequestBody Options options, @PathVariable String id) {
		return optionsService.updateOption(options);
	}
	
	/** 删除 */  
	@RequestMapping(method=RequestMethod.DELETE, value="/global/{id}")
	public @ResponseBody Map<String, Object> removeGlobalSetting(@PathVariable String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("result", optionsService.removeOptionById(Integer.parseInt(id)));
		return returnValues;
	}
	
	/** 批量删除 */  
    @RequestMapping(method=RequestMethod.DELETE, value="/global")  
    public @ResponseBody void batchDelete(@RequestParam("items") Integer[] items) {  
        for(int i = 0; i < items.length; i++) {    
        	optionsService.removeOptionById(items[i]);  
        }  
    }  
}
