/**
 * Created by michael on 14-4-24.
 */

var pup = function ($) {

    if (!$) throw Error('pup.js required jquery core');

    //template
    var contextPath = "/tms/web";
    var templateCfg = {};
    var templates = {};
    var defaultTemplateUrl = '/template';
    var defaultRequestUrl = '/template';
    var requestUrlPrefix = '/template';
    var defaultTemplateConfigUrl = '/templateCfg/config';
    var isSetup = false;

    //pagination
    var defaultEdgePageCount = 11;

    return {
        template: {
            renderTemplateByKey : function(key, params, optional) {
                if(!key || !templateCfg[key]) {
                    throw new Error('template is not defined in template.cfg.js. key:' + key);
                }
                var options = templateCfg[key];
                $.extend(true, options, optional);
                if (params && $.isPlainObject(params)) {
                    options.dataParams = params;
                }
                options.key = key;
                this.renderTemplate(options);
            },

            renderTemplate : function (options) {
                if(!options) options = {};
                if($.type(options) == 'string') {
                    options = {
                        requestUrl: options
                    }
                }

                var targetSelector = options.targetSelector || '#main';

                var templateUrl = options.templateUrl || defaultTemplateUrl;
                var templateName = options.templateName || options.requestUrl || null;
                //development to false, production to true
                var templateCache = true;
                if (options.templateCache == false) templateCache = false;

                var data = true;
                if (options.data == false) data = false;
                var dataCache = true;
                if (options.dataCache == false) dataCache = false;
                var requestUrl = options.requestUrl || defaultRequestUrl;
                var templateRequestType = options.templateRequestType || 'GET';
                var dataRequestType = options.dataRequestType || 'GET';
                var dataUrl = options.dataUrl || options.requestUrl || null;
                var dataParams = options.dataParams || {};

                var beforeRender = options.beforeRender;
                var afterRender = options.afterRender;

                //var executableTemplate = eval(templateName.replace('/', '').replace(/\//g, '_'));
                var $target = null;
                if($.type(targetSelector) == 'string') {
                    $target = $(targetSelector);
                } else {
                    $target = targetSelector;
                }
                if(requestUrl) {
                	$.ajax({
                		url: contextPath + requestUrlPrefix + requestUrl,
                        type: templateRequestType,
                        cache: templateCache,
                    }).done(function(htmlData) {
                    	if(data && dataUrl) {
                            $.ajax({
                                type: dataRequestType,
                                dataType: "json",
                                cache: dataCache,
                                url: contextPath + dataUrl,
                                data : dataParams,
                            }).done(function(json) {
                                if(json.err) {
                                    if(json.err.code == 'unlogin')
                                    	$(targetSelector).html('<p class="text-primary">您当前未登录，请<a href="/login"><strong>登录</strong></a></p>');
                                    else {
                                        console.error(json.err);
                                        $(targetSelector).html('<p class="text-danger">'+ json.err + '</p>');
                                    }
                                } else {
                                    if(data) {
                                        if(beforeRender && typeof beforeRender == 'function') {
                                            beforeRender(json);
                                        }
                                        
                                        $(targetSelector).html(baidu.template(htmlData, json));
                                        if(afterRender && typeof afterRender == 'function') {
                                            afterRender(json);
                                        }
                                    }
                                }
                                templates[options.key] = null;
                            });
                        } else {
                            if(beforeRender && typeof beforeRender == 'function') {
                                beforeRender();
                            }
                            $(targetSelector).html(htmlData);
                            if(afterRender && typeof afterRender == 'function') {
                                afterRender();
                            }
                        }
                    });
                }
            },

            setup: function (options) {
                if (!isSetup) {
                    if (options) {
                        if (options.templateUrl) {
                            defaultTemplateUrl = options.templateUrl;
                        }
                        if (options.templateConfigUrl) {
                            defaultTemplateConfigUrl = options.templateConfigUrl;
                        }
                    }
                    this.reLoad();
                    isSetup = true;
                }
            },

            reLoad: function () {
                $.ajax({
                    dataType: "json",
                    url: contextPath + defaultTemplateConfigUrl,
                    async: false,
                    success: function (json) {
                        templateCfg = json;
                    }
                });
            }
        },

        /**
         *
         * @param options
         * {
         *      templateKey : 'pup.template.key'
         *      listBarSelector : '#listBarSelector'
         * }
         */
        Pagination: function (options) {
            //required init options
            this.templateKey = options.templateKey || null;

            //not required init options
            this.paginationParamName = options.paginationParamName || 'pagination';

            //other params
            this.conditions = {};
            this.where = '';
            this.sorts = {};
            this.total = 0;
            this.pageCount = 10;
            this.currentPage = 1;

            this.clear = function() {
                this.conditions = {};
                this.where = '';
                this.sorts = {};
            };

            this.totalPage = function() {
                if(this.total <= 0)
                    return 0;
                else
                    return Math.ceil(this.total / this.pageCount);
            }

            /**
             * 添加查询条件
             * @param String key
             * @param String|regex condition
             * @returns {pup}
             */
            this.condition = function (key, condition) {
                this.conditions[key] = condition;
                return this;
            };

            /**
             * 设置查询条件
             * @param {} conditions
             * @returns {pup}
             */
            this.setCondition = function (conditions) {
                this.conditions = conditions;
                return this;
            }

            /**
             * 添加Where查询条件
             * @param String where
             */
            this.setWhere = function (where) {
                this.where = where;
                return this;
            }

            /**
             * 添加排序条件
             * @param String key
             * @param asc|desc|1|-1 sortType
             */
            this.sort = function (key, sortType) {
                this.sorts[key] = sortType;
                return this;
            }

            /**
             * 设置排序条件
             * @param {} orders
             */
            this.setSort = function (sorts) {
                this.sorts = sorts;
                return this;
            }

            /**
             * 跳转到指定页∂
             * @param {Integer} pageNum
             */
            this.to = function (pageNum) {
                var paginationParamName = this.paginationParamName;
                var thiz = this;
                pup.template.renderTemplateByKey(this.templateKey, {
                    'condition': this.conditions,
                    'where': this.where,
                    'sort': this.sorts,
                    'currentPage': pageNum,
                    //'pageCount' : 10
                },
                {
                    beforeRender : function (data) {
                        var pageData = data[paginationParamName];
                        thiz.total = pageData.total;
                        thiz.pageCount = pageData.pageCount;
                        thiz.currentPage = pageData.currentPage;
                    }
                });
            };

            /**
             * 刷新当前页面数据
             */
            this.reload = function () {
                this.to(parseInt(this.currentPage));
            };

            /**
             * 上一页
             */
            this.previous = function () {
                if (this.currentPage <= 1)
                    return;
                else
                    this.to(parseInt(this.currentPage) - 1);
            };

            /**
             * 下一页
             */
            this.next = function () {
                if (this.currentPage >= this.totalPage())
                    return;
                else
                    this.to(parseInt(this.currentPage) + 1);
            };

            /**
             * 跳转到首页
             */
            this.first = function () {
                this.to(1);
            }

            /**
             * 跳转到末页
             */
            this.last = function () {
                if (this.totalPage() == 0) {
                    return;
                } else {
                    this.to(this.totalPage());
                }
            }

            /**
             * 渲染分页工具条
             * @param String pageBarSelector
             * @param Pagination pagination
             */
            this.bar = function (pageBarSelector) {
                var thiz = this;
                var beginEdge = 1;
                var centerNum = Math.ceil(defaultEdgePageCount / 2);
                var pageBar = '<ul class="pagination">';
                if (this.currentPage <= 1) {
                    pageBar += '<li class="disabled" data-type="pre"><a href="#" style="margin:0px;">&laquo;</a></li>';
                } else {
                    pageBar += '<li data-type="pre"><a href="#" style="margin:0px;">&laquo;</a></li>';
                }
                if (this.currentPage <= centerNum) {
                    beginEdge = 1;
                } else {
                    beginEdge = this.currentPage - centerNum + 1;
                }
                for (var i = 1; i <= defaultEdgePageCount; i++) {
                    var pageNum = beginEdge++;
                    if (this.currentPage == pageNum) {
                        pageBar += '<li class="active" data-type="to" data-page="'
                            + pageNum + '"><a href="#" style="margin:0px;">' + pageNum + '</a></li>';
                    } else if (pageNum > this.totalPage()) {
                        break;
                    } else {
                        pageBar += '<li data-type="to" data-page="'
                            + pageNum + '"><a href="#" style="margin:0px;">' + pageNum + '</a></li>';
                    }
                }
                if (this.currentPage >= this.totalPage()) {
                    pageBar += '<li class="disabled" data-type="next"><a href="#" style="margin:0px;">&raquo;</a></li>';
                } else {
                    pageBar += '<li data-type="next"><a href="#" style="margin:0px;">&raquo;</a></li>';
                }
                pageBar += '<li class="disabled"><a href="#" style="margin:0px;">共' + this.total + '条' + this.totalPage() + '页</a></li></ul>';
                $(pageBarSelector).html(pageBar);
                $(pageBarSelector + ' ul li:not(.disabled)').each(function (index, target) {
                    var jqElement = $(target);
                    var type = jqElement.attr('data-type');
                    if (type == 'pre') {
                        jqElement.on('click', function () {
                            thiz.previous();
                        });
                    } else if (type == 'next') {
                        jqElement.on('click', function () {
                            thiz.next();
                        });
                    } else if (type == 'to') {
                        jqElement.on('click', function () {
                            thiz.to(jqElement.attr('data-page'));
                        });
                    }
                });
            };
        },

        widgets: {

            labelRadio : function (selector) {

                this.container = $(selector);

                if (!this.container) {
                    console.error('There is no element like $(' + selector + ').');
                }

                this.radio = function (afterSelect) {

                    this.container.find('input[type="radio"]').each(function () {

                        $this = $(this);
                        $this.hide();

                        if ($this.is(':checked')) {
                            $this.wrap('<label class="btn btn-danger"></label>');
                        } else {
                            $this.wrap('<label class="btn btn-link"></label>');
                        }
                        $this.after($this.data('name'));
                    });

                    this.container.on('click', 'input[type="radio"]', null, function (e) {

                        $currentTarget = $(e.currentTarget);
                        $delegateTarget = $(e.delegateTarget);

                        if($currentTarget.closest('label').hasClass('btn-danger')) {

                            $currentTarget.closest('label')
                                .removeClass('btn-danger')
                                .addClass('btn-link')
                                .find('input[type="radio"]')
                                .prop('checked', false);

                        } else {

                            $delegateTarget
                                .find('label')
                                .removeClass('btn-xs btn-danger')
                                .addClass('btn-link btn-link-xs')
                                .find('input[type="radio"]:checked')
                                .prop('checked', false);

                            $currentTarget.closest('label')
                                .removeClass('btn-link ')
                                .addClass('btn-danger')
                                .find('input[type="radio"]')
                                .prop('checked', true);

                        }

                        if ($.isFunction(afterSelect)) {
                            afterSelect();
                        }
                    });

                    return this;
                };

                this.val = function () {
                    return this.container.find('input[type="radio"]:checked').val();
                }
            },
            
            layerManager : {
                layerMap : {},
                layerSuffix : 0,
                /**
                 * 打开一个对话层
                 * @param optional
                 * {
                 *     //default is something random, not required
                 *     layerKey : 'key for the open layer',
                 *     //default is body, not required
                 *     targetSelector : 'jquery selector for where the layer cover on',
                 *     //if set template attribute, html will be ignored, not required
                 *     html : 'html set to this layer',
                 *     template : {
                 *         //required
                 *         key : 'key for pup.template',
                 *         //not required
                 *         dataParam : {request param json obj},
                 *         //not required
                 *         beforeRender : fn{callback function before render template},
                 *         //not required
                 *         afterRender : fn{callback function after render template}
                 *     },
                 *     closeButton : {
                 *         visible : true,
                 *         topOffset : 10px,
                 *         rightOffset : 10px,
                 *         zIndex : 10
                 *     }
                 * }
                 */
                open : function(optional) {
                    console.debug('layerManager.open : ');
                    console.dir(optional);
                    var layerKey = null;
                    var targetSelector = null;
                    var html = null;
                    var templateKey = null;
                    var templateParam = null;
                    var templateBefore = null;
                    var templateAfter = null;
                    var btnCloseVisible = true;
                    var btnCloseTopOffset = null;
                    var btnCloseRightOffset = null;
                    var btnCloseZIndex = null;
                    if(optional) {
                        layerKey = optional.layerKey || ('layer_' + this.layerSuffix++);
                        targetSelector = optional.targetSelector || 'body';
                        html = optional.html || '';
                        if(optional.template) {
                            templateKey = optional.template.key;
                            templateParam = optional.template.dataParam;
                            templateBefore = optional.template.beforeRender;
                            templateAfter = optional.template.afterRender;
                        }
                        if(optional.closeButton) {
                            if(optional.closeButton.visible == false)
                                btnCloseVisible = false;
                            btnCloseTopOffset = optional.closeButton.topOffset;
                            btnCloseRightOffset = optional.closeButton.rightOffset;
                            btnCloseZIndex = optional.closeButton.zIndex;
                        }
                    }
                    
                    //remove modal-open class and model-backdrop attribute in order to show the dialog normally
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('.modal-scrollable').remove();
                    
                    var $target = $(targetSelector);
                    if(!$target) {
                        throw new Error('Can\'t find layer target element like : ' + targetSelector);
                    }

                    var layers = $target.data('layers');
                    if(!layers) {
                        layers = [];
                    }
                    if(layers.length == 0) {
                        $target.wrapInner('<div style="display:none;"></div>');
                    } else {
                        this.layerMap[layers[layers.length-1]].layer.hide();
                    }
                    layers.push(layerKey);
                    $target.data('layers', layers);

                    var $layer = $('<div style="overflow: auto;"></div>');
                    $layer.attr('data-pup-widgets-layer-key', layerKey);
                    $layer.css('display', 'none');
                    $layer.addClass('row');
                    if(btnCloseVisible) {
                        var $layerClose = $(document.createElement('div'));
                        $layerClose.addClass('layer-close');
                        if(btnCloseRightOffset)
                            $layerClose.css('right', btnCloseRightOffset);
                        if(btnCloseTopOffset)
                            $layerClose.css('top', btnCloseTopOffset);
                        if(btnCloseZIndex)
                            $layerClose.css('z-index', btnCloseZIndex);
                            console.log('z-index : ' + btnCloseZIndex);
                        $layerClose.append('<div style="margin-right: 30px;" class="pull-right m-t-10 m-b-15"></div>');
                        var $layerCloseButton = $('<a href="#" title="关闭" class="btn btn-info btn-circle"><i style="font-size: 24px;" class="icon-cancel-7"></i></a>');
                        $layerCloseButton.click(function(e) {
                            pup.widgets.layerManager.close({layerKey : layerKey, targetSelector : targetSelector});
                        });
                        $layerClose.find('>div').append($layerCloseButton);
                        $layer.append($layerClose);
                    }
                    var $layerContent = $('<div style="overflow: auto;" class="col-sm-12"></div>');
                    $layerContent.append('<div data-pup-widgets-layer-key="content"></div>');
                    $layer.append($layerContent);
                    this.layerMap[layerKey] = {};
                    this.layerMap[layerKey].layer = $layer;
                    $target.append($layer);

                    if(!templateKey) {
                        $layerContent.find('div[data-pup-widgets-layer-key="content"]').append(html);
                        $layer.show();
                    } else {
                        pup.template.renderTemplateByKey(templateKey, templateParam, {
                            targetSelector : $layerContent.find('div[data-pup-widgets-layer-key="content"]'),
                            beforeRender : templateBefore,
                            afterRender : function() {
                                if($.isFunction(templateAfter))
                                    templateAfter();
                                $layer.show();
                            }
                        });
                    }
                },
                close : function(optional) {
                    console.debug('layerManager.open : ');
                    console.dir(optional);
                    var layerKey = null;
                    var targetSelector = null;
                    if(optional) {
                        layerKey = optional.layerKey;
                        targetSelector = optional.targetSelector;
                    }
                    if(!targetSelector) {
                        targetSelector = 'body';
                    }
                    var $target = $(targetSelector);
                    if(!$target) {
                        throw new Error('Can\'t find layer target element like : ' + targetSelector);
                    }
                    var layers = $target.data('layers');
                    if(!layers || layers.length == 0) {
                        console.debug('no layers in ' + targetSelector);
                        return;
                    }
                    if(layerKey) {
                        var $layer = this.layerMap[layerKey].layer;
                        if(!$layer) {
                            return;
                        } else {
                            $layer.remove();
                            var nLayers = [];
                            for(var i = 0; i < layers.length; i++) {
                                if(layers[i] != layerKey) {
                                    nLayers.push(layers[i]);
                                }
                            }
                            layers = nLayers;
                            $target.data('layers', layers);
                        }
                    } else {
                        layerKey = layers.pop();
                        var $layer = this.layerMap[layerKey].layer;
                        $layer.remove();
                    }
                    if(layers.length == 0) {
                        $target.find('>div:first').find('>:first').unwrap();
                    } else {
                        this.layerMap[layers[layers.length-1]].layer.show();
                    }
                }
            }
        }
    }

}(jQuery);
