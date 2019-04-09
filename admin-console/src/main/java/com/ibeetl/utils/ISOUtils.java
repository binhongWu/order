package com.ibeetl.utils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 数据打包、解包用到的各种功能
 */
public class ISOUtils {
    public static Random random = new Random();

    /**
     * 填充字符串
     * @param s 原有字符串
     * @param isPadLeft 是否填充左侧
     * @param len 填充长度
     * @param c 填充的字符
     * @return 返回填充后的字符串
     */
    public static String padChar(String s,boolean isPadLeft,int len,char c){
        s=s.trim();
        if(s.length()>=len){
            return s;
        }
        StringBuffer d = new StringBuffer (s);
        int fill = len - s.length();
        while (fill-- > 0){
            if(isPadLeft){
                d.insert(0, c);
            }else{
                d.append (c);
            }
        }
        return d.toString();
    }

    /**
     * 填充左侧字符串
     * @param s 原有字符串
     * @param len 填充长度
     * @param c 填充的字符
     * @return 返回填充后的字符串
     */
    public static String padLeft(String s, int len, char c)
    {
        return padChar(s,true,len,c);
    }

    /**
     *  填充右侧字符串
     * @param s 原有字符串
     * @param len 填充长度
     * * @param c 填充的字符
     * @return 返回填充后的字符串
     */
    public static String zeroPad(String s, int len) {
        return padLeft(s, len, '0');
    }
    /**
     *  使用指定字符填充右侧字符串
     * @param s 原有字符串
     * @param len 填充长度
     * @return 返回填充后的字符串
     */
    public static String padRight(String s, int len,char c) {
        return padChar(s,false,len,c);
    }
    /**
     *  使用'0'填充右侧字符串
     * @param s 原有字符串
     * @param len 填充长度
     * @return 返回填充后的字符串
     */
    public static String zeroPadRight (String s, int len) {
        return padRight(s, len, '0');
    }

    /**
     * 字符串转BCD码
     * @param s - 数据
     * @param padLeft - 是否左填充
     * @param d 存储的数组.
     * @return 返回BCD字节数组
     */
    public static byte[] str2bcd(String s, boolean padLeft, byte[] d, int offset) {
        int len = s.length();
        int start = (((len & 1) == 1) && padLeft) ? 1 : 0;

        for (int i=start; i < len+start; i++)
            d [offset + (i >> 1)] |= (s.charAt(i-start)-'0') << ((i & 1) == 1 ? 0 : 4);

        return d;
    }

    /**
     * 字符串转BCD码
     * @param s - 数据
     * @param padLeft - 是否左填充
     * @return 返回BCD字节数组
     */
    public static byte[] str2bcd(String s, boolean padLeft) {
        int len = s.length();
        byte[] d = new byte[ (len+1) >> 1 ];
        return str2bcd(s, padLeft, d, 0);
    }

    /**
     * 字符串转BCD码
     * @param s - 数据
     * @param padLeft - 是否左填充
     * @param fill - 填充的数据
     * @return 返回BCD字节数组
     */
    public static byte[] str2bcd(String s, boolean padLeft, byte fill) {
        int len = s.length();
        byte[] d = new byte[ (len+1) >> 1 ];
        Arrays.fill (d, fill);
        int start = (((len & 1) == 1) && padLeft) ? 1 : 0;
        for (int i=start; i < len+start; i++)
            d [i >> 1] |= (s.charAt(i-start)-'0') << ((i & 1) == 1 ? 0 : 4);
        return d;
    }

    /**
     * BCD字节数组转字符串
     * @param b - BCD字节数组
     * @param offset - 偏移量
     * @param len - BCD数组长度
     * @param padLeft - 是否左填充
     * @return 返回转换后的字符串
     */
    public static String bcd2str(byte[] b, int offset, int len, boolean padLeft)
    {
        StringBuffer d = new StringBuffer(len);
        int start = (((len & 1) == 1) && padLeft) ? 1 : 0;
        for (int i=start; i < len+start; i++) {
            int shift = ((i & 1) == 1 ? 0 : 4);
            char c = Character.forDigit (
                    ((b[offset+(i>>1)] >> shift) & 0x0F), 16);
            if (c == 'd')
                c = '=';
            d.append (Character.toUpperCase (c));
        }
        return d.toString();
    }

    /**
     * 字节数组转换成十六进制字符串
     * @param b 字节数组
     * @return 返回十六进制字符串
     */
    public static String hexString(byte[] b) {
        StringBuffer d = new StringBuffer(b.length * 2);
        for (int i=0; i<b.length; i++) {
            char hi = Character.forDigit ((b[i] >> 4) & 0x0F, 16);
            char lo = Character.forDigit (b[i] & 0x0F, 16);
            d.append(Character.toUpperCase(hi));
            d.append(Character.toUpperCase(lo));
        }
        return d.toString();
    }


