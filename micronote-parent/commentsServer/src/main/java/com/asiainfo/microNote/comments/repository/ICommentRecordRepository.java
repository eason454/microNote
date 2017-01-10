package com.asiainfo.microNote.comments.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.asiainfo.microNote.comments.domain.entity.CommentRecord;



public interface ICommentRecordRepository extends PagingAndSortingRepository<CommentRecord, Long>{
	
}
