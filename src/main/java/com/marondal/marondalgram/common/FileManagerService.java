package com.marondal.marondalgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public final static String FILE_UPLOAD_PATH = "D:\\김인규 강사\\web\\0927\\springProject\\upload\\image/";
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);

	// 파일 저장 
	public static String saveFile(int userId, MultipartFile file) {
		
		if(file == null) {
			logger.error("FileManagerService::saveFile - 업로드 파일 없음");
			return null;
		}
		
		// 파일 경로
		// 사용자 별로 구분할 수 있도록
		// 사용자가 파일을 올린 시간 으로 구분
		// /12_33923959/test.png
		// 1970 년 1월 1일 기준으로 현지 밀리 세컨이 경과 되었는지 나타내는 수
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		// 디렉토리 생성
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 디렉토리 생성 에러
			logger.error("FileManagerService::saveFile - 디렉토리 생성 에러");
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			// 파일 저장 경로 + 파일 이름 경로 객체
			Path path = Paths.get(filePath + file.getOriginalFilename());
			// 파일 저장
			Files.write(path, bytes);
		} catch (IOException e) {
			// 파일 저장 실패
			logger.error("FileManagerService::saveFile - 파일 저장 에러");
			e.printStackTrace();
			return null;
		}
		
		// 파일 접근 가능한 주소 리턴
		// <img src="/images/12_124909218/test.png">
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
	// 파일 삭제 
	public static void removeFile(String filePath) {
		
		if(filePath == null) {
			logger.error("FileManagerService::removeFile - 삭제할 파일 없음");
			return ;
		}
		
		// 삭제할 파일 경로 
		// filePath : /images/2_981310982/test.png
		// 실제 파일 경로 : D:\\김인규 강사\\web\\0927\\springProject\\upload\\image\\2_981310982/test.png
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
		
		// 파일지우기 
		Path path = Paths.get(realFilePath);
		// 파일이 있는지 확인
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService::removeFile - 파일 삭제 실패");
				e.printStackTrace();
			}
		}
		
		// 디렉토리 삭제 (폴더)
		// 실제 디렉토리 경로 : D:\\김인규 강사\\web\\0927\\springProject\\upload\\image\\2_981310982
		path = path.getParent();
	
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService::removeFile - 디렉토리 삭제 실패");
				e.printStackTrace();
			}
		}
	}
}
