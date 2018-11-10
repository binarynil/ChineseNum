import java.math.BigInteger;

public class ChineseNum {

    public static void main(String[] args) {
        // Thanks to https://www.rootnetsec.com/picoctf-weird-rsa/ for the solve
        BigInteger c = new BigInteger("95272795986475189505518980251137003509292621140166383887854853863720692420204142448424074834657149326853553097626486371206617513769930277580823116437975487148956107509247564965652417450550680181691869432067892028368985007229633943149091684419834136214793476910417359537696632874045272326665036717324623992885");
        BigInteger p = new BigInteger ("11387480584909854985125335848240384226653929942757756384489381242206157197986555243995335158328781970310603060671486688856263776452654268043936036556215243");
        BigInteger q = new BigInteger ("12972222875218086547425818961477257915105515705982283726851833508079600460542479267972050216838604649742870515200462359007315431848784163790312424462439629");
        BigInteger dp = new BigInteger ("8191957726161111880866028229950166742224147653136894248088678244548815086744810656765529876284622829884409590596114090872889522887052772791407131880103961");
        BigInteger dq = new BigInteger ("3570695757580148093370242608506191464756425954703930236924583065811730548932270595568088372441809535917032142349986828862994856575730078580414026791444659");

        BigInteger qinv = q.modInverse(p);
        BigInteger m1 = c.modPow(dp, p);
        BigInteger m2 = c.modPow(dq, q);
        BigInteger h = qinv.multiply(m1.subtract(m2)).mod(p);
        BigInteger m = m2.add(h.multiply(q));
        String mString = "" + m;
        //String hex = BigInteger.toHexString(m);
        BigInteger hex2 = new BigInteger(mString,10);
        String hexString = hex2.toString(16);
        String hexToascii = hex2ascii(hexString);
        

        System.out.println("m: " + m);
        System.out.println();
        System.out.println("hex: " + hexString);
        System.out.println("ascii: " + hexToascii);
    
    }

    private static String hex2ascii(String hexString) {
        // Thanks to https://howtodoinjava.com/java/convert-hex-to-ascii-and-ascii-to-hex/
        StringBuilder asciiString = new StringBuilder("");

        for(int i = 0; i < hexString.length(); i+=2) {
            String temp = hexString.substring(i, i+2);
            if(Integer.parseInt(temp, 16) < 32 || Integer.parseInt(temp, 16) > 126) {
                asciiString.append(temp);
            }
            else {
                asciiString.append((char) Integer.parseInt(temp, 16));
            }
        }

        return asciiString.toString();
    }
}

