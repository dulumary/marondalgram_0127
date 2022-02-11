package com.marondal.marondalgram.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.marondalgram.common.FileManagerService;
import com.marondal.marondalgram.post.dao.PostDAO;
import com.marondal.marondalgram.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		String filePath = FileManagerService.saveFile(userId, file);
		return postDAO.insertPost(userId, userName, filePath, content);
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}

}
