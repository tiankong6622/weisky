/* **********************
Event Handlers
These are my custom event handlers to make my
web application behave the way I went when SWFUpload
completes different tasks.  These aren't part of the SWFUpload
package.  They are part of my application.  Without these none
of the actions SWFUpload makes will show up in my application.
********************** */
function preLoad() {
    //判断是否支持flash
}
function loadFailed() {
    //加载flash报错
}
function fileDialogStart() {
    //弹出选择文件框
}

//添加到队列中
function fileQueued(file) {
    var progress = new FileProgress(file, this.customSettings.progressTarget);
    progress.toggleCancel(true, this);
    progress.setStatus("等待上传，请稍候...");
}

//填充队列报错
function fileQueueError(file, errorCode, message) {

    if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
        alert("你添加到队列太多的文件\n" + (message === 0 ? "超出了允许添加的数量！" : "你每次允许选择" + (message > 1 ? "最多不超过 " + message + " 个文件." : "一个文件！")));
        return;
    }

    var progress = new FileProgress(file, this.customSettings.progressTarget);
    progress.setError();

    //错误信息
    switch (errorCode) {
        case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
            progress.setStatus("文件太大！");
            this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
            break;
        case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
            progress.setStatus("不能上传空字节的文件！");
            this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
            break;
        case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
            progress.setStatus("不允许的文件类型！");
            this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
            break;
        case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
            alert("你添加了太多的文件.  " + (message > 1 ? "你最多允许添加" + message + "个文件" : "你不能继续添加文件!"));
            break;
        default:
            if (file !== null) {
                progress.setStatus("未知错误！");
            }
            this.debug("错误代码: " + errorCode + ", 文件名: " + file.name + ", 文件大小: " + file.size + ", 信息: " + message);
            break;
    }

    progress.toggleCancel(true, this);

}

//选择完上传文件执行的函数
function fileDialogComplete(numFilesSelected, numFilesQueued) {
    //如果有自定义上传按钮，就通过自定义上传按钮点击执行上传，
    //如果设置了自动上传参数，就自动上传

    /*选择需要上传的文件之后，立即上传 */
    if (numFilesQueued > 0) {
        this.startUpload();
    }
}

//上传开始
function uploadStart(file) {

    //this.addPostParam("AttachmentCode", $attachmentCode);

    /*不做任何文件验证或任何东西，
    仅仅就更新UI，并返回true，表示上载开始*/
    var progress = new FileProgress(file, this.customSettings.progressTarget);
    // progress.setStatus("开始上传!");
    //  progress.setStartUpload();
    //可以取消上传
    progress.toggleCancel(true, this);

    return true;
}

//上传进度
function uploadProgress(file, bytesLoaded, bytesTotal) {
    var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
    var progress = new FileProgress(file, this.customSettings.progressTarget);
    progress.setProgress(percent);

    //可以取消上传
    progress.toggleCancel(true, this);
}

//上传成功
function uploadSuccess(file, serverData) {
    //文件上传成功之后，在这里创建缩略图
    var 
            progress = new FileProgress(file, this.customSettings.progressTarget),
            uploadSuccessCallback = this.customSettings.completeCallback;

    progress.setComplete();
    progress.toggleCancel(true, this);

    //当上传成功一个文件之后需要执行的函数
    if (!uploadSuccessCallback) return false;
    uploadSuccessCallback.call(file, serverData);

}

//上传完成执行函数
function uploadComplete(file) {
    var $queuedNum = this.getStats().files_queued;

    //如果队列中没有文件了，就表示上传全部完成了
    if ($queuedNum === 0) {

    } else {
        //如果队列中还有文件，就继续上传
        this.startUpload();
    }
}

//上传出错的函数
function uploadError(file, errorCode, message) {
    //取消上传
    try {
        var progress = new FileProgress(file, this.customSettings.progressTarget);
        progress.setError();
        progress.toggleCancel(true, this);

        switch (errorCode) {
            case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
                progress.setStatus("Upload Error: " + message);
                this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
                progress.setStatus("Configuration Error");
                this.debug("Error Code: No backend file, File name: " + file.name + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
                progress.setStatus("Upload Failed.");
                this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.IO_ERROR:
                progress.setStatus("Server (IO) Error");
                this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
                progress.setStatus("Security Error");
                this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
                progress.setStatus("Upload limit exceeded.");
                this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
                progress.setStatus("未找到文件");
                this.debug("Error Code: The file was not found, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
                progress.setStatus("Failed Validation.  Upload skipped.");
                this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
                if (this.getStats().files_queued === 0) {
                    document.getElementById(this.customSettings.cancelButtonId).disabled = true;
                }
                progress.setStatus("正在取消上传...");
                progress.setCancelled();
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
                progress.setStatus("暂停上传");
                break;
            default:
                progress.setStatus("Unhandled Error: " + error_code);
                this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
        }
    } catch (ex) {
        this.debug(ex);
    }
}


