package com.dev.common;

public class PageFactory {

	public static String getPage(int cPage, int numPerpage, int totalData,String js) {
		
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
			pageBar.append("<a class='page-link' href='javascript:"+js+"("+(pageNo-1)+")'>이전");
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
				pageBar.append("<a class='page-link' href='javascript:"+js+"("+(pageNo)+")'>"+pageNo);
				pageBar.append("</a>");
				pageBar.append("</li>");
			}
			pageNo++;
		}

		if(pageNo>totalPage){
			pageBar.append("<li class='page-item disabled'>");
			pageBar.append("<a class='page-link' href='#'>다음");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}else {
			pageBar.append("<li class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:"+js+"("+(pageNo)+")'>다음");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}
		pageBar.append("</ul>");
		
		
		return new String(pageBar);
		
	}
	
	
	
	
}

