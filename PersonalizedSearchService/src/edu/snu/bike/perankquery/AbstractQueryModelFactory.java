package edu.snu.bike.perankquery;

import java.util.HashMap;

import edu.snu.bike.perank.bean.SearchBean;

public abstract class AbstractQueryModelFactory {
	public abstract Model1Factory createModel1();
	public abstract Model2Factory createModel2();
	public abstract Model3Factory createModel3();
}
