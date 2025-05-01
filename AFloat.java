package arbitraryarithmetic;
public class AFloat{
    public String Number;
    public boolean numNegative=false;
    /**
     *Default constructor AFloat() that initializes the instance with value 0. 
     */
    public AFloat(){
        this.Number="0.0";
    }
    /**
     * Constructor AInteger(String Str) That Initializes The Instance By The Number Whose String Representation Is Given By 'Str'.
     * @param Str String Representing A Float
     */
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
    /**
     * Copy Constructor That Creates An Instance Of AFloat
     * @param x Another AFloat Object 
     */
    public AFloat(AFloat x){
        this.Number=x.Number;
        this.numNegative=x.numNegative;
    }

    /**
     * Parse A Static Function That Returns An Instance Of AFloat Class.
     * @param Str Input
     * @return Instance Of AFloat Class
     */
    public static AFloat parse(String Str){
        return new AFloat(Str);
    }

    /**
     * Check Whether A String Is Negative Or Positive
     * @param Str Input
     * @return True If Negative And False If Positive
     */
    private boolean isNegative(String Str){
        if(Str.charAt(0)=='-'){
            return true;
        }
        return false;
    }

    /**
     * Check Whetehr A String Is Zero Or Not
     * @param Str Input
     * @return True If Zero And False If Not Zero
     */
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

    /**
     * Reverse Of A String 
     * @param Str Input
     * @return Reverse Of Input String
     */
    private String ReverseString(String Str) {
        String Ans="";
        for(int i=Str.length()-1;i>=0;i--){
            Ans+=Str.charAt(i);  
        }   
        return Ans;  
    }

    /**
     * Absolute Part Of The Input String
     * @param Str Input
     * @return Absolute Value
     */
    private String Modulus(String Str){
        if(isNegative(Str)){
            return Str.substring(1);
        }
        return Str;
    }

    /**
     * Greatest Of The Two Strings Without Considering Sign
     * @param Str1 First Number String
     * @param Str2 Second Number String
     * @return Greatest Of The Numbers
     */
    private String MaxString(String Str1,String Str2){
        String str1=RemoveZerosStart(Str1);
        String str2=RemoveZerosStart(Str2);
        int L1=str1.length();
        int L2=str2.length();
        //First Checking Lengths And Returning Approrpriate String
        if(L1>L2){
            return Str1;
        }
        else if(L2>L1){
            return Str2;
        }
        else{
            //Now Checking The Digits If Lengths Are Same
            for(int i=0;i<L1;i++){
                if(str1.charAt(i)-'0'>str2.charAt(i)-'0'){
                    return Str1;
                }
                else if(str1.charAt(i)-'0'<str2.charAt(i)-'0'){
                   return Str2;
                }
            }
        }
        return Str1;
    }

    /**
     * Remove Decimal Point From The String('.')
     * @param Str Input
     * @return Str After Removing The Decimal Point
     */
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

    /**
     * To Get The Number of Decimal Places In The Number String
     * @param Str Input
     * @return Numer Of DecimalPlaces In The Number String
     */
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

    /**
     * To Add Zeros At The End(After The Decimal Part)
     * @param Str Input 
     * @param numZeros Number Of Zeros To End
     * @return Str After Adding Zeros
     */
    private String AppendZeros(String Str,int numZeros){
        String Ans=Str;
        for(int i=0;i<numZeros;i++){
            Ans+="0";
        }
        return Ans;
    }

    /**
     * Removing Zeros From The Start Of The Number
     * @param Str Input
     * @return String Without Leading Zeros
     */
    private String RemoveZerosStart(String Str){
        boolean Negative=isNegative(Str);
        Str=Modulus(Str);                 
        int index=0;
        //Checking For First Non-Zero Index
        while(index<Str.length()&&Str.charAt(index)=='0'){
            index++;
        }
        if(index==Str.length()){
            return "0";
        }
        String Ans;
        //Properly Adding The - Sign
        if(Negative){
            Ans="-"+Str.substring(index);
        }
        else{
            Ans=Str.substring(index);
        }
        return Ans;
    }

