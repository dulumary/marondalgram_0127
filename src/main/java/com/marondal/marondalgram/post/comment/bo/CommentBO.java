package com.marondal.marondalgram.post.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.post.comment.dao.CommentDAO;
import com.marondal.marondalgram.post.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	public int addComment(int postId, int userId, String userName, String content) {
		return commentDAO.insertComment(postId, userId, userName, content);
	}
	
	// postId로 댓글 리스트 가져오기  
	public List<Comment> getCommentList(int postId) {
		return commentDAO.selectCommentList(postId);
	}
	
	public int deleteComment(int postId) {
		return commentDAO.deleteComment(postId);
	}

}
