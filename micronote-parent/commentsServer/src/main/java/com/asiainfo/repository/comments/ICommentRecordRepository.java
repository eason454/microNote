package com.asiainfo.repository.comments;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.asiainfo.domain.entity.comments.CommentRecord;


public interface ICommentRecordRepository extends PagingAndSortingRepository<CommentRecord, Long>{
	
}
