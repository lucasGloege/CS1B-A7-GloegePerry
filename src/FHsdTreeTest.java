
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import org.junit.*;
import org.junit.tools.configuration.base.*;

import mockit.Deencapsulation;

import java.util.logging.Logger;

//import javax.annotation.Generated;

//@Generated(value = "org.junit-tools-1.0.6")
public class FHsdTreeTest
{

//	@Generated(value = "org.junit-tools-1.0.6")
    private Logger logger = Logger.getLogger(FHsdTreeTest.class.toString());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream out = System.out;
    private final PrintStream err = System.err;
    private final String lineSeparator = System.lineSeparator();

    @Before
    public void setUp() throws Exception
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {
        System.setOut(out);
        System.setErr(err);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {

    }

    private FHsdTree<Card> createTestSubject()
    {
        return new FHsdTree<Card>();
    }

    @MethodRef(name = "sizePhysical", signature = "()I")
    @Test
    public void testSizePhysical() throws Exception
    {
        FHsdTree<Card> testSubject;
        int result;

        // default test
        testSubject = createTestSubject();
        result = testSubject.sizePhysical();
        Assert.assertEquals(0, result);
        FHsdTreeNode<Card> root = testSubject.addChild(null, new Card());
        result = testSubject.sizePhysical();
        Assert.assertEquals(1, result);
        FHsdTreeNode<Card> newNode = testSubject.addChild(root, new Card());
        result = testSubject.sizePhysical();
        Assert.assertEquals(2, result);
        testSubject.remove(root, newNode.getData());
        Assert.assertEquals(2, result);
    }

    @MethodRef(name = "size", signature = "()I")
    @Test
    public void testSize() throws Exception
    {
        FHsdTree<Card> testSubject;
        int result;

        // default test
        testSubject = createTestSubject();
        result = testSubject.size();
        Assert.assertEquals(0, result);

        // adding and removing tests
        testSubject = createTestSubject();
        FHsdTreeNode<Card> root = null;
        Card x = new Card();
        FHsdTreeNode<Card> newNode = testSubject.addChild(null, x);
        Assert.assertNotNull(newNode);
        result = testSubject.size();
        Assert.assertEquals(1, result);
        root = newNode;
        x = new Card('2', Card.Suit.diamonds);
        newNode = testSubject.addChild(root, x);
        Assert.assertNotNull(newNode);
        result = testSubject.size();
        Assert.assertEquals(2, result);
        boolean success = testSubject.remove(root.getData());
        Assert.assertEquals(true, success);
        Assert.assertEquals("The tree should be 'empty' now.", true, testSubject.empty());
        result = testSubject.size();
        Assert.assertEquals(0, result);
    }

    @MethodRef(name = "clear", signature = "()V")
    @Test
    public void testClear() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> treeNode = null;
        int level = 0;

        // test 1
        testSubject = createTestSubject();
        treeNode = testSubject.addChild(null, new Card());
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { treeNode, level });
        Assert.assertEquals("A of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        // test 2
        FHsdTreeNode<Card> root = treeNode;
        treeNode = testSubject.addChild(root, new Card('2', Card.Suit.spades));
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        // test 3
        level = -1;
        try
        {
            Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
            { root, level });
        } catch (java.lang.StringIndexOutOfBoundsException e)
        {
            // e.printStackTrace();
            Assert.assertNotNull("StringIndexOutOfBoundsException thown as expected " + "due to nagative level"
                    + lineSeparator + "error:" + e.getLocalizedMessage(), e);
        }
        Assert.assertEquals("", outContent.toString());
        outContent.reset();

        // more tests after adding more nodes
        FHsdTreeNode<Card> newNode = testSubject.addChild(treeNode, new Card('9', Card.Suit.spades));
        newNode = testSubject.addChild(treeNode, new Card('J', Card.Suit.spades));
        level = 0;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "  J of spades"
                + lineSeparator + "  9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        testSubject.remove(newNode.getData());
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "   J of spades (D)"
                + lineSeparator + "   9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        testSubject.clear();

