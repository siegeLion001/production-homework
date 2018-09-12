$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'cmtextcontent/list',
        datatype: "json",
        colModel: [
            {label: '编号', name: 'id', index: 'id', width: 50, key: true},
            // {label: '类型', name: 'typeId', index: 'type_id', width: 80},
            // {label: '出版社', name: 'publisherId', index: 'publisher_id', width: 80},
            // {label: '教材', name: 'bookId', index: 'book_id', width: 80},
            {label: '类型', name: 'type', index: 'type', width: 80},
            {label: '出版社', name: 'publisher', index: 'publisher', width: 80},
            {label: '教材', name: 'book', index: 'book', width: 80},
            {label: '文章名称', name: 'articleName', index: 'article_name', width: 80}
            // ,{label: '文章结果', name: 'articleValue', index: 'article_value', width: 80}
        ],
        viewrecords: true,
        height: 600,
        rowNum: 30,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
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
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        typeOptions: [],
        publisherOptions: [],
        bookOptions: [],
        cmTextContent: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.getTypeOptions();
            vm.cmTextContent = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id);
            vm.getTypeOptions();
            vm.getPublisherOptions(vm.cmTextContent.typeId);
            vm.getBookOptions(vm.cmTextContent.publisherId)
        },
        saveOrUpdate: function (event) {
            var url = vm.cmTextContent.id == null ? "cmtextcontent/save" : "cmtextcontent/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.cmTextContent),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "cmtextcontent/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "cmtextcontent/info/" + id, function (r) {
                vm.cmTextContent = r.cmTextContent;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        },
        getTypeOptions: function () {
            //类型下拉数据获取
            $.post(baseURL + 'cmtype/getOptions', null, function (data) {
                vm.typeOptions = data.options;
            });
        },
        getPublisherOptions: function (typeId) {
            //类型下拉数据获取
            var d = {"typeId": typeId};
            $.post(baseURL + 'cmpublisher/getOptions', d, function (data) {
                vm.publisherOptions = data.options;
            });
        },
        getBookOptions: function (publisherId) {
            //类型下拉数据获取
            var d = {"publisherId": publisherId};
            $.post(baseURL + 'cmbook/getOptions', d, function (data) {
                vm.bookOptions = data.options;
            });
        }
    }
});