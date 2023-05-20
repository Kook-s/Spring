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
		<form name="newUpdate" action="" class="form-horizontal" method="post">
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
						<a	href="/free/delete.do?boNo=${free.boNo }"	class="btn btn-danger">삭제</a> 
						<a	href="/free/update.do?boNo=${free.boNo }"	class="btn btn-success">수정</a> 
						<a href="/free/list.do" class="btn btn-primary">목록</a>
					</p>
				</div>
			</div>
		</form>
		<hr>
	</div>
</body>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(()=>{
	var as = $(".form-group a")
	as.on("click",function(e){
		e.preventDefault()
		var text=$(this).text()
		if(text=='삭제'){
			if(confirm("정말 삭제하시겠습니까?")){
				location.href="/free/delete.do?boNo=${free.boNo }"
			}
		}
		if(text=='수정'){
			location.href="/free/update.do?boNo=${free.boNo }"	
		}
		if(text=='등록'){
			alert(3)
		}
		if(text=="목록"){
			location.href="/free/list.do"	
		}
	})
})
</script>
</html>


