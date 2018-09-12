$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'appinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'appId', name: 'appId', index: 'app_id', width: 50, key: true ,hidden:true},
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
			{ label: '应用名称', name: 'appName', index: 'app_name', width: 80 }, 			
			{ label: '应用评分', name: 'score', index: 'score', width: 80 } ,	
			{ label: '发布时间', name: 'createTime', index: 'create_time', width: 80 },
			{ label: '上架时间', name: 'upTime', index: 'up_time', width: 80},
			{ label: '下架时间', name: 'downTime', index: 'down_time', width: 80},
			{ label: '版本信息', name: '查看', formatter:function(cellvalue, options, row){
				return "<a href=\"appversioninfo.html?appId="+row.appId+"\">查看</a>" } , width: 80 } ,
			{ label: '评论', name: '查看', formatter:function(cellvalue, options, row){
				return "<a href=\"appcommentinfo.html?appId="+row.appId+"\">查看</a>" } , width: 80 } 
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
    			vm.appInfo.picPath= picPath;
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
	     q:{
            lableName: null,
            lableId:null,
            client:null
	        },
		showList: true,
		title: null,
		lableList: {},
		imgList:[],
		appInfo: {
			appName: null,
			lables: [],
			lableIdList:[],
			picPath:null ,
			introduce:null,
			imgList:[],
			size: 0,
			client:null,
			upTime:null,
			downTime:null
	    },
	    appId:null
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.appInfo= {
				lableIdList:[],
				picPath:null ,
				introduce:null,
				upTime:null,
				downTime:null
		    },
			this.getLableList();
		},
		update: function (event) {
			var appId = getSelectedRow();
			if(appId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(appId);
		},
		saveOrUpdate: function (event) {
		     if(vm.validator()){
	                return ;
	            }
			var url = vm.appInfo.appId == null ? "appinfo/save" : "appinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data:JSON.stringify(vm.appInfo),
			    success: function(r){
			    	if(r.code === 0){
			    		//vm.upload();
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		upload:function(){
			// 用表单来初始
		    var formData = new FormData();
		    var k=0;
		    var newImgList=[];
		    for (i = 0; i < vm.imgList.length; i++) {
		    	var obj = vm.imgList[i];
		    	if(obj.src){
		    		formData.append('file', obj);
		    		k=k+1;
		    	}else{
		    		newImgList.push(obj)
		    	}
		    }
		    console.log(newImgList);
		    if(k>0){
		    	$.ajax({
		    		url: baseURL +'appinfo/uploadImage?2',
		    		data: formData,
		    		type: "Post",
		    		dataType: "json",
		    		cache: false,//上传文件无需缓存
		    		processData: false,//用于对data参数进行序列化处理 这里必须false
		    		contentType: false, //必须
		    		success: function (r) {
		    			if(r.code === 0){
		    				if(r.picPath==0){
		    					vm.appInfo.imgList=newImgList;
		    				}else{
		    					vm.appInfo.imgList=newImgList.concat(r.picPath);
		    					newImgList=[];
		    				}
		    				vm.saveOrUpdate();
		    			}else{
		    				alert(r.msg);  
		    			}
		    		},
		    	})
		    }else{
		    	vm.appInfo.imgList=newImgList;
		    	vm.saveOrUpdate();
		    }
		},
		del: function (event) {
			var appIds = getSelectedRows();
			if(appIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "appinfo/delete",
				    contentType: "application/json",
				    data: JSON.stringify(appIds),
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
		getInfo: function(appId){
			$.get(baseURL + "appinfo/info/"+appId, function(r){
                vm.appInfo = r.appInfo;
                vm.appInfo.upTime=vm.appInfo.upTime.split(" ")[0];
                vm.appInfo.downTime=vm.appInfo.downTime.split(" ")[0];
                console.log(vm.appInfo.upTime,vm.appInfo.downTime);
                vm.q.lableId = r.appInfo.lables;
                var list = r.appInfo.imgList;
                vm.imgList=eval("("+list+")");
                vm.appInfo.imgList=eval("("+list+")");
                $('#image').attr('src',vm.appInfo.picPath);
                vm.getLableList();
            });
		},
		//获取标签列表
        getLableList: function () {
        	$.get(baseURL + "applableinfo/list", function (r) {
        		vm.lableList = r.list;
        		vm.ifArr();
        		
        	});
        },
        ifArr:function(){
            for(var i=0;i<vm.lableList.length;i++){
            	var one = vm.lableList[i];
            	if(vm.q.lableId&&vm.q.lableId.length){
                	for(var t=0;t<vm.q.lableId.length;t++){
                		if(one.lableId==vm.q.lableId[t]){
                			one.ifC = 1;
                		}
                			
                	}
            	}
            }
        },
        //保存标签
        saveLable:function(lableId){
        vm.appInfo.lableIdList = vm.appInfo.lableIdList+","+lableId;
        },
        //删除标签
        deleteLable:function(lableId){
         	for(var t=0;t<vm.appInfo.lableIdList.length;t++){
        		if(lableId==vm.appInfo.lableIdList[t]){
        			vm.appInfo.lableIdList = vm.appInfo.lableIdList.replace(","+lableId,"");
        			break;
        		}
        	}
        },
        fileClick: function() {
            document.getElementById('upload_file').click();
        },
        fileChange:function(el) {
            if (!el.target.files[0].size) return;
            this.fileList(el.target);
            el.target.value = ''
        },
        fileList:function(fileList) {
            let files = fileList.files;
            for (var i = 0; i < files.length; i++) {
                //判断是否为文件夹
                if (files[i].type != '') {
                    this.fileAdd(files[i]);
                } else {
                    //文件夹处理
                    this.folders(fileList.items[i]);
                }
            }
        },
        fileAdd:function(file) {
            //总大小
            this.size = this.size + file.size;
            //判断是否为图片文件
            if (file.type.indexOf('image') == -1) {
            	vm.appInfo.imgList =vm.appInfo.imgList?[].concat(vm.appInfo.imgList):[];
                file.src = 'wenjian.png';
                vm.imgList.push(file);
                vm.appInfo.imgList.push(file.src);
                vm.$forceUpdate();
            } else {
            	vm.appInfo.imgList =vm.appInfo.imgList?[].concat(vm.appInfo.imgList):[];
                let reader = new FileReader();
                reader.vue = this;
                reader.readAsDataURL(file);
                reader.onload = function () {
                    file.src = this.result;
                    vm.imgList.push(file);
                    vm.appInfo.imgList.push(file.src);
                    vm.$forceUpdate();
                }
            }
        },
        fileDel: function(index) {
        	vm.imgList.splice(index, 1);
        	vm.appInfo.imgList.splice(index, 1);
        },
        bytesToSize:function(bytes) {
            if (bytes === 0) return '0 B';
            let k = 1000, // or 1024
                sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
                i = Math.floor(Math.log(bytes) / Math.log(k));
            return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
        },
        dragenter:function(el) {
            el.stopPropagation();
            el.preventDefault();
        },
        dragover:function(el) {
            el.stopPropagation();
            el.preventDefault();
        },
        drop:function(el) {
            el.stopPropagation();
            el.preventDefault();
            this.fileList(el.dataTransfer);
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
				  "client":vm.q.client,
				},
                page:page
            }).trigger("reloadGrid");
		},
        validator: function () {
            if(isBlank(vm.appInfo.client)){
                alert("客户端不能为空");
                return true;
            }
            if(isBlank(vm.appInfo.appName)){
            	alert("应用名称不能为空");
            	return true;
            }
            if(isBlank(vm.appInfo.introduce)){
            	alert("应用简介不能为空");
            	return true;
            }
			if(isBlank(vm.appInfo.lableIdList)){
				alert("应用标签不能为空");
				return true;
			}
			if(isBlank(vm.appInfo.picPath)){
				alert("应用图标不能为空");
				return true;
			}
			if(isBlank(vm.imgList)){
				alert("展示图不能为空");
				return true;
			}
        }
	}
});
