package arbitraryarithmetic;
public class AInteger{
    public String Number;
    public boolean numNegative=false;
    
    public AInteger(){
        this.Number="0";
    }

    public AInteger(String Str){
        Str=RemoveZerosStart(Str);
        this.Number=Str;
        if(Str.charAt(0)=='-'){
            numNegative=true;
        }
    }

    public AInteger(AInteger x){
        this.Number=x.Number;
        this.numNegative=x.numNegative;
    }

    public static AInteger parse(String Str){
        return new AInteger(Str);
    }

    private boolean isNegative(String Str){
        if(Str.charAt(0)=='-'){
            return true;
        }
        return false;
    }

    private String ReverseString(String Str){
        String Ans="";
        for(int i=Str.length()-1;i>=0;i--){
            Ans+=Str.charAt(i);  
        }   
        return Ans;  
    }

    private String Modulus(String Str){
        if(isNegative(Str)){
            return Str.substring(1);
        }
        return Str;
    }

    private String MaxString(String Str1,String Str2){
        int L1=Str1.length();
        int L2=Str2.length();
        if(L1>L2){
            return Str1;
        }
        else if(L2>L1){
            return Str2;
        }
        else{
            for(int i=0;i<L1;i++){
                if(Str1.charAt(i)-'0'>Str2.charAt(i)-'0'){
                    return Str1;
                }
                else if(Str1.charAt(i)-'0'<Str2.charAt(i)-'0'){
                   return Str2;
                }
            }
        }
        return Str1;
    }

    private String RemoveZerosStart(String Str){
        boolean Negative=isNegative(Str);
        Str=Modulus(Str);                 
        int index=0;
        while(index<Str.length()&&Str.charAt(index)=='0'){
            index++;
        }
        if(index==Str.length()){
            return "0";
        }
        String Ans;
        if(Negative){
            Ans="-"+Str.substring(index);
        }
        else{
            Ans=Str.substring(index);
        }
        return Ans;
    }

    private String AddPositiveNums(String Str1,String Str2){
        String a=ReverseString(Str1);
        String b=ReverseString(Str2);
        int L1=a.length();
        int L2=b.length();
        String Ans="";
        int Carry=0;
        int maxLen;
        if(L1>L2){
            maxLen=L1;
        }
        else{
            maxLen=L2;
        }
        for(int i=0;i<maxLen;i++){
            int Digit1;
            int Digit2;
            if(i<L1){
                Digit1=a.charAt(i)-'0';
            }
            else Digit1=0;
            if(i<L2){
                Digit2=b.charAt(i)-'0';
            }
            else Digit2=0;
            char c=(char) (((Digit1+Digit2+Carry)%10)+'0');
            Ans+=c;
            Carry=(Digit1+Digit2+Carry)/10;
        }
        if(Carry>0){
            char c=(char) (Carry+'0');
            Ans+=c;
        }
        Ans=ReverseString(Ans);
        return Ans;
    }

    private String SubPositiveNums(String Str1,String Str2) {
        if(Str1.equals(Str2)){
            return "0";
        }
        String a,b;
        String Ans="";
        boolean ansNegative=false;
        if(MaxString(Str1, Str2).equals(Str1)){
            a=ReverseString(Str1);
            b=ReverseString(Str2);
        } 
        else{
            a=ReverseString(Str2);
            b=ReverseString(Str1);
            ansNegative=true;
        }
        int L1=a.length();
        int L2=b.length();
        int Borrow=0;
        int maxLen;
        if(L1>L2){
            maxLen=L1;
        }
        else{
            maxLen=L2;
        }
        for(int i=0;i<maxLen;i++){
            int Digit1;
            int Digit2;
            if(i<L1){
                Digit1=a.charAt(i)-'0';
            }
            else{
                Digit1=0;
            }
            if(i<L2){
                Digit2=b.charAt(i)-'0';
            }
            else{
                Digit2=0;
            }
            if(Digit1-Digit2-Borrow<0){
                char c=(char) ((Digit1-Digit2-Borrow+10)+'0');
                Ans+=c;
                Borrow=1;
            } 
            else{
                char c=(char) ((Digit1-Digit2-Borrow)+'0');
                Ans+=c;
                Borrow=0;
            }
        }
        if(ansNegative){
            Ans+='-';
        }
        Ans=ReverseString(Ans);
        return Ans;
    }

    private String MulPosSingleDigit(String Str,char Digit){
        String a=ReverseString(Str);
        int Borrow=0;
        int Num=Digit-'0';
        String Ans="";
        for(int i=0;i<a.length();i++){
            int Digit1=a.charAt(i)-'0';
            char c=(char) (((Digit1*Num+Borrow)%10)+'0');
            Ans+=c;
            Borrow=(Digit1*Num+Borrow)/10;
        }
        if (Borrow>0) {
            Ans+=(char) (Borrow+'0');
        }
        Ans=ReverseString(Ans);
        return Ans;
    }

    private String MulPositiveNums(String Str1,String Str2){
        String a=Str1;
        String b=ReverseString(Str2);
        String Ans="0"; 
        int L1=a.length();
        int L2=b.length();
        for(int i=0;i<L2;i++){
            String temp=MulPosSingleDigit(a,b.charAt(i));
            for(int j=0;j<i;j++){
                temp+="0";
            }
            Ans=AddPositiveNums(Ans,temp);
        }
        Ans=RemoveZerosStart(Ans);
        return Ans;
    }
}