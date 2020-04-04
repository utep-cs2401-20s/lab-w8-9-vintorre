class AminoAcidLL{
  //iter.increaseOccurances();
  //WordNode head = new WordNode(word); occurances default set to 1
  //public void addEnd(String word);
  //head.addEnd(word);
  //WordNode n = new WordNode(word);
  //iter = head;
  //while(iter.getNext() != null) iter = iter.getNext();
  //iter.setNext(n);
  private char aminoAcid;
  private String[] codons;
  private int[] counts;
  private AminoAcidLL next;

  public AminoAcidLL(){
  }


  /********************************************************************************************/
  /* Creates a new node, with a given amino acid/codon 
   * pair and increments the codon counter for that codon.
   * NOTE: Does not check for repeats!! */
  public AminoAcidLL(String inCodon){

    char aminoAcid = AminoAcidResources.getAminoAcidFromCodon(inCodon);
    String[] codons = AminoAcidResources.getCodonListForAminoAcid(aminoAcid);
    counts = new int[codons.length];
    countCodons(inCodon);
    next = null;


  }

  /*******************************************************************************************/
  public void countCodons(String c){
    for(int i = 0; i < codons.length; i++){
      if (codons[i].equals(c)) {
          counts[i]++;
      }
    }

  }

  /*******************************************************************************************/

  /********************************************************************************************/
  /* Recursive method that increments the count for a specific codon:
   * If it should be at this node, increments it and stops, 
   * if not passes the task to the next node. 
   * If there is no next node, add a new node to the list that would contain the codon. 
   */
  private void addCodon(String inCodon){
    if(aminoAcid == AminoAcidResources.getAminoAcidFromCodon(inCodon))
      countCodons(inCodon);

    if(next!= null){
      next.addCodon(inCodon);
    }

    else
      next = new AminoAcidLL(inCodon);
  }


  /********************************************************************************************/
  /* Shortcut to find the total number of instances of this amino acid */
  private int totalCount(){
    int sum = 0;
    while(next != null){
      for(int i = 0; i < counts.length; i++){
        sum+= counts[i];
      }
    }
    return sum;
  }

  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int totalDiff(AminoAcidLL inList){
    return Math.abs(totalCount() - inList.totalCount());
  }


  /********************************************************************************************/
  /* helper method for finding the list difference on two matching nodes
  *  must be matching, but this is not tracked */
  private int codonDiff(AminoAcidLL inList){
    int diff = 0;
    for(int i=0; i<codons.length; i++){
      diff += Math.abs(counts[i] - inList.counts[i]);
    }
    return diff;
  }

  /********************************************************************************************/
  /* Recursive method that finds the differences in **Amino Acid** counts. 
   * the list *must* be sorted to use this method */
  public int aminoAcidCompare(AminoAcidLL inList){
    int difference = 0;
    if(next != null)
      difference = inList.totalCount() - next.totalCount();
    return difference;
  }

  /********************************************************************************************/
  /* Same ad above, but counts the codon usage differences
   * Must be sorted. */
  public int codonCompare(AminoAcidLL inList){
    int difference = 0;

    return difference;
  }


  /********************************************************************************************/
  /* Recursively returns the total list of amino acids in the order that they are in in the linked list. */
  public char[] aminoAcidList(){
    char[] list = new char[length()];
    return(getAA(list, this, 0));
  }

  public char[] getAA(char[] list, AminoAcidLL head, int count){
    if(head.next == null){
      list[count] = head.aminoAcid;
      return list;
    }

    else{
      list[count] = head.aminoAcid;
      return getAA(list, head, count++);
    }
  }

  public int length(){
    int count = 0;
    AminoAcidLL current = this;
    while(current != null){
      count++;
      current = current.next;
    }
    return count;
  }

  /********************************************************************************************/
  /* Recursively returns the total counts of amino acids in the order that they are in in the linked list. */
  public int[] aminoAcidCounts(){
    return new int[]{};
  }


  /********************************************************************************************/
  /* recursively determines if a linked list is sorted or not */
  public boolean isSorted(){
    return false;
  }


  /********************************************************************************************/
  /* Static method for generating a linked list from an RNA sequence */
  public static AminoAcidLL createFromRNASequence(String inSequence){
    return null;
  }


  /********************************************************************************************/
  /* sorts a list by amino acid character*/
  public static AminoAcidLL sort(AminoAcidLL inList){
    return null;
  }
}