$(function () {
	//获取url上参数
	function GetQueryString(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r!=null)return unescape(r[2]); return null;
		} 	
	var appId=GetQueryString("appId");
    $("#jqGrid").jqGrid({
        url: baseURL + 'appversioninfo/list?appId='+appId,
        datatype: "json",
        colModel: [			
			{ label: 'appVersionId', name: 'appVersionId', index: 'app_version_id', width: 50, key: true , hidden:true},
			{ label: '应用版本', name: 'appVersion', index: 'app_version', width: 80 }, 			
			{ label: '应用大小', name: 'appSize', index: 'app_size', width: 80 }, 			
			{ label: '应用路径', name: 'appPath', index: 'app_path', width: 80 }, 			
			{ label: '更新介绍', name: 'introduce', index: 'introduce', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    //上传安装包
    new AjaxUpload('#uploadApk',{
        action: baseURL + "appinfo/uploadFile?appId="+appId,
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        appId:vm.appId,
        onSubmit:function(file, extension,appId){
            if (!(extension && /^(apk)$/.test(extension.toLowerCase()))){
                alert('只支持.apk格式');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
            	vm.appVersionInfo.appPath=r.apkPath;
            	vm.appVersionInfo.appVersion=r.version;
            	vm.appVersionInfo.appSize=r.size;
            	vm.appVersionInfo.packageName=r.packageName;
            	vm.appVersionInfo.appId=r.appId;
        		var apkPath = r.apkPath;
                $("#apkPath").val(apkPath);
                alert(r.msg);
            }else{
            	alert(r.msg);
            }
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		appVersionInfo: {
	        appPath:null,
			appVersion:null,
			appSize:null,
			packageName:null,
			appId:null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.appVersionInfo = {};
		},
		update: function (event) {
			var appVersionId = getSelectedRow();
			if(appVersionId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(appVersionId)
		},
		saveOrUpdate: function (event) {
			var url = vm.appVersionInfo.appVersionId == null ? "appversioninfo/save" : "appversioninfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.appVersionInfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var appVersionIds = getSelectedRows();
			if(appVersionIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "appversioninfo/delete",
				    contentType: "application/json",
				    data: JSON.stringify(appVersionIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(appVersionId){
			$.get(baseURL + "appversioninfo/info/"+appVersionId, function(r){
                vm.appVersionInfo = r.appVersionInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});