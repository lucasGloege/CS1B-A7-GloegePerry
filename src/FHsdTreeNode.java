public class FHsdTreeNode<E>
{
   // use protected access so the tree, in the same package,
   // or derived classes can access members
   protected FHsdTreeNode<E> firstChild, sib, prev;
   protected E data;
   protected FHsdTreeNode<E> myRoot; // needed to test for certain error
   protected boolean deleted; // true if the node has been removed from the tree

   public FHsdTreeNode(E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld, FHsdTreeNode<E> prv)
   {
      firstChild = chld;
      sib = sb;
      prev = prv;
      data = d;
      myRoot = null;
      deleted = false;
   }

   public FHsdTreeNode()
   {
      this(null, null, null, null);
   }

   public E getData()
   {
      return data;
   }

   // for use only by FHtree (default access)
   protected FHsdTreeNode(E d, FHsdTreeNode<E> sb, FHsdTreeNode<E> chld, FHsdTreeNode<E> prv, FHsdTreeNode<E> root)
   {
      this(d, sb, chld, prv);
      myRoot = root;
   }
}
