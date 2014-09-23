package com.sidausc.ds.tree;

//import com.sidausc.ds.searchtree.AVLTree.AVLNode;

/**
 * Class implementing a node of a binary tree by storing references to
 * an element, a parent node, a left node, and a right node.
 */
public class BTNode<E> implements BTPosition<E> {
	 protected int height;
  private E element;  // element stored at this node
  private BTPosition<E> left, right, parent;  // adjacent nodes
  /** Main constructor */
  public BTNode(){};
  public BTNode(E element, BTPosition<E> parent, 
		BTPosition<E> left, BTPosition<E> right) { 
	 
    setElement(element);
    setParent(parent);
    setLeft(left);
    setRight(right);
    
  
  }
 

  /** Returns the element stored at this position */
  public E element() { return element; }
  /** Sets the element stored at this position */
  public void setElement(E o) { element=o; }
  /** Returns the left child of this position */
  public BTPosition<E> getLeft() { return left; }
  /** Sets the left child of this position */
  public void setLeft(BTPosition<E> v) { left=v; }
  /** Returns the right child of this position */
  public BTPosition<E> getRight() { return right; }
  /** Sets the right child of this position */
  public void setRight(BTPosition<E> v) { right=v; }
  /** Returns the parent of this position */
  public BTPosition<E> getParent() { return parent; }
  /** Sets the parent of this position */
  public void setParent(BTPosition<E> v) { parent=v; }
  
  public String toString(){
	if(element() != null)
	  return element().toString();
	return "null element&not position";
  }
}