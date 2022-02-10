package com.marondal.marondalgram.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("imagePath") String imagePath,
			@Param("content") String content);

}
