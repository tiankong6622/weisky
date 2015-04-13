/// <reference path="swfupload.js" />
/// <reference path="../jquery-1.8.3.min.js" />

var swfu = null;
$(function () {
    swfu = new SWFUpload({
        upload_url: "/ajaxswf/upLoadFile",
        flash_url: "/comm/js/uploadify/swf/swfupload.swf",
        flash9_url: "/comm/js/uploadify/swf/swfupload_fp9.swf",

        //文件配置
        file_post_name: "Filedata",
        file_size_limit: "200MB",
        file_types: "*.jpg;*.png;*.gif;*.bmp;*.rar;*.zip;*.flv;*.doc;",
        file_types_description: "图片类型",
        file_upload_limit: 6,
        file_queue_limit: 1,
       

        //用户自定义设置进度条，上传和取消上传按钮
        custom_settings: {
            progressTarget: "divFileProgressContainer",
            completeCallback: uploadDoneCallback,
            cancelUpload: cancelUploadCallback
        },

        //文件上传事件绑定——事件绑定函数全部在handlers.js文件当中
        swfupload_loaded_handler: loadedFunction,
       // swfupload_preload_handler: preLoad,
        swfupload_load_failed_handler: loadFailed,
        file_queue_error_handler: fileQueueError,
        file_dialog_complete_handler: fileDialogComplete,
        upload_progress_handler: uploadProgress,
        upload_error_handler: uploadError,
        upload_success_handler: uploadSuccess,
        upload_complete_handler: uploadComplete,
        file_queued_handler: fileQueued,
        upload_start_handler: uploadStart,


        //上传文件添加的按钮设置
        button_placeholder_id: "uploadBtn",
        button_image_url: "/comm/js/uploadify/css/imgs/uploadBtn.png",
        button_width: "84",
        button_height: "24",
        button_cursor: -2,

        //调试及其它
        debug: false
    });
});