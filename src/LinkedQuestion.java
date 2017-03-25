import java.util.function.DoubleConsumer;

import static com.sun.tools.javac.jvm.ByteCodes.ret;

/**
 * Created by ilove on 13/03/2017.
 */
public class LinkedQuestion {

    /**
     * 删除链表的中间节点和a／b处的节点
     */

    /**\
     * 反转单项链表
     */
    class MonoNode{
        public int value;
        public MonoNode next;
        public MonoNode(int data){
            this.value=data;
        }
    }
    public MonoNode reverseList(MonoNode head){
        MonoNode pre= null;
        MonoNode next= null;
        while (head != null){
            //记录next Node
            next = head.next;
            //改变head.next
            head.next = pre;
            //更新pre
            pre = head;
            //更新head
            head = next;
        }
        return pre;
    }

    /**
     * 反相双向链表
     */
    class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data){
            this.value = data;
        }

    }
    //相对于反转单向链表  在于节点的新连接点不同
    public DoubleNode reverDoubleList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转部分单项链表
     *  class MonoNode{
         public int value;
         public MonoNode next;
         public MonoNode(int data){
            this.value=data;
        }
     }
     原理：找出form之前和to之后的节点，然后将之间的节点反转  再连接from之前和to之后的节点  from=1是特例
     */
    public MonoNode reversePart(MonoNode head,int from, int to){
        int len=0;
        MonoNode nodehead=head;
        MonoNode fPre=null;
        MonoNode tPos=null;
        //计算链表长度，并找出from之前的节点和to之后的节点
        while(nodehead != null){
            len++;
            fPre = (len == from-1 ? nodehead : fPre);
            tPos = (len == to + 1 ? nodehead : tPos);
            nodehead = nodehead.next;
        }

        if(from > to || from < 1 || to > len){
            return head;
        }
        //开始反转，此nodehead 为需反转节点中头节点
        nodehead = (fPre == null ? head : fPre.next);
        //需反转的第二个节点
        MonoNode node2 = nodehead.next;
        //？？？
        nodehead.next = tPos;
        //新建节点 next=null  ？？？
        MonoNode next =null;
        //从node2开始  node2本身就是nodehead.next  nodehead为需反转的头节点
        while(node2 != tPos){/* 注意注意注意注意注意注意条件   node2代表头节点的下一个节点 如果下一个节点为tpos  则代表连接完成！！  */
            //记录next
            next = node2.next;
            //改变head2.next
            node2.next = nodehead;
            //更新next1
            nodehead = node2;
            //更新node2
            node2 = next;
        }

        if(fPre != null){
            //
            fPre.next = nodehead;
            return head;
        }

        return nodehead;
    }

    /**
     * 环形单链表的约瑟夫问题
     * 输入：一个环形单向链表的头节点head和报数的值
     * 返回：最后生存下来的节点，且这个节点自己组成环形单向链表，其他节点都删掉
     */
    public int getLive(int i,int m){
        if(i ==1){
            return 1;
        }
        //计算生存节点,找规律得出
        return (getLive(i-1,m)+m-1)%i+1;
    }
    public MonoNode joesphusProblem(MonoNode head,int m){
        if(head == null || head.next == head || m<1){
            return head;
        }
        MonoNode cur = head.next;
        int temp = 1;
        while (cur!=head){
            temp++;
            cur = cur.next;
        }
        temp = getLive(temp,m);
        while(--temp != 0){
            head = head.next;
        }
        head.next = head;
        return head;
    }


    public static void main(String args[]){
        LinkedQuestion list=new LinkedQuestion();
        MonoNode node= list.new MonoNode(1);
        node.next=list.new MonoNode(2);
        node.next.next=list.new MonoNode(3);
        node.next.next.next=node;
        MonoNode nodes= list.joesphusProblem(node,3);

        System.out.print(nodes.value);


    }
}
