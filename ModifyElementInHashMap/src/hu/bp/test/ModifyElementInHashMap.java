package hu.bp.test;

import java.util.HashSet;

class MyObject {
	private int x;

	public MyObject(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof MyObject) {
			MyObject that = (MyObject) other;
			result = (this.getX() == that.getX());
		}
		return result;
	}

	@Override
	public int hashCode() {
		return (41 * (41 + getX()));
	}
}

public class ModifyElementInHashMap {

	public static void main(String[] args) {
		MyObject myObject = new MyObject(10);
		HashSet<MyObject> coll = new HashSet<MyObject>();
		coll.add(myObject);
		assert (coll.contains(myObject));

		myObject.setX(1);

		assert (coll.contains(myObject));
	}

}
