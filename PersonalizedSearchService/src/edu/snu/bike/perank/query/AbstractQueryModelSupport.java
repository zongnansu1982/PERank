package edu.snu.bike.perank.query;

public class AbstractQueryModelSupport extends AbstractQueryModelFactory {
	String index;
	public AbstractQueryModelSupport(String index){
	this.index=index;
	}
	
	@Override
	public Model1Factory createModel1() {
		// TODO Auto-generated method stub
		return new Model1Factory(index);
	}
	@Override
	public Model2Factory createModel2() {
		// TODO Auto-generated method stub
		return new Model2Factory(index);
	}
	@Override
	public Model3Factory createModel3() {
		// TODO Auto-generated method stub
		return new Model3Factory(index);
	}

}
