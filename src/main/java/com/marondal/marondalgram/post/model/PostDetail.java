package com.marondal.marondalgram.post.model;

import java.util.List;

import com.marondal.marondalgram.post.comment.model.Comment;

public class PostDetail {
	// 포스트 하나 + 댓글 리스트 + 좋아요 갯수 
	private Post post;
	private List<Comment> commentList;
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	

}
