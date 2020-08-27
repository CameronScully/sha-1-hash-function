import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger; //for byte to hex
import java.util.*; //hash table

/*
find a pair of integers (x, x') such that x =/= x' but the SSHA-1 hash values of the follwoing two messages are the same
The Cat-In-The-Hat owes Cameron x dollars
Cat-In-The-Hat owes Cameron x' dollars
SSH1(m) = SSH1(m')
*/

//output the two messages
//output their hash values (should be the same)
//output the number of trial your program has made before it finds the collision


class Solution1{
    public static String encrypt(String m){
        try{
            //new encryption object
            MessageDigest md = MessageDigest.getInstance("SHA-1");
        
            //encrypt message
            byte[] sha1 = md.digest(m.getBytes());
            
            //take first 24 bits
            byte[] ssha1 = new byte[]{sha1[0], sha1[1], sha1[2], sha1[3]};
            
            //convert byte array to hex for display
            BigInteger hexString = new BigInteger(1, ssha1);
            
            //base 16 for hex
            return hexString.toString(16);
        } catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage()); 
        }
        
        return "Error encrypting";
    }

    public static void main(String args[]){
        //intergers
        int x1 = 1;

        //attempts
        int count = 1;

        //genorated hashes as keys and the message
        Map map = new HashMap();

        while(true){
            count++;
            x1++;

            String m1 = "The Cat-In-The-Hat owes Cameron " + x1 + " dollars";

            String h1 = encrypt(m1);

            //store in hashmap
            String m2 = (String)map.put(h1, m1);
            if(m2 != null){
                //collision found
                System.out.println("Message 1: " + m1);
                System.out.println("Message 2: " + m2);
                System.out.println("Hash 1: " + encrypt(m1));
                System.out.println("Hash 2: " + encrypt(m2));
                System.out.println("Number of trails: " + count);
                break;
            }
        }
    }
}
