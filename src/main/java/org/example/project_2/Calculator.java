package org.example.project_2;




public class Calculator {



    // this method to convert infix equation ot postfix equation
    public static String infixToPostfix(String infixEquation) {
        // create string builder to store the answer
        StringBuilder postfix = new StringBuilder();
        // create stack to store each operator
        Stack operatorStack = new Stack();
        // iterate throw each token
        for (char token : infixEquation.toCharArray()) {
            // store the priority of the operator
            int priority = order(token);
            // chick if the token is space
            if (token ==' ')
                continue;
            if( (token=='(' || token== ')' || priority>-1 ) && !postfix.isEmpty() &&  postfix.charAt(postfix.length()-1) !=' '){
                postfix.append(' ');// give space to separate each number
            }
            // chick if the token is digit or not
            if (Character.isLetterOrDigit(token) || token=='.') {
                postfix.append(token); // append the digit in the ans
            }
            else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') { // pop all the operator from the stack
                while (!operatorStack.isEmpty() && (char)operatorStack.peek() != '(') {
                    postfix.append((char)operatorStack.pop());
                }
                operatorStack.pop();  // Discard the '('
            } else {
                /* pop all the high priority operator from the stack */
                    while (!operatorStack.isEmpty() &&
                            (order((char)operatorStack.peek()) >=priority)  )
                        postfix.append(operatorStack.pop());

                    operatorStack.push(token);// push the operator
                    }
        }
        // pop the all operator from the stack
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }
        return postfix.toString();
    }



    // this method to give the priority for each operator
    private static int order(char op) {

        switch (op) {
            case '+': case '-': return 1;

            case '*': case '/': return 2;

            case '^':  return 3;
            default:
                return -1;  // Invalid operator
        }
    }



    // this method to evaluate the postfix equation
    public static double evaluatePost(String posfix ){

        // this stack to store the operand and the answer
        Stack stack =new Stack();

        int size =posfix.length();

        for(int i=0 ;i<size;++i){
            // store the operand
            char operand =posfix.charAt(i);
            // the priority to chick if the token is operator or not
            int priority = order(operand);
            if (operand==' ')continue;

            System.out.println("first:"+operand);
            if(priority >0 )// the token is operatoer
            {
                double operand2 =(double)stack.pop();
                double operand1 =(double)stack.pop();
                // store the answer to the stack

                stack.push( calc(operand1, operand2,operand));
            }

            if (Character.isDigit(operand)){// the token is digit
                // store the digit
                double num = operand -'0';

                char next =' ';
                // store the next character
                if (i+1<size)  next =posfix.charAt(i+1);


                // to store all the number if it has more than 1 digit
               w1: while ((i+1<size) && next!=' ' && (order(next) ==-1) ){

                    next=posfix.charAt(i+1);

                    if (Character.isDigit(next) ){ // the next char is the rest of the number
                        num*=10;
                        num+=next-'0';

                    }else if(next=='.'){ // the flout point

                        StringBuilder st =new StringBuilder();
                        st.append((int) num);
                        st.append('.');

                        next=posfix.charAt(++i+1);

                        // to store the flouting digits
                        while ((i+1<size) && next!=' ' && (order(next) ==-1) ){

                            next=posfix.charAt(i+1);

                            if (Character.isDigit(next) ){ // the next char is the rest of the number

                                st.append(next);

                            }else {
                         num =  Double.parseDouble(st.toString());

                             
                            }
                            ++i;
                        }
                    }
                    ++i;
                }
                stack.push(num);

            }
        }

        if(stack.Size()==2 && (order(posfix.charAt(posfix.length()-1))>0)){

            double operand2 =(double)stack.pop();
            double operand1 =(double)stack.pop();
            // store the answer to the stack

           return ( calc(operand1, operand2,posfix.charAt(posfix.length()-1)));


        }



     return  (double) stack.pop();
    }

    // this method to evaluate the prefix equation
    public static double evaluateprefix(String prefix ){

        // this stack to store the operand and the answer
        Stack stack =new Stack();

        int size =prefix.length();


        for(int i=size-1 ;i>=0;--i){
            // store the operand
            char operand =prefix.charAt(i);
            // the priority to chick if the token is operator or not
            int priority = order(operand);

            if (operand==' ')continue;

            if(priority >0)// the token is operatoer
            {

                double operand1 =(double)stack.pop();
                double operand2 =(double)stack.pop();


                // store the answer to the stack
                stack.push(calc(operand1, operand2,operand));
            }

            if (Character.isDigit(operand) || operand=='.') {// the token is digit

                StringBuilder st =new StringBuilder();
                st.append(operand);
                while (i-1>=0 && (Character.isDigit(prefix.charAt(i-1)) || prefix.charAt(i-1)=='.')){
                    --i;
                   operand=prefix.charAt(i);
                    st.insert(0, operand);

                }

                double num= Double.parseDouble(st.toString());

                stack.push(num);

            }
        }
        return  (double) stack.pop();
    }




    // this method just to find the result of the operator
    private static double calc(double operand1 , double operand2, char operator ){

        switch (operator){

            case '*': return (operand1 * operand2) ;

            case '/': return (operand1 / operand2) ;

            case '+': return (operand1 + operand2) ;

            case '-': return (operand1 - operand2) ;

            case '^': return  Math.pow(operand1,operand2);


        }
        return Double.MAX_VALUE; // invalid operator
    }




    // this method to convert postfix to infix
    public static String posfixToPrefix(String postfix){

        int size=postfix.length();
        Stack stack =new Stack();

        for(int i=0 ;i<size;++i){

            char tockn =postfix.charAt(i);

            if(Character.isDigit(tockn)) {// chick if the token is digit

                StringBuilder st =new StringBuilder();
                st.append(tockn);
                while (i+1< size && (Character.isDigit(postfix.charAt(i+1)) || postfix.charAt(i+1)=='.') ){
                    ++i;
                    tockn=postfix.charAt(i);
                    st.append(tockn);

                }
                stack.push(st.toString()+" ");

            }else if (order(tockn)>0){ // the token is operator

                String operand1 =(String) stack.pop();
                String operand2 = (String) stack.pop();

                stack.push(tockn+" "+operand2+operand1+" ");

            }
        }

return (String) stack.pop();

    }









}



