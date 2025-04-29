package arbitraryarithmetic;
public class AFloat{
    public String Number;
    public boolean numNegative=false;

    public AFloat(){
        this.Number="0.0";
    }
    

    public AFloat(String Str){
        boolean Decimal=false;
        for(int i=0;i<Str.length();i++){
            if(Str.charAt(i)=='.'){
                Decimal=true;
            }
        }
        if(Decimal){
            this.Number=Str;
        }
        else{
            this.Number=Str+".0";
        }
        if(Str.charAt(0)=='-'){
            numNegative=true;
        }
    }

    public AFloat(AFloat x){
        this.Number=x.Number;
        this.numNegative=x.numNegative;
    }

    public static AFloat parse(String Str){
        return new AFloat(Str);
    }

    private boolean isNegative(String Str){
        if(Str.charAt(0)=='-'){
            return true;
        }
        return false;
    }

    private boolean isZero(String Str){
        int NumZeros=0;
        for(int i=0;i<Str.length();i++){
            if(Str.charAt(i)=='0'){
                NumZeros++;
            }
        }
        if(Str.length()-1==NumZeros){
            return true;
        }
        return false;
    }

    private String ReverseString(String Str) {
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

    private String RemoveDecimal(String Str){
        String Ans="";
        for(int i=0;i<Str.length();i++){
            if(Str.charAt(i)=='.'){
                continue;
            }
            Ans+=Str.charAt(i);
        }
        return Ans;
    }

    private int DecimalPlaces(String Str){
        int DecimalPos=-1;
        for(int i=0;i<Str.length();i++){
            if(Str.charAt(i)=='.'){
                DecimalPos=i;
            }
        }
        if(DecimalPos==-1){
            return 0;
        }
        return Str.length()-DecimalPos-1;
    }

    private String AppendZeros(String Str,int numZeros){
        String Ans=Str;
        for(int i=0;i<numZeros;i++){
            Ans+="0";
        }
        return Ans;
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

    private String RemoveUnnecessaryZeros(String Str){
        boolean Negative=isNegative(Str);
        Str=Modulus(Str);
        int DecPlaces=DecimalPlaces(Str);
        if (DecPlaces==0){
            String Ans=RemoveZerosStart(Str);
            if(Negative&&!Ans.equals("0")){
                Ans="-"+Ans;
            }
            return Ans;
        }
        String RealPart=Str.substring(0, Str.length()-DecPlaces-1);
        String DecPart=Str.substring(Str.length()-DecPlaces);
        RealPart=RemoveZerosStart(RealPart);
        DecPart=ReverseString(RemoveZerosStart(ReverseString(DecPart)));
        if(DecPart.length()==0){
            DecPart="0";
        }
        String Ans=RealPart+"."+DecPart;
        if (Negative&&!Ans.equals("0.0")&&!Ans.equals("0")) {
            Ans="-"+Ans;
        }
        return Ans;
    }

    private String AddPositiveNums(String Str1, String Str2) {
        int DecPlaces1=DecimalPlaces(Str1);
        int DecPlaces2=DecimalPlaces(Str2);
        int MaxDecPlaces;
        if(DecPlaces1>DecPlaces2){
            MaxDecPlaces=DecPlaces1;
        }
        else{
            MaxDecPlaces=DecPlaces2;
        }
        String a=Str1,b=Str2;
        if (MaxDecPlaces>DecPlaces1){
            a=AppendZeros(Str1,MaxDecPlaces-DecPlaces1);
        }
        if (MaxDecPlaces>DecPlaces2){
            b=AppendZeros(Str2,MaxDecPlaces-DecPlaces2);
        }
        a=ReverseString(RemoveDecimal(a));
        b=ReverseString(RemoveDecimal(b));
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
        if(MaxDecPlaces>0){
            int DecimalPos=Ans.length()-MaxDecPlaces;
            Ans=Ans.substring(0,DecimalPos)+"."+Ans.substring(DecimalPos);
        }
        Ans=RemoveUnnecessaryZeros(Ans);
        return Ans;
    }

    private String SubPositiveNums(String Str1,String Str2){
        if(Str1.equals(Str2)){
            return "0";
        }
        int DecPlaces1=DecimalPlaces(Str1);
        int DecPlaces2=DecimalPlaces(Str2);
        int MaxDecPlaces;
        if(DecPlaces1>DecPlaces2){
            MaxDecPlaces=DecPlaces1;
        }
        else{
            MaxDecPlaces=DecPlaces2;
        }
        String tempa=Str1,tempb=Str2;
        if (MaxDecPlaces>DecPlaces1){
            tempa=AppendZeros(Str1,MaxDecPlaces-DecPlaces1);
        }
        if (MaxDecPlaces>DecPlaces2){
            tempb=AppendZeros(Str2,MaxDecPlaces-DecPlaces2);
        }
        tempa=RemoveDecimal(tempa);
        tempb=RemoveDecimal(tempb);
        String a,b;
        String Ans="";
        boolean ansNegative=false;
        if(MaxString(tempa,tempb).equals(tempa)){
            a=ReverseString(tempa);
            b=ReverseString(tempb);
        } 
        else{
            a=ReverseString(tempb);
            b=ReverseString(tempa);
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
        Ans=ReverseString(Ans);
        if(MaxDecPlaces>0){
            int DecimalPos=Ans.length()-MaxDecPlaces;
            Ans=Ans.substring(0,DecimalPos)+"."+Ans.substring(DecimalPos);
        }
        if(ansNegative){
            Ans='-'+Ans;
        }
        Ans=RemoveUnnecessaryZeros(Ans);
        return Ans;
    }

    private String MulPosSingleDigit(String Str1,char Digit){
        int DecPlaces=DecimalPlaces(Str1);
        String a=ReverseString(RemoveDecimal(Str1));
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
        if(DecPlaces>0){
            int DecimalPos=Ans.length()-DecPlaces;
            Ans=Ans.substring(0,DecimalPos)+"."+Ans.substring(DecimalPos);
        }
        Ans=RemoveUnnecessaryZeros(Ans);
        return Ans;
    }

    private String MulPositiveNums(String Str1,String Str2){
        int DecPlaces1=DecimalPlaces(Str1);
        int DecPlaces2=DecimalPlaces(Str2);
        String a=RemoveDecimal(Str1);
        String b=ReverseString(RemoveDecimal(Str2));
        String Ans=""; 
        int L1=a.length();
        int L2=b.length();
        for(int i=0;i<L2;i++){
            String temp=AppendZeros(MulPosSingleDigit(a,b.charAt(i)),i);
            Ans=(AddPositiveNums(Ans,temp));
        }
        while(Ans.length()<=(DecPlaces1+DecPlaces2)){
            Ans="0"+Ans;
        }    
        int DecimalPos=Ans.length()-(DecPlaces1+DecPlaces2);
        Ans=Ans.substring(0,DecimalPos)+"."+Ans.substring(DecimalPos);
        Ans=RemoveUnnecessaryZeros(Ans);
        return Ans;
    }
}