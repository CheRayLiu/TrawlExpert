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
	 * The serialization version id for the KDNode.
	 */
	private static final long serialVersionUID = 320302736359757688L;
	/**
	 * The key-value pair in the node.
	 */
	KeyVal keyval;
	/**
	 * A pointer to the left node attached to this one.
	 */
	KDNode<KeyVal> left;
	/**
	 * A pointer to the right node attached to this one.
	 */
	KDNode<KeyVal> right;
	/**
	 * The number of nodes in the subtree under this node.
	 */
	int n;

	/**
	 * Constructor for KDNode. Creates a new KD-tree node.
	 * 
	 * @param keyval The key-value pair to insert into the node.
	 * @param n The number of nodes in this node's subtree.
	 */
	public KDNode(KeyVal keyval, int n) {
		this.keyval = keyval;
		this.n = n;
	}
}