    /**
     * Removing Zeros From The Start Of The Number And Last Of A Decimal Number
     * @param Str Input
     * @return String Without Unnecessary Zeros
     */
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
        //Using RemoveZerosStart For Real Part And Reverse Of Decimal Part
        String RealPart=Str.substring(0, Str.length()-DecPlaces-1);
        String DecPart=Str.substring(Str.length()-DecPlaces);
        RealPart=RemoveZerosStart(RealPart);
        DecPart=ReverseString(RemoveZerosStart(ReverseString(DecPart)));
        if(DecPart.length()==0){
            DecPart="0";
        }
        String Ans=RealPart+"."+DecPart;
        //Adding Appropriate Negative Sign
        if (Negative && !Ans.equals("0.0")&& !Ans.equals("0")) {
            Ans="-"+Ans;
        }
        return Ans;
    }

    /**
     * Truncates Answer To 30 Decimals If Input Has More Than 30 Decimals Else Returns Same String
     * @param Str Input 
     * @return Truncated String To 30 Decimal Places If Necessary
     */
    private String TruncateTo30Decimal(String Str){
        int DecPlaces = DecimalPlaces(Str);
        if(DecPlaces<=30){
            return Str;
        }
        int dotIndex=Str.indexOf('.');
        if(dotIndex==-1){
        return Str;
        }
    return Str.substring(0,dotIndex+1+30);
    }
    

    /**
     * Additon Of Two Positive Float Numbers
     * @param Str1 First Number String 
     * @param Str2 Second Number String
     * @return (Str1+Str2)
     */
    private String AddPositiveNums(String Str1, String Str2) {
        int DecPlaces1=DecimalPlaces(Str1);
        int DecPlaces2=DecimalPlaces(Str2);
        int MaxDecPlaces;
        //Finding Maximum Decimalplaces
        if(DecPlaces1>DecPlaces2){
            MaxDecPlaces=DecPlaces1;
        }
        else{
            MaxDecPlaces=DecPlaces2;
        }
        String a=Str1,b=Str2;
        //Adding Appropriate Number Of Zeros For Addition
        if(MaxDecPlaces>DecPlaces1){
            a=AppendZeros(Str1,MaxDecPlaces-DecPlaces1);
        }
        if (MaxDecPlaces>DecPlaces2){
            b=AppendZeros(Str2,MaxDecPlaces-DecPlaces2);
        }
        //Removing Decimal Point To Do Integer Addition
        a=ReverseString(RemoveDecimal(a));
        b=ReverseString(RemoveDecimal(b));
        int L1=a.length();
        int L2=b.length();
        String Ans="";
        int Carry=0;
        int maxLen;
        //Finding Maximum of Two Lengths To Run Through The Loop
        if(L1>L2){
            maxLen=L1;
        }
        else{
            maxLen=L2;
        }
        //Going Digit By Digit And Adding The Digits
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
        //Adding Decimal Point To The Integer Ans 
        if(MaxDecPlaces>0){
            int DecimalPos=Ans.length()-MaxDecPlaces;
            Ans=Ans.substring(0,DecimalPos)+"."+Ans.substring(DecimalPos);
        }
        Ans=RemoveUnnecessaryZeros(Ans);
        return Ans;
    }

    /**
     * Subtraction Of Two Positive Numbers (First-Second)
     * @param Str1 First Number String
     * @param Str2 Second Number String
     * @return (Str1-Str2)
     */
    private String SubPositiveNums(String Str1,String Str2){
        if(Str1.equals(Str2)){
            return "0";
        }
        int DecPlaces1=DecimalPlaces(Str1);
        int DecPlaces2=DecimalPlaces(Str2);
        int MaxDecPlaces;
        //Finding Maximum Decimalplaces
        if(DecPlaces1>DecPlaces2){
            MaxDecPlaces=DecPlaces1;
        }
        else{
            MaxDecPlaces=DecPlaces2;
        }
        String tempa=Str1,tempb=Str2;
        //Adding Appropriate Number Of Zeros For Subtraction
        if(MaxDecPlaces>DecPlaces1){
            tempa=AppendZeros(Str1,MaxDecPlaces-DecPlaces1);
        }
        if (MaxDecPlaces>DecPlaces2){
            tempb=AppendZeros(Str2,MaxDecPlaces-DecPlaces2);
        }
        //Removing The Decimal To Do Integer Subtraction
        tempa=RemoveDecimal(tempa);
        tempb=RemoveDecimal(tempb);
        String a,b;
        String Ans="";
        boolean ansNegative=false;
        //Checking Whether To Have Negative Sign Or Not
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
        //Finding Maximum of Two Lengths To Run Through The Loop
        if(L1>L2){
            maxLen=L1;
        }
        else{
            maxLen=L2;
        }
        //Going Digit By Digit And Subtracting The Digits
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
        //Adding Decimal Point To Intger Ans
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

    /**
     * Multplication Of A Float Number With A Digit
     * @param Str Input Number String
     * @param Digit Input Char Single Digit
     * @return (Str*digit)
     */
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

    /**
     * Multiplication Of Two Float Numbers
     * @param Str1 First Number String
     * @param Str2 Second Number String
     * @return (Str1*Str2)
     */
    private String MulPositiveNums(String Str1,String Str2){
        int DecPlaces1=DecimalPlaces(Str1);
        int DecPlaces2=DecimalPlaces(Str2);
        String a=RemoveDecimal(Str1);
        String b=ReverseString(RemoveDecimal(Str2));
        String Ans=""; 
        int L1=a.length();
        int L2=b.length();
        //Each Digit Of One Operand Is Multiplied With The Entire Other Operand 
        //And The Resulting Partial Products Are Then Added Together
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
    
    /**
     * Float Divison Of Two Numbers  
     * @param Str1 First Number String 
     * @param Str2 Second Number String
     * @return (Str1/Str2)
     */
    private String DivPosNums(String Str1,String Str2){
        //Throwing Excption If The Divisor Is Zero
        if(isZero(Str2)){
            throw new ArithmeticException("Division by zero error");
        }
        if(isZero(Str1)){
            return "0.0";
        }
        int DecPlaces1=DecimalPlaces(Str1);
        int DecPlaces2=DecimalPlaces(Str2);
        //Removing Decimal To Do Integer Divison
        String a=RemoveDecimal(Str1);
        String b=RemoveDecimal(Str2);
        String Ans="";
        a=RemoveZerosStart(a);
        b=RemoveZerosStart(b);
        //Adding Appropriate Number Of Zeros
        if(DecPlaces1>DecPlaces2){
            b=AppendZeros(b,DecPlaces1-DecPlaces2);
        }
        else if(DecPlaces1<DecPlaces2){
            a=AppendZeros(a,DecPlaces2-DecPlaces1);
        }
        String Current="";
        //Divison For Integer Part
        for(int j=0;j<a.length();j++){
            int count=0;
            Current+=a.charAt(j);
            while(MaxString(Current,b).equals(Current)){
                Current=RemoveZerosStart(Current);
                Current=SubPositiveNums(Current,b);
                count++;
            }
            Ans+=Integer.toString(count);
        }
        //Adding Decimal Point To Answer
        Ans+=".";
        Current+="0";
        //Keeping Precision
        int precision=30;
        //Changing Precision Because For Divison Of 1 and 10^31 It Should Give 0.00...(30 Zeros)
        if(b.length()>30){
            precision=b.length();
        }
        //Divison For Fractional part
        for(int i=0;i<precision;i++) {
            Current=RemoveZerosStart(Current);
            int count=0;
            while(MaxString(Current,b).equals(Current)){
                Current=SubPositiveNums(Current,b);
                count++;
            }
            Current+="0";
            Ans+=Integer.toString(count);
        }
        Ans=RemoveUnnecessaryZeros(Ans);
        if(Ans.charAt(0)=='.'){
            Ans="0"+Ans;
        }
        return Ans;
    }


    /**
     * Addition Of Two AIntegers
     * @param x AFloat 
     * @return (this.Number+x.Number)
     */
    public AFloat add(AFloat x){
        String Ans;
        if((this.numNegative)&&(x.numNegative)){
            Ans="-"+(AddPositiveNums(Modulus(x.Number),Modulus(this.Number)));
        }
        else if((!this.numNegative)&&(x.numNegative)){
            Ans=SubPositiveNums(Modulus(this.Number),Modulus(x.Number));
        }
        else if((this.numNegative)&&(!x.numNegative)){
            Ans=SubPositiveNums(Modulus(x.Number),Modulus(this.Number));
        }
        else{
            Ans=AddPositiveNums(Modulus(this.Number),Modulus(x.Number));
        }
        //Truncating To 30 Decimal
        Ans=TruncateTo30Decimal(Ans);
        return parse(Ans);
    }

    /**
     * Subraction Of Two AIntegers
     * @param x AFloat
     * @return (this.Number-x.Number)
     */
    public AFloat sub(AFloat x){
        String Ans;
        if((this.numNegative)&&(x.numNegative)){
            Ans=SubPositiveNums(Modulus(x.Number),Modulus(this.Number));
        }
        else if((!this.numNegative)&&(x.numNegative)){
            Ans=AddPositiveNums(Modulus(this.Number),Modulus(x.Number));
        }
        else if((this.numNegative)&&(!x.numNegative)){
            Ans="-"+(AddPositiveNums(Modulus(this.Number),Modulus(x.Number)));
        }
        else{
            Ans=SubPositiveNums(Modulus(this.Number),Modulus(x.Number));
        }
        //Truncating To 30 Decimal
        Ans=TruncateTo30Decimal(Ans);
        return parse(Ans);
    }

    /**
     * Multplication Of Two AIntegers
     * @param x AFloat
     * @return ((this.Number)*(x.Number))
     */
    public AFloat mul(AFloat x){
        String Ans;
        if((this.numNegative)&&(x.numNegative)){
            Ans=MulPositiveNums(Modulus(this.Number),Modulus(x.Number));
        }
        else if((!this.numNegative)&&(x.numNegative)){
            Ans="-"+(MulPositiveNums(Modulus(this.Number),Modulus(x.Number)));
        }
        else if((this.numNegative)&&(!x.numNegative)){
            Ans="-"+(MulPositiveNums(Modulus(this.Number),Modulus(x.Number)));
        }
        else{
            Ans=MulPositiveNums(Modulus(this.Number),Modulus(x.Number));
        }
        //Truncating To 30 Decimal
        Ans=TruncateTo30Decimal(Ans);
        return parse(Ans);
    }

    /**
     * Divison Of Two AIntegers
     * @param x AFloat
     * @return ((this.Number)/(x.Number))
     */
    public AFloat div(AFloat x){
        String Ans;
        if((this.numNegative)&&(x.numNegative)){
            Ans=DivPosNums(Modulus(this.Number),Modulus(x.Number));
        }
        else if((!this.numNegative)&&(x.numNegative)){
            Ans="-"+(DivPosNums(Modulus(this.Number),Modulus(x.Number)));
        }
        else if((this.numNegative)&&(!x.numNegative)){
            Ans="-"+(DivPosNums(Modulus(this.Number),Modulus(x.Number)));
        }
        else{
            Ans=DivPosNums(Modulus(this.Number),Modulus(x.Number));
        }
        //Truncating To 30 Decimal
        Ans=TruncateTo30Decimal(Ans);
        return parse(Ans);
    }

    public String toString(){
        return this.Number;
    }
}