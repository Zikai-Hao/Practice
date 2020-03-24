package SimpleArrayList;

import java.util.ArrayList;

/**
 * 仿写简单的ArrayList源码
 */
public class SimpleArrayList {
    //检查并发修改
    private transient int modcount = 0;
    //初次扩容容量
    private final int DEFAULT_SIZE = 10;
    //容量
    int size;
    //初始化
    private final Object[] DEFAULT_LIST = {};
    //储存结构
    transient private Object[] elementData;
    //无参构造
    public SimpleArrayList(){
        this.elementData = DEFAULT_LIST;
    }
    public SimpleArrayList(int size){
        if(size>0){
            this.elementData = new Object[size];
        }else if(size==0){
            this.elementData = DEFAULT_LIST;
        }else {
            throw new IllegalArgumentException("非法参数");
        }


    }

    public boolean add(Object obj){
        ensureSize(size+1);
        this.elementData[size++]=obj;
        return true;
    }

    private void ensureSize(int minSize){
        //如果是初次扩容，则使用默认的容量
        if(this.elementData==DEFAULT_LIST){
            minSize = Math.max(DEFAULT_SIZE,minSize);
        }
        //扩容
        if(minSize-elementData.length>0){
            int oldSize = elementData.length;
            int newSize = oldSize + (oldSize>>1);
            if(newSize-minSize<0){
                newSize = minSize;
            }
            Object[] newElement = new Object[newSize];
            System.arraycopy(elementData,0,newElement,0,elementData.length);
            this.elementData = newElement;
        }
    }

    private void rangeCheck(int index){
        if(index >= size || size < 0){
            throw  new IndexOutOfBoundsException("数组越界");
        }
    }
    public Object get(int index){
        rangeCheck(index);
        return elementData[index];
    }

    public Object set(int index,Object obj){
        rangeCheck(index);
        Object oldValue = elementData[index];
        elementData[index]=obj;
        return oldValue;

    }

    public int indexOf(Object obj){
        if(obj==null){
            for(int i=0; i < size; i++){
                if(elementData[i] == null){
                    return i;
                }
            }
        }else {
            for(int i=0; i < size;i++){
                if(elementData[i].equals(obj))
                    return i;
            }
        }
        return -1;
    }

    public Object remove(int index){
        rangeCheck(index);

        modcount++;

        Object oldValue = elementData[index];

        int numMoved = size - index +1;
        if(numMoved>0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }
    public int size(){
        return size;
    }
}
