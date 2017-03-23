<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>账号注册</title>

</head>
<body>
	<form id="inputForm" action="<%=request.getContextPath() %>/user/save"
		method="POST" >
		<div>
			<div class="span12">
				<table class="table">
					<tbody>
						<tr>
							<td style="text-align: center; width: 100%; border-top: none;"
								colspan="2">
									账号：<input id="username" name="username" type="text" /><br/>
									密码：<input id="password" name="password" type="password" />
								</td>
							<td style="text-align: center; width: 100%; border-top: none;"
								colspan="2">&nbsp;
									<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="return onSubmit()"
										value="提 交" />&nbsp;
										<input id="btnCancel" class="btn"
								type="button" value="返 回" onclick="history.go(-1)" />
								
								</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</body>

</html>
