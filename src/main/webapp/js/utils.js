
(function($) {
    toastr.options.extendedTimeOut = 10000000000;
    toastr.options.closeButton = true;
    toastr.options.positionClass = 'toast-top-center';
})(jQuery);


function toast(msg) {
    if(msg.type == 'success') {
        toastr.success(msg.body);
    } else if(msg.type == 'info') {
        toastr.info(msg.body);
    } else if(msg.type == 'warning') {
        toastr.warning(msg.body);
    } else if(msg.type == 'error') {
        toastr.error(msg.body);
    } else {
        toastr.info(msg.body);
    }
}

Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function format(dateStr, pattern) {
    if(pattern == undefined) {
        pattern = 'yyyy-MM-dd';
    }
    return new Date(dateStr).format(pattern);
}

function tags(tags) {
    var tagBuf = "";
    for(var i = 0; i < tags.length; i++) {
        if(i == 0) {
            tagBuf += tags[i];
        } else {
            tagBuf += "  " + tags[i];
        }
    }
    return tagBuf;
}

function posNum(value) {
    var pattern = /^\+?[1-9][0-9]*$/;
    if(!pattern.test(value)) return false;

    return true;

}

function ajaxPost(_url, _method, _para) {
    $.ajax({
        type: "post",
        url: _url,
        data: _para,
        timeout: 120000,
        dataType: "json",
        success: function (data){
            _method.call(this,data);
        },
        error: ajaxError
    });
}

function ajaxError(){
    toast({type: 'error', body: '操作出错'});
}

function ajaxPage(page) {
    $("#Pagination").pagination(page.totalNum, {
        callback: pageselectCallback,
        items_per_page : page.items_per_page,
        num_display_entries : page.num_display_entries,
        num_edge_entries : page.num_edge_entries,
        current_page : page.current_page,
        prev_text : 'Prev',
        next_text : 'Next'
    });
}

(function($){
    $.fn.serializeObject = function(){

        var self = this,
            json = {},
            push_counters = {},
            patterns = {
                "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
                "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
                "push":     /^$/,
                "fixed":    /^\d+$/,
                "named":    /^[a-zA-Z0-9_]+$/
            };


        this.build = function(base, key, value){
            base[key] = value;
            return base;
        };

        this.push_counter = function(key){
            if(push_counters[key] === undefined){
                push_counters[key] = 0;
            }
            return push_counters[key]++;
        };

        $.each($(this).serializeArray(), function(){

            // skip invalid keys
            if(!patterns.validate.test(this.name)){
                return;
            }

            var k,
                keys = this.name.match(patterns.key),
                merge = this.value,
                reverse_key = this.name;

            while((k = keys.pop()) !== undefined){

                // adjust reverse_key
                reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

                // push
                if(k.match(patterns.push)){
                    merge = self.build([], self.push_counter(reverse_key), merge);
                }

                // fixed
                else if(k.match(patterns.fixed)){
                    merge = self.build([], k, merge);
                }

                // named
                else if(k.match(patterns.named)){
                    merge = self.build({}, k, merge);
                }
            }

            json = $.extend(true, json, merge);
        });

        return json;
    };
})(jQuery);
