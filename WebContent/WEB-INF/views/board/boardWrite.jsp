<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#submit").on("click",function(){
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("작성 완료");
					
					alert("메세지 : " + data.success);
					
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
	});
	
	window.onload = function(){
	      var tableTotal = document.querySelector("#tableTotal");
	      var tableBody = document.querySelector("tbody");
	      var tableFoot = document.querySelector("#tableFoot");
	      var lineAdd = document.getElementById("lineAdd");
	      var lineDelete = document.getElementById("lineDelete");
	      
	      
	      lineAdd.addEventListener("click", createLine);
	      lineDelete.addEventListener("click", deleteLine);
	      
	      function createLine(){
	    	 var tbody = document.createElement("tbody");
	    	 var tr0 = document.createElement("tr");
		     var tr1 = document.createElement("tr");
		     var tr2 = document.createElement("tr");
		     var td1 = document.createElement("td");
		     var td2 = document.createElement("td");
		     var td3 = document.createElement("td");
		     var td4 = document.createElement("td");
		     var td5 = document.createElement("td");
		     var td6 = document.createElement("td");
		     
	         td1.style.width = "120px";
	         td1.style.textAlign = "center";
	         td1.innerHTML = "Title <input type='checkbox' name='checkbox'/>";
	         
	         td2.style.width = "400px";
	         td2.innerHTML = "<input name='boardTitle' type='text' size='50' value='${board.boardTitle}'>";
	         
	         td3.style.height = "300px";
	         td3.style.textAlign = "center";
	         td3.innerHTML = "Comment";
	         
	         td4.style.textAlign = "top";
	         td4.innerHTML = "<textarea name='boardComment' rows='20' cols='55'>${board.boardComment}</textarea>";
	         
	         td5.style.width = "120px";
	         td5.style.textAlign = "center";
	         td5.innerHTML = "Type";
	         
	         td6.style.width = "400px";
	         td6.innerHTML = "<select name='boardType'><option value='a01'>일반</option><option value='a02'>Q&A</option><option value='a03'>익명</option><option value='a04'>자유</option></select>";
	         
	         tr0.append(td5);
	         tr0.append(td6);
	         tr1.append(td1);
	         tr1.append(td2);
	         tr2.append(td3);
	         tr2.append(td4);
	         
	         tbody.append(tr0);
	         tbody.append(tr1);
	         tbody.append(tr2);
	         
	         tableTotal.insertBefore(tbody, tableFoot);
	         
	      }

	      function deleteLine(){
	        $j('input:checkbox[name="checkbox"]:checked').each(function(){
	        	if(this.checked){
	        		//tableTotal.remove("tbody");
	        		var check = $j('input:checkbox[name="checkbox"]');	//체크박스 전체 갯수
	        		var checked = $j('input:checkbox[name="checkbox"]:checked');	//선택된 체크박스 갯수
	        		for(var i=0; i<checked.length; i++){
	        			if(checked.length==check.length){
	        				alert('최소 하나의 작성란을 남겨야 합니다');
							return;
	        			}else{
		        			$j('input:checkbox[name="checkbox"]:checked').eq(i).closest("tbody").remove();
	        			}
	        		}
	        	}
	        });
	      }
	   }

</script>
<body>
<form:form commandName="boardVo" class="boardWrite">
	<table align="center">
		<tr>
			<td align="right">
				<button type="button" id="lineAdd">행 추가</button>
				<button type="button" id="lineDelete">행 삭제</button>
				<input id="submit" type="button" value="작성"/>
			</td>
		</tr>
		<tr>
			<td>
				<table border ="1" id="tableTotal">
					<tbody id="tableBody">
						<tr id="boardTr0">
							<td width="120" align="center" id="td5">
								Type
							</td>
							<td width="400" id="td6">
								<select name="boardList[0].boardType">
									<c:forEach var="board" items="${boardList }" begin="0" end="3">
										<option value="${board.codeId }">${board.codeName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr id="boardTr1">
							<td width="120" align="center" id="td1">
								Title
								<input type="checkbox" name="checkbox"/>
							</td>
							<td width="400" id="td2">
								<input name="boardList[0].boardTitle" type="text" size="50" value="${board.boardTitle}"> 
							</td>
						</tr>
						<tr id="boardTr2">
							<td height="300" align="center" id="td3">
								Comment
							</td>
							<td valign="top" id="td4">
								<textarea name="boardList[0].boardComment" rows="20" cols="55">${board.boardComment}</textarea>
							</td>
						</tr>
					</tbody>
					<tfoot id="tableFoot">
						<tr>
							<td align="center">
							Writer
							</td>
							<td>
							</td>
						</tr>
					</tfoot>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
	</table>
</form:form>	
</body>
</html>