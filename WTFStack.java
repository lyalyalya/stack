import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WTFStack {
    Integer curMax;
    List<LinkedList<Integer>> list=new ArrayList<>();
    //
    void push(Integer item){
        if(list.isEmpty()){
            list.add(new LinkedList<>());
            list.get(0).add(item);
            curMax=item;
            //System.out.println(list.get(list.size()-1));
        }else{
            if(item>curMax){
                list.add(new LinkedList<>());
                list.get(list.size()-1).add(item);
                //System.out.println(list.get(list.size()-1));
                curMax=item;
                //System.out.println("max"+curMax);
            }else{
                list.get(list.size()-1).add(item);
            }

        }
    }
    Integer pop(){
        if (list.isEmpty()){
            System.out.println("no elems");
            return null;
        } else {
            Integer last=list.get(list.size()-1).getLast();
            if((last<list.get(list.size()-1).getFirst())){
                list.get(list.size()-1).removeLast();

            }else if(last.equals(list.get(list.size()-1).getFirst())){
                if(list.get(list.size()-1).size()>1){
                    list.get(list.size()-1).removeLast();

                }else{
                    list.remove(list.size()-1);
                    if(!list.isEmpty()){
                        curMax=list.get(list.size()-1).getFirst();
                    }else curMax=null;
                }

            }else{
                if(list.size()-1<2) {
                    curMax=list.get(0).getLast();
                    //System.out.println("maxpop2"+curMax);

                }else {
                    curMax = list.get(list.size() - 2).getFirst();
                    list.remove(list.size() - 1);
                    //System.out.println("maxpop3"+curMax);
                }
            }
            //System.out.println(last);
            return last;
        }
    }
    Integer getCurMax(){
        if(list.isEmpty()){
            //System.out.println("empty");
            return null;
        }
        else return curMax;
    }

    public static void main(String[] args) throws WTFStack.WTFException {
        testOldImpl();
        testAllThreeValues();
        testOneTwo();

    }

    private static void testOldImpl() throws WTFException {
        WTFStack stack = new WTFStack();
        stack.push(5);
        stack.push(4);
        //stack: 4, 5
        if (stack.getCurMax() != 5) throw new WTFException("Incorrect result. Expected: " + 5 + ", Actual: " + stack.getCurMax());
        stack.push(4);
        stack.push(7);
        stack.push(4);
        //stack: 4, 7, 4, 4, 5
        if (stack.getCurMax() != 7) throw new WTFException("Incorrect result. Expected: " + 7 + ", Actual: " + stack.getCurMax());
        stack.push(7);
        stack.pop();
        //stack: 4,7, 4, 4, 5
        //System.out.println(stack.curMax);
        if (stack.getCurMax() != 7) throw new WTFException("Incorrect result. Expected: " + 7 + ", Actual: " + stack.getCurMax());
        stack.pop();
        stack.pop();
        //stack:  4,4, 5
        if (stack.getCurMax() != 5) throw new WTFException("Incorrect result. Expected: " + 5 + ", Actual: " + stack.getCurMax());
        stack.pop();
        //stack: 4 5
        if (stack.getCurMax() != 5) throw new WTFException("Incorrect result. Expected: " + 5 + ", Actual: " + stack.getCurMax());
        stack.pop();
        //stack:  5
        if (stack.getCurMax() != 5) throw new WTFException("Incorrect result. Expected: " + 5 + ", Actual: " + stack.getCurMax());

        stack.pop();
        if (stack.getCurMax() != null) throw new WTFException("Incorrect result. Expected: " + null + ", Actual: " + stack.getCurMax());

        stack.pop();
        if (stack.getCurMax() != null) throw new WTFException("Incorrect result. Expected: " + null + ", Actual: " + stack.getCurMax());
        stack.pop();
        stack.pop();

    }
    private static void testOneTwo() throws WTFException {
        WTFStack stack = new WTFStack();
        for (int i = 0; i < 10; i++) {
            stack.push(1);
        }

        for (int i = 0; i < 10; i++) {
            if (stack.getCurMax() != 1) throw new WTFException("Incorrect result. Expected: " + 1 + ", Actual: " + stack.getCurMax());
            stack.pop();
        }
        for (int i=0;i<5;i++){
            stack.push(1);
            if (stack.getCurMax() != 1) throw new WTFException("Incorrect result. Expected: " + 1 + ", Actual: " + stack.getCurMax());
        }
        stack.push(2);
        if (stack.getCurMax() != 2) throw new WTFException("Incorrect result. Expected: " + 2 + ", Actual: " + stack.getCurMax());
        stack.pop();
        if (stack.getCurMax() != 1) throw new WTFException("Incorrect result. Expected: " + 1 + ", Actual: " + stack.getCurMax());
        stack.pop();
        if (stack.getCurMax() != 1) throw new WTFException("Incorrect result. Expected: " + 1 + ", Actual: " + stack.getCurMax());
    }

    private static void testAllThreeValues() throws WTFException {
        WTFStack stack = new WTFStack();
        for (int i = 0; i < 100_000; i++) {
            stack.push(3);
        }

        for (int i = 0; i < 100_000; i++) {
            Integer max = stack.getCurMax();
            if (max != 3) throw new WTFException("Incorrect result. Expected: " + 3 + ", Actual: " + max);
            stack.pop();
        }
    }

    private static class WTFException extends Exception {
        WTFException(String s) {
            super(s);
        }
    }

}
