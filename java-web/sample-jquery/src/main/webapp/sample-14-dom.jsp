<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  	<title>Bootstrap 4 Example</title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  	
  	<style type="text/css">
  		div{
  			border:1px solid black;
  			margin:15px;
  			padding:15px;
  		}
  	</style>
</head>
<body>
<div class="container">
	<h1>엘리먼트 삭제하기</h1>
	
	<div id="box-1">
		<h3>remove</h3>
		<p>컨텐츠</p>
		<p>컨텐츠</p>
		<p>컨텐츠</p>
	</div>
	<div id="box-2">
		<h3>empty</h3>
		<p>컨텐츠</p>
		<p>컨텐츠</p>
		<p>컨텐츠</p>
	</div>
	<div id="box-3">
		<h3>hide</h3>
		<p>컨텐츠</p>
		<p>컨텐츠</p>
		<p>컨텐츠</p>
	</div>	
</div>
<script type="text/javascript">
	$("#box-1").remove(); //엘리먼트 자체를 삭제 
	$("#box-2").empty(); //기존에 있는 내용을 지울 때 (엘리먼트를 비우는 것)
	$("#box-3").hide(); //화면에서 안보이게만 해놓은 것(display:none;)
</script>
</body>
</html>