🚀Introduction :
Arbitrary precision arithmetic using this file should allow one to compute any expression involving arbitrarily large integers and arbitrarily large floating point precision numbers with arbitrary precision. All the operations that you implement should preserve the complete information of the number and should not introduce any kind of round-off errors.

When we have a large number they may cause overflow issues.Arbitrary precision arithmetic solves this issue by storing the given number in the form of string and performing different operations like addition, subtraction, multiplication and division.

This project provides support for arbitrary precision arithmetic, enabling the addition, subtraction, multiplication, and division of both integers and real numbers.You can get up to 30 decimal precision without any roundoff errors.

🛠️Code Overview :
Implementation of an arbitrary-precision arithmetic library in Java uses OOP concepts.Given two strings as input, it had support for addition, subtraction,multiplication, and division, for both integer and float data types. The answer will be printed on the terminal screen. By using OOP concepts By using OOP, we gain Encapsulation, Inheritance and Polymorphism
• AInteger.java: This class handles arbitrary-precision integers, providing methods for addition, subtraction, multiplication, and division.
• AFloat.java: This class handles arbitrary-precision floating-point numbers, ensuring precision even for very large or very small decimal values.
• MyInfArith.java: This is the main file that runs the code.

📁Folder Structure :
Arbitrary Precision Arithmetic/
    JavaSrc/arbitraryarithmetic/AFloat.java, AInteger.java
    MyInfArith.java
JavaClass
arbitraryarithmetic/aarithmetic.jar
python_script.py
build.xml
README.md
Report/Report.pdf, Report.tex

🔍Verification : 
•MyInfArith.java:
    1) java MyInfArith int add 23650078224912949497310933240250 42939783262467113798386384401498
       Output: 66589861487380063295697317641748
    2) java MyInfArith float div 8792726365283060579833950521677211.0 493835253617089647454998358
       Output: 17804979.091469989302961159520087878533
•Python Script:
    1) python3 Script.py int sub 3116511674006599806495512758577 57745242300346381144446453884008
      Output: -54628730626339781337950941125431
    2)python3 Script.py float add 84486723.420039 70974199.843732
      Output: 155460923.263771
•Ant:
    1) ant run -Darg1=int -Darg2=mul -Darg3=14344163160445929942680697312322 -Darg4=23017167694823904478474013730519
      Output: 330162008905899217578310782382075660760972861550182008086155118
    2) ant run -Darg1=int -Darg2=sub -Darg3=840196454.51725 -Darg4=712586963.70283
      Output: 127609490.81442
• JAR:
    1) java -cp bin:arbitraryarithmetic/aarithmetic.jar MyInfArith int div 2 0
      Output: Division by zero error
    2) java -cp bin:arbitraryarithmetic/aarithmetic.jar MyInfArith float div 00.20 05
      Output: 0.04

📜How To Use :
MyInfArith.java :
    • Go to src directory.
    • Compile MyInfArith.java using "javac MyInfArith.java"
    • Run using "java MyInfArith FirstArg SecondArg ThirdArg FourthArg".
Python Script : 
    • Go to Project directory.
    • Run using "python python_script.py FirstArg SecondArg ThirdArg FourthArg"
Ant Make file :
    • Ant clean will remove all compiled files.
    • ant run -Darg1 -Darg2 -Darg3 -Darg4.
JAR file :
    • Files in JAR file can be viewed using:
        jar tf aarithmetic.jar
    • Use the -cp option when compiling your Java file to include the JAR:
        javac -cp arbitraryarithmetic/aarithmetic.jar -d bin src/MyInfArith.java
    • While running the program, again include the JAR in the classpath:
        java -cp bin:arbitraryarithmetic/aarithmetic.jar MyInfArith FirstArg SecondArg ThirdArg FourthArg

The compiled aarithmetic.jar located in the arbitraryarithmetic directory contains the compiled classes for AInteger and AFloat inside the arbitraryarithmetic package. This JAR file can be used as a library in any Java project that needs arbitrary precision arithmetic.

📦To use this JAR file as library :
1. Ensure the JAR file aarithmetic.jar is in your working directory or specify its full path.
2. Use the -cp or -classpath option when compiling your Java file to include the JAR:
    javac -cp arbitrary/aarithmetic.jar YourProgram.java
3. While running the program, again include the JAR in the classpath:
    java -cp .:arbitrary/aarithmetic.jar YourProgram
   On Windows, use a semicolon (;) instead of a colon (:) as the classpath separator:
    java -cp .;arbitrary/aarithmetic.jar YourProgram
4. In your Java file, import the classes using:
    import arbitraryarithmetic.AInteger;
    import arbitraryarithmetic.AFloat;

🛑Limitations :
• Multiplication and division operations for extremely large numbers (over10,000 digits) will be slow for floating-point division due to manual   digit-by-digit operations.
• The current version does not support scientific notation.All numbers must be given as standard decimal strings.
• We are assuming input strings will be numbers, the library does not currently handle multiple decimal points strings, invalid characters.
• No support for other mathmatical operations apart form addition,subtraction,divison.

📦Docker :