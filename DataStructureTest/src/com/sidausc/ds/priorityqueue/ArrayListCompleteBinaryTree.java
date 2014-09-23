package com.sidausc.ds.priorityqueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sidausc.ds.listanditerators.BoundaryViolationException;
import com.sidausc.ds.listanditerators.InvalidPositionException;
import com.sidausc.ds.listanditerators.NodePositionList;
import com.sidausc.ds.listanditerators.Position;
import com.sidausc.ds.listanditerators.PositionList;
import com.sidausc.ds.priorityqueue.ArrayListCompleteBinaryTree.BTPos;
import com.sidausc.ds.tree.EmptyTreeException;

public class ArrayListCompleteBinaryTree<E> 
implements CompleteBinaryTree<E>  {
	protected ArrayList<BTPos<E>> T;  // indexed list of tree positions
	/** Nested class for a index list-based complete binary tree node. */
	public ArrayList<BTPos<E>> getArrayList(){return T;}
	protected static class BTPos<E> implements Position<E> {
		E element; // element stored at this position
		int index;      // index of this position in the array list
		public BTPos(E elt, int i) { 
		  element = elt;
		  index = i; 
		}
		public E element() { return element; }
		public int index() { return index; }
		public E setElement(E elt) {
		  E temp = element;
		  element = elt;
		  return temp;
		}
		public String toString(){
			return element.toString();
		}
	}
	/** default constructor */
	public ArrayListCompleteBinaryTree() { 
	T = new ArrayList<BTPos<E>>();
	T.add(0, null); // the location at rank 0 is deliberately empty
	}
	/** Returns the number of (internal and external) nodes. */
	public int size() { return T.size() - 1; } 
	/** Returns whether the tree is empty. */ 
	public boolean isEmpty() { return (size() == 0); } 
	
	
	
	/** Returns whether v is an internal node. */
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
	  return hasLeft(v);  // if v has a right child it will have a left child
	}
	/** Returns whether v is an external node. */
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
	  return !isInternal(v);
	}
	/** Returns whether v is the root node. */
	public boolean isRoot(Position<E> v) throws InvalidPositionException { 
	  BTPos<E> vv = checkPosition(v);
	  return vv.index() == 1;
	}
	/** Returns whether v has a left child. */
	public boolean hasLeft(Position<E> v) throws InvalidPositionException { 
	  BTPos<E> vv = checkPosition(v);
	  return 2*vv.index() <= size();
	}
	/** Returns whether v has a right child. */
	public boolean hasRight(Position<E> v) throws InvalidPositionException { 
	  BTPos<E> vv = checkPosition(v);
	  return 2*vv.index() + 1 <= size();
	}
	/** Returns the root of the tree. */
	public Position<E> root() throws EmptyTreeException {
	  if (isEmpty()) throw new EmptyTreeException("Tree is empty");
	  return T.get(1);
	} 
	/** Returns the left child of v. */
	public Position<E> left(Position<E> v) 
	  throws InvalidPositionException, BoundaryViolationException { 
	  if (!hasLeft(v)) throw new BoundaryViolationException("No left child");
	  BTPos<E> vv = checkPosition(v);
	  return T.get(2*vv.index());
	}
	/** Returns the right child of v. 
	 * @throws BoundaryViolationException */ 
	public Position<E> right(Position<E> v) 
	  throws InvalidPositionException, BoundaryViolationException { 
	  if (!hasRight(v)) throw new BoundaryViolationException("No right child");
	  BTPos<E> vv = checkPosition(v);
	  return T.get(2*vv.index() + 1);
	}
	
	
	
	/** Returns the parent of v. */
	public Position<E> parent(Position<E> v) 
	  throws InvalidPositionException, BoundaryViolationException { 
	  if (isRoot(v)) throw new BoundaryViolationException("No parent");
	  BTPos<E> vv = checkPosition(v);
	  return T.get(vv.index()/2);
	}
	/** Replaces the element at v. */
	public E replace(Position<E> v, E o) throws InvalidPositionException {
	  BTPos<E> vv = checkPosition(v);
	  return vv.setElement(o);
	}
	/** Adds an element just after the last node (in a level numbering). */
	public Position<E> add(E e) {
	  int i = size() + 1;
	  BTPos<E> p = new BTPos<E>(e,i);
	  T.add(i, p);
	  return p;
	}
	/** Removes and returns the element at the last node. */
	public E remove() {
	  if(isEmpty())
		try {
			throw new EmptyTreeException("Tree is empty");
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return T.remove(size()).element(); 
	}
	/** Determines whether the given position is a valid node. */
	protected BTPos<E> checkPosition(Position<E> v) 
	  throws InvalidPositionException 
	{
	  //if (v == null || !(v instanceof BTPos))
		 if (v == null )
	    throw new InvalidPositionException("Position is invalid");
	  return (BTPos<E>) v;
	}
	/** Returns an iterator of the elements stored at all nodes in the tree. */
	public Iterator<E> iterator() { 
	  ArrayList<E> list = new ArrayList<E>();
	  Iterator<BTPos<E>> iter = T.iterator();
	  iter.next(); // skip the first element
	  while (iter.hasNext())
	    list.add(iter.next().element());
	  return list.iterator();
	}
	@Override
	public boolean isLeftChild(Position<E> v) throws InvalidPositionException,
			BoundaryViolationException {
		if(isRoot(v))throw new BoundaryViolationException("is root, has no parent");
		if(left(parent(v)) == v){return true;}
		else return false;
	}
	@Override
	public boolean isRightChild(Position<E> v) throws InvalidPositionException,
			BoundaryViolationException {
		return !isLeftChild(v);
	}
	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		PositionList<Position<E>> pl = new NodePositionList<Position<E>>();
		for(int i=1; i<=size();i++){
			pl.addLast(T.get(i));
		}
		return pl;
	}
	@Override
	public Iterable<Position<E>> children(Position<E> v)
			throws InvalidPositionException {
		List<Position<E>> pl = new LinkedList<Position<E>>();
		int index = 0;
		if(hasLeft(v))
				pl.add(index++, v);
		if(hasRight(v))
				pl.add(index++, v);
		return pl;
	} 
	public String toString(){
		//remove the first empty value as index 0;
		List<BTPos> al = new ArrayList<BTPos>(T.subList(1, T.size()));
		return al.toString();
	}
} 