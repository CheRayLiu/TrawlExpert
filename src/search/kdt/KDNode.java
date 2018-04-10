package search.kdt;

import java.io.Serializable;

/**
 * A node in a serializable KD-tree.
 * 
 * @author Christopher W. Schankula
 *
 */
class KDNode<KeyVal extends Comparable<KeyVal>> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 320302736359757688L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	KeyVal keyval;
	KDNode<KeyVal> left;
	KDNode<KeyVal> right;
	int n;

	/**
	 * Constructor for KDNode. Creates a new KD-tree node.
	 * 
	 * @param keyval
	 * @param n
	 */
	public KDNode(KeyVal keyval, int n) {
		this.keyval = keyval;
		this.n = n;
	}
}