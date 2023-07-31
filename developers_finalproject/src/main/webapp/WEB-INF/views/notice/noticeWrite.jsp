<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${path }/css/noticeAndCommunity/coStyle.css" />

    <script src="https://unpkg.com/dropzone@5/dist/min/dropzone.min.js"></script>
    <link
      rel="stylesheet"
      href="https://unpkg.com/dropzone@5/dist/min/dropzone.min.css"
      type="text/css"
    />
<jsp:include page="/WEB-INF/views/common/header.jsp"/> 
<section>
   
    <div class="container p-3 nw-container">

        <div><h3 class="text-center">공지사항 작성</h3></div>
        <form class="notice-form" enctype="multipart/form-data"><!-- /notice/insertNotice.do -->
            <table class="table notice-table">
                <colgroup>
                    <col style="width: 20%">
                    <col style="width: 80%">
                    
                </colgroup>
                <tr>
                    <th class="text-center text-align">제목</th>
                    <td><input type="text" class="form-control" name="noticeTitle" ></td>
                </tr>
                <tr>
                    <th class="text-center text-align">내용</th>
                    <td><textarea class="form-control" rows="5" name="noticeContent"></textarea>
                </tr>
                
                <tr>
                    <th class="text-center text-align">
                        첨부파일
                    </th>
                    <td>
                        <div class="form-group row">
						    <label class="col-sm-2">첨부파일</label>    
						    <div class="col-sm-10">
						        <div class="dropzone" id="fileDropzone">
						            <div class="dz-message needsclick">
						                여기에 파일을 끌어 놓거나 업로드하려면 클릭하십시오.
						            </div>
						        </div>
						    </div>
						</div>

                    
                </td>
                </tr>
            </table>
            <div class="d-flex justify-content-center mt-4">
           <button type="submit" class="w-btn file-submit">등록하기</button>&nbsp;
           <button type="reset" class="btn btn-dark">취소</button>
                </div>
        </form>

    </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/> 
<script type="text/javascript">
Dropzone.autoDiscover = false;
  $(document).ready(function(){
      //var fileCount = ${fn:length(commonFileList)}; //해당 게시물의 첨부파일의 갯수
      
      Dropzone.autoDiscover = false;
      var myDropzone = new Dropzone(".dropzone", {
        url: '/notice//uploadFile.do', // 파일 업로드할 url
        method: "POST",
        paramName: 'files',
        params: {
            fk_code:${notice.noticeNo}
        },
        addRemoveLinks: true,
        dictRemoveFile: "삭제",
        init: function() {
            //if(fileCount > 0){
                 var thisDropzone = this;
                 
                 <c:forEach items="${commonFileList}" var="files">
                    var mockFile = {
                          code: "${files.fileNo}",
                          name: "${files.oriName}",
                          path: "/resources/imgs/${files.fileName}"
                        };
                    thisDropzone.emit("addedfile", mockFile);
                    thisDropzone.emit("thumbnail", mockFile, mockFile.path);
                    thisDropzone.emit("complete", mockFile);
                    thisDropzone.files.push(mockFile);
                </c:forEach>
           // }
        },
        removedfile: function(file) {
          // 파일 삭제 시
          var code = file.code == undefined ? file.temp : file.code; // 파일 업로드시 return 받은 code값
          console.log('code: ' + code);
            $.ajax({
                type: 'POST',
                url: '', // 파일 삭제할 url
                data: {code: code},
                success: function(data) {
                    console.log('success: ' + data);
                }
            });
     
            var _ref;
            return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
        }
        , success: function (file, response) {
          // 파일 업로드 성공 시
           file.temp = JSON.parse(response); // 파일을 삭제할 수도 있으므로 변수로 저장
        }
      });
  });
</script>
