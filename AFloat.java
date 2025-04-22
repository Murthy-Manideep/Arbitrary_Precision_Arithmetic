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
        int DecPlaces=DecimalPlaces(Str);
        if(DecPlaces==0){
            return RemoveZerosStart(Str);
        }
        String RealPart=Str.substring(0,Str.length()-DecPlaces-1);
        String DecPart=Str.substring(Str.length()-DecPlaces);
        String Ans=RemoveZerosStart(RealPart)+"."+ReverseString(RemoveZerosStart(ReverseString(DecPart)));
        return Ans;
    }
}