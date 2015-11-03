package edu.snu.bike.perank.query;

import java.util.HashMap;

import edu.snu.bike.perank.bean.SearchBean;

public class AbstractQueryModelSupport extends AbstractQueryModelFactory {
	String index;
	public AbstractQueryModelSupport(String index){
	this.index=index;
	}
	
	public Model1Factory createModel1() {
		// TODO Auto-generated method stub
		return new Model1Factory(index);
	}
	public Model2Factory createModel2() {
		// TODO Auto-generated method stub
		return new Model2Factory(index);
	}
	public Model3Factory createModel3() {
		// TODO Auto-generated method stub
		return new Model3Factory(index);
	}

}
