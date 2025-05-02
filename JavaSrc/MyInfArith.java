import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;
public class MyInfArith{
    public static void main(String[] args){
        if (args.length!=4){
            System.out.println("Wrong Use Case");
            return;
        }
        String FirstArg=args[0];
        String SecondArg=args[1];
        String ThirdArg=args[2];
        String FourthArg=args[3];
        if(FirstArg.equals("int")){
            if(!isValidInteger(ThirdArg)||!isValidInteger(FourthArg)){
                System.out.println("Enter Correct Integer");
                return;
            }
            AInteger A=new AInteger(ThirdArg);
            AInteger B=new AInteger(FourthArg);
            AInteger Output=null;
            if(SecondArg.equals("add")){
                Output=A.add(B);
                System.out.println("Output: "+Output);
            }
            else if(SecondArg.equals("sub")){
                Output=A.sub(B);
                System.out.println("Output: "+Output);
            }
            else if(SecondArg.equals("mul")){
                Output=A.mul(B);
                System.out.println("Output: "+Output);
            }
            else if(SecondArg.equals("div")){
                try{
                    Output=A.div(B);
                    System.out.println("Output: "+Output);
                } 
                catch (ArithmeticException e){
                    System.out.println("Division by zero error");
                }
            } 
            else{
                System.out.println("Use Only add,sub,mul,div");
            }
        }
        else if(FirstArg.equals("float")){
            AFloat A=new AFloat(ThirdArg);
            AFloat B=new AFloat(FourthArg);
            AFloat Output=null;
            if(SecondArg.equals("add")){
                Output=A.add(B);
                System.out.println("Output: "+Output);
            }
            else if(SecondArg.equals("sub")) {
                Output=A.sub(B);
                System.out.println("Output: "+Output);
            }
            else if (SecondArg.equals("mul")) {
                Output=A.mul(B);
                System.out.println("Output: "+Output);
            }
            else if(SecondArg.equals("div")){
                try{
                    Output=A.div(B);
                    System.out.println("Output: "+Output);
                } 
                catch(ArithmeticException e){
                    System.out.println("Division by zero error");
                }
            } 
            else{
                System.out.println("Use add,sub,mul,div");
            }
        } 
        else{
            System.out.println("Use int or float");
        }
    } 
    //Checking Whether It Is Integer or Decimal Point Number
    public static boolean isValidInteger(String Str){
        for(int i=0;i<Str.length();i++){
            if(Str.charAt(i)=='.'){
                return false;
            }
        }
        return true;
    }
}