$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'banana/list',
        datatype: "json",
        colModel: [			
			{ label: '编号', name: 'id', index: 'id', width: 50, key: true,hidden: true },
			{ label: '图片路径', name: 'url', index: 'url', width: 80 },
			{ label: '状态', name: 'state', index: 'state', width: 80,formatter:'select', editType:'select',editoptions:{value:"1:展示;2:不展示"} },
			{ label: '图片标题', name: 'title', index: 'title', width: 80 }, 			
			{ label: '连接地址', name: '连接地址', index: 'title', width: 80 },
			{ label: '客户端', name: 'client', index: 'client', width: 80,formatter:'select', editType:'select',editoptions:{value:"1:教师端;2:学生端;3:应用中心"} },
			{ label: '创建日期', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '内容', name: 'cnontent', index: 'content', width: 80 ,hidden: true},
			{ label: '修改日期', name: 'updateTime', index: 'update_time', width: 80 }
        ],
		viewrecords: true,
        height: 600,
        rowNum: 30,
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
        },
        beforeRequest: function() {
            var re_page = $(this).getGridParam('page'); //获取返回的当前页
            var re_total= $(this).getGridParam('lastpage'); //获取总页数
            if( re_total != 0 && re_page > re_total){
                layer.msg("输入的页码不能大于总页数，默认调整最后一页！", { time: 2000, icon: 2 });
                $(this).setGridParam({page:re_total}).trigger("reloadGrid"); //设置页码为1 并重新加载
            }
        }
    });

});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		banana: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.banana = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {

            if ( ue.hasContents()){
                var content = ue.getContent();

                vm.banana.content = content;
            }
            var boole = vm.checkoutData();
            if(boole){


			var url = vm.banana.id == null ? "banana/save" : "banana/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.banana),
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
            ue.execCommand('cleardoc');
            }
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "banana/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
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
		getInfo: function(id){
			$.get(baseURL + "banana/info/"+id, function(r){
                vm.banana = r.banana;
                ue.setContent(vm.banana.content);
            });
		},

		reload: function (event) {
			vm.showList = true;
            ue.execCommand('cleardoc');
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},

        checkoutData: function (){
            var ba = vm.banana
			var url = ba.url;

			if(url==null || url == '' ||undefined == url){
                alert("图片不能为空!");
				return false;
			}
            var state = ba.state;
			if(state == null || state =='' ||undefined == state){
				alert("状态不能为空");
                return false;
            }
            var title = ba.title;
			if(title==null ||title=='' ||undefined == title){
				alert("图片标题不能为空");
                return false;
			}
			var client = ba.client;
            if(client==null ||client=='' ||undefined == client){
                alert("客户端不能为空");
                return false;
            }
            return true;
		}
	}
});

layui.use('upload', function(){
    var $ = layui.jquery;
    var upload = layui.upload;
    layui.upload({

        url: baseURL +'homework/uploadFile'
        ,success: function(res){
            console.log(res); //上传成功返回值，必须为json格式
            var m = res.data[0];
            vm.banana.url = m;
            $("#uploadBanana").val(m);
            $("#pic").attr('src',m);
        }
    });

});



