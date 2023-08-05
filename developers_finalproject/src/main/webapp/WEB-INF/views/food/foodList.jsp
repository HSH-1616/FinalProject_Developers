<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<button onclick="location.href='${path }/food/foodTest.do'">json테스트</button>

<script>

//String link = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
//int numOfRows = 10;
//int pageNo = 1;
//String MobileOS = "ETC";
//String MobileApp = "Test";
//String _type = "json";
//String listYN = "Y";
//String arrange = "A";
//String keyword = "고든램지버거";
//int contentTypeId =39;
//String serviceKey = "Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D";
//
//String url = link + "?" +
//		"&numOfRows=" + numOfRows +
//		"&pageNo=" + pageNo +
//      "&MobileOS=" + MobileOS +
//      "&MobileApp=" + MobileApp +
//      "&_type=" + _type +
//      "&listYN=" + listYN +
//      "&=arrange" + arrange +
//      "&=keyword" + keyword +
//      "&contentTypeId=" + contentTypeId +
//      "&serviceKey=" + serviceKey;

var obj = {
		"numOfRows": 10,
		"pageNo": 1,
		"MobileOS": "ETC",
		"MobileApp": "TEST",
		"_type": "json",
		"listYN": "Y",
		"arrange": "A",
		"keyword": "고든램지버거",
		"contentTypeId": 39,
		"serviceKey": "Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
		};

function test() {
    $.ajax({
        url: "https://apis.data.go.kr/B551011/KorService1/areaBasedList1",
        type: "get",
        data: JSON.stringify(obj),
        dataType: "json",
        contentType: "application/json",
        success: function(data) {
            alert("성공");
        },
        error: function(errorThrown) {
            alert(errorThrown.statusText);
        }
    });
}
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />