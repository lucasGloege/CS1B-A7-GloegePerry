public class Card
{

   private char value;
   private Suit suit;
   private boolean errorFlag;

   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   public Card()
   {
      set('A', Suit.spades);
   }

   public Card(Card card)
   {
      set(card.value, card.suit);
   }

   public enum Suit
   {
      hearts, diamonds, clubs, spades;
   }

   public String toString()
   {
      if (errorFlag)
      {
         return "[invalid]";
      }
      return value + " of " + suit;
   }

   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.suit = suit;
         this.value = value;
         this.errorFlag = false;
         return true;
      }

      errorFlag = true;
      return false;
   }

   public char getValue()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean isErrorFlag()
   {
      return errorFlag;
   }

   public boolean equals(Card card)
   {
      return (this.value == card.value) && (this.suit == card.suit);
   }

   private static boolean isValid(char value, Suit suit)
   {
      return "AKQJT987654321".indexOf(value) > -1;
   }

}