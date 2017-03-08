/**
 * Created by ilove on 23/02/2017.
 */
public class Sort {
    /**
     * 原理：每一位上的数都和它之前的数进行比较，比到最后就完成了排序，此条实现：从左到右升序排列
     * for i = 1..ints.length-1 的每一次循环时，
     假定 ints的[0,i-1]（闭区间）有序，然后把 ints[i] 插到 ints的[0,i-1] 里面，并把插入位置之后的元素依次挤到 ints[i] 处
     * @param ints
     */
    public void insertSort(int[] ints){
        if (null==ints || ints.length<2)
            return;
        for (int i=1; i<ints.length; i++){
            int temp=ints[i];
            int j=i-1;//前面i从1开始计数
            while(j>-1 && temp<ints[j]){
                ints[j+1]=ints[j];//这里需要注意：j+1不等价于i
                j--;
            }
            ints[j+1]=temp;
        }

    }

    /**
     * 希尔排序法：和二分排序法几乎一样的原理，就是不停的二分，直到为1
     *
     * @param ints
     */
    public static void shellSort(int[] ints){
        if (null == ints || ints.length<2){
             return;
        }
        for(int d=ints.length/2;d>0;d/=2){
            for(int i=d;i<ints.length;i++){
                int temp = ints[i];
                int j=i-d;//随着i的增大，j值为01234。。。
                while(j>=0 && temp<ints[j]){
                    ints[j+d]= ints[j];//拿右边的数据去和左边的比较
                    j-=d;
                }
                ints[j+d]=temp;
            }
        }
    }

    /**
     * 冒泡排序需对所有的数都进行一次比较，依次比较相邻的两个数
     * @param ints
     */
    public void bubbleSort(int[] ints){
        if(null==ints || ints.length<2){
            return;
        }
        boolean flag;
        for (int i=0;i<ints.length-1;i++){
            flag=false;
            for (int j=0;j<ints.length-1-i;j++){
                if (ints[j]>ints[j+1]){
                    int temp=ints[j];
                    ints[j]=ints[j+1];
                    ints[j+1]=temp;
                    flag=true;
                }
            }
            if(false==flag){
                return;
            }
        }
    }

    /**
     * 选择排序
     * @param ints
     */
    public void selectSort(int[] ints){
        if (null == ints || ints.length<2)
            return;
        for(int i=0;i<ints.length;i++){
            int k=i;
            for (int j=i+1;j<ints.length;j++){
                if(ints[j]<ints[k]){
                    k=j;
                }
            }
            if(k!=i){
                int temp=ints[k];
                ints[k]=ints[i];
                ints[i]=temp;
            }
        }
    }

    /**
     * 归并排序
     * 原理：分治法
     * @param ints
     * @param low
     * @param high
     */
    public void mergeSort(int[] ints,int low, int high){
        int mid=(low+high)/2;
        if(low<high){
            mergeSort(ints,low,mid);
            mergeSort(ints,mid+1,high);
            merge(ints,low,mid,high);
        }
    }
    private void merge(int a[],int low,int mid,int high){
        int[] temp=new int[high-low+1];
        int i=low;
        int j=mid+1;
        int k=0;

        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++]=a[i++];
            }else{
                temp[k++]=a[j++];
            }
        }
        while(i<=mid){
            temp[k++]=a[i++];
        }

        while(j<=high){
            temp[k++]=a[j++];
        }

        for (int t=0;t<temp.length;t++){
            a[low+t]=temp[t];
        }
    }

    /**快排
     * @param ints
     * @param low
     * @param high
     */
    public void quickSort(int[] ints,int low,int high){
        if(null==ints||ints.length<2){
            return;
        }
        if(low<high){
            int mid=partition(ints,low,high);
            quickSort(ints,low,mid-1);
            quickSort(ints,mid+1,high);
        }
    }

    private int partition(int[] ints,int low,int high){
        int pivot =ints[low];

        while(low<high){
            while(low<high && ints[high]>=pivot){
                high--;
            }
            ints[low]=ints[high];
            while(low<high && ints[low]<=pivot){
                low++;
            }
            ints[high]=ints[low];
        }
        ints[low]=pivot;
        return low;
    }

    /**
     * 堆排序
     * @param ints
     */
    public void heapSort(int[] ints){
        if(null==ints||ints.length<2){
            return;
        }
        buildMaxHeap(ints);

        for (int i=ints.length-1;i>=0;i--){
            int temp =ints[0];
            ints[0]=ints[i];
            ints[i]=temp;
            adjustHeap(ints,i,0);
        }
    }

    private void buildMaxHeap(int[] ints){
        int mid =ints.length/2;
        for (int i=mid;i>=0;i--){
            adjustHeap(ints,ints.length,i);
        }
    }

    private void adjustHeap(int[] ints,int size,int parent){
        int left=2*parent+1;
        int right=2*parent+2;

        int largest = parent;
        if(left < size && ints[left] > ints[parent]){
            largest = left;
        }

        if (right<size && ints[right] > ints[largest]){
            largest = right;
        }

        if (parent != largest){
            int temp = ints[parent];
            ints[parent] = ints[largest];
            ints[largest] = temp;
            adjustHeap(ints,size,largest);
        }
    }

    /**
     * 基数排序
     * 原理：分配加收集
     * @param ints
     * @param begin
     * @param end
     * @param digit
     */
    public void radixSort(int[] ints,int begin,int end, int digit){
        final int radix=10;
         int[] count = new int[radix];
         int[] bucket= new int[end-begin+1];

         for (int i=1;i<=digit;i++){
             for (int j=0;j<radix;j++){
                 count[j]=0;
             }

             for (int j=begin;j<=end;j++){
                 count[j]=0;
             }

             for(int j=begin;j<=end;j++){
                 int index= getDigit(ints[j],i);
                 count[index]++;
             }

             for(int j=1;j<radix;j++){
                 int index=getDigit(ints[j],i);
                 bucket[count[index]-1]=ints[j];
                 count[index]--;
             }

             for (int j=0;j<bucket.length;j++){
                 ints[j]=bucket[j];
             }
         }
    }

    private int getDigit(int x,int d){
        String div ="1";
        while(d>=2){
            div +="0";

            d--;
        }
        return x/Integer.parseInt(div)%10;
    }
    public static void main(String args[]){
        int[] arr={55,34,21,40,5,98,4};
        shellSort(arr);
    }



}
