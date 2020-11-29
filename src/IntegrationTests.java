
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
public class IntegrationTests
{

//	@Generated(value = "org.junit-tools-1.0.6")
    private Logger logger = Logger.getLogger(IntegrationTests.class.toString());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream out = System.out;
    private final PrintStream err = System.err;
    /**
     * Sequence used by operating system to separate lines in text files
     */
    public static String newLine = System.getProperty("line.separator");

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

    @Test
    public void testFullIntegration()
    {
        FHsdTree<String> sceneTree = new FHsdTree<String>();
        FHsdTreeNode<String> tn;

        System.out.println("Starting tree empty? " + sceneTree.empty() + newLine);
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

        System.out.println(newLine + "------------ Loaded Tree ----------------- " + newLine);
        sceneTree.display();

        sceneTree.remove("spare mutant paw");
        sceneTree.remove("Miguel the human");
        sceneTree.remove("an imagined higgs boson");

        System.out.println(newLine + "----------- Virtual (soft) Tree -------------- " + newLine);
        sceneTree.display();

        System.out.println(newLine + "---------- Physical (hard) Display ----------- " + newLine);
        sceneTree.displayPhysical();

        System.out.println("------ Testing Sizes (compare with above) ------ " + newLine);
        System.out.println("virtual (soft) size: " + sceneTree.size());
        final int originalSizePhysical = sceneTree.sizePhysical();
        System.out.println("physical (hard) size: " + originalSizePhysical);

        System.out.println("------------ Collecting Garbage --------------- " + newLine);
        // sceneTree.collectGarbage();
        // int sizePhysical = sceneTree.sizePhysical();
        // boolean sizePhysicalGToriginalSizePhysical = sizePhysical >
        // originalSizePhysical;
        System.out.println("found soft-deleted nodes? " + sceneTree.collectGarbage());
        // System.out.println("found soft-deleted nodes? " +
        // sizePhysicalGToriginalSizePhysical);
        // int prevSizePhysical = sizePhysical;
        // sceneTree.collectGarbage();
        // sizePhysical = sceneTree.sizePhysical();
        // boolean sizePhysicalGTprevSizePhysical = sizePhysical > prevSizePhysical;
        System.out.println("immediate collect again? " + sceneTree.collectGarbage());
        // System.out.println("immediate collect again? " +
        // sizePhysicalGTprevSizePhysical);

        System.out.println("-------- Hard Display after garb col ---------- " + newLine);

        sceneTree.displayPhysical();

        System.out.println("Semi-deleted tree empty? " + sceneTree.empty() + newLine);
        sceneTree.remove("room");
        System.out.println("Completely-deleted tree empty? " + sceneTree.empty() + newLine);

        sceneTree.display();
        Assert.assertEquals("Starting tree empty? true" + newLine + newLine + newLine
                + "------------ Loaded Tree ----------------- " + newLine + newLine + "room" + newLine + " table"
                + newLine + "  south west leg" + newLine + "  south east leg" + newLine + "  north west leg" + newLine
                + "  north east leg" + newLine + " Miguel the human" + newLine + "  torso" + newLine + "   right arm"
                + newLine + "    right hand" + newLine + "     pinky" + newLine + "     ring finger" + newLine
                + "     middle finger" + newLine + "     index finger" + newLine + "     thumb" + newLine
                + "   left arm" + newLine + "    left hand" + newLine + "     pinky" + newLine + "     ring finger"
                + newLine + "     middle finger" + newLine + "     index finger" + newLine + "     thumb" + newLine
                + " Lily the canine" + newLine + "  torso" + newLine + "   wagging tail" + newLine
                + "   spare mutant paw" + newLine + "   left rear paw" + newLine + "   right rear paw" + newLine
                + "   left front paw" + newLine + "   right front paw" + newLine + newLine
                + "----------- Virtual (soft) Tree -------------- " + newLine + newLine + "room" + newLine + " table"
                + newLine + "  south west leg" + newLine + "  south east leg" + newLine + "  north west leg" + newLine
                + "  north east leg" + newLine + " Lily the canine" + newLine + "  torso" + newLine + "   wagging tail"
                + newLine + "   left rear paw" + newLine + "   right rear paw" + newLine + "   left front paw" + newLine
                + "   right front paw" + newLine + newLine + "---------- Physical (hard) Display ----------- " + newLine
                + newLine + "room" + newLine + " table" + newLine + "  south west leg" + newLine + "  south east leg"
                + newLine + "  north west leg" + newLine + "  north east leg" + newLine + " Miguel the human (D)"
                + newLine + "  torso" + newLine + "   right arm" + newLine + "    right hand" + newLine + "     pinky"
                + newLine + "     ring finger" + newLine + "     middle finger" + newLine + "     index finger"
                + newLine + "     thumb" + newLine + "   left arm" + newLine + "    left hand" + newLine + "     pinky"
                + newLine + "     ring finger" + newLine + "     middle finger" + newLine + "     index finger"
                + newLine + "     thumb" + newLine + " Lily the canine" + newLine + "  torso" + newLine
                + "   wagging tail" + newLine + "   spare mutant paw (D)" + newLine + "   left rear paw" + newLine
                + "   right rear paw" + newLine + "   left front paw" + newLine + "   right front paw" + newLine
                + "------ Testing Sizes (compare with above) ------ " + newLine + newLine + "virtual (soft) size: 13"
                + newLine + "physical (hard) size: 30" + newLine + "------------ Collecting Garbage --------------- "
                + newLine + newLine + "found soft-deleted nodes? true" + newLine + "immediate collect again? false"
                + newLine + "-------- Hard Display after garb col ---------- " + newLine + newLine + "room" + newLine
                + " table" + newLine + "  south west leg" + newLine + "  south east leg" + newLine + "  north west leg"
                + newLine + "  north east leg" + newLine + " Lily the canine" + newLine + "  torso" + newLine
                + "   wagging tail" + newLine + "   left rear paw" + newLine + "   right rear paw" + newLine
                + "   left front paw" + newLine + "   right front paw" + newLine + "Semi-deleted tree empty? false"
                + newLine + newLine + "Completely-deleted tree empty? true" + newLine + newLine + "",
                outContent.toString());
        Assert.assertEquals("", errContent.toString());
    }
}