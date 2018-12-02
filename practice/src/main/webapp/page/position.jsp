<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8;no-cache">
<title>Insert title here</title>
</head>
<style>
    div{border: 1px solid red;}
</style>

<div style="position: absolute;width: 100px;height: 100px;background-color: #000066"></div>
<div style="border: 1px solid red;width: 200px;height: 200px;background-color: black;">
</div>
<div style="position:absolute;width:300px;height: 300px;background-color: grey;">
    <div style="position: absolute;width:100px;height: 200px;background-color: green"></div>
</div>
<div style="width:300px;height:300px;position: relative;background-color: yellow;z-index:-1"></div>
