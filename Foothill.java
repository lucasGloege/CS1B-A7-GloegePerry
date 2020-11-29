import java.text.*;
import java.util.*;

//------------------------------------------------------
public class Foothill
{
   // ------- main --------------
   public static void main(String[] args) throws Exception
   {
      FHsdTree<String> sceneTree = new FHsdTree<String>();
      FHsdTreeNode<String> tn;

      System.out.println("Starting tree empty? " + sceneTree.empty() + "\n");
      // create a scene in a room
      tn = sceneTree.addChild(null, "room");

      // add three objects to the scene tree
      sceneTree.addChild(tn, "Lily the canine");
      sceneTree.addChild(tn, "Miguel the human");
      sceneTree.addChild(tn, "table");
      // add some parts to Miguel
      tn = sceneTree.find("Miguel the human");

      // Miguel's left arm
      tn = sceneTree.addChild(tn, "torso");
      tn = sceneTree.addChild(tn, "left arm");
      tn = sceneTree.addChild(tn, "left hand");
      sceneTree.addChild(tn, "thumb");
      sceneTree.addChild(tn, "index finger");
      sceneTree.addChild(tn, "middle finger");
      sceneTree.addChild(tn, "ring finger");
      sceneTree.addChild(tn, "pinky");

      // Miguel's right arm
      tn = sceneTree.find("Miguel the human");
      tn = sceneTree.find(tn, "torso", 0);
      tn = sceneTree.addChild(tn, "right arm");
      tn = sceneTree.addChild(tn, "right hand");
      sceneTree.addChild(tn, "thumb");
      sceneTree.addChild(tn, "index finger");
      sceneTree.addChild(tn, "middle finger");
      sceneTree.addChild(tn, "ring finger");
      sceneTree.addChild(tn, "pinky");

      // add some parts to Lily
      tn = sceneTree.find("Lily the canine");
      tn = sceneTree.addChild(tn, "torso");
      sceneTree.addChild(tn, "right front paw");
      sceneTree.addChild(tn, "left front paw");
      sceneTree.addChild(tn, "right rear paw");
      sceneTree.addChild(tn, "left rear paw");
      sceneTree.addChild(tn, "spare mutant paw");
      sceneTree.addChild(tn, "wagging tail");

      // add some parts to table
      tn = sceneTree.find("table");
      sceneTree.addChild(tn, "north east leg");
      sceneTree.addChild(tn, "north west leg");
      sceneTree.addChild(tn, "south east leg");
      sceneTree.addChild(tn, "south west leg");

      System.out.println("\n------------ Loaded Tree ----------------- \n");
      sceneTree.display();
      sceneTree.remove("spare mutant paw");
      sceneTree.remove("Miguel the human");
      sceneTree.remove("an imagined higgs boson");
      System.out.println("\n------------ Virtual (soft) Tree --------------- \n");
      sceneTree.display();
      System.out.println("\n----------- Physical (hard) Display ------------- \n");
      sceneTree.displayPhysical();
      System.out.println("------- Testing Sizes (compare with above) -------- \n");
      System.out.println("virtual (soft) size: " + sceneTree.size());
      System.out.println("physical (hard) size: " + sceneTree.sizePhysical());
      System.out.println("------------ Collecting Garbage ---------------- \n");
      System.out.println("found soft-deleted nodes? " + sceneTree.collectGarbage());
      System.out.println("immediate collect again? " + sceneTree.collectGarbage());

      System.out.println("--------- Hard Display after garbage collection ------------ \n");

      sceneTree.displayPhysical();

      System.out.println("Semi-deleted tree empty? " + sceneTree.empty() + "\n");
      sceneTree.remove("room");
      System.out.println("Completely-deleted tree empty? " + sceneTree.empty() + "\n");

      sceneTree.display();
   }
}

class FHsdTreeNode<E>
{
   // use protected access so the tree, in the same package,
   // or derived classes can access members
   protected FHsdTreeNode<E> firstChild, sib, prev;
   protected E data;
   protected FHsdTreeNode<E> myRoot; // needed to test for certain error
   protected boolean deleted; // true if the node has been removed from the tree

   public FHsdTreeNode(E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld, FHsdTreeNode<E> prv, boolean delete)
   {
      firstChild = chld;
      sib = sb;
      prev = prv;
      data = d;
      myRoot = null;
      deleted = delete;
   }

   public FHsdTreeNode()
   {
      this(null, null, null, null, false);
   }

