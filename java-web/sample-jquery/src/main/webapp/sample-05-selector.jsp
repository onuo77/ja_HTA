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
</head>
<body>
<div class="container">
	<h3>자식 필터 선택자</h3>
	<div>
		<p>p1</p>
		<p>p2</p>
		<p>p3</p>
		<p>p4</p>
	</div>
	<div>
		<p>p5</p>
		<p>p6</p>
		<p>p7</p>
		<p>p8</p>
		<div>
			<p>p12</p>
			<p>p13</p>
			<p>p14</p>
		</div>
	</div>
	<div>
		<p>p9</p>
		<p>p10</p>
		<p>p11</p>
	</div>
</div>
<script type="text/javascript">
	//스타일을 여러개 줄때는 객체로 작성 {name:"value", name:"value"}
	//css("프로퍼티", "값")
	//css({프로퍼티:"값", 프로퍼티:"값", 프로퍼티:"값"})
	//프로퍼티는 변수라 생각하면되고 -는 사용할 수 없어서 -부분은 카멜표기법으로하면됨(backgroundColor:'red')
	$('div').css({border:"1px solid black", margin:"10px", padding:"10px", backgroundColor:'#eee'});
	
	$("p:first").css('color','red');	//p1
	$("p:last").css('color','blue');	//p14
	
	$("p:first-child").css('background-color', 'yellow'); //p1, p5, p12, p9
	$("p:last-child").css('border', '1px solid red'); //p4, p14, p11
	
	$("p:nth-child(2)").css({color:'red', fontWeight:'bold'}); //p2, p6, p13, p10
</script>
</body>
</html>