<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>

<script type="text/javascript">

	window.onload = function(){
		
		$j("#allCheck").click(function(){
			if($j("#allCheck").prop("checked")){
				$j(".typeCheck").prop("checked", true);
			}else{
				$j(".typeCheck").prop("checked", false);
			}
			
		});
		
		$j(".typeCheck").click(function(){
			if($j("input[name='codeId']:checked").length == 4){
				$j(".typeCheck").prop("checked", true);
			}else{
				$j("#allCheck").prop("checked", false);
			}
		});
	}
</script>

</head>
<body>
<table  align="center">
	<tr>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.codeName}
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href ="/board/boardWrite.do">글쓰기</a>
		</td>
	</tr>
	<tr>
		<td>
			<form class="boardList">
				<input type="checkbox" id="allCheck" class="typeCheck" value="a00"/>전체
					<c:forEach var="comCode" items="${comCodeList }" begin="0" end="3">
						<input type="checkbox" class="typeCheck" name="codeId" value="${comCode.codeId }">${comCode.codeName }</>
					</c:forEach>
				<input type="submit" id="submit" value="조회"/>
			</form>
		</td>
	</tr>
</table>	
<c:set var="result" value="${param.result }"/>
<c:choose>
	<c:when test="${result eq 1 }">
		<script type="text/javascript">
			alert('삭제되었음');
			location.href = "/board/boardList.do"
		</script>
	</c:when>
	<c:when test="${result eq 0 }">
		<script type="text/javascript">
			alert('중복삭제');
			location.href = "/board/boardList.do"
		</script>
		<c:remove var="result"/>
	</c:when>
</c:choose>
<c:out value="${result }"/>
</body>
</html>