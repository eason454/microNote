package com.asiainfo.microNote.comments.service.user;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asiainfo.microNote.comments.pojo.CommentUser;

/**
 * 獲取用戶信息
 * @author yi
 */
@FeignClient("weeklyreport")
public interface IUserService {
	
	/**
	 * 根據用戶id查找用戶
	 * @param UserId
	 * @return
	 */
	@RequestMapping(path = "/microNote/queryUser/{user_id}", method = RequestMethod.GET)
	CommentUser getUserById(@PathVariable("userId") String UserId);
}
