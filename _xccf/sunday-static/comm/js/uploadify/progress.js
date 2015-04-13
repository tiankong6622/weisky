/***********************************
描述：控制文件上传进度的JS文件
最后修改：mmcai(拾荒者)
最后修改时间：2014-1-9

Constructor
file is a SWFUpload file object
targetID is the HTML element id attribute that the FileProgress HTML structure will be added to.
Instantiating a new FileProgress object with an existing file will reuse/update the existing DOM elements
***********************************/

//定义文件进度类
function FileProgress(file, targetID) {

    this.fileProgressID = file.id;
    this.fileTypes = "t" + file.type.replace(".", "");
    this.fileSize = formatBPS(file.size);

    this.opacity = 100;
    this.height = 0;

    this.fileProgressWrapper = document.getElementById(this.fileProgressID);


    if (!this.fileProgressWrapper) {
        //创建进度条元素层
        this.fileProgressWrapper = document.createElement("div");
        this.fileProgressWrapper.className = "progressWrapper"
        this.fileProgressWrapper.id = this.fileProgressID;


        //上传文件内部包括层
        this.fileProgressElement = document.createElement("div");
        this.fileProgressElement.className = "progressContainer";

        //上传文件类型
        var progressTypes = document.createElement("b");
        progressTypes.className = "progressTypes " + this.fileTypes;

        //上传文件进度文字
        var progressRate = document.createElement("span");
        progressRate.className = "progressRate";
        progressRate.setAttribute("style", "display:none");
        progressRate.innerHTML = "0%";

        //上传文件大小
        var progressSize = document.createElement("span");
        progressSize.className = "progressSize";
        progressSize.setAttribute("style", "display:none");
        progressSize.innerHTML = this.fileSize;

        //上传文件暂停取消按钮
        var progressCancel = document.createElement("a");
        progressCancel.className = "progressCancel";
        progressCancel.href = "javascript:void(0);";
        progressCancel.appendChild(document.createTextNode("取消"));

        //上传文件删除按钮
        var progressDel = document.createElement("a");
        progressDel.className = "progressDel";
        progressDel.href = "javascript:void(0);";
        progressDel.appendChild(document.createTextNode("删除"));

        //上传文件文件名
        var progressText = document.createElement("span");
        progressText.className = "progressName";
        progressText.appendChild(document.createTextNode(file.name));
        progressText.title = file.name;

        //上传进度条外围包括层
        var progressBar = document.createElement("span");
        progressBar.className = "progressBarInProgress";
        progressBar.setAttribute("style", "display:none");

        //上传进度条内部元素
        var progressBarInner = document.createElement("span");
        progressBarInner.className = "progressBarInner bar";
        progressBarInner.id = "progressBarInner";

        //上传文件进度信息或者是描述信息
        var progressStatus = document.createElement("span");
        progressStatus.className = "progressBarStatus";
        progressStatus.innerHTML = "&nbsp;";

        //创建元素
        progressBar.appendChild(progressBarInner);

        this.fileProgressElement.appendChild(progressTypes);
        this.fileProgressElement.appendChild(progressText);
        this.fileProgressElement.appendChild(progressBar);
        this.fileProgressElement.appendChild(progressSize);
        this.fileProgressElement.appendChild(progressRate);
        this.fileProgressElement.appendChild(progressStatus);

        this.fileProgressElement.appendChild(progressCancel);
        this.fileProgressElement.appendChild(progressDel);

        this.fileProgressWrapper.appendChild(this.fileProgressElement);
        document.getElementById(targetID).appendChild(this.fileProgressWrapper);

    }
    else {
        this.fileProgressElement = this.fileProgressWrapper.firstChild;
        this.reset();
    }

    this.height = this.fileProgressWrapper.offsetHeight;
    this.setTimer(null);
}

//不明白
FileProgress.prototype.setTimer = function (timer) {
    this.fileProgressElement["FP_TIMER"] = timer;
};

//不明白
FileProgress.prototype.getTimer = function (timer) {
    return this.fileProgressElement["FP_TIMER"] || null;
};

FileProgress.prototype.reset = function () {

    //定义显示隐藏的部分
    this.fileProgressElement.childNodes[2].style.display = "block";
    this.fileProgressElement.childNodes[3].style.display = "none";
    this.fileProgressElement.childNodes[4].style.display = "block";
    this.fileProgressElement.childNodes[5].style.display = "none";

    this.appear();
};

FileProgress.prototype.setProgress = function (percentage) {
    this.fileProgressElement.className = "progressContainer progressInfo";
    this.fileProgressElement.childNodes[2].childNodes[0].style.width = percentage + "%";
    this.fileProgressElement.childNodes[4].innerHTML = percentage + "%";

    if (percentage === 100) {
        this.setComplete();
    } else {
        this.appear();
    }
};

FileProgress.prototype.setComplete = function () {
    this.fileProgressElement.className = "progressContainer progressSuccess";
    this.fileProgressElement.childNodes[5].innerHTML = "上传完成";
    this.fileProgressElement.childNodes[5].style.display = "block";

    this.fileProgressElement.childNodes[2].childNodes[0].style.width = "100px";
    this.fileProgressElement.childNodes[2].style.display = "none";

    this.fileProgressElement.childNodes[3].style.display = "block";
    this.fileProgressElement.childNodes[4].style.display = "none";

    this.fileProgressElement.childNodes[6].style.display = "none";
    this.fileProgressElement.childNodes[7].style.display = "block";

};

