package org.example.project_2;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



import java.io.File;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class HelloApplication extends Application {

    private Button nextButton=new Button("Next");
    private Button previousButton=new Button("Previous");
    // current index for next and previous button
    int currentIndex=-1;
    // create table view
    LinkedList<Section> sections;
    @Override
    public void start(Stage stage) throws IOException {


        // create border pane
        BorderPane root = new BorderPane();


        // create menu Item and menu bar

        // create menu bar
        MenuBar menubar =new MenuBar();
        //create menu
        Menu menu_Read =new Menu("Read");
        // create menu item
        MenuItem menuItem_File =new MenuItem("File");
        MenuItem menuItem_Keyboard =new MenuItem("Keyboard");


        // add the menu item to menu
        menu_Read.getItems().addAll(menuItem_File,menuItem_Keyboard);
        // add the menu in the menu bar
        menubar.getMenus().add(menu_Read);
        // create liable and button
        Label fl = new Label("File:");
        Button bl = new Button("Load");
        Label path =new Label();


        // crate border bane for loud
        BorderPane loudP=new BorderPane();
        loudP.setPadding(new Insets(10));
        loudP.setLeft(fl);
        loudP.setRight(bl);
        loudP.setCenter(path);

        // create vbox to add the loud and menu bar
        VBox vBox =new VBox();
        vBox.getChildren().addAll(menubar,loudP);

        root.setTop(vBox);


        // crate border bane for next and previous
        BorderPane bp=new BorderPane();
        bp.setPadding(new Insets(30));
        bp.setLeft(nextButton);
        bp.setRight(previousButton);
        // disable next and previous
        previousButton.setDisable(true);
        nextButton.setDisable(true);

        root.setBottom(bp);
        // create text area
       TextArea textArea=new TextArea();
       textArea.setEditable(false);
        textArea.setPadding(new Insets(15));

       root.setCenter(textArea);

        // program the previous button
        previousButton.setOnAction(e -> {
            // Check if there is data and not at the beginning
            if ( currentIndex > 0) {
                currentIndex--; // Move to the previous item

                // Check if the current index is at the beginning
                if (currentIndex == 0) {
                    previousButton.setDisable(true); // Disable the previousButton at the beginning
                }

        textArea.setText(sections.get(currentIndex).toString());

            }
            chick();
        });


        nextButton.setOnAction(e -> {
            // Check if there is data and not at the end
            if ((currentIndex+1)<sections.size() ) {
                currentIndex++; // Move to the next item

                textArea.setText(sections.get(currentIndex).toString());

            }
            chick();
        });



        // program the read menu item
        menuItem_File.setOnAction(e->{

            stage.show();
        });



        // program the loud button
        FileChooser fileChooser =new FileChooser();
        bl.setOnAction(e->{

                    // create file chooser
                    File file_chooser =fileChooser.showOpenDialog(new Stage());
            // create link list for the sections
            sections=new LinkedList<Section>();
                    // create stack
                    Stack stack=new Stack();

            boolean isInfix =false; // if infix then true postfix then false ;

            int seSize=0;


                    if (file_chooser != null) {
                        try {
                            // rad the file and chick the validation
                            Scanner read_File= new Scanner(file_chooser);
                            readwile: while(read_File.hasNextLine()) {
                                        // read each line in the file
                                        String line =read_File.nextLine().trim();// remove the space
                                        if(!line.isEmpty()) {
                                            // chick the validation for the equation
                                            if (line.startsWith("<equation>") && line.endsWith("</equation>")) {
                                                // split the equation
                                             String equation  = line.substring(line.indexOf(">")+1,line.lastIndexOf("<"));
                                             // chick the equation if contain alphabet or not
                                             if(equation.matches("[a-zA-Z]")){
                                                 System.out.println(equation);
                                                  // print the error and break
                                                 path.setText("Error choose another file(equation contain alphabet)!");
                                                 break readwile;

                                             }
                                              // store the equation in the section
                                             Section s=sections.get(sections.size()-1);
                                                if(isInfix){ // add to the infix
                                                    s.addInfix(equation);
                                                }else { // add to the postfix
                                                    s.addPostfix(equation);
                                                }

                                                continue ;
                                            }

                                            // separate each tag
                                            String tag = line.substring(line.indexOf("<"), line.indexOf(">") + 1);

                                            if(!tag.isEmpty())
                                            switch (tag){

                                                case "<section>":{
                                                        // add the tag in the stack
                                                        stack.push("<section>");
                                                        // create new section
                                                       sections.add(new Section());
                                                }    break;

                                                case "</section>": {
                                                    if(stack.peek().equals("<section>")) {
                                                        // chick if stack empty or not
                                                       stack.pop();
                                                        break ;
                                                    }
                                                    // print the error
                                                    path.setText("Error choose another file(the file invalid!");
                                                    break readwile;

                                                }
                                                case "<postfix>":{
                                                    // push the tag in the stack
                                                    isInfix=false;
                                                    stack.push("<postfix>");

                                                }    break;

                                                case "</postfix>":{
                                                    // chick if the stack is empty
                                                    if(stack.peek().equals("<postfix>")) {
                                                        // pop the tag
                                                        stack.pop();
                                                        break ;
                                                    }
                                                    // print the error and break
                                                    path.setText("Error choose another file(the file invalid!");
                                                    break readwile;
                                                }


                                                case "<infix>":{
                                                    // add the tag in the stack
                                                    isInfix=true;
                                                    stack.push("<infix>");

                                                }    break;


                                                case "</infix>": {
                                                    // chick if the stack empty or not
                                                    if(stack.peek().equals("<infix>")) {
                                                        // pop the tag
                                                       stack.pop();
                                                    break ;
                                                }
                                                    // print the error message
                                                    path.setText("Error choose another file(the file invalid!");
                                                    break readwile;
                                                }


                                                default:{
                                                    // if the tage is not section , infix or posfix
                                                    if(tag.charAt(1)=='/') {
                                                        // chick if the stack empty
                                                        if (stack.isEmpty()) {
                                                            path.setText("Error choose another file(the file invalid!");
                                                            break readwile;
                                                        }
                                                        // print the error
                                                        System.out.println(tag);
                                                        System.out.println("defult delete :"+stack.pop());
                                                        break ;
                                                    }
                                                    // push the tag in the stack
                                                   stack.push(tag);
                                                }
                                            }
                                        }
                                    }
                                    // chick if the stack empty or not
                                    if(stack.isEmpty()) {
                                        // the file is valid
                                        path.setText(file_chooser.getPath());

                                        nextButton.setDisable(false);
                                    }
                                    else
                                        path.setText("Error choose another file(the file invalid!");

                                }catch (IOException ex){
                                    path.setText("Error choose another file(the file invalid!");
                                    ex.printStackTrace();
                                }
                            }
                }
        );

        // create new stage
        Stage   userstage = new Stage();

        // create grid pane
        GridPane userGridPane = new GridPane();
        userGridPane.setPadding(new Insets(30));
        userGridPane.setHgap(10);
        userGridPane.setVgap(10);

        // Create labels and text fields for book information
        Label POrIN_Label = new Label("Postfix or infix :");
        TextField user_TextField = new TextField();
        Label Equation_label = new Label("Enter Equation:");
        TextArea Equation_TextField = new TextArea();
        Equation_TextField.setEditable(false);

        // create combo box
        ComboBox<String> cbo =new ComboBox<>();
        cbo.getItems().setAll("postfix","infix");

        // Add labels and text fields to the GridPane
        userGridPane.add(POrIN_Label, 0, 0);
        userGridPane.add(cbo, 0, 1);
        userGridPane.add(Equation_label, 0, 2);
        userGridPane.add(user_TextField, 0, 3);
        userGridPane.add(new Label("Answer"), 0, 4);
        userGridPane.add(Equation_TextField, 0, 5);

        // create button
        Button print = new Button("Print");
        userGridPane.add(print, 0, 8);



        Scene scene2 =new Scene(userGridPane,300,320);

        userstage.setScene(scene2);


        menuItem_Keyboard.setOnAction(e->{





            print.setOnAction(r->{

                String s =user_TextField.getText();
                if ("infix".equals(cbo.getValue())){



                    try {
                    StringBuilder st =new StringBuilder();
                        st.append("==>");
                        st.append(Calculator.infixToPostfix(s));
                        st.append("==>");
                        st.append(Calculator.evaluatePost(Calculator.infixToPostfix(s)));

                        Equation_TextField.setText(st.toString());
                    }catch (Exception ex){

                        Equation_TextField.setText("error try again !");
                    }


                }else if("postfix".equals(cbo.getValue())){



                    try {

                        StringBuilder st =new StringBuilder();
                        st.append("==>");
                        st.append(Calculator.posfixToPrefix(s));
                        st.append("==>");
                        st.append(Calculator.evaluateprefix(Calculator.posfixToPrefix(s)));

                        Equation_TextField.setText(st.toString());

                    }catch (Exception ex){
                        Equation_TextField.setText("error try again !");
                    }

                }else {Equation_TextField.setText("error try again !");}

            });

            userstage.show();
        });




        Scene scene = new Scene(root, 420, 440);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    // chick status of next and previous
    void chick() {

        if(currentIndex+1 <sections.size())
            nextButton.setDisable(false);
        else
            nextButton.setDisable(true);

        if(currentIndex>0)
            previousButton.setDisable(false);
        else
            previousButton.setDisable(true);

    }

    public static void main(String[] args) {
        launch();
    }
}