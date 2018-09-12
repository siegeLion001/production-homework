$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'appsearchrecordinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'appSearchRecordId', name: 'appSearchRecordId', index: 'app_search_record_id', width: 50, key: true ,hidden:true},
			{ label: '客户端', name: 'client', index: 'client', width: 80 ,
				formatter:function(value,options,rowData){
	                 if( rowData.client == 1 ){
		                    return '教师端';
		                 }
	                 if(rowData.client == 2){
	                	 return '学生端';
	                 }
	                 
	             	}  
			}, 	
			{ label: '搜索内容', name: 'content', index: 'content', width: 80 }, 			
			{ label: '搜索次数', name: 'count', index: 'count', width: 80 }, 			
			{ label: '时间', name: 'createTime', index: 'create_time', width: 80 }			
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
			startTime:null,
            endTime:null,
            client:null
		},
		showList: true,
		title: null,
		appSearchRecordInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.appSearchRecordInfo = {};
		},
		update: function (event) {
			var appSearchRecordId = getSelectedRow();
			if(appSearchRecordId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(appSearchRecordId)
		},
		saveOrUpdate: function (event) {
			var url = vm.appSearchRecordInfo.appSearchRecordId == null ? "appsearchrecordinfo/save" : "appsearchrecordinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.appSearchRecordInfo),
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
			var appSearchRecordIds = getSelectedRows();
			if(appSearchRecordIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "appsearchrecordinfo/delete",
				    contentType: "application/json",
				    data: JSON.stringify(appSearchRecordIds),
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
		getInfo: function(appSearchRecordId){
			$.get(baseURL + "appsearchrecordinfo/info/"+appSearchRecordId, function(r){
                vm.appSearchRecordInfo = r.appSearchRecordInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{
					"startTime":vm.q.startTime,
		            "endTime":vm.q.endTime,
		            "client":vm.q.client
				},
                page:page
            }).trigger("reloadGrid");
		}
	}
});