    /**
     * 生成字节数组对应的十六进制打印字符串
     * @param b 字节数组
     * @return 返回十六进制字符串
     */
    public static String dumpString(byte[] b) {
        StringBuffer d = new StringBuffer(b.length * 2);
        for (int i=0; i<b.length; i++) {
            char c = (char) b[i];
            if (Character.isISOControl (c)) {
                switch (c) {
                    case '\r'  : d.append ("{CR}");   break;
                    case '\n'  : d.append ("{LF}");   break;
                    case '\000': d.append ("{NULL}"); break;
                    case '\001': d.append ("{SOH}");  break;
                    case '\002': d.append ("{STX}");  break;
                    case '\003': d.append ("{ETX}");  break;
                    case '\004': d.append ("{EOT}");  break;
                    case '\005': d.append ("{ENQ}");  break;
                    case '\006': d.append ("{ACK}");  break;
                    case '\007': d.append ("{BEL}");  break;
                    case '\020': d.append ("{DLE}");  break;
                    case '\025': d.append ("{NAK}");  break;
                    case '\026': d.append ("{SYN}");  break;
                    case '\034': d.append ("{FS}");  break;
                    case '\036': d.append ("{RS}");  break;
                    default:
                        char hi = Character.forDigit ((b[i] >> 4) & 0x0F, 16);
                        char lo = Character.forDigit (b[i] & 0x0F, 16);
                        d.append('[');
                        d.append(Character.toUpperCase(hi));
                        d.append(Character.toUpperCase(lo));
                        d.append(']');
                        break;
                }
            }
            else
                d.append (c);

        }
        return d.toString();
    }

    /**
     * 字节数组转换成十六进制字符串
     * @param b 字节数组
     * @return 返回十六进制字符串
     */
    public static String hexString(byte[] b, int offset, int len) {
        StringBuffer d = new StringBuffer(len * 2);
        len += offset;
        for (int i=offset; i<len; i++) {
            char hi = Character.forDigit ((b[i] >> 4) & 0x0F, 16);
            char lo = Character.forDigit (b[i] & 0x0F, 16);
            d.append(Character.toUpperCase(hi));
            d.append(Character.toUpperCase(lo));
        }
        return d.toString();
    }

    /**
     * bit representation of a BitSet
     * suitable for dumps and debugging
     * @param b - the BitSet
     * @return string representing the bits (i.e. 011010010...)
     */
    public static String bitSet2String (BitSet b) {
        int len = b.size();
        len = (len > 128) ? 128: len;
        StringBuffer d = new StringBuffer(len);
        for (int i=0; i<len; i++)
            d.append (b.get(i) ? '1' : '0');
        return d.toString();
    }

    /**
     * converts a BitSet into a binary field
     * used in pack routines
     * @param b - the BitSet
     * @return binary representation
     */
    public static byte[] bitSet2byte (BitSet b)
    {
        int len = (((b.length()+62)>>6)<<6);
        byte[] d = new byte[len >> 3];
        for (int i=0; i<len; i++)
            if (b.get(i+1))
                d[i >> 3] |= (0x80 >> (i % 8));
        if (len>64)
            d[0] |= 0x80;
        if (len>128)
            d[8] |= 0x80;
        return d;
    }

    /**
     * converts a BitSet into a binary field
     * used in pack routines
     * @param b - the BitSet
     * @param bytes - number of bytes to return
     * @return binary representation
     */
    public static byte[] bitSet2byte (BitSet b, int bytes)
    {
        int len = bytes * 8;

        byte[] d = new byte[bytes];
        for (int i=0; i<len; i++)
            if (b.get(i+1))
                d[i >> 3] |= (0x80 >> (i % 8));
        if (len>64)
            d[0] |= 0x80;
        if (len>128)
            d[8] |= 0x80;
        return d;
    }

    /**
     * Converts a binary representation of a Bitmap field
     * into a Java BitSet
     * @param b - binary representation
     * @param offset - staring offset
     * @param bitZeroMeansExtended - true for ISO-8583
     * @return java BitSet object
     */
    public static BitSet byte2BitSet
    (byte[] b, int offset, boolean bitZeroMeansExtended)
    {
        int len = bitZeroMeansExtended ?
                ((b[offset] & 0x80) == 0x80 ? 128 : 64) : 64;
        BitSet bmap = new BitSet (len);
        for (int i=0; i<len; i++)
            if (((b[offset + (i >> 3)]) & (0x80 >> (i % 8))) > 0)
                bmap.set(i+1);
        return bmap;
    }

    /**
     * Converts a binary representation of a Bitmap field
     * into a Java BitSet
     * @param b - binary representation
     * @param offset - staring offset
     * @param maxBits - max number of bits (supports 64,128 or 192)
     * @return java BitSet object
     */
    public static BitSet byte2BitSet (byte[] b, int offset, int maxBits) {
        int len = maxBits > 64 ?
                ((b[offset] & 0x80) == 0x80 ? 128 : 64) : maxBits;

        if (maxBits > 128 &&
                b.length > offset+8 &&
                (b[offset+8] & 0x80) == 0x80)
        {
            len = 192;
        }
        BitSet bmap = new BitSet (len);
        for (int i=0; i<len; i++)
            if (((b[offset + (i >> 3)]) & (0x80 >> (i % 8))) > 0)
                bmap.set(i+1);
        return bmap;
    }

