package com.moneyhub.web.lambda;

import com.moneyhub.web.usr.Client;

public class GenericTest {
	static class Box<T>{
		T item;
		void setItem(T item) {this.item = item;}
		T geTItem() { return item;}
	}
	public static void main(String[] args) {
		GenericTest s = new GenericTest();
		GenericTest.Box<String> s2 = new GenericTest.Box<>();
		GenericTest.Box<Integer> s3 = new GenericTest.Box<>();
		GenericTest.Box<Client> ubox = new GenericTest.Box<>();
	}

}
