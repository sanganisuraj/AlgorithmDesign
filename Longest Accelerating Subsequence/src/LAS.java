import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 
 * @author Jay Sharma and Suraj Sangani
 * 
 * The program computes the longest accelerating sequence of a sequence of numbers and prints the output according to the input entered.
 * The first line takes in 2 things:
 * 1) The number of integers in the input
 * 2) 0 if only the length of the sequence is to be printed and any other integer if the sequence is to be printed as well. 
 *
 */
public class LAS {
    static ArrayList<String> output = new ArrayList<String>();

    /*
     * This function computes the length of the sequence and prints the length and the sequence in accordance with the input
     */
    private static void longestSub(int[] input, int v) {
    	// Array for holding the length of the sequences computed
        int las[][] = new int[input.length][input.length];
        
        //TreeSet to hold the elements of the sequence in sorted order
        TreeSet<Integer> sequence = new TreeSet<Integer>();
        
        //String to hold the elements of the sequence in the TreeSet
        String outputsequence = "";
        
        //The maximum length of the sequence
        int maxL = 1;
        
        for(int j = 0; j<input.length; j++)
        {
            for(int i=0; i<j; i++) 
            {
                int counterfork = 0; //Variable to check how many times the loop for k is entered. If the loop is entered 0 times
                					//las is initialized to 2. 
                for(int k=0; k<i; k++)
                {
                	//Update the las as the input is processed.
                    if ((las[k][i]+1 > las[i][j]) && (input[i]-input[k] < input[j]-input[i]) && input[i]-input[k]>0 && input[j]-input[i]>0)
                    {
                        las[i][j] = 1+las[k][i];
                        counterfork++;
                    }
                }
           
                if(counterfork==0) {
                    las[i][j]=2;
                }
                
                //Update the length of the longest accelerating subsequence
                if(las[i][j]>maxL && counterfork!=0)
                {
                    maxL = las[i][j];
                }
            }
        }
        output.add(String.valueOf(maxL));
        if(maxL==1)
        	maxL++;
        //Since we want to pick up the elements from LAS when value of LAS[i][j]=2 only once we keep a counter to keep track of this.
        int counter=0;
        
        //Initialize q and r to maximum lengths of the array
        int q=input.length-1, r=input.length-1;
        
        //Loop to find out the occurrence of the maximum length in the LAS array.
        for(int i=0; i<=q; i++) {
        	for(int j=0; j<=r; j++) {
        		if(las[i][j]==maxL) {
        			q=i;
        			r=j;
        		}
        	}
        }
        
        //Logic to construct the elements of the sequence.
        while(maxL>=2) {
            if(counter>1) {
                break;
            }
        	while(q>0) {
        		if(las[q][r]==maxL)
        			break;
        		else
        			q--;
        	}
            sequence.add(input[q]);
            sequence.add(input[r]);
            r=q;
            q--;
            maxL--;
            if(maxL==2) {
                counter++;
            }
        }
        if(sequence.size()==2) {
        	sequence.remove(sequence.pollFirst());
        }
        
        //Add the elements of the sequence to the output
        Iterator<Integer> i = sequence.iterator();
        while(i.hasNext())
        {
            outputsequence += i.next() + " ";
        }
        if(v!=0)
            output.add(outputsequence);
    }
    
    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);

        //Array to hold input
        int input[];
        while(sc.hasNextLine()) {
        int n = sc.nextInt();
        input = new int[n];
        int v = sc.nextInt();

        sc.nextLine();
        if(n!=0)
        {
            for(int i=0;i<n;i++)
            {
            input[i] = sc.nextInt();
            }
            longestSub(input, v);
        }
        else if(n==0 && v==0) //print the output if n=0 and v=0 and exit
        {
            for(int i=0; i<output.size(); i++)
                System.out.println(output.get(i));
            System.exit(0);
        }

        }
        sc.close();
    }  
}