import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by ilove on 03/03/2017.
 *
 *
 */
public class jobalgorithm {
    /**
     * 一个栈一次压入1，2，3，4，5，那么从栈顶到栈底分别为5，4，3，2，1。
     * 将这个栈转置后，从栈顶到栈底为1，2，3，4，5，也就是实现栈中元素的逆序，
     * 但是只能用递归函数来实现，不准使用其他数据结构
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result=stack.pop();
        if (stack.isEmpty()){
            return result;
        }else{
            int last=getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i=getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    /**
     * 一个栈中元素的类型为整形，现在想将该栈从顶到底按照从大到小的顺排列，
     * 只许申请一个栈，除此之外，可以申请新的变量，但不能申请额外的数据结构，如何完成排序
     * @param args
     */


    /**
     * 汉诺塔游戏 变更游戏规则：不能直接从左移到右反之亦是
     * 非递归解法
     * @param args
     */
    public enum Action{
        /**
         * L=left M=middle R=right
         */
        No,LToM,MToL,MToR,RToM
    }
    public int hanoiProblem(int num,String left,String mid,String right){
        /**
         * using three stack structure as column
         */
        Stack<Integer> lS=new Stack<>();
        Stack<Integer> mS=new Stack<>();
        Stack<Integer> rS=new Stack<>();
        /**
         * 给三根栈预先压入最大的数值，以防出现比数据值小的bug
         */
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        /**
         * 使用for循环给lS循环push值，完成数据初始化
         */
        for (int i=num;i>0;i--){
            lS.push(i);
        }

        Action[] record={Action.No};
        /**
         * 步骤次数
         */
        int step=0;
        /**
         * num是最大数  num+1是因为初始化中预先给每一个栈压入了一个MAX_VALUE
         */
        //需再次琢磨
        while (rS.size()!=num+1){
            step+= fStackTotStack(record,Action.MToL,Action.LToM,lS,mS,left,mid);
            step+= fStackTotStack(record,Action.LToM,Action.MToL,mS,lS,mid,left);
            step+= fStackTotStack(record,Action.RToM,Action.MToR,mS,rS,mid,right);
            step+= fStackTotStack(record,Action.MToR,Action.RToM,rS,mS,right,mid);
        }
        return  step;
    }

    /**
     *
     * @param record
     * @param preNoAct 上一步行动
     * @param nowAct 当前行动
     * @param fStack 起始栈
     * @param tStack 目标栈
     * @param from 起始方位
     * @param to 目标方位
     * 打印语句示例：Move 1 from mS to lS
     * @return
     */
    public static int fStackTotStack(Action[] record,Action preNoAct,Action nowAct,
                     Stack<Integer> fStack,Stack<Integer> tStack,String from,String to){
        //如果上一步不为No ？？？？啥意思
        if (record[0]!=preNoAct && fStack.peek() < tStack.peek()){
            tStack.push(fStack.pop());
            System.out.println("Move"+tStack.peek()+" from "+from+" to "+to);
            record[0]=nowAct;
            return 1;
        }
        return 0;
    }

    /**
     * 实现：
     * 输入：整数数组arr，窗口大小为w。
     * 输出：一个长度为n-w+1de数组res，res[i]表示每一种窗口状态下的最大值
     * 【4，3，5】4，3，3，6，7  窗口最大值为5
     *  4【3，5，4】3，3，6，7   窗口最大值为5
     * @param arr
     * @param w
     */
    public int[] getMaXWindow(int[] arr,int w){
        if(arr == null || w<1 || arr.length<w){
            return null;
        }
        LinkedList<Integer> qmax=new LinkedList<>();
        int[] res =new int[arr.length-w+1];
        int index=0;
        for (int i=0;i<arr.length;i++){
            while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst()==i-w){
                qmax.pollFirst();
            }
            //i<w-1不构成窗口
            if(i>=w-1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    public static void main(String args[]){
        Stack<Integer> stack=new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverse(stack);
        System.out.println(stack.pop()+" "+stack.pop()+" "+stack.pop());
        Queue<Integer> q=new LinkedList<Integer>();


    }
}
