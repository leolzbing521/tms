
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle" type='button', data-toggle='collapse', data-target='.navbar-collapse'>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">test SCM</a>
		</div>
		<%-- <div class="collapse navbar-collapse">
			<c:if test="${!empty sessionScope.user.menu and !empty menus}">
				<c:forEach items="${menus }" var="menu">
					<li class="dropdown">
						<a class="dropdown-toggle" href="#", data-toggle="dropdown">
							${menu.name}
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
							<c:if test="${menu.children }">
								<c:forEach items="${menu.children }" var="func">
									<li role="presentation">
										<a role='menuitem', tabindex="-1", href='#'>${func.name }</a>
									</li>
								</c:forEach>
							</c:if>
						</ul>
					</li>
				</c:forEach>
			</c:if>
			
		</div> --%>
        <div class="collapse navbar-collapse">
        	<ul class="url nav navbar-nav navbar-left">
        		<li class="dropdown">
					<a class="dropdown-toggle" href="#", data-toggle="dropdown">
						采购
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<li role="presentation"><a role='menuitem', tabindex="-1", href='javascript:pup.template.renderTemplateByKey("purchase.list")'>采购管理</a></li>
						<li role="presentation"><a role='menuitem', tabindex="-1", href='javascript:pup.template.renderTemplateByKey("purchaseItem.list")'>商品管理</a></li>
					</ul>
				</li>
				
				<li class="dropdown">
					<a class="dropdown-toggle" href="#", data-toggle="dropdown">
						销售
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<li role="presentation"><a role='menuitem', tabindex="-1", href='javascript:pup.template.renderTemplateByKey("sale.list")'>销售管理</a></li>
					</ul>
				</li>
				
				<li class="dropdown">
					<a class="dropdown-toggle" href="#", data-toggle="dropdown">
						统计
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<li role="presentation"><a role='menuitem', tabindex="-1", href='javascript:pup.template.renderTemplateByKey("example.pageLoad")'>统计管理</a></li>
					</ul>
				</li>
<!-- 				<li class="dropdown">
					<a class="dropdown-toggle" href="#", data-toggle="dropdown">
						会员
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<li role="presentation"><a role='menuitem', tabindex="-1", href='javascript:pup.template.renderTemplateByKey("example.pageLoad")'>会员管理</a></li>
					</ul>
				</li> -->
				<li class="dropdown">
					<a class="dropdown-toggle" href="#", data-toggle="dropdown">
						授权
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<li role="presentation"><a role='menuitem', tabindex="-1", href='javascript:pup.template.renderTemplateByKey("example.pageLoad")'>用户管理</a></li>
						<li role="presentation"><a role='menuitem', tabindex="-1", href='javascript:pup.template.renderTemplateByKey("example.pageLoad")'>角色管理</a></li>
						<li role="presentation"><a role='menuitem', tabindex="-1", href='javascript:pup.template.renderTemplateByKey("example.pageLoad")'>菜单管理</a></li>
					</ul>
				</li>
        	</ul>
        	<c:if test="${sessionScope.user }">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a class="dropdown-toggle" href="#", data-toggle="dropdown">${sessionScope.user.username}</a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenuUser">
							<li role="presentation">
								<a role='menuitem', tabindex="-1", href='/user/setting'>设置</a>
								<a role='menuitem', tabindex="-1", href='logout'>注销</a>
							</li>
						</ul>
					</li>
				</ul>
			</c:if>
		</div>
	</div>
</div>
