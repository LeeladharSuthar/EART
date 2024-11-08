import java.util.*;
public class MyClass{
    public static class Key {
        public long n, variance;

        public Key(long n, long v) {
            this.n = n;
            this.variance = v;
        }
    }

    private static final int OFFSET_CONSTANT = 32;
    private static final int DELTA = 32;

    private static int variableOffset(int nl, int nr) {
        return nl < BinaryTree.R ? (BinaryTree.R * nl) % nr : (BinaryTree.R * nr) % nl;
    }

    private static long prngGenerator(int seed) {
        Random prng = new Random(seed);
        return Math.abs(prng.nextLong());
    }

    public static class BinaryTree {
        public static final int LEN_MAX = 65535;
        public static final int R = 32768;

        public static int reflection(int nl) {
            return LEN_MAX - nl + 1;
        }
    }

    // Encryption
    public static String encrypt(Key key, String plainText) {
        long n = key.n, variance = key.variance;
        StringBuilder cipherText = new StringBuilder();

        int nl = (int) (n % BinaryTree.LEN_MAX);
        int nr = (int) BinaryTree.reflection(nl);

        int varOffset = (int) variableOffset(nl, nr);

        for (int pos = 0; pos < plainText.length(); pos++) {
            int orgVal = plainText.charAt(pos);
            int valInitRef = (int) BinaryTree.reflection(orgVal);

            long prn = prngGenerator(pos);
            // dynamic offset
            int dynOffset = (int) (prn % variance);

            if ((valInitRef + varOffset + OFFSET_CONSTANT) > BinaryTree.LEN_MAX) {
                if (((valInitRef + varOffset + OFFSET_CONSTANT) % BinaryTree.LEN_MAX) < 32) {
                    valInitRef = (int) ((valInitRef + varOffset + OFFSET_CONSTANT) % BinaryTree.LEN_MAX) + DELTA
                            + dynOffset;
                } else {
                    valInitRef = (int) ((valInitRef + varOffset + OFFSET_CONSTANT) % BinaryTree.LEN_MAX) + dynOffset;
                }
            } else {
                valInitRef = valInitRef + varOffset + OFFSET_CONSTANT + dynOffset;
            }
            cipherText.append((char) (valInitRef));
        }

        return cipherText.toString();
    }

    public static String decrypt(Key key, String cipherText) {
        long N = key.n, variance = key.variance;
        StringBuilder plainText = new StringBuilder();

        int Nl = (int) (N % BinaryTree.LEN_MAX);
        int Nr = (int) BinaryTree.reflection(Nl);
        int varOffset = (int) variableOffset(Nl, Nr);

        for (int pos = 0; pos < cipherText.length(); pos++) {
            long prn = prngGenerator(pos);
            int dynOffset = (int) (prn % variance);

            int valOrg = cipherText.charAt(pos);
            int X = valOrg - dynOffset;

            int quotient;
            if ((X - (OFFSET_CONSTANT + varOffset)) <= 0)
                quotient = 1;
            else
                quotient = 0;

            X = (int) (BinaryTree.LEN_MAX * quotient + X) - OFFSET_CONSTANT - varOffset;
            int org_value = (int) BinaryTree.reflection(X);
            // if (org_value == 44) org_value = org_value + 14;
            if (org_value >= 0 && org_value <= 32 && org_value != 10)
                org_value = org_value + DELTA;
            plainText.append((char) (org_value));
        }

        return Utils.refine(plainText);
    }

    public static class Utils {
        public static final int CHUNK_SIZE = 60000;

        public static String refine(StringBuilder builder) {
            String res = builder.toString();
            // res = res.replace("@", " ");
            // res = res.replace("*", " ");
            return res;
        }
    }

    public static void main(String args[]) {
        Key myKey = new Key(42L, 3L);
        // String input = "LsOQzTಉiहS";
        // String input = "!€æŽ‡»Íš¶ñØ3»ØñØ=";
        // String input = "€गतिΩவிலக்கு੧আমরণ₹√ముద్రణీయతలు";
        String input = "कृपया➕అన్ని#ವಿಧಗಳನ್ನು23สร้างq5נפשךħĠЉރକ𝜋";    

        String output = encrypt(myKey, input);
        String str = decrypt(myKey, output);

        System.out.println("Text:"+input);
        System.out.println("Encrypted Text:"+output);
        System.out.println("Decrypted Text:"+str);

    }
}