        Assert.assertEquals(0, testSubject.sizePhysical());
        Assert.assertNull(testSubject.mRoot);
    }

    @MethodRef(name = "find", signature = "(QE;)QFHsdTreeNode<QE;>;")
    @Test
    public void testFind() throws Exception
    {
        FHsdTree<Card> testSubject;
        Card x = new Card();
        FHsdTreeNode<Card> result;

        // default tests
        testSubject = createTestSubject();
        result = testSubject.find(x);
        Assert.assertNull(result);

        result = testSubject.addChild(null, x);
        Assert.assertNotNull(result);
        result = testSubject.find(x);
        Assert.assertEquals(x, result.getData());

        result = testSubject.addChild(result, x);
        Assert.assertNotNull(result);
        result = testSubject.find(x);
        Assert.assertEquals(x, result.getData());

        x = new Card('2', Card.Suit.diamonds);
        result = testSubject.addChild(null, x);
        Assert.assertNull("Nobody should be able to add into a non-null tree with a null parent, "
                + "so the 2/diamonds should not have been added", result);
        result = testSubject.find(x);
        Assert.assertNull("Nobody should be able to add into a non-null tree with a null parent, "
                + "so the 2/diamonds should not have been added", result);

        result = testSubject.addChild(testSubject.mRoot, x);
        result = testSubject.find(x);
        Assert.assertEquals(x, result.getData());
        result = testSubject.find(x);
        Assert.assertEquals(x, result.getData());
    }

    @MethodRef(name = "remove", signature = "(QE;)Z")
    @Test
    public void testRemove() throws Exception
    {
        FHsdTree<Card> testSubject;
        Card x = null;
        boolean result;

        // default test
        testSubject = createTestSubject();
        result = testSubject.remove(x);
        Assert.assertEquals(false, result);

        // tests
        testSubject = createTestSubject();
        FHsdTreeNode<Card> root = null;
        x = new Card();
        FHsdTreeNode<Card> newNode = testSubject.addChild(null, x);
        Assert.assertNotNull(newNode);
        root = newNode;
        x = new Card('2', Card.Suit.diamonds);
        newNode = testSubject.addChild(root, x);
        Assert.assertNotNull(newNode);
        result = testSubject.remove(x);
        Assert.assertEquals(true, result);
        result = testSubject.remove(x);
        Assert.assertEquals("Cant 'remove' x twice", false, result);
        result = testSubject.remove(root.getData());
        Assert.assertEquals(true, result);
        Assert.assertEquals("The tree should be 'empty' now.", true, testSubject.empty());
    }

    @MethodRef(name = "displayPhysical", signature = "()V")
    @Test
    public void testDisplayPhysical() throws Exception
    {
        FHsdTree<Card> testSubject;

        // default test
        testSubject = createTestSubject();
        testSubject.displayPhysical();
        Assert.assertEquals("", outContent.toString());

        int result = testSubject.sizePhysical();
        Assert.assertEquals(0, result);
        FHsdTreeNode<Card> root = testSubject.addChild(null, new Card());
        result = testSubject.sizePhysical();
        Assert.assertEquals(1, result);
        FHsdTreeNode<Card> newNode = testSubject.addChild(root, new Card('9', Card.Suit.spades));
        boolean successfulRemove = testSubject.remove(newNode.getData());
        Assert.assertTrue(successfulRemove);
        result = testSubject.sizePhysical();
        Assert.assertEquals(2, result);
        newNode = testSubject.addChild(root, new Card('J', Card.Suit.spades));
        result = testSubject.sizePhysical();
        Assert.assertEquals(3, result);
        testSubject.displayPhysical();

        Assert.assertEquals("A of spades" + lineSeparator + " J of spades" + lineSeparator + " 9 of spades (D)"
                + lineSeparator + "", outContent.toString());
    }

    @MethodRef(name = "display", signature = "()V")
    @Test
    public void testDisplay() throws Exception
    {
        FHsdTree<Card> testSubject;

        // default test
        testSubject = createTestSubject();
        testSubject.display();
        Assert.assertEquals("", outContent.toString());
        ;

        int result = testSubject.sizePhysical();
        Assert.assertEquals(0, result);
        FHsdTreeNode<Card> root = testSubject.addChild(null, new Card());
        result = testSubject.sizePhysical();
        Assert.assertEquals(1, result);
        FHsdTreeNode<Card> newNode = testSubject.addChild(root, new Card('9', Card.Suit.spades));
        boolean successfulRemove = testSubject.remove(newNode.getData());
        Assert.assertTrue(successfulRemove);
        result = testSubject.sizePhysical();
        Assert.assertEquals(2, result);
        newNode = testSubject.addChild(root, new Card('J', Card.Suit.spades));
        result = testSubject.sizePhysical();
        Assert.assertEquals(3, result);
        testSubject.display();

        Assert.assertEquals("A of spades" + lineSeparator + " J of spades" + lineSeparator
        // + " 9 of spades (D)" + lineSeparator
                , outContent.toString());
        outContent.reset();

        // test handling of runaway indentation/recursion
        newNode = testSubject.addChild(root, new Card());
        FHsdTreeNode<Card> topNode = newNode;
        Assert.assertNotNull(newNode);
        for (int count = 0; count < 35; count++)
        {
            newNode = testSubject.addChild(newNode, new Card());
            Assert.assertNotNull(newNode);
        }
        result = testSubject.sizePhysical();
        Assert.assertEquals(39, result);
        // now level is 36
        testSubject.display();
        String output = outContent.toString();
        Assert.assertTrue(
                "If this test was not true, you let your indention/recursion go too far "
                        + "because the following output is just too long:" + lineSeparator + output,
                output.length() <= 1155);
        outContent.reset();
    }

    @MethodRef(name = "traverse", signature = "(QF;)V")
    @Test
    public void testTraverse() throws Exception
    {
        FHsdTree<Card> testSubject;
        CardTraverser func = new CardTraverser();
        FHsdTreeNode<Card> treeNode = null;

        // test 1
        System.out.println("test 1");
        testSubject = createTestSubject();
        treeNode = testSubject.addChild(null, new Card());
        testSubject.traverse(func);
        FHsdTreeNode<Card> root = treeNode;

        // test 2
        System.out.println("test 2");
        treeNode = testSubject.addChild(root, new Card('3', Card.Suit.clubs));
        testSubject.traverse(func);

        // test 3
        System.out.println("test 3");
        Card card4clubs = new Card('4', Card.Suit.clubs);
        treeNode = testSubject.addChild(root, card4clubs);
        testSubject.traverse(func);

        // test 4
        System.out.println("test 4");
        Card card5clubs = new Card('5', Card.Suit.clubs);
        treeNode = testSubject.addChild(treeNode, card5clubs);
        testSubject.traverse(func);

        // test 5: 'deleted' node
        System.out.println("test 5: 'deleted' node");
        treeNode = testSubject.addChild(root, new Card('6', Card.Suit.spades));
        testSubject.traverse(func);
        System.out.println("soft removing:" + card4clubs);
        testSubject.remove(card4clubs);
        testSubject.traverse(func);

        Assert.assertEquals("test 1" + lineSeparator + "Card:A of spades" + lineSeparator + "test 2" + lineSeparator
                + "Card:A of spades" + lineSeparator + "Card:3 of clubs" + lineSeparator + "test 3" + lineSeparator
                + "Card:A of spades" + lineSeparator + "Card:4 of clubs" + lineSeparator + "Card:3 of clubs"
                + lineSeparator + "test 4" + lineSeparator + "Card:A of spades" + lineSeparator + "Card:4 of clubs"
                + lineSeparator + "Card:5 of clubs" + lineSeparator + "Card:3 of clubs" + lineSeparator
                + "test 5: 'deleted' node" + lineSeparator + "Card:A of spades" + lineSeparator + "Card:6 of spades"
                + lineSeparator + "Card:4 of clubs" + lineSeparator + "Card:5 of clubs" + lineSeparator
                + "Card:3 of clubs" + lineSeparator + "soft removing:4 of clubs" + lineSeparator + "Card:A of spades"
                + lineSeparator + "Card:6 of spades" + lineSeparator + "Card:3 of clubs" + lineSeparator + "",
                outContent.toString());
    }

    @MethodRef(name = "collectGarbage", signature = "()Z")
    @Test
    public void testCollectGarbage() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> treeNode = null;
        int level = 0;

        // test 1
        testSubject = createTestSubject();
        treeNode = testSubject.addChild(null, new Card());
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { treeNode, level });
        Assert.assertEquals("A of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        // test 2
        FHsdTreeNode<Card> root = treeNode;
        treeNode = testSubject.addChild(root, new Card('2', Card.Suit.spades));
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        // test 3
        level = -1;
        try
        {
            Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
            { root, level });
        } catch (java.lang.StringIndexOutOfBoundsException e)
        {
            // e.printStackTrace();
            Assert.assertNotNull("StringIndexOutOfBoundsException thown as expected " + "due to nagative level"
                    + lineSeparator + "error:" + e.getLocalizedMessage(), e);
        }
        Assert.assertEquals("", outContent.toString());
        outContent.reset();

        // more tests after adding more nodes
        FHsdTreeNode<Card> newNode = testSubject.addChild(treeNode, new Card('9', Card.Suit.spades));
        newNode = testSubject.addChild(treeNode, new Card('J', Card.Suit.spades));
        level = 0;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "  J of spades"
                + lineSeparator + "  9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        testSubject.remove(newNode.getData());
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "   J of spades (D)"
                + lineSeparator + "   9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
