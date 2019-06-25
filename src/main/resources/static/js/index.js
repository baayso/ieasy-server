function isDefined(x) {
    return x !== null && x !== undefined;
}

var element;
var $;

layui.use(['element', 'jquery'], function () {

    var element = layui.element;
    var $ = layui.jquery;

    // 监听左侧菜单点击
    element.on('nav(admin-system-side-menu)', function (elem) {
        var url = elem.attr('lay-href');

        if (isDefined(url) && url !== "") {
            var title = $.trim(elem.text());

            openTab(title, url, url);
        }
    });

    // 监听tab选项卡切换
    element.on('tab(admin-layout-tabs)', function (data) {
        if (!isDefined(data.elem.context.attributes)) {
            return;
        }

        var layId = data.elem.context.attributes['lay-id'].nodeValue;
        var menuUrl = $('.layui-side-menu .layui-this > a').attr('lay-href');

        if (layId === menuUrl) {
            return;
        }

        layui.each($('.layui-side-menu .layui-this'), function () {
            $(this).removeClass('layui-this');
        });

        var aTag = $("#system-side-menu a[lay-href='" + layId + "']");

        if (aTag.parents('.layui-nav-itemed').length === 0) {

            layui.each($('.layui-side-menu .layui-nav-itemed'), function () {
                $(this).removeClass('layui-nav-itemed');
            });

            aTag.parents('.layui-nav-item').addClass('layui-nav-itemed');
        }

        aTag.parent().addClass('layui-this');
    });

    /**
     * 新增tab选项卡，如果已经存在则打开，不存在则新增。
     * @param tabTitle 选项卡标题名称
     * @param tabUrl 选项卡链接的页面URL
     * @param tabId 选项卡id
     */
    function openTab(tabTitle, tabUrl, tabId) {
        var has = $("#system_tabs_header > li[lay-id='" + tabId + "']").length > 0;

        if (!has) {
            element.tabAdd('admin-layout-tabs', {
                title: '<span>' + tabTitle + '</span>'
                , id: tabId // 选项卡标题的lay-id属性值
                , content: [
                    '<div class="admin-tabs-body-item layui-show">'
                    , '<iframe src="' + tabUrl + '" frameborder="0" class="admin-iframe"></iframe>'
                    , '</div>'
                ].join('')
            });
        }

        element.tabChange('admin-layout-tabs', tabId);
    }

});
