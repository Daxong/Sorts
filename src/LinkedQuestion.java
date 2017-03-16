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
    public static void main(String args[]){
        LinkedQuestion list=new LinkedQuestion();
        MonoNode node= list.new MonoNode(1);
        node.next=list.new MonoNode(2);
        node.next.next=list.new MonoNode(3);
        MonoNode nodes= list.reverseList(node);
        while(nodes !=null){
            System.out.println(nodes.value);
            nodes=nodes.next;
        }

    }
}
