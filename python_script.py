import os
import sys

#Directories
Bindir="JavaClass"
#Function To Run The java MyInfArtih And Compile The Java Files
def RunMyInfArith(data_type, operation, operand1, operand2):
    print(f"Running: MyInfArith {data_type} {operation} {operand1} {operand2}")
    #Checking JavaClass Directory Exists Or Not
    if not os.path.exists(Bindir):
        os.makedirs(Bindir)
    #Compiling JavaSrc Files
    compile_cmd = "javac -d JavaClass JavaSrc/arbitraryarithmetic/*.java JavaSrc/MyInfArith.java"
    result=os.system(compile_cmd)
    if result!=0:
        print("Compilation failed.")
        return
    #Run The Class Files
    Run=f"java -cp \"{Bindir}\" MyInfArith {data_type} {operation} {operand1} {operand2}"
    result=os.system(Run)
    #Checking Whether Compiled Correctly or Not
    if result!=0:
        print("Execution failed.")
        return

#Main Code
if len(sys.argv)!=5:
    print("Wrong Use Case")
else:
    dtype=sys.argv[1]
    op=sys.argv[2]
    op1=sys.argv[3]
    op2=sys.argv[4]
    RunMyInfArith(dtype,op,op1,op2)
