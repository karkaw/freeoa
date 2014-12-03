package com.damuzee.web.util;

import java.util.Iterator;

public class KeyReader implements Iterator<String> {

	private int cursorIndex;
	private int markIndex;
	private String key;
	private boolean isArrayElement;

	public KeyReader(String key) {
		this.key = key;
		markIndex = key.length();
		cursorIndex = key.length();
	}

	public boolean hasNext() {
		return cursorIndex > 0;
	}

	public String next() {
		markIndex = cursorIndex;
		while (cursorIndex > 0) {
			char ch = key.charAt(--cursorIndex);
			if (ch == ':') {
				isArrayElement = false;
				return key.substring(cursorIndex + 1, markIndex);
			}
			if (ch == '[') {
				isArrayElement = true;
				return key.substring(cursorIndex, markIndex);
			}
		}
		String ret = key.substring(cursorIndex, markIndex);
		return ret;
	}

	public void remove() {
		throw new UnsupportedOperationException("readonly.");
	}

	public boolean isArrayElement() {
		return isArrayElement;
	}
	
	public int index() {
		if (isArrayElement) {
			return Integer.valueOf(key.substring(cursorIndex +1, markIndex -1));
		}
		throw new UnsupportedOperationException("not an array element.");
	}

	public String parentKey() {
		return key.substring(0, cursorIndex);
	}

	public static void main(String[] args) {
		String key = "dept.name[0][0].field0";
		String key1 = "dept.name[0][1].field1";
		String key2 = "dept.name.firstname";
		String key3 = "dept.name.lastname";
		
		KeyReader reader = new KeyReader(key1);
		while (reader.hasNext()) {
			System.out.println(reader.next());
			if (reader.isArrayElement) {
				System.out.println(reader.index());
			} else {
				System.out.println("null");
			}
		}
	}
}
