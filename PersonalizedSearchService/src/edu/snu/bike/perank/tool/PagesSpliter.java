package edu.snu.bike.perank.tool;


public class pagesSpliter {
    /**
     * GOOGLE 分页
     * @param curpage 当前页
     * @param showNum 页面显示的页数
     * @param pages      实际页数
     * @return
     */
    public static Integer[] pagesSplit(Integer curpage, Integer showNum, Integer pages ) {
        
    	   Integer startIndex = curpage;
           
           Integer endIndex = showNum + startIndex - 1;
       
           if(endIndex > pages) {
           
               endIndex = pages;
           
           }
       
         
           Integer[] re= new Integer[endIndex-startIndex+1];
           int j=startIndex;
           for (int i = 0; i < re.length; i++) {
        	   re[i]=j;
//        	   System.out.println("page["+i+"]= "+j);
        	   j++;
		}
           return re;
        
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pagesSpliter.pagesSplit(3, 10, 6);
	}

}
