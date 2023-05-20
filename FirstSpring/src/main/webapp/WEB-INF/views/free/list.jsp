<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>자유게시판 목록</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">자유게시판 목록</h1>
		</div>
	</div>
	<div class="container">
		<form action="" method="post">
			<div>
				<div class="text-right">
					<span class="">전체 ${pagingVO.totalRecord } 건	</span>
				</div>
			</div>
			<div style="padding-top: 50px">
				<div class="row" style="padding-bottom: 20px">
					<div class="col-md-7"></div>
					<div class="col-md-5">
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-10">
								<form action="" method="get">
									<select name="searchType">
										<option value="title" <c:if test="${searchType == 'title' }"><c:out value="selected"/></c:if>>제목</option>
										<option value="writer" <c:if test="${searchType == 'writer' }"><c:out value="selected"/></c:if>>작성자</option>
										<option value="both" <c:if test="${searchType == 'both' }"><c:out value="selected"/></c:if>>제목+작성자</option>
									</select>
									<input type="text" name="searchWord" value="${searchWord }">
									<input type="submit" value="검색">
								</form>
							</div>
						</div>
					</div>
				</div>
				<table class="table">
					<thead class="table-dark">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="freeList" value="${pagingVO.dataList }"></c:set>
						<c:choose>
							
							<c:when test="${empty freeList }">
								<tr>
									<td colspan="5">조회하신 게시글이 존재하지 않습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${freeList }" var="free">
									<tr id=${free.boNo }>
										<td>${free.boNo }</td>
										<td>${free.boTitle }</td>
										<td>${free.boDate }</td>
										<td>${free.boWriter }</td>
										<td>${free.boHit }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						
					</tbody>
				</table>
			</div>
			<div align="left">
				<a onclick="checkForm(); return false;" class="btn btn-primary">&laquo;글쓰기</a>
				<div class="card-footer clearfix" id="pagingArea">
					${pagingVO.pagingHTML }
				</div>
			</div>
		</form>
		<hr>
	</div>
</body>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(()=>{
	var parea = $("#pagingArea");
	$("#pagingArea a").on("click",function(e){
		e.preventDefault()
		var page=$(this).data("page")
		location.href = "/free/list.do?page="+page;
	})
	
	$("tbody tr").on("click",function(){
		var bono=$(this).attr("id")
		location.href = "/free/detail.do?boNo="+bono
	})
})

function checkForm(){
	location.href="/free/insert.do";
}


</script>
</html>





