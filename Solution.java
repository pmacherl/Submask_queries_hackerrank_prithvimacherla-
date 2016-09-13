import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	String nm[]=sc.nextLine().split(" ");
	int n=Integer.parseInt(nm[0]);
	int m=Integer.parseInt(nm[1]);
	ArrayList<Query> queries=new ArrayList<>();
	//ArrayList<Query> modify=new ArrayList<>();
	for(int c=1;c<=m;c++){
        //Storing all the query objects in a list. Look at the constructor below. 
		String[] hold=sc.nextLine().split(" ");
		if(!hold[0].equals("3")){
			Query q=new Query(Integer.parseInt(hold[0]),Integer.parseInt(hold[1]),hold[2]);
			queries.add(q);
		}
		else{
			Query q=new Query(Integer.parseInt(hold[0]),0,hold[1]);
			queries.add(q);
		}		
	}
	for(int lines=0;lines<queries.size();lines++){
		if(queries.get(lines).getNum()==3){
            //Only if the query is of type 3, then we have to find the current value of that subset .
			int ans=0;
            int x=0;
			String s=queries.get(lines).getS();
			//int place=print.get(lines).getPos();
			int start;
			if(lines-1>=0)
				start=(lines-1);
			else
				start=0;
			for(int i=start;i>=0;i--){
                /*Parsing the query list from latest to oldest. Because, if a type 1 query occurs all the calculation done before that query turns out to be a waste of computation */
                /* checking whether this particular query will/wont have an effect on the current subset */
				boolean isSubset=true;
				String applyToMe=queries.get(i).getS();
				for(int pos=0;pos<n;pos++){
					if(s.charAt(pos)=='1'&&(s.charAt(pos)!=applyToMe.charAt(pos))){
						isSubset=false;
						break;
					}
				}
				if(isSubset){
                    //Manipulating the value if the current subset is related to this query's subset. 
					int number=queries.get(i).getNum();
					int value=queries.get(i).getVal();
					if(number==1){
						ans=value;
						break;
					}
					else{
						x=x^value;
					}
				}
			}
			System.out.println(ans^x);
			}
		}
	}
}
class Query{
    int pos;
	int num; // query type
	int val; // value given in query types 1 and 2
	String s; // the subset 
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public Query( int num, int val, String s) {
		super();
		this.num = num;
		this.val = val;
		this.s = s;
	}
	
}
