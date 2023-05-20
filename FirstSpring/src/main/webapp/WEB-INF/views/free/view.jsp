<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>자유게시판 상세보기</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">자유게시판 상세보기 / 수정 / 삭제</h3>
		</div>
	</div>

	<div class="container">
		<form id="allForm" action="/free/update.do" class="form-horizontal" method="get">
			<input type="hidden" value="${free.boNo }"  name="boNo">
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					${free.boTitle }
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8" style="word-break: break-all;">
					${free.boContent }
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<p>
						<a id="delA"	href=""	class="btn btn-danger"> 삭제</a> 
						<input type="submit" class="btn btn-success" value="수정 ">
						<a href="/free/list.do" class="btn btn-primary"> 목록</a>
					</p>
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
	var delA = $("#delA");
	
	delA.on("click", function(event){
		event.preventDefault();
		if(confirm("정말 삭제하시겠습니까?")){
			$("#allForm").attr('action', '/free/delete.do')
			$("#allForm").attr('method', 'post')
			$("#allForm").submit();
		}else{
			alert("삭제 취소");
			delA.attr("href", '#');
		}
	});
})
</script>
</html>


