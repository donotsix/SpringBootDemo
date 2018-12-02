<html>
<style>
body {
	margin: 0;
	top: 0;
}

div {
	border: 1px solid;
}

.div1 {
	left: 10%;
	width: 300px;
	height: 300px;
	position: absolute;
	border: 1px solid red;
}

.div2 {
	width: 300px;
	height: 300px;
	float: left;
}

.div3 {
	width: 100%;
	height: 50%;
	margin-left: 50px;
}

.div4 {
	width: 80%;
	height: 20%;
}

.div5 {
	width: 300px;
	height: 300px;
	margin-left: 300px;
}

.div6 {
	width: 300px;
	height: 300px;
	left: 30px;
    top: 20px;
	position: relative;
}
.div7 {
	width: 298px;
	height: 100px;
}
</style>
<body>
	<div class='div1'>
		div1
		<div class="div3">div3</div>
		<div class="div4">div4</div>
	</div>
	<div class='div2'>div2</div>
	<div class='div5'>
		div5
		<div class='div7'></div>
	</div>
	<div class='div6'>div6</div>
</body>
</html>
