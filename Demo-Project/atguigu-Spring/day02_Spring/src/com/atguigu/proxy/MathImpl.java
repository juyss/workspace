package com.atguigu.proxy;

public class MathImpl implements MathI {

	@Override
	public int add(int i, int j) {
		System.out.println("method:add,arguments:"+i+","+j);
		int result = i + j;
		System.out.println("method:add,result:"+result);
		return result;
	}

	@Override
	public int sub(int i, int j) {
		System.out.println("method:sub,arguments:"+i+","+j);
		int result = i - j;
		System.out.println("method:sub,result:"+result);
		return result;
	}

	@Override
	public int mul(int i, int j) {
		System.out.println("method:mul,arguments:"+i+","+j);
		int result = i * j;
		System.out.println("method:mul,result:"+result);
		return result;
	}

	@Override
	public int div(int i, int j) {
		System.out.println("method:div,arguments:"+i+","+j);
		int result = i / j;
		System.out.println("method:div,result:"+result);
		return result;
	}

	
	
}