    /**
     * Converts a binary representation of a Bitmap field
     * into a Java BitSet
     * @param bmap - BitSet
     * @param b - hex representation
     * @param bitOffset - (i.e. 0 for primary bitmap, 64 for secondary)
     * @return java BitSet object
     */
    public static BitSet byte2BitSet (BitSet bmap, byte[] b, int bitOffset)
    {
        int len = b.length << 3;
        for (int i=0; i<len; i++)
            if (((b[i >> 3]) & (0x80 >> (i % 8))) > 0)
                bmap.set(bitOffset + i + 1);
        return bmap;
    }

    public static int bytesToLen(byte[] lenBytes,boolean isBigEnd,int offset){
        if(lenBytes.length < offset + 2)
            throw new IllegalArgumentException("len-bytes array len must be equals or bigger than "+(offset + 2)+"!");

        if(isBigEnd){
            return (((lenBytes[offset] & 0xff) <<8) + (lenBytes[offset + 1] & 0xff));
        }else{
            return (((lenBytes[offset + 1] & 0xff) << 8 )+ (lenBytes[offset] & 0xff));
        }

    }

    /**
     * 将整型转成2字节长度表示
     *
     * @param isBigEnd
     * @return
     */
    public static byte[] len2TBytes(int len,boolean isBigEnd){
        byte[] b = new byte[2];
        if(isBigEnd){
            b[0] = (byte)((len >> 8) & 0xff);
            b[1] = (byte)(len & 0xff );
        }else{
            b[0] = (byte)(len & 0xff);
            b[1] = (byte)((len >> 8) & 0xff);
        }
        return b;
    }

    /**
     * Converts an ASCII representation of a Bitmap field
     * into a Java BitSet
     * @param b - hex representation
     * @param offset - starting offset
     * @param bitZeroMeansExtended - true for ISO-8583
     * @return java BitSet object
     */
    public static BitSet hex2BitSet
    (byte[] b, int offset, boolean bitZeroMeansExtended)
    {
        int len = bitZeroMeansExtended ?
                ((Character.digit((char)b[offset],16) & 0x08) == 8 ? 128 : 64) :
                64;
        BitSet bmap = new BitSet (len);
        for (int i=0; i<len; i++) {
            int digit = Character.digit((char)b[offset + (i >> 2)], 16);
            if ((digit & (0x08 >> (i%4))) > 0)
                bmap.set(i+1);
        }
        return bmap;
    }

    /**
     * Converts an ASCII representation of a Bitmap field
     * into a Java BitSet
     * @param b - hex representation
     * @param offset - starting offset
     * @param maxBits - max number of bits (supports 8, 16, 24, 32, 48, 52, 64,.. 128 or 192)
     * @return java BitSet object
     */
    public static BitSet hex2BitSet (byte[] b, int offset, int maxBits) {
        int len = maxBits > 64?
                ((Character.digit((char)b[offset],16) & 0x08) == 8 ? 128 : 64) :
                maxBits;
        BitSet bmap = new BitSet (len);
        for (int i=0; i<len; i++) {
            int digit = Character.digit((char)b[offset + (i >> 2)], 16);
            if ((digit & (0x08 >> (i%4))) > 0) {
                bmap.set(i+1);
                if (i==65 && maxBits > 128)
                    len = 192;
            }
        }
        return bmap;
    }

    /**
     * Converts an ASCII representation of a Bitmap field
     * into a Java BitSet
     * @param bmap - BitSet
     * @param b - hex representation
     * @param bitOffset - (i.e. 0 for primary bitmap, 64 for secondary)
     * @return java BitSet object
     */
    public static BitSet hex2BitSet (BitSet bmap, byte[] b, int bitOffset)
    {
        int len = b.length << 2;
        for (int i=0; i<len; i++) {
            int digit = Character.digit((char)b[i >> 2], 16);
            if ((digit & (0x08 >> (i%4))) > 0)
                bmap.set (bitOffset + i + 1);
        }
        return bmap;
    }

    /**
     * @param   b       source byte array
     * @param   offset  starting offset
     * @param   len     number of bytes in destination (processes len*2)
     * @return  byte[len]
     */
    public static byte[] hex2byte (byte[] b, int offset, int len) {
        byte[] d = new byte[len];
        for (int i=0; i<len*2; i++) {
            int shift = i%2 == 1 ? 0 : 4;
            d[i>>1] |= Character.digit((char) b[offset+i], 16) << shift;
        }
        return d;
    }

    /**
     * @param s source string (with Hex representation)
     * @return byte array
     */
    public static byte[] hex2byte (String s) {
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        return hex2byte (s.getBytes(), 0, s.length() >> 1);
    }

