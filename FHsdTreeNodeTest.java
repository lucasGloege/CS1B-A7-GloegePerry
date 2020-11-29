
import java.util.logging.Logger;

//import javax.annotation.Generated;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.tools.configuration.base.MethodRef;

//@Generated(value = "org.junit-tools-1.0.6")
public class FHsdTreeNodeTest
{

//	@Generated(value = "org.junit-tools-1.0.6")
    private Logger logger = Logger.getLogger(FHsdTreeNodeTest.class.toString());

    @Before
    public void setUp() throws Exception
    {

    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {

    }

    private FHsdTreeNode<Double> createTestSubject()
    {
        return new FHsdTreeNode<Double>(3.14, null, null, null, false);
    }

    @MethodRef(name = "getData", signature = "()QE;")
    @Test
    public void testGetData() throws Exception
    {
        FHsdTreeNode<Double> testSubject;
        Double result;

        // default test
        testSubject = createTestSubject();
    }

    @Test
    public void testFHsdTreeNode() throws Exception
    {
        FHsdTreeNode testSubject = new FHsdTreeNode();
        Object result = testSubject.getData();
        Assert.assertEquals(null, result);
    }
}