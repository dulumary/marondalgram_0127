<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
  	
  	<link rel="stylesheet" href="/static/css/style.css">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	<title>마론달그램</title>
</head>
<body>

<div id="wrap" >
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="d-flex justify-content-center">
			<div class="col-6">
				
				<!--  입력 상자  -->
				<div class="border rounded mt-3  bg-white">
					<div>
						<textarea class="form-control w-100 border-0 non-resize" rows=3 id="contentInput"></textarea>
					</div>
					<div class="d-flex justify-content-between m-2">
						<span class="img-icon"> <i class="bi bi-image" id="imgBtn"></i></span>
						<input type="file" id="fileInput" class="d-none">
						
						<button class="btn btn-sm btn-info" id="uploadBtn">업로드</button>
					</div>
				</div>
			</div>	
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
	<script>
		$(document).ready(function() {
			
			$("#imgBtn").on("click", function() {
				// fileInput 클릭 효과
				$("#fileInput").click();
				
			});
			
			$("#uploadBtn").on("click", function() {
				let content = $("#contentInput").val().trim();
				
				if(content == "") {
					alert("내용을 입력하세요");
					return ;
				}
				
				// 파일 유효성 검사
				if($("#fileInput")[0].files.length == 0) {
					alert("파일을 선택해주세요");
					return;
				}
				
				var formData = new FormData();
				formData.append("content", content);
				formData.append("file", $("#fileInput")[0].files[0]);
				
				$.ajax({
					type:"post",
					url:"/post/create",
					data:formData,
					enctype:"multipart/form-data",
					processData:false,
					contentType:false,
					success:function(data) {
						
						if(data.result == "success") {
							location.reload();
						} else {
							alert("글쓰기 실패");
						}
					},
					error:function() {
						alert("글쓰기 에러");
					}
				});
				
			});
		});
	
	</script>

</body>
</html>