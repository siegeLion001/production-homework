$(function () {
	//获取url上参数
	function GetQueryString(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r!=null)return unescape(r[2]); return null;
		} 	
	var appId=GetQueryString("appId");
	$.ajax({
		type: "POST",
	    url: baseURL + "appcommentinfo/list?appId="+appId,
	    contentType: "application/json",
	    success: function(r){
	    	if(r.code === 0){
	    		vm.commentList = r.list;
			}else{
				alert(r.msg);
			}
		}
	});
});
var vm = new Vue({
	el:'#rrapp',
	data:{
	     q:{
            lableName: null,
            lableId:null,
	        },
		showList: true,
		title: null,
		commentList:[],
		appCommentInfo: {
			commentId:null,
			parentId:null,
			content:null,
			appId:null,
			appVersionId:null
        	}, 
	    appId:null,
	    index:null
	},
	methods: {
		reply:function(index,commentId,appVersionId) {
			var reply = document.getElementById("index"+index).value;
			  vm.appCommentInfo.parentId=commentId;
			  vm.appCommentInfo.content=reply;
			  vm.appCommentInfo.appId=appId;
			  vm.appCommentInfo.appVersionId=appVersionId;
			  vm.index = index;
			  vm.saveOrUpdate();
			},
		display:function(index){
			var input = document.getElementById("input"+index);
			var index = document.getElementById("index"+index);
            if(index.style.display == 'block'|| index.style.display == '') {
            	input.style.display = 'none';
                index.style.display = 'none';
            } else {
            	input.style.display = 'block';
                index.style.display = 'block';
            }
		},
		saveOrUpdate: function (event) {
		     if(vm.validator()){
	                return ;
	            }
			var url = vm.appCommentInfo.commentId == null ? "appcommentinfo/save" : "appcommentinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data:JSON.stringify(vm.appCommentInfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('回复成功', function(r){
							var index = document.getElementById("index"+vm.index);
							var input = document.getElementById("input"+vm.index);
							vm.reload();
							index.style.display = 'none';
							input.style.display = 'none';
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		validator:function(){
		     if(isBlank(vm.appCommentInfo.content)){
	                alert("回复不能为空");
	                return true;
	            }
		}, 
		reload:function(){
		}
	}
});