FileProgress.prototype.setError = function () {

    this.fileProgressElement.className = "progressContainer progressError";
    this.fileProgressElement.childNodes[2].style.display = "none";
    this.fileProgressElement.childNodes[3].style.display = "none";
    this.fileProgressElement.childNodes[4].style.display = "none";

    this.fileProgressElement.childNodes[5].style.display = "block";
};

//取消上传
FileProgress.prototype.setCancelled = function () {

    var oSelf = this;
    this.setTimer(setTimeout(function () {
        oSelf.disappear();
    }, 2000));

};

//设置上传状态
FileProgress.prototype.setStatus = function (status) {
    this.fileProgressElement.childNodes[5].innerHTML = status;
};

//显示隐藏取消按钮
FileProgress.prototype.toggleCancel = function (show, swfUploadInstance) {

    //这里的取消和删除上传分为两种情况
    //1、在上传之前报错的show=false
    //2、在上传之后报错的show=true

    var 
            $self = this,
            $cancel = this.fileProgressElement.childNodes[6],
            $del = this.fileProgressElement.childNodes[7];

    //取消和删除函数在这里绑定
    if (swfUploadInstance) {
        var 
                fileID = this.fileProgressID,
                attachmentUnitId = swfUploadInstance.settings.post_params.AttachmentCode;

        //删除
        $del.onclick = function () {
            //从Flash上传队列中取消
            swfUploadInstance.cancelUpload(fileID);

            //执行异步去数据库删除
            if (swfUploadInstance.customSettings.cancelUpload) {
                $self.setError();
                $self.setStatus("正在删除...");
                swfUploadInstance.customSettings.cancelUpload.call($self, fileID);
            }

            //隐藏上传的状态值
            return false;
        };

        //取消,从上传队列中删掉
        $cancel.onclick = function () {
            swfUploadInstance.cancelUpload(fileID, false);
            $self.setStatus("正在取消...");
            $self.setError();
            $self.setCancelled();
            return false;
        }
    }

};

//添加上传的进度层
FileProgress.prototype.appear = function () {

    if (this.getTimer() !== null) {
        clearTimeout(this.getTimer());
        this.setTimer(null);
    }

    if (this.fileProgressWrapper.filters) {
        try {
            this.fileProgressWrapper.filters.item("DXImageTransform.Microsoft.Alpha").opacity = 100;
        } catch (e) {
            // If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
            this.fileProgressWrapper.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=100)";
        }
    }
    else {
        this.fileProgressWrapper.style.opacity = 1;
    }

    this.fileProgressWrapper.style.height = "";

    this.height = this.fileProgressWrapper.offsetHeight;
    this.opacity = 100;
    this.fileProgressWrapper.style.display = "";
};

// 隐藏并移除进度条盒子
FileProgress.prototype.disappear = function () {

    var reduceOpacityBy = 15;
    var reduceHeightBy = 4;
    var rate = 30; // 15 fps

    if (this.opacity > 0) {
        this.opacity -= reduceOpacityBy;
        if (this.opacity < 0) {
            this.opacity = 0;
        }

        if (this.fileProgressWrapper.filters) {
            try {
                this.fileProgressWrapper.filters.item("DXImageTransform.Microsoft.Alpha").opacity = this.opacity;
            } catch (e) {
                // If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
                this.fileProgressWrapper.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=" + this.opacity + ")";
            }
        } else {
            this.fileProgressWrapper.style.opacity = this.opacity / 100;
        }
    }

    if (this.height > 0) {
        this.height -= reduceHeightBy;
        if (this.height < 0) {
            this.height = 0;
        }

        this.fileProgressWrapper.style.height = this.height + "px";
    }

    if (this.height > 0 || this.opacity > 0) {
        var oSelf = this;
        this.setTimer(setTimeout(function () {
            oSelf.disappear();
        }, rate));
    }
    else {
        this.fileProgressWrapper.style.display = "none";
        this.setTimer(null);
    }

};


FileProgress.prototype.setStartUpload = function () {


}


function formatUnits(baseNumber, unitDivisors, unitLabels, singleFractional) {
    var i, unit, unitDivisor, unitLabel;

    if (baseNumber === 0) {
        return "0 " + unitLabels[unitLabels.length - 1];
    }

    if (singleFractional) {
        unit = baseNumber;
        unitLabel = unitLabels.length >= unitDivisors.length ? unitLabels[unitDivisors.length - 1] : "";
        for (i = 0; i < unitDivisors.length; i++) {
            if (baseNumber >= unitDivisors[i]) {
                unit = (baseNumber / unitDivisors[i]).toFixed(2);
                unitLabel = unitLabels.length >= i ? " " + unitLabels[i] : "";
                break;
            }
        }

        return unit + unitLabel;
    } else {
        var formattedStrings = [];
        var remainder = baseNumber;

        for (i = 0; i < unitDivisors.length; i++) {
            unitDivisor = unitDivisors[i];
            unitLabel = unitLabels.length > i ? " " + unitLabels[i] : "";

            unit = remainder / unitDivisor;
            if (i < unitDivisors.length - 1) {
                unit = Math.floor(unit);
            } else {
                unit = unit.toFixed(2);
            }
            if (unit > 0) {
                remainder = remainder % unitDivisor;

                formattedStrings.push(unit + unitLabel);
            }
        }

        return formattedStrings.join(" ");
    }
};

function formatBPS(baseNumber) {
    var bpsUnits = [1073741824, 1048576, 1024, 1], bpsUnitLabels = ["Gbps", "Mbps", "Kbps", "bps"];
    return formatUnits(baseNumber, bpsUnits, bpsUnitLabels, true);
};