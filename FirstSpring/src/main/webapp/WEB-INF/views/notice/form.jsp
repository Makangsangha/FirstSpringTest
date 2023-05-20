<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>공지게시판 등록</title>
</head>
<body>
	<c:set value="등록" var="name"/>			
	<c:if test="${status eq 'u'}"> 
		<c:set value="수정" var="name"/> 
	</c:if>
	
	<div class="jumbotron">
			<div class="container">		
				<h3 class="display-3">공지게시판 ${name }</h3>	
			</div>
	</div>
	<div class="container">
		<form name="newWrite" id="boardForm" action="/notice/insert.do" class="form-horizontal" method="post">
			<c:if test="${status eq 'u' }"> 
				<input name="boNo" type="hidden" class="form-control" value="${notice.boNo }">
				<input name="boWriter" type="hidden" class="form-control" value="${notice.boWriter }">
			</c:if>	
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<input id="boTitle" maxlength="100" name="boTitle" type="text" class="form-control" placeholder="subject" value="${notice.boTitle }">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8">
					<textarea id="boContent" name="boContent" cols="50" rows="5" class="form-control" placeholder="content">${notice.boContent }</textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<c:if test="${status ne 'u' }">  
						<input type="button" class="btn btn-primary " id="inputBtn" value="${name }">
					</c:if>			
					<c:if test="${status eq 'u' }">  
						<input type="button" class="btn btn-primary " id="updateBtn" value="${name }">
						<input type="button" class="btn btn-primary " id="backBtn" value="뒤로가기">
					</c:if>			
					<input id="listBtn" type="button" class="btn btn-primary " value="목록가기 ">
				</div>
			</div>
		</form>
		<hr>
	</div>
</body>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	CKEDITOR.replace("boContent");
	CKEDITOR.config.allowedContent = true;
	
	var inputBtn = $("#inputBtn");
	var updateBtn = $("#updateBtn");
	var listBtn = $("#listBtn");
	var backBtn = $("#backBtn");
	
	listBtn.on("click", function(){
		location.href = "/notice/list.do"
	})
	
	backBtn.on("click", function(){
		history.back();
	})
	
	inputBtn.on("click", function(){
		var title = $("#boTitle").val().trim();		
		var content = CKEDITOR.instances.boContent.getData();	
		
		if(title == ""){
			alert("제목을 입력해주세요");
			$("#boTitle").focus();
			return false;
		}
		
		if(content.trim() == ""){
			alert("내용을 입력해주세요!");
			$("#boContent").focus();
			return false;				
		}
		
		$("#boardForm").submit();
		
	});
	
	updateBtn.on("click", function(){
		var title = $("#boTitle").val().trim();		
		var content = CKEDITOR.instances.boContent.getData();	
		
		if(title == ""){
			alert("제목을 입력해주세요");
			$("#boTitle").focus();
			return false;
		}
		
		if(content == ""){
			alert("내용을 입력해주세요!");
			$("#boContent").focus();
			return false;				
		}
		
		$("#boardForm").attr('action', '/notice/update.do')
		$("#boardForm").submit();		
	});
	
	
});
</script>
</html>



