package com.sidausc.ds.tree;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.Position;

/**
 * An interface for a binary tree, where each node can have zero, one,
 * or two children.
 */
public interface BinaryTree<E> extends Tree<E> {
  /** Returns the left child of a node. */
  public Position<E> left(Position<E> v) 
    throws InvalidPositionException, BoundaryViolationException;
  /** Returns the right child of a node. */
  public Position<E> right(Position<E> v) 
    throws InvalidPositionException, BoundaryViolationException;
  /** Returns whether a node has a left child. */
  public boolean hasLeft(Position<E> v) throws InvalidPositionException;
  /** Returns whether a node has a right child. */
  public boolean hasRight(Position<E> v) throws InvalidPositionException;
  public boolean isLeftChild(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
  public boolean isRightChild(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
}