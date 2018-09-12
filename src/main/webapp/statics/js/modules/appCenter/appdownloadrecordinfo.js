$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'appdownloadrecordinfo/list',
        datatype: "json",
        colModel: [			
                   
			{ label: 'recordId', name: 'record_id', index: 'record_id', width: 50, key: true ,hidden:true},
			{ label: '用户名', name: 'user_name', index: 'user_name', width: 80 }, 			
			{ label: '应用名', name: 'app_name', index: 'app_version_id', width: 80 }, 			
			{ label: '应用版本', name: 'app_version', index: 'app_version_id', width: 80 }, 			
			{ label: '时间', name: 'create_time', index: 'create_time', width: 80 }		
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			appName:null,
			startTime:null,
			endTime:null,
			client:null,
			type:null
		},
		showList: true,
		title: null,
		appDownloadRecordInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.appDownloadRecordInfo = {};
		},
		update: function (event) {
			var recordId = getSelectedRow();
			if(recordId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(recordId)
		},
		saveOrUpdate: function (event) {
			var url = vm.appDownloadRecordInfo.recordId == null ? "appdownloadrecordinfo/save" : "appdownloadrecordinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.appDownloadRecordInfo),
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
			var recordIds = getSelectedRows();
			if(recordIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "appdownloadrecordinfo/delete",
				    contentType: "application/json",
				    data: JSON.stringify(recordIds),
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
		getInfo: function(recordId){
			$.get(baseURL + "appdownloadrecordinfo/info/"+recordId, function(r){
                vm.appDownloadRecordInfo = r.appDownloadRecordInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
		        postData: {
		        	'appName': vm.q.appName,
		    		'startTime':vm.q.startTime,
					'endTime':vm.q.endTime,
					'client':vm.q.client,
					'type':vm.q.type
		        	},
                page:page
            }).trigger("reloadGrid");
		}
	}
});