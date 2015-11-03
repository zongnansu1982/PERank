package edu.snu.bike.perank.query;

public class SimpleQueryFactory {
	public static String QUERYWITHOUTPREFERENCE="model1";
	public static String QUERYWITHPREFERENCE="model2";
	public static String QUERYWITHWEIGHTEDPREFERNCE="model3";
	private static SimpleQueryFactory query = new SimpleQueryFactory();
	private SimpleQueryFactory(){
		
	}
	public static SimpleQueryFactory getQueryModel(){
		return query;
	}
	
	public void factory(String type){
		if(type.endsWith(QUERYWITHOUTPREFERENCE)){
			System.out.println("Query Model 1");
		}
		if(type.endsWith(QUERYWITHPREFERENCE)){
			System.out.println("Query Model 2");
		}
		if(type.equals(QUERYWITHWEIGHTEDPREFERNCE)){
			System.out.println("Query Model 3");
		}
	}
	
	public static  void main(String[] args){
		
		SimpleQueryFactory query= SimpleQueryFactory.getQueryModel();
		query.factory(SimpleQueryFactory.QUERYWITHOUTPREFERENCE);
		
	}
	
}
