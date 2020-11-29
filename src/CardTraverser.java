/**
 * @author Baba Kofi Weusijana
 *
 */
public class CardTraverser implements Traverser<Card>
{

    @Override
    public void visit(Card x)
    {
        System.out.println("Card:" + x);
    }

}
