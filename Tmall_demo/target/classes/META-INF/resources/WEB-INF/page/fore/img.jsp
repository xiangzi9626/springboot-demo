<%@ page contentType="text/html;charset=UTF-8" %>
<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <script src="${pageContext.request.contextPath}/res/js/jquery-3.2.1.js"></script>
    <title>植物识别</title>
</head>
<body style="background:#2b542c;width:100%;height:calc(100%)">
<div style="width:100%;text-align: center;margin-top:80px;color:#ffff00">
    <h3>植物识别系统</h3>
    <h4>可识别超过2万种常见植物和近8千种花卉</h4>
</div>
<div style="width:100%;text-align:center;margin-top:80px">
<table style="text-align: center;margin:auto">
    <tr>
        <td style="text-align:center;">
             <img id="img" style="max-width:100%;max-height:300px;" src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/2.jpg"/>
        </td>
        <td>
             <div style="margin-left:50px;color: #FFFFFF">
                <h3>识别结果</h3>
                <h6 id="name0">牡丹</h6>
                 <h6 id="name1"></h6>
                 <h6 id="name2"></h6>
            </div>
        </td>
    </tr>
</table>
</div>
<div style="width:100%;text-align:center;margin-top:80px;">
    <input onclick="$('#uploadImg').click()" type="button" value="选择图片" style="background: #22AB38;color:#FFFFFF;padding:15px 40px;border:0"/>
</div>
<!----//上传图片隐藏表单///---->
<form method="post" style="display: none;" id="uploadForm" enctype="multipart/form-data">
    <input multiple accept="image/*" type="file" id="uploadImg" name="upload"
           style="display:none">
</form>
<script>
    $(function () {
        $("#uploadImg").on("change", function () {
            var file = $("#uploadImg").get(0).files[0];
            var formData = new FormData();//*
            formData.append("file", file);
            formData.append("filePath",$("#uploadImg").val());
            $.ajax({
                type: 'post',
                url: '/tmall/recognition',
                data: formData,
                processData: false,//*
                contentType: false,//*
                success: function (data) {
                    var obj = eval("(" + data + ")");
                     $("#img").attr("src","${pageContext.request.contextPath}/res/images/item/productDetailsPicture/"+obj.fileName);
                    $("#name0").text(obj.name0);
                    $("#name1").text(obj.name1);
                    $("#name2").text(obj.name2);
                     $(file).val("");
                }
            })
        })
    })
</script>
</body>
</html>