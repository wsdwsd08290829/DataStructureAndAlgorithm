package com.sidausc.ds.listanditerators;




/** List of favorite elements, with their access counts. */
public class FavoriteList<E>{
  protected PositionList<Entry<E>> fList;	// List of entries
  /** Constructor; O(1) time */
  public FavoriteList() { fList = new NodePositionList<Entry<E>>(); }
  /** Returns the number of elements in the list; O(1) time */
  public int size() { return fList.size(); }
  /** Returns whehter the list is empty; O(1) time */
  public boolean isEmpty() { return fList.isEmpty(); }
  /** Removes a given element, provided it is in the list; O(n) time 
 * @throws InvalidPositionException 
 * @throws EmptyListException */
  public void remove(E obj) throws InvalidPositionException, EmptyListException {
    Position<Entry<E>> p = find(obj);	// search for obj
    if (p != null)
      fList.remove(p);		// remove the entry
    }
  /** Increments the access count for the given element and inserts it
    * if it is not already present; O(n) time 
 * @throws InvalidPositionException 
 * @throws EmptyListException 
 * @throws BoundaryViolationException */
  public void access(E obj) throws InvalidPositionException, EmptyListException, BoundaryViolationException {
    Position<Entry<E>> p = find(obj);	// find the position of obj
    if (p != null)
      p.element().incrementCount();	// increment access count
    else {
      fList.addLast(new Entry<E>(obj));	// add the new entry at the end
      p = fList.last();
    }
    moveUp(p); 		// moves the entry to its final position
    }
  /** Returns the k most accessed elements, for a given k;  O(k) time 
 * @throws InvalidPositionException 
 * @throws EmptyListException */
  public Iterable<E> top(int k) throws InvalidPositionException, EmptyListException {
    if (k < 0 || k > size())
      throw new IllegalArgumentException("Invalid argument");
    PositionList<E> T = new NodePositionList<E>(); // top-k list
    int i = 0;	// counter of the entries added to the list
    for (Entry<E> e: fList) {
      if (i++ >= k)
	break;		// all the k entries have been added
      T.addLast(e.value());	// add one entry to the list
    }
    return T;
  }
  /** String representation of the favorite list */
  public String toString() { return fList.toString(); }
  /** Helper method that extracts the value of the entry at a given position 
 * @throws InvalidPositionException */
  protected E value(Position<Entry<E>> p) throws InvalidPositionException { return ( p.element()).value(); }
  /** Helper method that extracts the counter of the entry at a given position 
 * @throws InvalidPositionException */
  protected int count(Position<Entry<E>> p) throws InvalidPositionException { return ( p.element()).count(); }
  /** Finds the position of a given element, or returns null; O(n) time 
 * @throws EmptyListException 
 * @throws InvalidPositionException */
  protected Position<Entry<E>> find(E obj) throws InvalidPositionException {
  //  for (Position<Entry<E>> p: fList.positions())  //??????????????????
   //   if (value(p).equals(obj)) 
	try{
		if(fList != null){
		  for(DNode<Entry<E>> temp = (DNode<Entry<E>>) fList.first(); temp !=null; temp = temp.getNext()){
			  if(temp.element() != null){
				  if(temp.element().value.equals(obj))return temp;
			  }
		  }
		}
	}catch(EmptyListException e){
		
	}
	return null;
  }
  /** Moves up an entry to its correct position in the list;  O(n) time 
 * @throws InvalidPositionException 
 * @throws BoundaryViolationException 
 * @throws EmptyListException */
  protected void moveUp(Position<Entry<E>> cur) throws InvalidPositionException, EmptyListException, BoundaryViolationException {
    Entry<E> e = cur.element();
    int c = count(cur);
    while (cur != fList.first()) {
      Position<Entry<E>> prev = fList.prev(cur);	// previous position
      if (c <=  count(prev)) break;	// entry is at correct position
      fList.set(cur, prev.element());	// move down previous entry
      cur = prev;
    }
    fList.set(cur, e);	// store the entry in its final position
  }
  /** Inner class storing elements and  their access counts. */
  protected static class Entry<E> {
    private E value;	// element
    private int count;	// access count
    /** Constructor */
    Entry(E v) { count = 1; value = v; }
    /** Returns the element */
    public E value() { return value; }
    /** Returns the access count */
    public int count() { return count; }
    /** Increments the access count */
    public int incrementCount() { return ++count; }
    /** String representation of the entry as [count,value] */
    public String toString() { return "[" + count + "," + value + "]"; }
  }
} // End of FavoriteList class