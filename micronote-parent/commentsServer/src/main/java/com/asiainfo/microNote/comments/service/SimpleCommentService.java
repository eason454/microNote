package com.asiainfo.microNote.comments.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.asiainfo.microNote.comments.domain.entity.Comment;
import com.asiainfo.microNote.comments.domain.entity.CommentRecord;
import com.asiainfo.microNote.comments.pojo.CommentRecordInfo;
import com.asiainfo.microNote.comments.pojo.CommentUser;
import com.asiainfo.microNote.comments.repository.ICommentRecordRepository;
import com.asiainfo.microNote.comments.repository.ICommentRepository;
import com.asiainfo.microNote.comments.service.user.IUserService;

/**
 * 
 * @author yi
 *
 */
@Component
public class SimpleCommentService implements ICommentsService {
	@Autowired
	ICommentRecordRepository commentRecordRepository;

	@Autowired
	ICommentRepository commentRepository;

	@Autowired
	IUserService userService;

	// 用户信息弱引用
	public static final Map<String, CommentUser> userMap = new HashMap<String, CommentUser>();

	@Override
	public Page<CommentRecordInfo> getComment(long targetId, String targetType, int page, int size) {
		// 查詢評論
		Comment comment = commentRepository.findByCommentTargetIdAndTargetTypeOrderByCreateDateDesc(targetId,
				targetType);
		
		Page<CommentRecord> recordsPage = commentRecordRepository.findByCommentOrderByCreateDateDesc(comment,
				new PageRequest(page, size, new Sort("createDate")));
		
		if (comment == null)
			return null;
		
		List<CommentRecordInfo> commentRecordInfoList = new ArrayList<CommentRecordInfo>();

		for (CommentRecord commentRecord : recordsPage.getContent()) {
			CommentRecordInfo commentRecordInfo = new CommentRecordInfo();
			// 获取用户信息
			CommentUser commentUser = getUser(commentRecord.getUserId());
			// 生成评论信息
			BeanUtils.copyProperties(commentRecord, commentRecordInfo);
			commentRecordInfo.setUserName(commentUser.getName());
			commentRecordInfoList.add(commentRecordInfo);
		}
		Page<CommentRecordInfo> pageCommentRecordInfo = new PageImpl<CommentRecordInfo>(commentRecordInfoList);
		BeanUtils.copyProperties(recordsPage, pageCommentRecordInfo);
		// 查詢用戶信息獲取品論用戶名
		return pageCommentRecordInfo;
	}

	@Override
	public boolean addComment(long targetId, String targetType, CommentRecord commentRecord) {
		Comment comment;
		// 判断是否已经有的对象的评论
		comment = commentRepository.findByCommentTargetIdAndTargetTypeOrderByCreateDateDesc(targetId, targetType);
		// 没有增加一个评论
		if (comment == null)
			comment = commentRepository.save(new Comment(targetId, targetType));
		// 对评论进行增加评论项目
		commentRecord.setComment(comment);
		comment.getRecords().add(commentRecord);
		commentRepository.save(comment);
		return true;
	}

	public boolean updateComment(CommentRecord commentRecord){
		CommentRecord newCommentRecord = commentRecordRepository.findOne(commentRecord.getId());
		newCommentRecord.setContent(commentRecord.getContent());
		newCommentRecord.getComment();
		commentRecordRepository.save(newCommentRecord);
		return true;
	}
	
	@Override
	public boolean deleteComment(long commentRecordId) {
		commentRecordRepository.delete(commentRecordId);
		return true;
	}

	/**
	 * 获取user的信息
	 * 
	 * @param userId
	 * @return
	 */
	private CommentUser getUser(String userId) {

		CommentUser commentUser;
		if (userMap.containsKey(userId))
			commentUser = userMap.get(userId);
		else {
			commentUser = userService.getUserById(userId);
			userMap.put(userId, commentUser);
		}
		return commentUser;
	}

}
