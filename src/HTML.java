import java.util.Stack;
import java.util.StringTokenizer;
import java.io.*;
/**
 * Created by ilove on 13/04/2017.
 */
    //检查HTML文档的括号匹配
public class HTML {
    //嵌套类，存放HTML标志
    public static class Tag {
        String name;//标志名
        boolean opening;//起始标志
        public Tag() {//默认构造方法
        name = "";
        opening=false;
    }
    public Tag(String nm, boolean type) {//构造方法
        name = nm;
        opening = type;
    }
    public boolean isOpening() { return opening; }//判断是否起始标志
    public String getName() { return name; }//返回标志名称
}

    //通过缩格显示匹配标志的层次
    private void indent(int level) {
        for (int k=0; k<level; k++)
            System.out.print("\t│");
    }

//检查每个起始标志是否都对应于一个结束标志
    public boolean isHTMLMatched(Tag[] tag) {
        int level = 0;//标志的层次
        Stack S = new Stack();//存放标志的栈
        for (int i=0; (i<tag.length) && (tag[i] != null); i++) {//逐一检查各标志
            if (tag[i].isOpening()) {//若遇到起始标志，则
                S.push(tag[i].getName());//压之入栈
                indent(level++);
                System.out.println("\t┌"+tag[i].getName());
            } else {//否则，即当前标志为结束标志，故
                if (S.isEmpty())//若此时栈空，则
                    return false;//报告"不匹配"
                if (!((String) S.pop()).equals(tag[i].getName())) {
                    //否则，若当前标志与弹出的标志不匹配，则 return false;//报告"不匹配"
                    indent(--level);
                }
                System.out.println("\t└"+tag[i].getName());
            }//else
         }//for

    //至此，已扫描完整个文档


    if (S.isEmpty())//若此时栈为空，则
        return true;//报告"匹配"
    else//否则
        return false;//报告"不匹配"
     }//isHTMLMatched

    public final static int CAPACITY = 1000;//数组的最大容量

    // 从HTML文档中提取标志，依次存入数组
    public Tag[] parseHTML(BufferedReader r) throws IOException {

        String line;//文档中的一行
        boolean inTag = false;//标志:当前是否扫描到标志
        Tag[] tag = new Tag[CAPACITY];//存放标志的数组
        int count = 0;//标志的计数器
        while ((line = r.readLine()) != null) {//依次读入文档的各行
            StringTokenizer st = new StringTokenizer(line,"<> \t",true);//标志的特征为尖括号
            while (st.hasMoreTokens()) {
                String token = (String) st.nextToken();
                if (token.equals("<"))//若扫描到'<'，说明遇到了下一标志，故
                    inTag = true;//将当前状态设为"正在扫描标志"
                else if (token.equals(">"))//若扫描到'>'，说明上一标志扫描完毕，故
                    inTag = false;//将当前状态设为"处于标志之外"
                else if (inTag) { //若正在扫描一个标志
                    if ( (token.length() == 0) || (token.charAt(0) != '/') ) //若是起始标志，则

                        tag[count++] = new Tag(token, true);//加入之
                    else
                        tag[count++] = new Tag(token.substring(1), false); //加入一个结束标志(跳过首字符'/')
            }//请注意:所有非标志部分均被忽略了
        }//while
    }//while
    return tag; //返回标志数组
}

public static void main(String[] args) throws IOException {
    BufferedReader stdr = new BufferedReader(new InputStreamReader(System.in));//标准 输入
    HTML tagChecker = new HTML();
    if (tagChecker.isHTMLMatched(tagChecker.parseHTML(stdr)))
        System.out.println("该文件符合HTML的标志匹配"); else
        System.out.println("该文件不符合HTML的标志匹配"); }

}
