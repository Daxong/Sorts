import java.util.Arrays;

/**
 * 需要考虑到字符集的影响
 */
public class TestSplitString {
    public static String splitString(String str,int byteNum) throws Exception {
        //将完整的字符串变为char[]
        char[] c = str.toCharArray();
        //将c数组打印
        System.out.println(Arrays.toString(c));
        //Constructs a new String by decoding the specified subarray of bytes using the platform's default charset.
        return new String(c,0,getToIndex(c, byteNum));
    }

    public static int getToIndex(char[] c,int byteNum) throws Exception{
        int num = 0;
        for(int i = 0;i < c.length;i++){
            //获取c的长度
            num += ((c[i] + "").getBytes("utf-8")).length;
            //长度达不到给定参数 继续加长度
            if(num >= byteNum)

                return i + 1;
            if(i + 1 == c.length)
                //遍历到最后倒数第二步，也就没必要继续遍历了
                return c.length;
        }
        return 0;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(splitString("我ABC们DEF",5));
    }
}
