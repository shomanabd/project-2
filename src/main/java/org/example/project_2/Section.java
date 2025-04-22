package org.example.project_2;

import java.util.LinkedList;

public class Section {

    private LinkedList<String> postfix ;// list to store postfix equations

    private  LinkedList<String>  infix ; // list to store infix equations

    public Section(){
      postfix=new LinkedList<String>();
      infix=new LinkedList<String>();


    }

    public void addPostfix(String eq){

        postfix.add(eq);
    }

    public void addInfix(String eq){

        infix.add(eq);
    }


    @Override
    public String toString() {

        StringBuilder st =new StringBuilder();
        st.append("Infix:\n");
        for (String s :infix){
            st.append("*"+s);
            st.append("==>");
            st.append(Calculator.infixToPostfix(s));
            st.append("==>");
            st.append(Calculator.evaluatePost(Calculator.infixToPostfix(s)));
            st.append("\n");
        }

        st.append("Postfix:\n");
        for (String s :postfix){
            st.append("*"+s);
            st.append("==>");
            st.append(Calculator.posfixToPrefix(s));
            st.append("==>");
            st.append(Calculator.evaluateprefix(Calculator.posfixToPrefix(s)));
            st.append("\n");
        }

        return st.toString();

    }
}