   public E getData()
   {
      return data;
   }

   // for use only by FHtree (default access)
   protected FHsdTreeNode(E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld, FHsdTreeNode<E> prv, FHsdTreeNode<E> root)
   {
      this(d, sb, chld, prv, false);
      myRoot = root;
   }
}

class FHsdTree<E> implements Cloneable
{
   protected int mSize;
   protected FHsdTreeNode<E> mRoot;
   protected boolean garbageCollected;
   
   public FHsdTree()
   {
      clear();
   }

   public boolean empty()
   {
      return (mSize == 0);
   }

   public int size(FHsdTreeNode<E> root)
   {
      if(mRoot == null || mRoot.deleted == true)
      {
         return 0;
      }
      return getSoftSize(root, 0, 0);
   }
   
   public int size()
   {
      if(mRoot == null || mRoot.deleted == true)
      {
         return 0;
      }
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
               
      }
      return count;
   }

   public void clear()
   {
      mSize = 0;
      mRoot = null;
      garbageCollected = false;
   }

   public FHsdTreeNode<E> find(E x)
   {
      return find(mRoot, x, 0);
   }

   public boolean remove(E x)
   {
      if(x == null)
         return false;
      
      if(find(x) == null)
         return false;
      
      if(find(x).deleted == true)
         return false;
      
      return remove(mRoot, x);
   }

   public boolean remove(FHsdTreeNode<E> root, E x)
   {
      FHsdTreeNode<E> tn;
      
      if (mSize == 0 || root == null)
         return false;
      
      if(find(mRoot, x, 0) == null)
         return false; 
      
      if(mRoot.data == x)
      {
         mSize = 0;
         removeNode(mRoot); 
         return true;
      }
      
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

      if(nodeToDelete == mRoot)
      {
         mRoot = null;
         mSize = 0; 
         return;
      }
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
         output += " (D)";
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
         mRoot = new FHsdTreeNode<E>(x, null, null, null, false);
         mRoot.myRoot = mRoot;
         mSize = 1;
         return mRoot;
      }
      if (treeNode == null)
         return null; // error inserting into non_null tree with a null parent
      if (treeNode.myRoot != mRoot)
         return null; // silent error, node does not belong to this tree
      if(treeNode.deleted == true)
         return null;
      
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
            return null;

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
      newNode = new FHsdTreeNode<E>(root.data, cloneSubtree(root.sib), cloneSubtree(root.firstChild), null, false);

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
      if(treeNode == null)
         return;
      
      if(treeNode.deleted)
      {
         traverse(func, treeNode.sib, level);
         return;
      }
      
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
      //if(garbageCollected)
        // return false;
      //else
        // garbageCollected = true;
      return collectGarbage(mRoot, 0);
   }
   
   public boolean collectGarbage(FHsdTreeNode<E> root)
   {
      return collectGarbage(root,0);
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
         {
            removeNode(root.firstChild);
         }
        
      if(root.sib != null)
         if(level > 0 && root.sib.deleted != true)
            collectGarbage(root.sib, level);
         else if(root.sib.deleted == true)
         {
            removeNode(root.sib);
         }
      return true;
   }
}
/*
Run:
Starting tree empty? true


------------ Loaded Tree ----------------- 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Miguel the human
  torso
   right arm
    right hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
   left arm
    left hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
 Lily the canine
  torso
   wagging tail
   spare mutant paw
   left rear paw
   right rear paw
   left front paw
   right front paw

------------ Virtual (soft) Tree --------------- 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw

----------- Physical (hard) Display ------------- 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Miguel the human (D)
  torso
   right arm
    right hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
   left arm
    left hand
     pinky
     ring finger
     middle finger
     index finger
     thumb
 Lily the canine
  torso
   wagging tail
   spare mutant paw (D)
   left rear paw
   right rear paw
   left front paw
   right front paw
------- Testing Sizes (compare with above) -------- 

virtual (soft) size: 13
physical (hard) size: 30
------------ Collecting Garbage ---------------- 

found soft-deleted nodes? true
immediate collect again? true
--------- Hard Display after garbage collection ------------ 

room
 table
  south west leg
  south east leg
  north west leg
  north east leg
 Lily the canine
  torso
   wagging tail
   left rear paw
   right rear paw
   left front paw
   right front paw
Semi-deleted tree empty? false

Completely-deleted tree empty? true

*/
