import java.util.*;
public class Dice {
    private int N;
    private String nChar;
    public void setN(int nbr){
        this.N=nbr;
    }
    public int getN(){
        return N;
    }
    public void setSequence(String nbrChar){
        this.nChar=nbrChar;
    }
    public String getSequence(){
        return nChar;
    }
    public int nbrThrows(){
        Scanner sc= new Scanner(System.in);
        int nbr;
        do {
            while (!sc.hasNextInt()) {
                String input = sc.next();
            }
            nbr = sc.nextInt();
        }while (nbr < 1 || nbr > 1000000 );
        return nbr;
    }

    public String sequence(int number){
        Scanner sc= new Scanner(System.in);
        String regex = "[1-6]+";
        String sequence= sc.nextLine();
        while(sequence.isEmpty() || sequence.length() != number || !sequence.matches(regex)){
            sequence= sc.nextLine();
        }
        return sequence;
    }

    public int hasTwoSix(String str)
    {
        int count = 0;
        for(int i=0; i<str.length();i++){
            try {
                if(str.charAt(i-1)!='6' && str.charAt(i)=='6' && str.charAt(i+1)=='6' && str.charAt(i+2)!='6')count++;
            }
            catch(StringIndexOutOfBoundsException e){
                if(i==0) {
                    try {
                        if(str.charAt(i)=='6' && str.charAt(i+1)=='6' && str.charAt(i+2)!='6')count++;
                    }
                    catch(StringIndexOutOfBoundsException err)
                    {
                        if(str.length()<2);
                        else
                        {
                            if(str.equals("66"))count++;
                        }
                    }
                }
                else if(i==str.length()-2)
                {
                    if(str.charAt(i-1)!='6' && str.charAt(i)=='6' && str.charAt(i+1)=='6')count++;
                }
                else
                {
                    if(str.charAt(i-2)!='6' && str.charAt(i-1)=='6' && str.charAt(i)=='6')count++;
                }
            }
        }
        return count;
    }

    public int longestSubsequence(String str){
        ArrayList<Integer> indexesOfSixArray = new ArrayList<Integer>();
        for(int i=0; i<str.length();i++){
            if(str.charAt(i) == '6'){
                indexesOfSixArray.add(i);
            }
        }
        indexesOfSixArray.add(str.length());
        //System.out.println(indexesOfSixArray);
        int longest;
        int distance;
        if(indexesOfSixArray.isEmpty()){
            longest= str.length();
        } else{
            longest=indexesOfSixArray.get(0);
            for (int i = 0; i < indexesOfSixArray.size(); i++) {
                try{
                    distance = indexesOfSixArray.get(i+1) - indexesOfSixArray.get(i);
                    if(distance > longest){
                        longest = distance - 1;
                    }
                } catch(IndexOutOfBoundsException e){
                    e.getMessage();
                }

            }
        }
        return longest;
    }

    public int luckySerie(String str){
        ArrayList<Integer> indexesOfNoSixFiveArray = new ArrayList<Integer>();
        for(int i=0; i<str.length();i++){
            if(str.charAt(i) != '6' && str.charAt(i) != '5'){
                indexesOfNoSixFiveArray.add(i);
            }
        }
        indexesOfNoSixFiveArray.add(str.length());
        //System.out.println(indexesOfNoSixFiveArray);

        int luckySerieLength;
        int distance;
        if(indexesOfNoSixFiveArray.isEmpty()){
            luckySerieLength= str.length();
        } else{
            luckySerieLength=indexesOfNoSixFiveArray.get(0);
            for (int i = 0; i < indexesOfNoSixFiveArray.size(); i++) {
                try{
                    distance = indexesOfNoSixFiveArray.get(i+1) - indexesOfNoSixFiveArray.get(i);
                    if(distance > luckySerieLength){
                        luckySerieLength = distance - 1;
                    }
                } catch(IndexOutOfBoundsException e){
                    e.getMessage();
                }

            }
        }
        return luckySerieLength;
    }

    public static void main(String[] args){
        Dice d = new Dice();
        d.setN(d.nbrThrows());
        int n = d.getN();
        d.setSequence(d.sequence(n));
        String sequence = d.getSequence();
        // EX1
        int hasTwoSix = d.hasTwoSix(sequence);
        System.out.println(hasTwoSix);
        // EX2
        int longest = d.longestSubsequence(sequence);
        System.out.println(longest);
        // EX3
        int luckySerieLength = d.luckySerie(sequence);
        System.out.print(luckySerieLength);
    }
}