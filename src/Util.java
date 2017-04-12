/**
 * Created by ilove on 12/04/2017.
 */
public class Util {
    /**
     * 十进制转任意进制小工具
     * @param number
     * @param jinzhi
     */
    public static void BaseConsversion(int number,int jinzhi){
        String res="";
        while(number != 0){
            res += ""+number % jinzhi;
            number = number / jinzhi;
        }
        String  s="";
        for(int i=res.length()-1;i>-1;i--){
            s += res.charAt(i);
        }
        System.out.println(s);
    }
    public static void main(String[] args){
        BaseConsversion(101,2);
    }
}
