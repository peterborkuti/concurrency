package hashcache;

public class Main {

	public static void main(String[] args) throws Exception {
		HashCachedThinker thinker = new HashCachedThinker();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				thinker.setInput(j);
				System.out.println(thinker.call());
			}
		}

	}

}
