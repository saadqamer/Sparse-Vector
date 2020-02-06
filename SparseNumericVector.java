package A1Q1;

import java.util.*;



/**
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value.  The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like
 * inner products (projections).  Note that location indices can be any integer
 * from 1 to Long.MAX_VALUE.  The representation is based upon a
 * singly-linked list.
 * The following methods are supported:  iterator, getSize, getFirst,
 * add, remove, and dot, which takes the dot product of the with a second vector
 * passed as a parameter.
 */
public class SparseNumericVector implements Iterable {

    protected SparseNumericNode head = null;
    protected SparseNumericNode tail = null;
    protected long size = 0;

  /**
     * Iterator
     */
    @Override
    public Iterator<SparseNumericElement> iterator() { //iterator
        return new SparseNumericIterator(this);
    }

    /**
     * @return number of non-zero elements in vector
     */
   public long getSize() {
        return size;
    }

     /**
     * @return the first node in the list.
     */
    public SparseNumericNode getFirst() {
        return head;
    }
    
    /**
     * Add the element to the vector.  It is inserted to maintain the
     * vector in increasing order of index.  If the element has zero value, or if 
     * an element with the same index already exists, an UnsupportedOperationException is thrown.
     *  
     * @param e element to add
     * @exception UnsupportedoperationException to throw if value of of element is 0, or if index already exists
     */
    public void add(SparseNumericElement e) throws UnsupportedOperationException
  	{
    	SparseNumericNode current = head;	//temporary node to get head node
    	SparseNumericNode newNode = new SparseNumericNode(e,null);	//creation of new node with given element
    	
    	
    	if(e.getValue() == 0) //if value of element is 0 throw exception
    	{
    		throw new UnsupportedOperationException();
    	}
    	//if list is empty or if element to enter list is less then the head
    	else if(head == null || (head.getElement().getIndex() > newNode.getElement().getIndex())) 
    	{
    		newNode.setNext(head);
    		head = newNode;
    		
    		if(size==0)
    		{
    			tail = head;
    		}
    		size++;
    		
    	}
    	else //if index exists already throw exception
    	{
    		
    		while(current.getNext() != null && (current.getNext().getElement().getIndex() <= newNode.getElement().getIndex()))
    		{
    			if(current.getNext().getElement().getIndex() == newNode.getElement().getIndex())
    			{
    				throw new UnsupportedOperationException();
    			}
    			current = current.getNext();
    		}
    		
    		newNode.setNext(current.getNext());
			current.setNext(newNode);
			size++;
			if(current.getNext() == null)
			{
				tail = current; //tail points to last element added
			}
    		
    	}
    	
    	
       
    }

    /**
     * If an element with the specified index exists, it is removed and the
     * method returns true.  If not, it returns false.
     *
     * @param index of element to remove
     * @return true if removed, false if does not exist
     */
    public boolean remove(Long index) 
    {
    	SparseNumericNode previous = head; //keep track of previous node of current
    	if(head == null)	//if list is empty return false
    	{
    		return false; 
    	}
    	else if(head.getElement().getIndex() == index)//if element to remove is head of list
    	{
    		previous = previous.getNext();
    		head = previous.getNext();
    		size--;
    		return true;
    	}
    	else
    	{//if element to remove is somewhere in the list besides the head
    	
    		while(previous.getNext() != null && (previous.getNext().getElement().getIndex() != index))
    		{
    			previous = previous.getNext();
    		}
    			
    		if(previous.getNext() == null)
    		{
    			return false;
    		}
    		
    		previous.setNext(previous.getNext().getNext());
	    	size--;
	    	return true;
			
    		
    	}
    	
    	
    }

    /**
     * Returns the inner product of the vector with a second vector passed as a
     * parameter.  The vectors are assumed to reside in the same space.
     * Runs in O(m+n) time, where m and n are the number of non-zero elements in
     * each vector.
     * @param Y Second vector with which to take inner product
     * @return result of inner product
     */

    public double dot (SparseNumericVector Y) {
        
        double dott = 0.0; //initiate dot product value
        
        
        SparseNumericNode currentx; //temporary node to keep track of head of one list
        SparseNumericNode currenty = Y.head;	//temporary node to keep track head of other list
       
        while(currenty!=null)
        {
        	currentx = this.head;	//keep track of head on one list
        	while(currentx!=null)
        	{
        		//if element being looked at is equivalent to element on the other list in terms of index calculate dot product
        		if(currentx.getElement().getIndex() == currenty.getElement().getIndex())
        		{
        			
        			dott+= currentx.getElement().getValue()*currenty.getElement().getValue();
        		}
        		currentx = currentx.getNext();
        	}
        	//System.out.println(currenty.getElement().getIndex());
        	currenty = currenty.getNext();
        	
        }
        return dott;
   }

       /**
     * returns string representation of sparse vector
     */

    @Override
    public String toString() {
        String sparseVectorString = "";
        Iterator<SparseNumericElement> it = iterator();
        SparseNumericElement x;
        while (it.hasNext()) {
            x = it.next();
            sparseVectorString += "(index " + x.getIndex() + ", value " + x.getValue() + ")\n";
        }
        return sparseVectorString;
    }
}
