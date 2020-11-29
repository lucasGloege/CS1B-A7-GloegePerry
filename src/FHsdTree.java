public class FHsdTree<E> implements Cloneable
{
   protected int mSize;
   protected FHsdTreeNode<E> mRoot;
   
   public FHsdTree()
   {
      clear();
   }

   public boolean empty()
   {
      return (mSize == 0);
   }

   public int size()
   {
      return getSoftSize(mRoot, 0, 0);
   }
   
   public int getSoftSize(FHsdTreeNode<E> parent, int level, int count)
   {
      if(parent != null)
      {
         if(parent.deleted == false)
         {
            count++;
            count = getSoftSize(parent.firstChild, level + 1, count);
         }
         
         if (level > 0)
            count = getSoftSize(parent.sib, level, count); 
               //if(parent.sib.sib != null)
                 // count = getSoftSize(parent.sib.sib, level, count);    
               
      }
      return count;
   }

   public void clear()
   {
      mSize = 0;
      mRoot = null;
   }

   public FHsdTreeNode<E> find(E x)
   {
      return find(mRoot, x, 0);
   }

   public boolean remove(E x)
   {
      return remove(mRoot, x);
   }

   public boolean remove(FHsdTreeNode<E> root, E x)
   {
      FHsdTreeNode<E> tn = null;
      
      if(root.data == x)
      {
         mSize = 0;
         removeNode(mRoot); 
         return true;
      }
      
      
      if (mSize == 0 || root == null)
         return false;

      if ((tn = find(root, x, 0)) != null)
      {
         tn.deleted = true; 
         return true;
      }
      return false;
   }
   
   private void removeNode(FHsdTreeNode<E> nodeToDelete)
   { 
      if (nodeToDelete == null || mRoot == null)
         return;
      if (nodeToDelete.myRoot != mRoot)
         return; // silent error, node does not belong to this tree

      // remove all the children of this node
      while (nodeToDelete.firstChild != null)
         removeNode(nodeToDelete.firstChild);

      if (nodeToDelete.prev == null)
         mRoot = null; // last node in tree
      else if (nodeToDelete.prev.sib == nodeToDelete)
         nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
      else
         nodeToDelete.prev.firstChild = nodeToDelete.sib; // adjust parent

      // adjust the successor sib's prev pointer
      if (nodeToDelete.sib != null)
         nodeToDelete.sib.prev = nodeToDelete.prev; 
   }
   
   
   public void display()
   {
      display(mRoot, 0);
   }
   
   //Does NOT print out deleted nodes
   public void display(FHsdTreeNode<E> treeNode, int level)
   {
      String indent;

      // stop runaway indentation/recursion
      if (level > (int) blankString.length() - 1)
      {
         System.out.println(blankString + " ... ");
         return;
      }

      if (treeNode == null)
         return;

      if(treeNode.deleted != true)
      {
         indent = blankString.substring(0, level);

         // pre-order processing done here ("visit")
         System.out.println(indent + treeNode.data);
      }
      // recursive step done here
      if(treeNode.firstChild != null)
         if(treeNode.firstChild.deleted != true && treeNode.deleted != true)
            display(treeNode.firstChild, level + 1);
            
      if(treeNode.sib != null)
         if(level > 0 && treeNode.sib.deleted != true)
            display(treeNode.sib, level);
         else if(treeNode.sib.deleted)
            display(treeNode.sib,level);
   }

   public void displayPhysical()
   {
      displayPhysical(mRoot, 0); 
   }
   
   //Prints out deleted nodes as well
   public void displayPhysical(FHsdTreeNode<E> treeNode, int level)
   {
      String indent;

      // stop runaway indentation/recursion
      if (level > (int) blankString.length() - 1)
      {
         System.out.println(blankString + " ... ");
         return;
      }

      if (treeNode == null)
         return;

      indent = blankString.substring(0, level);

      // pre-order processing done here ("visit")
      String output = indent + treeNode.data; 
      if(treeNode.deleted == true)
         output += " (D) ";
      System.out.println(output);
      
      // recursive step done here
      displayPhysical(treeNode.firstChild, level + 1);
      if (level > 0)
         displayPhysical(treeNode.sib, level);
   }
   
   public <F extends Traverser<? super E>> void traverse(F func)
   {
      traverse(func, mRoot, 0);
   }

   public FHsdTreeNode<E> addChild(FHsdTreeNode<E> treeNode, E x)
   {
      // empty tree? - create a root node if user passes in null
      if (mSize == 0)
      {
         if (treeNode != null)
            return null; // error something's fishy. treeNode can't right
         mRoot = new FHsdTreeNode<E>(x, null, null, null);
         mRoot.myRoot = mRoot;
         mSize = 1;
         return mRoot;
      }
      if (treeNode == null)
         return null; // error inserting into non_null tree with a null parent
      if (treeNode.myRoot != mRoot)
         return null; // silent error, node does not belong to this tree

      // push this node into the head of the sibling list; adjust prev pointers
      FHsdTreeNode<E> newNode = new FHsdTreeNode<E>(x, treeNode.firstChild, null, treeNode, mRoot); // sb, chld, prv, rt
      treeNode.firstChild = newNode;
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      ++mSize;
      return newNode;
   }

   public FHsdTreeNode<E> find(FHsdTreeNode<E> root, E x, int level)
   {
      FHsdTreeNode<E> retval;

      if (mSize == 0 || root == null)
         return null;

      if (root.data.equals(x))
         if(root.deleted != true)
            return root;
         else
            return root.prev;

      // otherwise, recurse. don't process sibs if this was the original call
      if (level > 0 && (retval = find(root.sib, x, level)) != null)
         return retval;
      
      return find(root.firstChild, x, ++level);
   }

   public Object clone() throws CloneNotSupportedException
   {
      FHsdTree<E> newObject = (FHsdTree<E>) super.clone();
      newObject.clear(); // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      newObject.setMyRoots(newObject.mRoot);

      return newObject;
   }

   private FHsdTreeNode<E> cloneSubtree(FHsdTreeNode<E> root)
   {
      FHsdTreeNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new FHsdTreeNode<E>(root.data, cloneSubtree(root.sib), cloneSubtree(root.firstChild), null);

      // the prev pointer is set by parent recursive call ... this is the code:
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      if (newNode.firstChild != null)
         newNode.firstChild.prev = newNode;
      return newNode;
   }

   // recursively sets all myRoots to mRoot
   private void setMyRoots(FHsdTreeNode<E> treeNode)
   {
      if (treeNode == null)
         return;

      treeNode.myRoot = mRoot;
      setMyRoots(treeNode.sib);
      setMyRoots(treeNode.firstChild);
   }

   // define this as a static member so recursive display() does not need
   // a local version
   final static String blankString = "                                    ";

   // let be public so client can call on subtree
   

   // often helper of typical public version, but also callable by on subtree
   public <F extends Traverser<? super E>> void traverse(F func, FHsdTreeNode<E> treeNode, int level)
   {
      if (treeNode == null)
         return;

      func.visit(treeNode.data);

      // recursive step done here
      traverse(func, treeNode.firstChild, level + 1);
      if (level > 0)
         traverse(func, treeNode.sib, level);
   }

   // Prints out tree, INCLUDING deleted nodes
   

   public int sizePhysical()
   {
      return mSize;  
   }

   public boolean collectGarbage()
   {
      return collectGarbage(mRoot, 0);
   }
   
   public boolean collectGarbage(FHsdTreeNode<E> root, int level)
   {
      if(root.deleted == true)
      {
         removeNode(root);
         return true;
      }
      
      if(root.firstChild != null)
         if(root.firstChild.deleted != true)
            collectGarbage(root.firstChild, level + 1);
         else  
            removeNode(root.firstChild);
        
      if(root.sib != null)
         if(level > 0 && root.sib.deleted != true)
            collectGarbage(root.sib, level);
         else if(root.sib.deleted == true)
            removeNode(root.sib);
      
      return true;
   }
}