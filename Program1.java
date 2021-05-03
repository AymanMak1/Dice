import java.util.*;
class Dice {
    private int N;
    private String nChar;
    public void setN(int nbr){
        this.N=nbr;
    }
    public int getN(){
        return N;
    }
    public void  setSequence(String nbrChar){
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

    public static boolean notIn(int item, ArrayList<Integer> arr) {
        boolean isIn = true;
        for(int i=0; i<arr.size(); i++) {
            if(item == arr.get(i))isIn = false;
        }
        return isIn;
    }

    public static int maximum(ArrayList<Integer> arr) {
        int maximum = arr.get(0);
        for(int i=1; i<arr.size(); i++) {
            if(maximum < arr.get(i)) maximum = arr.get(i);
        }
        return maximum;
    }

    public int luckySerie (String str){

        ArrayList<Integer> Indexes = new ArrayList<Integer>();

        //Distances between every two indexes
        ArrayList<Integer> Distances = new ArrayList<Integer>();

        //Repeats for each distances
        ArrayList<Integer> Repeats = new ArrayList<Integer>();

        //Array for most frequent distances
        ArrayList<Integer> Frequents = new ArrayList<Integer>();

        int luckyLength;
        Indexes.add(-1);
        for(int i=0; i<str.length();i++) {
            if(str.charAt(i) != '6' && str.charAt(i) != '5') {
                Indexes.add(i);
            }
        }
        Indexes.add(str.length());

        if(Indexes.isEmpty()) {
            luckyLength = str.length();
        }
        else {
            for(int i=0; i<Indexes.size()-1; i++) {
                Distances.add(Indexes.get(i+1)-Indexes.get(i)-1);
            }
        }

        int count;
        for(int i=0;i<Distances.size();i++) {
            int help = Distances.get(i);
            count = 0;
            for(int j=0; j<Distances.size();j++) {
                if(help == Distances.get(j))count++;
                if(help == 0)break;
            }
            Repeats.add(count);
        }

        //We search for the maximum value
        int max = maximum(Repeats);
        for(int i=0;i<Distances.size();i++) {
            if(Repeats.get(i) == max) {
                int frequentlyLength = Distances.get(i);
                if(notIn(frequentlyLength,Frequents))Frequents.add(frequentlyLength);
            }
        }

        return (maximum(Frequents));
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