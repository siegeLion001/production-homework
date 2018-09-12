$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'applableinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'lableId', name: 'lableId', index: 'lable_id', width: 50, key: true,hidden:true },
			{ label: '标签名', name: 'lableName', index: 'lable_name', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }		
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
        gridComplete:function(data){
        	console.log(data,this)
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    //图标上传
    new AjaxUpload('#chooseImage',{
    	action: baseURL + "appinfo/uploadImage?1",
    	name: 'file',
    	autoSubmit:true,
    	responseType:"json",
    	onSubmit:function(file, extension){
    		if (!(extension && /^(png|jpg|jpeg)$/.test(extension.toLowerCase()))){
    			alert('只支持.png|.jpg|.jpegk格式');
    			return false;
    		}
    	},
    	onComplete : function(file, r){
    		if(r.code == 0){
    			alert("操作成功");
    			var picPath = r.picPath;
                $("#cropedBigImg").val(picPath);
    			vm.appLableInfo.iconPath= picPath;
    			$('#image').attr('src',picPath);
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
		appLableInfo: {
			iconPath:null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.appLableInfo = {};
		},
		update: function (event) {
			var lableId = getSelectedRow();
			if(lableId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(lableId)
		},
		saveOrUpdate: function (event) {
			var url = vm.appLableInfo.lableId == null ? "applableinfo/save" : "applableinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.appLableInfo),
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
			var lableIds = getSelectedRows();
			if(lableIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "applableinfo/delete",
				    contentType: "application/json",
				    data: JSON.stringify(lableIds),
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
		getInfo: function(lableId){
			$.get(baseURL + "applableinfo/info/"+lableId, function(r){
                vm.appLableInfo = r.appLableInfo;
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