    /**
     * format double value
     * @return a String of fieldLen characters (right justified)
     */
    public static String formatDouble(double d, int len) {
        String prefix = Long.toString((long) d);
        String suffix = Integer.toString (
                (int) ((Math.round(d * 100f)) % 100) );
        try {
            if (len > 3)
                prefix = ISOUtils.padLeft(prefix,len-3,' ');
            suffix = ISOUtils.zeroPad(suffix, 2);
        } catch (Exception e) {
            // should not happen
        }
        return prefix + "." + suffix;
    }

    /**
     * prepare long value used as amount for display
     * (implicit 2 decimals)
     * @param l value
     * @param len display len
     * @return formated field
     */
    public static String formatAmount(long l, int len)  {
        String buf = Long.toString(l);
        if (l < 100)
            buf = zeroPad(buf, 3);
        StringBuffer s = new StringBuffer(padLeft (buf, len-1, ' ') );
        s.insert(len-3, '.');
        return s.toString();
    }

    /**
     * XML normalizer
     * @param s source String
     * @param canonical true if we want to normalize \r and \n as well
     * @return normalized string suitable for XML Output
     */
    public static String normalize (String s, boolean canonical) {
        StringBuffer str = new StringBuffer();

        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '<':
                    str.append("&lt;");
                    break;
                case '>':
                    str.append("&gt;");
                    break;
                case '&':
                    str.append("&amp;");
                    break;
                case '"':
                    str.append("&quot;");
                    break;
                case '\r':
                case '\n':
                    if (canonical) {
                        str.append("&#");
                        str.append(Integer.toString((int) (ch & 0xFF)));
                        str.append(';');
                        break;
                    }
                    // else, default append char
                default:
                    if (ch < 0x20) {
                        str.append("&#");
                        str.append(Integer.toString((int) (ch & 0xFF)));
                        str.append(';');
                    }
                    else if (ch > 0xff00) {
                        str.append((char) (ch & 0xFF));
                    } else
                        str.append(ch);
            }
        }
        return (str.toString());
    }

    /**
     * XML normalizer (default canonical)
     * @param s source String
     * @return normalized string suitable for XML Output
     */
    public static String normalize (String s) {
        return normalize (s, true);
    }

    /**
     * Protects PAN, Track2, CVC (suitable for logs).
     *
     * <pre>
     * "40000101010001" is converted to "400001____0001"
     * "40000101010001=020128375" is converted to "400001____0001=0201_____"
     * "123" is converted to "___"
     * </pre>
     * @param s string to be protected
     * @return 'protected' String
     */
    public static String protect (String s) {
        StringBuffer sb = new StringBuffer();
        int len   = s.length();
        int clear = len > 6 ? 6 : 0;
        int lastFourIndex = -1;
        if (clear > 0) {
            lastFourIndex = s.indexOf ('=') - 4;
            if (lastFourIndex < 0) {
                lastFourIndex = s.indexOf ('^') - 4;
                if (lastFourIndex < 0)
                    lastFourIndex = len - 4;
            }
        }
        for (int i=0; i<len; i++) {
            if (s.charAt(i) == '=')
                clear = 1;  // use clear=5 to keep the expiration date
            else if (s.charAt(i) == '^') {
                lastFourIndex = 0;
                clear = len - i;
            }
            else if (i == lastFourIndex)
                clear = 4;
            sb.append (clear-- > 0 ? s.charAt(i) : '_');
        }
        return sb.toString();
    }

    public static String protect(String s,char maskChar,int offset,int maskLen) {
        int strLen=s.length();
        if(strLen<=offset||strLen<(offset+maskLen)){
            return s;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(s);

        String maskChars="";
        for(int i=0;i<maskLen;i++){
            maskChars+=maskChar;
        }
        sb=sb.replace(offset, offset+maskLen, maskChars);
        return sb.toString();
    }

    public static String protectMiddle(String s,char maskChar,int startLen,int endLen) {
        int strLen=s.length();
        if(strLen<=startLen){
            return s;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(s);


        String maskChars="";
        int maskLen=strLen-startLen-endLen;
        if(maskLen<=0){
            maskLen=1;
        }

        for(int i=0;i<maskLen;i++){
            maskChars+=maskChar;
        }
        sb=sb.replace(startLen,startLen+maskLen, maskChars);
        return sb.toString();
    }

    public static int[] toIntArray(String s) {
        StringTokenizer st = new StringTokenizer (s);
        int[] array = new int [st.countTokens()];
        for (int i=0; st.hasMoreTokens(); i++)
            array[i] = Integer.parseInt (st.nextToken());
        return array;
    }

    public static String[] toStringArray(String s) {
        StringTokenizer st = new StringTokenizer (s);
        String[] array = new String [st.countTokens()];
        for (int i=0; st.hasMoreTokens(); i++)
            array[i] = st.nextToken();
        return array;
    }

    /**
     * Bitwise XOR between corresponding bytes
     * @param op1 byteArray1
     * @param op2 byteArray2
     * @return an array of length = the smallest between op1 and op2
     */
    public static byte[] xor (byte[] op1, byte[] op2) {
        byte[] result = null;
        // Use the smallest array
        if (op2.length > op1.length) {
            result = new byte[op1.length];
        }
        else {
            result = new byte[op2.length];
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte)(op1[i] ^ op2[i]);
        }
        return  result;
    }

    /**
     * Bitwise XOR between corresponding byte arrays represented in hex
     * @param op1 hexstring 1
     * @param op2 hexstring 2
     * @return an array of length = the smallest between op1 and op2
     */
    public static String hexor (String op1, String op2) {
        byte[] xor = xor (hex2byte (op1), hex2byte (op2));
        return hexString (xor);
    }

    /**
     * Trims a byte[] to a certain length
     * @param array the byte[] to be trimmed
     * @param length the wanted length
     * @return the trimmed byte[]
     */
    public static byte[] trim (byte[] array, int length) {
        byte[] trimmedArray = new byte[length];
        System.arraycopy(array, 0, trimmedArray, 0, length);
        return  trimmedArray;
    }

    /**
     * Concatenates two byte arrays (array1 and array2)
     * @param array1
     * @param array2
     * @return the concatenated array
     */
    public static byte[] concat (byte[] array1, byte[] array2) {
        byte[] concatArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, concatArray, 0, array1.length);
        System.arraycopy(array2, 0, concatArray, array1.length, array2.length);
        return  concatArray;
    }

    /**
     * Concatenates two byte arrays (array1 and array2)
     * @param array1
     * @param beginIndex1
     * @param length1
     * @param array2
     * @param beginIndex2
     * @param length2
     * @return the concatenated array
     */
    public static byte[] concat (byte[] array1, int beginIndex1, int length1, byte[] array2,
                                 int beginIndex2, int length2) {
        byte[] concatArray = new byte[length1 + length2];
        System.arraycopy(array1, beginIndex1, concatArray, 0, length1);
        System.arraycopy(array2, beginIndex2, concatArray, length1, length2);
        return  concatArray;
    }

    /**
     * Causes the currently executing thread to sleep (temporarily cease
     * execution) for the specified number of milliseconds. The thread
     * does not lose ownership of any monitors.
     *
     * This is the same as Thread.sleep () without throwing InterruptedException
     *
     * @param      millis   the length of time to sleep in milliseconds.
     */
    public static void sleep (long millis) {
        try {
            Thread.sleep (millis);
        } catch (InterruptedException e) { }
    }

    /**
     * Left unPad with '0'
     * @param s - original string
     * @return zero unPadded string
     */
    public static String zeroUnPad( String s ) {
        return unPadLeft(s, '0');
    }

    /**
     * Right unPad with ' '
     * @param s - original string
     * @return blank unPadded string
     */
    public static String blankUnPad( String s ) {
        return unPadRight(s, ' ');
    }

    /**
     * Unpad from right.
     * @param s - original string
     * @param c - padding char
     * @return unPadded string.
     */
    public static String unPadRight(String s, char c) {
        int end = s.length();
        if (end == 0)
            return s;
        while ( ( 0 < end) && (s.charAt(end-1) == c) ) end --;
        return ( 0 < end )? s.substring( 0, end ): s.substring( 0, 1 );
    }

    /**
     * Unpad from left.
     * @param s - original string
     * @param c - padding char
     * @return unPadded string.
     */
    public static String unPadLeft(String s, char c) {
        int fill = 0, end = s.length();
        if (end == 0)
            return s;
        while ( (fill < end) && (s.charAt(fill) == c) ) fill ++;
        return ( fill < end )? s.substring( fill, end ): s.substring( fill-1, end );
    }

    /**
     * @return true if the string is zero-filled ( 0 char filled )
     **/
    public static boolean isZero( String s ) {
        int i = 0, len = s.length();
        while ( i < len && ( s.charAt( i ) == '0' ) ){
            i++;
        }
        return ( i >= len );
    }

    /**
     * @return true if the string is blank filled (space char filled)
     */
    public static boolean isBlank( String s ){
        return (s.trim().length() == 0);
    }

    /**
     * Return true if the string is alphanum.
     * <code>{letter digit (.) (_) (-) ( ) (?) }</code>
     *
     **/
    public static boolean isAlphaNumeric ( String s ) {
        int i = 0, len = s.length();
        while ( i < len && ( Character.isLetterOrDigit( s.charAt( i ) ) ||
                s.charAt( i ) == ' ' || s.charAt( i ) == '.' ||
                s.charAt( i ) == '-' || s.charAt( i ) == '_' )
                || s.charAt( i ) == '?' ){
            i++;
        }
        return ( i >= len );
    }

    /**
     * Return true if the string represent a number
     * in the specified radix.
     * <br><br>
     **/
    public static boolean isNumeric ( String s, int radix ) {
        int i = 0, len = s.length();
        while ( i < len && Character.digit( s.charAt( i ), radix ) > -1  ){
            i++;
        }
        return ( i >= len );
    }

    /**
     * Converts a BitSet into an extended binary field
     * used in pack routines. The result is always in the
     * extended format: (16 bytes of length)
     * <br><br>
     * @param b the BitSet
     * @return binary representation
     */
    public static byte[] bitSet2extendedByte ( BitSet b ){
        int len = 128;
        byte[] d = new byte[len >> 3];
        for ( int i=0; i<len; i++ )
            if (b.get(i+1))
                d[i >> 3] |= (0x80 >> (i % 8));
        d[0] |= 0x80;
        return d;
    }

    /**
     * Converts a String to an integer of base radix.
     * <br><br>
     * String constraints are:
     * <li>Number must be less than 10 digits</li>
     * <li>Number must be positive</li>
     * @param s String representation of number
     * @param radix Number base to use
     * @return integer value of number
     * @throws NumberFormatException
     */
    public static int parseInt (String s, int radix) throws NumberFormatException {
        int length = s.length();
        if (length > 9)
            throw new NumberFormatException ("Number can have maximum 9 digits");
        int result = 0;
        int index = 0;
        int digit = Character.digit (s.charAt(index++), radix);
        if (digit == -1)
            throw new NumberFormatException ("String contains non-digit");
        result = digit;
        while (index < length) {
            result *= radix;
            digit = Character.digit (s.charAt(index++), radix);
            if (digit == -1)
                throw new NumberFormatException ("String contains non-digit");
            result += digit;
        }
        return result;
    }

    /**
     * Converts a String to an integer of radix 10.
     * <br><br>
     * String constraints are:
     * <li>Number must be less than 10 digits</li>
     * <li>Number must be positive</li>
     * @param s String representation of number
     * @return integer value of number
     * @throws NumberFormatException
     */
    public static int parseInt (String s) throws NumberFormatException {
        return parseInt (s, 10);
    }

    /**
     * Converts a character array to an integer of base radix.
     * <br><br>
     * Array constraints are:
     * <li>Number must be less than 10 digits</li>
     * <li>Number must be positive</li>
     * @param cArray Character Array representation of number
     * @param radix Number base to use
     * @return integer value of number
     * @throws NumberFormatException
     */
    public static int parseInt (char[] cArray, int radix) throws NumberFormatException {
        int length = cArray.length;
        if (length > 9)
            throw new NumberFormatException ("Number can have maximum 9 digits");
        int result = 0;
        int index = 0;
        int digit = Character.digit(cArray[index++], radix);
        if (digit == -1)
            throw new NumberFormatException ("Char array contains non-digit");
        result = digit;
        while (index < length) {
            result *= radix;
            digit = Character.digit(cArray[index++],radix);
            if (digit == -1)
                throw new NumberFormatException ("Char array contains non-digit");
            result += digit;
        }
        return result;
    }

    /**
     * Converts a character array to an integer of radix 10.
     * <br><br>
     * Array constraints are:
     * <li>Number must be less than 10 digits</li>
     * <li>Number must be positive</li>
     * @param cArray Character Array representation of number
     * @return integer value of number
     * @throws NumberFormatException
     */
    public static int parseInt (char[] cArray) throws NumberFormatException {
        return parseInt (cArray,10);
    }

    /**
     * Converts a byte array to an integer of base radix.
     * <br><br>
     * Array constraints are:
     * <li>Number must be less than 10 digits</li>
     * <li>Number must be positive</li>
     * @param bArray Byte Array representation of number
     * @param radix Number base to use
     * @return integer value of number
     * @throws NumberFormatException
     */
    public static int parseInt (byte[] bArray, int radix) throws NumberFormatException {
        int length = bArray.length;
        if (length > 9)
            throw new NumberFormatException ("Number can have maximum 9 digits");
        int result = 0;
        int index = 0;
        int digit = Character.digit((char)bArray[index++], radix);
        if (digit == -1)
            throw new NumberFormatException ("Byte array contains non-digit");
        result = digit;
        while (index < length) {
            result *= radix;
            digit = Character.digit((char)bArray[index++],radix);
            if (digit == -1)
                throw new NumberFormatException ("Byte array contains non-digit");
            result += digit;
        }
        return result;
    }

    /**
     * Converts a byte array to an integer of radix 10.
     * <br><br>
     * Array constraints are:
     * <li>Number must be less than 10 digits</li>
     * <li>Number must be positive</li>
     * @param bArray Byte Array representation of number
     * @return integer value of number
     * @throws NumberFormatException
     */
    public static int parseInt (byte[] bArray) throws NumberFormatException {
        return parseInt (bArray,10);
    }

    private static String hexOffset (int i) {
        i = (i>>4) << 4;
        int w = i > 0xFFFF ? 8 : 4;
        try {
            return zeroPad (Integer.toString (i, 16), w);
        } catch (Exception e) {
            // should not happen
            return e.getMessage();
        }
    }

    /**
     * @param b a byte[] buffer
     * @return hexdump
     */
    public static String hexdump (byte[] b) {
        return hexdump (b, 0, b.length);
    }

    /**
     * @param b a byte[] buffer
     * @param offset starting offset
     * @param len the Length
     * @return hexdump
     */
    public static String hexdump (byte[] b, int offset, int len) {
        StringBuffer sb    = new StringBuffer ();
        StringBuffer hex   = new StringBuffer ();
        StringBuffer ascii = new StringBuffer ();
        String sep         = "  ";
        String lineSep     = System.getProperty ("line.separator");

        for (int i=offset; i<len; i++) {
            char hi = Character.forDigit ((b[i] >> 4) & 0x0F, 16);
            char lo = Character.forDigit (b[i] & 0x0F, 16);
            hex.append (Character.toUpperCase(hi));
            hex.append (Character.toUpperCase(lo));
            hex.append (' ');
            char c = (char) b[i];
            ascii.append ((c >= 32 && c < 127) ? c : '.');

            int j = i % 16;
            switch (j) {
                case 7 :
                    hex.append (' ');
                    break;
                case 15 :
                    sb.append (hexOffset (i));
                    sb.append (sep);
                    sb.append (hex.toString());
                    sb.append (' ');
                    sb.append (ascii.toString());
                    sb.append (lineSep);
                    hex   = new StringBuffer ();
                    ascii = new StringBuffer ();
                    break;
            }
        }
        if (hex.length() > 0) {
            while (hex.length () < 49)
                hex.append (' ');

            sb.append (hexOffset (len));
            sb.append (sep);
            sb.append (hex.toString());
            sb.append (' ');
            sb.append (ascii.toString());
            sb.append (lineSep);
        }
        return sb.toString();
    }

    /**
     * pads a string with 'F's (useful for pinoffset management)
     * @param s an [hex]string
     * @param len desired length
     * @return string right padded with 'F's
     */
    public static String strpadf (String s, int len) {
        return padRight(s,len,'F');
    }

    /**
     * reverse the effect of strpadf
     * @param s F padded string
     * @return trimmed string
     */
    public static String trimf (String s) {
        if (s != null) {
            int l = s.length();
            if (l > 0) {
                while (--l >= 0) {
                    if (s.charAt (l) != 'F')
                        break;
                }
                s = l == 0 ? "" : s.substring (0, l+1);
            }
        }
        return s;
    }

    /**
     * compact a byte array
     * @param b
     * @param len
     * @return
     */
    public static byte[] compact(byte[] b, int len) {
        if (b.length <= len) return b;
        else {
            byte[] buf = new byte[len];
            System.arraycopy(b, 0, buf, 0, len);
            return buf;
        }
    }

    public static byte[] subarray(byte[] b,int offset, int len) {
        if(b.length<offset+len){
            len=b.length-offset;
        }
        byte[] buf = new byte[len];
        System.arraycopy(b, offset, buf, 0, len);
        return buf;
    }

    public static byte[] unpadRight(byte[] tgt,byte padding){
        int len = tgt.length;
        for(;len > 0; len --){
            if(tgt[len - 1] != padding){
                break;
            }
        }
        if( len > 0){
            byte[] rslt = new byte[len];
            System.arraycopy(tgt, 0, rslt, 0, len);
            return rslt;
        }
        return new byte[0];
    }


    /**
     * 将字节数组转成给定的正整型表示.
     *
     * @param bytes 字节数组
     * @param offset 便宜量
     * @param len 转换的长度
     * @param isBigEndian 是否高位在前
     * @return
     */
    public static int bytesToInt(byte[] bytes,int offset,int len,boolean isBigEndian){
        if(len > 4)
            throw new IllegalArgumentException("len should be under 4!");
        if(len == 4){
            int last = bytes[offset] & 0xff;
            if(isBigEndian){
                last = bytes[offset + len] & 0xff;
            }
            if(last >= 8){
                throw new IllegalArgumentException("illegal input:"+ Dump.getHexDump(bytes, offset, len));
            }
        }

        int rslt = 0;
        for(int i = 0 ; i < len ; i++){
            int j = i;
            if(isBigEndian){
                j = len - i -1;
            }
            rslt += ((bytes[j] & 0xff) << (i * 8));
        }
        return rslt;
    }

    public static byte[] intToBytes(int value,boolean isBigEndian){
        if(value < 0){
            throw new IllegalArgumentException("illegal input:"+value);
        }
        int len = 0;
        if((value & 0xFFFFFF00) == 0){
            len = 1;
        }else if ((value & 0xFFFF0000) == 0){
            len = 2;
        }else if ((value & 0xFF000000) == 0){
            len  = 3;
        }else{
            len = 4;
        }

        return intToBytes(value, len, isBigEndian);
    }

    /**
     * 将正整型转成具体的字节数组表示
     *
     * @param value 待转换的字节数组
     * @param len 被转换的长度,若不足则会被填充00
     * @param isBigEndian 是否高位在前
     * @return
     */
    public static byte[] intToBytes(int value,int len,boolean isBigEndian){
        /**不再判断长度限制**/
        if(value < 0){
            throw new IllegalArgumentException("illegal input:"+value);
        }

        byte[] bs = new byte[len];
        Arrays.fill(bs, (byte)0x00);
        for(int i = 0; i < len; i++){
            int j = i;
            if(isBigEndian){
                j = len - i -1;
            }
            bs[j] = (byte)((value >> (i * 8)) & 0xff);
        }
        return bs;
    }

    public static int lastIndexOf(final char[] array, final char objectToFind) {
        return lastIndexOf(array, objectToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(final char[] array, final char valueToFind, int startIndex) {
        if (array == null || array.length == 0) {
            return -1;
        }
        if (startIndex < 0) {
            return -1;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 随机生成字符串（包含数据、大小写字母）
     * @param length 长度
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            boolean isChar = (random.nextInt(2) % 2 == 0);// 输出字母还是数字
            if (isChar) { // 字符串
                int choice = (random.nextInt(2) % 2 == 0) ? 65 : 97; // 取得大写字母还是小写字母
                ret.append((char) (choice + random.nextInt(26)));
            } else { // 数字
                ret.append(Integer.toString(random.nextInt(10)));
            }
        }
        return ret.toString();
    }

    /**
     * 随机生成数字字符串
     * @param length 长度
     * @return
     */
    public static String getRandomNumberString(int length) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            ret.append(Integer.toString(random.nextInt(10)));
        }
        return ret.toString();
    }

    /**
     * 随机生成十六进制字符串
     * @param length 长度
     * @return
     */
    public static String getRandomHexString(int length) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            ret.append(Integer.toHexString(random.nextInt(256)));
        }
        return ret.toString().substring(0,length).toUpperCase(Locale.ENGLISH);
    }

    /**
     * 从分转为元
     * @param amount 分为单位的金额
     * @return
     */
    public static String formatAmountFTY(String amount){
        BigDecimal amt=new BigDecimal(amount);
        amt=amt.divide(new BigDecimal("100"));
        return amt.toString();
    }

    /**
     * 从分转为元
     * @param amount 分为单位的金额
     * @return
     */
    public static String formatAmountFTY(float amount){
        BigDecimal amt=new BigDecimal(amount);
        amt=amt.divide(new BigDecimal("100"));
        return amt.toString();
    }

    /**
     * 从分转为万
     * @param amount 分为单位的金额
     * @return
     */
    public static String formatAmountFTW(String amount){
        BigDecimal amt=new BigDecimal(amount);
        amt=amt.divide(new BigDecimal("1000000"));
        return amt.toString();
    }

    /**
     * 从分转为万
     * @param amount 分为单位的金额
     * @return
     */
    public static String formatAmountFTW(float amount){
        BigDecimal amt=new BigDecimal(amount);
        amt=amt.divide(new BigDecimal("1000000"));
        return amt.toString();
    }

    /**
     * 从元转为分
     * @param amount 元为单位的金额
     * @return
     */
    public static String formatAmountYTF(String amount){
        BigDecimal amt=new BigDecimal(amount);
        amt=amt.multiply(new BigDecimal("100"));
        amt=amt.setScale(0, BigDecimal.ROUND_DOWN);
        return amt.toString();
    }

    /**
     * 从元转为分
     * @param amount 元为单位的金额
     * @return
     */
    public static String formatAmountYTF(long amount){
        BigDecimal amt=new BigDecimal(amount);
        amt=amt.multiply(new BigDecimal("100"));
        amt=amt.setScale(0, BigDecimal.ROUND_DOWN);
        return amt.toString();
    }


    /**
     * 从元转为分
     * @param amount 元为单位的金额
     * @return
     */
    public static String formatAmountYTF(double amt){
        String amt2=""+amt;
        return formatAmountYTF(amt2);
    }

    public static String randomUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static int lrcValue(byte[] data, int start, int length){
        try
        {
            int sum = 0;
            for (int i = start; i < start + length; i++)
            {
                sum ^= data[i];
            }
            return sum;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static class Dump {
        private static final byte[] highDigits;

        private static final byte[] lowDigits;

        // initialize lookup tables
        static {
            final byte[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'A', 'B', 'C', 'D', 'E', 'F' };

            int i;
            byte[] high = new byte[256];
            byte[] low = new byte[256];

            for (i = 0; i < 256; i++) {
                high[i] = digits[i >>> 4];
                low[i] = digits[i & 0x0F];
            }

            highDigits = high;
            lowDigits = low;
        }
        public static String getHexDump(byte[] bytes,int offset,int length) {

            if (bytes == null || bytes.length == 0)
                return "empty";
            if(offset >= bytes.length){
                return "out of length,totallen:"+bytes.length+",offset:"+offset;
            }

            StringBuffer out = new StringBuffer();

            int byteValue = bytes[offset] & 0xFF;
            out.append((char) highDigits[byteValue]);
            out.append((char) lowDigits[byteValue]);

            for (int i=offset + 1; (i<bytes.length && (i-offset )<length); i++) {
                out.append(' ');
                byteValue = bytes[i] & 0xFF;
                out.append((char) highDigits[byteValue]);
                out.append((char) lowDigits[byteValue]);
            }
            return out.toString();
        }

        public static String getHexDump(byte[] bytes) {
            return getHexDump(bytes,0,bytes.length);
        }

    }
}

