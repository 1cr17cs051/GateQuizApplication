package com.example.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.QuizContract.CategoriesTable;
import com.example.quizapplication.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizApplication.db";
    private static final int DATABASE_VERSION = 1;

    private static  QuizDbHelper instance;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){
        if(instance == null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        filCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void filCategoriesTable(){
        Category c1 = new Category("P & D.S");
        addCategory(c1);
        Category c2 = new Category("O.S");
        addCategory(c2);
        Category c3 = new Category("A.T.C");
        addCategory(c3);
        Category c4 = new Category("D.B.M.S");
        addCategory(c4);
        Category c6 = new Category("AMPTITUDE");
        addCategory(c6);
        Category c7 = new Category("VERBAL");
        addCategory(c7);
        Category c8 = new Category("REASONING");
        addCategory(c8);





    }

    private void addCategory(Category category){
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME,category.getName());
        db.insert(CategoriesTable.TABLE_NAME,null,cv);

    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Consider the following three functions :\n" +
                "[P1] int *g(void)\n" +
                "{\n" +
                "intx=10;\n" +
                "return (& x);\n" +
                "}\n" +
                "[P2] int *g(void)\n" +
                "{\n" +
                "int *px;\n" +
                "*px=10;\n" +
                "return px;\n" +
                "}\n" +
                "[P3] int *g(void)\n" +
                "{\n" +
                "int *px\n" +
                "px=(int*)malloc (size of (int));\n" +
                "*px=10;\n" +
                "return px;\n" +
                "}\n" +
                "Which of the above three functions are likely to cause problems with\n" +
                "pointers ?","Only P3","Only P1 and P3","Only P1 and P2","P1, P2 and P3",3,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q1);
        Question q2 = new Question("Consider the following program\n" +
                "Program P2\nVar n:int:\n" +
                "procedure W (var x:int)\n" +
                "begin\n" +
                "X=X+1\n" +
                "Print x;\n" +
                "end\n" +
                "Procedure D\n" +
                "Begin\n" +
                "var n:int;\n" +
                "n=3;\n" +
                "W(n);\n" +
                "End\n" +
                "Begin \\\\begin P2\n" +
                "n=10;\n" +
                "D;\n" +
                "end\n" +
                "If the language has dynamic scooping and parameters are passed by\n" +
                "reference, what will be printed by the program ?","10","11","3","None of the above",4,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q2);
        Question q3 = new Question("The results returned by function under value-result and reference\n" +
                "parameter passing conventions","Do not differ","Differ in the presence of loops","Differ in all cases","May differ in the presence of exception",2,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q3);
        Question q4 = new Question("Consider the following declaration of a two-dimensional array in C :\n" +
                "Char a[100][100]\n" +
                "Assuming that the main memory is byte-addressable and that array\n" +
                "is stored starting form memory address 0, the address of a [40] [50] is","4040","4050","5040","5050",2,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q4);
        Question q5 = new Question("Consider the following C function.\n" +
                "fl oat f(fl oat x, int y){\n" +
                "fl oat p, s; int i;\n" +
                "for (s=1, p=1, i=1, i<y; i++)\n{\n" +
                "p)=x/i;\n" +
                "s+=p;\n" +
                "}\n" +
                "return s;\n" +
                "}\n" +
                "For large values of y, the return value of the function f best\n" +
                "approximates","x^y","e^x","In(1 + x)","x^x",2,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q5);
        Question q6 = new Question("Assume the following C variable declaration\n" +
                "int)A[10], B[10][10];\nOf the following expressions\n(1) A[2]\n" +
                "(2) A[2][3]\n" +
                "(3) B[1]\n" +
                "(4) B[2][3]\nWhich will not give compile-time errors if used as left hand sides of\n" +
                "assignment statements in a C program ?","1, 2, and 4, only","2, 3, and 4, only","2 and 4 only","4 only",1,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q6);
        Question q7 = new Question("Let T(n) be the number of different binary search trees on n distinct\n" +
                "elements.\n" +
                "Then,where x is","n-k+1","n-k","n-k-1","n-k-2",1,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q7);
        Question q8 = new Question("Suppose the numbers 7, 5, 1, 8, 3, 6, 0, 9, 4, 2 are inserted in that\n" +
                "order into an initially empty binary search tree. The binary search\n" +
                "tree uses the usual ordering on natural numbers. What is the inorder\ntransversal sequence of the resultant tree ?","7 5 1 0 3 2 4 6 8 9","0 2 4 3 1 6 5 9 8 7","0 1 2 3 4 5 6 7 8 9","9 8 6 4 2 3 0 1 5 7",3,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q8);
        Question q9 = new Question("A data structure is required for storing a set of integers such that\n" +
                "each of the following operations can be done is (logn) time, where n\n" +
                "is the number of elements in the set.\n" +
                "1. Delection of the smallest element.\n" +
                "2. Insertion of an element if it is not already present in the set.\n" +
                "Which of the following data structures can be used for this purpose ?","A heap can be used but not a balanced binary search tree","A balanced binary search tree can be used but not a heap","Both balanced binary search tree and heap can be used","Neither balanced binary search tree nor heap can be used",2,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q9);
        Question q10 = new Question("Let S be a stack of size n >= 1. Starting with the empty stack, suppose\n" +
                "we push the first n natural numbers in sequence, and then perform n\n" +
                "pop operations. Assume that Push and Pop operation take X seconds\n" +
                "each , and Y seconds elapse between the end of the one such stack\n" +
                "operation and the start of the next operation. For m >= 1, define\n" +
                "the stack-life of mcs the time elapsed from the end or Push (m) to\n" +
                "the start of the pop operation that removes m from S . The average\n" +
                "stack-life of an element of this stack is","n(X+Y)","3Y+2X","n(X+Y)-X","Y+2X",4,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q10);
        Question q11 = new Question("In the following C program fragment, j, k, n and TwoLog_n\n" +
                "are integer variables, and A is an array of integers. The variable n\n" +
                "is initialized to an integer >= 3, and TwoLog_n is initialized to the\n" +
                "value of 2*[log2(n)]\n" +
                "for (k=3;k<=n;k++)\n" +
                "A[k]=0;\n" +
                "for (k=2;k<=TwoLog_n;k++)\n" +
                "for (j=k+1;j<=n;j++)\n" +
                "A[j]=A[j]<(j%k);\n" +
                "for(j=3;j<=n;j++)\n" +
                "if (!A[j])printf(“%d”,j);\n" +
                "The set of number printed by this program fragment is","{m | m <= n,(Ǝi) [m = i!]}","{m | m <= n,(Ǝi) [m = i^2]}","{m | m <= n,m is prime}","{m | m <= n,m is odd}",2,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q11);
        Question q12 = new Question("Consider the C program shown below.\n" +
                "#include <stdio.h>\n" +
                "#defi ne print(x)printf(“%d”,x)\n" +
                "int x;\n" +
                "void Q (int z){\n" +
                "z+=x; print(z);\n" +
                "}\n" +
                "void p (int)y){\n" +
                "int x=)y+2;\nQ(x);)y=x-1;\n" +
                "print(x);\n" +
                "}\n" +
                "main (void){\n" +
                "x=5;\n" +
                "p(&x);\n" +
                "print(x);\n" +
                "}","12 7 6","22 12 11","14 6 6","7 6 6",1,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q12);
        Question q13 = new Question("Consider the function - defined below.\n" +
                "struct item {\n" +
                "int data;\n" +
                "struct item)next;\n" +
                "};\n" +
                "int f (struct item )p){\n" +
                "return ((p==NULL)||(p−>next==NULL)||\n" +
                "((p−>data<=p−>next−>data)&&\n" +
                "f(p−>next)));\n" +
                "}\n" +
                "For a given linked list p, the function f return 1 if and only if","the list is empty or has exactly one element","the elements in the list are sorted in non-decreasing order of data\n" +
                "value","the elements in the list are sorted in non-increasing order of data\n" +
                "value","not all elements in the list have the same data value",2,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q13);
        Question q14 = new Question("The goal of structured programming is to","have well indented programs","be able to infer the flow of control from the compiled code","be able to infer the flow of control form the program text","avoid the use of GOTO statements",4,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q14);
        Question q15 = new Question("Consider the following C function\n" +
                "void swap (int a, int b)\n" +
                "{int temp;\n" +
                "temp =a;\n" +
                "a =b;\n" +
                "b =temp;\n" +
                "}\n" +
                "In the order to exchange the values of two variables x and y .","call swap (x,y)","call swap (&x,&y)","swap (x,y) cannot be used as it does not return any value","swap (x,y) cannot be used as the parameters are passed by value",4,Question.DIFFICULTY_EASY,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q15);
        Question q16 = new Question("A single array A [1........MAXSIZE] is used to implement two stacks.\n" +
                "The two stacks grow from opposite ends of the array. Variables top\n1 and top 2 (top 1<top 2) point to the location of the topmost\n" +
                "element in each of the stacks. If the space is to be used efficiently, the\n" +
                "condition for “stack full” is","(top 1=MAXSIZE/2) and (top 2=MAXSIZE/2+1)","top 1+top2=MAXSIZE","(top 1=MAXSIZE/2) or (top2=MAXSIZE)","top 1=top 2−1",4,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q16);
        Question q17 = new Question("The following numbers are inserted into an empty binary search tree\n" +
                "in the given order: 10, 1, 3, 5, 15, 12, 16. What is the height of the\n" +
                "binary search tree (tree height is the maximum distance of a leaf node\n" +
                "from the root) ?","2","3","4","6",2,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q17);
        Question q18 = new Question("The best data structure to check whether an arithmetic expression\n" +
                "has balanced parenthesis is a","queue","stack","tree","list",2,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q18);
        Question q19 = new Question("Consider the following C function\n" +
                "int f(int n)\n" +
                "{static int i=1;\n" +
                "if (n>=5) return n;\n" +
                "n=n+i;\n" +
                "i++;\n" +
                "return f(n);\n" +
                "}\n" +
                "The value returned by f(1) is","5","6","7","8",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q19);
        Question q20 = new Question("Consider the following program fragment for reversing the digits in a\n" +
                "given integer to obtain a new integer. Let ....... n d d d. = 1 2 m\n" +
                "int n, rev;\n" +
                "rev=0;\n" +
                "while(n>0){\n" +
                "rev=rev)10+n%10;\nn=n/10;\n" +
                "}\n" +
                "The loop invariant condition at the end of the ith iteration is","n = d1d2......dm−i and rev = dmdm−1......dm−i+1","n = dm−i+1.....dm−1dm or rev = dm−i .....d2d1","n != rev","n = d1d2....dm or rev = dm......d2d1",1,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q20);
        Question q21 = new Question("Consider the following C program segment:\n" +
                "char p[20];\n" +
                "char)s= “string”;\n" +
                "int length=strlen(s);\n" +
                "for (i=0;i<length; i++)\n" +
                "p[i]=s[length\u0001i];\n" +
                "printf(“% s”, p);\n" +
                "The output of the program is","gnirts","string","gnirt","no output is printed",4,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q21);

        Question q22 = new Question("How many different insertion sequences of the key values using hte\n" +
                "same hash function and linear probing will result in the hash table\n" +
                "shown above ?","10","20","30"
                ,"40",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q22);

        Question q23 = new Question("Assume that the operators +, -,# are left associative and ^ is\n" +
                "right associative .The order of precedence (from highest to lowest)\n" +
                "is ^,#, +, -. The postfix expression corresponding to the infix\n" +
                "expression a + b#c - d ^ e ^ f is","abc #+def ^^−","abc #+de^f^","ab+c#d−e^f^","−+a#bc^^def",1,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q23);
        Question q24 = new Question("Consider the following C program\n" +
                "main ()\n" +
                "{ int x, y, m, n;\n" +
                "scanf(“%d%d”, &x,&y);\n" +
                "/)Assume x>0 and y>0)/\n" +
                "m=x; n=y;\n" +
                "while (m!=n)\n" +
                "{ if (m>n)\n" +
                "m=m−n;\n" +
                "else\n" +
                "n=n−m;\n" +
                "}\n" +
                "printf(“%d”,n);\n" +
                "}\n" +
                "The program computers","x/y, using repeated subtraction","x mod y using repeated subtraction","the greatest common divisor of x and y","the least common multiple of x only",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q24);
        Question q25 = new Question("What does the following algorithm approximate ? (Assume m>1,\n" +
                "Є> 0).\n" +
                "x=m;\n" +
                "y=1;\n" +
                "while (x−y>!)\n" +
                "{ x=(x+y)/2;\n" +
                "y=m/x;\n" +
                "}\n" +
                "print (x);","log m","m^2","m^(1/2)","m^(1/3)",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q25);
        Question q26 = new Question("Consider the following C program segment\n" +
                "struct Cellnode {\n" +
                "struct CellNode )leftChild;\n" +
                "int element;\n" +
                "struct CellNode )rightChild;\n" +
                "}\n" +
                "int DoSomething (struct CellNode )ptr)\n" +
                "{\n" +
                "int value=0;\n" +
                "if(ptr!=NULL)\n" +
                "{ if (ptr−>leftChild !=NULL)\n" +
                "value=1+DoSomething (ptr−>leftChild);\n" +
                "if (ptr−>rightChild!=NULL)\n" +
                "value=max(value,1+DoSomething(ptr−>\n" +
                "right child));\n" +
                "return (value);\n" +
                "}\n" +
                "The value returned by the function DoSomething when a pointer to\n" +
                "the proof of a non-empty tree is passed as argument is","The number of leaf nodes in the tree","The number of nodes in the tree","The number of internal nodes in the tree","The height of the tree.",4,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q26);
        Question q27 = new Question("Choose the best matching between the programming styles in Group\n" +
                "1 and their characteristics in Group 2.\n" +
                "Group-I         \n" +
                "P. Functional\n" +
                "Q. Logic\n" +
                "R. Object-oriented\n" +
                "S. Imperative\n" +
                "Group-2\n"+
                "1. Command-based, procedural\n" +
                "2. Imperative, abstract data types\n" +
                "3. Side-effect free, declarative, expression\n" +
                "evaluation\n" +
                "4. Declarative, clausal representation,\n" +
                "theorem proving","P-2, Q-3, R-4, S-1","P-4, Q-3, R-2, S-1","P-3, Q-4, R-1, S-2","P-3, Q-4, R-2, S-1",4,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q27);
        Question q28 = new Question("What does the following C-statement declare?\nint(*f)(int*)","A function that takes an integer pointer as argument and returns\n" +
                "an integer","A function that takes an integer pointer as argument and returns\n" +
                "an integer pointer","A pointer to a function that takes an integer pointer as argument\n" +
                "an returns","A function that takes an integer pointer as argument returns a\n" +
                "function pointer",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q28);
        Question q29 = new Question("An Abstract Data type (ADT) is","same as an abstract class","a data type that cannot be instantiated","a data type for which only the operations defined on it can be\n" +
                "used, but none else","all of the above",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q29);
        Question q30 = new Question("A common property of logic programming languages and functional\n" +
                "languages is","both are procedural language","both are based onλ−calculus","both are declarative","all of the above",4,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q30);
        Question q31 = new Question("Which of the following are essential features of an object-oriented\n" +
                "programming languages?\n" +
                "1. Abstraction and encapsulation\n" +
                "2. Strictly-typedness\n" +
                "3. Type-safe property coupled with sub-type rule\n" +
                "4. Polymorphism in the presence of inheritance","1 and 2 only","1 and 4 only","1,2 and 4 only","1,3 and 4 only",2,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q31);
        Question q32 = new Question("A program P reads in 500 integers in the range (0, 100) representing\n" +
                "the scores of 500 students. It then prints the frequency of each score\n" +
                "above 50. What be the best way for P to store the frequencies?","An array of 50 numbers","An array of 100 numbers","An array of 500 numbers","A dynamically allocated array of 550 numbers",1,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q32);
        Question q33 = new Question("An implementation of a queue Q, using two stacks S1 and S2 , is\n" +
                "given below\n" +
                "void insert(Q,x){\n" +
                "push (S1,x);\n" +
                "}\n" +
                "void delete(Q, x){\n" +
                "if (stack-empty (S2))then\nif (stack-empty (S1))then{\n" +
                "print(“Q is empty”);\n" +
                "return;\n" +
                "}\n" +
                "else while (! (stack-empty)(S1)){\n" +
                "x=pop(S1);\n" +
                "push(S2,x);\n" +
                "}\n" +
                "x=pop(S2);\n" +
                "}\n" +
                "Let n insert and m(# n) delete operations be performed in an\n" +
                "arbitrary on an empty queue Q, Let x and y be the number of push\n" +
                "and pop operations performed respectively in the processes. Which\n" +
                "one of the following is true for all m and n ?","n + m <= x < 2n and 2m <= n + m","n + m <= x < 2n and 2m <= y <= 2n","2m <= x < 2n and 2m <= y <= n + m","2m <= x < 2n and 2m <= y <= 2n",3,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q33);
        Question q34 = new Question("Consider the following C-function in which a[n] and b[n] are two\n" +
                "sorted integer arrays and c[n+m] be another integer array.\n" +
                "void xyz (int a[],int b[],int c[]){\n" +
                "int i, j, k;\n" +
                "i=j=k=0;\n" +
                "while((i<n))&&(j<m)\n" +
                "if (a[i]<b[j]c[k++]=a[i++];\n" +
                "else c[k++]=b[j++];\n" +
                "}\n" +
                "Which of the following condition (s) hold (s) after the termination of\n" +
                "the while loop ?\n" +
                "I j<m, k=n+j-1, and a [n-1]<b[j] if i=n\n" +
                "II i<n, k=m+j-1, and b[m-1]#a[i] if j=m","only (I)","only (II)","either (I) or (II) but not both","neither (I) nor (II)",3,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q34);
        Question q35 = new Question("Consider the C code to swap two integers and these five statements:\n" +
                "the code\n" +
                "void swap(int )px,int )py){\n" +
                ")px=)px−)\n" +
                "py;\n" +
                ")py=)px+)py;\n" +
                ")px=)py−)\n" +
                "px;\n" +
                "}\n" +
                "S1: will generate a compilation error\n" +
                "S2: may generate a segmentation fault at runtime depending on the\n" +
                "arguments passed\n" +
                "S3: correctly implements the swap procedure for all input pointers\n" +
                "referreing to integers stored in memory locations accessible tot\n" +
                "he process\n" +
                "S4: implements the swap procedure correctly for some but not all\n" +
                "valid input pointers\n" +
                "S5: may add or subtract integers and pointers","S1","S2 and S3","S2 and S4","S2 and S3",3,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q35);
        Question q36 =new Question("A 3-ary max heap os like a binary max heap, but instead of 2 children,\n" +
                "nodes have 3 children, A 3-ary heap can be represented by an array\n" +
                "as follows: The root is stored in the first location, a [0], nodes in the\n" +
                "next level, from left to right, is stored form a[1] to a[3]. The nodes\n" +
                "from the second level of the tree from left to right are stored from a[4]\n" +
                "location onward.\n" +
                "An item x can be inserted into a 3-ary heap containing n items by\n" +
                "placing x in the location a [n] and pushing it up the tree to satisfy\n" +
                "the heap property.\n\n"+"Which one of the following is a valid sequence of elements in an array\n" +
                "representing 2-ary max heap ?","1, 3, 5, 6, 8, 9","9, 6, 3, 1, 8, 5","9, 3, 6, 8, 5, 1","9, 5, 6, 8, 3, 1",4,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q36);
        Question q37 = new Question("A 3-ary max heap os like a binary max heap, but instead of 2 children,\n" +
                "nodes have 3 children, A 3-ary heap can be represented by an array\n" +
                "as follows: The root is stored in the first location, a [0], nodes in the\n" +
                "next level, from left to right, is stored form a[1] to a[3]. The nodes\n" +
                "from the second level of the tree from left to right are stored from a[4]\n" +
                "location onward.\n" +
                "An item x can be inserted into a 3-ary heap containing n items by\n" +
                "placing x in the location a [n] and pushing it up the tree to satisfy\n" +
                "the heap property.\n\n"+"Suppose the elements 7, 2, 10, and 4 are inserted, in that order, into\n" +
                "the valid 3-ary max heap found in the above question, Q. 33. Which\n" +
                "on of the following is the sequence of items in the array representing\n" +
                "the resultant heap ?","10, 7, 9, 8, 3, 1, 5, 2, 6, 4","10, 9, 8, 7, 6, 5, 4, 3, 2, 1","10, 9, 4, 5, 7, 6, 8, 2, 1, 3","10, 8, 6, 9, 7, 2, 3, 4, 1, 5",1,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q37);
        Question q38 = new Question("The following postfix expression with single digit operands in\n" +
                "evaluated using a stack\n823^/23*+ 51*−\nNote that^is the exponentiation operator. The top two elements of\n" +
                "the stack after the first* is evaluated are","6,1","5,7","3,2","1,5",2,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q38);
        Question q39 = new Question("Which combination of the integer variables x,y, and z makes the\n" +
                "variable a get the value 4 in the following expression?\n" +
                "a = (x > y)?((x> z)?x:z): ((y > z)?y:z)","x = 3,y = 4,z = 2","x = 6,y = 5,z = 3","x = 6,y = 3,z = 5","x = 5,y = 4,z = 5",1,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q39);
        Question q40 = new Question("Choose the correct option to fill ?1 and ?2 so that the program\n" +
                "below prints an input string in reverse order. Assume that the input\nstring is terminated by a newline character.\n" +
                "void reverse (void) {\n" +
                "int c;\n" +
                "if(?1) reverse();\n" +
                "?2\n" +
                "}\n" +
                "main () {\n" +
                "printf(“Enter Text”); printf(\"\\n\");\n" +
                "reverse(); printf(\"\\n\");\n" +
                "}","?1 is (getchar () ! = '\\n')\n" +
                "?2 is getchar (c);","?1 is (getchar ()) ! = '\\n')\n" +
                "?2 is getchar (c);","?1 is (c ! = '\\n')\n" +
                "?2 is putchar (c);","?1 is (( c = getchar ()) ! = '\\n')\n" +
                "?2 is putchar (c);",4,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q40);
        Question q41 = new Question("Consider the program below:\n" +
                "#include<stdio.h>\n" +
                "int fun(int n, int *f_p){\n" +
                "int t,f;\n" +
                "if (n<=1){\n" +
                "*f_p=1\n" +
                "return 1;\n" +
                "}\n" +
                "t=fun(n-1,f_p);\n" +
                "f=t+*f_p;\n" +
                "*f_p=t;\n" +
                "return f;\n" +
                "}\n" +
                "int main () {\n" +
                "int x=15;\nprintf(“%d\\n”,fun(5,&x));\n" +
                "return 0;\n" +
                "}\n" +
                "The value printed is:","6","8","14","15",2,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q41);
        Question q42 = new Question("What is the maximum height of any AVL-tree with 7 nodes ? Assume\n" +
                "that the height of a tree with a single node is 0.","2","3","4","5",2,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q42);
        Question q43 = new Question("Which one of the following array represents a binary max-heap?Which one of the follow9ng array represents a binary max-heap?","{25, 12, 16, 13, 10, 8, 14}","{25, 14, 13, 16, 10, 8, 12}","{25, 14, 16, 13, 10, 8, 12}","{25, 14, 12, 13, 10, 8, 16}",3,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q43);
        Question q44 = new Question("What is the appropriate paring of items in the two columns listing\n" +
                "various activities encountered in a software life cycle ?\n\nP. Requirement Capture\n" +
                "Q. Design\n" +
                "R. Implementation\n" +
                "S. Maintenance\n" +
                "1. Module Development and\n" +
                "Integration\n" +
                "2. Domain Analysis\n" +
                "3. Structural and Behavioral\n" +
                "Modeling\n" +
                "4. Performance Tuning","P-3 Q-2, R-4 S-1","P-2 Q-3 R-1 S-4","P-3 Q-2 R-1 S-4","P-2 Q-3 R-4 S-1",2,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q44);
        Question q45 = new Question("What is the value printed by the following C program ?\n" +
                "#include<stdio.h>\n" +
                "int f(int *a, int n)\n" +
                "{\n" +
                "if (n<=0) return 0;\n" +
                "else if (*a%2==0) return *a+f(a+1,n-1);\n" +
                "else return *a-f(a+1,n-1);\n" +
                "}\nint main()\n" +
                "{\n" +
                "int a[]={12, 7, 13, 4, 11, 6};\n" +
                "printf(“%d”,f(a,6));\n" +
                "return 0;\n" +
                "}","-9","5","15","19",3,Question.DIFFICULTY_HARD,Category.PROGRAMMING_AND_DATASTRUCTURES);
        addQuestion(q45);

        // operating systems

        Question q46 = new Question("Which of the following statements is false ?","Virtual memory implements the translation of a program’s\n" +
                "address space into physical memory address space.","Virtual memory allows each program to exceed the size of the\n" +
                "primary memory.","Virtual memory increases the degree of multi-programming",
                "Virtual memory reduces the context switching overhead",1,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q46);
        Question q47 = new Question("Consider a set of n tasks with known runtimes , , ........ r r rn\n" +
                "1 2 to be\n" +
                "run on a uniprocessor machine. Which of the following processor\n" +
                "scheduling algorithms will result in the maximum throughput ?","Round-Robin","Shortest-Job-First","Highest-Response-Ratio-Next",
                "First-come-First-Served",2,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q47);
        Question q48 = new Question("Where does the swap space reside ?","RAM","DISK","ROM",
                "On-Chip cache",2,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q48);
        Question q49 = new Question("Consider a virtual memory system with FIFO page replacement\n" +
                "policy. For an arbitrary page access pattern, increasing the number\n" +
                "of page frames in main memory will.","Always decrease the number of page faults","Always increase the number of page faults","Sometimes increase the number of page faults",
                "Never affect the number of page faults",3,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q49);
        Question q50 = new Question("Consider a machine with 64 MB physical memory and a 32-bit virtual\n" +
                "address space. If the page size is 4 KB, what is the approximate size\n" +
                "of the page table ?","16 MB","8MB","2MB",
                "24MB",2,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q50);
        Question q51 = new Question("Consider Peterson’s algorithm for mutual exclusion between two\n" +
                "concurrent processes i and j . The program executed by process is\n" +
                "shown below.\n" +
                "repeat\n" +
                "fl ag[i]=true;\n" +
                "turn=j;\n" +
                "while(p)do no-op;\n" +
                "Enter critical section, perform actions, then\n" +
                "exit critical section\n" +
                "Flag[i]=false;\n" +
                "Perform other non-critical section actions.\n" +
                "Until false;\n" +
                "For the program to guarantee mutual exclusion, the predicate P in\nthe while loop should be","flag [j]= true and turn =j","flag [j]=true and turn =j","flag [i]=true and turn=j",
                "flag [i]=true and turn=i",2,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q51);
        Question q52 = new Question("Which of the following scheduling algorithms is non-preemptive ?","Round Robin","First-In First-Out","Multilevel Queue Scheduling",
                "Multilevel Queue Scheduling with Feedback",2,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q52);
        Question q53 = new Question("The optimal page replacement algorithm will select the page that","Has not been used for the longest time in the past","Will not be used for the longest time in the future","Has been used least number of times",
                "Has been used most number of times",2,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q53);
        Question q54 = new Question("Which combination of the following features will suffice to characterize\n" +
                "an OS as a multi-programmed OS ? More than one program may be\n" +
                "loaded into main memory at the same time for execution. (B) If\n" +
                "a program waits for certain events such as I/O, another program\n" +
                "is immediately scheduled for execution. (C) If the execution of a\n" +
                "program terminates, another program is immediately scheduled for\n" +
                "execution.","A","A and B","A and C",
                "A,B and C",2,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q54);
        Question q55 = new Question("In the index allocation scheme of blocks to a file, the maximum\n" +
                "possible size of the file depends on","The size of the blocks, and the size of the address of the blocks","The number of blocks used for the index, and the size of the\n" +
                "blocks.","The size of the blocks, the number of blocks used for the index,\n" +
                "and the size of the address of the blocks.",
                "None of the above.",2,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q55);
        Question q56 = new Question("Using a larger block size in a fixed block size file system leads to","better disk throughput but poorer disk space utilization","better disk throughput and better disk space utilization","poorer disk throughput but better disk space utilization",
                "poorer disk throughput and poorer disk space utilization",1,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q56);
        Question q57 = new Question("In a system with 32 bit virtual addresses and 1 KB page size, use of\n" +
                "one-level page tables for virtual to physical address translation is not\n" +
                "practical because of","the large amount of internal fragmentation","the large amount of external fragmentation","the large memory overhead in maintaining page tables",
                "the large computation overhead in the translation process",3,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q57);
        Question q58 = new Question("A uni-processor computer system only has two processes, both of\n" +
                "which alternate 10 ms CPU bursts with 90 ms I/O bursts. Both the\n" +
                "processes were created at nearly the same time. The I/O of both\n" +
                "processes can proceed in parallel. Which of the following scheduling\n" +
                "strategies will result in the least CPU utilizations (over a long period\n" +
                "of time) for this system ?","First come first served scheduling","Shortest remaining time first scheduling","Static priority scheduling with different priorities for the two\n" +
                "processes",
                "Round robin scheduling with a time quantum of 5 ms.",1,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q58);
        Question q59 = new Question("A processor uses 2-level page table fro virtual to physical address\n" +
                "translation. Page table for both levels are stored in the main memory.\n" +
                "Virtual and physical addresses are both 32 bits wide. The memory is\n" +
                "byte addressable. For virtual to physical address translation, the 10\n" +
                "most significant bits of the virtual address are used as index into the\n" +
                "first level page table while the next 10 bits are used as index into the\n" +
                "second level page table. The 12 least significant bits of the virtual\n" +
                "address are used as offset within the page. Assume that the page\n" +
                "table entries in both levels of page tables are 4 a bytes wide. Further,\n" +
                "the processor has a translation look aside buffer(TLB), with a hit\n" +
                "rate of 96%. The TLB caches recently used virtual page numbers and\n" +
                "the corresponding physical page numbers. The processor also has a\n" +
                "physically addressed cache with a bit ratio of 90%. Main memory\n" +
                "access time is 10 ns, cache access time is 1 ns, and {LB access time\nia also 1 ns\n\nAssuming that no page faults occur, the average time taken to access\n" +
                "a virtual address is approximately (to the nearest 0.5 ns)","1.5 ns","2 ns","3 ns",
                "4 ns",4,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q59);
        Question q60 = new Question("A processor uses 2-level page table fro virtual to physical address\n" +
                "translation. Page table for both levels are stored in the main memory.\n" +
                "Virtual and physical addresses are both 32 bits wide. The memory is\n" +
                "byte addressable. For virtual to physical address translation, the 10\n" +
                "most significant bits of the virtual address are used as index into the\n" +
                "first level page table while the next 10 bits are used as index into the\n" +
                "second level page table. The 12 least significant bits of the virtual\n" +
                "address are used as offset within the page. Assume that the page\n" +
                "table entries in both levels of page tables are 4 a bytes wide. Further,\n" +
                "the processor has a translation look aside buffer(TLB), with a hit\n" +
                "rate of 96%. The TLB caches recently used virtual page numbers and\n" +
                "the corresponding physical page numbers. The processor also has a\n" +
                "physically addressed cache with a bit ratio of 90%. Main memory\n" +
                "access time is 10 ns, cache access time is 1 ns, and {LB access time\nis also 1 ns\n\nSuppose a process has only the following pages in its virtual address\n" +
                "space; two contiguous code pages starting at virtual address\n" +
                "0 # 0000000, two contiguous data pages starting at virtual address\n" +
                "0 # 00400000,and a stack page starting at virtual address\n" +
                "0 # FFFFF000. The amount of memory required for storing the page\n" +
                "tables of this process is","8 KB","12 KB","16 KB",
                "20 KB",4,Question.DIFFICULTY_EASY,Category.OPERATING_SYSTEMS);
        addQuestion(q60);
        Question q61 = new Question("Which of the following will always lead to an output staring with\n" +
                "‘001100110011’?",
                "P(S) at W, V(S) at X, P(T) at Y, V(T) at Z, S and T initially\n" +
                        "1",
                "P(S) at W, V(T) at X, P(T) at Y, V(S) at Z, S initially 1, and\n" +
                        "T initially 0",
                "P(S) at W, V(T) at X, P(T) at Y, V(S) at Z, S and T initially 1",
                "P(S) at W, V(T) at X, P(T) at Y, V(S) at Z, S initially 1, and\n" +
                        "T initially 0",2,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q61);
        Question q62 = new Question("Which of the following will ensure that the output string never\ncontains a substring of the form 0.1” or 10”1 where n is odd?",
                "P(S) at W, V(S) at X, P(T) at Y, V(T) at Z, S and T initially 1",
                "P(S) at W, V(T) at X, P(T) at Y, V(S) at Z, S and T initially 1",
                "P(S) at W, V(S) at X, P(T) at Y, V(S) at Z, S initially 1",
                "(S) at W, V(T) at X, P(T) at Y, P(S) at Z, S and T initially 1",3,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q62);
        Question q63 = new Question("Consider the following statements with respect to user-level threads\n" +
                "and kernel-supported threads\n" +
                "(i) Context which is faster with kernel-supported threads\n" +
                "(ii) For user-level threads. a system call can block the entire process\n" +
                "(iii) Kernel-supported threads can be scheduled independently\n" +
                "(iv) User-level threads are transparent to the kernel\n" +
                "Which of the above statements are true?",
                "(ii),(iii) and (iv) only",
                "(ii) and (iii) only",
                "(i) and (iii) only",
                "(i) and (ii) only",2,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q63);
        Question q64 = new Question("Consider an operating system capable of loading and executing a\n" +
                "single sequential user process at a time. The disk head scheduling\n" +
                "algorithm used is First Come First Served (FCFS). If FCFS is replaced\n" +
                "by shortest seek Time Fist (SSTF), claimed by the vendor to given\n" +
                "50% better beachmark results, what is the expected improvement in\n" +
                "the I/O performance of user programs?",
                "50%",
                "40%",
                "25%",
                "0%",4,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q64);
        Question q65 = new Question("The minimum number of page frames that must be allocated to a\n" +
                "running process in a virtual memory environment is determined by",
                "the instruction set architecture",
                "page size",
                "physical memory size",
                "number of processes in memory",1,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q65);
        Question q66 = new Question("Consider the following set of processes, with the arrival times and the\n" +
                "CPU-burst times given in milliseconds\n"+"Process\tArrival time\tBurst time\np1\t0\t5\np2\t1\t3\np3\t2\t3\np4\t4\t1\n\nWhat is the average turnaround time for these processes with\n" +
                "the preemptive shortest remaining processing time first (SRPT)\n" +
                "algorithm?",
                "5.50",
                "5.75",
                "6.00",
                "6.25",1,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q66);
        Question q67 = new Question("Consider a system with a two-level paging scheme in which a regular\n" +
                "memory access takes 150 nanoseconds, and servicing a page fault\n" +
                "takes 8 milliseconds. An average instruction takes 100 nanoseconds\n" +
                "of CPU time, and two memory accesses. The TLB hit ratio is 99%,\n" +
                "and the page fault rate is one in every 10,000 instructions. What is\n" +
                "the effective average instruction execution time?",
                "645 nanoseconds",
                "1050 nanoseconds",
                "1215 nanoseconds",
                "1230 nanoseconds",4,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q67);
        Question q68 = new Question("A Unix-style I-node has 10 direct pointers and one single, one double\n" +
                "and one triple indirect pointers. Disk block size is 1 Kbyte, disk block\n" +
                "address is 32 bits, and 48-bit integers are used. What is the maximum\n" +
                "possible file size?",
                "2^24 bytes",
                "2^32 bytes",
                "2^34 bytes",
                "2^48 bytes",3,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q68);
        Question q69 = new Question("Consider three CPU-intensive processes, which require 10,20 and 30\n" +
                "time units and arrive at times 0,2, and 6, respectively. How many\n" +
                "context switches are needed if the operating system implements a\n" +
                "shortes remaining time first scheduling algorithm? Do not count the\n" +
                "context switches at time zero and at the end",
                "1",
                "2",
                "3",
                "4",2,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q69);
        Question q70 = new Question("The atomic feth-and-set x,y instruction unconditionally sets the\n" +
                "memory location x to 1 and fetches the old value of x in y without\n" +
                "allowing any intervening access to the memory location x . Consider\n" +
                "the following implementation of P and V functions on a binary\n" +
                "semaphore S.\n" +
                "void p (binary_semaphore*S){\n" +
                "unsigned y;\n" +
                "unsigned*x =& (S->value);}\n" +
                "do {\n" +
                "fetch-and-set x,y;\n" +
                "} while(y);\n" +
                "}\n" +
                "void V (binary_semphore*S){\n" +
                "{S_>value = 0;\n" +
                "}\n" +
                "Which one of the following is true?",
                "The implementation may not work if context switching is disabled\n" +
                        "in P",
                "Instead of using fetch-and-set, a pair of normal load/store can\n" +
                        "be used",
                "The implementation of V is wrong",
                "The code does not implement a binary semaphore",1,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q70);
        Question q71 = new Question("A CPU generates 32-bit virtual addresses. The page size is 4 KB. The\n" +
                "processor has a translation look-aside buffer (TLB) which can hold a\n" +
                "total of 128 page table entries and is 4-way set associative.\n" +
                "The minimum size of the TLB tag is",
                "11 bits",
                "13 bits",
                "15 bits",
                "20 bits",1,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q71);
        Question q72 = new Question("A computer system supports 32-bit virtual addresses as well as 32-bit\n" +
                "physical addresses, Since the virtual address space is of the same size\n" +
                "as the physical address space, the operating system designers decide\n" +
                "to get rid of the virtual entirely. Which one of the following is true?",
                "Efficient implementation of multi-user support is no longer\n" +
                        "possible",
                "The processor cache organization can be made more efficient now",
                "Hardware support for memory management is no longer needed",
                "CPU scheduling can be made more efficient now",3,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q72);
        Question q73 = new Question("Consider three processes (process id 0,1,2, respectively) with compute\n" +
                "time bursts 2,4, and 8 time units. All processes arrive at time zero.\n" +
                "Consider the longest remaining time first (LRTF) scheduling\n" +
                "algorithm. In LRTF ties are broken by giving priority to the process\n" +
                "with the lowest process id . The average turn around time is",
                "13 units",
                "14 units",
                "15 units",
                "16 units",1,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q73);
        Question q74 = new Question("Consider three processes, all arriving at time zero, with total\n" +
                "execution time of 10, 20 and 30 units, respectively. Each process\n" +
                "spends the first 20% of execution time doing I/O, the next 70% of\n" +
                "time doing computation, and the last 10% of time doing I/O again.\nThe operating system uses a shortest remaining compute time first\n" +
                "scheduling algorithm and schedules a new process either when the\n" +
                "running process get blocked on I/O or when the running process\n" +
                "finishes its compute burst. Assume that all I/O operations can be\n" +
                "overlapped as much as possible. For what percentage of time does the\n" +
                "CPU remain idle?",
                "0%",
                "10.6%",
                "30.0%",
                "89.4%",2,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q74);
        Question q75 = new Question("Group-1 contains some CPU scheduling algorithms and group-2\n" +
                "contains some applications. Match entries in Group-1 entries in\n" +
                "Group-2\n" +
                "Group-1\n" +
                "P. Gang Scheduling\n" +
                "Q. Rate Monotonic Scheduling\n" +
                "R. Fair Share scheduling\n" +
                "Group-2\n" +
                "1. Guaranteed Scheduling\n" +
                "2. Real-time Scheduling\n" +
                "3. Thread Scheduling",
                "P-3;Q-2;R-1",
                "P-1;Q-2;R-3",
                "P-2;Q-3;R-1",
                "P-1;Q-3;R-2",1,Question.DIFFICULTY_MEDIUM,Category.OPERATING_SYSTEMS);
        addQuestion(q75);
        Question q76 = new Question("Barrier is a synchronization construct where a set of processes\n" +
                "synchronizes globally i.e. each process in the set arrives at the barrier\n" +
                "and waits for all others to arrive and then all processes leave the\n" +
                "barrier. Let the number of processes in the set be three and S be a\n" +
                "binary semaphore with the usual P and V functions. Consider the\n" +
                "following C implementation of a barrier with line numbers shown on\n" +
                "the left.\n" +
                "Void barrier(void) {\n" +
                "1 : P(S)\n" +
                "2 : Process_arrived++;\n" +
                "3 : V (S) :\n" +
                "4 : while (process_arrived’=3);\n" +
                "5 : P(S);\n" +
                "6 : Precess_left++;\n" +
                "7 : if(process_left==3)\n" +
                "8 : process_arrived=0;\n" +
                "9 : process_left+0;\n" +
                "10 : }\n" +
                "11 : V(S);\n" +
                "}\n"+"The variable process_arrived and process_left are shared among\n" +
                "all processes and are initialized to zero. In a concurrent program\n" +
                "all the three processes call the barrier function when they need to\n" +
                "synchronize globally.\n\nThe above implementation of barrier is incorrect. Which one of the\n" +
                "following is true?",
                "The barrier implementation is wrong due to the use of binary\n" +
                        "semaphore S",
                "The barrier implementation may lead to a deadlock if two barrier\n" +
                        "invocations are used in immediate succession",
                "Lines 6 to 10 need not be inside a critical section",
                "The barrier implementation is correct if there are only two\n" +
                        "processes instead of three",2,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q76);
        Question q77 = new Question("Barrier is a synchronization construct where a set of processes\n" +
                "synchronizes globally i.e. each process in the set arrives at the barrier\n" +
                "and waits for all others to arrive and then all processes leave the\n" +
                "barrier. Let the number of processes in the set be three and S be a\n" +
                "binary semaphore with the usual P and V functions. Consider the\n" +
                "following C implementation of a barrier with line numbers shown on\n" +
                "the left.\n" +
                "Void barrier(void) {\n" +
                "1 : P(S)\n" +
                "2 : Process_arrived++;\n" +
                "3 : V (S) :\n" +
                "4 : while (process_arrived’=3);\n" +
                "5 : P(S);\n" +
                "6 : Precess_left++;\n" +
                "7 : if(process_left==3)\n" +
                "8 : process_arrived=0;\n" +
                "9 : process_left+0;\n" +
                "10 : }\n" +
                "11 : V(S);\n" +
                "}\nThe variable process_arrived and process_left are shared among\n" +
                "all processes and are initialized to zero. In a concurrent program\n" +
                "all the three processes call the barrier function when they need to\n" +
                "synchronize globally.\n\nWhich one of the following rectifies the problem in the implementation?",
                "lines 6 to 10 are simply replaced by process_arrived",
                "At the beginning of the barrier the first process to enter the barrier\n" +
                        "waits until process_arrived becomes zero before proceeding to\n" +
                        "execute P(S)",
                "Context switch is disabled at the beginning of the barrier and\n" +
                        "re-enabled at the end.",
                "The variable process_left is made private instead of shared",2,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q77);
        Question q78 = new Question("Consider the following statements about user level threads and kernel\n" +
                "level threads. Which one of the following statements is FALSE?",
                "Context switch time is longer for kernel level threads than for\n" +
                        "user level threads",
                "User level threads do not need any hardware support",
                "Related kernal level thread can be scheduled on different\n" +
                        "processors in a multiprocessor system",
                "Blocking one kernel level thread blocks all related threads",4,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q78);
        Question q79 = new Question("An operating system uses Shortest Remaining Time first (SRT) process\n" +
                "scheduling algorithm. Consider the arrival times and execution times\n" +
                "for the following processes\n\nProcess\tExecution Time\tArrival Time\np1\t20\t0\np2\t25\t15\np3\t10\t30\np4\t15\t45\n\nWhat is the total waiting time for process p2\n",
                "5",
                "15",
                "40",
                "55",2,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q79);
        Question q80 = new Question("A virtual memory system uses first In First Out (FIFO) page\n" +
                "replacement policy and allocates a fixed number of frames to a\n" +
                "process. Consider the following statements:\n" +
                "P: Increasing the number of page frames allocated to a process\n" +
                "sometimes increases the page fault rate.\n" +
                "Q: Some program do not exhibit locality of reference.\n" +
                "Which one of the following is TRUE?",
                "Both P and Q are ture, and Q is the reason for P",
                "Both P and Q are true, but Q is not the reason for P",
                "P is false, but Q is true",
                "Both P and Q are false",2,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q80);
        Question q81 = new Question("A single processor system has three resource types X,Y, and Z, which\n" +
                "are shared by three processes. There are 5 units of each resource type.\n" +
                "Consider the following scenario, where the column alloc denotes the\n" +
                "number of units of each resource type allocated to each process, and\n" +
                "the column request denotes the number of units of each resource type\n" +
                "requested by a process in order to complete execution. Which of these\n" +
                "processes will finish LAST?\n\nalloc\t\trequest\np0   121\t\t103\np1   201\t\t012\np2   221\t\t120\n",
                "p0",
                "p1",
                "p2",
                "None of the above, since the system is in a deadlock",3,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q81);
        Question q82 = new Question("Two processes, P1 and P2, need to access a critical section of\n" +
                "code. Consider the following synchronization construct used by the\n" +
                "processes:\n" +
                "/* P1 */\n" +
                "while (true) {\n" +
                "wants1=true;\n" +
                "while(wants2==true);\n" +
                "/* Critical\n" +
                "Section*/\n" +
                "wants 1 = false;\n" +
                "}\n" +
                "/* Remainder section*/\n" +
                "/*P2*/\n" +
                "while (true) {\n" +
                "wants2 = true;\n" +
                "while (wants1 == true);\n" +
                "/* Critical\n" +
                "Section*/\n" +
                "wants 2 = false;\n" +
                "}\n" +
                "/*Remainder section*/\n" +
                "Here, wants 1 and wants 2 are shared variables, Which are initialized\n" +
                "to false. Which one of the following statements is TRUE about the above construct?",
                "It does not ensure mutual exclusion.",
                "It does not ensure bounded waiting",
                "It requires that processes enter the critical section in strict\n" +
                        "alternation",
                "It does not prevent deadlocks, but ensures mutual exclusion",4,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q82);
        Question q83 = new Question("A process has been allocated 3 page frames. Assume that none of the\n" +
                "pages of the process are available in the memory initially. The process\n" +
                "makes the following sequence of page references (reference string):\n" +
                "1,2,1,3,7,4,5,6,3,1.\n\nIf optimal page replacement policy is used, how many page faults\n" +
                "occur for the above reference string?",
                "7",
                "8",
                "9",
                "10",1,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q83);
        Question q84 = new Question("A process has been allocated 3 page frames. Assume that none of the\n" +
                "pages of the process are available in the memory initially. The process\n" +
                "makes the following sequence of page references (reference string):\n" +
                "1,2,1,3,7,4,5,6,3,1.\n\nLeast Recently Used (LRU) page replacement policy is a practical\n" +
                "approximation to optimal page replacement. For the above reference\n" +
                "string, how many more page faults occur with LRU than with the\n" +
                "optimal page replacement policy?",
                "0",
                "1",
                "2",
                "3",3,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q84);
        Question q85 = new Question("Which of the following system calls results in the sending of SYN\n" +
                "packets?",
                "socket",
                "bind",
                "listen",
                "connect",4,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q85);
        Question q86 = new Question("The data block of a very large file in the Unix file system are allocated\n" +
                "using",
                "Contiguous allocation",
                "Linked allocation",
                "indexed allocation",
                "an extension of indexed allocation",4,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q86);
        Question q87 = new Question("Which of the following statements about synchronous and\n" +
                "asynchronous I/O is NOT true?",
                "An ISR is invoked on completion of I/O in synchronous I/O but\n" +
                        "not in asynchronous I/O",
                "In both synchronous and asynchronous I/O an ISR (Interrupt\n" +
                        "Serive Routine) is invoked after completion of the I/O",
                "A process making a synchronous I/O cal waits until I/O is\n" +
                        "complete, but a process making an asynchronous I/O call does\n" +
                        "not wait for completion of the I/O",
                "In the case of synchronous I/O, the process waiting for the\n" +
                        "completion of I/O is woken up by the ISR that is invoked afterr\n" +
                        "the completion of I/O",2,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q87);
        Question q88 = new Question("Which of the following is NOT true of deadlock prevention and\n" +
                "deadlock avoidance schemes?",
                "In deadlock prevention, the request for resources is always granted\n" +
                        "if the resulting state is safe",
                "In deadlock avoidance, the request for resources is always granted\n" +
                        "if the resulting state is safe",
                "Deadlock avoidance is less restrictive than deadlock prevention",
                "Deadlock avoidance requires knowledge of resource requirements\n" +
                        "a priori",3,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q88);
        Question q89 = new Question("A processor uses 36 bit physical addresses and 32 bit virtual addresses,\n" +
                "with a page frame size of 4 Kbytes. Each page table entry is of size 4\n" +
                "bytes. A three level page table is used for virtual-to-physical address\n" +
                "translation, where the virtual address is used as follows\n" +
                ": bits 30-31 are used to index into the first level page table,\n" +
                ": bits 21-29 are used to index into second level page table\n" +
                ": bits 12-20 are used to index into third level page table\n" +
                ": bits 0-11 are used as offset within the page\n" +
                "The number of bits required for addressing the next level page table\n" +
                "(or page frame) in the page table entry of the first, second and third\n" +
                "level page table are respectively",
                "20,20 and 20",
                "24,24 and 24",
                "24,24 and 20",
                "25,25 and 24",2,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q89);
        Question q90 = new Question("Consider a disk system with 100 cylinders. The requests to access the\n" +
                "cylinders occur in following sequence :\n" +
                "4, 34, 10, 7, 19, 73, 2, 15, 6, 20\n" +
                "Assuming that the head is currently at cylinder 50, what is the time\n" +
                "taken to satisfy all requests if it takes 1 ms to move from one cylinder\n" +
                "to adjacent one and shortest seek time first policy is used ?",
                "95 ms",
                "119 ms",
                "233 ms",
                "276 ms",2,Question.DIFFICULTY_HARD,Category.OPERATING_SYSTEMS);
        addQuestion(q90);


        //


        Question q91 = new Question("Which of the following statements true ?",
                "If a language is context free it can be always be accepted by a\n" +
                        "deterministic push-down automaton.",
                "The union of two context free language is context free.",
                "The intersection of two context free language is context free",
                "The complement of a context free language is context free",2,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q91);
        Question q92 = new Question("Given an arbitary non-deterministic finite automaton (NFA) with N\n" +
                "states, the maximum number of states in an equivalent minimized\n" +
                "DFA is at least.",
                "N^2",
                "2^N",
                "2N",
                "N!",3,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q92);
        Question q93 = new Question("Consider a DFA over Σ = {a,b} accepting all strings which have\n" +
                "number of a's divisible by 6 and number of b's divisible by 8. What is\n" +
                "the minimum number of states that the DFA will have ?",
                "8",
                "14",
                "15",
                "48",3,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q93);
        Question q94 = new Question("Consider the following problem x .\n" +
                "Given a Turing machine M over the input alphabet Σ, any state q\n" +
                "of M.\n" +
                "And a word w ! Σ) does the computation of M on w visit the state q?\nWhich of the following statements about x is correct ?",
                "x is decidable",
                "x is undecidable but partially decidable",
                "x is undecidable and not even partially decidable",
                "x is not a decision problem",1,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q94);
        Question q95 = new Question("The smallest finite automaton which accepts the language {x length\n" +
                "of x is divisible by 3} has",
                "2 states",
                "3 states",
                "4 states",
                "5 states",2,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q95);
        Question q96 = new Question("Which of the following is true ?",
                "The complement of a recursive language is recursive.",
                "The complement of a recursively enumerable language is recursively enumerable",
                "The complement of a recursive language is either recursive or\n" +
                        "recursively enumerable",
                "The complement of a context-free language is context-free.",1,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q96);
        Question q97 = new Question("The C language is :",
                "A context free language",
                "A context sensitive language",
                "A regular language",
                "Parsable fully only by a Turing machine",1,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q97);
        Question q98 = new Question("The language accepted by a Pushdown Automaton in which the stack\n" +
                "is limited to 10 items is best described as",
                "Context free",
                "Regular",
                "Deterministic Context free",
                "Recursive",2,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q98);
        Question q99 = new Question("Ram and Shyam have been asked to show that a certain problem Π\n" +
                "is NP-complete. Ram shows a polynomial time reduction from the\n" +
                "3-SAT problem to Π , and Shyam shows a polynomial time reduction\n" +
                "from Π to 3-SAT. Which of the following can be inferred from these\n" +
                "reduction?",
                "Π is NP-hard but not NP-complete",
                "Π is in NP, but is not NP-complete",
                "Π is NP-complete",
                "Π is neither Np-hard, nor in NP",3,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q99);
        Question q100 = new Question("Nobody knows yet if P = NP. Consider the language L defined as\n" +
                "follows\n" +
                "L = (0 1)* P NP\n" +
                "     φ othervise\n" +
                "Which of the following statements is true?",
                "L is recursive",
                "L is recursively enumerable but not recursive",
                "L is not recursively enumerable",
                "Whether L is recursive or not will be known after we find out if\n" +
                        "P = NP",1,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q100);
        Question q101 = new Question("The regular expression 0 *( 10)* denotes the same set as",
                "(1 * 0) * 1 *",
                "0 + (0 + 10) *",
                "(0 + 1) * 10(0 + 1) *",
                "None of the above",2,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q101);
        Question q102 = new Question("If the strings of a language L can be effectively enumerated in\n" +
                "lexicographic (i.e. alphabetic) order, which of the following statements\n" +
                "is true?",
                "L is necessarily finite",
                "L is regular but not necessarily finite",
                "L is context free but not necessarily regular",
                "L is recursive but not necessarily context free",2,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q102);
        Question q103 = new Question("Let G = ({S},{a,b}R,S be a context free grammar where the rule set\n" +
                "R is\n" +
                "S -> a S b | S S | ε\n" +
                "Which of the following statements is true?",
                "G is not ambiguous",
                "There exist x,y, Є L(G) such that xy !Є L(G)",
                "There is a deterministic pushdown automaton that accepts L(G)",
                "We can find a deterministic finite state automaton that accepts\n" +
                        "L(G)",3,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q103);
        Question q104 = new Question("Define languages L0 and L1 as follows\n" +
                "L0 = {< M,w,0 >|M halts on w}\n" +
                "L0 = {< M,w,1 >|M does not halts on w}\n" +
                "Here< M,w, i > is a triplet, whose first component. M is an encoding\n" +
                "of a Turing Machine, second component,w, is a string, and third\n" +
                "component, t , is a bit.\n" +
                "Let L = L0 U L1. Which of the following is true?",
                "L is recursively enumerable, but !L is not",
                "!L is recursively enumerable, but L is not",
                "Both L and !L are recursive",
                "Neither L nor !L is recursively enumerable",2,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q104);
        Question q105 = new Question("The language {a^mb^(m+n) | m,n <= 1} is",
                "regular",
                "context-free but not regular",
                "context sensitive but not context free",
                "type-0 but not context sensitive",2,Question.DIFFICULTY_HARD,Category.AUTOMATA);
        addQuestion(q105);
        Question q106 = new Question("Consider three decision problem P1,P2 and P3. It is known that P1 is\n" +
                "decidable and P2 is undecidable. Which one of the following is TRUE?",
                "P3 is decidable if P1 is reducible to P3",
                "P3 is undecidable if P3 is reducible to P2",
                "PL3 is undecidable if P2 is reducible to P3",
                "P3 is decidable if P3 is reducible to P2’s complement",3,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q106);
        Question q107 = new Question("Let Nf and Np denote the classes of languages accepted by nondeterministic\n" +
                "finite automata and non-deterministic push-down\n" +
                "automata, respectively. let Df and DP denote the classes of languages\n" +
                "accepted by deterministic finite automata and deterministic push down automata, respectively. Which one of the following is TRUE?",
                "Df Є Nf and DP Є NP",
                "Df Є Nf and DP = NP",
                "Df = Nf and DP = NP",
                "Df = Nf and DP Є NP",4,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q107);
        Question q108 = new Question("Let L1 be a recursive language, and let L2 be a recursively enumerable\n" +
                "but not a recursive language. Which one of the following is TRUE?",
                "~L is recursive and ~L is recursively enumerable",
                "~L is recursive and ~L is not recursively enumerable",
                "~L and ~L are recursively enumerable",
                "~L is recursively enumerable and ~L is recursive",2,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q108);
        Question q109 = new Question("Consider the languages\n" +
                "L {WW^R |W Є {0,1}*}\n" +
                "L {W#W^R |W Є {0,1}*}" +", where # is a special symbol\n" +
                "L3 = {WW|W Є {0,1}*}\n" +
                "Which one of the following is TRUE?",
                "L1 is a deterministic CFL",
                "L2 is a deterministic CFL",
                "L3 is a CFL, but not a deterministic CFL",
                "L3 is a deterministic CFL",2,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q109);
        Question q110 = new Question("Consider the following two problems on undirected graphs\nα: Given G(V,E), does G have an independent set of size |V |− 4?\n" +
                "β: Given G(V,E), does G have an independent set of size 5?\n" +
                "Which one of the following is TRUE?",
                "α is in the P and β is NP-complete",
                "α is NP-complete and β is P",
                "Both α and β are NP-complete",
                "Both α and β are in P",3,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q110);
        Question q111 = new Question("Let S be an NP-complete problem Q and R be two other problems\n" +
                "not known to be in NP. Q is polynomial-time reducible to S and S\n" +
                "is polynomial-time reducible to R. Which one of the following\n" +
                "statements is true?",
                "R is NP-complete",
                "R is NP-hard",
                "Q is NP-complete",
                "Q is NP-hard",1,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q111);
        Question q112 = new Question("Let SHAM, be the problem of finding a Hamiltonian cycle in a graph\n" +
                "G + (V,E) with [V] divisible by 3 and DHAM’ be the problem of\n" +
                "determining if a Hamltonian cycle exists in such graphs. Which one\n" +
                "of the following is true?",
                "Both DHAM, and SHAM, are NP-hard",
                "SHAM, is NP-hard, but DHAM, is not",
                "DHAM, is NP-hard, but SHAM, is not",
                "Neither DHAM,nor SHAM, is NP-hard",2,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q112);
        Question q113 = new Question("Consider the following statements about the context-free grammar,\n" +
                "G = {S -> SS,S -> ab,S -> ba,S -> Є}\n" +
                "1. G is ambiguous.\n" +
                "2. G produces all strings with equal number of a’s and b’s .\n" +
                "3. G can be accepted by a deterministic PDA.\n" +
                "Which combination below expresses all the true statements about G\n" +
                "?",
                "1 only",
                "1 and 3 only",
                "2 and 3 only",
                "1, 2 and 3",2,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q113);
        Question q114 = new Question("Let L1 be regular language, L2 be a deterministic context-free language\n" +
                "and L3 a recursively enumerable, but not recursive, language. Which\n" +
                "one of the following statements is false?",
                "L1 Ո L2 is a deterministic CFL",
                "L3 Ո L1 is recursive",
                "L1 U L2 is context free",
                "L1 Ո L2 Ո L3 is recursively enumerable",2,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q114);
        Question q115 = new Question("Consider the regular language L = (111 + 111111)* . The minimum\n" +
                "number of states in any DFA accepting this languages is",
                "3",
                "5",
                "8",
                "9",4,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q115);
        Question q116 = new Question("Which of the following problems is undecidable?",
                "Membership problem for CFGs",
                "Ambiguity problem for CFGs",
                "Finiteness problem for FSAs",
                "Equivalence problem for FSAs",2,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q116);
        Question q117 = new Question("Which of the following is TRUE?",
                "Every subset of a regular set is regular",
                "Every finite subset of a non-regular set is regular",
                "The union of two non-regular sets is not regular",
                "Infinite union of finite sets is regular",2,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q117);
        Question q118 = new Question("A minimum state deterministic finite automation accepting the\n" +
                "language L = {w | w Є (0,1}* , number of 0s &1s in w are divisible\n" +
                "by 3 and 5, respectively} has",
                "15 states",
                "11 states",
                "10 states",
                "9 states",1,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q118);
        Question q119 = new Question("Which of the following in true for the language{a^P | P is a prime}?",
                "It is not accepted by a Turning Machine",
                "It is regular but not context-free",
                "It is context-free but not regular",
                "It is neither regular nor context-free, but accepted by a Turing\n" +
                        "machine",4,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q119);
        Question q120 = new Question("Which of the following are decidable?\n" +
                "1. Whether the intersection of two regular languages is infinite\n" +
                "2. Whether a given context-free language is regular\n" +
                "3. Whether two push-down automata accept the same language\n" +
                "4. Whether a given grammar is context-free",
                "1 and 2",
                "1 and 4",
                "2 and 3",
                "2 and 4",2,Question.DIFFICULTY_MEDIUM,Category.AUTOMATA);
        addQuestion(q120);
        Question q121 = new Question("If L and ~L are recursively enumerable then L is",
                "regular",
                "context-free",
                "context-sensitive",
                "recursive",4,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q121);
        Question q122 = new Question("Which of the following statements is false?",
                "Every NFA can be converted to an equivalent DFA",
                "Every non-deterministic Turing machine can be converted to an\n" +
                        "equivalent deterministic Turing machine",
                "Every regular language is also a context-free language",
                "Every subset of a recursively enumerable set is recursive",4,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q122);
        Question q123 = new Question("Which of the following statements are true ?\n" +
                "1. Every left-recursive grammar can be converted to a right-recursive\n" +
                "grammar and vice-versa\n" +
                "2. All ε-productions can be removed from any context-free grammar\n" +
                "by suitable transformations\n" +
                "3. The language generated by a context-free grammar all of whose\n" +
                "production are of the form X -> ω or X -> ωY (where, w is a\n" +
                "staring of terminals and Y is a non-terminal), is always regular\n" +
                "4. The derivation trees of strings generated by a context-free\n" +
                "grammar in Chomsky Normal Form are always binary trees.",
                "1, 2, 3 and 4",
                "2, 3 and 4 only",
                "1, 3 and 4 only",
                "1, 2 and 4 only",3,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q123);
        Question q124 = new Question("Match List-I with List-II and select the correct answer using the\n" +
                "codes given below the lists:\n" +
                "List-I\n" +
                "A. Checking that identifiers are declared before their use\n" +
                "B. Number of formal parameters in the declaration to a function\n" +
                "agress with the number of actual parameters in a use of that\n" +
                "function\n" +
                "C. Arithmetic expressions with matched pairs of parentheses\n" +
                "D. Palindromes\n" +
                "List-II\n" +
                "1. L = {a^n.b^n.c^n.d^n.| n ≤ 1,m ≤ 1}\n" +
                "2. X -> XbX | XcX | dXf | g\n" +
                "3. L = {wcw | w Є (a | b)*}\n" +
                "4. X -> bXb | cXc | ε\n" +
                "Codes:\n" +
                "A B C D",
                "1 3 2 4",
                "3 1 4 2",
                "3 1 2 4",
                "1 3 4 2",3,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q124);
        Question q125 = new Question("Which of the following are regular sets?\n" +
                "1. {a^n b^2m | n ≤ 0,m ≤ 0}\n" +
                "2. {a^n b^m | n = 2m}\n" +
                "3. {a^n b^m | n ≠ m}\n" +
                "4. {xcy | x,y Є {a,b}*}",
                "1 and 4 only",
                "1 and 3 only",
                "1 only",
                "4 only",1,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q125);
        Question q126 = new Question("S \" a S a b S b a b\n" +
                "The language generated by the above grammar over the alphabet\n" +
                "{a,b} is the set of",
                "all palindromes",
                "all odd length palindromes",
                "strings that begin and end with the same symbol",
                "all even length palindromes",2,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q126);
        Question q127 = new Question("Which one of the following languages over the alphabet {0, 1} is\n" +
                "described by the regular expression :\n" +
                "(0 + 1)*0(0 + 1)*0(0 + 1)* ?",
                "The set of all strings containing the substring 00",
                "The set of all strings containing at most two 0’s",
                "The set of all strings containing at least two 0’s",
                "The set of all strings that being and end with either 0 or 1",3,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q127);
        Question q128 = new Question("Which one of the following is FALSE ?",
                "There is a unique minimal DFA for every regular language",
                "Every NFA can be converted to an equivalent PDA",
                "Complement of every context-free language is recursive",
                "Every nondeterministic PDAcan be converted to an equivalent\n" +
                        "deterministic PDA",4,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q128);
        Question q129 = new Question("Match all items in Group I with correct options from those given in\n" +
                "Group 2\n\n"+"Group1\t\t\tGroup2\nP:Regular expression\t1.Syntax analysis\nQPushdown automata\t2. Code generation\nR. Data flow analysis\t3. Lexical analysis\nRegister allocation\t4. Code Optimization",
                "P-4, Q-1, R-2, S-3",
                "P-3, Q-1, R-4, S-2",
                "P-3, Q-4, R-1, S-2",
                "P-2, Q-1, R-4, S-3",2,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q129);
        Question q130 = new Question("Let L1 be a recursive language. Let L2 and L3 be language that\n" +
                "are recursively enumerable but not recursive. What of the following\n" +
                "statements is not necessarily true ?",
                "L1 − L1 is recursively enumerable",
                "L1 − L3 is recursively enumerable",
                "L2 Ո L3 is recursively enumerable",
                "L2 Ո L3 is recursively enumerable",2,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q130);
        Question q131 = new Question("Let L = {ω Є (0 + 1)*| ω ) has even number of 1s}, i.e., L is the set\n" +
                "of all bit strings with even number of 1s. Which one of the regular\n" +
                "expressions below represents L ?",
                "(0*10*1)*",
                "0*(10*10*)*",
                "0*(10*1)*0*",
                "0*1(10*1)*10*",2,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q131);
        Question q132 = new Question("Let ω by any string of length n in{0,1}). Let L be the set of all\n" +
                "substring so ω. What is the minimum number of states in a nondeterministic\n" +
                "finite automation that accepts L ?",
                "n-1",
                "n",
                "n+1",
                "2^n+1",3,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q132);
        Question q133 = new Question("The smallest finite automaton which accepts the language {x length\n" +
                "of x is divisible by 3} has",
                "2 states",
                "3 states",
                "4 states",
                "5 states",1,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q133);
        Question q134 = new Question("Which of the following is true ?",
                "The complement of a recursive language is recursive.",
                "The complement of a recursively enumerable language is recursively enumerable",
                "The complement of a recursive language is either recursive or\n" +
                        "recursively enumerable",
                "The complement of a context-free language is context-free",1,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q134);
        Question q135 = new Question("The C language is :",
                "A context free language",
                "A context sensitive language",
                "A regular language",
                "Parsable fully only by a Turing machine",1,Question.DIFFICULTY_EASY,Category.AUTOMATA);
        addQuestion(q135);
        Question qA1 = new Question(" A Square shaped wooden plate has a diagonal length of 30cm. Can you find the Perimeter of plate ?\n",
                "120 cm",
                "60 cm",
                "45 cm",
                "85 cm",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA1);
        Question qA2 = new Question(" Evaluate the expression: 2 + 5 * 6 - 7 * 8 / 4 + 6",
                "22",
                "21",
                "23",
                "24",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA2);
        Question qA3 = new Question("There were 12 students in a particular Class. The class teacher knows the overall height of all the students in the class is 1400. Can you find the average height of students in the class?",
                "110.84",
                "122.5",
                "116.67",
                "128.34",3,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA3);
        Question qA4 = new Question("Can you find the approximate value for the following expression:\n" +
                "\n" +
                "29.8% of 260 + 60.01% of 510 − 103.57 = ?",
                "450",
                "210",
                "280",
                "350",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA4);
        Question qA5 = new Question("A worker is kept on a contract for 100 days to make some toys. On any of these 100 days he does not make more than 20 toys. If on any day, he makes more than 12 toys, then he makes at most 6 toys each on the next two days. What is the maximum possible number of toys that he can make over the period of 100 days?",
                "1109",
                "1208",
                "1100",
                "1076",2,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA5);
        Question qA6 = new Question("From a list of four comics, four friends discuss their favourite comics. At least 2 friends vote for Mandrake, not more than 3 vote for Mammaji, 1 votes for Alibaba and 2 vote for Tom & Jerry. If two friends have exactly voted for 2 different comics each, and 2 friends for exactly 3 different comics each, then how many votes did Mandrake get?",
                "2",
                "3",
                "4",
                "cant say",3,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA6);
        Question qA7 = new Question("From a bag containing 242 balls, one ball weighs 19.9 grams and all the other weigh 19.5 grams each. Using a simple balance where balls can be kept on either pan, what is the minimum weighs required to identify the defective ball?",
                "3",
                "4",
                "5",
                "7",3,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA7);
        Question qA8 = new Question(" Read the information given below and answer the question that follows.\n" +
                "Five friends, viz. Shan, Monu, Jai, Karan and Bunty are living in five different cities named Karanpur, Jaipur, Vizanagar, Barnala and Patiala, not necessarily in that order.\n" +
                "Their salaries are 7 Lacs, 8 Lacs, 9 Lacs, 11 Lacs, 13 Lacs (INR per annum), in no particular order. Further, the following information is given about them:\n" +
                "I. Karan, who does not live in Barnala, earns a salary that is a prime number multiple of 1 Lac.\n" +
                "II. Monu made a call to one of his four mentioned friends who lives in Patiala and who earns a perfect square multiple of 1 Lac in salary.\n" +
                "III. Jai's salary is 1 Lac more than the average salary of Karan and Shan\n" +
                "IV. Monu lives in the city, which has the shortest name amongst the above cities.\n\nIf Karan lives in Vizanagar, then what is the average salary of the persons living in Barnala and Karanpur?",
                "9",
                "10",
                "12",
                "data insufficient",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA8);
        Question qA9 = new Question("Read the information given below and answer the question that follows.\n" +
                "Five friends, viz. Shan, Monu, Jai, Karan and Bunty are living in five different cities named Karanpur, Jaipur, Vizanagar, Barnala and Patiala, not necessarily in that order.\n" +
                "Their salaries are 7 Lacs, 8 Lacs, 9 Lacs, 11 Lacs, 13 Lacs (INR per annum), in no particular order. Further, the following information is given about them:\n" +
                "I. Karan, who does not live in Barnala, earns a salary that is a prime number multiple of 1 Lac.\n" +
                "II. Monu made a call to one of his four mentioned friends who lives in Patiala and who earns a perfect square multiple of 1 Lac in salary.\n" +
                "III. Jai's salary is 1 Lac more than the average salary of Karan and Shan\n" +
                "IV. Monu lives in the city, which has the shortest name amongst the above cities.\n\nWho stays in Patiala?",
                "shan",
                "monu",
                "bunty",
                "jai",3,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA9);
        Question qA10 = new Question("Read the information given below and answer the question that follows.\n" +
                "Five friends, viz. Shan, Monu, Jai, Karan and Bunty are living in five different cities named Karanpur, Jaipur, Vizanagar, Barnala and Patiala, not necessarily in that order.\n" +
                "Their salaries are 7 Lacs, 8 Lacs, 9 Lacs, 11 Lacs, 13 Lacs (INR per annum), in no particular order. Further, the following information is given about them:\n" +
                "I. Karan, who does not live in Barnala, earns a salary that is a prime number multiple of 1 Lac.\n" +
                "II. Monu made a call to one of his four mentioned friends who lives in Patiala and who earns a perfect square multiple of 1 Lac in salary.\n" +
                "III. Jai's salary is 1 Lac more than the average salary of Karan and Shan\n" +
                "IV. Monu lives in the city, which has the shortest name amongst the above cities.\n\nIf Monu and Jai live in cities with names starting with consecutive alphabets, then who lives in Vizanagar?",
                "shan",
                "monu",
                "bunty",
                "karan",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA10);
        Question qA11 = new Question("A square and a regular hexagon have the same area. Find the ratio of the perimeter of the square to the perimeter of the hexagon.",
                "√3 : 2",
                "1 : 2",
                "4√3 : 4√4",
                "4√4 : 4√3\n",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA11);
        Question qA12 = new Question("If x + y = 1, then what is the value of (x^3 + y^3 + 3xy)?",
                "1",
                "2",
                "9",
                "0",1,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA12);
        Question qA13 = new Question("Last Sunday, every customer who visited the CENTRA MALL was given a gift coupon, on every purchase worth Rs. 1000, with a unique six-digit code written on it. Each code was such that-\n" +
                "(i). The first digit was non-zero.\n" +
                "(ii). All the six digits were distinct.\n" +
                "(iii). The 1st and the 6th digits added up to 9 and so do the 2 nd and 5 th digits, and also the 3rd and 4th digits.\n" +
                "A gift was given to a customer who had two coupons with codes such that the numbers formed using the first three digits of each code were the reverse of each other.\n" +
                "The number of coupons distributed could not have been more than",
                "504",
                "729",
                "432",
                "648",3,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA13);
        Question qA14 = new Question(" If the radius of a circle is diminished by 10%, then its area is diminished by:",
                "10%",
                "19%",
                "20%",
                "36%",2,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA14);
        Question qA15 = new Question(" A boat travels 20 kms upstream in 6 hrs and 18 kms downstream in 4 hrs.Find\n" +
                "the speed of the boat in still water and the speed of the water current?\n",
                " 1/2 kmph",
                "7/12 kmph",
                " 5 kmph",
                "none of these",2,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA15);
        Question qA16 = new Question(" At what time after 4.00 p.m. is the minutes hand of a clock exactly aligned with\n" +
                "the hour hand?",
                " 4:21:49.5",
                " 4:27:49.5",
                "3:21:49.5",
                " 4:21:44.5",1,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA16);
        Question qA17 = new Question(" A shop keeper sold a T.V set for Rs.17,940 with a discount of 8% and earned a\n" +
                "profit of 19.6%.What would have been the percentage of profit earned if no\n" +
                "discount was offered?",
                "24.8%",
                "25%",
                "26.4%",
                "None of these",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA17);
        Question qA18 = new Question(" If (2x-y)=4 then (6x-3y)=?",
                "15",
                "12",
                "18",
                "10",2,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA18);
        Question qA19 = new Question(" A clock is set right at 8 a.m. The clock gains 10 minutes in 24 hours. What will be\n" +
                "the true time when the clock indicates 1 p.m. on the following day?\n",
                " 48 min. past 12\n",
                " 38 min. past 12",
                "28 min. past 12",
                "25 min. past 12",1,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA19);
        Question qA20 = new Question("What is the missing number in this series? 8 2 14 6 11 ? 14 6 18 12\n",
                "16",
                "9",
                "15",
                "6",2,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA20);
        Question qA21 = new Question(" Dinesh travelled 1200 km by air which formed 2/5 of his trip. One third of the\n" +
                "whole trip, he travelled by car and the rest of the journey he performed by train.\n" +
                "What was the distance travelled by train?",
                "600Km",
                " 700Km",
                " 800Km",
                " 900Km",3,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA21);
        Question qA22 = new Question(" A train which travels at a uniform speed due to some mechanical fault after\n" +
                "traveling for an hour goes at 3/5th of the original speed and reaches the destination\n" +
                "2 hrs late.If the fault had occurred after traveling another 50 miles the train would\n" +
                "have reached 40 min earlier. What is distance between the two stations.\n",
                "300",
                "310",
                "320",
                "305",1,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA22);
        Question qA23 = new Question(" The average between a two digit number and the number obtained by\n" +
                "interchanging the digits is 9. What is the difference between the two digits of the\n" +
                "number?",
                "8",
                "2",
                "5",
                "cannot be determined",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA23);
        Question qA24 = new Question(" Pipe A can fill in 20 minutes and Pipe B in 30 mins and Pipe C can empty the\n" +
                "same in 40 mins.If all of them work together, find the time taken to fill the tank",
                "17 1/7 mins",
                "20 mins",
                " 8 mins",
                " none of these",1,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA24);
        Question qA25 = new Question("A person has 4 coins each of different denomination. What is the number of\n" +
                "different sums of money the person can form (using one or more coins at a time)?",
                "16",
                "15",
                "12",
                "11",2,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA25);
        Question qA26 = new Question(" The simple interest on a certain sum of money for 3 years is 225 and the\n" +
                "compound interest on the same sum at the same rate for 2 years is 153 then the\n" +
                "principal invested is",
                "1500",
                "2250",
                "3000",
                "1875",4,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA26);
        Question qA27 = new Question("A cow is tethered in the middle of a field with a 14 feet long rope. If the cow\n" +
                "grazes 100 sq. ft. per day, then approximately what time will be taken by the cow to\n" +
                "graze the whole field ?",
                "2 days",
                "6 days",
                "18 days",
                "24 days",2,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA27);
        Question qA28 = new Question("2 hours after a freight train leaves Delhi a passenger train leaves the same\n" +
                "station travelling in the same direction at an average speed of 16 km/hr. After\n" +
                "travelling 4 hrs the passenger train overtakes the freight train. The average speed of\n" +
                "the freight train was?\n",
                "40",
                "30",
                "80",
                "60",1,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA28);
        Question qA29 = new Question(" The two colors seen at the extreme ends of the pH chart are:",
                "Red and Blue",
                "Red and Green",
                "Green and Blue\n",
                "Orange and Green",1,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA29);
        Question qA30 = new Question(" 8 15 24 35 48 63 _?",
                "70",
                "80",
                "75",
                "88",2,Question.DIFFICULTY_EASY,Category.AMPTITUDE);
        addQuestion(qA30);
        Question qA31 = new Question(" One of Mr. Horton, his wife, their son, and Mr. Horton’s mother is a doctor and\n" +
                "another is a lawyer.\n" +
                "a) If the doctor is younger than the lawyer, then the doctor and the lawyer are not blood\n" +
                "relatives.\n" +
                "b) If the doctor is a woman, then the doctor and the lawyer are blood relatives.\n" +
                "c) If the lawyer is a man, then the doctor is a man. Whose occupation you know?",
                " Mr. Horton: he is the doctor",
                " Mr. Horton’s son: she is the lawyer",
                " Mr. Horton: he is the doctor",
                " Mr. Horton’s mother: she is the doctor",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA31);
        Question qA32 = new Question(" Union Information and Broadcasting ministry recently gave an indication to\n" +
                "change which of the following laws on a larger scale, as the existing provisions of the\n" +
                "Act are inadequate to cater to the phenomenal growth of the print media in view of\n" +
                "the liberalization of the government policies?\n",
                "Press & Registration of Books Act, (PRB Act) 1867",
                "The Delivery Of Books ‘And Newspapers’ (Public Libraries) Act, 1954\n",
                "Indian Press (Emergency Powers ) Act 1931",
                "none",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA32);
        Question qA33 = new Question(" 2 numbers differ by 5.If their product is 336,then the sum of the 2 numbers is:",
                "21",
                "51",
                "28",
                "37",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA33);
        Question qA34 = new Question("Which number is the odd one out? 9678 4572 5261 3527 7768",
                "7768",
                "3527",
                "4527",
                "9678",2,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA34);
        Question qA35 = new Question(" If x=y=2z and xyz=256 then what is the value of x?",
                "8",
                "3",
                "5",
                "6",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA35);
        Question qA36 = new Question(" A radio when sold at a certain price gives a gain of 20%. What will be the gain\n" +
                "percent, if sold for thrice the price?\n",
                "280",
                "270",
                "290",
                "260",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA36);
        Question qA37 = new Question("If the value of x lies between 0 & 1 which of the following is the largest?",
                "x",
                "x^2",
                "-x",
                "1/x",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA37);
        Question qA38 = new Question(" x% of y is y% of ?",
                "x/y",
                "2y",
                "x",
                "cant be determined",3,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA38);
        Question qA39 = new Question("The tutor of Alexander the great was",
                "Darius",
                "cyrus",
                "socrates",
                "aristotle",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA39);
        Question qA40 = new Question("Thirty men take 20 days to complete a job working 9 hours a day. How many\n" +
                "hour a day should 40 men work to complete the job?",
                "8 hrs",
                "71/2 hrs",
                "7 hrs",
                "9 hrs",2,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA40);
        Question qA41 = new Question("Goitre caused by the deficiency of ………",
                "vitamin d",
                "iron",
                "vitamin a",
                "iodine",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA41);
        Question qA42 = new Question("Who invented Napier’s Bones",
                "John Napier",
                "William Oughtred",
                "Charles Babbage",
                "Napier Bone",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA42);
        Question qA43 = new Question("A and B can do a piece of work in 45 days and 40 days respectively. They began\n" +
                "to do the work together but A leaves after some days and then B completed the\n" +
                "remaining work n 23 days. The number of days after which A left the work was",
                "9",
                "11",
                "12",
                "15",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA43);
        Question qA44 = new Question("Sam and Mala have a conversation. Sam says I am certainly not over 40 Mala\n" +
                "Says I am 38 and you are at least 5 years older than me · Now Sam says you are at\n" +
                "least 39 all the statements by the two are false. How old are they really?",
                "Mala = 38 yrs, Sam =31 yrs.",
                "Mala = 38 yrs, Sam =41 yrs.",
                "Mala = 31 yrs, Sam =41 yrs.",
                "Mala = 45 yrs, Sam =41 yrs.",2,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA44);
        Question qA45 = new Question("What is the code name for Windows Vista?",
                " Longhorn",
                " Longhund",
                " Stackspray",
                " Pearl",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA45);
        Question qA46 = new Question(" On sports day, if 30 children were made to stand in a column, 16 columns could\n" +
                "be formed. If 24 children were made to stand in a column, how many columns could\n" +
                "be formed?",
                "20",
                "30",
                "40",
                "50",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA46);
        Question qA47 = new Question("The probability that a man will be alive for 25 years is 3/5 and the probability\n" +
                "that his wife will be alive for 25 years is 2/3. Find the probability that only the man\n" +
                "will be alive for 25 years.",
                "2/5",
                "1/5",
                "3/5",
                "4/5",2,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA47);
        Question qA48 = new Question(" In a single throw of a dice, what is the probability of getting a number greater\n" +
                "than 4?",
                "1/2",
                "2/3",
                "1/4",
                "1/3",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA48);
        Question qA49 = new Question(" If every alternative letter starting from B of the English alphabet is written in\n" +
                "small letter, rest all are written in capital letters, how the month “September” be\n" +
                "written. (1) SeptEMbEr (2) SEpTeMBEr (3) SeptembeR (4) SepteMber (5) None of\n" +
                "the above",
                "(1)",
                "(2)",
                "(3)",
                "(5)",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA49);
        Question qA50 = new Question(" After allowing a discount of 11.11% ,a trader still makes a gain of 14.28 % .at\n" +
                "how many precent above the cost price does he mark his goods?",
                "28.50%",
                "35%",
                "22.22%",
                "none of these",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA50);
        Question qA51 = new Question("There is a certain relation between two given words on one side of : : and one\n" +
                "word is given on another side of : : while another word is to be found from the given\n" +
                "alternatives, having the same relation with this word as the given pair has. Select the\n" +
                "best alternative. Horse : Jockey : : Car : ?\n",
                "Mechanic",
                "Chauffeur",
                "Steering",
                "Brake",2,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA51);
        Question qA52 = new Question(" Pipe A can fill in 20 minutes and Pipe B in 30 mins and Pipe C can empty the\n" +
                "same in 40 mins.If all of them work together, find the time taken to fill the tank",
                "17 1/7  mins",
                "20 mins",
                "none",
                "50 mins",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA52);
        Question qA53 = new Question(" There are 3 triplet brothers. They look identical. The oldest is John, he always\n" +
                "tells the truth. The second is Jack, he always tells a lie. The third is Joe, he either\n" +
                "tells the truth or a lie. Jimmie Dean went to visit them one day. He was wondering\n" +
                "who was who. So he asked each person a question. He asked the one who was sitting\n" +
                "on the left: “Who is the guy sitting in the middle?”. The answer was “He is John.”\n" +
                "He asked the one who was sitting in the middle: “What is your name?”. The answer\n" +
                "was “I am Joe.” He asked the one who was sitting on the right: “What is the guy\n" +
                "sitting in the middle?”. The answer was “He is Jack.” Jimmie Dean got really\n" +
                "confused. Basically, he asked 3 same questions, but he got 3 different answers. which\n" +
                "is not true?",
                "left most is joe",
                "middle is jack",
                "right id john",
                "middle is john",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA53);
        Question qA54 = new Question("A / B = C; C > D then",
                " A is always greater than D",
                " C is always greater than D",
                " B is always less than D",
                "none",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA54);
        Question qA55 = new Question("Consider the following statements: 1. The Administrative Reforms Commission\n" +
                "(ARC) had recommended that the Department of Personnel of a State should be put\n" +
                "under the charge of the Chief Secretary of the State. 2. Chief Secretary of a State is not involved in any manner in the promotion of State Civil officers to the All-India\n" +
                "Services. Which of the statements given above is/are correct?",
                "only 1",
                "only 2",
                "both 1 and 2",
                "neither 1 and 2",1,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA55);
        Question qA56 = new Question(" The population of a town was 1,60,000 three years ago. If it increased by 3%,\n" +
                "2.5% and 5% respectively in the last three years, then the present population of the\n" +
                "town is :",
                " 1,77,000",
                " 1,77,366",
                " 1,77,461",
                " 1,77,596",2,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA56);
        Question qA57 = new Question(" What is the population of India ?",
                "98 crores",
                "more than 2 billion",
                "121 crores",
                "less than 1 billion",3,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA57);
        Question qA58 = new Question(" What is the missing number in this series? 8 2 14 6 11 ? 14 6 18 12",
                "8",
                "6",
                "9",
                "11",3,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA58);
        Question qA59 = new Question(" Average age of students of an adult school is 40 years. 120 new students whose\n" +
                "average age is 32 years joined the school. As a result the average age is decreased by\n" +
                "4 years. Find the number of students of the school after joining of the new students:",
                "1200",
                "120",
                "360",
                "240",4,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA59);
        Question qA60 = new Question("On sports day,if 30 children were made to stand in a column,16 columns could\n" +
                "be formed. If 24 children were made to stand in a column , how many columns\n" +
                "could be formed?",
                "48",
                "20",
                "30",
                "16",2,Question.DIFFICULTY_MEDIUM,Category.AMPTITUDE);
        addQuestion(qA60);



        Question qA61 = new Question("Which of the following numbers is divisible by 3? (i) 541326 (ii) 5967013",
                "(ii) only",
                "(i) only",
                "(i) and (ii) both",
                "(i) and (ii) none",2,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA61);
        Question qA62 = new Question("A square is divided into 9 identical smaller squares. Six identical balls are to be\n" +
                "placed in these smaller squares such that each of the three rows gets at least one ball\n" +
                "(one ball in one square only). In how many different ways can this be done?\n",
                "81",
                "91",
                "41",
                "51",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA62);
        Question qA63 = new Question("A man owns 2/3 of the market research beauro business and sells 3/4 of his\n" +
                "shares for Rs.75000. What is the value of Business\n",
                "150000",
                "13000",
                "240000",
                "34000",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA63);
        Question qA64 = new Question("1,2,6,24,_?",
                "111",
                "151",
                "120",
                "125",3,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA64);
        Question qA65 = new Question(" The cost of 16 packets of salt,each weighing 900 grams is Rs.28.What will be the\n" +
                "cost of 27 packets ,if each packet weighs 1Kg?\n",
                " Rs.52.50",
                " Rs.56",
                " Rs.58.50",
                " Rs.64.75",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA65);
        Question qA66 = new Question("Ronald and Michelle have two children. The probability that the first child is a\n" +
                "girl, is 50%. The probability that the second child is a girl, is also 50%. Ronald and\n" +
                "Michelle tell you that they have a daughter. What is the probability that their other\n" +
                "child is also a girl?",
                "1/2",
                "1/3",
                "1/4",
                "1/5",2,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA66);
        Question qA67 = new Question(" Find the value of (21/4-1)( 23/4 +21/2+21/4+1)",
                "1",
                "2",
                "3",
                "4",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA67);
        Question qA68 = new Question("The product of two fractions is 14/15 and their quotient is 35/24. the greater\n" +
                "fraction is",
                "4/5",
                "7/6",
                "7/5",
                "7/4",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA68);
        Question qA69 = new Question(" 500 men are arranged in an array of 10 rows and 50 columns according to their\n" +
                "heights. Tallest among each row of all are asked to fall out. And the shortest among\n" +
                "them is A. Similarly after resuming that to their original podsitions that the shortest\n" +
                "among each column are asked to fall out. And the tallest among them is B . Now\n" +
                "who is taller among A and B ?",
                "A",
                "B",
                "both",
                "none",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA69);
        Question qA70= new Question("Choose the pair of numbers which comes next 75 65 85 55 45 85 35",
                "25 15",
                "25 85",
                "35 25",
                "25 75",2,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA70);
        Question qA71 = new Question("A three digit number consists of 9,5 and one more number. When these digits\n" +
                "are reversed and then subtracted from the original number the answer yielded will\n" +
                "be consisting of the same digits arranged yet in a different order. What is the other\n" +
                "digit?",
                "1",
                "2",
                "3",
                "4",4,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA71);
        Question qA72 = new Question("A piece of cloth cost Rs 35. if the length of the piece would have been 4m longer\n" +
                "and each meter cost Re 1 less , the cost would have remained unchanged. how long\n" +
                "is the piece?",
                "10",
                "11",
                "12",
                "none",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA72);
        Question qA73 = new Question("In a journey of 15 miles two third distance was travelled with 40 mph and\n" +
                "remaining with 60 mph.How muvh time the journey takes\n",
                "40 min",
                "30 min",
                "120 min",
                "20 min",4,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA73);
        Question qA74 = new Question("Solid cube of 6 * 6 * 6. This cube is cut into to 216 small cubes. (1 * 1 * 1).the big\n" +
                "cube is painted in all its faces. Then how many of cubes are painted at least 2 sides.",
                "56",
                "45",
                "23",
                "28",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA74);
        Question qA75= new Question("Find the average of first 40 natural numbers.",
                "40",
                "35",
                "30.6",
                "20.5",4,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA75);
        Question qA76 = new Question(": 1, 5, 14, 30, ?, 91",
                "45",
                "55",
                "60",
                "70",2,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA76);
        Question qA77 = new Question(" There is a shortage of tubelights, bulbs and fans in a village – Gurgaon. It is\n" +
                "found that\n" +
                "a) All houses do not have either tubelight or bulb or fan.\n" +
                "b) Exactly 19% of houses do not have just one of these.\n" +
                "c) Atleast 67% of houses do not have tubelights.\n" +
                "d) Atleast 83% of houses do not have bulbs.\n" +
                "e) Atleast 73% of houses do not have fans.",
                "42%",
                "46%",
                "50%",
                "54%",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA77);
        Question qA78 = new Question(" If 9 engines consume 24 metric tonnes of coal, when each is working 8 hours a\n" +
                "day; how much coal will be required for 8 engines, each running 13 hours a day, it\n" +
                "being given that 3 engines of the former type consume as much as 4 engines of latter\n" +
                "type.",
                "22 metric tonnes",
                "27 metric tonnes",
                "26 metric tonnes",
                "25 metric tonnes",3,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA78);
        Question qA79 = new Question("To 15 lts of water containing 20% alcohol, we add 5 lts of pure water. What is %\n" +
                "alcohol.",
                "20%",
                "34%",
                "15%",
                "14%",3,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA79);
        Question qA80 = new Question("A house wife saved Rs. 2.50 in buying an item on sale .If she spent Rs.25 for the\n" +
                "item ,approximately how much percent she saved in the transaction ?\n",
                "8%",
                "9%",
                "10%",
                "11%",2,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA80);
        Question qA81 = new Question(": Superheroes Liza and Tamar leave the same camp and run in opposite\n" +
                "directions. Liza runs 1 mile per second (mps) and Tamar runs 2 mps. How far apart\n" +
                "are they in miles after 1 hour?\n",
                "10800 mile",
                "19008 mile",
                "12300 mile",
                "14000 mile",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA81);
        Question qA82 = new Question("A = 5, B = 0, C = 2, D = 10, E = 2. What is then AB + EE – (ED)powerB +\n" +
                "(AC)powerE = ?",
                "113",
                "103",
                "93",
                "111",2,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA82);
        Question qA83 = new Question(" A man can row upstream at 8 kmph and downstream at 13 kmph.The speed of\n" +
                "the stream is?\n",
                "2.5 kmph",
                "4.2 kmph",
                "5 kmph",
                "10.5 kmph",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA83);
        Question qA84 = new Question(" Find what is the next letter. Please try to find. O,T,T,F,F,S,S,E,N,_ What is that\n" +
                "letter?",
                "B",
                "S",
                "Q",
                "T",4,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA84);
        Question qA85 = new Question(" There are 3 societies A, B, C. A lent cars to B and C as many as they had\n" +
                "Already. After some time B gave as many tractors to A and C as many as they have.\n" +
                "After sometime c did the same thing. At the end of this transaction each one of them\n" +
                "had 24. Find the cars each originally had.",
                "A had 21 cars, B had 39 cars & C had 12 cars",
                "A had 39 cars, B had 39 cars & C had 12 cars",
                "A had 39 cars, B had 21 cars & C had 19 cars\n",
                "A had 39 cars, B had 21 cars & C had 12 cars",4,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA85);
        Question qA86 = new Question("A papaya tree was planted 2 years ago. It increases at the rate of 20% every\n" +
                "year. If at present, the height of the tree is 540 cm, what was it when the tree was\n" +
                "planted?\n",
                "432 cm",
                "324 cm",
                "375 cm",
                "400 cm",3,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA86);
        Question qA87 = new Question("A is twice as good a workman as B and together they finish a piece of work in 18\n" +
                "days.In how many days will A alone finish the work",
                "27",
                "26",
                "25",
                "24",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA87);
        Question qA88 = new Question("The sum of 5 successive odd numbers is 1075. What is the largest of these\n" +
                "numbers?",
                "215",
                "223",
                "219",
                "217",3,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA88);
        Question qA89 = new Question(" 7 Pink, 5 Black, 11 Yellow balls are there. Minimum no. atleast to get one black\n" +
                "and yellow ball",
                "17",
                "13",
                "15",
                "19",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA89);
        Question qA90 = new Question(" There are 20 poles with a constant distance between each pole. A car takes 24\n" +
                "second to reach the 12th pole.How much will it take to reach the last pole.",
                "41.45 seconds",
                "40.45 seconds",
                "42.45 seconds",
                "41.00 seconds",1,Question.DIFFICULTY_HARD,Category.AMPTITUDE);
        addQuestion(qA90);

       Question qll1 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "chess",
                "badminton",
                "volleyball",
                "cricket",1,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll1);
        Question qll2 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "win",
                "complete",
                "victory",
                "succeed",2,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll2);
        Question qll3 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "jackal",
                "horse",
                "cat",
                "dog",2,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll3);
        Question qll4 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "YB",
                "AD",
                "IK",
                "MP",3,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll4);
        Question qll5 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "BCDF",
                "KJNL",
                "RSTU",
                "WXYZ",3,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll5);
        Question qll6 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "avoid",
                "abstruct",
                "block",
                "impede",1,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll6);
        Question qll7 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "Arvind Kejriwal",
                " Siddaramaiah",
                "Nabam Tuki",
                "Draupadi Murmu",4,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll7);
        Question qll8 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "Wheat",
                "corn",
                "cotton",
                "millet",3,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll8);
        Question qll9 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "Sparrow",
                "Parrot",
                "Koel",
                "Swan",4,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(qll9);
        Question ql10 = new Question("Four of the following five are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "Manure",
                "Nitrogen",
                "Ammonia",
                "Potash",2,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql10);
        Question ql11 = new Question("Statements:\n" +
                "I. The bank decided to give advances to the priority sector at the rate of interest at par with the corporate sector.\n" +
                "II. The percentage of bad loans given by the banks to the priority sector is very low compared to the corporate sector.",
                " I is the cause and II is its effect.",
                " Both I and II are independent causes.",
                "Both I and II are effects of independent causes.",
                "Both I and II are effects of some common causes.",4,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql11);
        Question ql12 = new Question("Statements:\n" +
                "I. Many seats in the private medical colleges in the state have remained vacant this year.\n" +
                "II. The government medical colleges in the state could accommodate all the students who sought admission this year.",
                " I is the cause and II is its effect.",
                " II is the cause and I is its effect.",
                "Both I and II are effects of independent causes.",
                "Both I and II are independent causes.",2,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql12);
        Question ql13 = new Question("Statements:\n" +
                "I. The Ganges river is flooded due to large sheets of ice coming down from the Himalayas.\n" +
                "II. Many people in the Himalayan ranges lost their houses under huge sheets of ice.",
                " I is the cause and II is its effect.",
                "Both I and II are effects of some common causes.",
                "Both I and II are independent causes.",
                "Both I and II are effects of independent causes.",2,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql13);
        Question ql14 = new Question(" In a class of 20 students, Shreya is 5 from the top and Annie is 7 ranks below Shreya. Find Annie’s rank from the bottom.",
                "3",
                "5",
                "6",
                "9",4,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql14);
        Question ql15 = new Question("Rita is sitting 5th from the left end of the row and Sita is 11th to the right of Rita with Tina being 4th to left of Sita. Madhuri is 8th to the right of Tina. What is the total number of students in the row if Madhuri is sitting at the extreme end?",
                "20",
                "28",
                "23",
                "12",1,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql15);
        Question ql16 = new Question("Karuna is sitting 25th from the left end and Preeti is sitting 26th from the right end. Preeti is at 20th to the left of Karuna. What is the total number of students sitting in the row?",
                "21",
                "30",
                "32",
                "28",2,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql16);
        Question ql17 = new Question("In a row of 30 children, A is 11th from the right end of row. If there are 4 children between A and B, What is the position of B from the left end of the row?",
                "5",
                "8",
                "9",
                "Cannot be determined",4,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql17);
        Question ql18 = new Question("Prerna is 5th from the left end and Charu is 4th from the right of the row. Charu interchanges her position with the one who is sitting 3rd to the right of Prerna and now Charu is 10th from the right end. How many children are there in the row?",
                "18",
                "17",
                "20",
                "16",2,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql18);
        Question ql19 = new Question("Judge: Court:: Doctor:?",
                "Hospital",
                "Muncipality",
                "Tehsil",
                "Factory",1,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql19);
        Question ql20 = new Question("Carbon dioxide: Extinguish:: Oxygen:?",
                "Burn",
                "Foam",
                "Explode",
                "Isolate",1,Question.DIFFICULTY_EASY,Category.LOGICAL);
        addQuestion(ql20);
        Question ql31 = new Question("Kitten: Cat:: Cub:?",
                "Tiger",
                "Girrafe",
                "Horse",
                "Dear",1,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql31);
        Question ql32 = new Question("Den: lion:: stable: ?",
                "Cat",
                "Horse",
                "Dog",
                "Cow",2,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql32);
        Question ql33 = new Question(" Thermometer: Temperature:: Barometer:?",
                "Stress",
                "Pressure",
                "Strain",
                "Force",2,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql33);
        Question ql34 = new Question(" In which year was Sugan born?\n" +
                "I. Sugan’s present age is 20 years more than his child\n" +
                "II. Sugans have two children. The first child was born in 1993?",
                "Only I",
                "Neither I or II",
                "Either I or II",
                "Only II",2,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql34);
        Question ql35 = new Question("How many students did secure First class in a class of 50 students?\n" +
                "I. Double the number of first-class students secured second class and all the remaining students failed\n" +
                "II. Number of students failed was twice the number of first class students in the class",
                "Both I and II",
                "Neither I and II",
                "Only I",
                "Only II",1,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql35);
        Question ql36 = new Question("Quartz: Radio::? : Cement",
                "Plaster of paris",
                "plastic",
                "leather",
                "gypsum",4,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql36);
        Question ql37 = new Question("Carbon dioxide: Extinguish:: Oxygen:?",
                "Isolate",
                "Foam",
                "Explode",
                "Burn",4,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql37);
        Question ql38 = new Question("Farming: Monsoons:: Market:?",
                "volume",
                "demand",
                "supply",
                "rate",2,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql38);
        Question ql39 = new Question(" Three of the following Four are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "Apple",
                "Orange",
                "Brinjal",
                "Grapes",3,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql39);
        Question ql40 = new Question("Three of the following Four are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "Badminton",
                "Table tennis",
                "rugby",
                "hockey",3,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql40);
        Question ql41 = new Question("Three of the following Four are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "mathura",
                "varanasi",
                "haridwar",
                "allahabad",1,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql41);
        Question ql42 = new Question("Three of the following Four are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "papaya",
                "water melon",
                "mango",
                "orange",3,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql42);
        Question ql43 = new Question("Three of the following Four are alike in a certain way and so form a group. Which is the one that does not belong to that group?",
                "italy",
                "greece",
                "poland",
                "europe",4,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql43);
        Question ql44 = new Question(" Statement: Should India install more nuclear reactors for electricity generation.\n" +
                "Arguments:\n" +
                "I. Yes, the government can earn more revenue from them.\n" +
                "II. No, this will increase the costs of the government.",
                " Only I is strong",
                "Only II is strong",
                "Both I & II are strong",
                "None of the above are valid",4,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql44);
        Question ql45 = new Question("Statement: India should increase the number of universities from 350 to 1500.\n" +
                "Arguments:\n" +
                "I. Yes, it is crucial to sustaining India’s growth because we have a large number of people seeking education.\n" +
                "II. No, the increase will cause a dilution in academic standards and more corruption.",
                "Only I is strong",
                "Only II is strong",
                "Both are equally valid",
                "None are valid",1,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql45);
        Question ql46 = new Question("Statement: Is clear focus the key to high achievements?" +
                "Arguments:\n" +
                "I. Yes, one with no goal or focus leads a barren existence.\n" +
                "II. No, behind every successful man is a woman.",
                "Only I is strong",
                "Only II is strong",
                "Both I & II are strong",
                "None of the above are valid",4,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql46);
        Question ql47 = new Question("A is fifteenth from the left end and B is eight from the right end. If there are 4 boys between them and B is to the right of A then the total number of a student sitting in the row.",
                "27",
                "28",
                "29",
                "26",1,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql47);
        Question ql48 = new Question("In a row of 30 children, Madhav is 12th from the left end. Raghu a friend of Madhav is 3 to the left of Madhav. Find the position of Raghu from the left end.",
                "10th",
                "5th",
                "8th",
                "9th",4,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql48);
        Question ql49 = new Question("Avinash is 5 ranks above Sunil in a class of 30. If Sunil rank is 15th from the last. What is Avinash rank from the start?",
                "10",
                "12",
                "13",
                "11",4,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql49);
        Question ql50 = new Question("P is fifteenth from the left end in a row of boys and Q is eighteenth from the right end. If R is tenth from P towards the right end and fourth from Q towards the right end. How many boys are there in the row?",
                "38",
                "40",
                "35",
                "36",1,Question.DIFFICULTY_HARD,Category.LOGICAL);
        addQuestion(ql50);
        Question ql61 = new Question("In a group of 6 students P, Q, R, S, T and U each one having different height. P is taller than T but not as tall as U. Q and U are not the tallest and also R is the shortest. Who is the tallest among them?",
                "S",
                "Q",
                "P",
                "U",1,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql61);
        Question ql62 = new Question("Look at this series: 2, 1, (1/2), (1/4), ... What number should come next?",
                "1/3",
                "1/8",
                "2/8",
                "1/16",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql62);
        Question ql63 = new Question("26, 12, 10, 16, ?",
                "50",
                "52",
                "53",
                "56",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql63);
        Question ql64 = new Question("Look carefully for the pattern, and then choose which pair of numbers comes next.\n" +
                "\n" +
                "2 44 4 41 6 38 8",
                "10 12",
                "35 32",
                "34 9",
                "35 10",2,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql64);
        Question ql65 = new Question("In the following question, a number series is given with one term missing. Choose the correct alternative that will same pattern and fill in the blank spaces.: 1, 4, 9, 16, 25, x",
                "35",
                "36",
                "48",
                "49",1,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql65);
        Question ql66 = new Question("4, 7, 12, .., 28, 39",
                "19",
                "24",
                "14",
                "16",1,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql66);
        Question ql67 = new Question("32 31 32 29 32 27 32",
                "25 32",
                "31 32",
                "29 32",
                "25 30",1,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql67);
        Question ql68 = new Question("3 2 2 5 5 5 7 8 8 9 11 11 ?",
                "12",
                "11",
                "9",
                "14",2,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql68);
        Question ql69 = new Question("What is the next number in this sequence?\n" +
                "\n" +
                "1, 3, 8, 19, 42, 89, ..?..",
                "108",
                "184",
                "167",
                "97",2,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql69);
        Question ql70 = new Question("1, 9, 25, 49, ?, 121. What will come at the place of question mark?",
                "100",
                "91",
                "64",
                "81",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql70);
        Question ql71 = new Question("30, 34, 43, 59, 84, 120,?",
                "169",
                "148",
                "153",
                "176",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql71);
        Question ql72 = new Question("Here are some words translated from an artificial language.\n" +
                "\n" +
                "krekinblaf means workforce\n" +
                "dritakrekin means groundwork\n" +
                "krekinalti means workplace\n" +
                "\n" +
                "Which word could mean \"someplace\"?",
                "moropalti",
                "krekindrita",
                "altiblaf",
                "dritaalti",1,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql72);
        Question ql73 = new Question("Here are some words translated from an artificial language.\n" +
                "\n" +
                "plekapaki means fruitcake\n" +
                "pakishillen means cakewalk\n" +
                "treftalan means buttercup\n" +
                "\n" +
                "Which word could mean “cupcake”?",
                "shillenalan",
                "treftpleka",
                "pakitreft",
                "alanpaki",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql73);
        Question ql74 = new Question("Here are some words translated from an artificial language.\n" +
                "\n" +
                "moolokarn means blue sky\n" +
                "wilkospadi means bicycle race\n" +
                "moolowilko means blue bicycle\n" +
                "\n" +
                "Which word could mean \"racecar\"?",
                "wilkozwet",
                "spadiwildo",
                "moolobrell",
                "spadivolo",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql74);
        Question ql75 = new Question("Here are some words translated from an artificial language.\n" +
                "\n" +
                "migenlasan means cupboard\n" +
                "lasanpoen means boardwalk\n" +
                "cuopdansa means pullman\n" +
                "\n" +
                "Which word could mean \"walkway\"?",
                "poenmigen",
                "Iasandansa",
                "cuopeisel",
                "poenforc",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql75);
        Question ql76 = new Question("Here are some words translated from an artificial language.\n" +
                "\n" +
                "aptaose means first base\n" +
                "eptaose means second base\n" +
                "\n" +
                "lartabuk means ballpark Which word could mean \"baseball\"?",
                "buklarta",
                "bukose",
                "oseepta",
                "oselarta",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql76);
        Question ql77 = new Question("One New York publisher has estimated that 50,000 to 60,000 people in the United States want an anthology that includes the complete works of William Shakespeare. And what accounts for this renewed interest in Shakespeare? As scholars point out, his psychological insights into both male and female characters are amazing even today. This paragraph best supports the statement that",
                "Shakespeare's characters are more interesting than fictional characters today.",
                "people even today are interested in Shakespeare's work because of the characters.",
                "academic scholars are putting together an anthology of Shakespeare's work.",
                "New Yorkers have a renewed interested in the work of Shakespeare.",2,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql77);
        Question ql78 = new Question("Generation Xers are those people born roughly between 1965 and 1981. As employees, Generation Xers tend to be more challenged when they can carry out tasks independently. This makes Generation Xers the most entrepreneurial generation in history. This paragraph best supports the statement that Generation Xers",
                "work harder than people from other generations.",
                "have a tendency to be self-directed workers",
                "have an interest in making history",
                "tend to work in jobs that require risk-taking behavior.",2,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql78);
        Question ql79 = new Question("The criminal justice system needs to change. The system could be more just if it allowed victims the opportunity to confront the person who has harmed them. Also,\n" +
                "mediation between victims and their offenders would give the offenders a chance to apologize for the harm they have done. This paragraph best supports the statement\n" +
                "that victims of a crime should",
                "learn to forgive their offenders",
                "have the right to confront their offenders",
                "learn the art of mediation",
                "insist that their offenders be punished",2,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql79);
        Question ql80 = new Question("Should girls play boys'sports?",
                " No, Girls have their rights and can choose if they want to play sports with boys or not.",
                "No, Males are stronger and more aggressive in general, meaning females are more likely to be injured when playing on all-male teams.",
                " Yes, Safety is first for girls they can get hurt.",
                "Yes, Sports were created for women and men, girls and boys. Women may be a little more delicate then men, but if they want to play a sport, then they have to be prepared. ",4,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql80);
        Question ql81 = new Question("One of the warmest winters on record has put consumers in the mood to spend money. Spending is likely to be the strongest in thirteen years. During the month of February, sales of existing single-family homes hit an annual record rate of 4.75 million. This paragraph best supports the statement that",
                "warm winter weather is likely to affect the rate of home sales",
                "consumer spending will be higher thirteen years from now than it is today",
                " more people buy houses in the month of February than in any other month",
                "during the winter months, the prices of single-family homes are the lowest ",1,Question.DIFFICULTY_MEDIUM,Category.LOGICAL);
        addQuestion(ql81);

        Question v1 = new Question("Choose the synonym of foment",
                "Interrogate",
                "spoil",
                "spray",
                "incite",4,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v1);
        Question v2 = new Question("Choose the synonym of predicament",
                "truthful",
                "easy",
                "Quandary",
                "denial",3,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v2);
        Question v3 = new Question("Choose the synonym of rupture",
                "shatter",
                "reptile",
                "tunnel",
                "vein",1,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v3);
        Question v4 = new Question("Choose the synonym of bustle",
                "body",
                "break",
                "return",
                "hurry",4,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v4);

        Question v5 = new Question("Choose the synonym of hesitated",
                "stopped",
                "paused",
                "slowed",
                "postponed",2,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v5);

        Question v6 = new Question("Choose the synonym of manifest",
                "easily percieved",
                "easily acquired",
                "easily infected",
                "easily deflected",1,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v6);

        Question v7 = new Question("Choose the synonym of obstreperous",
                "sullen",
                "unruly",
                "lazy",
                "awkward",2,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v7);

        Question v8 = new Question("Choose the antonym of quiscent",
                "active",
                "dormant",
                "weak",
                "unconcerned",1,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v8);

        Question v9 = new Question("Choose the antonym of fraudulent",
                "candid",
                "direct",
                "forthright",
                "genuine",4,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v9);

        Question v10 = new Question("Choose the antonym of belittle",
                "criticize",
                "flatter",
                "exaggerate",
                "adore",3,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v10);

        Question v11 = new Question("Choose the antonym of epilogue",
                "dialogue",
                "predule",
                "postscript",
                "epigram",2,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v11);

        Question v12 = new Question("Choose the antonym of feasibility",
                "unsuitability",
                "cheapness",
                "impropriety",
                "impracticability",4,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v12);

        Question v13 = new Question("Research suggests there will be an annual rise in___________until 2042, before a decline sets in.",
                "fatalities",
                "vital",
                "healthful",
                "whole",1,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v13);

        Question v14 = new Question("We become so anaesthetized that it almost seems normal, and we don’t even question the choices we make or the values we are___________.",
                "facing",
                "promoting",
                "permitting",
                "pretending",2,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v14);

        Question v15 = new Question("It will subsume multiple indirect taxes, including State-level sales tax, octroi and other levies, that make doing business in India a_________as well as a logistical nightmare.",
                "dissent",
                "rigid",
                "veto",
                "compliance",4,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v15);

        Question v16 = new Question("Over time, the number of antibiotics to which bacteria are ______________ has been dwindling, and some pathogens have become resistant to most or all existing drugs.",
                "defiant",
                "susceptible",
                "rebellious",
                "contrary",2,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v16);

        Question v17 = new Question("This will arguably be one of the most important indicators for both China and the rest of the global economy for______________to come.",
                "days",
                "times",
                "events",
                "years",4,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v17);

        Question v18 = new Question("India’s job market is_____________overcrowded with school-leavers and even graduates and post-graduates who remain unemployed because they lack practical skills.",
                "desparetely",
                "content",
                "recreant",
                "reprehensible",1,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v18);

        Question v19 = new Question("To be successful, business models need to localise, adapt, and frequently re-invent themselves to create a_____________Indian product offering.",
                "bulky",
                "rigidly",
                "uniquely",
                "ponderous",3,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v19);

        Question v20 = new Question("Survival of mankind itself is in danger due to__________of atomic weapons.",
                "indictment",
                "proliferation",
                "encouragement",
                "preparation",2,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v20);

        Question v21 = new Question("spot errors in : When exposed, it revealed an ecosystem / of Vyapam officials in collision / with racketeers and middlemen who had/rigged the central examination systems thoroughly.",
                " When exposed, it revealed an ecosystem",
                "of Vyapam officials in a collision",
                "with racketeers and middlemen who had",
                "rigged the central examination systems thoroughly.",4,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v21);

        Question v22 = new Question("spot errors in : When politicians compare racism or corruption or / certain religions to cancer, they’re certainly / suggesting some violent treatment – wrench it out, / irradiate it, destroy everything in the vicinity.",
                "When politicians compare racism or corruption or",
                "certain religions to cancer, they’re certainly",
                "suggesting some violent treatment – wrench it out,",
                "irradiate it, destroy everything in the vicinity.",2,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v22);

        Question v23 = new Question("spot errors in : It is not, according to many whom cover the / White House, a work of journalistic merit, / or a rigorously factual account / backed by cataloged evidence.",
                "It is not, according to many whom cover the",
                "White House, a work of journalistic merit,",
                "or a rigorously factual account",
                "backed by cataloged evidence.",1,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v23);

        Question v24 = new Question("which of the following is wrongly spelt?",
                "Quandary",
                "froliccsome",
                "condolence",
                "acclaim",2,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v24);

        Question v25 = new Question("Which of the following four words is wrongly spelt?",
                "epigram",
                "berate",
                "dilative",
                "buccolic",4,Question.DIFFICULTY_MEDIUM,Category.VERBAL);
        addQuestion(v25);

        Question v26 = new Question("In the questions below the sentences have been given in Direct/Indirect speech. From the given alternatives, choose the one which best expresses the given sentence in Indirect/Direct speech.\n" +
                "If you don't keep quite I shall shoot you, he said to her in a calm voice.",
                "He warned her to shoot if she didn't keep quite calmly.",
                "He said calmly that I shall shoot you if you don't be quite.",
                "He warned her calmly that he would shoot her if she didn't keep quite.",
                "Calmly he warned her that be quite or else he will have to shoot her",3,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(v26);
        Question v27 = new Question("A. chandragupta:mouryan B. bardar:mugal C. krisha:kushan D. mahavira:jainis",
                "A",
                "B",
                "C",
                "D",4,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(v27);
        Question v28 = new Question(" A. Ammeter:current B. hygrometer:presure C. odometer:speed D. seismograph:earthquakes",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(v28);
        Question v29 = new Question("A. solder:tin B.haematite:iran C. bauxite:aluminium D.malachite:copper",
                "A",
                "B",
                "C",
                "D",1,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(v29);
        Question v30 = new Question("A. whale:mammal B. salamander:insect C. snake:reptile D. frog:pmphibiam",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(v30);

        Question ve1 = new Question("Find the odd man out of the group in the following questions: a) iran:asia b) candera:Australia c) norway:europe d) algeria:aferica\n",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve1);
        Question ve2 = new Question("A.scapel: surgeon B. chisel:solder C.awl:cobbler D. knife:chef",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve2);
        Question ve3 = new Question("A. mulder:proteins B.curie:redium C. becquerel:radioactivity D.einstein:television",
                "A",
                "B",
                "C",
                "D",4,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve3);
        Question ve4 = new Question("A. sheep:bleat B. horse:neigh C.ass:grunt D.owl:hoot",
                "A",
                "B",
                "C",
                "D",3,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve4);
        Question ve5 = new Question(" A. door:bang B. piano:play C.rain:ptler D.drum:be",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve5);
        Question ve6 = new Question("In the questions below the sentences have been given in Direct/Indirect speech. From the given alternatives, choose the one which best expresses the given sentence in Indirect/Direct speech.\n" +
                "If you don't keep quite I shall shoot you, he said to her in a calm voice.",
                "He warned her to shoot if she didn't keep quite calmly.",
                "He said calmly that I shall shoot you if you don't be quite.",
                "He warned her calmly that he would shoot her if she didn't keep quite.",
                "Calmly he warned her that be quite or else he will have to shoot her",3,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve6);
        Question ve7 = new Question("A. chandragupta:mouryan B. bardar:mugal C. krisha:kushan D. mahavira:jainis",
                "A",
                "B",
                "C",
                "D",4,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve7);
        Question ve8 = new Question(" A. Ammeter:current B. hygrometer:presure C. odometer:speed D. seismograph:earthquakes",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve8);
        Question ve9 = new Question("A. solder:tin B.haematite:iran C. bauxite:aluminium D.malachite:copper",
                "A",
                "B",
                "C",
                "D",1,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve9);
        Question ve10 = new Question("A. whale:mammal B. salamander:insect C. snake:reptile D. frog:pmphibiam",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve10);
        Question ve11 = new Question("A. profit:loss B. wise:foolish C. virtue:vice D. seduce:attract",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve11);
        Question ve12 = new Question(" A. iran:asia B. candera:Australia C.norway:europe D.algeria:aferica",
                "A",
                "B",
                "C",
                "D",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve12);
        Question ve13 = new Question("Find the Synonym of Juncture",
                "crushing",
                "critical point",
                "intricate",
                "praise",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve13);
        Question ve14 = new Question("Find the antonyms of ANTITHESIS",
                "similarity",
                "dislike",
                "regularity",
                "none of these",1,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve14);
        Question ve15 = new Question("Find the antonyms of BLEAK",
                "cheerful",
                "pleasure",
                "bantering",
                "speed",1,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve15);
        Question ve16 = new Question("Find the antonyms of CELERITY",
                "blame",
                "delay",
                "bantering",
                "speed",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve16);
        Question ve17 = new Question(" Find the antonyms of DANK",
                "neat",
                "damp",
                "dry",
                "none of these",3,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve17);
        Question ve18 = new Question("Find the antonyms of EULOGISTIC",
                "windy",
                "uncomlimentary",
                "greedy",
                "none of these",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve18);
        Question ve19 = new Question(" Find the antonyms of HAGGARD",
                "random",
                "overused",
                "rested",
                "none of these",3,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve19);
        Question ve20 = new Question("Find the antonyms of IGNOBLE",
                "noble",
                "tedious",
                "unrelated",
                "none of these",1,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve20);
        Question ve21 = new Question("Find the antonyms of Jeopardy",
                "safety",
                "serious",
                "salvage",
                "sedate",1,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve21);
        Question ve22 = new Question("In the questions below the sentences have been given in Active/Passive voice. From the given alternatives, choose the one which best expresses the given sentence in Passive/Active voice.\n" +
                "After driving professor Kumar to the museum she dropped him at his hotel.",
                "After being driven to the museum, Professor Kumar was dropped at his hotel.",
                "Professor Kumar was being driven dropped at his hotel.",
                "After she had driven Professor Kumar to the museum she had dropped him at his hotel.",
                "After she was driven Professor Kumar to the museum she had dropped him at his hotel.",1,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve22);
        Question ve23 = new Question("I remember my sister taking me to the museum.",
                "I remember I was taken to the museum by my sister.",
                "I remember being taken to the museum by my sister.",
                "I remember being taken to the museum by my sister.",
                "I remember taken to the museum by my sister.",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve23);
        Question ve24 = new Question(" Who is creating this mess ?",
                "Who has been created this mess? ",
                "By whom has this mess been created? ",
                "By whom this mess is being created?",
                "By whom is this mess being created?",4,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve24);
        Question ve25 = new Question("They greet me cheerfully every morning.",
                "Every morning I was greeted cheerfully.",
                "I am greeted cheerfully by them every morning.",
                ".I am being greeted cheerfully by them every morning. ",
                "Cheerful greeting is done by them every morning to me.",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve25);
        Question ve26 = new Question("Darjeeling grows tea.",
                "Tea is being grown in Darjeeling. ",
                "Let the tea be grown in Darjeeling. ",
                "Tea is grown in Darjeeling ",
                "Tea grows in Darjeeling",3,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve26);
        Question ve27 = new Question(" They have built a perfect dam across the river.\n",
                "Across the river a perfect dam was built.",
                "A perfect dam has been built by them across the river. ",
                "A perfect dam should have been built by them. ",
                "Across the river was a perfect dam",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve27);
        Question ve28 = new Question("Do you imitate others?",
                "Are others being imitated by you? ",
                "re others imitated by you?",
                "Have others being imitated by you? ",
                "Were others being imitated by you?",2,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve28);
        Question ve29 = new Question("You need to clean your shoes properly.",
                "Your shoes are needed to clean properly.",
                "You are needed to clean your shoes properly. ",
                "Your shoes need to be cleaned properly.",
                "Your shoes are needed by you to clean properly",3,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve29);
        Question ve30 = new Question(" I told him that he was not working hard.",
                "I said to him, \"You are not working hard.\"",
                "I told to him, \"You are not working hard.\"",
                "I said, \"You are not working hard.\" ",
                "I said to him, \"He is not working hard.\"",1,Question.DIFFICULTY_EASY,Category.VERBAL);
        addQuestion(ve30);


        Question vh1 = new Question("Spot errors in sentences",
                "A man should work hard",
                "A man should not waste his time",
                "A boy should not waste his time",
                "No error.",4,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh1);
        Question vh2 = new Question("Spot errors in sentences",
                "The scenery here is not good",
                "I have lost my furnitures",
                "We have received no information",
                "He told his mother this news",2,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh2);
        Question vh3 = new Question("Spot errors in sentences",
                "The boy who does best he will get a prize",
                "Whoever does best will get a prize",
                "Who did this ? I",
                "He and I are brothers",1,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh3);
        Question vh4 = new Question("Spot errors in sentences",
                "Please excuse the trouble",
                "He took pains over his work",
                "In India there are many poor",
                "Scouts wear shorts",1,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh4);
        Question vh5 = new Question("Spot errors in sentences",
                "I went for a walk with some friends",
                "He is wiser than me",
                "The master tested the boy if he could read English",
                "I shall see whether the brakes work well",3,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh5);
        Question vh6 = new Question("Spot errors in sentences",
                "I spent the holidays with my family",
                "Write this in your note book",
                "Gone my kind regards to all",
                "No error",4,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh6);
        Question vh7 = new Question("Spot errors in sentences",
                "I have no any friends",
                "Neither man has come",
                "All idle man should do some work or other",
                "Shakespeare is greater than any other poet",1,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh7);
        Question vh8 = new Question("Spot errors in sentences",
                "Have you got a pencil ? I have not got one",
                "He enjoyed during the holidays",
                "I asked for my pencil but he did not give it to me",
                "No error",2,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh8);
        Question vh9 = new Question("Spot errors in sentences",
                "The boy was wearing a new suit",
                "He took objection at this",
                "Please put your sign here",
                "Please put your signature here",3,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh9);
        Question vh10 = new Question("Spot errors in sentences",
                "Everyone knows this",
                "Every man knows this",
                "These all mangoes are ripe",
                "He hold the book in both hands",3,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh10);
        Question vh11 = new Question(
                "The highest reward for a man's toil is not what he gets for it but what________",
                "he makes out of it",
                "he gets for others",
                "he has overcome through it",
                "he becomes by it",4,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh11);
        Question vh12 = new Question("A country's wealth is its people.But instead of drawing out the strengths of the people, instead of drawing out their talent, this use of religion debases, degrades and depresses than ________.",
                "in greater and greater measure",
                "further",
                "beyond reasonable limit",
                "more and more",1,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh12);
        Question vh13 = new Question("\n" +
                "Scientists have found________evidence that Neanderthals, now an extinct species of humans________Europe , were cannibals.",
                "grisly, living in",
                "incontrovertible, A cross",
                "chilling , inhibiting",
                "proper, in",3,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh13);
        Question vh14 = new Question("  \n" +
                "________ the more they remain the same.",
                "The less the dynamism",
                "The more things change",
                "The more pronounced the transformation",
                "The more the merrier",2,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh14);
        Question vh15 = new Question(
                "The trail is the thing , not the end of the trail. Travel too fast and you miss",
                "all you are travelling for.",
                "all the sights you are supposed to see.",
                "the very excitement of your travel",
                "all the enjoyments of travel",3,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh15);
        Question vh16 = new Question("\n" +
                "Overall, all the recent policy changes by the government only amount to a________ in the sugar industry.",
                "superficial attempt at liberalisation",
                "drop in the ocean",
                "large change",
                "small regulating authority",1,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh16);
        Question vh17 = new Question("For some of our ruling politicians, social justice has apparently come to mean that ________.",
                "they have a right to plunder public money from the treasury ",
                "they have a right to the plunder of public money from the treasury ",

                        "theirs is the right to plunder public money from the treasury ",
                "the right is theirs to plunder public money from the treasury ",1,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh17);
        Question vh18 = new Question(" In pusuance of their decision to resist what they saw as anti-labour policies, the company employees, union launched agitation to________.",
                "show their virility",
                "reaffirm their commitment to the company",
                "bring down the government",
                "demonstrate their strength",4,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh18);
        Question vh19 = new Question("\n" +
                "I am an entertainer ____________ .I have to keep smiling because, deep in my heart, laughter and sorrow have an affinity",
                "Even if I have tears in me",
                "Despite conditions of extreme adversity",

                        "Inspite of misery around me",
                "Although I have yet to make it big",2,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh19);
        Question vh20 = new Question(
                "________that in this apparent mess , two things not be interfered with.",

                        "It is important",
                "it is of cardinal important",
                "It should be urgently understood",
                "It cannot be emphasised",1,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh20);
        Question vh21 = new Question("Antonym of: Unimpeachable",
                "fruitful",
                "rampaging",
                "faulty",
                "pensive",3,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh21);
        Question vh22 = new Question("Antonym of: Vacillation",
                "remose",
                "releif",
                "respect",
                "steadfastness",4,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh22);
        Question vh23 = new Question("Antonym of:untenable",
                "supportable",
                "tender",
                "sheepish",
                "tremulous",1,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh23);
        Question vh24 = new Question("Antonym of: Unseemly",
                "effortless",
                "proper",
                "conductive",
                "pointed",2,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh24);
        Question vh25 = new Question("Antonym of: Unkempt",
                "bombed",
                "washed",
                "neat",
                "tawdry",4,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh25);
        Question vh26 = new Question("synonym of :reprove",
                "prevail",
                "rebuke",
                "ascertain",
                "prove false",2,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh26);
        Question vh27 = new Question("synonym of :Tentative",
                "prevalent",
                "portable",
                "wry",
                "provisional",4,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh27);
        Question vh28 = new Question("synonym of : Tepid",
                "boiling",
                "luckerworm",
                "freezing",
                "gaseous",2,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh28);
        Question vh29 = new Question("synonym of : Tautology",
                "memory",
                "repetition",
                "tension",
                "amile",2,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh29);
        Question vh30 = new Question("synonym of : Temaporal",
                "preistly",
                "scholarly",
                "secular",
                "sleepy",3,Question.DIFFICULTY_HARD,Category.VERBAL);
        addQuestion(vh30);

        Question de1 = new Question("Given relations r(w,x) and s(y,z), the result of select distinct w,x\n" +
                "from r,s :\n" +
                "is guaranteed to be same as r, provided :",
                "r has no duplicates and s is non empty",
                "r and s have no duplicates",
                "s has no duplicates and r is non empty",
                "r and s have the same number of tuples",3,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de1);
        Question de2 = new Question("Consider a schema R(A,B,C,D) and functional dependencies A \" B\n" +
                "and C \" D. Then the decomposition of R intoR1(AB) and R2]CDg is\n" +
                ":",
                "Dependency preserving and lossless join",
                "Lossless join but not dependency preserving",
                "Dependency preserving but not lossless join",
                "Not dependency preserving and not lossless join",3,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de2);
        Question de3 = new Question("Suppose the adjacency relation of vertices in a graph is represented\n" +
                "in a table Adj (X,Y). Which of the following queries cannot be\n" +
                "expressed by a relational algebra expression of constant length ?",
                "List all vertices adjacent to a given vertex.",
                "List all vertices which have self loops",
                "List all vertices which belong to cycles of less than three vertices",
                "List all vertices reachable from a given vertex",3,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de3);
        Question de4 = new Question("Let r and s be two relations over the relation schemes R and S\n" +
                "respectively, and let A be an attribute in R. Then the relational\n" +
                "algebra expression σA=a(r ⋈ sg)is always equal to :",
                "σA = a(r)",
                "r",
                "σA=a(r ⋈ s)",
                "none of these",3,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de4);
        Question de5 = new Question("R,(A,B,C,D) is a relation. Which of the following does not have a lossless join, dependency preserving BCNF decomposition ?",
                "A -> B,B ->CD",
                "A -> B,B -> C,C -> D",
                "AB -> C,C -> AD",
                "A -> BCD",4,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de5);
        Question de6 = new Question("Consider a relation geq which represents “greater than or equal to”,\n" +
                "that is, (x,y) ! geq only if y <= x:\n" +
                "Create table gaq\n" +
                "( Ib integer not null\n" +
                "ub integer not null\n" +
                "primary key Ib\n" +
                "foreign key (ub) references geq on delete cascade):" +
                "Which of the following is possible if a tuple (x,y) is deleted ?",
                "A tuple (z,w) with z > y is deleted",
                "A tuple (z,w) with z > x is deleted",
                "A tuple (z,w) with w < x is deleted",
                "The deletion of (x,y) is prohibited",3,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de6);
        Question de7 = new Question("Relation R with an associated set of functional dependencies, F , is\n" +
                "decomposed into BCNF. The redundancy (arising out of functional\n" +
                "dependencies) in the resulting set of relations is.",
                "Zero",
                "More than zero but less than that of an equivalent 3NF\n" +
                        "decomposition",
                "Proportional to the size of F+",
                "Indetermine.",1,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de7);
        Question de8 = new Question("With regard to the expressive power of the formal relational query\n" +
                "languages, which of the following statements is true ?",
                "Relational algebra is more powerful than relational calculus.",
                "Relational algebra has the same power as relational calculus.",
                "Relational algebra has the same power as safe relational calculus.",
                "None of the above.",3,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de8);
        Question de9 = new Question("Relation R is decomposed using a set of functional dependencies,\n" +
                "F ,and relation S is decomposed using another set of functional\n" +
                "dependencies, G. One decomposition is definitely BCNF, the other\n" +
                "is definitely. 3NF , but it is not known which is which. To make a\n" +
                "guaranteed identification, which one of the following tests should be\n" +
                "used on the decompositions ? (Assume that the closures of F and G\n" +
                "are available).",
                "Dependency-preservation",
                "Lossless-join",
                "BCNF definition",
                "3NF definition",3,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de9);
        Question de10 = new Question("Which of the following scenarios may lead to an irrecoverable error\n" +
                "in a database system?",
                "A transaction writes a data item after it is read by an uncommitted\n" +
                        "transaction",
                "A transaction read a data item after it is read by an uncommitted\n" +
                        "transaction",
                "A transaction read a data item after it is written by an committed\n" +
                        "transaction",
                "A transaction read a data item after it is written by an\n" +
                        "uncommitted transaction",4,Question.DIFFICULTY_EASY,Category.DBMS);
        addQuestion(de10);

        Question dm1 = new Question("Consider the following functional dependencise in a database:\n" +
                "Data_of_Birth->Age Age->Eligibility\n" +
                "Name->Roll_number Roll_number->Name\n" +
                "Course_number->Course_name Course_number->Instructor\n" +
                "(Roll_number,Course_number)->Grade" +
                "The relation(Roll)number,Name,Date_of_brith,Age)is",
                "in second normal normal form but not in third normal form",
                "in third normal form but not in BCNF",
                "in BCNF",
                "in none of the above",4,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm1);
        Question dm2 = new Question("Consider the set of relations shown below and the SQL query that\n" +
                "follow:\n" +
                "Students:(Roll_number,Name,Date_of_birth)\n" +
                "Courses:(Course_number,Course_name,Instructor)\n" +
                "Grades:(Roll_number,Course_number,Grade)\n" +
                "select distrinct Name\n" +
                "from Students, Courses, Grades\n" +
                "Where Students,Roll_number=Grades. Toll_number\n" +
                "and Courses. Instructor=Korth\n" +
                "and Courses. Course_number=Grades. Course_number\n" +
                "and Grades.grade=A\n" +
                "Which of the following sets is computed by the above query?",
                "Names of students who have got an A grade in all courses taught\n" +
                        "by Korth",
                "Names of students who have got an A grade in all courses",
                "Name of students who have got an A grade in at least one of the\n" +
                        "courses taught by Korth",
                "None of the above",3,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm2);
        Question dm3 = new Question("Consider the following relation schema pertaining to a students\n" +
                "database:" +
                "Student(rollno,name,address)\n" +
                "Enroll(rollno,courseno, coursename)\n" +
                "where the primary keys are shown underlined. The number of tuples\n" +
                "in the student and Enroll tables are 120 and 8 respectively. What are\n" +
                "the maximum and minimum number of tuples that can be present in\n" +
                "(Student*Enroll), where ‘*’ denotes natural join?",
                "8,8",
                "120,8",
                "960,8",
                "960,120",1,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm3);
        Question dm4 = new Question("It is desired to design an object-oriented employee record system for a\n" +
                "company. Each employee has a name, unique id and salary. Employees\n" +
                "belong to different categories and their salary is determined by their\n" +
                "category. The functions get Name., getld and compute Salary are\n" +
                "required. Given the class hierarchy below, possible locations for these\n" +
                "functions are:\n" +
                "(i) getld is implemented in the superclass\n" +
                "(ii) getld is implemented in the suclass\n" +
                "(iii) getName is an abstract function in the superclass\n" +
                "(iv) getName is implemented in the superclass\n" +
                "(v) getName is implemented in the subclass\n" +
                "(vi) getSalary is an abstract function in the superclass\n" +
                "(vii) getSalary is implemented in the superclass\n" +
                "(viii) getSalary is implemented in the subclass" +
                "employee -> manager" +
                "employee-> engineer" +
                "employee -> secretary",
                "i,iv,vi,viii",
                "i,iv,vii",
                "i,iii,v,vi,viii",
                "ii,v,viii",1,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm4);
        Question dm5 = new Question("The relation scheme student Performance (name, courselNo, rollNo,\n" +
                "grade) has the following functional dependencies:\n" +
                "name, courseNo ->grade\n" +
                "RollNo, courseNo -> grade\n" +
                "name -> rollNo\n" +
                "rollNo -> name\n" +
                "The highest normal form of this relation scheme is",
                "2NF",
                "3NF",
                "BCNF",
                "4NF",1,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm5);
        Question dm6 = new Question("The order of an internal node in a B* tree index is the maximum\n" +
                "number of children it can have. Suppose that a child pointer takes 6\n" +
                "bytes, the search field value takes 14 bytes., and the block size is 512\n" +
                "bytes. What is the order of the internal node?",
                "24",
                "25",
                "26",
                "27",3,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm6);
        Question dm7 = new Question("The employee information in a company is stored in the relation\n" +
                "Employee (name, sex, salary, deptName)\n" +
                "Consider the following SQL query\n" +
                "select deptname\n" +
                "from Employee\n" +
                "where sex=‘M’\n" +
                "group by deptName\n" +
                "having avg (salary)>\n" +
                "(select avg(salary)from Employee)\n" +
                "It returns the names of the department in which",
                "the average salary is more than the average salary in the company",
                "the average salary of male employees is more than the average\n" +
                        "salary of all male employees in the company",
                "the average salary of male employees is more than the average\n" +
                        "salary of employees in the same department",
                "the average salary of made employees is more than the average\n" +
                        "salary in the company",4,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm7);
        Question dm8 = new Question("Which one of the following is a key factor for preferring B+-trees to\n" +
                "binary search trees for indexing database relation?",
                "Database relations have a large number of record",
                "Database relations are sorted on the primary key",
                "B+-trees require less memory than binary search trees",
                "Data transfer from disks is in blocks",4,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm8);
        Question dm9 = new Question("Which-one of the following statements about normal forms is FALSE?",
                "BCNF is stricter than 3 NF",
                "Loss less, dependency-preserving decomposition into 3 NF is\n" +
                        "always possible",
                "Loss less, dependency-preserving decomposition into BCNF is\n" +
                        "always possible",
                "Any relation with two attributes is BCNF",3,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm9);
        Question dm10 = new Question("Let E1 and E2 be two entities in an E/R diagram with simple singlevalued\n" +
                "attributes. R1 and R2 are two relationships between E1 and E2\n" +
                "where R1 is one-to-many and R2 is many-to-many. R1 and R2 do not\n" +
                "have any attributes of their own. What is the minimum number of\n" +
                "tables required to represent this situation in the relational model?",
                "2",
                "3",
                "4",
                "5",2,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dm10);

        Question dH1 = new Question("The following table has two attributes A and C where A is the\n" +
                "primary key and C is the foreign key referencing A with on-delete\n" +
                "cascade.\n" +
                "A\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "5\n" +
                "7\n" +
                "9\n" +
                "6\n" +
                "C\n" +
                "4\n" +
                "4\n" +
                "3\n" +
                "2\n" +
                "2\n" +
                "5\n" +
                "4\n" +
                "The set of all tuples that must be additionally deleted to preserve\n" +
                "referential integrity when the tuple (2,4) is deleted is:",
                "(3,4) and (6,4)",
                "(5,2) and (7,2)",
                "(5,2)(7,2) and (9,5)",
                "1",3,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH1);
        Question dH2 = new Question("The relation book (title, price) contains the titles and prices of\n" +
                "different books. Assuming that no two books have the same price,\n" +
                "what does the following SQL\n" +
                "select title\n" +
                "from book as B" +
                "where (select count(*)\n" +
                "from book as T\n" +
                "where T. price>B.Price)<5",
                "Titles of the four most expensive books",
                "Title of the fifth most inexpensive book",
                "Title of the fifth most expensive book",
                "Titles of the five most expensive books",4,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH2);
        Question dH3 = new Question("Consider a relation scheme R = (A,B,C,D,E,H) on which the\n" +
                "following functional dependencies hold:\n" +
                "{A -> B, . BC -> D, E -> C,D -> A}\n" +
                "What are the candidate keys of R?",
                "AE,BE",
                "AE,BE,DE",
                "AEH,BEH,BCH",
                "AEH,BEH,DEH",4,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH3);
        Question dH4 = new Question("Consider the following log sequence of two transactions on a bank\n" +
                "account, with initial balance 12000,that transfer 2000 to a mortgage\n" +
                "payment and, then apply a 5% interest.\n" +
                "1. T1 start\n" +
                "2. T1 B old = 12000 new = 10000\n" +
                "3. T1 M old = 0 ne = 2000\n" +
                "4. T1 commit\n" +
                "5. T2 start\n" +
                "6. T2 B old = 10000 new = 10500\n" +
                "7. T2 commit\n" +
                "Suppose the database system crashed just before log record 7 is\n" +
                "written. When the system is restarted, which one statement is true of\n" +
                "the recovery procedure?",
                "We must redo log record 6 to set B to 10500",
                "We must undo log record 6 to set B to 10000 and then redo log\n" +
                        "records 2 and 3",
                "We need not redo log records 2 and 3 because transaction T1 has\n" +
                        "committed",
                "We can apply redo and undo operations in arbitrary order because\n" +
                        "they are idempotent",3,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH4);
        Question dH5 = new Question("Consider the relation account (customer, balance) where customer\n" +
                "is a primary key and there are no mall values. We would like to" +
                "rank customers according to decreasing balance. The customer with\n" +
                "the largest balance gets rank 1. Ties are not broken but ranks are\n" +
                "skipped: if exactly two customers have the largest balance they each\n" +
                "get rank 1 and rank 2 is not assigned.\n" +
                "Query 1 : Select A. customer, count (B. customer) from account A,\n" +
                "account B where A. customer\n" +
                "Query 2 : Select A. customer, 1+count(B. customer)from account\n" +
                "A, accountB where A, balance<B, balance 7 group by A.\n" +
                "customer\n" +
                "Consider these statements about Query 1 and Query 2." +
                "1. Query 1will produce the same row set as Query 2 for some but\n" +
                "not all databases\n" +
                "2. Both Query 1 Query 2 are correct implementations of the\n" +
                "specification\n" +
                "3. Query 1 is a correct implementation of the specification but\n" +
                "Query 2 is not\n" +
                "4. Neither query 1 nor Query 2 is a correct implementation of the\n" +
                "specification\n" +
                "5. Assigning rank with a pure relational Query takes less time than\n" +
                "scanning in decreasing balance order the assigning ranks using\n" +
                "ODBC\n" +
                "Which two of the above statements are correct?",
                "2 AND 5",
                "1 AND 3",
                "1 AND 4",
                "3 AND 5",3,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH5);
        Question dH6 = new Question("Consider the relation enrolled (student, course) in which student,\n" +
                "course) is the primary key, and the relation paid (student, amount)\n" +
                "where student is the primary key . Assume no null values and no\n" +
                "foreign keys or integrity constraints. Given the following four queries:\n" +
                "Query 1: Select from enrolled where student in (select student form\n" +
                "paid)\n" +
                "Query 2: Select student from paid where student in (select student\n" +
                "from enrolled)\n" +
                "Query 3: Select E. student from enrolled E, paid P where E.\n" +
                "student= P student\n" +
                "Query 4: Se3lect student from paid where exists (select*from\n" +
                "enrolled where enrolled student=paid.student\n" +
                "Which one of the following statements is correct?",
                "All queries return identical row sets for any database",
                "Query 2 and Query 4 return identical row sets for all databases\n" +
                        "but there exist database for which Query 1 and Query 2 retrun\n" +
                        "different row sets",
                "There exist databases for which Query 3 returns strictly fewer\n" +
                        "rows than Query 2",
                "There exist databases for which Query 4 will encounter an\n" +
                        "intergrity violation at runtime",2,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH6);
        Question dH7 = new Question("Consider the table employee (empId, name, department, salary) and\n" +
                "the two queries Q1,Q2 below. Assuming that department 5 has more\n" +
                "than one employee, and we want to find the employees who get higher" +
                "salary than anyone in the department 5, which one of the statements\n" +
                "is TRUE for any arbitrary employee table?\n" +
                "Q1 : Select e. empId\n" +
                "From employee e\n" +
                "Where not exists\n" +
                "(Select*From employee s Where s. department=”5” and\n" +
                "s.salay>=e.salary)\n" +
                "Q.2 : Select e. empId\n" +
                "From employee e\n" +
                "Where e.salary>Any\n" +
                "(Select distinct salary From employee s Where s. department=”5”)",
                "Q1 is the correct query",
                "Q2 is the correct query",
                "Both Q1 and Q2 produce the same answer",
                "Neither Q1 nor Q2 is the correct query",2,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH7);
        Question dH8 = new Question("Which one of the following statements is FALSE?",
                "Any relation with two attributes is in BCNF",
                "A relation in which every key has only one attribute is in 2NF",
                "A prime attribute can be transitively dependent on a key in 3NF\n" +
                        "relation",
                "A prime attribute can be transitively dependent on a key in a\n" +
                        "BNCF relation",4,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH8);
        Question dH9 = new Question("Consider the following schedules involving two transactions. Which\n" +
                "one of the following statements is TRUE?\n" +
                "S1:r1(X);r1(Y);r2 (X);r2(Y);w2(Y);w1(X)" +
                "S2:r1(X);r2(X);r2 (Y);w2(Y);r1(Y);w1(X)",
                "Both S1 and S2 are conflict serializable",
                "S1 is conflict serializable and S2 is not conflict serializable",
                "S1 is not conflict serializable and S2 is conflict serializable",
                "Both S1 and S2 are not conflict serializable",3,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH9);
        Question dH10 = new Question("Consider the following relational schemes for a library database:\n" +
                "Book (Title, Author, Catalog_no, Publisher, Year, price)" +
                "Collection (Title, Author, Catalog_no)\n" +
                "Which the following functional dependencies:\n" +
                "I. Title Author \"Catalog_no\n" +
                "II. Catalog_no \"Title Author Publisher Year\n" +
                "III. Publisher Title Year\"price\n" +
                "Assume {Author, Title} is the key for both schemes: which of the\n" +
                "following statements is true?",
                "Both Book and Collection are in BCNF",
                "Both Book and Collection are in 3NF only",
                "Book is in 2NF and Collection is in 3NF",
                "Both Book and Collection are in 2NF only",3,Question.DIFFICULTY_MEDIUM,Category.DBMS);
        addQuestion(dH10);

    }



    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID,question.getCategory_id());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME,null);


        if(c.moveToFirst()){
            do{
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            }while(c.moveToNext());
        }
        c.close();
        return categoryList;
    }


    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategory_id(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }



    public ArrayList<Question> getQuestions(int categoryID , String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " + " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID),difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategory_id(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}

