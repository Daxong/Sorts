/**
 * Created by ilove on 12/04/2017.
 */
public class Util {
    /**
     * 十进制转任意进制小工具
     * @param number
     * @param jinzhi
     */
    public static void baseConsversion(int number,int jinzhi){
        String res="";
        String[] overten={"a","b","c","d","e","f","g","h","j","k","l","i"};
        while(number != 0){
            int mod= number % jinzhi;
            if(mod-9>0){
                res += overten[mod-10];

            }else{
                res += ""+number % jinzhi;
            }

            number = number / jinzhi;
        }
        String  s="";
        for(int i=res.length()-1;i>-1;i--){
            s += res.charAt(i);
        }
        System.out.println(s);
    }
    public static void main(String[] args){
        baseConsversion(255,11);
    }
}
