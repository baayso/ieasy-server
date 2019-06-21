$(function () {
    var $menu = $("#menu");
    var $menuSelected = $('.layui-this');

    if (isDefined($menuSelected) && $menuSelected.length > 0) {
        var height = $menu.height();
        var top = $menuSelected.offset().top;

        if (top > height) {
            $menu.scrollTop($menu.scrollTop() + top - $menu.offset().top);
        }

        $menuSelected.parent().parent().addClass("layui-nav-itemed");
    }
});


function isDefined(x) {
    return x !== null && x !== undefined;
}


var request = {};

request.post = function (url, param, callback) {

    if (jQuery.isFunction(param)) {
        callback = param;
        param = undefined;
    }

    $.post(url, param, function (result) {

        if (result && result.redirectUrl) {
            window.location.href = result.redirectUrl;
        }

        callback(result);
    });

};

request.syncPost = function (url, param, callback) {
    $.ajax({
        url: url,
        type: 'POST',
        data: param,
        async: false,
        success: function (result) {

            if (result && result.redirectUrl) {
                window.location.href = result.redirectUrl;
            }

            callback(result);
        },
        error: function (e) {
        }
    });
};

request.get = function (url, param, callback) {

    if (jQuery.isFunction(param)) {
        callback = param;
        param = undefined;
    }

    $.get(url, param, function (result) {

        if (result && result.redirectUrl) {
            window.location.href = result.redirectUrl;
        }

        callback(result);
    });

};


/**
 * 获取url中"?"号后的参数。
 *
 * @returns {{}} 参数对象
 */
function getParameters() {
    var url = window.location.search; // 获取url中"?"号后的字符串

    var params = {};

    if (url.indexOf("?") !== -1) {
        var strs = url.substr(1).split("&");

        for (var i = 0; i < strs.length; i++) {
            var param = strs[i].split("=");

            params[param[0]] = decodeURI(param[1]);
        }
    }

    return params;
}


// easyui datagrid onBeforeLoad
function dataGridOnBeforeLoad(param) {
    param.limit = param.rows;

    return true;
}

// easyui datagrid loadFilter
function dataGridLoadFilter(result) {
    // 可以改变源数据的标准数据格式。
    // 这个函数必须返回包含'total'和'rows'属性的标准数据对象。

    if (!result) {
        return {
            total: 0,
            rows: []
        }
    }

    if (result.redirectUrl) {
        window.location.href = result.redirectUrl;
    }
    else if (result.status === true) {
        if (isDefined(result.data.recordCount)) {
            return {
                total: result.data.recordCount,
                rows: result.data.dataList
            }
        }
        else {
            return result.data;
        }
    }
    else if (result.status === false) {
        layer.msg(result.message);

        result = {
            total: 0,
            rows: []
        };
    }

    return result;
}

// 退出登录
$('.logout').click(function () {
    layer.confirm('确认退出登录吗？', {
        title: '提示'
    }, function () {
        window.location.href = '/user/logout';
    });
});
