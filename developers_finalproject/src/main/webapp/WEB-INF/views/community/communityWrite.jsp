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
    <div class="card-box-write">
    <div style="margin-top:2%;">
     	<h2>게시글 작성</h2>
     	</div>
        <form action="" class="card-box-form">
      
        <div class="card-box">
            <div class="card" style="width:1300px;">
                <div class="registImgCon form-control">
                    <div class="registImg">
                      <div class="dropzone" id="dropDiv"></div>
                      <div id="imgInfo">
                        <p>-파일은 최대 5개까지 등록 가능합니다.</p>
                        <p>-권장 사이즈(500px * 500px)</p>
                      </div>
                      <!-- 포스팅 - 이미지/동영상 dropzone 영역 -->
                      <div id="dropPreview">
                        <ul class="list-unstyled mb-0" id="dropzone-preview">
                          <li class="mt-2" id="dropzone-preview-list">
                            <!-- This is used as the file preview template -->
                            <div class="border rounded-3">
                              <div class="d-flex align-items-center p-2">
                                <div class="flex-shrink-0 me-3">
                                  <div class="width-8 h-auto rounded-3">
                                    <img
                                      data-dz-thumbnail="data-dz-thumbnail"
                                      class="w-full h-auto rounded-3 block"
                                      src="#"
                                      alt="Dropzone-Image"
                                      style="width: 120px"
                                    />
                                  </div>
                                </div>
                                <div class="flex-grow-1">
                                  <div class="pt-1">
                                    <h6
                                      class="font-semibold mb-1"
                                      data-dz-name="data-dz-name"
                                    >
                                      &nbsp;
                                    </h6>
                                    <p
                                      class="text-sm text-muted fw-normal"
                                      data-dz-size="data-dz-size"
                                    ></p>
                                    <strong
                                      class="error text-danger"
                                      data-dz-errormessage="data-dz-errormessage"
                                    ></strong>
                                  </div>
                                </div>
                                <div class="shrink-0 ms-3">
                                  <button
                                    data-dz-remove="data-dz-remove"
                                    class="del-img w-btn w-btn-red"
                                  >
                                    Delete
                                  </button>
                                </div>
                              </div>
                            </div>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
               
                <div class="card-body">
                    
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">제목</label>
                        <input type="text" class="form-control border border-danger" id="exampleFormControlInput1">
                      </div>
                      <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">내용</label>
                        <textarea class="form-control border border-danger" id="exampleFormControlTextarea1" rows="7"></textarea>
                      </div>
                    
                      <div class="comu-buttons">
                        <button class="w-btn w-btn-blue-outline">등록</button>&nbsp;&nbsp;&nbsp;
                        <button class="w-btn w-btn-gray-outline">취소</button>
                      </div>
                    
             </div>



        </div>
        </div>
        </form>
        </div>
    </section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
  <script>

        Dropzone.autoDiscover = false;
        var dropzonePreviewNode = document.querySelector("#dropzone-preview-list");
        dropzonePreviewNode.id = "";
        var previewTemplate = dropzonePreviewNode.parentNode.innerHTML;
        dropzonePreviewNode.parentNode.removeChild(dropzonePreviewNode);

        const dropzone = new Dropzone(".dropzone", {
        autoProcessQueue: false,
        url: "https://httpbin.org/post", // 파일을 업로드할 서버 주소 url.
        method: "post", // 기본 post로 request 감. put으로도 할수있음
        uploadMultiple: true,
        maxFiles: 5,
        maxFilesize: 5,
        acceptedFiles: "image/*",
        previewTemplate: previewTemplate, // 만일 기본 테마를 사용하지않고 커스텀 업로드 테마를 사용하고 싶다면
        previewsContainer: "#dropzone-preview", // 드롭존 영역을 .dropzone이 아닌 다른 엘리먼트에서 하고싶을때
        init: function () {
                /* 최초 dropzone 설정시 init을 통해 호출 */
                var submitButton = document.querySelector("#btn-upload-file");
                var myDropzone = this; //closure
                submitButton.addEventListener("click", function () {
                    console.log("업로드"); //tell Dropzone to process all queued files
                    myDropzone.processQueue();
                });
            },
        });
  </script>

</html>