//		boolean collected = testSubject.collectGarbage();
        boolean collected = Deencapsulation.invoke(testSubject, "collectGarbage");
        Assert.assertEquals(true, collected);
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator +
        // " J of spades (D)" + lineSeparator +
                "   9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
    }

    @MethodRef(name = "addChild", signature = "(QFHsdTreeNode<QE;>;QE;)QFHsdTreeNode<QE;>;")
    @Test
    public void testAddChild() throws Exception
    {
        FHsdTree<Card> testSubject;
        Card x = new Card();
        FHsdTreeNode<Card> result;

        // test 1
        testSubject = createTestSubject();
        result = testSubject.addChild(null, x);
        Assert.assertNotNull(result);
        FHsdTreeNode<Card> root = result;
        result = testSubject.addChild(root, new Card('3', Card.Suit.clubs));
        Assert.assertNotNull(result);
        FHsdTreeNode<Card> testSubjectNode3clubs = result;

        // test inserting into non-null tree with a null parent
        x = new Card('2', Card.Suit.diamonds);
        result = testSubject.addChild(null, x);
        Assert.assertEquals(null, result);

        // test for adding to an empty tree but using a non-null root
        FHsdTree<Card> testSubject2 = createTestSubject();
        result = testSubject2.addChild(root, x);
        Assert.assertEquals(null, result);

        // test trying to insert a node that does not belong to a tree
        result = testSubject2.addChild(null, new Card());
        Assert.assertNotNull(result);
        // FHsdTreeNode<Card> testSubject2root = result;
        result = testSubject2.addChild(testSubjectNode3clubs, new Card());
        Assert.assertEquals(null, result);

        // test trying to add a child to a soft-deleted subtree
        boolean removed = testSubject.remove(testSubjectNode3clubs.getData());
        Assert.assertEquals(true, removed);
        result = testSubject.addChild(testSubjectNode3clubs, new Card());
        Assert.assertEquals(null, result);
    }

    @MethodRef(name = "find", signature = "(QFHsdTreeNode<QE;>;QE;I)QFHsdTreeNode<QE;>;")
    @Test
    public void testFind_1() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> root = null;
        Card x = new Card();
        int level = 0;
        FHsdTreeNode<Card> result;

        testSubject = createTestSubject();
        root = testSubject.addChild(null, x);
        Assert.assertNotNull(root);
        Card qh = new Card('Q', Card.Suit.hearts);
        FHsdTreeNode<Card> qhNode = testSubject.addChild(root, qh);
        Assert.assertNotNull(qhNode);
        x = new Card('2', Card.Suit.diamonds);
        FHsdTreeNode<Card> xNode = testSubject.addChild(root, x);
        Assert.assertNotNull(xNode);

        // test 1
        result = testSubject.find(root, x, level);
        Assert.assertEquals(xNode, result);

        // test 2
        level = 0;
        result = testSubject.find(null, x, level);
        Assert.assertEquals(null, result);

        // test 3
        level = -10;
        result = testSubject.find(root, x, level);
        Assert.assertEquals(xNode, result);

        // test 4
        level = 1;
        result = testSubject.find(xNode, qh, level);
        Assert.assertEquals(qhNode, result);
        result = testSubject.find(root, x, level);
        Assert.assertEquals(xNode, result);
        FHsdTreeNode<Card> clubsRoot = testSubject.addChild(root, new Card('A', Card.Suit.clubs));
        FHsdTreeNode<Card> clubsLastChild = testSubject.addChild(clubsRoot, new Card('2', Card.Suit.clubs));
        testSubject.addChild(clubsRoot, new Card('3', Card.Suit.clubs));
        testSubject.addChild(clubsRoot, new Card('4', Card.Suit.clubs));
        testSubject.addChild(clubsRoot, new Card('5', Card.Suit.clubs));
        testSubject.addChild(clubsRoot, new Card('6', Card.Suit.clubs));
        testSubject.addChild(clubsRoot, new Card('7', Card.Suit.clubs));
        xNode = testSubject.addChild(clubsRoot, new Card('8', Card.Suit.clubs));
        testSubject.addChild(clubsRoot, new Card('9', Card.Suit.clubs));
        testSubject.display();
        Assert.assertEquals("A of spades" + lineSeparator + // root
                " A of clubs" + lineSeparator + "  9 of clubs" + lineSeparator + "  8 of clubs" + lineSeparator + // xNode
                "  7 of clubs" + lineSeparator + "  6 of clubs" + lineSeparator + "  5 of clubs" + lineSeparator
                + "  4 of clubs" + lineSeparator + "  3 of clubs" + lineSeparator + "  2 of clubs" + lineSeparator + // clubsLastChild
                " 2 of diamonds" + lineSeparator + " Q of hearts" + lineSeparator + "", outContent.toString());

        level = 1;
        result = testSubject.find(root, clubsLastChild.getData(), level);
        Assert.assertEquals(clubsLastChild, result);
        result = testSubject.find(xNode, clubsLastChild.getData(), level);
        Assert.assertEquals("Should be able to find clubsLastChild because level > 0", clubsLastChild, result);
        level = 0;
        result = testSubject.find(xNode, clubsLastChild.getData(), level);
        Assert.assertNull("Shouldn't be able to find clubsLastChild because level = 0", result);

    }

    @MethodRef(name = "remove", signature = "(QFHsdTreeNode<QE;>;QE;)Z")
    @Test
    public void testRemove_1() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> root = null;
        Card x = new Card();
        boolean result;

        // tests
        testSubject = createTestSubject();
        root = null;
        FHsdTreeNode<Card> newNode = testSubject.addChild(null, x);
        Assert.assertNotNull(newNode);
        root = newNode;
        x = new Card('2', Card.Suit.diamonds);
        newNode = testSubject.addChild(root, x);
        Assert.assertNotNull(newNode);
        result = testSubject.remove(root, x);
        Assert.assertEquals(true, result);
        result = testSubject.remove(root, x);
        Assert.assertEquals("Cant 'remove' x twice", false, result);
        result = testSubject.remove(root, root.getData());
        Assert.assertEquals(true, result);
        Assert.assertEquals("The tree should be 'empty' now.", true, testSubject.empty());
    }

    @MethodRef(name = "removeNode", signature = "(QFHsdTreeNode<QE;>;)V")
    @Test
    public void testRemoveNode() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> root = null;
        Card x = new Card();
        boolean result;
        Class[] parameterTypes =
        { FHsdTreeNode.class };

        // tests
        testSubject = createTestSubject();
        root = null;
        FHsdTreeNode<Card> newNode = testSubject.addChild(null, x);
        Assert.assertNotNull(newNode);
        root = newNode;
        x = new Card('2', Card.Suit.diamonds);
        newNode = testSubject.addChild(root, x);
        Assert.assertNotNull(newNode);
        Deencapsulation.invoke(testSubject, "removeNode", newNode);
        FHsdTreeNode<Card> xNode = testSubject.find(x);
        Assert.assertNull("Shouldn't be able to find 2/diamonds in tree", xNode);
        Deencapsulation.invoke(testSubject, "removeNode", root);
        Assert.assertEquals("The tree should be actually empty now.", true, testSubject.empty());

        // test trying to remove from an empty tree
        Deencapsulation.invoke(testSubject, "removeNode", root);
        Assert.assertEquals("The tree should still be empty and no errors were thrown", true, testSubject.empty());

        // test trying to remove node does not belong to this tree
        FHsdTree<Card> testSubject1 = createTestSubject();
        FHsdTreeNode<Card> newNodeFromTestSubject1 = testSubject1.addChild(null, x);
        Assert.assertNotNull(newNode);
        Assert.assertEquals(1, testSubject1.sizePhysical());

        FHsdTree<Card> testSubject2 = createTestSubject();
        FHsdTreeNode<Card> root2 = testSubject2.addChild(null, x);
        Assert.assertNotNull(root2);
        Assert.assertEquals(1, testSubject2.sizePhysical());
        Deencapsulation.invoke(testSubject2, "removeNode", newNodeFromTestSubject1);
        Assert.assertEquals(1, testSubject2.sizePhysical());

    }

    @MethodRef(name = "clone", signature = "()QObject;")
    @Test
    public void testClone() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTree<Card> result;

        // default test
        testSubject = createTestSubject();
        Card x = new Card();
        FHsdTreeNode<Card> root = testSubject.addChild(null, x);
        Assert.assertNotNull(root);
        x = new Card('J', Card.Suit.diamonds);
        FHsdTreeNode<Card> temp = testSubject.addChild(root, x);
        Assert.assertNotNull(temp);
        x = new Card('2', Card.Suit.diamonds);
        temp = testSubject.addChild(temp, x);
        Assert.assertNotNull(temp);

        result = (FHsdTree<Card>) testSubject.clone();
        Assert.assertTrue("Shouldn't be the same object in memory", result != testSubject);
        Assert.assertTrue("Should be of the same class", result.getClass() == testSubject.getClass());
        Assert.assertTrue("Should have the same size", result.size() == testSubject.size());
        Assert.assertTrue("Should have the same internal values", isEqualTreeNodes(result.mRoot, testSubject.mRoot));
    }

    @MethodRef(name = "cloneSubtree", signature = "(QFHsdTreeNode<QE;>;)QFHsdTreeNode<QE;>;")
    @Test
    public void testCloneSubtree() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> root = null;
        FHsdTreeNode<Card> result;
        Card qh = new Card('Q', Card.Suit.hearts);

        // test 1
        testSubject = createTestSubject();
        testSubject.addChild(null, qh);
        root = testSubject.find(qh);
        result = Deencapsulation.invoke(testSubject, "cloneSubtree", new Object[]
        { root });
        Assert.assertEquals(true, isEqualTreeNodes(root, result));

        FHsdTreeNode<Card> newNode = testSubject.addChild(root, new Card());
        result = Deencapsulation.invoke(testSubject, "cloneSubtree", new Object[]
        { newNode });
        Assert.assertEquals(true, isEqualTreeNodes(newNode, result));

        FHsdTreeNode<Card> node2spades = testSubject.addChild(root, new Card('2', Card.Suit.spades));
        result = Deencapsulation.invoke(testSubject, "cloneSubtree", new Object[]
        { node2spades });
        Assert.assertEquals(true, isEqualTreeNodes(node2spades, result));
    }

    /**
     * For Option B-1: FHtreeNode<Card> inputs are casted to FHsdTreeNode<Card>
     * 
     * @param a
     * @param b
     * @return boolean
     */
