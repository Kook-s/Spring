<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>공지게시판 등록</title>
</head>
<body>
<c:if test="${status eq 'u' }">
	<c:set var="name" value="수정"></c:set>
</c:if>
<c:if test="${status eq 'i' }">
	<c:set var="name" value="등록"></c:set>
</c:if>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">공지게시판 ${name }</h3>
		</div>
	</div>
	<div class="container">
		<form id="form" action="" class="form-horizontal" method="post">
			<input name="id" type="hidden" class="form-control" value="">
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<input id="title" name="boTitle" type="text" class="form-control" value="${notice.boTitle }" placeholder="subject">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8">
					<textarea id="content" name="boContent" cols="50" rows="5" class="form-control" placeholder="content">${notice.boContent }</textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="button" class="btn btn-primary " value="${name }">				
					<input type="button" class="btn btn-primary " value="취소">
				</div>
			</div>
		</form>
		<hr>
	</div>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
	
</body>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(()=>{
	CKEDITOR.replace("content");
	CKEDITOR.config.allowContent = true;
	
	var btn = $("input[type=button]")
	btn.on("click",function(){
		var val = $(this).val()
		if(val=='수정'){
			var title = $("#title").val()
			//var content = $("#content").val()
			var content = CKEDITOR.instances.content.getData();
			if(title ==""){
				alert("제목을 입력 해주세요!");
				$("#title").focus();
				return false;
			}
			
			if(content ==""){
				alert("내용을 입력 해주세요!");
				$("#content").focus();
				return false;
			}
			$("#form").append("<input type='text' name='boNo' value='${notice.boNo}'>")
			$("#form").submit()
		}
		if(val=='취소'){
			if('${status}'=='u'){
				location.href="/notice/detail.do?boNo=${notice.boNo }"
			}else{
				location.href="/notice/list.do"
			}
		}
		if(val=='등록'){
			var title = $("#title").val()
			/* var content = $("#content").val() */
			var content = CKEDITOR.instances.content.getData();
			if(title ==""){
				alert("제목을 입력 해주세요!");
				$("#title").focus();
				return false;
			}
			
			if(content ==""){
				alert("내용을 입력 해주세요!");
				$("#content").focus();
				return false;
			}
			$("#form").attr("action","/notice/insert.do")
			$("#form").submit()
		}
	})
	
})
</script>
</html>



