package com.dev.common;

public class PageFactory {

   public static String getPage(int cPage, int numPerpage, int totalData,String url) {
      
      //매개변수값을 이용해서 pageBar만들어주는 함수
      StringBuffer pageBar=new StringBuffer();
      
      int totalPage=(int)(Math.ceil((double)totalData/numPerpage));
      int pageBarSize=5;
      int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
      int pageEnd=pageNo+pageBarSize-1;
      
      pageBar.append("<ul class='pagination justify-content-center pagination-sm'>");
      
      if(pageNo==1) {
         pageBar.append("<li class='page-item disabled'>");
         pageBar.append("<a class='page-link' href='#'>이전");
         pageBar.append("</a>");
         pageBar.append("</li>");
      }else {
         pageBar.append("<li class='page-item'>");
         pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo-1)+")'>이전");
         pageBar.append("</a>");
         pageBar.append("</li>");
      }
      while(!(pageNo>pageEnd||pageNo>totalPage)) {
         if(pageNo==cPage) {
            pageBar.append("<li class='page-item disabled'>");
            pageBar.append("<a class='page-link' href='#'>"+pageNo);
            pageBar.append("</a>");
            pageBar.append("</li>");
         }else {
            pageBar.append("<li class='page-item'>");
            pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>"+pageNo);
            pageBar.append("</a>");
            pageBar.append("</li>");
         }
         pageNo++;
      }
//      String str="""
//            
//      """.formatted(,,,,,,,);
      
      
      if(pageNo>totalPage){
         pageBar.append("<li class='page-item disabled'>");
         pageBar.append("<a class='page-link' href='#'>다음");
         pageBar.append("</a>");
         pageBar.append("</li>");
      }else {
         pageBar.append("<li class='page-item'>");
         pageBar.append("<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>다음");
         pageBar.append("</a>");
         pageBar.append("</li>");
      }
      pageBar.append("</ul>");
      
      //스트립트문추가
      pageBar.append("<script>");
      pageBar.append("function fn_paging(no){");
      pageBar.append("location.assign('"+url+"?cPage='+no+'&numPerpage="+numPerpage+"');");
      pageBar.append("}");
      pageBar.append("</script>");
      
      return new String(pageBar);
      
   }
   
   
   
   
}