//	boolean isEqualTreeNodes(FHtreeNode<Card> a, FHtreeNode<Card> b) {
//		return isEqualTreeNodes((FHsdTreeNode<Card>) a, (FHsdTreeNode<Card>) b);
//	}

    boolean isEqualTreeNodes(FHsdTreeNode<Card> a, FHsdTreeNode<Card> b)
    {
        if (a == null)
        {
            return (b == null);
        }
        if (b == null)
        {
            return (a == null);
        }
        if (a.getClass() != b.getClass())
        {
            return false;
        }
        if (a.getData() == null)
        {
            if (b.getData() != null)
            {
                return false;
            }
        } else if (!a.getData().equals(b.getData()))
        {
            return false;
        }

        if (a.firstChild == null)
        {
            if (b.firstChild != null)
            {
                return false;
            }
        } else if (!isEqualTreeNodes(a.firstChild, b.firstChild))
        {
            return false;
        }

        if (a.sib == null)
        {
            if (b.sib != null)
            {
                return false;
            }
        } else if (!isEqualTreeNodes(a.sib, b.sib))
        {
            return false;
        }
        // Ignoring prev to avoid infinite recursion
        // if (a.prev == null) {
        // if (b.prev != null) {
        // return false;
        // }
        // } else if (!isEqualTreeNodes(a.prev,b.prev)) {
        // return false;
        // }

        if (a.data == null)
        {
            if (b.data != null)
            {
                return false;
            }
        } else if (!a.data.equals(b.data))
        {
            return false;
        }

        if (a.deleted != b.deleted)
        {
            return false;
        }

        // No need to check myRoot equality,
        // that only tells us which tree the node is in

        return true;
    }

    @MethodRef(name = "setMyRoots", signature = "(QFHsdTreeNode<QE;>;)V")
    @Test
    public void testSetMyRoots() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> treeNode = null;
        int level = 0;

        // test 1
        testSubject = createTestSubject();
        treeNode = testSubject.addChild(null, new Card());
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { treeNode, level });
        Assert.assertEquals("A of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        // test 2
        FHsdTreeNode<Card> root = treeNode;
        treeNode = testSubject.addChild(root, new Card('2', Card.Suit.spades));
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        // test 3
        level = -1;
        try
        {
            Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
            { root, level });
        } catch (java.lang.StringIndexOutOfBoundsException e)
        {
            // e.printStackTrace();
            Assert.assertNotNull("StringIndexOutOfBoundsException thown as expected " + "due to nagative level"
                    + lineSeparator + "error:" + e.getLocalizedMessage(), e);
        }
        Assert.assertEquals("", outContent.toString());
        outContent.reset();

        // more tests after adding more nodes
        FHsdTreeNode<Card> newNode = testSubject.addChild(treeNode, new Card('9', Card.Suit.spades));
        newNode = testSubject.addChild(treeNode, new Card('J', Card.Suit.spades));
        level = 0;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "  J of spades"
                + lineSeparator + "  9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        treeNode.myRoot = null;
        newNode.myRoot = null;
        root.myRoot = null;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "  J of spades"
                + lineSeparator + "  9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        Deencapsulation.invoke(testSubject, "setMyRoots", new Object[]
        { root });
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "  J of spades"
                + lineSeparator + "  9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        Assert.assertEquals(root, root.myRoot);
        Assert.assertEquals(root, treeNode.myRoot);
        Assert.assertEquals(root, newNode.myRoot);

        // test trying to call setMyRoots on a tree that has a null mRoot
        testSubject = createTestSubject();
        Assert.assertEquals(0, testSubject.sizePhysical());
        testSubject.displayPhysical();
        Assert.assertEquals("", outContent.toString());
        outContent.reset();
        Deencapsulation.invoke(testSubject, "setMyRoots", new Object[]
        { root });
        testSubject.displayPhysical();
        Assert.assertEquals(0, testSubject.sizePhysical());
        Assert.assertEquals("There should be no output and no exceptions throwns", "", outContent.toString());
        outContent.reset();
    }

    @MethodRef(name = "displayPhysical", signature = "(QFHsdTreeNode<QE;>;I)V")
    @Test
    public void testDisplayPhysical_1() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> treeNode = null;
        int level = 0;

        // test 1
        testSubject = createTestSubject();
        treeNode = testSubject.addChild(null, new Card());
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { treeNode, level });
        Assert.assertEquals("A of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        // test 2
        FHsdTreeNode<Card> root = treeNode;
        treeNode = testSubject.addChild(root, new Card('2', Card.Suit.spades));
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        // test 3
        level = -1;
        try
        {
            Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
            { root, level });
        } catch (java.lang.StringIndexOutOfBoundsException e)
        {
            // e.printStackTrace();
            Assert.assertNotNull("StringIndexOutOfBoundsException thown as expected " + "due to nagative level"
                    + lineSeparator + "error:" + e.getLocalizedMessage(), e);
        }
        Assert.assertEquals("", outContent.toString());
        outContent.reset();

        // test 4: using a sub-tree
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { treeNode, level });
        Assert.assertEquals(" 2 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        // more tests after adding more nodes
        FHsdTreeNode<Card> newNode = testSubject.addChild(treeNode, new Card('9', Card.Suit.spades));
        newNode = testSubject.addChild(treeNode, new Card('J', Card.Suit.spades));
        level = 0;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "  J of spades"
                + lineSeparator + "  9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { treeNode, level });
        Assert.assertEquals(
                " 2 of spades" + lineSeparator + "  J of spades" + lineSeparator + "  9 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { newNode, level });
        Assert.assertEquals(" J of spades" + lineSeparator + " 9 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        testSubject.remove(newNode.getData());
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { newNode, level });
        Assert.assertEquals(" J of spades (D)" + lineSeparator + " 9 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();
    }

    @MethodRef(name = "display", signature = "(QFHsdTreeNode<QE;>;I)V")
    @Test
    public void testDisplay_1() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> treeNode = null;
        int level = 0;

        // test 1
        testSubject = createTestSubject();
        treeNode = testSubject.addChild(null, new Card());
        Deencapsulation.invoke(testSubject, "display", new Object[]
        { treeNode, level });
        Assert.assertEquals("A of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        // test 2
        FHsdTreeNode<Card> root = treeNode;
        treeNode = testSubject.addChild(root, new Card('2', Card.Suit.spades));
        Deencapsulation.invoke(testSubject, "display", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "display", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        // test 3
        level = -1;
        try
        {
            Deencapsulation.invoke(testSubject, "display", new Object[]
            { root, level });
        } catch (java.lang.StringIndexOutOfBoundsException e)
        {
            // e.printStackTrace();
            Assert.assertNotNull("StringIndexOutOfBoundsException thown as expected " + "due to nagative level"
                    + lineSeparator + "error:" + e.getLocalizedMessage(), e);
        }
        Assert.assertEquals("", outContent.toString());
        outContent.reset();

        // test 4: using a sub-tree
        level = 1;
        Deencapsulation.invoke(testSubject, "display", new Object[]
        { treeNode, level });
        Assert.assertEquals(" 2 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        // more tests after adding more nodes
        FHsdTreeNode<Card> node9spades = testSubject.addChild(treeNode, new Card('9', Card.Suit.spades));
        FHsdTreeNode<Card> newNode = testSubject.addChild(treeNode, new Card('J', Card.Suit.spades));
        level = 0;
        Deencapsulation.invoke(testSubject, "display", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "  J of spades"
                + lineSeparator + "  9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "display", new Object[]
        { treeNode, level });
        Assert.assertEquals(
                " 2 of spades" + lineSeparator + "  J of spades" + lineSeparator + "  9 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "display", new Object[]
        { newNode, level });
        Assert.assertEquals(" J of spades" + lineSeparator + " 9 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        testSubject.remove(newNode.getData());
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { newNode, level });
        Assert.assertEquals(" J of spades (D)" + lineSeparator + " 9 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        // test handling of runaway indentation/recursion
        level = 36;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { node9spades, level });
        String output = outContent.toString();
        Assert.assertTrue(
                "If this test was not true, you let your indention/recursion go too far "
                        + "because the following output is just too long:" + lineSeparator + output,
                output.length() <= 43);
        outContent.reset();
    }

    @MethodRef(name = "traverse", signature = "(QF;QFHsdTreeNode<QE;>;I)V")
    @Test
    public void testTraverse_1() throws Exception
    {
        FHsdTree<Card> testSubject;
        CardTraverser func = new CardTraverser();
        FHsdTreeNode<Card> treeNode = null;
        int level = 0;

        // test 1
        System.out.println("test 1");
        testSubject = createTestSubject();
        treeNode = testSubject.addChild(null, new Card());
//		testSubject.traverse(func);
        testSubject.traverse(func, treeNode, level);
        FHsdTreeNode<Card> root = treeNode;

        // test 2
        System.out.println("test 2");
        treeNode = testSubject.addChild(root, new Card('3', Card.Suit.clubs));
        level = 0;
        testSubject.traverse(func, root, level);

        // test 3
        System.out.println("test 3");
        Card card4clubs = new Card('4', Card.Suit.clubs);
        treeNode = testSubject.addChild(root, card4clubs);
        level = -10;
        testSubject.traverse(func, treeNode, level);

        // test 4
        System.out.println("test 4");
        Card card5clubs = new Card('5', Card.Suit.clubs);
        treeNode = testSubject.addChild(treeNode, card5clubs);
        level = 10;
        testSubject.traverse(func, treeNode, level);

        // test 5: 'deleted' node
        System.out.println("test 5: 'deleted' node");
        treeNode = testSubject.addChild(root, new Card('6', Card.Suit.spades));
        level = 1;
        testSubject.traverse(func, treeNode, level);
        System.out.println("soft removing:" + card4clubs);
        testSubject.remove(card4clubs);
        testSubject.traverse(func, treeNode, level);

        Assert.assertEquals(
                "test 1" + lineSeparator + "Card:A of spades" + lineSeparator + "test 2" + lineSeparator
                        + "Card:A of spades" + lineSeparator + "Card:3 of clubs" + lineSeparator + "test 3"
                        + lineSeparator + "Card:4 of clubs" + lineSeparator + "test 4" + lineSeparator
                        + "Card:5 of clubs" + lineSeparator + "test 5: 'deleted' node" + lineSeparator
                        + "Card:6 of spades" + lineSeparator + "Card:4 of clubs" + lineSeparator + "Card:5 of clubs"
                        + lineSeparator + "Card:3 of clubs" + lineSeparator + "soft removing:4 of clubs" + lineSeparator
                        + "Card:6 of spades" + lineSeparator + "Card:3 of clubs" + lineSeparator + "",
                outContent.toString());
    }

    @MethodRef(name = "size", signature = "(QFHsdTreeNode<QE;>;)I")
    @Test
    public void testSize_1() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> root = null;
        int result;

        // test 1
        testSubject = createTestSubject();
        root = (FHsdTreeNode<Card>) testSubject.mRoot;
        result = testSubject.size(root);
        Assert.assertEquals(0, result, 0);

        // adding and removing tests
        testSubject = createTestSubject();
        root = null;
        Card x = new Card();
        FHsdTreeNode<Card> newNode = testSubject.addChild(null, x);
        Assert.assertNotNull(newNode);
        root = newNode;
        result = testSubject.size(root);
        Assert.assertEquals(1, result);
        x = new Card('2', Card.Suit.diamonds);
        newNode = testSubject.addChild(root, x);
        Assert.assertNotNull(newNode);
        result = testSubject.size(root);
        Assert.assertEquals(2, result);
        result = testSubject.size(newNode);
        Assert.assertEquals(1, result);
        boolean success = testSubject.remove(root.getData());
        Assert.assertEquals(true, success);
        Assert.assertEquals("The tree should be 'empty' now.", true, testSubject.empty());
        result = testSubject.size(root);
        Assert.assertEquals(0, result);
    }

    @MethodRef(name = "empty", signature = "()Z")
    @Test
    public void testEmpty() throws Exception
    {
        FHsdTree<Card> testSubject;
        boolean result;

        // default empty test
        testSubject = createTestSubject();
        result = Deencapsulation.invoke(testSubject, "empty");

        Assert.assertEquals(true, result);

        // default not empty test
        testSubject.addChild(null, new Card());
        result = Deencapsulation.invoke(testSubject, "empty");

        Assert.assertEquals(false, result);
    }

    @MethodRef(name = "collectGarbage", signature = "(QFHsdTreeNode<QE;>;)Z")
    @Test
    public void testCollectGarbage_1() throws Exception
    {
        FHsdTree<Card> testSubject;
        FHsdTreeNode<Card> treeNode = null;
        int level = 0;

        // test 1
        testSubject = createTestSubject();
        treeNode = testSubject.addChild(null, new Card());
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { treeNode, level });
        Assert.assertEquals("A of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        // test 2
        FHsdTreeNode<Card> root = treeNode;
        treeNode = testSubject.addChild(root, new Card('2', Card.Suit.spades));
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "",
                outContent.toString());
        outContent.reset();

        // test 3
        level = -1;
        try
        {
            Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
            { root, level });
        } catch (java.lang.StringIndexOutOfBoundsException e)
        {
            // e.printStackTrace();
            Assert.assertNotNull("StringIndexOutOfBoundsException thown as expected " + "due to nagative level"
                    + lineSeparator + "error:" + e.getLocalizedMessage(), e);
        }
        Assert.assertEquals("", outContent.toString());
        outContent.reset();

        // more tests after adding more nodes
        FHsdTreeNode<Card> newNode = testSubject.addChild(treeNode, new Card('9', Card.Suit.spades));
        newNode = testSubject.addChild(treeNode, new Card('J', Card.Suit.spades));
        level = 0;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals("A of spades" + lineSeparator + " 2 of spades" + lineSeparator + "  J of spades"
                + lineSeparator + "  9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();

        testSubject.remove(newNode.getData());
        level = 1;
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator + "   J of spades (D)"
                + lineSeparator + "   9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
        boolean collected = Deencapsulation.invoke(testSubject, "collectGarbage", new Object[]
        { treeNode });
        Assert.assertEquals(true, collected);
//		testSubject.collectGarbage(treeNode, 0);
//		testSubject.collectGarbage(treeNode, false);// Students using boolean parameter instead of an int
        Deencapsulation.invoke(testSubject, "displayPhysical", new Object[]
        { root, level });
        Assert.assertEquals(" A of spades" + lineSeparator + "  2 of spades" + lineSeparator +
        // " J of spades (D)" + lineSeparator +
                "   9 of spades" + lineSeparator + "", outContent.toString());
        outContent.reset();
    }
}