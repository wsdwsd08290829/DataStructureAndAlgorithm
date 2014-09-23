package com.sidausc.ds.tree;

import com.sidausc.ds.listanditerators.Position;

public interface BTPosition<E> extends Position<E> {
	/** Returns the element stored at this position */

	  /** Sets the element stored at this position */
	  public void setElement(E o);
	  /** Returns the left child of this position */
	  public BTPosition<E> getLeft();
	  /** Sets the left child of this position */
	  public void setLeft(BTPosition<E> v);
	  /** Returns the right child of this position */
	  public BTPosition<E> getRight();
	  /** Sets the right child of this position */
	  public void setRight(BTPosition<E> v);
	  /** Returns the parent of this position */
	  public BTPosition<E> getParent();
	  /** Sets the parent of this position */
	  public void setParent(BTPosition<E> v);
	  public String toString();
	}

