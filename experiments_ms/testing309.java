package test309;

public class testing309 {

	public int lengthOfLongestSubstring(String s) {
		int counter =0;
		char check =' ';
		int longest =0;
		boolean flag=false;
		String checked="";
		checked = checked+s.charAt(0);
		for (int i =1; i<s.length(); i++)
		{
			check = s.charAt(i);
			for(int j=0; j<checked.length(); j++) 
			{
				if(checked.charAt(j)==check ) 
				{
					flag = true;
					counter=0;
				}
				if(checked.charAt(j)!=check) 
				{
					counter++;
				}
			}
			if(counter==checked.length() && flag == false) 
			{
				checked=checked+check;
				if(longest<checked.length()) {
					longest = checked.length();
				}
				counter =0;
			}
			if (flag == true) 
			{
				flag=false;
				checked="";
				checked=checked+check;
			}
		}
		if (longest ==0) 
		{
			return 1;
		}
		else return longest;

	}
	public static void main(String[] args) {
		testing309 s = new testing309();
		String ss = "dvdf";
		System.out.println("longest: "+s.lengthOfLongestSubstring(ss));

	}
}
