package cache.interfaces;

public interface Cache<CACHEABLETYPE, KEY> {
	/**
	 * stores the obj. If there is already an object stored for the
	 * key, ii updates the cache and the previous value will be list 
	 * @param obj
	 */
	public void store(KEY key, CACHEABLETYPE obj);
	/**
	 * Checks if there is a stored object for the key
	 * @param key
	 * @return
	 */
	public boolean stored(KEY key);
	/**
	 * retrieves the stored object based on the key
	 * @param key
	 * @return
	 */
	public CACHEABLETYPE get(KEY key);
}
