package edu.snu.bike.perank.bean;

public class DoublebingBean {
	private String startNode;
	public String getStartNode() {
		return startNode;
	}
	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}
	public String getEndNode() {
		return endNode;
	}
	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIterationClass() {
		return iterationClass;
	}
	public void setIterationClass(String iterationClass) {
		this.iterationClass = iterationClass;
	}
	public int getIterationNum() {
		return iterationNum;
	}
	public void setIterationNum(int iterationNum) {
		this.iterationNum = iterationNum;
	}
	public int getDoublingItr() {
		return DoublingItr;
	}
	public void setDoublingItr(int doublingItr) {
		DoublingItr = doublingItr;
	}
	private String endNode;
	private String type;
	private String iterationClass;
	private int iterationNum;
	private int DoublingItr;
	

}
