package com.asiainfo.microNote.comments.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.asiainfo.microNote.comments.domain.entity.Comment;
import com.asiainfo.microNote.comments.domain.entity.CommentRecord;



public interface ICommentRecordRepository extends PagingAndSortingRepository<CommentRecord, Long>{
	Page<CommentRecord>  findByCommentOrderByCreateDateDesc(Comment comment, Pageable pageable);
}
